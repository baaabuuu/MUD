package Client;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;

import java.awt.BorderLayout;
import java.awt.Toolkit;

public class Cl_Main extends JFrame{
	
	private String userName;
	private boolean loggedIn = false;
	static JPanel G_BG;
	static Cl_Game game;
	static Cl_Main mainFrame;
	static Cl_Login login;
	static Cl_Consumer input;
	static Cl_Transmit transmit;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					G_BG = new Game_Background();
					mainFrame = new Cl_Main();
					mainFrame.setVisible(true);
					mainFrame.setSize(1280,650);
					mainFrame.setContentPane(G_BG);
					login = new Cl_Login(mainFrame);
			        login.setVisible(true);
					
			        transmit = new Cl_Transmit("transmitter name", "servername", 8080);
			        
			        input = new Cl_Consumer(mainFrame, transmit);
			        input.start();
			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Cl_Main() {
		setTitle("The Game!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cl_Main.class.getResource("/Client/IconImage.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 600);
		//getContentPane().setBackground(Color.RED);
		//setContentPane(new JLabel(new ImageIcon(Cl_Main.class.getResource("IconImage.png"))));
	}
	public static void runGame(){
		game = new Cl_Game(mainFrame);
		G_BG.setLayout(new BorderLayout());
		G_BG.add(game, BorderLayout.CENTER);
		mainFrame.setContentPane(G_BG);
	}
	private String eventAreaInfo;
	public void updEventArea(String text){
		eventAreaInfo = game.eventArea.getText();
		eventAreaInfo += "\n" + text;
		game.eventArea.setText(eventAreaInfo);
	}
	private String chatRecAreaInfo;
	public void updChatArea(String text){
		chatRecAreaInfo = game.chatRecArea.getText();
		chatRecAreaInfo += "\n" + text;
		game.chatRecArea.setText(chatRecAreaInfo);
		transmit.putToQueue("" + text);
	}
	private String[] labelSplit;
	public void updLabels(String inbound){
		labelSplit = inbound.split(" ");
		
		game.lblHP.setText("HP: " + labelSplit[0]);
		game.lblMight.setText("Might: " + labelSplit[1]);
		game.lblDex.setText("Dexterity: " + labelSplit[2]);
		game.lblWisdom.setText("Widom: " + labelSplit[3]);
		game.lblConstitution.setText("Constitution: " + labelSplit[4]);
		game.lblCrit.setText("Crit: " + labelSplit[5]);
		game.lblAccuracy.setText("Accuracy: " + labelSplit[6]);
		game.lblEvasion.setText("Evasion: " + labelSplit[7]);
		game.lblArmor.setText("Armor: " + labelSplit[8]);
		game.lblResist.setText("Resist: " + labelSplit[9]);
	}
	public void updList(String temp){
		
	}
}
class Game_Background extends JPanel{
	Image bg = new ImageIcon(Cl_Main.class.getResource("BackgroundImage.jpg")).getImage();
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}