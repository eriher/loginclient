

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2120924315150798267L;
	private JFrame		 	frame;
	private JTextField		username;
	private JPasswordField		password;
	private JPanel			panel;
	private EventHandler	events;
	
	public LoginScreen(EventHandler events)
	{
		this.events=events;
		makeFrame();
	}
	
	public void makeFrame()
	{
		frame =  new JFrame("LoginScreen");
		//frame.setPreferredSize(new Dimension(400,400));
		Container contentpane = frame.getContentPane();
		contentpane.setLayout(new BorderLayout());
		panel = new JPanel((new GridLayout(2,2)));
		username = new JTextField();
		password = new JPasswordField();
		panel.add(new JLabel("Username:"));
		panel.add(username);
		panel.add(new JLabel("Password:"));
		panel.add(password);
		JButton login = new JButton("Login");
		login.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						events.test(username.getText(),new String(password.getPassword()));
			}
		});
		contentpane.add(panel, BorderLayout.CENTER);
		contentpane.add(login, BorderLayout.SOUTH);
		contentpane.setPreferredSize(new Dimension(250,80));
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
}