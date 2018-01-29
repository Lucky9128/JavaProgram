import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Home {
	
	public static void main(String args[]) {
			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT FOODITEM.FOODID,FOODITEM.NAME,FOODITEM.PRICE FROM DEMODB.FOODITEM INNER JOIN DEMODB.FOODCATEGORY ON DEMODB.FOODCATEGORY.ID = DEMODB.FOODITEM.CATEGORYID AND DEMODB.FOODCATEGORY.ID ='C01'");
			rs.next();
			
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));

			 rs.close();
			 st.close();
			con.close();
			}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			}
	}
	
	protected static String AddSingleQuote(String Name) {
		return ("'"+Name+"'");
	}
}
