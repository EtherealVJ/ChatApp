package application;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.io.*;
import javax.swing.border.*;
import java.util.Calendar;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
/**
 * 
 * @author MISHRA BIPINKUMAR BIMALKUMAR - 312026	
 *  	   CHOWDHARY VRAJ PARESHKUMAR   - 312010
 *  	   GAMIT CHINTAN VASANTBHAI     - 312013	
 *  	   TITA OM DIVYESHBHAI          - 312065
 *
 */
public class Server  implements ActionListener{
	
	JPanel p1;
    JTextField t;
    JButton b1;
    static JPanel a1;
    
    static JFrame f1 = new JFrame();
    
    static Box vertical = Box.createVerticalBox();
    
    static ServerSocket skt;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    
    Boolean typing;
    
	Server(){
	   f1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	   p1 = new JPanel();
	   p1.setLayout(null);
	   p1.setBackground(new Color(7,94,84));
	   p1.setBounds(0,0,450,70);
	   f1.add(p1);
	   
	   ImageIcon i1 = createImageIcon("/application/icons/3.png","arrow left");
	   Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	   ImageIcon i3 = new ImageIcon(i2);
	   JLabel l1 = new JLabel(i3);
	   l1.setBounds(5,17,30,30);
	   p1.add(l1);
	   
	   l1.addMouseListener(new MouseAdapter(){
		   public void mouseClicked(MouseEvent me) {
			   System.exit(0);
		   }
	   });
	   
	   ImageIcon i4 = createImageIcon("/application/icons/1.png","Gaitonde Bhau");
	   Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
	   ImageIcon i6 = new ImageIcon(i5);
	   JLabel l2 = new JLabel(i6);
	   l2.setBounds(40,5,60,60);
	   p1.add(l2);
	   
	   JLabel l3 = new JLabel("Ali");
	   l3.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
	   l3.setForeground(Color.white);
	   l3.setBounds(110,20,140,20);
	   p1.add(l3);
  
       JLabel l4 = new JLabel("Active Now");
       l4.setFont(new Font("SAN_SERIF",Font.BOLD,14));
       l4.setForeground(Color.white);
       l4.setBounds(110,40,140,20);
       p1.add(l4);
       
       Timer T = new Timer(1, new ActionListener() {
    	     public void actionPerformed(ActionEvent ae) {
                 if(!typing) {
                	 l4.setText("Active Now");
                 }
    	     }
       });
       
       T.setInitialDelay(2000);
       
       ImageIcon i7 = createImageIcon("/application/icons/video.png","video icon");
	   Image i8 = i7.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
	   ImageIcon i9 = new ImageIcon(i8);
	   JLabel l5 = new JLabel(i9);
	   l5.setBounds(310,18,35,35);
	   p1.add(l5);
       
	   
	   ImageIcon i10 = createImageIcon("/application/icons/phone.png","phone icon");
	   Image i11 = i10.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
	   ImageIcon i12 = new ImageIcon(i11);
	   JLabel l6 = new JLabel(i12);
	   l6.setBounds(370,20,35,35);
	   p1.add(l6);
	   
	   
	   ImageIcon i13 = createImageIcon("/application/icons/3icon.png","3 dot icons");
	   Image i14 = i13.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
	   ImageIcon i15 = new ImageIcon(i14);
	   JLabel l7 = new JLabel(i15);
	   l7.setBounds(420,22,13,25);
	   p1.add(l7);
	   
	   //main panel
	   a1 = new JPanel();
	   //a1.setBounds(5,73,440,538);
	   //a1.setBackground(Color.blue);
	   a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
	   //f1.add(a1);
	   
	   
	   //to add scrollbar in a1
	   JScrollPane sp = new JScrollPane(a1);
	   sp.setBounds(5,73,440,538);
	   //to add empty border 
	   sp.setBorder(BorderFactory.createEmptyBorder());
	   sp.getVerticalScrollBar().setBackground(Color.GREEN);
	   
	   
	   //using plaf : pluggable look and feel! ScrollBarUI's subclass is BasicScrollBarUI
	   ScrollBarUI ui = new BasicScrollBarUI() {
		   //decrease Button is protected so it should be either public or protected but not default
		   
		    protected JButton createDecreaseButton(int orientation) {
		    	JButton button = super.createDecreaseButton(orientation);
		    	button.setBackground(new Color(7,94,84));
		    	button.setForeground(Color.white);
		    	this.thumbColor = new Color(7,94,84);
		    	return button;
		    }
		    
		    protected JButton createIncreaseButton(int orientation) {
		    	JButton button = super.createIncreaseButton(orientation);
		    	button.setBackground(new Color(7,94,84));
		    	button.setForeground(Color.white);
		    	this.thumbColor = new Color(7,94,84);
		    	return button;
		    }
	   };
	   
	   sp.getVerticalScrollBar().setUI(ui);
	   f1.add(sp);
	   
	   
	   b1 = new JButton("send");
	   b1.addActionListener(this);
	   b1.setBounds(360,619,88,40);
	   b1.setBackground(new Color(7,94,84));
	   b1.setForeground(Color.white);
	   b1.setFont(new Font("SANS_SERIF",Font.BOLD,16));
	   f1.add(b1);
	   
	   t = new JTextField();
	   t.setBounds(5,620,355,40);
	   t.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
	   f1.add(t);
	   
	   t.addKeyListener(new KeyAdapter() {
		   public void keyPressed(KeyEvent ke) {
			     l4.setText("typing...");
			     
			     T.stop();
			     
			     typing = true;
		   }
		   
		   public void keyReleased(KeyEvent ke) {
			   typing = false;
			   
			   if(!T.isRunning()) {
				   T.start();
			   }
		   }
	   });
	   
	   f1.getContentPane().setBackground(Color.white);
	   f1.setLayout(null);
	   f1.setSize(450,700);
	   f1.setLocation(300,150);
       //setUndecorated(true);
       f1.setVisible(true);
       
 }
	
	
   // Returns an ImageIcon, or null if the path was invalid. 
   private static ImageIcon createImageIcon(String path,
      String description) {
      java.net.URL imgURL = Server.class.getResource(path);
      
      if (imgURL != null) {
         return new ImageIcon(imgURL, description);
      } else {            
         System.err.println("Couldn't find file: " + path);
         return null;
      }
   }
   
