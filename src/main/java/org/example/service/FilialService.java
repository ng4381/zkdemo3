package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.example.dto.FilialDto;
import org.example.entity.Company;
import org.example.entity.Filial;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FilialService {
    private final SessionFactory sessionFactory;

    public void saveFilial(Filial filial) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(filial);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Filial getFilialById(Long id) {
        EntityManager entityManager2 = sessionFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        Filial filial = entityManager2.find(Filial.class, id);
        entityManager2.getTransaction().commit();
        entityManager2.close();
        return filial;
    }

    public List<FilialDto> getAllFilialsDto() {
        EntityManager entityManager2 = sessionFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        Query query = entityManager2.createQuery("FROM Filial", Filial.class);
        List<Filial> result = query.getResultList();
        entityManager2.getTransaction().commit();
        entityManager2.close();
        return result.stream().map(
                filial -> FilialDto.builder()
                        .id(filial.getId())
                        .name(filial.getName())
                        .address(filial.getAddress().toString())
                        .company(filial.getCompany().getName())
                        .build()
        ).collect(Collectors.toList());
    }

    public List<FilialDto> getFilialsDtoByCompanyId(Long companyId) {
        EntityManager entityManager2 = sessionFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        Query query = entityManager2.createQuery("FROM Filial f WHERE f.company.id = :companyId", Filial.class);
        System.out.println("NIKITA companyId = " + companyId.intValue());
        query.setParameter("companyId", companyId);
        List<Filial> result = query.getResultList();
        entityManager2.getTransaction().commit();
        entityManager2.close();
        return result.stream().map(
                filial -> FilialDto.builder()
                        .id(filial.getId())
                        .name(filial.getName())
                        .address(filial.getAddress().toString())
                        .company(filial.getCompany().getName())
                        .build()
        ).collect(Collectors.toList());
    }
}
