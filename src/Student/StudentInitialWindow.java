package Student;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Start.InitialWindow;

public class StudentInitialWindow implements ActionListener{
	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private JButton markAttendanceBtn;
	private JButton requestBtn;
	private JButton viewBtn;
	private JButton voteBtn;
	private JButton feedbackBtn;
	private JTable table;
	private JLabel breakfastLabel;
	private JLabel lunchLabel;
	private JLabel dinnerLabel;
	private JLabel b1Label;
	private JLabel b2Label;
	private JLabel d2Label;
	private JLabel d3Label;
	private JLabel d1Label;
	private JLabel l1Label;
	private JLabel l2Label;
	private JLabel l3Label;
	private JLabel l4Label;
	private JLabel menuLabel;
	private String name;
	private String mess;
	private JLabel breakfast;
	private JLabel lunch;
	private JLabel dinner;
	private JButton backButton;

	public StudentInitialWindow(String n, String m) {
		// TODO Auto-generated constructor stub
		name = n;
		mess = m;
	}

	public void initialWindowUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Student Window");
		
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
				backButton = new JButton("Log Out");
				backButton.setBounds(870, 630, 100, 40);
				backButton.addActionListener(this);
		
		//left side buttons
		markAttendanceBtn = new JButton("Mark Attendance");
		markAttendanceBtn.setBounds(80, 250, 150, 30);
		voteBtn = new JButton("Vote for Menu");
		voteBtn.setBounds(80, 310, 150, 30);
		requestBtn = new JButton("Request for Refund");
		requestBtn.setBounds(80, 370, 150, 30);
		feedbackBtn = new JButton("Give a feedback");
		feedbackBtn.setBounds(80, 430, 150, 30);
		
		//images
		ImageIcon ic1 = new ImageIcon("C:\\Users\\hp\\Desktop\\mini proj\\iamges\\d.png");
		breakfast = new JLabel(ic1);
		Dimension size2 = breakfast.getPreferredSize();
		breakfast.setBounds(330, 250, size2.width, size2.height);
		
		ImageIcon ic2 = new ImageIcon("C:\\Users\\hp\\Desktop\\mini proj\\iamges\\b.png");
		lunch = new JLabel(ic2);
		Dimension size3 = lunch.getPreferredSize();
		lunch.setBounds(550, 250, size3.width, size3.height);
		
		ImageIcon ic3 = new ImageIcon("C:\\Users\\hp\\Desktop\\mini proj\\iamges\\l.png");
		dinner = new JLabel(ic3);
		Dimension size4 = dinner.getPreferredSize();
		dinner.setBounds(770, 250, size4.width, size4.height);
		
		//labels
		menuLabel = new JLabel("What's on table for today? ");
		menuLabel.setFont (menuLabel.getFont().deriveFont (28.0f));
		menuLabel.setBounds(450, 190, 400, 50);
		breakfastLabel = new JLabel("Breakfast: ");
		breakfastLabel.setFont (breakfastLabel.getFont().deriveFont (18.0f));
		breakfastLabel.setBounds(330, 330, 250, 50);
		lunchLabel = new JLabel("Lunch: ");
		lunchLabel.setFont (lunchLabel.getFont().deriveFont (18.0f));
		lunchLabel.setBounds(550, 330, 250, 50);
		dinnerLabel = new JLabel("Dinner: ");
		dinnerLabel.setFont (dinnerLabel.getFont().deriveFont (18.0f));
		dinnerLabel.setBounds(770, 330, 250, 50);
		
