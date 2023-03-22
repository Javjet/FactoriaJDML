package CreacionInicialBDSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class InsertarDatos {
    final static Scanner sc = new Scanner(System.in);

    public static void MenuInsercion(Connection connection) throws SQLException {
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1 -> InsertarCentro(connection);
                case 2 -> InsertarProyecto(connection);
                case 3 -> InsertarUsuario(connection);
                case 0 -> System.out.println("Salir");
                default -> System.out.println("Opcion no existente");
            }
        } while (opcion != 0);
    }

    public static int menu() {
        System.out.println("1. Insertar Centro");
        System.out.println("2. Insertar Proyecto");
        System.out.println("3. Insertar Usuario");
        System.out.println("0. Salir");
        return sc.nextInt();
    }
    //Metodo para insertar centro si no existe controlando datos
    public static void InsertarCentro(Connection connection) throws SQLException {
        int id_Centro;
        String nombre;
        String web;
        String contacto;
        int activo;
        id_Centro = Leer.pedirEntero("Introduce el identificador del centro: ");

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Centros WHERE ID_Centro=?");
        ps.setInt(1, id_Centro);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("No se ha insertado el registro, existe un registro con ese Id");
        } else {
            do {
                nombre = Leer.pedirCadena("Introduce el nombre del centro: ");
                if (nombre.length() > 20) {
                    System.out.println("Longitud del nombre debe ser menor o igual que 20 caracteres");
                }

            } while (nombre.length() > 20);
            do {
                web = Leer.pedirCadena("Introduce la direccion web del centro (Si no tiene pon 0): ");
                if (web.length() > 50) {
                    System.out.println("Longitud de la direccion debe ser menor o igual que 50 caracteres");
                }

            } while (web.length() > 50);
            do {
                contacto = Leer.pedirCadena("Introduce el nuevo nombre de la persona de contacto del centro: ");
                if (contacto.length() > 20) {
                    System.out.println("Longitud del contacto debe ser menor o igual que 20 caracteres");
                }

            } while (contacto.length() > 20);
            do {
                activo = Leer.pedirEntero("Introduce 0 si el centro esta inactivo o 1 si esta activo: ");
                if (activo != 1 && activo != 0) {
                    System.out.println("El valor introducido debe ser 0 o 1");
                }

            } while (activo != 1 && activo != 0);
            ps = connection.prepareStatement("INSERT INTO Centros(ID_CENTRO,Nombre,Web,Contacto,Activo) VALUES (?,?,?,?,?)");
            ps.setInt(1, id_Centro);
            ps.setString(2, nombre);
            ps.setString(3, web);
            ps.setString(4, contacto);
            ps.setInt(5, activo);
            ps.execute();
            System.out.println("Se ha insertado el registro");
        }
    }

    //Metodo para insertar un proyecto si no existe controlando datos
    public static void InsertarProyecto(Connection connection) throws SQLException {
        int id_Proyecto;
        String titulo;
        String descripcion;
        String coordinador;

        String estado = "Activo";
        String visibilidad = "Public";
        int visitas = 0;
        System.out.println(connection.isClosed());
        id_Proyecto = Leer.pedirEntero("Introduce el Identificador del proyecto: ");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Proyectos WHERE PROYECTO_ID=?");
        ps.setInt(1, id_Proyecto);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            System.out.println("No se ha insertado el registro, existe un registro con ese Id");
        } else {
            do {

                titulo = Leer.pedirCadena("Introduce el Nombre del proyecto: ");
                if (titulo.length() > 20) {
                    System.out.println("Longitud del titulo o igual que 20 caracteres");
                }

            } while (titulo.length() > 20);
            do {
                descripcion = Leer.pedirCadena("Introduce la descripcion del proyecto: ");
                if (descripcion.length() > 120) {
                    System.out.println("Longitud de la descripcion debe ser menor o igual que 120 caracteres");
                }

            } while (descripcion.length() > 120);
            do {

                coordinador = Leer.pedirCadena("Introduce el nombre del coordinador del proyecto: ");
                if (coordinador.length() > 20) {
                    System.out.println("Longitud del nombre debe ser menor o igual que 20 caracteres");
                }

            } while (coordinador.length() > 20);
            ps = connection.prepareStatement("INSERT INTO Proyectos(PROYECTO_ID,Titulo,Descripcion,Coordinador,Estado,Visibilidad,Visitas) VALUES (?,?,?,?,?,?,?)");

            ps.setInt(1, id_Proyecto);
            ps.setString(2, titulo);
            ps.setString(3, descripcion);
            ps.setString(4, coordinador);
            ps.setString(5, estado);
            ps.setString(6, visibilidad);
            ps.setInt(7, visitas);
            ps.execute();
            System.out.println("Se ha insertado el registro");
        }
    }
    //Metodo para modificar centro si no existe controlando datos y controlando que exista una familia profesional y centro para el usuario
    public static void InsertarUsuario(Connection connection) throws SQLException {
        int id_centro = 0;
        int id_usuario = 0;
        int famProf = 0;
        boolean insertar = true;
        String error = "";
        PreparedStatement ps;
        String nombre;
        String apellidos;
        String contraseña;
        String email;
        String nombreUsu;
        String rol = "miembro";
        double puntuacion = 0.0;
        int tlf;
        /*ps = connection.prepareStatement("INSERT INTO FamiliaProfesional(FAMILIA_PROFESIONAL_ID,Nombre_Familia) VALUES (?,?)");
        ps.setInt(1, 99);
        ps.setString(2, "NOMBRE");
        ps.execute();*/
        if (insertar) {
            id_centro = Leer.pedirEntero("Introduce el identificador de centro: ");
            ps = connection.prepareStatement("SELECT * FROM Centros WHERE ID_Centro=?");
            ps.setInt(1, id_centro);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

            } else {
                error = "No existe el id centro introducido en la tabla Centros";
                insertar = false;
            }
        }
        if (insertar) {
            famProf = Leer.pedirEntero("Introduce el identificador de la familia profesional");
            ps = connection.prepareStatement("SELECT * FROM FamiliaProfesional WHERE FAMILIA_PROFESIONAL_ID=?");
            ps.setInt(1, famProf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

            } else {
                error = "No existe el id de la familia profesional introducida en la tabla FamiliaProfesional";
                insertar = false;
            }
        }
        if (insertar) {
            id_usuario = Leer.pedirEntero("Introduce el identificador de usuario: ");
            ps = connection.prepareStatement("SELECT * FROM Usuario WHERE ID_USUARIO=?");
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                error = "Ya existe el id usuario introducido en la tabla Usuario";
                insertar = false;
            }
        }
        if (insertar) {

            do {
                nombreUsu = Leer.pedirCadena("Introduce el nombre de usuario del usuario: ");
                if (nombreUsu.length() > 30) {
                    System.out.println("Longitud del nombre de usuario o igual que 30");
                }
            } while (nombreUsu.length() > 30);
            do {
                nombre = Leer.pedirCadena("Introduce el nombre del usuario: ");
                if (nombre.length() > 20) {
                    System.out.println("Longitud del nombre o igual que 20");
                }
            } while (nombre.length() > 20);

            do {
                apellidos = Leer.pedirCadena("Introduce los apellidos del usuario: ");
                if (apellidos.length() > 50) {
                    System.out.println("Longitud de los apellidos debe ser igual o menor que 50");
                }
            } while (apellidos.length() > 50);

            do {
                contraseña = Leer.pedirCadena("Introduce la contraseña del usuario: ");
                if (contraseña.length() > 30) {
                    System.out.println("Longitud de la contraseña menor o igual que 30");
                }
            } while (contraseña.length() > 30);
            do {
                email = Leer.pedirCadena("Introduce el email del usuario: ");
                if (email.length() > 40) {
                    System.out.println("Longitud del email debe ser menor o igual que 40");
                }
            } while (email.length() > 40);
            do {
                tlf = Leer.pedirEntero("Introduce un número de telefono: ");
                if (Integer.toString(tlf).length() != 9) {
                    System.out.println("Longitud del telefono debe ser igual que 9");
                }
            } while (Integer.toString(tlf).length() != 9);

            ps = connection.prepareStatement("INSERT INTO Usuario(ID_Centro,ID_USUARIO,Nombre,Apellidos,Contraseña,Rol,Puntuacion,Familia_Profesional,Email,Telefono,NombreUsuario) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

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
            ps.setString(11,nombreUsu);
            ps.execute();
        } else {
            System.out.println(error);
        }


    }

}
