package Student;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class StudentWindow implements ActionListener {
	private JFrame frame;
	private JLabel background;
	private Component titleLabel;
	private JButton loginButton;
	private JButton signupButton;
	public void studentWindowUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Student");
		
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
		
		//Log-in button
		loginButton = new JButton("Log In");
		loginButton.setBounds(550, 310, 100, 40);
		loginButton.addActionListener(this);
		
		//Sign-up Button
		signupButton = new JButton("Sign Up");
		signupButton.setBounds(550, 385, 100, 40);
		signupButton.addActionListener(this);
		
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(loginButton);
		background.add(signupButton);
		//frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==loginButton)
		{
			frame.dispose();
			Login aw = new Login();
			aw.loginUI();
		}
		if(e.getSource()==signupButton)
		{
			frame.dispose();
			SignUp sw = new SignUp();
			sw.signUpUI();
		}
	}
}
