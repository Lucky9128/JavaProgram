import java.awt.Menu;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

import oracle.sql.DATE;

public class Test {
	public static void main(String args[]) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select Pass from DEMODB.Login where Name = 'Luc'");
			System.out.println(rs.isBeforeFirst());
			 rs.close();
			 st.close();
			con.close();
			}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}
}
