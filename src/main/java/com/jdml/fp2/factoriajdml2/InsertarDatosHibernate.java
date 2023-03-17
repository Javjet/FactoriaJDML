package com.jdml.fp2.factoriajdml2;

import Clases.CentrosEntity;
import Clases.FamiliaProfesionalEntity;
import Clases.UsuarioEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InsertarDatosHibernate {
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    static UsuarioEntity usuario =null;

    static CentrosEntity centros=null;

    static FamiliaProfesionalEntity familia=null;
    public static void main(String[] args){

        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        insertCentros(transaction,crearCentro());
        insertUsuario(transaction,crearUsuario());
        insertCFamiliaPro(transaction,crearFamilia());
        entityManager.close();
        entityManagerFactory.close();
    }
    /*public static SessionFactory getCurrentSessionFromJPA() {
        // JPA and Hibernate SessionFactory example
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = emf.createEntityManager();
        // Get the Hibernate Session from the EntityManager in JPA
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        SessionFactory factory = session.getSessionFactory();
        return factory;
    }*/

    public static void insertUsuario(EntityTransaction transaction,UsuarioEntity usuario){

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

    public static void insertCentros(EntityTransaction transaction,CentrosEntity centros){

        try{
            transaction.begin();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(centros);
            tx.commit();
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    public static void insertCFamiliaPro(EntityTransaction transaction, FamiliaProfesionalEntity familia){

        try{
            transaction.begin();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(familia);
            tx.commit();
            transaction.commit();
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
        }
    }

    public static CentrosEntity crearCentro(){

        centros =new CentrosEntity();
        centros.setActivo((byte) 1);
        centros.setIdCentro(1);
        centros.setNombre("IES San Alberto Magno");
        centros.setWeb(" ");
        centros.setContacto("");
        return centros;
    }

    public static FamiliaProfesionalEntity crearFamilia(){

        familia =new FamiliaProfesionalEntity();
        familia.setFamiliaProfesionalId(1);
        familia.setNombreFamilia("Fontanero");
        return familia;
    }

    public static UsuarioEntity crearUsuario(){
        usuario =new UsuarioEntity();
        String nombre;
        String apellidos;
        String email;
        String nombreUsuario;
        String contraseña;
        String rol;
        //double puntuacion;
        int familiaProfesional;
        int telefono;
        int id_Centro;
        int id_Usuario;

        do {
            nombre=Leer.pedirCadena("Introduzca el nombre");
        }while (nombre.length()>30 && nombre!=null);
        do {
            apellidos=Leer.pedirCadena("Introduzca los apellidos");
        }while (apellidos.length()>50&& apellidos!=null);
        do {
            email=Leer.pedirCadena("Introduzca el email");
        }while (email.length()>40&& email!=null);
        do {
            nombreUsuario=Leer.pedirCadena("Introduzca el nombre de usuario");
        }while (nombreUsuario.length()>30&& nombreUsuario!=null);
        do {
            contraseña=Leer.pedirCadena("Introduzca la contraseña");
        }while (contraseña.length()>30&& contraseña!=null);
        do {
            rol=Leer.pedirCadena("Introduzca el rol (miembro o coordinador)");
        }while (!rol.equals("miembro") && !rol.equals("coordinador"));
        do {
            telefono=Leer.pedirEntero("Introduzca un telefono");
            if (String.valueOf(telefono).length()!=9 ){
                System.out.println(" Introduzca un telefono de 9 digitos de longitud, ejemplo: 999111333");
            }
        }while (String.valueOf(telefono).length()!=9 );
        do {
            familiaProfesional=Leer.pedirEntero("Introduzca el rol (miembro o coordinador)");
        }while (!rol.equals("miembro") && !rol.equals("coordinador"));
        do {
            id_Centro=centros.getIdCentro();
        }while (id_Centro!=0);
        do {
            id_Usuario=Leer.pedirEntero("Introduzca el numero del usuario");
        }while (id_Usuario!= 0);


        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setEmail(email);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContraseña(contraseña);
        usuario.setRol(rol);
        usuario.setFamiliaProfesional(familiaProfesional);
        usuario.setPuntuacion(0.0);
        usuario.setTelefono(telefono);
        usuario.setIdCentro(id_Centro);


        return usuario;
    }


}


