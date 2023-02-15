package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.example.dto.CompanyDto;
import org.example.entity.Address;
import org.example.entity.Company;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CompanyService {
    private final SessionFactory sessionFactory;

    public void saveCompany(Company company) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Company getCompanyById(Long id) {
        EntityManager entityManager2 = sessionFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        Company company = entityManager2.find(Company.class, id);
        entityManager2.getTransaction().commit();
        entityManager2.close();
        return company;
    }

    public List<CompanyDto> getAllCompaniesDto() {
        EntityManager entityManager2 = sessionFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        Query query = entityManager2.createQuery("FROM Company", Company.class);
        List<Company> result = query.getResultList();
        entityManager2.getTransaction().commit();
        entityManager2.close();
        return result.stream().map(
                company -> CompanyDto.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .address(company.getAddress().toString())
                        .build()
        ).collect(Collectors.toList());
    }
}
