package Conexiones;

import ClasesMongo.CentrosDeProyectoMongo;
import ClasesMongo.CentrosMongo;
import CreacionInicialBDSQL.Leer;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Arrays;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ConexionMongoDB {
    final static String uri = "mongodb://localhost:27017/?maxPoolSize=20&w=majority";
    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        InsertCentro();
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1 -> leerDatosUsuarioProyecto();
                case 2 -> insertardatos();
                case 3 -> eliminardatos();
                case 4 -> modificardatos();
                case 0 -> System.out.println("Salir");
            }
        } while (opcion != 0);
    }

    public static int menu() {
        System.out.println("leer Usuarios");
        System.out.println("Insertar centro");
        System.out.println("Eliminar proyecto");
        System.out.println("Modificar proyecto");
        System.out.println("Salir");
        return sc.nextInt();
    }

    public static void leerDatosUsuarioProyecto() {
        //este metodo nos permite mostrar por pantalla los nombres de los usuarios
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2");
            String nombre = Leer.pedirCadena("Introduce el nombre del Proyecto que quieres leer: ");

            Iterable<Document> agProyectos = db.getCollection("Proyectos").aggregate(Arrays.asList(Aggregates.
                    lookup("Usuarios",nombre,"_id","datosUsuarios")));
            for (Document p:agProyectos){
                System.out.println(p.toJson());
            }
        } catch (MongoException e) {
            System.out.println(e);
        }
    }

    public static void insertardatos() {
        boolean clave=false;
        //este metodo nos permite insertar datos en la coleccion de centros


        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2");
            MongoCollection<Document> collection = db.getCollection("Centros");

            do {
                try{

                    int id_Centro = Leer.pedirEntero("Intoduce el identificador del centro: ");
                    String nombre = Leer.pedirCadena("Intoduce el nombre del centro: ");
                    String Web = Leer.pedirCadena("Intoduce la direccion web del centro(Si no tiene pon 0): ");
                    String Contacto = Leer.pedirCadena("Intoduce el nombre de la persona de contacto del centro: ");

                    Document document = new Document("_id", id_Centro)
                            .append("nombre", nombre)
                            .append("web",Web)
                            .append("contacto",Contacto);
                    collection.insertOne(document);
                    clave=true;
                }catch (MongoWriteException e){
                    System.out.println("se ha introducido un Id que ya existe");
                }
            }while (clave!=true);


        }catch (MongoException e) {
            System.out.println(e);
        }
    }

    public static void eliminardatos() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2");
            MongoCollection<Document> collection = db.getCollection("Proyectos");
            String nombre = Leer.pedirCadena("Intoduce el nombre del proyecto que desear eliminar: ");
            collection.deleteOne(eq("nombre",nombre));

        }catch (MongoException e) {
            System.out.println(e);
        }
    }
    public static void modificardatos(){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2");
            MongoCollection<Document> collection = db.getCollection("Proyectos");
            String usuariosLast = Leer.pedirCadena("Intoduce el nombre del proyecto que desear eliminar: ");
            String usuariosNews = Leer.pedirCadena("Intoduce el nombre del proyecto que desear eliminar: ");
            collection.updateOne(eq("Usuarios", usuariosLast), new Document("$set", new Document("Usuarios", usuariosNews)));

        }catch (MongoException e) {
            System.out.println(e);
        }
    }

    public static void InsertCentro(){
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

            MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2").withCodecRegistry( codecRegistry);;

            MongoCollection<CentrosMongo> CentrosMongo = db.getCollection("centros", ClasesMongo.CentrosMongo.class);

            CentrosMongo centro=new CentrosMongo();
            centro.setNombre("Ies San Alberto Magno");
            centro.setWeb("0");
            centro.setContacto("Javier Fernandez");
            centro.setActivo(true);
            CentrosMongo.insertOne(centro);
        } catch (MongoException e) {
            System.out.println(e);
        }
    }

}
