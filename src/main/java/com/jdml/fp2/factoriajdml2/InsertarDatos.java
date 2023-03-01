package com.jdml.fp2.factoriajdml2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class InsertarDatos {
    final static Scanner sc = new Scanner(System.in);
    public static void MenuInsercion(){
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1 -> InsertarCentro();
                case 2 -> InsertarProyecto();
                case 3 -> InsertarUsuario();

                case 0 -> System.out.println("Salir");
            }
        } while (opcion != 0);
    }
    public static int menu() {
        System.out.println("Insertar Centro");
        System.out.println("Insertar Proyecto");
        System.out.println("Insertar Usuario");
        System.out.println("Salir");
        return sc.nextInt();
    }
    public static void InsertarCentro(){
        try(Connection connection = ConexionSql.conectar()) {
            int  id_Centro=Leer.pedirEntero("Intoduce el identificador del centro: ");
            String  nombre=Leer.pedirCadena("Intoduce el nombre del centro: ");
            String  Web=Leer.pedirCadena("Intoduce la direccion web del centro(Si no tiene pon 0): ");
            String  Contacto=Leer.pedirCadena("Intoduce el nombre de la persona de contacto del centro: ");

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Centros(ID_CENTRO,Nombre,Web,Contacto) VALUES (?,?,?,?)");

            ps.setInt(1,id_Centro);
            ps.setString(2,nombre);
            ps.setString(3,Web);
            ps.setString(4,Contacto);
            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void InsertarProyecto(){
        try(Connection connection = ConexionSql.conectar()) {
            int id_Proyecto=Leer.pedirEntero("Intoduce el Identificador del proyecto: ");
            String titulo=Leer.pedirCadena("Intoduce el Nombre del proyecto: ");
            String descripcion=Leer.pedirCadena("Intoduce la descripcion del proyecto: ");
            String coordinador=Leer.pedirCadena("Intoduce el nombre del coordinador del proyecto: ");
            String estado="Activo";
            String visibilidad="Public";
            int visitas=0;

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Proyectos(PROYECTO_ID,Titulo,Descripcion,Coordinador,Estado,Visibilidad,Visitas) VALUES (?,?,?,?,?,?,?)");

            ps.setInt(1,id_Proyecto);
            ps.setString(2,titulo);
            ps.setString(3,descripcion);
            ps.setString(4,coordinador);
            ps.setString(5,estado);
            ps.setString(6,visibilidad);
            ps.setInt(7,visitas);
            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void InsertarUsuario(){
        try(Connection connection = ConexionSql.conectar()) {
            int id_centro=Leer.pedirEntero("Intoduce el identificador de centro: ");
            int id_usuario=Leer.pedirEntero("Intoduce el identificador de usuario: ");
            String nombre=Leer.pedirCadena("Intoduce tu nombre: ");
            String apellidos=Leer.pedirCadena("Intoduce tus apellidos: ");
            String contraseña=Leer.pedirCadena("Intoduce una contraseña: ");
            String rol="miembro";
            double puntuacion=0.0;
            int famProf=0;
            String email=Leer.pedirCadena("Intoduce una dirección de correo: ");
            int tlf=Leer.pedirEntero("Intoduce un número de telefono: ");

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Usuario(ID_Centro,ID_USUARIO,Nombre,Apellidos,Contraseña,Rol,Puntuacion,Familia_Profesional,Email,Telefono) VALUES (?,?,?,?,?,?,?,?,?,?)");

            ps.setInt(1,id_centro);
            ps.setInt(2,id_usuario);
            ps.setString(3,nombre);
            ps.setString(4,apellidos);
            ps.setString(5,contraseña);
            ps.setString(6,rol);
            ps.setDouble(7,puntuacion);
            ps.setInt(8,famProf);
            ps.setString(9,email);
            ps.setInt(10,tlf);

            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }

/*
    public static void InsertarCentro(){
        try(Connection connection = ConexionSql.conectar()) {
            int  id_Centro=Leer.pedirEntero("Intoduce el identificador del centro: ");
            String  nombre=Leer.pedirCadena("Intoduce el nombre del centro: ");
            String  Web=Leer.pedirCadena("Intoduce la direccion web del centro(Si no tiene pon 0): ");
            String  Contacto=Leer.pedirCadena("Intoduce el nombre de la persona de contacto del centro: ");

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Centros(ID_CENTRO,Nombre,Web,Contacto) VALUES (?,?,?,?)");

            ps.setInt(1,id_Centro);
            ps.setString(2,nombre);
            ps.setString(3,Web);
            ps.setString(4,Contacto);
            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static void InsertarProyecto(){
        try(Connection connection = ConexionSql.conectar()) {
            int id_Proyecto=Leer.pedirEntero("Intoduce el Identificador del proyecto: ");
            String titulo=Leer.pedirCadena("Intoduce el Nombre del proyecto: ");
            String descripcion=Leer.pedirCadena("Intoduce la descripcion del proyecto: ");
            String coordinador=Leer.pedirCadena("Intoduce el nombre del coordinador del proyecto: ");
            String estado="Activo";
            String visibilidad="Public";
            int visitas=0;

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Proyectos(PROYECTO_ID,Titulo,Descripcion,Coordinador,Estado,Visibilidad,Visitas) VALUES (?,?,?,?,?,?,?)");

            ps.setInt(1,id_Proyecto);
            ps.setString(2,titulo);
            ps.setString(3,descripcion);
            ps.setString(4,coordinador);
            ps.setString(5,estado);
            ps.setString(6,visibilidad);
            ps.setInt(7,visitas);
            ps.execute();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void InsertarUsuario(int id_centro,int id_usuario,String nombre,String apellidos,String contraseña,
                                       int famProf,String email,int tlf) {
        try (Connection connection = ConexionSql.conectar()) {
            String rol = "miembro";
            double puntuacion = 0.0;

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Usuario(ID_Centro,ID_USUARIO,Nombre,Apellidos,Contraseña,Rol,Puntuacion,Familia_Profesional,Email,Telefono) VALUES (?,?,?,?,?,?,?,?,?,?)");

            ps.setInt(1, id_centro);
            ps.setInt(2, id_usuario);
            ps.setString(3, nombre);
            ps.setString(4, apellidos);
            ps.setString(5, contraseña);
            ps.setString(6, rol);
            ps.setDouble(7, puntuacion);
            ps.setInt(8, famProf);
            ps.setString(9, email);
            ps.setInt(10, tlf);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
