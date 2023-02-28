package com.jdml.fp2.factoriajdml2;

import org.exist.storage.DBBroker;
import org.exist.xmldb.EXistResource;
import org.exist.xmldb.LocalDatabaseInstanceManager;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.transform.OutputKeys;

import static org.exist.security.utils.Utils.getOrCreateCollection;


public class ConexionExist_DB {

    private static String URI = "xmldb:exist://localhost:8181/exist/fp";

    static Collection collection = null;

    private static String user="admin";

    private static String password="";

    /**
     * args[0] Should be the name of the collection to access
     * args[1] Should be the name of the resource to read from the collection
     */
    public static void main(String args[]) throws Exception {

        final String driver = "org.exist.xmldb.DatabaseImpl";

        // initialize database driver
        try {

            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);
            collection = DatabaseManager.getCollection(URI,user,password);

            if (collection==null){
                System.out.println("La colección no existe");
            }else {
                XPathQueryService xPathQueryService= (XPathQueryService) collection.getService("XPathQueryService","1.0");
                String query ="for $em in /EMPLEADOS/EMP_ROW[DEPT_NO=10] return $em";
                ResourceSet result =xPathQueryService.query(query);
                ResourceIterator iterator=result.getIterator();
                if (!iterator.hasMoreResources()){
                    System.out.println("La consulta no devuelve nada.");
                }
                while (iterator.hasMoreResources()){
                    Resource resource=iterator.nextResource();
                    System.out.println((String) resource.getContent());
                }
                collection.close();
            }


        } catch (Exception e){

        }
    }
}
