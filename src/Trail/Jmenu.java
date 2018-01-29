package Trail;

import java.awt.Color;
import java.awt.ScrollPane;

import javax.swing.JFrame;

public class Jmenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame = new JFrame();
		frame.setBounds(0, 0,900,600);
		frame.setTitle("Biller");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(99,95,94));
		
		ScrollPane sc = new ScrollPane();
		sc.setBounds(10,10,100,200);
		frame.getContentPane().add(sc);
		
	}

}
