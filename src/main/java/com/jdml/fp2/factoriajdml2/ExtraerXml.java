package com.jdml.fp2.factoriajdml2;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.transform.OutputKeys;


public class ExtraerXml {

    private static String URI = "xmldb:exist://localhost:8181/exist/";

    /**
     * args[0] Should be the name of the collection to access
     * args[1] Should be the name of the resource to read from the collection
     */
    public static void main(String args[]) throws Exception {

        final String driver = "org.exist.xmldb.DatabaseImpl";

        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        Collection col = null;
        XMLResource res = null;
        try {
            // get the collection
            col = DatabaseManager.getCollection(URI + "centros");
            col.setProperty(OutputKeys.INDENT, "no");
            res = (XMLResource)col.getResource("centrosFP2.xml");

            if(res == null) {
                System.out.println("document not found!");
            } else {
                System.out.println(res.getContent());
            }
        } finally {
            //dont forget to clean up!

            if(res != null) {
                try { ((EXistResource)res).freeResources(); } catch(XMLDBException xe) {xe.printStackTrace();}
            }

            if(col != null) {
                try { col.close(); } catch(XMLDBException xe) {xe.printStackTrace();}
            }
        }
    }
}