   public static void main(String[] args) {
       new Server().f1.setVisible(true);
       String msgInput = "";
	   try {
        //'6001' is port number
    	skt = new ServerSocket(6001);
    	
    	while(true) {
    	   s = skt.accept();
           //in and out from server.
    	   din = new DataInputStream(s.getInputStream());
    	   dout = new DataOutputStream(s.getOutputStream());
    	   
    	   while(true){
    	     
    		 msgInput = din.readUTF();
    	     JPanel p2 = formatLabel(msgInput);
    	   
    	     JPanel left = new JPanel(new BorderLayout());
    	     left.add(p2,BorderLayout.LINE_START);
    	     vertical.add(left);
    	     vertical.add(Box.createVerticalStrut(15));
    	     a1.add(vertical,BorderLayout.PAGE_START);
    	     f1.validate();//act like repaint in awt.
        	}
    	}
 	   }catch(Exception e) {
    	 
    	   System.out.println(e);

       }
       
   }

@Override
   public void actionPerformed(ActionEvent arg0) {
      try {
       String out = t.getText();
       sendTextToFile(out);
       JPanel p2 =  formatLabel(out);
       
       //p2.setLayout(new BorderLayout());
       
       a1.setLayout(new BorderLayout());
       
       JPanel right = new JPanel(new BorderLayout());
       
       right.add(p2,BorderLayout.LINE_END);
       //to vertically expand and create vertical box so message will be vertically aligned.
       vertical.add(right);
       //to create vertical strut.
       vertical.add(Box.createVerticalStrut(15));
       
       a1.add(vertical,BorderLayout.PAGE_START);
       dout.writeUTF(out);  
       t.setText("");
       
      }catch(Exception e) {
    	  System.out.println(e);
      }
   }

    public static JPanel formatLabel(String out) {
          //we will add panel(of message) to the Panel.
    	   JPanel p3 = new JPanel();
           p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));//along Y-axis 
           
           JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
           l1.setFont(new Font("Tahoma",Font.PLAIN,16));
           l1.setBackground(new Color(37,211,102));
           l1.setOpaque(true);//to make opaque
           l1.setBorder(new EmptyBorder(15,15,15,50));//to add chat bubble
           
           Calendar cal = Calendar.getInstance();
           SimpleDateFormat SF = new SimpleDateFormat("HH:mm");
           
           JLabel l2 = new JLabel();
           l2.setText(SF.format(cal.getTime()));
           
           p3.add(l1);
           p3.add(l2);
                   
           return p3;
    }
      //chat backups
       public void sendTextToFile(String out) {
    	     FileWriter f = null;
    	     try {
    	    	 f = new FileWriter("chat.txt");
    	    	 PrintWriter p = new PrintWriter(new BufferedWriter(f));
    	    	 p.println("Server : "+out);//it will append message in file in new line.
    	    	 
    	    }catch(FileNotFoundException e){
                
    	    	System.out.println(e.toString());
    	    
    	    }catch(Exception e) {
    	    	System.out.println(e.toString());
    	    }finally {
    	       if(f!=null){
    	    	 try {
    	           f.close();
    	    	 }catch(IOException e) {
    	    		System.out.println(e);
    	    	 }
    	       }else {
    	        System.out.println("File Not Opened!");
    	       }
    	    }
    }

}
