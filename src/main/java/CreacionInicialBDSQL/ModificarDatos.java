package CreacionInicialBDSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModificarDatos {

    public static void MenuInsercion(Connection connection) throws SQLException {
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1 -> ModificarCentro(connection);
                case 2 -> ModificarProyecto(connection);
                case 3 -> ModificarUsuario(connection);
                case 0 -> System.out.println("Salir");
                default -> System.out.println("Opcion no existente");
            }
        } while (opcion != 0);
    }

    //Menu
    public static int menu() {
        System.out.println("1 - Modificar Centro");
        System.out.println("2 - Modificar Proyecto");
        System.out.println("3 - Modificar Usuario");
        System.out.println("0 - Salir");
        return Leer.pedirEntero("Introduzca una opcion del menu");
    }

    //Metodo para modificar centro si existe controlando datos
    private static void ModificarCentro(Connection connection) throws SQLException {

        int centro_id = Leer.pedirEntero("Introduce el id del centro: ");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Centros WHERE ID_Centro=?");
        ps.setInt(1, centro_id);
        ResultSet resultSet = ps.executeQuery();
        String newnombre;
        String newContacto;
        String newWeb;
        int activo;
        if (resultSet.next()) {
            do {
                newnombre = Leer.pedirCadena("Introduce el nuevo nombre del centro: ");
                if (newnombre.length() > 20) {
                    System.out.println("Longitud del nombre debe ser menor o igual que 20");
                }

            } while (newnombre.length() > 20);
            do {
                newWeb = Leer.pedirCadena("Introduce la nueva direccion web del centro (Si no tiene pon 0): ");
                if (newWeb.length() > 50) {
                    System.out.println("Longitud de la direccion debe ser menor o igual que 50");
                }

            } while (newWeb.length() > 50);
            do {
                newContacto = Leer.pedirCadena("Introduce el nuevo nombre de la persona de contacto del centro: ");
                if (newContacto.length() > 20) {
                    System.out.println("Longitud del contacto debe ser menor o igual que 20");
                }

            } while (newContacto.length() > 20);
            do {
                activo = Leer.pedirEntero("Introduce 0 si el centro esta inactivo o 1 si esta activo: ");
                if (activo != 1 && activo != 0) {
                    System.out.println("El valor introducido debe ser 0 o 1");
                }

            } while (activo != 1 && activo != 0);
            ps = connection.prepareStatement("UPDATE Centros SET Nombre=?,Web=?,Contacto=?,Activo=? WHERE ID_Centro=?");
            ps.setString(1, newnombre);
            ps.setString(2, newWeb);
            ps.setString(3, newContacto);
            ps.setInt(4, activo);
            ps.setInt(5, centro_id);
            ps.execute();
        } else {
            System.out.println("No existe el centro con el id introducido");
        }
    }

    //Metodo para modificar proyecto si existe controlando datos
    private static void ModificarProyecto(Connection connection) throws SQLException {

        int proyecto_Id = Leer.pedirEntero("Intoduce el id del proyecto: ");
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Proyectos WHERE PROYECTO_ID=?");
        ps.setInt(1, proyecto_Id);
        ResultSet resultSet = ps.executeQuery();
        String newtitulo;
        String newdescripcion;
        String newcoordinador;
        String newestado;
        String newvisiblidad;
        if (resultSet.next()) {
            do {

                newtitulo = Leer.pedirCadena("Intoduce el nuevo titulo del proyecto: ");
                if (newtitulo.length() > 20) {
                    System.out.println("Longitud del titulo o igual que 20 caracteres");
                }

            } while (newtitulo.length() > 20);
            do {


                newdescripcion = Leer.pedirCadena("Introduce la nueva descripcion del proyecto: ");
                if (newdescripcion.length() > 120) {
                    System.out.println("Longitud de la descripcion debe ser menor o igual que 120 caracteres");
                }

            } while (newdescripcion.length() > 120);
            do {

                newcoordinador = Leer.pedirCadena("Introduce el nuevo nombre del coordinador: ");
                if (newcoordinador.length() > 20) {
                    System.out.println("Longitud del nombre debe ser menor o igual que 20 caracteres");
                }

            } while (newcoordinador.length() > 20);
            do {

                newestado = Leer.pedirCadena("Introduce el nuevo estado del proyecto (Activo/Finalizado): ");
                if (newestado.length() > 20 || (!newestado.equalsIgnoreCase("Activo") && !newestado.equalsIgnoreCase("Finalizado"))) {
                    System.out.println("Longitud del estado dbe ser menor o igual que 20 caracteres y debe ser activo o finalizado");
                }

            } while (newestado.length() > 20 || (!newestado.equalsIgnoreCase("Activo") && !newestado.equalsIgnoreCase("Finalizado")));
            do {

                newvisiblidad = Leer.pedirCadena("Introduce la nueva visibilidad del proyecto (Public/Private): ");
                if (newvisiblidad.length() > 20 || (!newvisiblidad.equalsIgnoreCase("Public") && !newvisiblidad.equalsIgnoreCase("Private"))) {

                    System.out.println("Longitud de la visibilidad debe ser menor o igual que 20 caracteres");
                    ;
                }
            } while (newvisiblidad.length() > 20 || (!newvisiblidad.equalsIgnoreCase("Public") && !newvisiblidad.equalsIgnoreCase("Private")));


            ps = connection.prepareStatement("UPDATE Proyectos SET Titulo=?,Descripcion=?,Coordinador=?,Estado=?,Visibilidad=? WHERE PROYECTO_ID=?");
            ps.setString(1, newtitulo);
            ps.setString(2, newdescripcion);
            ps.setString(3, newcoordinador);
            ps.setString(4, newestado);
            ps.setString(5, newvisiblidad);
            ps.setInt(6, proyecto_Id);
            ps.execute();
        } else {
            System.out.println("No existe el proyecto con el id introducido, ");
        }
    }

    //Metodo para modificar usuario si existe controlando datos
    private static void ModificarUsuario(Connection connection) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM Usuario WHERE ID_USUARIO=?");
        int id;
        String newnombre;
        ResultSet resultSet;
        String newapellidos;
        String newcontraseña;
        String newrol;

        do {
            id = Leer.pedirEntero("Introduce el id del usuario para modificar: ");
            ps.setInt(1, id);
            if (Integer.toString(id).length() > 20) {
                System.out.println("Longitud del id del usuario o igual que 20");
            }
        } while (Integer.toString(id).length() > 20);

        resultSet = ps.executeQuery();

        if (resultSet.next()) {

            do {
                newnombre = Leer.pedirCadena("Introduce el nuevo nombre del usuario: ");
                if (newnombre.length() > 20) {
                    System.out.println("Longitud del nombre o igual que 20");
                }
            } while (newnombre.length() > 20);

            do {
                newapellidos = Leer.pedirCadena("Introduce los nuevos apellidos del usuario: ");
                if (newapellidos.length() > 50) {
                    System.out.println("Longitud de los apellidos debe ser igual que 50");
                }
            } while (newapellidos.length() > 50);

            do {
                newcontraseña = Leer.pedirCadena("Introduce la nueva contraseña del usuario: ");
                if (newcontraseña.length() > 30) {
                    System.out.println("Longitud de la contraseña menor o igual que 30");
                }
            } while (newcontraseña.length() > 30);

            do {
                newrol = Leer.pedirCadena("Introduce el nuevo rol del usuario (Alumno o Profesor): ");
                if (newrol.length() > 20 || (!newrol.equalsIgnoreCase("Alumno") && !newrol.equalsIgnoreCase("Profesor"))) {
                    System.out.println("Longitud del estado dbe ser menor o igual que 20 caracteres");
                }

            } while (newrol.length() > 20 || (!newrol.equalsIgnoreCase("Alumno") && !newrol.equalsIgnoreCase("Profesor")));

            ps = connection.prepareStatement("UPDATE Usuario SET Nombre=?,Apellidos=?,Contraseña=?,Rol=? WHERE ID_USUARIO=?");
            ps.setString(1, newnombre);
            ps.setString(2, newapellidos);
            ps.setString(3, newcontraseña);
            ps.setString(4, newrol);
            ps.setInt(5, id);
        } else {
            System.out.println("No existe el usuario con el id introducido ");
        }
    }
}
