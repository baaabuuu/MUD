package Client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

import components.DocumentSizeFilter;
import java.awt.Label;
import java.awt.Color;
import java.awt.Toolkit;

public class Cl_Main extends JFrame implements ActionListener, KeyListener{

	private JPanel contentPane;
	public JButton actionSend, chatSend;
	public JLabel lblMainTitle, lblPlayerHealth, lblChat, lblTemp1, lblTemp2, lblRemainingWordsChat, lblRemainingWordsAction;
	public JTextArea eventArea, actionArea, chatRecArea, chatTypArea;
	private DefaultStyledDocument docAction, docChat;
	private Label label_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cl_Main frame = new Cl_Main();
					frame.setVisible(true);
					frame.setSize(1280,800);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Cl_Main() {
		setTitle("The Game!");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Christian\\git\\MUD\\Client\\Client\\IconImage.png"));
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		eventArea.setToolTipText("Read me!");
		eventArea.setLineWrap(true);
		eventArea.setEditable(false);
		
		JScrollPane eventAreaScroll = new JScrollPane(eventArea);
		eventAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		eventAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		eventAreaScroll.setBounds(10, 50, 600, 440);
		contentPane.add(eventAreaScroll);
		
		actionArea = new JTextArea();
		actionArea.setLineWrap(true);
		actionArea.setWrapStyleWord(true);
		actionArea.setToolTipText("Type me!");
		actionArea.addKeyListener(this);
		actionArea.setBounds(10, 500, 500, 75);
		actionArea.setDocument(docAction);
		contentPane.add(actionArea);
		
		
		chatRecArea = new JTextArea("Welcome to the chat!");
		chatRecArea.setToolTipText("Read me!");
		chatRecArea.setLineWrap(true);
		chatRecArea.setEditable(false);
		
		JScrollPane chatRecieveAreaScroll = new JScrollPane(chatRecArea);
		chatRecieveAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatRecieveAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatRecieveAreaScroll.setBounds(800, 51, 465, 440);
		contentPane.add(chatRecieveAreaScroll);
		
		chatTypArea = new JTextArea();
		chatTypArea.setLineWrap(true);
		chatTypArea.setToolTipText("Type me!");
		chatTypArea.addKeyListener(this);
		chatTypArea.setBounds(800, 500, 365, 75);
		chatTypArea.setDocument(docChat);
		contentPane.add(chatTypArea);
		
		
		actionSend = new JButton("Send");
		actionSend.setFont(new Font("Tahoma", Font.PLAIN, 11));
		actionSend.setBounds(510, 500, 100, 75);
		contentPane.add(actionSend);
		
		chatSend = new JButton("Send");
		chatSend.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chatSend.setBounds(1164, 500, 100, 75);
		contentPane.add(chatSend);
		
		lblRemainingWordsAction = new JLabel("Action");
		lblRemainingWordsAction.setBounds(10, 585, 200, 14);
		contentPane.add(lblRemainingWordsAction);
		
		lblRemainingWordsChat = new JLabel("Chat");
		lblRemainingWordsChat.setBounds(800, 585, 200, 14);
		contentPane.add(lblRemainingWordsChat);
		
		lblPlayerHealth = new JLabel("lblPlayerHealth");
		lblPlayerHealth.setBounds(615, 56, 175, 14);
		contentPane.add(lblPlayerHealth);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(615, 81, 175, 14);
		contentPane.add(lblNewLabel_3);
		
		Label label = new Label("Event Area");
		label.setBounds(10, 22, 200, 22);
		contentPane.add(label);
		
		label_1 = new Label("Chat Area");
		label_1.setBounds(800, 22, 200, 22);
		contentPane.add(label_1);
		
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
			updEventArea("Player: " + actionAreaTemp);
			actionArea.setText("");
		}else if(e.getSource() == chatTypArea && e.getKeyCode() == KeyEvent.VK_ENTER){
			updChatArea("" + chatTypAreaTemp);
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
