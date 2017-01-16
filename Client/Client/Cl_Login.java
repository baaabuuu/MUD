package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import components.BCrypt;

import java.awt.Font;
import java.awt.Color;

public class Cl_Login extends JPanel implements ActionListener{
	JTextField userField;
	private JPasswordField passField;
	private JLabel lblUser, lblPass, lblError;
	private JButton btnLogin, btnExit;
	private String hashed;
	private Cl_Transmit transmit;
	private Cl_Main main;
	
	public Cl_Login(Cl_Main main, Cl_Transmit transmit) {
		this.transmit = transmit;
		this.main = main;
		
		setLayout(null);
		setOpaque(false);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(540, 370, 90, 25);
		btnLogin.setForeground(Color.white);
        btnLogin.setFocusPainted(false);
        btnLogin.setBackground(new Color(219, 142, 27));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	login();
            }
        });
		add(btnLogin);
		
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
		
		lblUser = new JLabel("Username");
		lblUser.setForeground(Color.white);
		lblUser.setBounds(540, 300, 60, 14);
		add(lblUser);
		
		lblPass = new JLabel("Password");
		lblPass.setForeground(Color.white);
		lblPass.setBounds(540, 335, 60, 14);
		add(lblPass);
		
		passField = new JPasswordField(20);
		passField.setBounds(610, 335, 130, 20);
		passField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	login();
            }
        });
		add(passField);
		
		userField = new JTextField(20);
		userField.setBounds(610, 300, 130, 20);
		userField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	passField.requestFocus();
            }
        });
		add(userField);
		
		lblError = new JLabel(" ");
		lblError.setForeground(Color.RED);
		lblError.setBounds(540, 275, 200, 14);
		add(lblError);
	}
	private void login(){
		// Reset Error label.
		lblError.setText(" ");
		// There has to be something in user-name field.
		if(userField.getText().equals("")){
			lblError.setText("Error: Type in a username");
		}else{
			// If a nullPointerException happens, then it's due to a
			// connection not established and Error label is updated.
			//try{
				transmit.putToQueue(":DAT:" + userField.getText());
		    	
		    	char[] pass = passField.getPassword();
		    	String plainText = "";
		    	
		    	for(int i = 0; i < pass.length; i++){
		    		plainText += pass[i];
		    	}
		    	System.out.println("plaintext " + plainText);
		    	for(int i = 0; i<3; i++){
		    		System.out.println("hashed " + hashed);
		    		try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
		    		if(hashed != null){
		    			if(BCrypt.checkpw(plainText, hashed)){
		            		
		    				main.runGame();
		    				main.updUsername(userField.getText());
		            		break;
		            	}
		    		}
		    		if(i == 2){
		        		lblError.setText("Error: Failed to login");
		        	}
		    	}
		    	plainText = "";
		    	hashed = null;
		    	pass = null;
			//}catch(NullPointerException e2){
			//	lblError.setText("Error: No connection, restart game");
			//}
			
		}
    	
	}
    public void updHashed(String hashed){
    	this.hashed = hashed;
    }
	public void actionPerformed(ActionEvent arg0) {}
}