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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.Statement;

public class SignUp implements ActionListener {
	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private Component clgLabel;
	private Component cityLabel;
	private Component passLabel;
	private Component unameLabel;
	private Component nameLabel;
	private Component messLabel;
	private JTextField unameTxt;
	private JTextField nameTxt;
	private JTextField cityTxt;
	private JTextField passTxt;
	private JTextField clgTxt;
	private JTextField messTxt;
	private JButton insertBtn;

	public void signUpUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Sign Up - Student");
		
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
		
		nameLabel = new JLabel("Enter Name: ");
		nameLabel.setFont (nameLabel.getFont().deriveFont (15.0f));
		nameLabel.setBounds(300, 200, 250, 50);
		unameLabel = new JLabel("Enter UserName: ");
		unameLabel.setFont (unameLabel.getFont().deriveFont (15.0f));
		unameLabel.setBounds(300, 260, 250, 50);
		passLabel = new JLabel("Password: ");
		passLabel.setFont (passLabel.getFont().deriveFont (15.0f));
		passLabel.setBounds(300, 320, 250, 50);
		cityLabel = new JLabel("City: ");
		cityLabel.setFont (cityLabel.getFont().deriveFont (15.0f));
		cityLabel.setBounds(300, 380, 250, 50);
		clgLabel = new JLabel("College: ");
		clgLabel.setFont (clgLabel.getFont().deriveFont (15.0f));
		clgLabel.setBounds(300, 440, 250, 50);
		messLabel = new JLabel("Mess name: ");
		messLabel.setFont(messLabel.getFont().deriveFont(15.0f));
		messLabel.setBounds(300, 500, 250, 50);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(500, 210, 200, 30);
		unameTxt = new JTextField();
		unameTxt.setBounds(500, 270, 200, 30);
		passTxt = new JTextField();
		passTxt.setBounds(500, 330, 200, 30);
		cityTxt = new JTextField();
		cityTxt.setBounds(500, 390, 200, 30);
		clgTxt = new JTextField();
		clgTxt.setBounds(500, 450, 200, 30);
		messTxt = new JTextField();
		messTxt.setBounds(500, 510, 200, 30);
		
		insertBtn = new JButton("SignUp");
		insertBtn.setBounds(770, 550, 150, 30);
		insertBtn.addActionListener(this);

		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(cityLabel);
		background.add(nameLabel);
		background.add(unameLabel);
		background.add(passLabel);
		background.add(messLabel);
		background.add(clgLabel);
		background.add(cityTxt);
		background.add(nameTxt);
		background.add(unameTxt);
		background.add(passTxt);
		background.add(messTxt);
		background.add(clgTxt);
		background.add(insertBtn);
		//frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==insertBtn)
		{
			String name = nameTxt.getText();
			String u_name = unameTxt.getText();
			String p_name = passTxt.getText();
			String city = cityTxt.getText();
			String clg = clgTxt.getText();
			String mess = messTxt.getText();
			String query ;
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 

			      //System.out.println("Database Connected");
				int flag =0;
				query = "select * from students where username='"+u_name+"';";
				Statement stmt1=con.createStatement();
			      ResultSet rs1 = stmt1.executeQuery(query);
			      while(rs1.next())
			      {
			    	  flag=1;
			      }
			      if(flag==1)
			      {
			    	  JOptionPane.showMessageDialog(frame, "Username is already taken");
			      }
			      else
			      {
			    	  query = "insert into students values('"+name+"', '"+u_name+"', '"+p_name+"', '"+city+"', '"+clg+"', '"+mess+"', now());";
			    	  Statement stmt=con.createStatement();
				      stmt.executeUpdate(query);
				      JOptionPane.showMessageDialog(frame, "Signed Up Successfully");
				      frame.dispose();
				      Login l = new Login();
				      l.loginUI();
			      }
			    }
		    catch (Exception e1) 
		      {
		    	e1.printStackTrace();
		      }
		}
	}
}