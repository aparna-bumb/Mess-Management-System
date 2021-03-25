package Admin;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddMenu implements ActionListener{
	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private JButton backButton;
	private String name;
	private String mess;
	private Component b1Label;
	private JLabel b2Label;
	private JLabel d1Label;
	private JLabel d2Label;
	private JLabel l1Label;
	private Component l2Label;
	private JLabel l3Label;
	private JLabel l4Label;
	private JLabel dateLabel;
	private JLabel d3Label;
	private Component date1Label;
	private JTextField b1Txt;
	private JTextField b2Txt;
	private JTextField d1Txt;
	private JTextField d2Txt;
	private JTextField d3Txt;
	private JTextField l1Txt;
	private JTextField l2Txt;
	private JTextField l3Txt;
	private JTextField l4Txt;
	private JTextField pnameTxt;
	private JButton submitButton;
	private JButton updateButton;
	private Component lLabel;

	public AddMenu(String n, String m) {
		// TODO Auto-generated constructor stub
		name = n;
		mess = m;
	}


	public void addMenuUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Add Menu - Admin");
		
		//background
		background = new JLabel();
		background.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\hp\\Desktop\\mini proj\\iamges\\bg1.jpg").getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT)));
		//background.setIcon(new ImageIcon("C:\\Users\\hp\\Desktop\\mini proj\\iamges\\bg1.jpg"));
		Dimension size1 = background.getPreferredSize();
		background.setBounds(0, 0, size1.width, size1.height);
		
		//title
		titleLabel = new JLabel("Mess Management System");
		titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		titleLabel.setFont (titleLabel.getFont ().deriveFont (40.0f));
		Dimension sizeTitle = titleLabel.getPreferredSize();
		titleLabel.setBounds(300, 80, 700, sizeTitle.height);
		
		//logo
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("C:\\Users\\hp\\Desktop\\mini proj\\iamges\\logosmall.png"));
		Dimension size = logo.getPreferredSize();
		logo.setBounds(130, 50, size.width, size.height);
		
		//Back Button
		backButton = new JButton("Back");
		backButton.setBounds(870, 630, 100, 40);
		backButton.addActionListener(this);
		
		//labels
		dateLabel = new JLabel("Date: ");
		dateLabel.setFont (dateLabel.getFont().deriveFont (15.0f));
		dateLabel.setBounds(100, 200, 250, 50);
		Date date = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		formatter = new SimpleDateFormat("dd MMMM yyyy");  
		strDate = formatter.format(date);
		date1Label = new JLabel(strDate);
		date1Label.setFont (date1Label.getFont().deriveFont (15.0f));
		date1Label.setBounds(290, 200, 250, 50);
		b1Label = new JLabel("Breakfast item1: ");
		b1Label.setFont (b1Label.getFont().deriveFont (15.0f));
		b1Label.setBounds(100, 250, 250, 50);
		b2Label = new JLabel("Breakfast item2: ");
		b2Label.setFont (b2Label.getFont().deriveFont (15.0f));
		b2Label.setBounds(100, 310, 250, 50);
		d1Label = new JLabel("Dinner Item1: ");
		d1Label.setFont (d1Label.getFont().deriveFont (15.0f));
		d1Label.setBounds(100, 370, 250, 50);
		d2Label = new JLabel("Dinner Item2: ");
		d2Label.setFont (d2Label.getFont().deriveFont (15.0f));
		d2Label.setBounds(100, 430, 250, 50);
		d3Label = new JLabel("Dinner Item3: ");
		d3Label.setFont (d3Label.getFont().deriveFont (15.0f));
		d3Label.setBounds(100, 490, 250, 50);
		lLabel = new JLabel("Lunch: ");
		lLabel.setFont (lLabel.getFont().deriveFont (15.0f));
		lLabel.setBounds(570, 250, 250, 50);
		l1Label = new JLabel("Lunch - Roti: ");
		l1Label.setFont (l1Label.getFont().deriveFont (15.0f));
		l1Label.setBounds(570, 310, 250, 50);
		l2Label = new JLabel("Lunch - Sabji: ");
		l2Label.setFont (l2Label.getFont().deriveFont (15.0f));
		l2Label.setBounds(570, 370, 250, 50);
		l3Label = new JLabel("Lunch - Daal: ");
		l3Label.setFont (l3Label.getFont().deriveFont (15.0f));
		l3Label.setBounds(570, 430, 250, 50);
		l4Label = new JLabel("Extras: ");
		l4Label.setFont (l4Label.getFont().deriveFont (15.0f));
		l4Label.setBounds(570, 490, 250, 50);
		
		//textfields
		b1Txt = new JTextField();
		b1Txt.setBounds(270, 265, 180, 26);
		b2Txt = new JTextField();
		b2Txt.setBounds(270, 325, 180, 26);
		d1Txt = new JTextField();
		d1Txt.setBounds(270, 385, 180, 26);
		d2Txt = new JTextField();
		d2Txt.setBounds(270, 445, 180, 26);
		d3Txt = new JTextField();
		d3Txt.setBounds(270, 505, 180, 26);
		l1Txt = new JTextField();
		l1Txt.setBounds(730, 325, 180, 26);
		l2Txt = new JTextField();
		l2Txt.setBounds(730, 385, 180, 26);
		l3Txt = new JTextField();
		l3Txt.setBounds(730, 445, 180, 26);
		l4Txt = new JTextField();
		l4Txt.setBounds(730, 505, 180, 26);
		/*pnameTxt = new JTextField();
		pnameTxt.setBounds(500, 210, 200, 30);*/
		
		//submit and update buttons
		submitButton = new JButton("Submit");
		submitButton.setBounds(380, 555, 100, 40);
		submitButton.addActionListener(this);
		updateButton = new JButton("Update");
		updateButton.setBounds(540, 555, 100, 40);
		updateButton.addActionListener(this);
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(backButton);
		background.add(lLabel);
		background.add(l1Label);
		background.add(l2Label);
		background.add(l3Label);
		background.add(l4Label);
		background.add(b1Label);
		background.add(b2Label);
		background.add(d1Label);
		background.add(d2Label);
		background.add(d3Label);
		background.add(dateLabel);
		background.add(date1Label);
		background.add(b1Txt);
		background.add(b2Txt);
		background.add(d1Txt);
		background.add(d2Txt);
		background.add(d3Txt);
		background.add(l1Txt);
		background.add(l2Txt);
		background.add(l3Txt);
		background.add(l4Txt);
		background.add(submitButton);
		background.add(updateButton);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==backButton)
		{
			frame.dispose();
			AdminInitialWindow aiw = new AdminInitialWindow(name, mess);
			aiw.initialWindowUI();
		}
		if(e.getSource()==submitButton)
		{
			LocalDate da = java.time.LocalDate.now();
			String d1 = da.toString();
			String query = "insert into menu values('"+mess+"', '"+name+"', '"+d1+"', '"+b1Txt.getText()+"', '"+
					b2Txt.getText()+"', '"+d1Txt.getText()+"', '"+d2Txt.getText()+"', '"+d3Txt.getText()+"', '"+
					l1Txt.getText()+"', '"+l2Txt.getText()+"', '"+l3Txt.getText()+"', '"+l4Txt.getText()+"');";
			//System.out.println(query);
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
	
			      //System.out.println("Database Connected");
			      Statement stmt=con.createStatement();
			      stmt.executeUpdate(query);
			    }
		    catch (Exception e1) 
		      {
		    	e1.printStackTrace();
		      }
		}
		if(e.getSource()==updateButton)
		{
			LocalDate da = java.time.LocalDate.now();
			String d1 = da.toString();
			String query = "update menu set mess_name='"+mess+"', admin_name='"+name+"', breakfast1='"+b1Txt.getText()+"', breakfast2='"+
					b2Txt.getText()+"', roti='"+l1Txt.getText()+"', sabji='"+l2Txt.getText()+"', daal='"+l3Txt.getText()+"', chawal='"+
					l4Txt.getText()+"', dinner1='"+d1Txt.getText()+"', dinner2='"+d2Txt.getText()+"', dinner3='"+d3Txt.getText()+"' where date_='"+d1+"';";
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
	
			      //System.out.println("Database Connected");
			      Statement stmt=con.createStatement();
			      stmt.executeUpdate(query);
			    }
		    catch (Exception e1) 
		      {
		    	e1.printStackTrace();
		      }
		}
	}
}
