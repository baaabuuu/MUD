package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class Cl_Main extends JFrame implements KeyListener{
	// Declare components for the program's window
		public JButton btn1;
		public JLabel lbl1, lbl2, lbl3;
		public JTextArea txtArea1, txtArea2, txtArea3, txtArea4;
	Cl_Main(){
		// Set a BorderLayout on the main window
		getContentPane().setLayout(new BorderLayout());
		
		txtArea1 = new JTextArea(20,20);
		txtArea1.setLineWrap(true);
		txtArea1.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(txtArea1);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		txtArea2 = new JTextArea(20,20);
		txtArea2.setLineWrap(true);
		txtArea2.setEditable(false);
		
		JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
        
        p1.add(scroll);
        p1.add(Box.createRigidArea(new Dimension(10, 10)));
        p1.add(txtArea2);
        
        getContentPane().add(p1, BorderLayout.CENTER);
		
		
	}
	
	
	
	
	
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		Cl_Main window = new Cl_Main();
        
		window.setTitle("TempName"); // Set title on window
		window.setSize(640,480);	// Set size
		window.setResizable(false);    // Allow the window to be resized
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);      // Make the window visible
    }
	
	
	
	
	
}
