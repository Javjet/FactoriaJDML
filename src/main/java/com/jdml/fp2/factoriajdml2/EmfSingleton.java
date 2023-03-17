package com.jdml.fp2.factoriajdml2;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmfSingleton {
    //instancia Singleton
    private static EmfSingleton emfInstancia = new EmfSingleton();
    //Nombre de la unidad de persistencia
    static private final String PERSISTANCE_UNIT_NAME = "default";

    //Creaacion de la factoria
    private EntityManagerFactory emf = null;

    //metodo que devuelve la instancia de la factoria
    public static EmfSingleton getInstance() {
        return emfInstancia;
    }

    private EmfSingleton() {
    }

    //creacion del entity manager a partir de la factoria devuelta
    public EntityManagerFactory getEmf() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
        }
        return this.emf;
    }
}
