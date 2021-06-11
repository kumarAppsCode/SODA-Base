import java.sql.SQLException;

import javax.naming.NamingException;

import oracle.soda.OracleCollection;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;

public class DropCollection {

    
    public static void main(String[] args) throws ClassNotFoundException, OracleException, SQLException, NamingException {
        
        // createStrDoc();
        createStrDocKey();        
    }


    public static void createStrDoc()throws ClassNotFoundException, OracleException, SQLException, NamingException{
        OracleCollection col= DBConnection.openCollection("MyJSONCollection");     
        OracleDocument doc=DBConnection.getOracleDatabase().createDocumentFromString("{ \"name\" : \"Dinesh\"}");
        col.insert(doc);
        String content=doc.getContentAsString();
        String contentType=doc.getKey();
        System.out.println("Content:==>"+content);
        System.out.println("contentType:==>"+contentType);
        DBConnection.destroyConnection();

    }

    public static void createStrDocKey()throws ClassNotFoundException, OracleException, SQLException, NamingException{
        OracleCollection col= DBConnection.openCollection("MyJSONCollection");     
        OracleDocument doc=DBConnection.getOracleDatabase().createDocumentFromString("mykey", ("{ \"name\" : \"Dinesh1\"}"));
        col.insert(doc);
        String content=doc.getContentAsString();
        String contentType=doc.getKey();
        System.out.println("Content:==>"+content);
        System.out.println("contentType:==>"+contentType);
        DBConnection.destroyConnection();

    }

    // https://docs.oracle.com/en/database/oracle/simple-oracle-document-access/java/adsda/using-soda-java.html#GUID-AFDEEF42-DC2D-4104-BA92-8AFC36299078

    
}