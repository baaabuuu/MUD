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
	
	private boolean loggedIn = false;
	static JPanel G_BG;
	static Cl_Game game;
	static Cl_Main mainFrame;
	static Cl_Login login;
	static Cl_Consumer consumer;
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
					String ipaddress = "192.168.1.50";
					transmit = new Cl_Transmit("transmitter", ipaddress, 8080);
			        transmit.start();
			        
			        consumer = new Cl_Consumer(mainFrame, transmit);
			        consumer.start();
			        
					login = new Cl_Login(mainFrame, transmit);
			        login.setVisible(true);
					
			        
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
		setBounds(100, 100, 1280, 650);
		//getContentPane().setBackground(Color.RED);
		//setContentPane(new JLabel(new ImageIcon(Cl_Main.class.getResource("IconImage.png"))));
	}
	/**
	 * Initialize the Cl_Game JPanel and adds it to the Game_Background panel, then updates
	 * the Cl_Main JFrame with the modified JPanel.
	 */
	public static void runGame(){
		game = new Cl_Game(mainFrame);
		G_BG.setLayout(new BorderLayout());
		G_BG.add(game, BorderLayout.CENTER);
		mainFrame.setContentPane(G_BG);
	}
	private String userName;
	public void updUsername(String name){
		userName = name;
		//game.lblName.setText("User: " + userName);
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
	private String[] lblSplit;
	public void updLabels(String inbound){
		lblSplit = inbound.split(" ");
		
		game.lblHP.setText("HP: " + lblSplit[0]);
		game.lblMight.setText("Might: " + lblSplit[1]);
		game.lblDex.setText("Dexterity: " + lblSplit[2]);
		game.lblWisdom.setText("Widom: " + lblSplit[3]);
		game.lblConstitution.setText("Constitution: " + lblSplit[4]);
		game.lblCrit.setText("Crit: " + lblSplit[5]);
		game.lblAccuracy.setText("Accuracy: " + lblSplit[6]);
		game.lblEvasion.setText("Evasion: " + lblSplit[7]);
		game.lblArmor.setText("Armor: " + lblSplit[8]);
		game.lblResist.setText("Resist: " + lblSplit[9]);
	}
	private String[] listSplit;
	public void updList(String inbound){
		listSplit = inbound.split("@");
		
		
		
	}
}
/** 
 * A JPanel that contains the "BackgroundImage.jpg" background image.
 */
class Game_Background extends JPanel{
	Image bg = new ImageIcon(Cl_Main.class.getResource("BackgroundImage.jpg")).getImage();
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}