		b1Label = new JLabel(getb1());
		b1Label.setFont (b1Label.getFont().deriveFont (15.0f));
		b1Label.setBounds(330, 365, 250, 50);
		b2Label = new JLabel(getb2());
		b2Label.setFont (b2Label.getFont().deriveFont (15.0f));
		b2Label.setBounds(330, 390, 250, 50);
		d1Label = new JLabel(getd1());
		d1Label.setFont (d1Label.getFont().deriveFont (15.0f));
		d1Label.setBounds(770, 365, 250, 50);
		d2Label = new JLabel(getd2());
		d2Label.setFont (d2Label.getFont().deriveFont (15.0f));
		d2Label.setBounds(770, 390, 250, 50);
		d3Label = new JLabel(getd3());
		d3Label.setFont (d3Label.getFont().deriveFont (15.0f));
		d3Label.setBounds(770, 415, 250, 50);
		l1Label = new JLabel(getl1());
		l1Label.setFont (l1Label.getFont().deriveFont (15.0f));
		l1Label.setBounds(550, 365, 250, 50);
		l2Label = new JLabel(getl2());
		l2Label.setFont (l2Label.getFont().deriveFont (15.0f));
		l2Label.setBounds(550, 390, 250, 50);
		l3Label = new JLabel(getl3());
		l3Label.setFont (l3Label.getFont().deriveFont (15.0f));
		l3Label.setBounds(550, 415, 250, 50);
		l4Label = new JLabel(getl4());
		l4Label.setFont (l4Label.getFont().deriveFont (15.0f));
		l4Label.setBounds(550, 440, 250, 50);
		
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(backButton);
		background.add(markAttendanceBtn);
		background.add(voteBtn);
		background.add(requestBtn);
		background.add(feedbackBtn);
		background.add(breakfast);
		background.add(lunch);
		background.add(dinner);
		background.add(breakfastLabel);
		background.add(lunchLabel);
		background.add(dinnerLabel);
		background.add(menuLabel);
		background.add(b1Label);
		background.add(b2Label);
		background.add(d1Label);
		background.add(d2Label);
		background.add(d3Label);
		background.add(l1Label);
		background.add(l2Label);
		background.add(l3Label);
		background.add(l4Label);
		markAttendanceBtn.addActionListener(this);
		voteBtn.addActionListener(this);
		requestBtn.addActionListener(this);
		feedbackBtn.addActionListener(this);
		//frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==markAttendanceBtn)
		{
			frame.dispose();
			MarkAttendance ma = new MarkAttendance(name, mess);
			ma.markAttendanceUI();
		}
		if(e.getSource()==feedbackBtn)
		{
			frame.dispose();
			Feedback f = new Feedback(name, mess);
			f.feedbackUI();
		}
		if(e.getSource()==requestBtn)
		{
			frame.dispose();
			Refund r = new Refund(name, mess);
			r.refundUI();
		}
		if(e.getSource()==voteBtn)
		{
			frame.dispose();
			VoteForMenu vfm = new VoteForMenu(name, mess);
			vfm.voteUI();
		}
		if(e.getSource()==backButton)
		{
			frame.dispose();
			InitialWindow iw = new InitialWindow();
			iw.initialWindowUI();
		}
	}
	
	
	String getb1()
	{
		return get(4);
	}
	String getb2()
	{
		return get(5);
	}
	String getd1()
	{
		return get(10);
	}
	String getd2()
	{
		return get(11);
	}
	String getd3()
	{
		return get(12);
	}
	String getl1()
	{
		return get(6);
	}
	String getl2()
	{
		return get(7);
	}
	String getl3()
	{
		return get(8);
	}
	String getl4()
	{
		return get(9);
	}
	
	String get(int i)
	{
		try
		{
			LocalDate da = java.time.LocalDate.now();
			String d1 = da.toString();
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 

		      //System.out.println("Database Connected");
		      Statement stmt=con.createStatement();
		      String query = "select * from menu where date_='"+d1+"';";
		      ResultSet rs = stmt.executeQuery(query);
		      while(rs.next())
		      {
		    	  return rs.getString(i);  
		      }
		    }
	    catch (Exception e1) 
	      {
	    	e1.printStackTrace();
	      }
		return "";
	}
}
