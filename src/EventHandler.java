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
		user = komm.requestLogin(new String[]{"login",username,password});
		makenewframe();
	}


	private void makenewframe() {
		
		LinkedList<Day> week = user.getCurrentWeek();
		JFrame frame =  new JFrame("TestScreen");
		//frame.setPreferredSize(new Dimension(400,400));
		Container contentpane = frame.getContentPane();
		contentpane.setLayout(new BorderLayout());
		JPanel panel1 = new JPanel((new GridLayout(1,7)));
		JPanel panel2 = new JPanel((new GridLayout(1,7)));
		GregorianCalendar c = new GregorianCalendar(Locale.FRANCE);
		DateFormat dateFormat = new SimpleDateFormat("EEEE d/MM");
		for(int x = 0;x<7;x++){
			c.setWeekDate(2014, week.get(x).week, week.get(x).day);
			JLabel dag = new JLabel(dateFormat.format(c.getTime()));
			JLabel starttid = new JLabel(week.get(x).getStart());
			
			panel1.add(dag);
			panel2.add(starttid);
		}
		contentpane.add(panel1, BorderLayout.NORTH);
		contentpane.add(panel2, BorderLayout.CENTER);
		contentpane.setPreferredSize(new Dimension(700,100));
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		
	}

}
