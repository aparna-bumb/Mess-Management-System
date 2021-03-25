package Admin;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FeedBack implements ActionListener{

	
	private String name;
	private String mess;
	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private JButton backButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton ackButton;
	private int id;
	public FeedBack(String n, String m) {
		// TODO Auto-generated constructor stub
		name = n;
		mess = m;
	}
	public void feedbackUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("View Feedbacks - Admin");
		
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
		
		//table
		String query = "select * from feedback where status = 0 and mess_name = '"+mess+"';";
		table = showTable(query);
		scrollPane=new JScrollPane(table);
		scrollPane.setBounds(300, 250, 650, 170);
		
		//acknowledge button
		ackButton = new JButton("Acknowledge");
		ackButton.setBounds(550, 450, 150, 40);
		ackButton.addActionListener(this);
		
		table.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent me ) {
				JTable t = (JTable)me.getSource();
				int row = t.getSelectedRow();
				int column = t.getSelectedColumn();
				String poll = (String)t.getValueAt(row, column);
				id = Integer.parseInt(poll);
				System.out.println(poll);
			}
		});
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(scrollPane);
		background.add(backButton);
		background.add(ackButton);
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
			AdminInitialWindow aiw = new AdminInitialWindow(name, mess);
			aiw.initialWindowUI();
		}
		if(e.getSource()==ackButton)
		{
			acknowledge();
		}
	}
	
	public JTable showTable(String q)
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu");   
			Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(q);
            String h[]={"ID", "Student name","Feedback"};
            String d[][]=new String[500][3]; 
            int i=0, j=0;
            while(rs.next()){
            	d[i][j++]=rs.getString(1);
                d[i][j++]=rs.getString(2);
                d[i][j++]=rs.getString(5);
                i++;
                j=0;
            }
        
            table=new JTable(d,h);
            //table.print();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return table;
	}
	
	public void acknowledge()
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu");   
			Statement stmt=con.createStatement();
			String query = "update feedback set status= 1 where feedback_id="+id+";";
			stmt.executeUpdate(query);
			JOptionPane.showMessageDialog(frame, "Feedback  acknowledged!");
			scrollPane.setVisible(false);
			query = "select * from feedback where status = 0 and mess_name = '"+mess+"';";
			table = showTable(query);
			scrollPane=new JScrollPane(table);
			scrollPane.setBounds(300, 250, 650, 170);
            background.add(scrollPane);
            scrollPane.setVisible(true);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
