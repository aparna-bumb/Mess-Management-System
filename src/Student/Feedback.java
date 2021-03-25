package Student;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Feedback implements ActionListener{

	
	private String name;
	private String mess;
	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private JButton backButton;
	private JLabel nameLabel;
	private JLabel messLabel;
	private JTextField nameTxt;
	private JTextField messTxt;
	private JLabel feedbackLabel;
	private JTextArea feedbackTxt;
	private JButton submitButton;


	public Feedback(String n, String m) {
		// TODO Auto-generated constructor stub
		name = n;
		mess = m;
	}


	public void feedbackUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Give a feedback");
		
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
		nameLabel = new JLabel("Enter Name: ");
		nameLabel.setFont (nameLabel.getFont().deriveFont (15.0f));
		nameLabel.setBounds(300, 220, 250, 50);
		messLabel = new JLabel("Mess Name: ");
		messLabel.setFont (messLabel.getFont().deriveFont (15.0f));
		messLabel.setBounds(300, 270, 250, 50);
		feedbackLabel = new JLabel("Feedback: ");
		feedbackLabel.setFont (feedbackLabel.getFont().deriveFont (15.0f));
		feedbackLabel.setBounds(300, 320, 250, 50);
		
		//Test fields 
		nameTxt = new JTextField();
		nameTxt.setBounds(460, 235, 200, 30);
		messTxt = new JTextField();
		messTxt.setBounds(460, 285, 200, 30);
		feedbackTxt = new JTextArea();
		feedbackTxt.setBounds(460, 335, 450, 150);
		
		//submit button
		submitButton = new JButton("Send");
		submitButton.setBounds(460, 500, 100, 40);
		submitButton.addActionListener(this);
		
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(backButton);
		background.add(nameLabel);
		background.add(messLabel);
		background.add(feedbackLabel);
		background.add(feedbackTxt);
		background.add(messTxt);
		background.add(nameTxt);
		background.add(submitButton);
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
		if(e.getSource()==submitButton)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 
			      Statement stmt=con.createStatement();
			      String query = "insert into feedback values(null, '"+name+"', '"+mess+"', now(), '"+feedbackTxt.getText()+"', 0);";
			      System.out.println(query);
			      stmt.executeUpdate(query);
			      JOptionPane.showMessageDialog(frame, "Feedback sent!");
			    }
		    catch (Exception e1) 
		      {
		    	e1.printStackTrace();
		      }
		}
	}

}
