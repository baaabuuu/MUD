package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Cl_Login extends JDialog implements ActionListener {
	private JTextField userField;
	private JPasswordField passField;
	private JLabel userLabel, passLabel, empty, empty2, empty3;
	private JButton btnLogin, btnCancel;
    private boolean succeeded;
	
	Cl_Login(Frame parent){
		super(parent,"Login",true);
		
		getRootPane().setOpaque(false);
		getContentPane ().setBackground (new Color (0, 0, 0, 0));
		
		//Removes top tool-bar.
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
        GridBagConstraints cs = new GridBagConstraints();
        
        cs.fill = GridBagConstraints.HORIZONTAL;
		
        empty = new JLabel(" ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 3;
        panel.add(empty, cs);
        
        empty2 = new JLabel("     ");
        cs.gridx = 4;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(empty2, cs);
        
        empty3 = new JLabel("     ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(empty3, cs);
        
        userLabel = new JLabel("Username: ");
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(userLabel, cs);
        
        userField = new JTextField(20);
        cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(userField, cs);
        
        passLabel = new JLabel("Password: ");
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(passLabel, cs);
        
        passField = new JPasswordField(20);
        cs.gridx = 2;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(passField, cs);
        
        btnLogin = new JButton("Login");
        
        btnCancel = new JButton("Exit");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.dispose();
            }
        });
        
        JPanel bp = new JPanel();
        bp.setOpaque(false);
        bp.add(btnLogin);
        bp.add(btnCancel);
        
        JPanel logBack = new Login_Background(); 
        logBack.setLayout(new BorderLayout());
        logBack.add(panel,BorderLayout.CENTER);
        logBack.add(bp,BorderLayout.PAGE_END);
        //getContentPane().add(panel, BorderLayout.CENTER);
        //getContentPane().add(bp, BorderLayout.PAGE_END);
        
        getContentPane().add(logBack, BorderLayout.CENTER);
        
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
	}
	public String getUsername() {
        return userField.getText().trim();
    }

    public String getPassword() {
        return new String(passField.getPassword());
    }
	public void actionPerformed(ActionEvent e) {}
	
}
class Login_Background extends JPanel{
	Image bg = new ImageIcon(Cl_Login.class.getResource("loginBackground.png")).getImage();
	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}