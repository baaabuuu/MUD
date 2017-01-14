package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class Cl_Ser extends JPanel implements ActionListener{
	private JTextField serverField, socketField;
	private JLabel lblServer, lblSocket, lblError;
	private JButton btnConnect, btnExit;
	private Cl_Main parent;
	
	public Cl_Ser(Cl_Main parent) {
		this.parent = parent;
		
		setLayout(null);
		setOpaque(false);
		
		btnConnect = new JButton("Login");
		btnConnect.setBounds(540, 370, 90, 25);
		btnConnect.setForeground(Color.white);
        btnConnect.setFocusPainted(false);
        btnConnect.setBackground(new Color(219, 142, 27));
        btnConnect.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		add(btnConnect);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(650, 370, 90, 25);
		btnExit.setForeground(Color.white);
        btnExit.setFocusPainted(false);
        btnExit.setBackground(new Color(219, 142, 27));
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
		add(btnExit);
		
		lblServer = new JLabel("IP Address");
		lblServer.setForeground(Color.white);
		lblServer.setBounds(540, 300, 60, 14);
		add(lblServer);
		
		lblSocket = new JLabel("Password");
		lblSocket.setForeground(Color.white);
		lblSocket.setBounds(540, 335, 60, 14);
		add(lblSocket);
		
		socketField = new JTextField(20);
		socketField.setBounds(610, 335, 130, 20);
		socketField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
		add(socketField);
		
		serverField = new JTextField(20);
		serverField.setBounds(610, 300, 130, 20);
		add(serverField);
		
		
		lblError = new JLabel(" ");
		lblError.setForeground(Color.RED);
		lblError.setBounds(540, 275, 200, 14);
		add(lblError);
	}
	public void actionPerformed(ActionEvent arg0) {}
}