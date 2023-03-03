package Conexiones;

import org.w3c.dom.Document;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import org.exist.xmldb.DatabaseImpl;
import org.exist.xmldb.XmldbURI;
//import org.exist.xmldb.XmldbXMLResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class ConexionExist_DB {

    private static String URI = "xmldb:exist://localhost:8181/exist/xmlrpc/db/fp/";

    //{"CentrosCFGMyS.xml", "familias.xml", "proyectos.xml"};

    private static int[] ListaIdQueries = {0, 1, 2};

    private static HashMap<Integer, String> Queries = new HashMap<>();

    static Collection collection = null;

    private static String user = "admin";

    private static String password = "";

    static DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
    static DocumentBuilder docBuilder;
    static Path path=Path.of("target\\Recursos\\xmls");
    static File centroXmls;
    static File proyectoXmls;
    static File familiasXmls;
    static Document docParsed=null;

    public static void main(String args[]) throws Exception {


        BufferedWriter bufferedWriter = null;

        final String driver = "org.exist.xmldb.DatabaseImpl";

        // initialize database driver

        /*Queries.put(0, "xquery version \"3.1\";" +
                "let $Centros := doc(\"fp/CentrosCFGMyS.xml\")//*:Row" +
                "\n" +
                "return <centros> {\n" +
                "    for $Centro in $Centros\n" +
                "return <centro> {$Centro/*}</centro>} \n" +
                "    \n" +
                "</centros>");*/
        Queries.put(0,
                "let $Centros := doc(\"CentrosCFGMyS.xml\")//*:Row\n" +
                        "return <centros> {\n" +
                        "    for $Centro in $Centros\n" +
                        "return <centro> {$Centro/*}</centro>} \n" +
                        "</centros>");
        Queries.put(1,
                "let $Familias := doc(\"familias.xml\")//*:option\n" +
                        "\n" +
                        "return <Familias>\n" +
                        "{for $Familia in $Familias\n" +
                        "return <Familia>{$Familia/data()}</Familia>}\n" +
                        "</Familias>");
        Queries.put(2,
                "let $row := doc(\"proyectos.xml\")//*:Row\n" +
                        "\n" +
                        "    \n" +
                        "return \n" +
                        "    <Proyectos>\n" +
                        "       \n" +
                        "        {for $row2 in $row return\n" +
                        "            <Proyecto>\n" +
                        "                {$row2/*}\n" +
                        "            </Proyecto>\n" +
                        "        }\n" +
                        "       </Proyectos>\n");

        try {

            docBuilder= docFactory.newDocumentBuilder();
            //Si no existe el directorio se crea
            if(!Files.exists(path))
                Files.createDirectories(path);

            centroXmls=new File(path+"\\centro.xml");
            familiasXmls=new File(path+"\\familias.xml");
            proyectoXmls=new File(path+"\\proyecto.xml");

            Class<?> cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);

            collection = DatabaseManager.getCollection(URI, user, password);
            if (collection == null) {
                System.out.println("La colección no existe");
            } else {
                for (int URIFile : ListaIdQueries) {
                    if (URIFile == 0){
                        bufferedWriter=new BufferedWriter(new FileWriter(centroXmls));
                    }else if (URIFile==1) {
                        bufferedWriter=new BufferedWriter(new FileWriter(familiasXmls));
                    } else if (URIFile==2) {
                        bufferedWriter=new BufferedWriter(new FileWriter(proyectoXmls));
                    }
                    XPathQueryService xPathQueryService = (XPathQueryService) collection.getService("XPathQueryService", "3.1");

                    ResourceSet result = xPathQueryService.query(Queries.get(URIFile));

                    ResourceIterator iterator = result.getIterator();
                    if (!iterator.hasMoreResources()) {
                        System.out.println("La consulta no devuelve nada.");
                    }
                    while (iterator.hasMoreResources()) {
                        Resource resource = iterator.nextResource();
                        System.out.println((String) resource.getContent());
                        bufferedWriter.write((String) resource.getContent());

                    }
                    bufferedWriter.flush();
                    if (URIFile == 0){
                        docParsed=docBuilder.parse(centroXmls);
                        //docParsed.setXmlVersion("1.0");
                        Transform(docParsed,centroXmls);
                    }else if (URIFile==1) {
                        docParsed=docBuilder.parse(familiasXmls);
                        //docParsed.setXmlVersion("1.0");
                        Transform(docParsed,familiasXmls);
                    } else if (URIFile==2) {
                        docParsed=docBuilder.parse(proyectoXmls);
                        //docParsed.setXmlVersion("1.0");
                        Transform(docParsed,proyectoXmls);
                    }
                }


                //docParsed=docBuilder.getDOMImplementation().createDocument(null,"Empleados",null);
                /*docParsed.setXmlVersion("1.0");
                Transform(familiasXmls);
                Transform(proyectoXmls);*/
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            if (collection!=null)
            collection.close();
        }
    }
    public static void Transform(Document documento,File fichero){

        Transformer transformer= null;
        documento.normalizeDocument();
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD,"xml");
            transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.setOutputProperty("{http:xml.apache.org/xslt}indent-amount","4");
            transformer.transform(new DOMSource(documento) ,new StreamResult(fichero));
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
}
