import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HomePage {
	protected MenuList Menu;
	private UserData MakeTable;
	private JFrame frame;
	public  JTextField textField;
	public JTextField textField_1;
	private  JPanel panel_1;
	private  JTextField textField_2;
	private  JTextField textField_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePage() {
		initialize();
		//MainPage();
		//MakeOrder(null);
		//OrderDetailPanel(null);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		int ScreenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int ScreenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setAlwaysOnTop(false);
		frame.setResizable(false);
		frame.setTitle("Biller");
		frame.setBounds(0, 0,(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-40);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(99,95,94));
				frame.getContentPane().setLayout(null);
			
				JPanel ContentFile = new JPanel();
				ContentFile.setBounds(508, 213, 342,299);
				ContentFile.setBackground(Color.BLACK);
				frame.getContentPane().add(ContentFile);
				ContentFile.setLayout(null);
				
				JLabel lblWelcome = new JLabel("Welcome");
				lblWelcome.setBounds(89, 0, 161, 56);
				lblWelcome.setForeground(Color.WHITE);
				lblWelcome.setBackground(Color.BLACK);
				lblWelcome.setFont(new Font("Algerian",Font.BOLD,30));
				ContentFile.add(lblWelcome);
				
				JLabel lblUsername = new JLabel("UserName");
				lblUsername.setBounds(79, 82, 150, 23);
				lblUsername.setForeground(Color.WHITE);
				lblUsername.setBackground(Color.BLACK);
				lblUsername.setFont(new Font("Algerian",Font.BOLD,25));
				ContentFile.add(lblUsername);
				
				textField = new JTextField();
				textField.setBounds(79, 116, 197, 23);
				ContentFile.add(textField);
				textField.setColumns(10);
				
				JLabel lblPassword = new JLabel("Password");
				lblPassword.setBounds(79, 150, 150, 23);
				lblPassword.setForeground(Color.WHITE);
				lblPassword.setBackground(Color.BLACK);
				lblPassword.setFont(new Font("Algerian",Font.BOLD,25));
				ContentFile.add(lblPassword);
				
				textField_1 = new JTextField();
				textField_1.setColumns(10);
				textField_1.setBounds(79, 184, 197, 23);
				ContentFile.add(textField_1);
				
				JButton btnClickMe = new JButton("Login In");
				btnClickMe.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","1234");
							Statement st = con.createStatement();
							ResultSet rs = st.executeQuery("Select Pass from DEMODB.Login where Name = "+Home.AddSingleQuote(textField.getText().trim()));
							
							if(rs.isBeforeFirst())
							{
								rs.next();
								System.out.println(rs);
								if(rs.getString(1).equals(textField_1.getText())) {
									frame.setVisible(false);
									MainPage();
								}else {
									JOptionPane.showMessageDialog(frame, "Please Enter Valid Credentials");
								}
							}else {
								JOptionPane.showMessageDialog(frame, "Invaid Username");
							}
							 rs.close();
							 st.close();
							con.close();
							}catch(Exception e) {
							System.out.println("erro"+e.getMessage());
							
						}
					}
				});
				btnClickMe.setBounds(77, 245, 196, 23);
				ContentFile.add(btnClickMe);
	}
	/*
	 * Create A Main Page after login
	 */
	private void MainPage() {

		MakeTable = new UserData();
		Menu = new MenuList();
		for(int i=0;i<MakeTable.getNumberOfTables();i++) {
			if(MakeTable.getTableData(i).getTableStatus()==1) {
				MakeTable.getTableData(i).setOrderFromDatabase();
			}
		}
		JFrame frame2 = new JFrame();
		Border border = BorderFactory.createLineBorder(Color.black, 2, true);
		frame2.setAlwaysOnTop(true);
		frame2.setAlwaysOnTop(false);
		frame2.setResizable(false);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setBounds(0, 0,(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-40);
		frame2.getContentPane().setLayout(null);
		
		
		JPanel MenuPanel = new JPanel();
		
		MenuPanel.setPreferredSize(new Dimension(1061,817));
		MenuPanel.setBackground(Color.black);
		MenuPanel.setLayout(new FlowLayout(0,10,10));
		//MenuPanel.setBackground(Color.red);
		
		JScrollPane panel_2 = new JScrollPane(MenuPanel);
		panel_2.setVisible(false);
		
				
		panel_1 = new JPanel();
		panel_1.setBounds(289, 11, 1061, 717);
		panel_1.setBackground(Color.white);
		frame2.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 60));
		panel_2.setBounds(289, 11, 1061, 717);
		
		frame2.getContentPane().add(panel_2);

		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 269, 717);
		frame2.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(289, 11, 1061, 717);
		panel_3.setBackground(Color.white);
		panel_3.setForeground(Color.black);
		frame2.getContentPane().add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(289, 11, 1061, 717);
		panel_4.setBackground(Color.black);
		panel_4.setForeground(Color.white);
		frame2.getContentPane().add(panel_4);
		SetTables();
		JButton btnNewButton = new JButton("Home");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel_1.setVisible(true);
				panel_2.setVisible(false);
				MenuPanel.setVisible(false);
				panel_3.setVisible(false);
				panel_4.setVisible(false);
				
			}
		});
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.white);
		btnNewButton.setBounds(0, 0, 269, 85);
		panel.add(btnNewButton);
		

		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setBorder(border);
		btnMenu.setBackground(Color.WHITE);
		btnMenu.setForeground(Color.black);
		btnMenu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				panel_1.setVisible(false);
				panel_2.setVisible(true);
				MenuPanel.setVisible(true);
				panel_3.setVisible(false);
				panel_4.setVisible(false);
				
			}
		});
		btnMenu.setBounds(0, 96, 269, 85);
		panel.add(btnMenu);
		

		JButton btnTables = new JButton("Tables");
		btnTables.setBackground(Color.BLACK);
		btnTables.setForeground(Color.white);
		btnTables.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				MenuPanel.setVisible(false);
				panel_3.setVisible(true);
				panel_4.setVisible(false);
				
			}
		});
		btnTables.setBounds(0, 192, 269, 85);
		panel.add(btnTables);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				frame2.setVisible(false);
			}
		});
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setForeground(Color.black);
		btnLogout.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				panel_1.setVisible(false);
				panel_2.setVisible(false);
				MenuPanel.setVisible(false);
				panel_3.setVisible(false);
				panel_4.setVisible(true);
			}
		});
		btnLogout.setBounds(0, 288, 269, 85);
		
		JPanel CategoryPanel[] = new JPanel[Menu.getNumberOfCateogries()];
		for(int i=0;i<Menu.getNumberOfCateogries();i++) {
			CategoryPanel[i] = new JPanel();
			CategoryPanel[i].setPreferredSize(new Dimension(310,500));
			CategoryPanel[i].setBackground(Color.white);
			CategoryPanel[i].setLayout(new FlowLayout());

			JButton jl = new JButton(Menu.getFoodCategory(i).getCategoryName());
			jl.setBackground(Color.black);
			jl.setPreferredSize(new Dimension(300,30));
			jl.setForeground(Color.white);
			CategoryPanel[i].add(jl);
			MenuPanel.add(CategoryPanel[i]);
		}
		JLabel FoodItemLabel[][] = new  JLabel[Menu.getNumberOfCateogries()][Menu.getFoodCategory(0).getItems()];
		for(int i=0;i<Menu.getNumberOfCateogries();i++) {
			for(int j=0;j<Menu.getFoodCategory(i).getItems();j++) {
				int catnum = i;
				int Foodnum = j;
				FoodItemLabel[catnum][Foodnum] = new JLabel(Menu.getFoodCategory(catnum).getFoodItem(Foodnum).getItemName());
				FoodItemLabel[catnum][Foodnum].setForeground(Color.black);
				FoodItemLabel[catnum][Foodnum].setBorder(border);						
				FoodItemLabel[catnum][Foodnum].setOpaque(true);
				FoodItemLabel[catnum][Foodnum].setBackground(null);
				FoodItemLabel[catnum][Foodnum].setFont(new Font("Magneto",Font.BOLD,15));
				FoodItemLabel[catnum][Foodnum].setPreferredSize(new Dimension(300,30));
				FoodItemLabel[catnum][Foodnum].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						FoodItemLabel[catnum][Foodnum].setBackground(Color.BLUE);
						FoodItemLabel[catnum][Foodnum].setFont(new Font("Magneto",Font.BOLD,17));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						FoodItemLabel[catnum][Foodnum].setBackground(null);
						FoodItemLabel[catnum][Foodnum].setFont(new Font("Magneto",Font.BOLD,15));
					}
				});
				CategoryPanel[catnum].add(FoodItemLabel[catnum][Foodnum]);
			}
		}
		
		panel.add(btnLogout);
		frame2.setVisible(true);
	}
	
	private void SetTables() {		
		
		JButton TableButton[] = new JButton[MakeTable.getNumberOfTables()];
		
		
		JPopupMenu PopMenu = new JPopupMenu();
		JMenuItem Time = new JMenuItem("Time");
		JMenuItem Owner = new JMenuItem("Owner");
		JMenuItem Book = new JMenuItem("Book");
		JMenuItem Order = new JMenuItem("Order");
		JMenuItem Capacity = new JMenuItem("Capacity");
		Order.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderDetailPanel(MakeTable.getTableDataByName(PopMenu.getName()));
			}
		});
		Book.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MakeTable.getTableDataByName(PopMenu.getName()).CreateNewOrder();
				MakeOrder(MakeTable.getTableDataByName(PopMenu.getName()));
			}
		});
		JMenuItem CheckOut = new JMenuItem("CheckOut");
	
		
		
		for(int i=0;i<MakeTable.getNumberOfTables();i++) {
			int num = i;
			TableButton[i] = new JButton(String.valueOf(MakeTable.getTableData(i).getTableNumber()));
			TableButton[i].setPreferredSize(new Dimension(300,150));
			
			switch(MakeTable.getTableData(i).getTableStatus()) {
			case 0:
				TableButton[i].setBackground(Color.white);
				TableButton[i].setForeground(Color.black);
				break;
			case 1:
				TableButton[i].setBackground(Color.black);
				TableButton[i].setForeground(Color.WHITE);
				break;
			case 2:
				TableButton[i].setBackground(Color.GRAY);
				TableButton[i].setForeground(Color.WHITE);
				break;
				default:
					TableButton[i].setBackground(Color.white);
					TableButton[i].setForeground(Color.black);
					break;
			}
			TableButton[i].setFont(new Font("Magneto",Font.CENTER_BASELINE,20));
			TableButton[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					TableButton[num].setSize(new Dimension(310,160));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					TableButton[num].setSize(new Dimension(300,150));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					switch(MakeTable.getTableData(num).getTableStatus()) {
					case 0:
						PopMenu.removeAll();
						PopMenu.add(Book);
						PopMenu.add(Capacity);
						PopMenu.setName(String.valueOf(MakeTable.getTableData(num).getTableNumber()));
						Capacity.setText("Capacity : "+MakeTable.getTableData(num).getCapacity());
						break;
					case 1:
						PopMenu.removeAll();
						PopMenu.add(Owner);
						PopMenu.setName("1");
						Owner.setText("Owner : "+MakeTable.getTableData(num).getOwnerName());
						PopMenu.add(Capacity);
						PopMenu.add(Time);
						PopMenu.setName(String.valueOf(MakeTable.getTableData(num).getTableNumber()));
						Capacity.setText("Capacity : "+MakeTable.getTableData(num).getCapacity());
						PopMenu.add(Order);
						PopMenu.add(CheckOut);
						break;
					case 2:
						PopMenu.removeAll();
						PopMenu.add(Owner);
						PopMenu.setName("2");
						Owner.setText("Owner : "+MakeTable.getTableData(num).getOwnerName());
						PopMenu.add(Capacity);
						PopMenu.setName(String.valueOf(MakeTable.getTableData(num).getTableNumber()));
						Capacity.setText("Capacity : "+MakeTable.getTableData(num).getCapacity());
						PopMenu.add(Time);
						break;
					}
					for(int NumberofMenu = 2; NumberofMenu<PopMenu.getComponentCount()+2;NumberofMenu++) {
						int num = NumberofMenu;
						if(NumberofMenu%2==0) {
							PopMenu.getComponent(NumberofMenu-2).setBackground(Color.white);
							PopMenu.getComponent(NumberofMenu-2).setForeground(Color.black);
							PopMenu.getComponent(NumberofMenu-2).addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									PopMenu.getComponent(num-2).setBackground(Color.black);
									PopMenu.getComponent(num-2).setForeground(Color.white);
							}
							@Override
							public void mouseExited(MouseEvent e) {
								PopMenu.getComponent(num-2).setBackground(Color.white);
								PopMenu.getComponent(num-2).setForeground(Color.black);
							}
							});
						}else {
							PopMenu.getComponent(num-2).setBackground(Color.black);
							PopMenu.getComponent(num-2).setForeground(Color.white);		
							PopMenu.getComponent(NumberofMenu-2).addMouseListener(new MouseAdapter() {
								@Override
								public void mouseEntered(MouseEvent arg0) {
									PopMenu.getComponent(num-2).setBackground(Color.white);
									PopMenu.getComponent(num-2).setForeground(Color.black);
							}
							@Override
							public void mouseExited(MouseEvent e) {
								PopMenu.getComponent(num-2).setBackground(Color.black);
								PopMenu.getComponent(num-2).setForeground(Color.white);
							}
							});		
						}
					}
					PopMenu.show(TableButton[num], e.getX(), e.getY());
				}
			});
			
			
			panel_1.add(TableButton[i]);
		}
		
	}
	private void MakeOrder(TableData Table) {
		JFrame OrderFrame = new JFrame();
		
		
		OrderFrame.setBounds(100,150,900,500);
		OrderFrame.setVisible(true);
		//OrderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OrderFrame.getContentPane().setLayout(null);
		OrderFrame.setTitle("Table Number : "+Table.getTableNumber());
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 618, 96);
		panel_1.setBackground(Color.white);
		panel_1.setLayout(null);
		OrderFrame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.white);
		panel_2.setBounds(10, 118, 618, 332);
		OrderFrame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(283, 55, 62, 20);
		lblNewLabel_1.setText(String.valueOf(Table.getOrder().getTotal()));
		panel_1.add(lblNewLabel_1);
		
		JComboBox AddItemtext = new JComboBox();
		AddItemtext.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {  	
				try {
				if(AddItemtext.getEditor().getEditorComponent().toString()!=null) {
					List<String> Similar = Menu.getSimilarFoodItem(AddItemtext.getEditor().getItem().toString());	
					AddItemtext.removeAllItems();
				for(int i=0;i<Similar.size();i++) {
					AddItemtext.addItem(Similar.get(i).toString());
				}
				}
				}catch(Exception ef) {
					ef.printStackTrace();
				}
			}
		});
		
		AddItemtext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		AddItemtext.setBounds(80,10,166,30);
		AddItemtext.setEditable(true);
		panel_1.add(AddItemtext);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.black);
		panel_3.setBounds(638, 215, 236, 235);
		OrderFrame.getContentPane().add(panel_3);
		
		JLabel lblFoodiTem = new JLabel("QTY");
		lblFoodiTem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFoodiTem.setForeground(Color.WHITE);
		lblFoodiTem.setBounds(182, 0, 44, 27);
		panel_3.add(lblFoodiTem);
		
		JLabel label = new JLabel("Food Item");
		label.setBounds(10, 0, 124, 27);
		panel_3.add(label);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel CapacityLabel = new JLabel("Person");
		CapacityLabel.setBounds(355,55,65,20);
		panel_1.add(CapacityLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner.setBounds(419, 55, 40, 20);
		panel_1.add(spinner);
		
		JLabel AddItemLabel = new JLabel("FoodItem");
		AddItemLabel.setBounds(10,10,65,20);
		panel_1.add(AddItemLabel);
		
		
		JButton Done = new JButton("Done");
		Done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Table.getOrder().getNumberofItems()==0) {
					JOptionPane.showMessageDialog(frame, "Please Select Some Item");
				}else {
					JOptionPane.showMessageDialog(frame, "Thanks");
					Table.setTableStatus(1);
					Table.Update();
					Referesh();
					OrderFrame.setVisible(false);
				}
			}
		});
		Done.setBounds(355,9,100,23);
		panel_1.add(Done);
		
		JLabel lblQty = new JLabel("QTY");
		lblQty.setBounds(10, 55, 59, 20);
		panel_1.add(lblQty);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(80, 55, 70, 20);
		panel_1.add(spinner_1);
		
		JCheckBox checkBox = new JCheckBox("1/2");
		checkBox.setBounds(171, 54, 48, 23);
		panel_1.add(checkBox);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(AddItemtext.getSelectedItem().toString().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please Select some food item");
				}else if((int)spinner_1.getValue()==0){
					JOptionPane.showMessageDialog(frame, "Please Select Quantity");
				}else if(Menu.FoodItemExsist(AddItemtext.getSelectedItem().toString())){
					Table.getOrder().addFoodItem(AddItemtext.getSelectedItem().toString(),(Integer)spinner_1.getValue(),0,Menu.getMyNameFood(AddItemtext.getSelectedItem().toString()).getPrice());
					lblNewLabel_1.setText(String.valueOf(Table.getOrder().getTotal()));
					JOptionPane.showMessageDialog(frame,AddItemtext.getSelectedItem()+"is Ordered");
					
				}else {
					JOptionPane.showMessageDialog(frame, "Food Item is not available\nPlease Check your Enter Name");
				}
			}
		});
		btnAdd.setBounds(256, 9, 89, 23);
		panel_1.add(btnAdd);
		
		JPanel panel = new JPanel();
		panel.setBounds(638, 11, 236, 198);
		OrderFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblOwnername = new JLabel("OwnerName");
		lblOwnername.setBounds(10, 8, 75, 20);
		panel.add(lblOwnername);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 31, 170, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 62, 95, 20);
		panel.add(lblPhone);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 84, 170, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 107, 84, 20);
		panel.add(lblAddress);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 127, 170, 60);
		panel.add(textArea);
		
		
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch(JOptionPane.showConfirmDialog(frame, "Want to cancel Order", "Order Cancel Confirmation", 0)) {
				case 0:
					OrderFrame.setVisible(false);
					break;
				case 1:
					break;
				}
				
			}
		});
		btnNewButton_1.setBounds(465, 9, 100, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			
			}
		});
		btnReset.setBounds(476, 54, 89, 23);
		panel_1.add(btnReset);
		AddItemtext.setBounds(85,15,140,20);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(225, 55, 48, 20);
		panel_1.add(lblTotal);
	}

	private void OrderDetailPanel(TableData Table) {
		JFrame OrderDetailPanel = new JFrame();
		OrderDetailPanel.setBounds(100,150,900,500);
		OrderDetailPanel.setVisible(true);
		OrderDetailPanel.setTitle("Table Number : "+Table.getTableNumber());
		OrderDetailPanel.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setPreferredSize(new Dimension(850,40));
		lblNewLabel.setText("Leukcy");
		OrderDetailPanel.getContentPane().add(lblNewLabel);
		
		
		JLabel List[] = new JLabel[Table.getOrder().getNumberofItems()];
		
		for(int i=0;i<List.length;i++) {
			List[i] = new JLabel(Table.getOrder().getNameofFoodIdByIndex(i));
			List[i].setPreferredSize(new Dimension(850,40));
			List[i].setVisible(true);
			OrderDetailPanel.getContentPane().add(List[i]);
		}
		
	}
	protected void showTables(){
		for(int i=0;i<MakeTable.getNumberOfTables();i++) {
			System.out.println(MakeTable.getTableData(i).getTableNumber()+"=> "+MakeTable.getTableData(i).getTableStatus());
		}
	}
	protected void Referesh() {
		for(int i=0;i<panel_1.getComponentCount();i++) {
			if(MakeTable.getTableData(i).getTableStatus()==1) {
				panel_1.getComponent(i).setBackground(Color.BLACK);
				panel_1.getComponent(i).setForeground(Color.WHITE);
			}
		}
	}
}
