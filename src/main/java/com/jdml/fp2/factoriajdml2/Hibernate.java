package com.jdml.fp2.factoriajdml2;

import Clases.CentrosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.hibernate.HibernateException;

public class Hibernate {
    static EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
    static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        eliminacionCentro();
    }
    public static void leerCentros(){
        CentrosEntity centros = em.find(CentrosEntity.class, 1);
        System.out.println(centros.getNombre());
    }

    public static void modificarCentro(){
        try {
            CentrosEntity centro;
            EntityTransaction transaction = em.getTransaction();
            Query query = em.createQuery("Select c from CentrosEntity c where c.autoId = 1");
            transaction.begin();
            centro = (CentrosEntity) query.getSingleResult();
            centro.setContacto("Sandra Vela");
            em.persist(centro);
            transaction.commit();
            em.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    public static void insercionCentro(){
        try {
            CentrosEntity centro = new CentrosEntity();
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            centro.setIdCentro(2);
            centro.setNombre("IES Domingo Miral");
            centro.setWeb("iesdomingomiral.com");
            centro.setContacto("Arantxa");
            em.persist(centro);
            transaction.commit();
            em.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }


    public static void eliminacionCentro(){
        try {
            CentrosEntity centro;
            EntityTransaction transaction = em.getTransaction();
            Query query = em.createQuery("Select c from CentrosEntity c where c.autoId = 3");
            transaction.begin();
            centro = (CentrosEntity) query.getSingleResult();
            em.remove(centro);
            transaction.commit();
            em.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }
}
