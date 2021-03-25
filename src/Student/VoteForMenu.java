package Student;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VoteForMenu implements ActionListener{

	private String name;
	private String mess;
	private JButton backButton;
	private JLabel titleLabel;
	private JLabel background;
	private JFrame frame;
	private JLabel dateLabel;
	private JLabel i1Label;
	private Component i2Label;
	private JLabel dTxt;
	private JButton vote1Button;
	private JButton vote2Button;
	private Component item1Label;
	private Component item2Label;
	private int pollid;
	public VoteForMenu(String n, String m) {
		// TODO Auto-generated constructor stub
		name = n;
		mess = m;
	}
	public void voteUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Vote for Menu");
		
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
		
		dateLabel = new JLabel("Poll is created for date: ");
		dateLabel.setFont (dateLabel.getFont().deriveFont (15.0f));
		dateLabel.setBounds(300, 250, 250, 50);
		
		i1Label = new JLabel("Menu 1: ");
		i1Label.setFont (i1Label.getFont().deriveFont (15.0f));
		i1Label.setBounds(300, 300, 250, 50);
		i2Label = new JLabel("Menu 2: ");
		i2Label.setFont (i2Label.getFont().deriveFont (15.0f));
		i2Label.setBounds(600, 300, 250, 50);
		
		getMenu();
		
		
		vote1Button = new JButton("Vote for Menu 1");
		vote1Button.setBounds(300, 410, 150, 40);
		vote1Button.addActionListener(this);
		
		vote2Button = new JButton("Vote for Menu 2");
		vote2Button.setBounds(600, 410, 150, 40);
		vote2Button.addActionListener(this);
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(backButton);
		background.add(dTxt);
		background.add(dateLabel);
		background.add(i1Label);
		background.add(i2Label);
		background.add(item2Label);
		background.add(item1Label);
		background.add(vote1Button);
		background.add(vote2Button);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==vote1Button)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
			      Statement stmt=con.createStatement();
			      String s = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
			      String query = "insert into votes values(null,"+pollid+", '"+mess+"', '"+name+"', '"+s+"', 1, 0);";
			      stmt.executeUpdate(query);
			      JOptionPane.showMessageDialog(frame, "You have voted for Menu 1!");
			    }
		    catch (Exception e1) 
		      {
		    	e1.printStackTrace();
		      }
		}
		if(e.getSource()==vote2Button)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
			      Statement stmt=con.createStatement();
			      String s = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
			      String query = "insert into votes values(null,"+pollid+", '"+mess+"', '"+name+"', '"+s+"', 0, 1);";
			      stmt.executeUpdate(query);
			      JOptionPane.showMessageDialog(frame, "You have voted for Menu 1!");
			    }
		    catch (Exception e1) 
		      {
		    	e1.printStackTrace();
		      }
		}
		if(e.getSource()==backButton)
		{
			frame.dispose();
			StudentInitialWindow siw = new StudentInitialWindow(name, mess);
			siw.initialWindowUI();
		}
	}

	public void getMenu()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
		      Statement stmt=con.createStatement();
		      String query = "select * from poll where mess_name='"+mess+"' and status = 1";
		      ResultSet rs = stmt.executeQuery(query);
		      if(rs.next())
		      {
		    	  String i1 = rs.getString(6);
		    	  String i2 = rs.getString(7);
		    	  Date d = rs.getDate(5);
		    	  String d1 = d.toString();
		    	  pollid = rs.getInt(1);
		    	  
		    	  item1Label = new JLabel(i1);
		  		item1Label.setFont (item1Label.getFont().deriveFont (15.0f));
		  		item1Label.setBounds(300, 330, 250, 50);
		  		item2Label = new JLabel(i2);
		  		item2Label.setFont (item2Label.getFont().deriveFont (15.0f));
		  		item2Label.setBounds(600, 330, 250, 50);
		  		
		  		dTxt = new JLabel(d1);
				dTxt.setFont (dTxt.getFont().deriveFont (15.0f));
				dTxt.setBounds(500, 250, 250, 50);
		    	  
		      }
		    }
	    catch (Exception e1) 
	      {
	    	e1.printStackTrace();
	      }
	}
}
