package Trail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;


public class CreateMenuBar {  
    private static final long serialVersionUID = 1L;  
  
    private static void createAndShowGUI() {  
  
        // Create and set up the window.  
        final JFrame frame = new JFrame("Scroll Pane Example");  
  
        // Display the window.  
        frame.setSize(500, 500);  
        frame.setVisible(true);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        // set flow layout for the frame  
        frame.getContentPane().setLayout(null);  

        JPanel jp = new JPanel();
        jp.setSize(new Dimension(200,200));
        jp.setLayout(new FlowLayout());
        
        JButton jb = new JButton("hello1");
        jb.setBackground(Color.blue);
      //  jb.setBounds(0,0,200,75);
        jb.setPreferredSize(new Dimension(200,75));
        
       JButton jb2 = new JButton("hello2");
        jb2.setBackground(Color.blue);
        //jb2.setBounds(0,75,200,75);
        jb2.setPreferredSize(new Dimension(200,75));
        
        JButton jb3 = new JButton("hello3");
        jb3.setBackground(Color.blue);
        //jb3.setBounds(0,150,200,75);
        jb3.setPreferredSize(new Dimension(200,75));
        
      
        jp.add(jb);
        jp.add(jb2);
        jp.add(jb3);
        
        
        JScrollPane scrollableTextArea = new JScrollPane(jp);  
        scrollableTextArea.setBounds(0,0,200,150);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);  
  
        frame.getContentPane().add(scrollableTextArea);  
    }  
    public static void main(String[] args) {  
  
  
        javax.swing.SwingUtilities.invokeLater(new Runnable() {  
  
            public void run() {  
                createAndShowGUI();  
            }  
        });  
    }  
}  
