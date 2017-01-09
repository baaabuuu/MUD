package Client;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextArea;
import javax.swing.text.DefaultStyledDocument;

import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import components.DocumentSizeFilter;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

public class Cl_Main extends JFrame{
	private String userName;
	private boolean loggedIn = false;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPanel G_BG = new Game_Background();
					Cl_Game game = new Cl_Game();
					G_BG.setLayout(new BorderLayout());
					G_BG.add(game, BorderLayout.CENTER);
					Cl_Main mainFrame = new Cl_Main();
					mainFrame.setVisible(true);
					mainFrame.setSize(1280,650);
					mainFrame.setContentPane(G_BG);
					Cl_Login loginDlg = new Cl_Login(mainFrame);
			        loginDlg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Cl_Main() {
		setTitle("The Game!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cl_Main.class.getResource("IconImage.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 600);
		//getContentPane().setBackground(Color.RED);
		//setContentPane(new JLabel(new ImageIcon(Cl_Main.class.getResource("IconImage.png"))));
	}
}
class Login_Background extends JPanel{
	Image bg = new ImageIcon(Cl_Main.class.getResource("BackgroundImage.png")).getImage();
	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
class Game_Background extends JPanel{
	Image bg = new ImageIcon(Cl_Main.class.getResource("BackgroundImage.jpg")).getImage();
	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}