package com.jdml.fp2.factoriajdml2;

import Clases.UsuarioEntity;
import Conexiones.ConexionSql;
import jakarta.transaction.Transaction;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static String DBName=null;

    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            DBName="FactoriaProyectos";
            addDB();
            opcion = menu();
            switch (opcion) {
                case 1 -> crearTablas();
                case 2 -> borrarTablas();
                case 3 -> crearBD();
                case 4 -> insertarDatos();
                case 5 -> modificarDatos();
                case 0 -> System.out.println("Salir");
            }
        } while (opcion != 0);


    }

    public static int menu() {
        System.out.println("Crear tablas");
        System.out.println("Borrar tablas");
        System.out.println("Modificar Datos");
        System.out.println("Insertar Datos");
        System.out.println("Salir");
        return sc.nextInt();
    }

    public static void crearTablas() {

        try (Connection connection = ConexionSql.conectar()) {
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
                    "foreign key (ID_Centro) REFERENCES Centros(ID_CENTRO) ON DELETE CASCADE "+
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
                    "foreign key (Proyecto_id) REFERENCES Proyectos(PROYECTO_ID) ON DELETE CASCADE"+
                    ")");

            //Tabla Familia Profesional Implicada
            statement.execute("CREATE TABLE IF NOT EXISTS FamiliaProfesionalImplicada(" +
                    "AUTO_ID int not null auto_increment primary key," +
                    "Proyecto_ID int not null," +
                    "FAMILIA_PROFESIONAL_ID int not null," +
                    "foreign key (Proyecto_ID) REFERENCES Proyectos(PROYECTO_ID)," +
                    "foreign key (FAMILIA_PROFESIONAL_ID) REFERENCES FamiliaProfesional(FAMILIA_PROFESIONAL_ID))");

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
                    "foreign key (ID_Centro) REFERENCES Centros(ID_CENTRO)) ");

            //Tabla Tags
            statement.execute("CREATE TABLE IF NOT EXISTS Tags(" +
                    "AUTO_ID int not null auto_increment primary key," +
                    "Proyecto_ID int not null ," +
                    "Tag varchar(20)," +
                    "foreign key (Proyecto_ID) REFERENCES Proyectos(PROYECTO_ID) ON DELETE CASCADE)");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void borrarTablas() {
        try (Connection connection = ConexionSql.conectar()) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearBD() {
        try (Connection connection = ConexionSql.conectar()) {
            Statement statement = connection.createStatement();
            DBName="FactoriaProyectos";
            statement.execute("CREATE DATABASE "+DBName+"");
            if (!ConexionSql.getURL().contains(DBName)){
                ConexionSql.setURL("/"+DBName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDB(){
        if (!ConexionSql.getURL().contains(DBName)){
            ConexionSql.setURL("/"+DBName);
        }
    }

    public static void insertarDatos(){
        InsertarDatos.MenuInsercion();
    }

    public void saveUser(UsuarioEntity user){
        Transaction transaction = null;

    }

   /* public static void insertarUsuario(Usuario user){

        PreparedStatement ps;
        ResultSet rs;

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = new Employee("John", "Doe", "john.doe@example.com");
            session.save(employee);
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        //Pido el codigo del profesor con un longitud maxima de 4 digitos y como es tipo de dato int no puede ser nulo
        do {
            correcto = true;
            cod_Prof = Leer.pedirEntero("\nIntroduzca un codigo de profesor unico y no nulo");
            ps = conexion.prepareStatement("SELECT COD_PROF FROM C1_PROFESORES WHERE COD_PROF = ?;");
            ps.setInt(1, cod_Prof);
            rs = ps.executeQuery();
            if (rs.next()) {
                correcto = false;
                System.out.println("\nEl codigo de profesor "+cod_Prof+" ya existe en la base de datos por favor, introduzca otro codigo");
            }
            if ((cod_Prof + "").length() > 4){
                System.out.println("\nEl codigo de profesor "+cod_Prof+" supera la longitud de 4 caracteres");
            }
        } while (!correcto || (cod_Prof + "").length() > 4);

        //Pido la especialidad del profesor con dos siglas como maximo
        do {
            correcto = false;
            especialidad = Leer.pedirCadena("\nIntroduzca la especialidad del profesor, Ej: FQ o IF");
            ps = conexion.prepareStatement("SELECT ESPECIALIDAD FROM C1_ESPECIALIDAD WHERE ESPECIALIDAD = ?;");
            ps.setString(1, especialidad);
            rs = ps.executeQuery();
            if (rs.next()) {
                correcto = true;
            }
            if(especialidad.length() > 2){
                System.out.println("\nPara las especialidades debe introducir un caracter o dos como maximo, "+especialidad+" no es valido supera los dos caracteres");
            }
        } while (!correcto || especialidad.length() > 2);

        //Pido el codigo del centro
        do {

            correcto = false;
            cod_Centro = Leer.pedirEntero("\nIntroduzca un codigo de centro no nulo");

            ps = conexion.prepareStatement("SELECT COD_CENTRO FROM C1_CENTROS WHERE COD_CENTRO = ?;");
            ps.setInt(1, cod_Centro);
            rs = ps.executeQuery();

            if (rs.next()) {
                correcto = true;}
            else {
                System.out.println("\nEl codigo del centro \""+cod_Centro+"\" no existe o es nulo");
            }
        } while (!correcto);

        //Pido nombres y apellidos
        do {
            nombre_Apellidos = Leer.pedirCadena("\nIntroduzca el nombre y los apellidos del profesor");
            if(nombre_Apellidos.length() > 30){
                System.out.println("\nEl nombre del profesor \""+nombre_Apellidos+"\" supera la longitud de 30 caracteres");
            }
        } while (nombre_Apellidos.length() > 30);

        //Pido codigo del jefe de departamento
        do {
            cod_Jefe_Dep = Leer.pedirEntero("\nIntroduzca el codigo del jefe de departamento", null);
            if((cod_Jefe_Dep + "").length() > 4){
                System.out.println("El codigo del jefe de departamento \""+cod_Jefe_Dep+"\" supera la longitud de 4 digitos");
            }
        } while ((cod_Jefe_Dep + "").length() > 4);
        //Pido fecha de nacimiento
        do {
            /*Pattern pattern = new Pattern("([1][9][0-9][0-9]|20[0-1]([0-9]|[0-2]))-(1[0-2]|0[1-9]-([0-2][0-9]|3[0-1]))");
            fecha_Nac = Leer.pedirCadena("Introduzca la fecha", );*/
/*            fecha_Nac = Leer.pedirCadena("\nIntroduzca la fecha");
            if (fecha_Nac.length() > 10){
                System.out.println("La longitud de la fecha debe ser de 10 caracteres");
            }
        } while (fecha_Nac.length() != 10);
        do {
            sexo = Leer.pedirCadena("\nIntroduzca el sexo del profesor con un caracter M o F", "M|m|F|f");
        } while (sexo.length() > 1);
        ps = conexion.prepareStatement("INSERT INTO C1_PROFESORES VALUES (?,?,?,?,?,?,?);");
        ps.setInt(1,cod_Prof);
        ps.setString(2,nombre_Apellidos);
        ps.setString(3,especialidad);
        ps.setInt(4,cod_Jefe_Dep);
        ps.setString(5,fecha_Nac);
        ps.setString(6,sexo);
        ps.setInt(7,cod_Centro);
        ps.execute();
    }*/

    public static void modificarDatos(){
        ModificarDatos.MenuInsercion();
    }

}