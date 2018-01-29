import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vbfhb extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vbfhb frame = new vbfhb();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vbfhb() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MenuList Menu = new MenuList();
		
		JComboBox comboBox = new JComboBox();
		comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(comboBox.getEditor().getItem());
			}
		});
		comboBox.setBounds(114, 58, 282, 35);
		comboBox.setEditable(true);
		contentPane.add(comboBox);
	
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(129, 144, 89, 23);
		contentPane.add(btnNewButton);
		
	}
}
