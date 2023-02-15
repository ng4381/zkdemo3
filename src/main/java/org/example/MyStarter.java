package org.example;

import org.example.sidepage.SidebarPageConfigAjaxbasedImpl;

public class MyStarter {
    public static void main(String[] args) {

        SidebarPageConfigAjaxbasedImpl sp = new SidebarPageConfigAjaxbasedImpl();
        sp.getPages().forEach(System.out::println);

        /*
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

        Address address = Address.builder()
                .zip("123562")
                .city("Haifa")
                .street("Usha")
                .house(1)
                .flat(1)
                .build();



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
