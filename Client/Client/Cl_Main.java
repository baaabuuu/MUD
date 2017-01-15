package Client;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Toolkit;

// Client start/main class and the JFrame that all Panels attach to.
public class Cl_Main extends JFrame{
	public String userName;
	static JPanel G_BG;
	static Cl_Game game;
	static Cl_Main mainFrame;
	static Cl_Login login;
	static Cl_Consumer consumer;
	static Cl_Transmit transmit;
	static Cl_Ser server;
	String ipAddress;
	int socket;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Start new main and set size. 
					mainFrame = new Cl_Main();
					mainFrame.setVisible(true);
					mainFrame.setSize(1280,650);
					// start game and add a reference to main.
					game = new Cl_Game(mainFrame);
					// Start game background and set layout.
					G_BG = new Game_Background();
					G_BG.setLayout(new BorderLayout());
					// Start server selection and add a reference to main.
					server = new Cl_Ser(mainFrame);
					// Add server selection to background pane
					G_BG.add(server, BorderLayout.CENTER);
					// Add background pane to main.
					mainFrame.setContentPane(G_BG);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Cl_Main() {
		// Add title, icon, set resize and exit on close.
		setTitle("Dank Souls: The final deadline!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cl_Main.class.getResource("/Client/IconImage.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * Remove login panel and add game panel, then send login success.
	 */
	public void runGame(){
		G_BG.remove(login);
		G_BG.add(game, BorderLayout.CENTER);
		mainFrame.setContentPane(G_BG);
		// Tell server login was successful
		transmit.putToQueue(":DAT:" + "YES");
		// Set focus to actionArea.
		game.actionArea.requestFocus();
	}
	/**
	 * Initialize and start both transmit and consumer.
	 * Initialize login panel and replace server panel with login panel.
	 */
	public void runLogin(){
		transmit = new Cl_Transmit("transmitter", ipAddress, socket);
        transmit.start();
        
        consumer = new Cl_Consumer(mainFrame, transmit);
        consumer.start();
        
        login = new Cl_Login(mainFrame, transmit);
        
		G_BG.remove(server);
		G_BG.add(login, BorderLayout.CENTER);
		mainFrame.setContentPane(G_BG);
		// Set focus to userField.
		login.userField.requestFocus();
	}
	/**
	 * Update user name value and label.
	 */
	public void updUsername(String name){
		this.userName = name;
		game.lblName.setText("User: " + name);
	}
	
	private String eventAreaInfo;
	/**
	 * Update event area, by adding input text to the current text.
	 */
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
	/**
	 * Update chat area, by adding input text to the current text.
	 */
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
	}
	private String[] lblSplit;
	/**
	 * Update labels, by splitting input string by "@" and distributing
	 * its content to the labels.
	 */
	public void updLabels(String inbound){
		lblSplit = inbound.split("@");
		
		game.lblChar.setText("Character: " + lblSplit[0]);
		game.lblClass.setText("Class: " + lblSplit[1]);
		game.lblMight.setText("Might: " + lblSplit[2]);
		game.lblDex.setText("Dex: " + lblSplit[3]);
		game.lblWisdom.setText("Wisdom: " + lblSplit[4]);
		game.lblConstitution.setText("Con: " + lblSplit[5]);
		game.lblHP.setText("HP: " + lblSplit[6] + "/" + lblSplit[7]);
		game.lblCrit.setText("Crit: " + lblSplit[8]);
		game.lblAccuracy.setText("Acc: " + lblSplit[9]);
		game.lblEvasion.setText("Evasion: " + "-" + lblSplit[3]);
		game.lblArmor.setText("Armor: " + lblSplit[10]);
		game.lblDmg.setText("Dmg: " + lblSplit[11]);
	}
	private String[] inboundSplit;
	private String[] invSplit;
	private String[] equipSplit;
	public String[] descArray;
	/**
	 * Update item list, by splitting input string by "(#)", then
	 * splitting each of those by "@".
	 * 
	 * First split is between inventory and equipped items.
	 * 
	 * Second split is splitting alternating between item name
	 * and description. Index 0 is null, but every second there
	 * after is an item and +1 is its item description.
	 * 
	 * Item name is displayed in a JList, while descriptions are
	 * stored in a separate String array.
	 */
	public void updList(String inbound){
		// First split.
		inboundSplit = inbound.split("(#)");
		// Second split.
		invSplit = inboundSplit[0].split("@");
		equipSplit = inboundSplit[1].split("@");
		// Set array to null, so that it doesn't run a valueChanged
		// event in Cl_Game when .clear(); is used.
		descArray = null;
		// Clear list.
		game.listModel.clear();
		// Create array to contain item descriptions.
		descArray = new String[(invSplit.length+equipSplit.length-2)/2];
		//Add each array to list and each description into an array.
		int x = 0;
		for(int i = 0; i < invSplit.length;i++){
			if(i%2 == 1){
				System.out.println(invSplit[i]);
				game.listModel.addElement(invSplit[i]);
				descArray[x] = invSplit[i+1];
				x++;
			}
		}
		for(int i = 0; i < equipSplit.length;i++){
			if(i%2 == 1){
				game.listModel.addElement("[E]" + equipSplit[i]);
				descArray[x] = equipSplit[i+1];
				x++;
			}
		}
		game.itemList.setSelectedIndex(0);
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
