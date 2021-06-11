import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import oracle.soda.OracleException;

public class PrintCollectionList {

    public static void main(String[] args) throws ClassNotFoundException, OracleException, SQLException, NamingException {
    
        printCollection();
    }

    public static void printCollection() throws ClassNotFoundException, OracleException, SQLException, NamingException{

        List<String> names=DBConnection.getCollectionNames();

        for(String name : names){
            System.out.println("Collection Name: "+name);
        }

        DBConnection.destroyConnection();

    }


}