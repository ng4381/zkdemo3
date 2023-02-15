package org.example.viewmodel;

import org.example.dto.CompanyDto;
import org.example.dto.FilialDto;
import org.example.entity.Address;
import org.example.entity.Company;
import org.example.service.CompanyService;
import org.example.service.FilialService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

public class CompanyViewModel extends SelectorComposer<Component> {

    private CompanyService companyService;
    private FilialService filialService;

    @Wire
    private Listbox companiesListBox;

    @Wire
    private Listbox filialsListBox;

    @Wire
    private Radiogroup radioBtn;

    @Wire
    private Textbox companyNameTextBox;

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


    ListModelList<CompanyDto> companiesDataModel = new ListModelList<>();
    ListModelList<FilialDto> filialsDataModel = new ListModelList<>();

    public void init() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        companyService = new CompanyService(sessionFactory);
        filialService = new FilialService(sessionFactory);

        companiesListBox.setModel(companiesDataModel);
        filialsListBox.setModel(filialsDataModel);
        updateCompaniesDataModel();
    }

    private void updateCompaniesDataModel() {
        companiesDataModel.clear();
        companiesDataModel.addAll(companyService.getAllCompaniesDto());

        filialsDataModel.clear();
    }
    private void emptyAllFields() {
        companyNameTextBox.setValue("");
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

    @Listen("onClick = #saveCompany")
    public void saveCompany() {
        Address address = Address.builder()
                .zip(zip.getValue())
                .city(city.getValue())
                .street(street.getValue())
                .house(house.getValue())
                .flat(flat.getValue())
                .build();

        Company company = Company.builder()
                .name(companyNameTextBox.getValue())
                .address(address)
                .build();

        companyService.saveCompany(company);
        updateCompaniesDataModel();
        emptyAllFields();
    }

    @Listen("onSelect = #companiesListBox")
    public void showDetail() {
        CompanyDto company = companiesListBox.getSelectedItem().getValue();
        filialsDataModel.clear();
        filialsDataModel.addAll(filialService.getFilialsDtoByCompanyId(company.getId()));
    }

}
