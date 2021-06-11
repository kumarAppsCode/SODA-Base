
import java.sql.SQLException;

import javax.naming.NamingException;


import oracle.soda.OracleCollection;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;

public class InsertJson {


    public static void main(String[] args) throws ClassNotFoundException, OracleException, SQLException, NamingException {
        // insertDocument();
        insertWithKey();
    }



     public static void insertWithKey() throws ClassNotFoundException, OracleException, SQLException, NamingException{
        
        // Configures the collection with client-assigned document keys
        OracleDocument collMeta =DBConnection.getRDBMSClient().createMetadataBuilder().keyColumnAssignmentMethod("client").build();
        
        OracleCollection clientKeysColl = DBConnection.getOracleDatabase().
        admin().createCollection("location",collMeta);
        
        // For a collection configured with client-assigned document keys,
        // you must provide the key for the input document.
        OracleDocument cKeyDoc =
        DBConnection.getOracleDatabase().createDocumentFromString("myKey", "{ \"name\" : \"Alexander1\"}");
        
        // If key "myKey" already identifies a document in the collection
        // then cKeyDoc replaces the existing doc.
        OracleDocument savedDoc = clientKeysColl.saveAndGet(cKeyDoc);
        
        // Get document key ("myKey")
        String key = savedDoc.getKey();
        
        // Get the generated creation timestamp
        String createdOn = savedDoc.getCreatedOn();
         
        // Get the generated last-modified timestamp
        String lastModified = savedDoc.getLastModified();
         
        // Get the generated version
        String version = savedDoc.getVersion();

        System.out.println("key==>"+key);
        System.out.println("createdOn==>"+createdOn);
        System.out.println("lastModified==>"+lastModified);
        System.out.println("version==>"+version);

        DBConnection.destroyConnection();

     }










    // public static void insertDocument() throws ClassNotFoundException, OracleException, SQLException, NamingException {
    //     String a="{\"name\":\"dinesh\"}";
    //     OracleCollection col=DBConnection.getOracleDatabase().openCollection("MyJSONCollection");
    //     System.out.println("col==>"+col);
    //     // String a="name:dinesh}";
    //     // String a=null;
    //     System.out.println(a);
    //     boolean abc= DBConnection.isJSONValid(a);
    //     System.out.println("Boolean is valid String:"+abc);
    //     ByteArrayInputStream targetStream = new ByteArrayInputStream(a.getBytes());
    //     OracleDocument doc=DBConnection.getOracleDatabase().createDocumentFromString(targetStream.toString());
    //     System.out.println("doc==>"+doc);
    //     col.insert(doc);
    //     DBConnection.destroyConnection();
    // }

}