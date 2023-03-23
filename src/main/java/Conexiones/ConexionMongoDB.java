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
import java.util.Objects;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ConexionMongoDB {
    final static String uri = "mongodb://localhost:27017/?maxPoolSize=20&w=majority";
    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            opcion = menu();
            switch (opcion) {
                case 1 -> createDB();
                case 2 -> InsertCentro();
                case 3 -> ModifyCentro();
                case 4 -> DeleteCentro();
                case 0 -> System.out.println("Salir");
            }
        } while (opcion != 0);
    }

    public static int menu() {
        System.out.println("Crear DB Mongo");
        System.out.println("Insertar centro");
        System.out.println("Modificar centro");
        System.out.println("Eliminar centro");
        System.out.println("Salir");
        return sc.nextInt();
    }

    //Metodo para crear db en mongo con sus collections
    public static void createDB() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2");

            boolean createColUser = true;
            boolean createColCenter = true;
            boolean createColProyect = true;
            for (String nombre : db.listCollectionNames()) {
                if (nombre.equalsIgnoreCase("Usuarios")) {
                    createColUser = false;
                }
                if (nombre.equalsIgnoreCase("Centros")) {
                    createColCenter = false;
                }

                if (nombre.equalsIgnoreCase("Proyectos")) {
                    createColProyect = false;
                }

            }
            if (createColUser)
                db.createCollection("Usuarios");
            if (createColCenter)
                db.createCollection("Centros");
            if (createColProyect)
                db.createCollection("Proyectos");
        } catch (MongoException e) {
            System.out.println(e);
        }
    }

    //Metodo para insertar centro
    public static void InsertCentro() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

            MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2").withCodecRegistry(codecRegistry);

            MongoCollection<CentrosMongo> CentrosMongo = db.getCollection("Centros", ClasesMongo.CentrosMongo.class);

            CentrosMongo centro = new CentrosMongo();
            centro.setNombre(Leer.pedirCadena("Introduzca el nombre del centro"));
            centro.setWeb(Leer.pedirCadena("Introduzca la Web del centro"));
            centro.setActivo(true);
            CentrosMongo.insertOne(centro);
        } catch (MongoException e) {
            System.out.println(e);
        }
    }

    //Metodo para modificar un centro
    public static void ModifyCentro() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

            MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2").withCodecRegistry(codecRegistry);

            MongoCollection<CentrosMongo> CentrosMongo = db.getCollection("Centros", ClasesMongo.CentrosMongo.class);
            String nombreCentro = Leer.pedirCadena("Introduzca el nombre del centro que quiere modificar");
            CentrosMongo centro = CentrosMongo.find(eq("nombre", nombreCentro), CentrosMongo.class).first();
            if (centro != null) {
                if (Leer.pedirCadena("Introduzca \"si\" para modificar el nombre del centro").equalsIgnoreCase("SI")) {
                    centro.setNombre(Leer.pedirCadena("Introduzca el nombre del centro"));
                }
                if (Leer.pedirCadena("Introduzca \"si\" para modificar la Web del centro").equalsIgnoreCase("SI")) {
                    centro.setWeb(Leer.pedirCadena("Introduzca la Web del centro"));
                }
                if (Leer.pedirCadena("Introduzca \"si\" para modificar el Coordinador del centro").equalsIgnoreCase("SI")) {
                    centro.setContacto(Leer.pedirCadena("Introduzca el Coordinador del centro"));
                }
                CentrosMongo.replaceOne(eq("nombre", nombreCentro), centro);
            }else {
                System.out.println("El archivo que desea modificar no existe");
            }
        } catch (MongoException e) {
            System.out.println(e);
        }catch (org.bson.codecs.configuration.CodecConfigurationException c){
            System.out.println("La version del objeto que intenta recuperar esta desactualizada");
        }
    }

    //Metodo para eliminar un centro
    public static void DeleteCentro() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

            MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2").withCodecRegistry(codecRegistry);

            MongoCollection<CentrosMongo> CentrosMongo = db.getCollection("Centros", ClasesMongo.CentrosMongo.class);
            CentrosMongo.deleteOne(eq("nombre", Leer.pedirCadena("Introduzca el nombre del centro que eliminar")));
        } catch (MongoException e) {
            System.out.println(e);
        }
    }
/**
 * public static void leerDatosUsuarioProyecto() {
 *         //este metodo nos permite mostrar por pantalla los nombres de los usuarios
 *         try (MongoClient mongoClient = MongoClients.create(uri)) {
 *             MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2");
 *             String nombre = Leer.pedirCadena("Introduce el nombre del Proyecto que quieres leer: ");
 *
 *             Iterable<Document> agProyectos = db.getCollection("Proyectos").aggregate(Arrays.asList(Aggregates.
 *                     lookup("Usuarios", nombre, "_id", "datosUsuarios")));
 *             for (Document p : agProyectos) {
 *                 System.out.println(p.toJson());
 *             }
 *         } catch (MongoException e) {
 *             System.out.println(e);
 *         }
 *     }
 *
 *     public static void insertardatos() {
 *         boolean clave = false;
 *         //este metodo nos permite insertar datos en la coleccion de centros
 *
 *
 *         try (MongoClient mongoClient = MongoClients.create(uri)) {
 *             MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2");
 *             MongoCollection<Document> collection = db.getCollection("Centros");
 *
 *             do {
 *                 try {
 *
 *                     int id_Centro = Leer.pedirEntero("Intoduce el identificador del centro: ");
 *                     String nombre = Leer.pedirCadena("Intoduce el nombre del centro: ");
 *                     String Web = Leer.pedirCadena("Intoduce la direccion web del centro(Si no tiene pon 0): ");
 *                     String Contacto = Leer.pedirCadena("Intoduce el nombre de la persona de contacto del centro: ");
 *
 *                     Document document = new Document("_id", id_Centro)
 *                             .append("nombre", nombre)
 *                             .append("web", Web)
 *                             .append("contacto", Contacto);
 *                     collection.insertOne(document);
 *                     clave = true;
 *                 } catch (MongoWriteException e) {
 *                     System.out.println("se ha introducido un Id que ya existe");
 *                 }
 *             } while (clave != true);
 *
 *
 *         } catch (MongoException e) {
 *             System.out.println(e);
 *         }
 *     }
 *
 *     public static void eliminardatos() {
 *         try (MongoClient mongoClient = MongoClients.create(uri)) {
 *             MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2");
 *             MongoCollection<Document> collection = db.getCollection("Proyectos");
 *             String nombre = Leer.pedirCadena("Introduce el nombre del proyecto que desear eliminar: ");
 *             collection.deleteOne(eq("nombre", nombre));
 *
 *         } catch (MongoException e) {
 *             System.out.println(e);
 *         }
 *     }
 *
 *     public static void modificardatos() {
 *         try (MongoClient mongoClient = MongoClients.create(uri)) {
 *             MongoDatabase db = mongoClient.getDatabase("FactoriaProyectosFP2");
 *             MongoCollection<Document> collection = db.getCollection("Proyectos");
 *             String usuariosLast = Leer.pedirCadena("Intoduce el nombre del proyecto que desear eliminar: ");
 *             String usuariosNews = Leer.pedirCadena("Intoduce el nombre del proyecto que desear eliminar: ");
 *             collection.updateOne(eq("Usuarios", usuariosLast), new Document("$set", new Document("Usuarios", usuariosNews)));
 *
 *         } catch (MongoException e) {
 *             System.out.println(e);
 *         }
 *     }*/
}
