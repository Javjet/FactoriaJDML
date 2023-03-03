package com.jdml.fp2.factoriajdml2;

import Clases.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.internal.TransactionImpl;
import java.sql.*;
import java.sql.Date;

public class InsertarDatosHibernate {
    static SessionFactory sessionFactory = getCurrentSessionFromJPA();
    public static void main(String[] args){

        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("prueba");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        insertUsuario(transaction,crearUsuario());
        entityManager.close();
        entityManagerFactory.close();
    }
    public static SessionFactory getCurrentSessionFromJPA() {
        // JPA and Hibernate SessionFactory example
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("prueba");
        EntityManager entityManager = emf.createEntityManager();
        // Get the Hibernate Session from the EntityManager in JPA
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        SessionFactory factory = session.getSessionFactory();
        return factory;
    }

    public static void insertUsuario(EntityTransaction transaction,Usuario usuario){

        try{
            transaction.begin();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(usuario);
            tx.commit();
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    public static Usuario crearUsuario(){
        Usuario usuario =new Usuario();
        String nombre;
        String apellidos;
        String email;
        String nombreUsuario;
        String contraseña;
        String rol;
        double puntuacion;
        int familiaProfesional;
        int telefono;
        int id_Centro;
        int id_Usuario;



        do {
            nombre=Leer.pedirCadena("Introduzca el nombre");
        }while (nombre.length()>30);
        do {
            apellidos=Leer.pedirCadena("Introduzca los apellidos");
        }while (apellidos.length()>50);
        do {
            email=Leer.pedirCadena("Introduzca el email");
        }while (email.length()>40);
        do {
            nombreUsuario=Leer.pedirCadena("Introduzca el nombre de usuario");
        }while (nombreUsuario.length()>30);
        do {
            contraseña=Leer.pedirCadena("Introduzca la contraseña");
        }while (contraseña.length()>30);
        do {
            rol=Leer.pedirCadena("Introduzca el rol (miembro o coordinador)");
        }while (!rol.equals("miembro") && !rol.equals("coordinador"));
        do {
            telefono=Leer.pedirEntero("Introduzca un telefono");
            if (String.valueOf(telefono).length()!=9){
                System.out.println(" Introduzca un telefono de 9 digitos de longitud, ejemplo: 999111333");
            }
        }while (String.valueOf(telefono).length()!=9);
        do {
            familiaProfesional=Leer.pedirEntero("Introduzca el rol (miembro o coordinador)");
        }while (!rol.equals("miembro") && !rol.equals("coordinador"));
        do {
            id_Centro=Leer.pedirEntero("Introduzca el rol (miembro o coordinador)");
        }while (!rol.equals("miembro") && !rol.equals("coordinador"));
        do {
            id_Usuario=Leer.pedirEntero("Introduzca el rol (miembro o coordinador)");
        }while (id_Usuario!=0);


        usuario.setNombre("");
        usuario.setApellidos("");
        usuario.setEmail("");
        usuario.setNombreUsuario("");
        usuario.setContraseña("");
        usuario.setRol("miembro");
        usuario.setFamiliaProfesional(1);
        usuario.setPuntuacion(0.0);
        usuario.setTelefono(999666333);

        return usuario;
    }


}


