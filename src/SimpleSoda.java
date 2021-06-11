// import java.sql.Connection;
// import java.sql.DriverManager;

// import oracle.soda.rdbms.OracleRDBMSClient;
// import oracle.soda.OracleDatabase;
// import oracle.soda.OracleCursor;
// import oracle.soda.OracleCollection;
// import oracle.soda.OracleDocument;
// import oracle.soda.OracleException;

// import java.util.Properties;

// import oracle.jdbc.OracleConnection;

// public class SimpleSoda {
//   public static void main(String[] args) {
   
//     try {
//     // SODA works on top of a regular JDBC connection.
//     String url = "jdbc:oracle:thin:@db202106021234_high?TNS_ADMIN=D:\\Projects\\New Learn\\SODA\\DB JAVA\\Wallet_DB202106021234";
//     // Get a JDBC connection to an Oracle instance
//     OracleConnection conn = (OracleConnection) DriverManager.getConnection(url);
//     // Get an OracleRDBMSClient - starting point of SODA
//     OracleRDBMSClient cl = new OracleRDBMSClient();

//     // Get a SODA database
//     OracleDatabase db = cl.getDatabase(conn);
      
//     // Create a collection with the name "mycollection".
//     OracleCollection col = db.admin().createCollection("mycollection");
      
//     // Create a few JSON documents, representing users and the number of friends they have
//     col.insert( db.createDocumentFromString(
// 			      "{\"name\": \"Venkat\",  \"address\": {\"city\": \"Bengaluru\"}}") ) ;
//     col.insert( db.createDocumentFromString(
// 			      "{\"name\": \"Matilda\", \"address\": {\"city\": \"San Francisco\"}}" ) );
//     col.insert( db.createDocumentFromString(
// 			      "{\"name\": \"Eric\",    \"address\": {\"city\": \"Sydney\"}}" ) );
      
//     // Find all cities starting with S
//     OracleCursor c = col.find().filter(
//         db.createDocumentFromString( "{ \"address.city\" : {\"$like\" : \"S%\" } }")
// 			     ).getCursor();
//     System.out.println("Cities starting with S:");
//     while (c.hasNext()) {
//       // Get the next document
//       OracleDocument fetchedDoc = c.next();
//       System.out.println (fetchedDoc.getContentAsString());
//     }
//     c.close();

//     // Drop the collection
//     col.admin().drop();
//     System.out.println ("\n* Collection dropped *");

//     conn.close();
//     }
//     catch (Exception e) {
//       e.printStackTrace();
//     }
//   }
// }