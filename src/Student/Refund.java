package Student;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Refund implements ActionListener{
	
	public static String name;
	private String mess;
	private JFrame frame;
	private JLabel background;
	private JLabel titleLabel;
	private JButton backButton;
	private JPanel chatPanel;

	public Refund(String n, String m) {
		// TODO Auto-generated constructor stub
		name = n;
		mess = m;
	}

	public void refundUI()
	{
		frame = new JFrame();
		//panel = new JPanel();
		frame.setSize(1015, 740);
		frame.setTitle("Get Connectedt");
		
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
		
		//chat panel
		try {
			chatPanel = new ServerPanel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chatPanel.setBounds(300,  200, 280, 330);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		chatPanel.setBorder(blackline);
        background.add(chatPanel);
		
		frame.add(background);
		//background.setLayout(new FlowLayout());
		background.add(titleLabel);
		background.add(logo);
		background.add(backButton);
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
	}
}

class ServerPanel extends JPanel implements Runnable
{
    private JTextField messageField;
    private JTextField nameField;
    public JTextArea textArea;
    public Socket clientSocket;
	public ServerSocket serverSocket;

    ServerPanel() throws IOException
    {
        serverSocket = new ServerSocket(5555);
        JLabel nameLabel = new JLabel("Chat with Mess Admin");
        add(nameLabel);

        //Text area
        textArea = new JTextArea(14,20);
        textArea.setEditable(false);
        add(textArea);

        //Message
        messageField = new JTextField(15);
        add(messageField);

        //Button
        JButton submitButton = new JButton("Send");
        submitButton.addActionListener(new SendTextListener());
        add(submitButton);

        Thread thread = new Thread(this);
        thread.start();
    }

@Override    
public void run()
{
    try
    {
    	clientSocket = serverSocket.accept();
        String clientInformation;
        DataInputStream din=new DataInputStream(clientSocket.getInputStream());
     
        while (true)
        {
            clientInformation = din.readUTF();  
            textArea.append("\n" + clientInformation);    
        }
     
    }
    catch (Exception exception)
    {
        exception.printStackTrace();
    }
}
class SendTextListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try
        {

        	DataOutputStream dout=new DataOutputStream(clientSocket.getOutputStream()); 
        	String str1= messageField.getText();
        	String str = Refund.name + ": " + str1;
            textArea.append("\nYou: " + str1);
            messageField.setText("");
            dout.writeUTF(str);  
            dout.flush();  
            /*textArea.append("\n"+Refund.name + messageField.getText());
            dout.writeUTF(messageField.getText());  
            messageField.setText("");
            dout.flush();  */
        }
        catch (IOException e1)
        {
            System.out.println(e1.getMessage());
        }
    }
}
}
