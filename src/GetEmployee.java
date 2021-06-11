// import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import oracle.soda.OracleCollection;
import oracle.soda.OracleCursor;
import oracle.soda.OracleDocument;
import oracle.soda.OracleException;

public class GetEmployee {
    public static void main(String[] args) {
        getEmployee();

    }   
    
    public static void getEmployee(){
        
        try {
		OracleCollection col=DBConnection.getOracleDatabase().openCollection("employee");
        OracleCursor  cur=col.find().getCursor();
        while(cur.hasNext()){
            OracleDocument doc=cur.next();
            System.out.println(doc.getContentAsString());
        }

        } catch (ClassNotFoundException | OracleException | SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            System.out.println(e.getMessage());
		}finally {

            //  try {
			// 	DBConnection.cursorDestroy();
			// } catch (SQLException e) {
			// 	// TODO Auto-generated catch block
			// 	e.printStackTrace();
			// }   
        }
    }

}