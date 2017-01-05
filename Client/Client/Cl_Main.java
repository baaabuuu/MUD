package Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import components.DocumentSizeFilter;

public class Cl_Main extends JFrame implements KeyListener{
	// Declare components for the program's window
		public JButton btn1;
		public JLabel lblMainTitle, lblPlayerHealth, lbl3, lblRemainingWords;
		public JTextArea eventArea, actionArea, chatRecieveArea, chatTypingArea;
		private DefaultStyledDocument doc;
		
	Cl_Main(){
		// Set a BorderLayout on the main window
		getContentPane().setLayout(new BorderLayout());
		
		
		doc = new DefaultStyledDocument();
        doc.setDocumentFilter(new DocumentSizeFilter(255));
        doc.addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e) {updateCount();}
			public void insertUpdate(DocumentEvent e) {updateCount();}
			public void removeUpdate(DocumentEvent e) {updateCount();}
        });
		
		
        eventArea = new JTextArea(20,20);
        eventArea.setLineWrap(true);
        eventArea.setEditable(false);
		
		JScrollPane eventAreaScroll = new JScrollPane(eventArea);
		eventAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		actionArea = new JTextArea(1,1);
		actionArea.setLineWrap(true);
		actionArea.setEditable(true);
		actionArea.setDocument(doc);
		
		lblRemainingWords = new JLabel();
		
		updateCount();
		
		JPanel p1 = new JPanel();
		GroupLayout layout = new GroupLayout(p1);
        p1.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
        		   layout.createSequentialGroup()
        		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		           .addComponent(lblMainTitle)
        		           .addComponent(eventAreaScroll)
        		           .addComponent(actionArea))
        		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		           .addComponent(lblPlayerHealth)
        		           .addComponent(lblPlayerHealth)
        		           .addComponent(lblRemainingWords))
        		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           		           .addComponent(chatRecieveArea)
           		           .addComponent(chatTypingArea))
        		);
        		layout.setVerticalGroup(
        		   layout.createSequentialGroup()
        		      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        		           .addComponent(lblPlayerHealth)
        		           .addComponent(lblPlayerHealth)
        		           .addComponent(lblPlayerHealth))
        		      .addComponent(lblPlayerHealth)
        		);
        
        
        p1.add(lblPlayerHealth);
        p1.add(Box.createRigidArea(new Dimension(20, 20)));
        p1.add(actionArea);
        p1.add(Box.createRigidArea(new Dimension(20, 20)));
        p1.add(lblPlayerHealth);
        p1.add(Box.createRigidArea(new Dimension(20, 20)));
        
        getContentPane().add(p1, BorderLayout.WEST);
		
		
	}
	public void updateMainWindow(String text){
		
	}
	private void updateCount()
    {
		lblRemainingWords.setText((255 -doc.getLength()) + " characters remaining");
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
