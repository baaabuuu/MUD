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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultStyledDocument;

import components.DocumentSizeFilter;

public class Cl_Game extends JPanel implements ActionListener, KeyListener{
	
	private JButton actionSend, chatSend;
	private DefaultCaret eventCaret, chatCaret;
	public JLabel lblEvent, lblChat, lblRemainingWordsChat, lblRemainingWordsAction;
	public JLabel  lblHP, lblMight, lblDex, lblWisdom, lblConstitution, lblCrit;
	public JLabel lblAccuracy, lblEvasion, lblArmor, lblResist, lblName;
	public JTextArea eventArea, actionArea, chatArea, chatTypArea;
	public JList itemList;
	private DefaultStyledDocument docAction, docChat;
	Cl_Main parent;
	
	public Cl_Game(Cl_Main parent) {
		this.parent = parent;
		//setBorder(new EmptyBorder(5, 5, 5, 5));
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
		eventArea.setEditable(false);
		eventCaret = (DefaultCaret)eventArea.getCaret();
		eventCaret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		
		
		JScrollPane eventAreaScroll = new JScrollPane(eventArea);
		eventAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		eventAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		eventAreaScroll.setOpaque(false);
		eventAreaScroll.getViewport().setOpaque(false);
		eventAreaScroll.setBounds(10, 50, 600, 440);
		JScrollBar sbEvent = eventAreaScroll.getVerticalScrollBar();
        sbEvent.setUI(new MyScrollbarUI());
		add(eventAreaScroll);
		
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
		
		chatArea = new MyTextArea("Welcome to the chat!");
		chatArea.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		chatArea.setForeground(Color.white);
		chatArea.setOpaque(false);
		chatArea.setToolTipText("Read me!");
		chatArea.setLineWrap(true);
		chatArea.setEditable(false);
		chatCaret = (DefaultCaret)chatArea.getCaret();
		chatCaret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		
		JScrollPane chatAreaScroll = new JScrollPane(chatArea);
		chatAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatAreaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatAreaScroll.setOpaque(false);
		chatAreaScroll.getViewport().setOpaque(false);
		chatAreaScroll.setBounds(800, 51, 465, 440);
		chatAreaScroll.setBackground(new Color(219, 142, 27));
		JScrollBar sbChat = chatAreaScroll.getVerticalScrollBar();
        sbChat.setUI(new MyScrollbarUI());
		add(chatAreaScroll);
		
		chatTypArea = new MyTextArea("");
		chatTypArea.setForeground(Color.white);
		chatTypArea.setOpaque(false);
		chatTypArea.setLineWrap(true);
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
		
		// List and label counters.
		itemList = new MyList();
		itemList.setBounds(620, 281, 170, 209);
		itemList.setBackground(new Color(1,1,1, (float) 0.01));
		add(itemList);
		
		lblRemainingWordsAction = new JLabel("Action");
		lblRemainingWordsAction.setForeground(Color.white);
		lblRemainingWordsAction.setBounds(10, 585, 200, 14);
		add(lblRemainingWordsAction);
		
		lblRemainingWordsChat = new JLabel("Chat");
		lblRemainingWordsChat.setForeground(Color.white);
		lblRemainingWordsChat.setBounds(800, 585, 200, 14);
		add(lblRemainingWordsChat);
		
		// Middle Area Labels.
		lblName = new JLabel("User: GhandiErGUD!");
		lblName.setForeground(Color.white);
		lblName.setBounds(615, 56, 175, 14);
		add(lblName);
		
		lblHP = new JLabel("HP: 1");
		lblHP.setForeground(Color.white);
		lblHP.setBounds(615, 76, 175, 14);
		add(lblHP);
		
		lblMight = new JLabel("Might: 9001");
		lblMight.setForeground(Color.white);
		lblMight.setBounds(615, 96, 175, 14);
		add(lblMight);
		
		lblDex = new JLabel("Dex: Too much!");
		lblDex.setForeground(Color.white);
		lblDex.setBounds(615, 116, 175, 14);
		add(lblDex);
		
		lblWisdom = new JLabel("Wisdom: mom?");
		lblWisdom.setForeground(Color.white);
		lblWisdom.setBounds(615, 136, 175, 14);
		add(lblWisdom);
		
		lblConstitution = new JLabel("Constitution: 666");
		lblConstitution.setForeground(Color.white);
		lblConstitution.setBounds(615, 156, 175, 14);
		add(lblConstitution);
		
		lblCrit = new JLabel("Crit: 100%");
		lblCrit.setForeground(Color.white);
		lblCrit.setBounds(615, 176, 175, 14);
		add(lblCrit);
		
		lblAccuracy = new JLabel("Accuracy: 5%");
		lblAccuracy.setForeground(Color.white);
		lblAccuracy.setBounds(615, 196, 175, 14);
		add(lblAccuracy);
		
		lblEvasion = new JLabel("Evasion: 95%");
		lblEvasion.setForeground(Color.white);
		lblEvasion.setBounds(615, 216, 175, 14);
		add(lblEvasion);
		
		lblArmor = new JLabel("Armor: 0%");
		lblArmor.setForeground(Color.white);
		lblArmor.setBounds(615, 236, 175, 14);
		add(lblArmor);
		
		lblResist = new JLabel("Resist Element: 100%");
		lblResist.setForeground(Color.white);
		lblResist.setBounds(615, 256, 175, 14);
		add(lblResist);
		
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
		if(e.getSource() == actionArea && e.getKeyCode() == KeyEvent.VK_ENTER){
			//Message must be more than 0 character.
			if(actionAreaTemp.length() > 0){
				parent.updEventArea(parent.userName + ": " + actionAreaTemp);
				parent.transmit.putToQueue(":ACT:" + actionAreaTemp);
			}
			actionArea.setText("");
		}else if(e.getSource() == chatTypArea && e.getKeyCode() == KeyEvent.VK_ENTER){
			if(chatTypAreaTemp.length() > 0){
				parent.updChatArea(parent.userName + ": " + chatTypAreaTemp);
			}
			chatTypArea.setText("");
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == actionSend){
			parent.updEventArea("Player: " + actionArea.getText());
			parent.transmit.putToQueue(":ACT:" + actionArea.getText());
			actionArea.setText("");
		}else if(e.getSource() == chatTypArea || e.getSource() == chatSend){
			parent.updChatArea("Player: " + chatTypArea.getText());
			chatTypArea.setText("");
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
//Modified JList that adds a background.
class MyList extends JList {
  private Image img;
  public MyList() {
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