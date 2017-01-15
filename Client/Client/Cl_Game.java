package Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultStyledDocument;

import components.DocumentSizeFilter;
import java.awt.Component;

public class Cl_Game extends JPanel implements ActionListener, KeyListener, ListSelectionListener {
	
	private JButton actionSend, chatSend;
	private JScrollBar sbEvent, sbChat, sbItem, sbList;
	private DefaultCaret eventCaret, chatCaret;
	private JScrollPane eventAreaScroll, chatAreaScroll, itemScroll, listScroll;
	public JLabel lblEvent, lblChat, lblRemainingWordsChat, lblRemainingWordsAction;
	public JLabel  lblHP, lblMight, lblDex, lblWisdom, lblConstitution, lblCrit, lblList;
	public JLabel lblAccuracy, lblEvasion, lblArmor, lblDmg, lblName, lblChar, lblClass;
	public JTextArea eventArea, actionArea, chatArea, chatTypArea, itemArea;
	public JList<String> itemList;
	public DefaultListModel<String> listModel;
	private DefaultStyledDocument docAction, docChat;
	Cl_Main main;
	
	public Cl_Game(Cl_Main main) {
		this.main = main;
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
		
		eventArea = new MyTextArea("Welcome Adventurer!");
		eventArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		eventArea.setForeground(Color.white);
		eventArea.setOpaque(false);
		eventArea.setToolTipText("Read me!");
		eventArea.setLineWrap(true);
		eventArea.setWrapStyleWord(true);
		eventArea.setEditable(false);
		eventCaret = (DefaultCaret)eventArea.getCaret();
		eventCaret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		
		eventAreaScroll = new JScrollPane(eventArea);
		eventAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		eventAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		eventAreaScroll.setOpaque(false);
		eventAreaScroll.getViewport().setOpaque(false);
		eventAreaScroll.setBounds(10, 50, 600, 440);
		sbEvent = eventAreaScroll.getVerticalScrollBar();
        sbEvent.setUI(new MyScrollbarUI());
		add(eventAreaScroll);
		
		chatArea = new MyTextArea("Welcome to the chat!");
		chatArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		chatArea.setForeground(Color.white);
		chatArea.setOpaque(false);
		chatArea.setToolTipText("Read me!");
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setEditable(false);
		chatCaret = (DefaultCaret)chatArea.getCaret();
		chatCaret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		
		chatAreaScroll = new JScrollPane(chatArea);
		chatAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatAreaScroll.setOpaque(false);
		chatAreaScroll.getViewport().setOpaque(false);
		chatAreaScroll.setBounds(800, 51, 465, 440);
		chatAreaScroll.setBackground(new Color(219, 142, 27));
		sbChat = chatAreaScroll.getVerticalScrollBar();
        sbChat.setUI(new MyScrollbarUI());
		add(chatAreaScroll);
		
		chatArea = new MyTextArea("Item descriptions");
		chatArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		chatArea.setForeground(Color.white);
		chatArea.setOpaque(false);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatArea.setEditable(false);
		
		itemArea = new MyTextArea("Item description area.");
		itemArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 11));
		itemArea.setForeground(Color.white);
		itemArea.setOpaque(false);
		itemArea.setLineWrap(true);
		itemArea.setWrapStyleWord(true);
		itemArea.setEditable(false);
		
		itemScroll = new JScrollPane(itemArea);
		itemScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		itemScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		itemScroll.setOpaque(false);
		itemScroll.getViewport().setOpaque(false);
		itemScroll.setBounds(620, 221, 170, 150);
		itemScroll.setBackground(new Color(219, 142, 27));
		sbItem = itemScroll.getVerticalScrollBar();
		sbItem.setUI(new MyScrollbarUI());
		add(itemScroll);
		
		listModel = new DefaultListModel<String>();
		
		itemList = new JList(listModel);
		itemList.setForeground(Color.white);
		itemList.setBackground(Color.darkGray);
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemList.setVisibleRowCount(-1);
		itemList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		itemList.addListSelectionListener(this);
		itemList.addKeyListener(this);
		
		listScroll = new JScrollPane(itemList);
		listScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		listScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listScroll.setOpaque(false);
		listScroll.getViewport().setOpaque(false);
		listScroll.setBounds(620, 395, 170, 180);
		listScroll.setBackground(new Color(219, 142, 27));
		sbList = listScroll.getVerticalScrollBar();
		sbList.setUI(new MyScrollbarUI());
		add(listScroll);

		actionArea = new MyTextArea("");
		actionArea.setForeground(Color.white);
		actionArea.setOpaque(false);
		actionArea.setLineWrap(true);
		actionArea.setWrapStyleWord(true);
		actionArea.setToolTipText("Type me!");
		actionArea.addKeyListener(this);
		actionArea.setBounds(10, 500, 500, 75);
		actionArea.setDocument(docAction);
		add(actionArea);
		
		chatTypArea = new MyTextArea("");
		chatTypArea.setForeground(Color.white);
		chatTypArea.setOpaque(false);
		chatTypArea.setLineWrap(true);
		chatTypArea.setWrapStyleWord(true);
		chatTypArea.setToolTipText("Type me!");
		chatTypArea.addKeyListener(this);
		chatTypArea.setBounds(800, 500, 365, 75);
		chatTypArea.setDocument(docChat);
		add(chatTypArea);
		
		// Buttons.
		actionSend = new JButton("Send");
		actionSend.setForeground(Color.white);
		actionSend.setFocusPainted(false);
		actionSend.addActionListener(this);
		actionSend.setBackground(new Color(219, 142, 27));
		actionSend.setFont(new Font("Tahoma", Font.BOLD, 12));
		actionSend.setBounds(510, 500, 100, 75);
		add(actionSend);
		
		chatSend = new JButton("Send");
		chatSend.setForeground(Color.white);
		chatSend.setFocusPainted(false);
		chatSend.addActionListener(this);
		chatSend.setBackground(new Color(219, 142, 27));
		chatSend.setFont(new Font("Tahoma", Font.BOLD, 12));
		chatSend.setBounds(1164, 500, 100, 75);
		add(chatSend);
		
		// label counters.
		lblRemainingWordsAction = new JLabel("Action");
		lblRemainingWordsAction.setForeground(Color.white);
		lblRemainingWordsAction.setBounds(10, 585, 200, 14);
		add(lblRemainingWordsAction);
		
		lblRemainingWordsChat = new JLabel("Chat");
		lblRemainingWordsChat.setForeground(Color.white);
		lblRemainingWordsChat.setBounds(800, 585, 200, 14);
		add(lblRemainingWordsChat);
		
		// Middle Area Labels.
		lblName = new JLabel("User: ?");
		lblName.setForeground(Color.white);
		lblName.setBounds(615, 56, 175, 14);
		add(lblName);
		
		lblChar = new JLabel("Character: ?");
		lblChar.setForeground(Color.white);
		lblChar.setBounds(615, 76, 175, 14);
		add(lblChar);
		
		lblClass = new JLabel("Class: ?");
		lblClass.setForeground(Color.white);
		lblClass.setBounds(615, 96, 175, 14);
		add(lblClass);
		
		lblHP = new JLabel("HP: ?");
		lblHP.setForeground(Color.white);
		lblHP.setBounds(615, 116, 85, 14);
		add(lblHP);
		
		lblDmg = new JLabel("Dmg: ?");
		lblDmg.setForeground(Color.white);
		lblDmg.setBounds(705, 116, 85, 14);
		add(lblDmg);
		
		lblMight = new JLabel("Might: ?");
		lblMight.setForeground(Color.white);
		lblMight.setBounds(615, 136, 85, 14);
		add(lblMight);
		
		lblDex = new JLabel("Dex: ?");
		lblDex.setForeground(Color.white);
		lblDex.setBounds(705, 136, 85, 14);
		add(lblDex);
		
		lblWisdom = new JLabel("Wisdom: ?");
		lblWisdom.setForeground(Color.white);
		lblWisdom.setBounds(615, 156, 85, 14);
		add(lblWisdom);
		
		lblConstitution = new JLabel("Con: ?");
		lblConstitution.setForeground(Color.white);
		lblConstitution.setBounds(705, 156, 85, 14);
		add(lblConstitution);
		
		lblCrit = new JLabel("Crit: ?");
		lblCrit.setForeground(Color.white);
		lblCrit.setBounds(615, 176, 85, 14);
		add(lblCrit);
		
		lblAccuracy = new JLabel("Acc: ?");
		lblAccuracy.setForeground(Color.white);
		lblAccuracy.setBounds(705, 176, 85, 14);
		add(lblAccuracy);
		
		lblEvasion = new JLabel("Evasion: ?");
		lblEvasion.setForeground(Color.white);
		lblEvasion.setBounds(615, 196, 85, 14);
		add(lblEvasion);
		
		lblArmor = new JLabel("Armor: ?");
		lblArmor.setForeground(Color.white);
		lblArmor.setBounds(705, 196, 85, 14);
		add(lblArmor);
		
		lblList = new JLabel("Press ENTER to equip item");
		lblList.setForeground(Color.WHITE);
		lblList.setBounds(620, 376, 170, 14);
		add(lblList);
		
		// Update remainingWords labels.
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
		if(actionAreaTemp != null){
			if(e.getSource() == actionArea && e.getKeyCode() == KeyEvent.VK_ENTER){
				//Message must be more than 0 character.
				if(actionAreaTemp.length() > 0){
					main.updEventArea(main.userName + ": " + actionAreaTemp);
					main.transmit.putToQueue(":ACT:" + actionAreaTemp);
				}
				actionArea.setText("");
			}else if(e.getSource() == chatTypArea && e.getKeyCode() == KeyEvent.VK_ENTER){
				if(chatTypAreaTemp.length() > 0){
					main.updChatArea(main.userName + ": " + chatTypAreaTemp);
					main.transmit.putToQueue(":CHA:" + main.userName + ": " + chatTypArea.getText());
				}
				chatTypArea.setText("");
			}
		}
		if(e.getSource() == itemList && e.getKeyCode() == KeyEvent.VK_ENTER){
			//main.transmit.putToQueue(":INV:" + itemList.getSelectedIndex());
			System.out.println(":INV:" + itemList.getSelectedIndex());
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == actionSend){
			main.updEventArea(main.userName + ": " + actionArea.getText());
			main.transmit.putToQueue(":ACT:" + actionArea.getText());
			actionArea.setText("");
		}else if(e.getSource() == chatTypArea || e.getSource() == chatSend){
			main.updChatArea(main.userName + ": " + chatTypArea.getText());
			main.transmit.putToQueue(":CHA:" + main.userName + ": " + chatTypArea.getText());
			chatTypArea.setText("");
		}
	}
	public void valueChanged(ListSelectionEvent arg0) {
		if(main.descArray != null){
			itemArea.setText(main.descArray[itemList.getSelectedIndex()].replace("^", "\n"));
		}
	}
}
//Modified JTextarea that adds a background.
class MyTextArea extends JTextArea {
    private Image img;
    public MyTextArea(String text) {
    	super(text);
        img = new ImageIcon(Cl_Main.class.getResource("TransparentBlack.png")).getImage();
    }
    protected void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
        super.paintComponent(g);
    }
}
class MyScrollbarUI extends MetalScrollBarUI {
    private Image imageThumb, imageTrack;
    private JButton b = new JButton() {
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(0, 0);
        }
    };
    MyScrollbarUI() {
        imageThumb = FauxImage.create(32, 32, new Color(219, 142, 27));
        imageTrack = FauxImage.create(32, 32, Color.darkGray);
    }
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        g.setColor(Color.blue);
        ((Graphics2D) g).drawImage(imageThumb,
            r.x, r.y, r.width, r.height, null);
    }
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        ((Graphics2D) g).drawImage(imageTrack,
            r.x, r.y, r.width, r.height, null);
    }
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return b;
    }
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return b;
    }
}
class FauxImage {
    static public Image create(int w, int h, Color c) {
        BufferedImage bi = new BufferedImage(
            w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.setPaint(c);
        g2d.fillRect(0, 0, w, h);
        g2d.dispose();
        return bi;
    }
}