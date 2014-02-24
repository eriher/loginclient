import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
		
	}
	
	
	public void test(String username, String password)
	{
		komm = new Kommunikation();
		user = (User)komm.communicate(new String[]{"login",username,password});
		if(user == null)
		{
			komm.disconnect();
			System.out.println("Wrong Login");
		}
		else
		makenewframe();
	}
	public void check(Object... command)
	{
		System.out.println((String)komm.communicate(command));
	}
	public  void newschedule(Object...command)
	{
		for(int x=1;x<53;x++)
		{
			for(int y=0;y<7;y++)
			{
				user.getschema().get(x).get(y).setStart((String)command[0]);
				user.getschema().get(x).get(y).setSlut((String)command[1]);
			}
		}
		check("modifyschedule",user.getschema());
	}


	private void makenewframe() {
		
		LinkedList<Day> week = user.getCurrentWeek();
		final JFrame frame =  new JFrame("TestScreen");
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
		JPanel panel6 = new JPanel((new GridLayout(2,1)));
		JButton checkin = new JButton("Check In");
		checkin.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						check("checkin");
			}
		});
		JButton checkout = new JButton("Check Out");
		checkout.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						check("checkout");
			}
		});
		panel6.add(checkin);
		panel6.add(checkout);
		JPanel panel = new JPanel((new GridLayout(3,2)));
		final JTextField newusername = new JTextField();
		final JPasswordField newpassword = new JPasswordField();
		panel.add(new JLabel("Username:"));
		panel.add(newusername);
		panel.add(new JLabel("Password:"));
		panel.add(newpassword);
		final JComboBox<String> ualist = new JComboBox<String>();
		ualist.addItem("User");
		ualist.addItem("Boss");
		ualist.addItem("Admin");
		panel.add(ualist);
		JButton createuser = new JButton("Create User");
		createuser.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						check("createuser",newusername.getText(),new String (newpassword.getPassword()),(String)ualist.getSelectedItem());
			}
		});
		panel.add(createuser);
		JPanel panel3 = new JPanel();
		final JComboBox<String> starth = new JComboBox<String>();
		final JComboBox<String> startm = new JComboBox<String>();
		for(int x=0;x<10;x++)
		starth.addItem("0"+Integer.toString(x));
		for(int x=10;x<24;x++)
		starth.addItem(Integer.toString(x));
		startm.addItem("00");
		startm.addItem("15");
		startm.addItem("30");
		startm.addItem("45");
		JButton mod = new JButton("Mod Schedule");
		mod.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						newschedule(starth.getSelectedItem()+":"+startm.getSelectedItem()
								,"");
						makenewframe();
						frame.dispose();
			}
		});
		panel3.add(new JLabel("Starttid:"));
		panel3.add(starth);
		panel3.add(startm);
		panel3.add(mod);
		contentpane.add(panel1, BorderLayout.CENTER);
		contentpane.add(new JLabel("This user is Logged in: "+user.getLogin().getUsername()), BorderLayout.NORTH);
		contentpane.add(panel6, BorderLayout.WEST);
		contentpane.add(panel3, BorderLayout.SOUTH);
		contentpane.add(panel, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	    frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	          komm.disconnect();
	        }
	      });	
	}

}
