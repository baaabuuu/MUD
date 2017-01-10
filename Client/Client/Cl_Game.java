package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;

import components.DocumentSizeFilter;

public class Cl_Game extends JPanel implements ActionListener, KeyListener{
	
	private JButton actionSend, chatSend;
	public JLabel lblEvent, lblChat, lblRemainingWordsChat, lblRemainingWordsAction;
	public JLabel  lblHP, lblMight, lblDex, lblWisdom, lblConstitution, lblCrit;
	public JLabel lblAccuracy, lblEvasion, lblArmor, lblResist;
	public JTextArea eventArea, actionArea, chatRecArea, chatTypArea;
	private DefaultStyledDocument docAction, docChat;
	
	public Cl_Game() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		setOpaque(false);
		
		docAction = new DefaultStyledDocument();
		docAction.setDocumentFilter(new DocumentSizeFilter(255));
		docAction.addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e) {updateCount(1);}
			public void insertUpdate(DocumentEvent e) {updateCount(1);}
			public void removeUpdate(DocumentEvent e) {updateCount(1);}
        });
		docChat = new DefaultStyledDocument();
		docChat.setDocumentFilter(new DocumentSizeFilter(255));
		docChat.addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e) {updateCount(2);}
			public void insertUpdate(DocumentEvent e) {updateCount(2);}
			public void removeUpdate(DocumentEvent e) {updateCount(2);}
        });
		
		eventArea = new JTextArea("Welcome Adventurer!");
		eventArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		eventArea.setToolTipText("Read me!");
		eventArea.setLineWrap(true);
		eventArea.setEditable(false);
		
		JScrollPane eventAreaScroll = new JScrollPane(eventArea);
		eventAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		eventAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		eventAreaScroll.setBounds(10, 50, 600, 440);
		add(eventAreaScroll);
		
		actionArea = new MyTextArea();
		actionArea.setBackground(new Color(1,1,1, (float) 0.01));
		actionArea.setLineWrap(true);
		actionArea.setWrapStyleWord(true);
		actionArea.setToolTipText("Type me!");
		actionArea.addKeyListener(this);
		actionArea.setBounds(10, 500, 500, 75);
		actionArea.setDocument(docAction);
		add(actionArea);
		
		chatRecArea = new JTextArea("Welcome to the chat!");
		chatRecArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		chatRecArea.setToolTipText("Read me!");
		chatRecArea.setLineWrap(true);
		chatRecArea.setEditable(false);
		
		JScrollPane chatRecieveAreaScroll = new JScrollPane(chatRecArea);
		chatRecieveAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatRecieveAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatRecieveAreaScroll.setBounds(800, 51, 465, 440);
		add(chatRecieveAreaScroll);
		
		chatTypArea = new JTextArea();
		chatTypArea.setLineWrap(true);
		chatTypArea.setToolTipText("Type me!");
		chatTypArea.addKeyListener(this);
		chatTypArea.setBounds(800, 500, 365, 75);
		chatTypArea.setDocument(docChat);
		add(chatTypArea);
		
		actionSend = new JButton("Send");
		actionSend.setForeground(Color.white);
		actionSend.setFocusPainted(false);
		actionSend.setBackground(new Color(219, 142, 27));
		actionSend.setFont(new Font("Tahoma", Font.BOLD, 12));
		actionSend.setBounds(510, 500, 100, 75);
		add(actionSend);
		
		chatSend = new JButton("Send");
		chatSend.setForeground(Color.white);
		chatSend.setFocusPainted(false);
		chatSend.setBackground(new Color(219, 142, 27));
		chatSend.setFont(new Font("Tahoma", Font.BOLD, 12));
		chatSend.setBounds(1164, 500, 100, 75);
		add(chatSend);
		
		lblRemainingWordsAction = new JLabel("Action");
		lblRemainingWordsAction.setForeground(Color.white);
		lblRemainingWordsAction.setBounds(10, 585, 200, 14);
		add(lblRemainingWordsAction);
		
		lblRemainingWordsChat = new JLabel("Chat");
		lblRemainingWordsChat.setForeground(Color.white);
		lblRemainingWordsChat.setBounds(800, 585, 200, 14);
		add(lblRemainingWordsChat);
		
		lblEvent = new JLabel("Event Area");
		lblEvent.setForeground(Color.white);
		lblEvent.setBounds(10, 22, 200, 22);
		add(lblEvent);
		
		lblChat = new JLabel("Chat Area");
		lblChat.setForeground(Color.white);
		lblChat.setBounds(800, 22, 200, 22);
		add(lblChat);
		
		lblHP = new JLabel("HP: 1");
		lblHP.setForeground(Color.white);
		lblHP.setBounds(615, 56, 175, 14);
		lblHP.setToolTipText("Seems serious...");
		add(lblHP);
		
		lblMight = new JLabel("Might: 9001");
		lblMight.setForeground(Color.white);
		lblMight.setBounds(615, 76, 175, 14);
		lblMight.setToolTipText("A god among men!");
		add(lblMight);
		
		lblDex = new JLabel("Dex: Too much!");
		lblDex.setForeground(Color.white);
		lblDex.setBounds(615, 96, 175, 14);
		add(lblDex);
		
		lblWisdom = new JLabel("Wisdom: mom?");
		lblWisdom.setForeground(Color.white);
		lblWisdom.setBounds(615, 116, 175, 14);
		add(lblWisdom);
		
		lblConstitution = new JLabel("Constitution: 666");
		lblConstitution.setForeground(Color.white);
		lblConstitution.setBounds(615, 136, 175, 14);
		add(lblConstitution);
		
		lblCrit = new JLabel("Crit: 100%");
		lblCrit.setForeground(Color.white);
		lblCrit.setBounds(615, 156, 175, 14);
		add(lblCrit);
		
		lblAccuracy = new JLabel("Accuracy: 5%");
		lblAccuracy.setForeground(Color.white);
		lblAccuracy.setBounds(615, 176, 175, 14);
		add(lblAccuracy);
		
		lblEvasion = new JLabel("Evasion: 95%");
		lblEvasion.setForeground(Color.white);
		lblEvasion.setBounds(615, 196, 175, 14);
		add(lblEvasion);
		
		lblArmor = new JLabel("Armor: 0%");
		lblArmor.setForeground(Color.white);
		lblArmor.setBounds(615, 216, 175, 14);
		add(lblArmor);
		
		lblResist = new JLabel("Resist Element: 100%");
		lblResist.setForeground(Color.white);
		lblResist.setBounds(615, 236, 175, 14);
		add(lblResist);
		
		
		updateCount(1);
		updateCount(2);
	}
	private void updateCount(int i) {
		if(i == 1){
			lblRemainingWordsAction.setText((255 -docAction.getLength()) + " characters remaining");
		}else if(i == 2){
			lblRemainingWordsChat.setText((255 -docChat.getLength()) + " characters remaining");
		}
    }
	private String eventAreaInfo;
	public void updEventArea(String text){
		eventAreaInfo = eventArea.getText();
		eventAreaInfo += "\n" + text;
		eventArea.setText(eventAreaInfo);
	}
	private String chatRecAreaInfo;
	public void updChatArea(String text){
		chatRecAreaInfo = chatRecArea.getText();
		chatRecAreaInfo += "\n" + text;
		chatRecArea.setText(chatRecAreaInfo);
	}
	public void keyTyped(KeyEvent e) {}
	private String actionAreaTemp;
	private String chatTypAreaTemp;
	public void keyPressed(KeyEvent e) {
		//On ENTER pressed in actionArea, create temporary string without newline
		//and update actionArea without newline.
		if(e.getSource() == actionArea && e.getKeyCode() == KeyEvent.VK_ENTER){
			actionAreaTemp = actionArea.getText().replace("\n","");
			actionArea.setText(actionAreaTemp);
		}else if(e.getSource() == chatTypArea && e.getKeyCode() == KeyEvent.VK_ENTER){
			chatTypAreaTemp = chatTypArea.getText().replace("\n","");
			chatTypArea.setText(chatTypAreaTemp);
		}
	}
	public void keyReleased(KeyEvent e) {
		//On ENTER release from actionArea, update eventArea with temporary
		//text and reset actionArea.
		if(e.getSource() == actionArea && e.getKeyCode() == KeyEvent.VK_ENTER){
			//Message must be more than 0 character.
			if(actionAreaTemp.length() > 0){
				updEventArea("Player: " + actionAreaTemp);
			}
			actionArea.setText("");
		}else if(e.getSource() == chatTypArea && e.getKeyCode() == KeyEvent.VK_ENTER){
			if(chatTypAreaTemp.length() > 0){
				updChatArea("Player: " + chatTypAreaTemp);
			}
			chatTypArea.setText("");
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == actionSend){
			updEventArea(actionArea.getText());
		}else if(e.getSource() == chatTypArea || e.getSource() == chatSend){
			updChatArea(chatTypArea.getText());
		}
	}
}
class MyTextArea extends JTextArea {
    private Image img;
    public MyTextArea() {
        img = new ImageIcon(Cl_Main.class.getResource("loginBackground.png")).getImage();
    }
    protected void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
        super.paintComponent(g);
    }
}