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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UpdatePolls implements ActionListener{
	private int pollid;
	private String name;
	private String mess;
	private JTable table;
	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private JButton backButton;
	private JButton deactivateButton;
	private JScrollPane scrollPane;


	public UpdatePolls(String n, String m) {
		// TODO Auto-generated constructor stub
		name= n;
		mess = m;
	}

	public void updatePollsUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Update a Poll - Admin");
		
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
		String query = "select * from poll where status = 1 and mess_name = '"+mess+"';";
		table = showTable(query);
		scrollPane=new JScrollPane(table);
		scrollPane.setBounds(300, 250, 650, 170);
		
		//Deactivate Button
		deactivateButton = new JButton("Deactivate");
		deactivateButton.setBounds(575, 450, 100, 40);
		deactivateButton.addActionListener(this);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me ) {
				JTable t = (JTable)me.getSource();
				int row = t.getSelectedRow();
				int column = t.getSelectedColumn();
				String poll = (String)t.getValueAt(row, column);
				pollid = Integer.parseInt(poll);
				System.out.println(poll);
			}
		});
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(scrollPane);
		background.add(backButton);
		background.add(deactivateButton);
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
		if(e.getSource()==deactivateButton)
		{
			deactivate();
		}
	}
	
	public JTable showTable(String q)
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu");   
			Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(q);
            String query = "select * from votes where mess_name = '"+mess+"' and ";
            String h[]={"Poll ID","Type","Date","Menu 1","Menu 2","Vote for 1", "Vote for 2"};
            String d[][]=new String[50][7]; 
            int i=0, j=0;
            while(rs.next()){
            	int count1 =0, count2=0;
            	d[i][j++]=Integer.toString(rs.getInt(1));
            	d[i][j++]=rs.getString(4);
            	Date date = rs.getDate(5);
            	d[i][j++]=date.toString();
                d[i][j++]=rs.getString(6);
                d[i][j++]=rs.getString(7);
                query = "select * from votes where vote_1 = 1 and poll_id ="+rs.getInt(1)+";";
                //System.out.println(query);
                Statement stmt1=con.createStatement();
                ResultSet rs1=stmt1.executeQuery(query);
                while(rs1.next())
                {
                	count1++;
                }
                query = "select * from votes where vote_2 = 1 and poll_id ="+rs.getInt(1)+";";
                //System.out.println(query);
                Statement stmt2=con.createStatement();
                ResultSet rs2=stmt2.executeQuery(query);
                while(rs2.next())
                {
                	count2++;
                }
                d[i][j++]=Integer.toString(count1);
                d[i][j++]=Integer.toString(count2);
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
	
	void deactivate()
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mess","root","appurachu");   
			Statement stmt=con.createStatement();
			String query = "update poll set status= 0 where poll_id="+pollid+";";
			stmt.executeUpdate(query);
			JOptionPane.showMessageDialog(frame, "Poll deactivated successfully");
			scrollPane.setVisible(false);
			query = "select * from poll where status = 1 and mess_name = '"+mess+"';";
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
