package org.example.viewmodel;

import org.apache.commons.lang3.StringUtils;
import org.example.dto.CompanyDto;
import org.example.dto.FilialDto;
import org.example.entity.Address;
import org.example.entity.Company;
import org.example.entity.LegalEntityType;
import org.example.service.AddressService;
import org.example.service.CompanyService;
import org.example.service.FilialService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class CompanyViewModel extends SelectorComposer<Component> {

    private CompanyService companyService;
    private AddressService addressService;
    private FilialService filialService;

    @Wire
    private Listbox companiesListBox;

    @Wire
    private Listbox filialsListBox;

    @Wire
    private Radiogroup legalEntityType;

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
        addressService = new AddressService();
        filialService = new FilialService(sessionFactory);

        companiesListBox.setModel(companiesDataModel);
        filialsListBox.setModel(filialsDataModel);
        updateCompaniesDataModel();

        ListModelList<LegalEntityType> legalEntityTypes = new ListModelList<>();
        legalEntityTypes.addAll(new ArrayList<LegalEntityType>(Arrays.asList(LegalEntityType.values())));

        legalEntityType.setModel(legalEntityTypes);

    }

    private void updateCompaniesDataModel() {
        companiesDataModel.clear();
        companiesDataModel.addAll(companyService.getAllCompaniesDto());

        filialsDataModel.clear();
    }

    private void emptyAllFields() {
        companyNameTextBox.setValue(null);
        zip.setValue(null);
        city.setValue(null);
        street.setValue(null);
        house.setValue(null);
        flat.setValue(null);
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        init();
    }

    @Listen("onClick = #saveCompany")
    public void saveCompany() {
        System.out.println("== FLAT == " + flat);
        System.out.println("== FLAT GET VALUE == " + flat.getValue());
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
                .legalEntityType(legalEntityType.getSelectedItem().getValue())
                .build();

        String addressViolations = addressService.getAllViolations(address);
        String companyViolations = companyService.getAllViolations(company);

        if (!StringUtils.isEmpty(companyViolations)) {
            Messagebox.show(companyViolations);
            return;
        }
        if (!StringUtils.isEmpty(addressViolations)) {
            Messagebox.show(addressViolations);
            return;
        }
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
