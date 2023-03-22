package CreacionInicialBDSQL;

import Conexiones.ConexionSql;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class SqlMain {
    static String DBName = null;

    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;


        DBName = "FactoriaProyectos";

        try (Connection connection = ConexionSql.conectar("")) {
            crearBD(connection);
            ConexionSql.conectar("/"+DBName);
            do {
                switch (opcion = menu()) {
                    case 1 -> crearTablas(connection);
                    case 2 -> borrarTablas(connection);
                    case 3 -> insertarDatos(connection);
                    case 4 -> modificarDatos(connection);
                    case 5 -> eliminarDatos(connection);
                    case 0 -> System.out.println("Salir");
                    default -> System.out.println("Opcion no existente");
                }

            } while (opcion != 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static int menu() {
        System.out.println("1. Crear tablas (SQL)");
        System.out.println("2. Borrar tablas (SQL)");
        System.out.println("3. Insertar Datos (SQL)");
        System.out.println("4. Modificar Datos");
        System.out.println("5. Eliminar Datos");
        System.out.println("Salir");
        return sc.nextInt();
    }

    public static void crearTablas(Connection connection) {

        try {
            Statement statement = connection.createStatement();

            //Tabla Proyectos
            statement.execute("CREATE TABLE IF NOT EXISTS Proyectos (" +
                    "AUTO_ID int not null auto_increment primary key," +
                    "PROYECTO_ID int not null unique," +
                    "Titulo varchar(20)," +
                    "Descripcion varchar(120)," +
                    "Coordinador varchar(20)," +
                    "Estado varchar(20)," +
                    "Visibilidad varchar(20)," +
                    "Visitas int)");

            //Tabla Centros

            //Nuevos campos , Activo
            //Nuevas restricciones Activo BOOLEAN DEFAULT true
            statement.execute("CREATE TABLE IF NOT EXISTS Centros(" +
                    "AUTO_ID int not null auto_increment primary key," +
                    "ID_CENTRO int not null unique," +
                    "Nombre varchar(20)," +
                    "Web varchar(50)," +
                    "Contacto varchar(20)," +
                    "Activo BOOLEAN DEFAULT true)");

            //Tabla Familia Profesional

            statement.execute("CREATE TABLE IF NOT EXISTS FamiliaProfesional(" +
                    "AUTO_ID int not null auto_increment primary key," +
                    "FAMILIA_PROFESIONAL_ID int not null unique," +
                    "Nombre_Familia varchar(60) not null unique)");


            //Tabla usuarios
            //Nuevos campos , NombreUsuario
            //Nuevas restricciones Nombre not null, contraseña not null, apellidos not null,
            statement.execute("CREATE TABLE IF NOT EXISTS Usuario(" +
                    "AUTO_ID int not null auto_increment primary key ," +
                    "ID_Centro int not null ," +
                    "ID_USUARIO int not null unique ," +
                    "NombreUsuario varchar(30) not null unique ," +
                    "Nombre varchar(20) not null," +
                    "Apellidos varchar(50) not null," +
                    "Contraseña varchar(30) not null," +
                    "Rol varchar(15)," +
                    "Puntuacion double ," +
                    "Familia_Profesional int not null," +
                    "Email varchar(40)," +
                    "Telefono int," +
                    "foreign key (Familia_Profesional) REFERENCES FamiliaProfesional(FAMILIA_PROFESIONAL_ID)," +
                    "foreign key (ID_Centro) REFERENCES Centros(ID_CENTRO) ON DELETE CASCADE " +
                    ")");

            //Tabla Participantes
            statement.execute("CREATE TABLE IF NOT EXISTS Participantes (" +
                    "AUTO_ID int not null auto_increment primary key ," +
                    "Proyecto_id int not null," +
                    "id_Usuario int not null, " +
                    "Cordinador varchar(20)," +
                    "Fec_Ini date ," +
                    "Fec_Fin date, " +
                    "foreign key (Proyecto_id)REFERENCES Proyectos(PROYECTO_ID) ON DELETE CASCADE, " +
                    "foreign key (id_Usuario) REFERENCES Usuario(ID_USUARIO) ON DELETE CASCADE" +

                    ")");

            //Tabla Comentarios
            statement.execute("CREATE TABLE IF NOT EXISTS Comentarios(" +
                    "AUTO_ID int not null auto_increment primary key ," +
                    "Escritor int not null ," +
                    "Proyecto_id int not null ," +
                    "Contenido varchar(20)," +
                    "foreign key (Escritor) REFERENCES Usuario(ID_USUARIO) ON DELETE CASCADE," +
                    "foreign key (Proyecto_id) REFERENCES Proyectos(PROYECTO_ID) ON DELETE CASCADE" +
                    ")");

            //Tabla Familia Profesional Implicada
            statement.execute("CREATE TABLE IF NOT EXISTS FamiliaProfesionalImplicada(" +
                    "AUTO_ID int not null auto_increment primary key," +
                    "Proyecto_ID int not null," +
                    "FAMILIA_PROFESIONAL_ID int not null," +
                    "foreign key (Proyecto_ID) REFERENCES Proyectos(PROYECTO_ID) ON DELETE CASCADE," +
                    "foreign key (FAMILIA_PROFESIONAL_ID) REFERENCES FamiliaProfesional(FAMILIA_PROFESIONAL_ID) ON DELETE CASCADE)");

            //Tabla ProyectosFav
            statement.execute("CREATE TABLE IF NOT EXISTS ProyectosFav(" +
                    "AUTO_ID int not null auto_increment primary key ," +
                    "Proyecto_ID int not null," +
                    "ID_USUARIO int not null ," +
                    "foreign key (Proyecto_ID) REFERENCES Proyectos(PROYECTO_ID) ON DELETE CASCADE," +
                    "foreign key (ID_USUARIO) REFERENCES Usuario(ID_USUARIO) ON DELETE CASCADE)");

            //Tabla CentrosDeProyectos
            statement.execute("CREATE TABLE IF NOT EXISTS CentrosDeProyecto(" +
                    "AUTO_ID int not null auto_increment primary key," +
                    "Proyecto_ID int not null," +
                    "ID_Centro int not null ," +
                    "foreign key (Proyecto_ID) REFERENCES Proyectos(PROYECTO_ID) ON DELETE CASCADE," +
                    "foreign key (ID_Centro) REFERENCES Centros(ID_CENTRO) ) ");

            //Tabla Tags
            statement.execute("CREATE TABLE IF NOT EXISTS Tags(" +
                    "AUTO_ID int not null auto_increment primary key," +
                    "Proyecto_ID int not null ," +
                    "Tag varchar(20)," +
                    "foreign key (Proyecto_ID) REFERENCES Proyectos(PROYECTO_ID) ON DELETE CASCADE)");


        } catch (SQLException e) {
            System.out.println("La creacion de tablas no se llevo a cabo, error sql");
            e.printStackTrace();
        }
    }

    public static void borrarTablas(Connection connection) {
        try {
            System.out.println("Introduzca \"BORRAR\" para eliminar todas las tablas");
            if (sc.nextLine().equalsIgnoreCase( "BORRAR")) {
                Statement statement = connection.createStatement();
                statement.execute("DROP TABLE IF EXISTS Tags");
                statement.execute("DROP TABLE IF EXISTS ProyectosFav");
                statement.execute("DROP TABLE IF EXISTS CentrosDeProyecto");
                statement.execute("DROP TABLE IF EXISTS FamiliaProfesionalImplicada");
                statement.execute("DROP TABLE IF EXISTS Comentarios");
                statement.execute("DROP TABLE IF EXISTS Participantes");
                statement.execute("DROP TABLE IF EXISTS Usuario");
                statement.execute("DROP TABLE IF EXISTS FamiliaProfesional");
                statement.execute("DROP TABLE IF EXISTS Proyectos");
                statement.execute("DROP TABLE IF EXISTS Centros");
            }else {
                System.out.println("La eliminacion de tablas no se llevo a cabo");
            }
        } catch (SQLException e) {
            System.out.println("La eliminacion de tablas no se llevo a cabo, error sql");
            e.printStackTrace();
        }
    }

    public static void crearBD(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS" + DBName + "");
        } catch (SQLException e) {
            System.out.println("La creacion de la base de datos no se llevo a cabo, error sql");
            e.printStackTrace();
        }
    }


    public static void insertarDatos(Connection connection) {
        InsertarDatos.MenuInsercion(connection);
    }


    public static void modificarDatos(Connection connection) {
        ModificarDatos.MenuInsercion(connection);
    }

    public static void eliminarDatos(Connection connection){ EliminarDatos.MenuEliminacion(connection);}

}