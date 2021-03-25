package Admin;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CreatePoll implements ActionListener {

	
	private String name;
	private String mess;
	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private JButton backButton;
	private JLabel unameTxt;
	private JLabel messTxt;
	private JComboBox<String> categoryCombo;
	private JLabel i2Label;
	private JLabel i1Label;
	private JLabel forLabel;
	private JLabel dateLabel;
	private JLabel messLabel;
	private JLabel unameLabel;
	private JTextField dateTxt;
	private JTextField i1Txt;
	private JTextField i2Txt;
	private JButton createButton;
	private JButton updateBtn;
	CreatePoll(String n, String m)
	{
		name = n;
		mess = m;
	}
	
	public void createPollUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Create a Poll - Admin");
		
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
		unameLabel = new JLabel("UserName: ");
		unameLabel.setFont (unameLabel.getFont().deriveFont (15.0f));
		unameLabel.setBounds(300, 220, 250, 50);
		messLabel = new JLabel("Mess Name: ");
		messLabel.setFont (messLabel.getFont().deriveFont (15.0f));
		messLabel.setBounds(300, 270, 250, 50);
		dateLabel = new JLabel("Date: ");
		dateLabel.setFont (dateLabel.getFont().deriveFont (15.0f));
		dateLabel.setBounds(300, 320, 250, 50);
		forLabel = new JLabel("Create a poll for: ");
		forLabel.setFont (forLabel.getFont().deriveFont (15.0f));
		forLabel.setBounds(300, 370, 250, 50);
		i1Label = new JLabel("Menu 1: ");
		i1Label.setFont (i1Label.getFont().deriveFont (15.0f));
		i1Label.setBounds(300, 420, 250, 50);
		i2Label = new JLabel("Menu 2: ");
		i2Label.setFont (i2Label.getFont().deriveFont (15.0f));
		i2Label.setBounds(300, 470, 250, 50);
		
		unameTxt = new JLabel(name);
		unameTxt.setFont (unameTxt.getFont().deriveFont (15.0f));
		unameTxt.setBounds(500, 220, 250, 50);
		messTxt = new JLabel(mess);
		messTxt.setFont (messTxt.getFont().deriveFont (15.0f));
		messTxt.setBounds(500, 270, 250, 50);
		
		//textfields
		dateTxt = new JTextField();
		dateTxt.setBounds(500, 330, 200, 30);
		i1Txt = new JTextField();
		i1Txt.setBounds(500, 435, 200, 30);
		i2Txt = new JTextField();
		i2Txt.setBounds(500, 485, 200, 30);
		
		//combobox
		//Combo box
		String category[]= {"Break-fast", "Lunch", "Dinner"};
		categoryCombo = new JComboBox<String>(category);
		categoryCombo.setBounds(500, 380, 200, 30);
		
		//create button
		createButton = new JButton("Create");
		createButton.setBounds(400, 540, 100, 40);
		createButton.addActionListener(this);
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(backButton);
		background.add(unameLabel);
		background.add(messLabel);
		background.add(dateLabel);
		background.add(forLabel);
		background.add(i1Label);
		background.add(i2Label);
		background.add(unameTxt);
		background.add(messTxt);
		background.add(categoryCombo);
		background.add(dateTxt);
		background.add(i1Txt);
		background.add(i2Txt);
		background.add(createButton);
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
		if(e.getSource()==createButton)
		{
			try
			{
				LocalDate da = java.time.LocalDate.now();
				String d1 = da.toString();
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu"); 

			      //System.out.println("Database Connected");
			      Statement stmt=con.createStatement();
			      String query = "insert into poll values(null, '"+mess+"', '"+name+"', '"+(String) categoryCombo.getItemAt(categoryCombo.getSelectedIndex())+"', '"+
			    		  dateTxt.getText()+"', '"+i1Txt.getText()+"', '"+i2Txt.getText()+"', now(), 1);";
			      System.out.println(query);
			      stmt.executeUpdate(query);
			      JOptionPane.showMessageDialog(frame, "Poll created successfully");
			    }
		    catch (Exception e1) 
		      {
		    	e1.printStackTrace();
		      }
		}
	}

}
