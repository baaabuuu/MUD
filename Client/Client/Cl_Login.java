package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Cl_Login extends JDialog implements ActionListener {
	private JTextField userField;
	private JPasswordField passField;
	private JLabel lblUser, lblPass, lblError, empty, empty2;
	private JButton btnLogin, btnExit;
	private String hashed;
	private Cl_Transmit transmit;
	private Cl_Main parent;
	
	Cl_Login(Cl_Main parent, Cl_Transmit transmit){
		super(parent,"Login",true);
		this.transmit = transmit;
		this.parent = parent;
		
		
		getRootPane().setOpaque(false);
		getContentPane().setBackground (new Color (0, 0, 0, 0));
		
		//Removes top tool-bar.
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
        GridBagConstraints cs = new GridBagConstraints();
        
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        lblError = new JLabel(" ");
        lblError.setForeground(Color.red);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(lblError, cs);
        
        empty = new JLabel("     ");
        cs.gridx = 4;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(empty, cs);
        
        empty2 = new JLabel("     ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(empty2, cs);
        
        lblUser = new JLabel("Username: ");
        lblUser.setForeground(Color.white);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lblUser, cs);
        
        userField = new JTextField(20);
        cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(userField, cs);
        
        lblPass = new JLabel("Password: ");
        lblPass.setForeground(Color.white);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lblPass, cs);
        
        passField = new JPasswordField(20);
        cs.gridx = 2;
        cs.gridy = 2;
        cs.gridwidth = 2;
        passField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        panel.add(passField, cs);
        
        btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(75,25));
        btnLogin.setForeground(Color.white);
        btnLogin.setFocusPainted(false);
        btnLogin.setBackground(new Color(219, 142, 27));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	login();
            }
        });
        
        btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(75,25));
        btnExit.setForeground(Color.white);
        btnExit.setFocusPainted(false);
        btnExit.setBackground(new Color(219, 142, 27));
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.dispose();
                System.exit(0);
            }
        });
        
        JPanel bp = new JPanel();
        bp.setOpaque(false);
        bp.add(btnLogin);
        bp.add(btnExit);
        
        JPanel logBack = new Login_Background(); 
        logBack.setLayout(new BorderLayout());
        logBack.add(panel,BorderLayout.CENTER);
        logBack.add(bp,BorderLayout.PAGE_END);
        
        getContentPane().add(logBack, BorderLayout.CENTER);
        
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
	}
	private void login(){
		lblError.setText(" ");
		
    	transmit.putToQueue(":DAT:" + userField.getText());
    	
    	//hashed = "$2a$10$PeCKmQYkxo/B0kb8Ccda8.xmlh8aAc20OkRFlrvuaiiZua14n/bKG";
    	
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
            		parent.updUsername(userField.getText());
            		Cl_Main.runGame();
            		transmit.putToQueue(":DAT:" + "YES");
            		dispose();
            		break;
            	}
    		}
    		if(i == 2){
        		lblError.setText("ERROR");
        	}
    	}
    	plainText = "";
    	hashed = null;
    	pass = null;
	}
    public void updHashed(String hashed){
    	this.hashed = hashed;
    }
	public void actionPerformed(ActionEvent e) {}
	
}

/** 
 * A JPanel that contains the "loginBackground.png" background image.
 */
class Login_Background extends JPanel{
	Image bg = new ImageIcon(Cl_Login.class.getResource("loginBackground.png")).getImage();
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}