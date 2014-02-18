

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class User implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1251343211799029911L;
	public Login login;
	public LinkedList<Day> week;
	public HashMap<Integer,LinkedList<Day>> schema;
	
	public User(String un, String pw)
	{
		schema = new HashMap<Integer, LinkedList<Day>>();
		login = new Login(un,pw);
		for(int x=1;x<53;x++){
			week = new LinkedList<Day>();
			for(int y=1;y<8;y++){
				Day dag = new Day(x,y);
				week.add(dag);	
			}
			schema.put(x, week);
		}
	}
	
	public Login getLogin()
	{
		return login;
	}
	
	public HashMap<Integer, LinkedList<Day>> getschema()
	{
		return schema;
	}
}
