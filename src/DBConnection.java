
// import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
// import java.util.logging.Level;
// import java.util.logging.Logger;
import javax.naming.NamingException;
// import javax.json.JsonException;
import javax.naming.Context;
import javax.naming.InitialContext;
// 
// import java.sql.DriverManager;
// import java.util.Properties;
import oracle.jdbc.OracleConnection;
import oracle.soda.OracleCollection;
import oracle.soda.OracleCursor;
import oracle.soda.OracleDatabase;
// import oracle.soda.OracleDocument;
import oracle.soda.OracleException;
import oracle.soda.rdbms.OracleRDBMSClient;
// 
// import org.json.*;


public class DBConnection {

    static OracleConnection connection;
    static OracleCursor cursor; 


    public static OracleConnection getConnectionDS(String datasource) throws SQLException, NamingException {
        OracleConnection con = null;
        DataSource data = null;
        Context initialContext = new InitialContext();
        data = (DataSource) initialContext.lookup(datasource);
        if (data != null) {
            con = (OracleConnection) data.getConnection();
        } else {
            System.out.println("Failed to Find JDBC DataSource.");
        }
        return con;
    }

    public static OracleConnection getDBConnection() throws SQLException, ClassNotFoundException {
        OracleConnection conn = null;
        try {

            String url = "jdbc:oracle:thin:@***_high?TNS_ADMIN=D:\\Projects\\New Learn\\SODA\\DB JAVA\\Wallet_DB202106021234";
    
   
   
            Properties props = new Properties();
            props.setProperty("user", "SODA_DEMO");
            props.setProperty("password", "Oracleworlds123#");

            // Get a JDBC connection to an Oracle instance.
            conn = (OracleConnection) DriverManager.getConnection(url, props);
            // Enable JDBC implicit statement caching
            conn.setImplicitCachingEnabled(true);
            conn.setStatementCacheSize(50);    

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static OracleConnection dbInitialization() throws ClassNotFoundException, SQLException, NamingException {
        // Data Sources
        // return connection=getConnectionDS("datasource");     
        // DB
        return connection=getDBConnection();     
    }   


    public static OracleDatabase getOracleDatabase() throws ClassNotFoundException, OracleException, SQLException, NamingException{
        // Get an OracleRDBMSClient - starting point of SODA for Java application.
        OracleRDBMSClient cl = new OracleRDBMSClient();
        // Get a database.
        OracleDatabase db = cl.getDatabase(dbInitialization());
        return db;

    }


    public static OracleCollection createCollection(String collectionName) throws ClassNotFoundException, OracleException, SQLException, NamingException {
        
        OracleCollection col = getOracleDatabase().admin().createCollection(collectionName);  

        return col;
    }

    // List Collection
    public static List<String> getCollectionNames() throws ClassNotFoundException, OracleException, SQLException, NamingException{
        List<String> names=getOracleDatabase().admin().getCollectionNames();
        return names;
    } 

    // Open Collection 
    public static OracleCollection openCollection(String MyCollectionName) throws ClassNotFoundException, OracleException, SQLException, NamingException {
        OracleCollection col = ((OracleDatabase) getOracleDatabase().admin()).openCollection(MyCollectionName);
        return col;
    }


    public static void destroyConnection() throws SQLException {
        
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            
        }
    }

    public static OracleRDBMSClient getRDBMSClient(){
        OracleRDBMSClient cl = new OracleRDBMSClient();
		return cl;
    }



    public static void cursorDestroy() throws SQLException {
        
        try {
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            
        }
    }

    // public static boolean isJSONValid(String StringValue) {
    //     try {
    //         new JSONObject(StringValue);

    //     } catch (JsonException | JSONException ex) {
    //         // edited, to include @Arthur's comment
    //         // e.g. in case JSONArray is valid as well...
    //         try {
    //             new JSONArray(StringValue);
    //         } catch (JSONException ex1) {
    //             return false;
    //         }
    //     }
    //     return true;
    // }


}