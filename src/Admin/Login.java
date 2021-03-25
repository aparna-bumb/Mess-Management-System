package Admin;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Student.StudentInitialWindow;
import Student.StudentWindow;


public class Login implements ActionListener {
	private JFrame frame;
	private JLabel background;
	private Component titleLabel;
	private JButton submitButton;
	private JLabel uLabel;
	private JTextField uTxt;
	private JLabel pLabel;
	private JTextField pTxt;
	private JButton backButton;
	public void loginUI()
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
		titleLabel.setFont (titleLabel.getFont ().deriveFont (55.0f));
		Dimension sizeTitle = titleLabel.getPreferredSize();
		titleLabel.setBounds(200, 100, 700, sizeTitle.height);
		
		//logo
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("C:\\Users\\hp\\Desktop\\mini proj\\iamges\\logo1.png"));
		Dimension size = logo.getPreferredSize();
		logo.setBounds(256, 250, size.width, size.height);
		
		//Username
		uLabel = new JLabel("Username: ");
		uLabel.setFont(uLabel.getFont().deriveFont(20.0f));
		uLabel.setBounds(550, 270, 200, 40);
		uTxt = new JTextField();
		uTxt.setBounds(550, 310, 150, 30);
		
		//Password
		pLabel = new JLabel("Password: ");
		pLabel.setFont(pLabel.getFont().deriveFont(20.0f));
		pTxt = new JTextField();
		pLabel.setBounds(550, 370, 200, 40);
		pTxt.setBounds(550, 410, 150, 30);
		
		//Submit Button
		submitButton = new JButton("Submit");
		submitButton.setBounds(450, 490, 100, 40);
		submitButton.addActionListener(this);
		
		//Back Button
		backButton = new JButton("Back");
		backButton.setBounds(870, 630, 100, 40);
		backButton.addActionListener(this);
		
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(pLabel);
		background.add(uLabel);
		background.add(pTxt);
		background.add(uTxt);
		background.add(submitButton);
		background.add(backButton);
		//frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==submitButton)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu");
			    int flag=0;  
				String username = uTxt.getText();
			      String password = pTxt.getText();
			      //System.out.println("Database Connected");
			      Statement stmt=con.createStatement();
			      String query = "select * from admin where username='"+username+"';";
			      ResultSet rs = stmt.executeQuery(query);
			      while(rs.next())
			      {
			    	  if(username.equals(rs.getString(2))==true)
			    	  {
			    		  if(password.equals(rs.getString(3))==false)
			    		  {
			    			  JOptionPane.showMessageDialog(frame, "Wrong Password");
			    			  flag=2;
			    		  }
			    		  else
			    		  {
			    			  frame.dispose();
			    			  AdminInitialWindow siw = new AdminInitialWindow(username, rs.getString(4));
			    			  flag=1;
			    			  siw.initialWindowUI();
			    		  }
			    	  }
			      }
			      if(flag==0)
			      {
			    	  JOptionPane.showMessageDialog(frame, "UserName not found :(");
			      }
			    }
		    catch (Exception e1) 
		      {
		    	e1.printStackTrace();
		      }
		}
		if(e.getSource()==backButton)
		{
			frame.dispose();
			StudentWindow sw = new StudentWindow();
			sw.studentWindowUI();
		}
	}
}
