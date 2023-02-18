package org.example;

import org.apache.commons.lang3.StringUtils;
import org.example.entity.Address;
import org.example.service.AddressService;
import org.example.sidepage.SidebarPageConfigAjaxbasedImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class MyStarter {
    public static void main(String[] args) {

//        SidebarPageConfigAjaxbasedImpl sp = new SidebarPageConfigAjaxbasedImpl();
//        sp.getPages().forEach(System.out::println);



//        SessionFactory sessionFactory = new Configuration().configure()
//                .buildSessionFactory();

        String strNull = null;

        Optional.ofNullable(strNull).orElse(null);

        /*
        Address address = Address.builder()
                .zip("123562")
                .city("Haifa")
                .street("Usha")
                .house(1)
                .flat(1)
                .build();

        String addressViolations = new AddressService().getAllViolations(address);
        System.out.println(addressViolations);

        System.out.println(new AddressService().getAllViolations(address));

         */

        /*

        Company company = new Company();
        company.setName("qwe");
        company.setAddress(address);

        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(address);
        entityManager.persist(company);
        entityManager.getTransaction().commit();
        entityManager.close();

        EntityManager entityManager2 = sessionFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        Query query = entityManager2.createQuery("FROM Company", Company.class);
        List<Company> result = query.getResultList();
        entityManager2.getTransaction().commit();
        entityManager2.close();
        System.out.println("=====================================================================");
        result.forEach(System.out::println);

         */
    }
}
