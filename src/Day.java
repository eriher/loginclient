

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Day implements Serializable{
	

		/**
	 * 
	 */
	private static final long serialVersionUID = -8592734446181177749L;
		public int day;
		public int week;
		public String starttid 	= "";
		public String sluttid 	= "";
		public String checkin 	= "";
		public String checkout 	= "";
		
		public Day(int x, int y) {
			week = x;
			day = y;
		}
		
		public void setStart(String starttid){
			this.starttid = starttid;
		}
		public void setSlut(String sluttid){
			this.sluttid = sluttid;
		}
		public void setCheckIn(){
			String  x = getTime();
			this.checkin = x;
		}
		public void setCheckOut(){
			String  x = getTime();
			this.checkout = x;
		}
		public String day(){
			return Integer.toString(day);
		}
		public String week(){
			return Integer.toString(week);
		}
		public String getStart(){
			return starttid;
		}
		public String getSlut(){
			return starttid;
		}
		public String getCheckIn()
		{
			return checkin;
		}
		public String getCheckOut()
		{
			return checkout;
		}
		private String getTime()
		{
			DateFormat dayFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date();
			return dayFormat.format(date);
		}
}


