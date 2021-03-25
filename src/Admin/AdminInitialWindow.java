package Admin;
import Start.InitialWindow;
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
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminInitialWindow implements ActionListener{

	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private JButton viewBtn;
	private JButton feedbackBtn;
	private JButton addMenuBtn;
	private JButton pollBtn;
	private JButton approveBtn;
	private String name;
	private String mess;
	private JLabel totalLabel;
	private JComponent totalnumLabel;
	private JLabel bLabel;
	private JComponent lLabel;
	private Component dLabel;
	private JLabel bnumLabel;
	private Component lnumLabel;
	private Component dnumLabel;
	private JButton backButton;
	
	AdminInitialWindow(String n, String m)
	{
		name = n;
		mess = m;
	}

	public void initialWindowUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Login - Admin");
		
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
		
		backButton = new JButton("LogOut");
		backButton.setBounds(870, 630, 100, 40);
		backButton.addActionListener(this);
		
		//left side buttons
		addMenuBtn = new JButton("Add today's Menu");
		addMenuBtn.setBounds(80, 250, 150, 30);
		pollBtn = new JButton("Create a Poll");
		pollBtn.setBounds(80, 310, 150, 30);
		viewBtn = new JButton("Update Polls");
		viewBtn.setBounds(80, 370, 150, 30);
		approveBtn = new JButton("Approve a Refund");
		approveBtn.setBounds(80, 430, 150, 30);
		feedbackBtn = new JButton("View feedbacks");
		feedbackBtn.setBounds(80, 490, 150, 30);
		
		
		generateAttendance();
		totalLabel = new JLabel("Total Students Registred ");
		totalLabel.setFont (totalLabel.getFont().deriveFont (23.0f));
		totalLabel.setBounds(350, 240, 300, 50);
		
		int total;
		total = getTotal();
		totalnumLabel = new JLabel(Integer.toString(total));
		totalnumLabel.setFont (totalnumLabel.getFont().deriveFont (23.0f));
		totalnumLabel.setBounds(650, 240, 40, 50);
		
		bLabel = new JLabel("Attendance for break-fast:");
		bLabel.setFont (bLabel.getFont().deriveFont (18.0f));
		bLabel.setBounds(350, 270, 300, 50);
		lLabel = new JLabel("Attendance for lunch: ");
		lLabel.setFont (lLabel.getFont().deriveFont (18.0f));
		lLabel.setBounds(350, 295, 300, 50);
		dLabel = new JLabel("Attendance for dinner:  ");
		dLabel.setFont (dLabel.getFont().deriveFont (18.0f));
		dLabel.setBounds(350, 320, 300, 50);
		
		int total_b;
		total_b = getTotal_b();
		bnumLabel = new JLabel(Integer.toString(total_b));
		bnumLabel.setFont (bnumLabel.getFont().deriveFont (20.0f));
		bnumLabel.setBounds(600, 270, 300, 50);
		int total_l;
		total_l = getTotal_l();
		lnumLabel = new JLabel(Integer.toString(total_l));
		lnumLabel.setFont (lnumLabel.getFont().deriveFont (20.0f));
		lnumLabel.setBounds(600, 295, 300, 50);
		int total_d;
		total_d = getTotal_d();
		dnumLabel = new JLabel(Integer.toString(total_d));
		dnumLabel.setFont (dnumLabel.getFont().deriveFont (20.0f));
		dnumLabel.setBounds(600, 320, 300, 50);
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(backButton);
		background.add(totalLabel);
		background.add(totalnumLabel);
		background.add(addMenuBtn);
		background.add(pollBtn);
		background.add(viewBtn);
		background.add(approveBtn);
		background.add(feedbackBtn);
		background.add(lLabel);
		background.add(bLabel);
		background.add(dLabel);
		background.add(lnumLabel);
		background.add(bnumLabel);
		background.add(dnumLabel);
		addMenuBtn.addActionListener(this);
		pollBtn.addActionListener(this);
		viewBtn.addActionListener(this);
		approveBtn.addActionListener(this);
		feedbackBtn.addActionListener(this);
		//frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addMenuBtn)
		{
			frame.dispose();
			AddMenu am = new AddMenu(name, mess);
			am.addMenuUI();
		}
		if(e.getSource()==pollBtn)
		{
			frame.dispose();
			CreatePoll cp = new CreatePoll(name, mess);
			cp.createPollUI();
		}
		if(e.getSource()==feedbackBtn)
		{
			frame.dispose();
			FeedBack fd = new FeedBack(name, mess);
			fd.feedbackUI();
		}
		if(e.getSource()==approveBtn)
		{
			frame.dispose();
			Refund r = new Refund(name, mess);
			r.refundUI();
		}
		if(e.getSource()==viewBtn)
		{
			frame.dispose();
			UpdatePolls up = new UpdatePolls(name, mess);
			up.updatePollsUI();
		}
		if(e.getSource()==backButton)
		{
			frame.dispose();
			InitialWindow iw = new InitialWindow();
			iw.initialWindowUI();
		}
	}
	
	public void generateAttendance()
	{
		try
		{
			LocalDate da = java.time.LocalDate.now();
			da.toString();
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
		      Statement stmt=con.createStatement();
		      Statement stmt1=con.createStatement();
		      String query = "select * from attendance where date_= curDate() and mess_name='"+mess+"';";
		      ResultSet rs = stmt.executeQuery(query);
		      if(!rs.next())
		      {
		    	  query = "select * from students where mess_joined='"+mess+"';";
		    	  ResultSet rs1 = stmt.executeQuery(query);
		    	  while(rs1.next())
		    	  {
		    		  String n = rs1.getString(2);
		    		  query = "insert into attendance values('"+n+"', '"+mess+"', now(), 0, 0, 0);";
		    		  stmt1.executeUpdate(query);
		    	  }
		      }
		    }
	    catch (Exception e1) 
	      {
	    	e1.printStackTrace();
	      }
	}
	
	int getTotal()
	{
		try
		{
			int count = 0;
			LocalDate da = java.time.LocalDate.now();
			da.toString();
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
		      Statement stmt=con.createStatement();
		      Statement stmt1=con.createStatement();
		      String query = "select * from students where mess_joined='"+mess+"';";
		      ResultSet rs = stmt.executeQuery(query);
		      while(rs.next())
		      {
		    	  count++;
		      }
		      return count;
		    }
	    catch (Exception e1) 
	      {
	    	e1.printStackTrace();
	      }
		return 0;
	}

	int getTotal_b()
	{
		try
		{
			int count = 0;
			LocalDate da = java.time.LocalDate.now();
			da.toString();
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
		      Statement stmt=con.createStatement();
		      Statement stmt1=con.createStatement();
		      String query = "select * from attendance where mess_name='"+mess+"' and date_=curDate() and mark_b = 1;";
		      ResultSet rs = stmt.executeQuery(query);
		      while(rs.next())
		      {
		    	  count++;
		      }
		      return count;
		    }
	    catch (Exception e1) 
	      {
	    	e1.printStackTrace();
	      }
		return 0;
	}
	
	int getTotal_l()
	{
		try
		{
			int count = 0;
			LocalDate da = java.time.LocalDate.now();
			da.toString();
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
		      Statement stmt=con.createStatement();
		      Statement stmt1=con.createStatement();
		      String query = "select * from attendance where mess_name='"+mess+"' and date_=curDate() and mark_l = 1;";
		      ResultSet rs = stmt.executeQuery(query);
		      while(rs.next())
		      {
		    	  System.out.println("lunch");
		    	  count++;
		      }
		      return count;
		    }
	    catch (Exception e1) 
	      {
	    	e1.printStackTrace();
	      }
		return 0;
}
	int getTotal_d()
	{
		try
		{
			int count = 0;
			LocalDate da = java.time.LocalDate.now();
			da.toString();
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
		      Statement stmt=con.createStatement();
		      Statement stmt1=con.createStatement();
		      String query = "select * from attendance where mess_name='"+mess+"' and date_=curDate() and mark_d = 1;";
		      ResultSet rs = stmt.executeQuery(query);
		      while(rs.next())
		      {
		    	  count++;
		      }
		      return count;
		    }
	    catch (Exception e1) 
	      {
	    	e1.printStackTrace();
	      }
		return 0;
	}
}

