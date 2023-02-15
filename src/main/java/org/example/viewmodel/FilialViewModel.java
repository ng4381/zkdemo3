package org.example.viewmodel;

import org.example.dto.CompanyDto;
import org.example.dto.FilialDto;
import org.example.entity.Address;
import org.example.entity.Company;
import org.example.entity.Filial;
import org.example.service.CompanyService;
import org.example.service.FilialService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.Set;

public class FilialViewModel extends SelectorComposer<Component> {

    private CompanyService companyService;
    private FilialService filialService;

    @Wire
    private Listbox filialsListBox;

    @Wire
    private Listbox companiesListBox;

    @Wire
    private Radiogroup radioBtn;

    @Wire
    private Textbox filialNameTextBox;

    @Wire
    private Textbox zip;
    @Wire
    private Textbox city;
    @Wire
    private Textbox street;
    @Wire
    private Intbox house;
    @Wire
    private Intbox flat;


    ListModelList<FilialDto> filialsDataModel = new ListModelList<>();
    ListModelList<CompanyDto> companiesDataModel = new ListModelList<>();

    public void init() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        companyService = new CompanyService(sessionFactory);
        filialService = new FilialService(sessionFactory);

        filialsListBox.setModel(filialsDataModel);
        companiesListBox.setModel(companiesDataModel);
        updateCompaniesDataModel();

    }

    private void updateCompaniesDataModel() {
        companiesDataModel.clear();
        companiesDataModel.addAll(companyService.getAllCompaniesDto());

        filialsDataModel.clear();
        filialsDataModel.addAll(filialService.getAllFilialsDto());
    }

    private void emptyAllFields() {
        filialNameTextBox.setValue("");
        zip.setValue("");
        city.setValue("");
        street.setValue("");
        house.setValue(0);
        flat.setValue(0);
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        init();
    }

    @Listen("onClick = #saveFilial")
    public void saveFilial() {
        Address address = Address.builder()
                .zip(zip.getValue())
                .city(city.getValue())
                .street(street.getValue())
                .house(house.getValue())
                .flat(flat.getValue())
                .build();

        Set<CompanyDto> selection = ((ListModelList) companiesListBox.getModel()).getSelection();

        if (selection.isEmpty()) {
            throw new RuntimeException("Selection is empty!");
        }
        Long companyId = selection.iterator().next().getId();
        Company company = companyService.getCompanyById(companyId);

        if (company == null) {
            throw new RuntimeException(String.format("Company not found (%s)", companyId));
        }

        Filial filial = Filial.builder()
                .name(filialNameTextBox.getValue())
                .address(address)
                .company(company)
                .build();

        filialService.saveFilial(filial);
        updateCompaniesDataModel();
        emptyAllFields();
    }

}
