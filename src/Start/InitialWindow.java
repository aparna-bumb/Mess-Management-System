package Start;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;

import Admin.AdminWindow;
import Student.StudentWindow;

public class InitialWindow implements ActionListener{
	private JFrame frame;
	private JLabel background;
	private Component titleLabel;
	private JButton adminButton;
	private JButton studentButton;

	public void initialWindowUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Mess Management System");
		
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
		
		//Admin button
		adminButton = new JButton("Admin");
		adminButton.setBounds(550, 300, 100, 40);
		adminButton.addActionListener(this);
		
		//Student Button
		studentButton = new JButton("Student");
		studentButton.setBounds(550, 375, 100, 40);
		studentButton.addActionListener(this);
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(adminButton);
		background.add(studentButton);
		//frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==adminButton)
		{
			frame.dispose();
			AdminWindow aw = new AdminWindow();
			aw.adminWindowUI();
		}
		if(e.getSource()==studentButton)
		{
			frame.dispose();
			StudentWindow sw = new StudentWindow();
			sw.studentWindowUI();
		}
	}
}
