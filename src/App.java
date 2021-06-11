import java.sql.DriverManager;
import java.util.Properties;
import oracle.jdbc.OracleConnection;
import oracle.soda.OracleCollection;
import oracle.soda.OracleCursor;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;
import oracle.soda.rdbms.OracleRDBMSClient;


public class App {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Set up the JDBC connection string, schemaName, and password.
        // Replace with info appropriate for your Oracle Database instance.
        // String url = "jdbc:oracle:thin:@//hostName:port/serviceName";  
//      String url = "jdbc:oracle:thin:SODA_DEMO/Oracleworlds123#@db202106021234_high?TNS_ADMIN=D:\\Projects\\New Learn\\SODA\\DB JAVA\\Wallet_DB202106021234";
//      String url = "jdbc:oracle:thin:Wallet_DB202106021234?TNS_ADMIN=D:\\Projects\\New Learn\\SODA\\DB JAVA\\Wallet_DB202106021234";
//        String url = "jdbc:oracle:thin:db202106021234_high?TNS_ADMIN=D:\\Projects\\New Learn\\SODA\\DB JAVA\\Wallet_DB202106021234";
//********************************************************
//   String url="DB_URL=jdbc:oracle:thin:@db202106021234_high?TNS_ADMIN=D:\\Projects\\New Learn\\SODA\\DB JAVA\\Zip\\Wallet_DB202106021234.zip";    

     String url="jdbc:oracle:thin:@db202106021234_high?TNS_ADMIN=D:\\Projects\\New Learn\\SODA\\DB JAVA\\Wallet_DB202106021234"; 
      
      Properties props = new Properties();
      props.setProperty("user", "SODA_DEMO");
      props.setProperty("password", "Oracleworlds123#");
 
      OracleConnection conn = null;
 
      try
      {
        // Get a JDBC connection to an Oracle instance.
        conn = (OracleConnection) DriverManager.getConnection(url, props);
        System.out.println("====1===");
        // Enable JDBC implicit statement caching
        conn.setImplicitCachingEnabled(true);
        conn.setStatementCacheSize(50);
        System.out.println("====2===");
        // Get an OracleRDBMSClient - starting point of SODA for Java application.
          OracleRDBMSClient cl = new OracleRDBMSClient();
          System.out.println("====3===");
        // Get a database.
          OracleDatabase db = cl.getDatabase(conn);
          System.out.println("====4==="+db);
        // Create a collection with the name "MyJSONCollection".
        // This creates a database table, also named "MyJSONCollection", to store the collection.
          OracleCollection col = db.admin().createCollection("MyJSONCollection");
          System.out.println("====5===");
        // Create a JSON document.
          OracleDocument doc =
          db.createDocumentFromString("{ \"name\" : \"Alexander\" }");
          System.out.println("====6===");
        // Insert the document into a collection.
            col.insert(doc);
 
        // Find all documents in the collection.
          OracleCursor c = null;
 
        try 
        {
          c = col.find().getCursor();
          OracleDocument resultDoc;
 
          while (c.hasNext())
          {
            // Get the next document.
            resultDoc = c.next();
 
            // Print document components
            System.out.println ("Key:         " + resultDoc.getKey());
            System.out.println ("Content:     " + resultDoc.getContentAsString());
            System.out.println ("Version:     " + resultDoc.getVersion());
            System.out.println ("Last modified: " + resultDoc.getLastModified());
            System.out.println ("Created on:    " + resultDoc.getCreatedOn());
            System.out.println ("Media:         " + resultDoc.getMediaType());
            System.out.println ("\n");
          }
        }
        finally
        {
          // IMPORTANT: YOU MUST CLOSE THE CURSOR TO RELEASE RESOURCES.
          if (c != null) c.close();
        }
 
        // Drop the collection, deleting the table underlying it and the collection metadata.
//        if (args.length > 0 && args[0].equals("drop")) {
//          col.admin().drop();
//          System.out.println ("\nCollection dropped");
//        }
    }
    // SODA for Java throws a checked OracleException
    catch (OracleException e) { e.printStackTrace(); }
    catch (Exception e) { e.printStackTrace(); }
    finally 
    {
      try { if (conn != null)  conn.close(); }
      catch (Exception e) { }
    }
  }

}
