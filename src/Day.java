

import java.io.Serializable;

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
		
		public void setStartTime(String starttid){
			this.starttid = starttid;
		}
		public void setSlutTid(String sluttid){
			this.sluttid = sluttid;
		}
		public void setCheckIn(String checkin){
			this.checkin = checkin;
		}
		public void setCheckOut(String checkout){
			this.checkout = checkout;
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

}


