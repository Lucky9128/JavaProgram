import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Start{

	public static void main(String rag[]) {
		JFrame frame = new JFrame();
		frame.setBounds(50,100,500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200,60));
		panel.setBackground(Color.red);
		panel.setLayout(new FlowLayout(0,0,0));
		
		JButton label = new JButton("Hello");
		label.setPreferredSize(new Dimension(210,30));
		label.setVisible(true);
		panel.add(label);
		
		JButton label2 = new JButton("Hello2");
		label2.setPreferredSize(new Dimension(210,30));
		label2.setVisible(true);
		panel.add(label2);
		
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBounds(10,10,150,200);
		
		
		frame.getContentPane().add(scroll);
		frame.setVisible(true);
	}

}