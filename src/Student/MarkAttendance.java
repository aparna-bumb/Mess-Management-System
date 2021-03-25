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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MarkAttendance implements ActionListener{
	
	private String name;
	private String mess;
	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private JButton backButton;
	private JLabel nameLabel;
	private JLabel unameLabel;
	private JLabel messLabel;
	private Component dateLabel;
	private JLabel nameTxt;
	private Component unameTxt;
	private Component messTxt;
	private Component dateTxt;
	private JButton breakfastBtn;
	private JButton dinnerBtn;
	private JButton lunchBtn;

	MarkAttendance(String n, String m)
	{
		name = n;
		mess=m;
	}
	
	public void markAttendanceUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("MarkAttendance - Student");
		
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
		nameLabel = new JLabel("Name of Student: ");
		nameLabel.setFont (nameLabel.getFont().deriveFont (15.0f));
		nameLabel.setBounds(300, 250, 250, 50);
		unameLabel = new JLabel("UserName: ");
		unameLabel.setFont (unameLabel.getFont().deriveFont (15.0f));
		unameLabel.setBounds(300, 290, 250, 50);
		messLabel = new JLabel("Mess Enrolled for: ");
		messLabel.setFont (messLabel.getFont().deriveFont (15.0f));
		messLabel.setBounds(300, 330, 250, 50);
		dateLabel = new JLabel("Date: ");
		dateLabel.setFont (dateLabel.getFont().deriveFont (15.0f));
		dateLabel.setBounds(300, 370, 250, 50);
		
		nameTxt = new JLabel(getName());
		nameTxt.setFont (nameTxt.getFont().deriveFont (15.0f));
		nameTxt.setBounds(500, 250, 250, 50);
		unameTxt = new JLabel(name);
		unameTxt.setFont (unameTxt.getFont().deriveFont (15.0f));
		unameTxt.setBounds(500, 290, 250, 50);
		messTxt = new JLabel(mess);
		messTxt.setFont (messTxt.getFont().deriveFont (15.0f));
		messTxt.setBounds(500, 330, 250, 50);
		Date date = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = formatter.format(date);
		formatter = new SimpleDateFormat("dd MMMM yyyy");  
		strDate = formatter.format(date);
		dateTxt = new JLabel(strDate);
		dateTxt.setFont (dateTxt.getFont().deriveFont (15.0f));
		dateTxt.setBounds(500, 370, 250, 50);
		
		//buttons
		breakfastBtn = new JButton("Mark for Breakfast");
		breakfastBtn.setBounds(80, 260, 150, 30);
		dinnerBtn = new JButton("Mark for Dinner");
		dinnerBtn.setBounds(80, 380, 150, 30);
		lunchBtn = new JButton("Mark for Lunch");
		lunchBtn.setBounds(80, 320, 150, 30);
		
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(backButton);
		background.add(unameLabel);
		background.add(messLabel);
		background.add(dateLabel);
		background.add(nameLabel);
		background.add(nameTxt);
		background.add(unameTxt);
		background.add(dateTxt);
		background.add(messTxt);
		background.add(breakfastBtn);
		background.add(dinnerBtn);
		background.add(lunchBtn);
		breakfastBtn.addActionListener(this);
		lunchBtn.addActionListener(this);
		dinnerBtn.addActionListener(this);
		//frame.add(panel);
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
			StudentInitialWindow siw = new StudentInitialWindow(name, mess);
			siw.initialWindowUI();
		}
		if(e.getSource()==breakfastBtn)
		{
			mark("b");
			JOptionPane.showMessageDialog(frame, "Marked for Breakfast");
			frame.dispose();
			StudentInitialWindow siw = new StudentInitialWindow(name, mess);
			siw.initialWindowUI();
		}
		if(e.getSource()==lunchBtn)
		{
			mark("l");
			JOptionPane.showMessageDialog(frame, "Marked for Lunch");
			frame.dispose();
			StudentInitialWindow siw = new StudentInitialWindow(name, mess);
			siw.initialWindowUI();
		}
		if(e.getSource()==dinnerBtn)
		{
			mark("d");
			JOptionPane.showMessageDialog(frame, "Marked for Dinner");
			frame.dispose();
			StudentInitialWindow siw = new StudentInitialWindow(name, mess);
			siw.initialWindowUI();
		}
	}
	
	String getName()
	{
		return get(1);
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
		      String query = "select * from students where username='"+name+"';";
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
	
	public void mark(String s)
	{
		try
		{
			LocalDate da = java.time.LocalDate.now();
			String d1 = da.toString();
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 

		      //System.out.println("Database Connected");
		      Statement stmt=con.createStatement();
		      String query = "select * from attendance where date_='"+d1+"' and uname='"+name+"';";
		      //System.out.println(query);
		      ResultSet rs = stmt.executeQuery(query);
		    	  query = "update attendance set mark_"+ s +" = 1 where uname='"+name+"' and date_='"+d1+"';";
		    	  //System.out.println(query);
		    	  stmt.executeUpdate(query);
		    	  
		    }
	    catch (Exception e1) 
	      {
	    	e1.printStackTrace();
	      }
	}

}
