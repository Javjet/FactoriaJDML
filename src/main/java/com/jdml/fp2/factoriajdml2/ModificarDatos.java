package com.jdml.fp2.factoriajdml2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ModificarDatos {

    final static Scanner sc = new Scanner(System.in);
    public static void MenuInsercion(){
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1 -> ModificarCentro();
                case 2 -> ModificarProyecto();
                case 3 -> ModificarUsuario();

                case 0 -> System.out.println("Salir");
            }
        } while (opcion != 0);
    }
    public static int menu() {
        System.out.println("Modificar Centro");
        System.out.println("Modificar Proyecto");
        System.out.println("Modificar Usuario");
        System.out.println("Salir");
        return sc.nextInt();
    }

    private static void ModificarCentro() {
        try(Connection connection = ConexionSql.conectar()){
            String  nombre=Leer.pedirCadena("Intoduce el nombre del centro: ");
            String  newnombre=Leer.pedirCadena("Intoduce el nuevo nombre del centro: ");
            String  newWeb=Leer.pedirCadena("Intoduce la nueva direccion web del centro(Si no tiene pon 0): ");
            String  newContacto=Leer.pedirCadena("Intoduce el nuevo nombre de la persona de contacto del centro: ");

            PreparedStatement ps = connection.prepareStatement("UPDATE Centros SET Nombre=?,Web=?,Contacto=? WHERE Nombre=?" );
            ps.setString(1,newnombre);
            ps.setString(2,newWeb);
            ps.setString(3,newContacto);
            ps.setString(4,nombre);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static void ModificarProyecto() {
        try(Connection connection = ConexionSql.conectar()){
            String  titulo=Leer.pedirCadena("Intoduce el nombre del centro: ");
            String  newtitulo=Leer.pedirCadena("Intoduce el nuevo nombre del centro: ");
            String  newdescripcion=Leer.pedirCadena("Intoduce la nueva direccion web del centro(Si no tiene pon 0): ");
            String  newcoordinador=Leer.pedirCadena("Intoduce el nuevo nombre de la persona de contacto del centro: ");
            String  newestado=Leer.pedirCadena("Intoduce el nuevo estado del proyecto (Activo/Finalizado): ");
            String  newvisiblidad=Leer.pedirCadena("Intoduce la nueva visibilidad del proyecto (Public/Private): ");


            PreparedStatement ps = connection.prepareStatement("UPDATE Proyectos SET Titulo=?,Descripcion=?,Coordinador=?,Estado=?,Visibilidad=? WHERE Titulo=?" );
            ps.setString(1,newtitulo);
            ps.setString(2,newdescripcion);
            ps.setString(3,newcoordinador);
            ps.setString(4,newestado);
            ps.setString(5,newvisiblidad);
            ps.setString(6,titulo);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static void ModificarUsuario() {
        try(Connection connection = ConexionSql.conectar()){
            String  nombre=Leer.pedirCadena("Intoduce el nombre del centro: ");
            String  newnombre=Leer.pedirCadena("Intoduce el nuevo nombre del centro: ");
            String  newapellidos=Leer.pedirCadena("Intoduce la nueva direccion web del centro(Si no tiene pon 0): ");
            String  newcontraseña=Leer.pedirCadena("Intoduce el nuevo nombre de la persona de contacto del centro: ");
            String  newrol=Leer.pedirCadena("Intoduce el nuevo nombre de la persona de contacto del centro: ");

            PreparedStatement ps = connection.prepareStatement("UPDATE Usuario SET Nombre=?,Apellidos=?,Contraseña=?,Rol=? WHERE Nombre=?" );
            ps.setString(1,newnombre);
            ps.setString(2,newapellidos);
            ps.setString(3,newcontraseña);
            ps.setString(4,newrol);
            ps.setString(5,nombre);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


}
