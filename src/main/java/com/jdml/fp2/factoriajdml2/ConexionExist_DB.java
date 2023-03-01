package com.jdml.fp2.factoriajdml2;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

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


public class ConexionExist_DB {

    private static String URI = "xmldb:exist://localhost:8181/exist/xmlrpc/db/fp/";

    //{"CentrosCFGMyS.xml", "familias.xml", "proyectos.xml"};

    private static int[] ListaIdQueries = {0,1,2};

    private static HashMap<Integer, String> Queries = new HashMap<>();

    static Collection collection = null;

    private static String user = "admin";

    private static String password = "";

    /**
     * args[0] Should be the name of the collection to access
     * args[1] Should be the name of the resource to read from the collection
     */
    public static void main(String args[]) throws Exception {

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
                "    let $centros := doc(\'CentrosCFGMyS.xml\')//*:Row \n"+
                "        return <centros> \n" +
                "           {$Centros }\n" +
                "        </centros>");
        Queries.put(1, "xquery version \"3.1\";\n" +
                "\n" +
                "let $Familias := doc(\"fp/familias.xml\")//*:option\n" +
                "\n" +
                "return <Familias>\n" +
                "{for $Familia in $Familias\n" +
                "return <Familia>{$Familia/data()}</Familia>}\n" +
                "</Familias>");
        Queries.put(2, "xquery version \"3.1\";\n" +
                "let $row := doc(\"fp/proyectos.xml\")//*:Row\n" +
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

            Class<?> cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");
            DatabaseManager.registerDatabase(database);

            /*System.out.println(collection.getResource("CentrosCFGMyS.xml").getId());

            System.out.println(collection.getResource("familias.xml").getId());

            System.out.println(collection.getResource("proyectos.xml").getId());*/
            for (int URIFile : ListaIdQueries) {

                collection = DatabaseManager.getCollection(URI, user, password);

                if (collection == null) {
                    System.out.println("La colección no existe");
                } else {


                    XPathQueryService xPathQueryService = (XPathQueryService) collection.getService("XPathQueryService", "1.0");

                    xPathQueryService.queryResource("CentrosCFGMyS.xml",Queries.get(URIFile));

                    ResourceSet result = xPathQueryService.query(Queries.get(URIFile));

                    ResourceIterator iterator = result.getIterator();
                    if (!iterator.hasMoreResources()) {
                        System.out.println("La consulta no devuelve nada.");
                    }
                    while (iterator.hasMoreResources()) {
                        Resource resource = iterator.nextResource();
                        System.out.println((String) resource.getContent());
                    }
                    collection.close();
                }
            }

            System.out.println(DatabaseManager.getCollection("URI/db/fp"));

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
