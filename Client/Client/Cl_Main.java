package Client;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Toolkit;

public class Cl_Main extends JFrame{
	public String userName;
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
					mainFrame = new Cl_Main();
					mainFrame.setVisible(true);
					mainFrame.setSize(1280,650);
					
					game = new Cl_Game(mainFrame);
					
					String ipaddress = "192.168.1.50";
					transmit = new Cl_Transmit("transmitter", ipaddress, 8080);
			        transmit.start();
			        
			        login = new Cl_Login(mainFrame, transmit);
			        
			        consumer = new Cl_Consumer(mainFrame, transmit);
			        consumer.start();
					
					G_BG = new Game_Background();
					
					G_BG.setLayout(new BorderLayout());
					G_BG.add(login, BorderLayout.CENTER);
					mainFrame.setContentPane(G_BG);
					
			        // ConnectException
					
					
			        
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
		//setBounds(100, 100, 1280, 650);
	}
	/**
	 * Initialize the Cl_Game JPanel and adds it to the Game_Background panel, then updates
	 * the Cl_Main JFrame with the modified JPanel.
	 */
	public static void runGame(){
		
		G_BG.remove(login);
		G_BG.add(game, BorderLayout.CENTER);
		mainFrame.setContentPane(G_BG);
		// Tell server login was successful
		transmit.putToQueue(":DAT:" + "YES");
	}
	public void updUsername(String name){
		this.userName = name;
		game.lblName.setText("User: " + name);
	}
	private String eventAreaInfo;
	public void updEventArea(String text){
		
		if(game.eventArea.getLineCount() > 39){
			try {
				game.eventArea.replaceRange("", 0, game.eventArea.getLineEndOffset(0));
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		eventAreaInfo = game.eventArea.getText();
		eventAreaInfo += "\n" + text;
		
		game.eventArea.setText(eventAreaInfo);
		
		
	}
	private String chatRecAreaInfo;
	public void updChatArea(String text){
		if(game.chatArea.getLineCount() > 39){
			try {
				game.chatArea.replaceRange("", 0, game.chatArea.getLineEndOffset(0));
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		chatRecAreaInfo = game.chatArea.getText();
		chatRecAreaInfo += "\n" + text;
		game.chatArea.setText(chatRecAreaInfo);
		transmit.putToQueue(":CHA:" + text);
	}
	private String[] lblSplit;
	public void updLabels(String inbound){
		lblSplit = inbound.split("@");
		
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
