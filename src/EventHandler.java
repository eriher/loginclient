import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class EventHandler {
	
	private Kommunikation komm;
	private User user;
	
	public EventHandler()
	{
		komm = new Kommunikation();
	}
	
	
	public void test(String username, String password)
	{
		user = (User)komm.communicate(new String[]{"login",username,password});
		if(user == null)
			System.out.println("Wrong Login");
		else
		makenewframe();
	}
	public void check(String[] command)
	{
		System.out.println((String)komm.communicate(command));
	}


	private void makenewframe() {
		
		LinkedList<Day> week = user.getCurrentWeek();
		JFrame frame =  new JFrame("TestScreen");
		//frame.setPreferredSize(new Dimension(400,400));
		Container contentpane = frame.getContentPane();
		contentpane.setLayout(new BorderLayout());
		JPanel panel1 = new JPanel((new GridLayout(1,8)));
		JPanel panel5;
		panel5 = new JPanel((new GridLayout(2,1)));
		panel5.add(new JLabel("Dag"));
		panel5.add(new JLabel("Start Tid:"));
		panel1.add(panel5);
		GregorianCalendar c = new GregorianCalendar(Locale.FRANCE);
		DateFormat dateFormat = new SimpleDateFormat("E d/MM");
		for(int x = 0;x<7;x++){
			c.setWeekDate(2014, week.get(x).week, week.get(x).day);
			panel5 = new JPanel((new GridLayout(2,1)));
			JLabel dag = new JLabel(dateFormat.format(c.getTime()));
			JLabel tid = new JLabel(week.get(x).getStart());
			panel5.add(dag);
			panel5.add(tid);
			panel1.add(panel5);
		}
		JButton checkin = new JButton("Check In");
		checkin.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						check(new String[]{"checkin"});
			}
		});
		
		
		JButton checkout = new JButton("Check Out");
		checkout.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						check(new String[]{"checkout"});
			}
		});
		JPanel panel = new JPanel((new GridLayout(3,2)));
		final JTextField newusername = new JTextField();
		final JPasswordField newpassword = new JPasswordField();
		panel.add(new JLabel("Username:"));
		panel.add(newusername);
		panel.add(new JLabel("Password:"));
		panel.add(newpassword);
		JButton createuser = new JButton("Create User");
		createuser.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						check(new String[]{"createuser",newusername.getText(),new String (newpassword.getPassword())});
			}
		});
		panel.add(createuser);
		contentpane.add(panel1, BorderLayout.NORTH);
		contentpane.add(new JLabel("This user: "+user.getLogin().getUsername()+" is Logged in"), BorderLayout.CENTER);
		contentpane.add(checkout, BorderLayout.WEST);
		contentpane.add(checkin, BorderLayout.EAST);
		contentpane.add(panel, BorderLayout.SOUTH);
		contentpane.setPreferredSize(new Dimension(500,300));
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		
	}

}
