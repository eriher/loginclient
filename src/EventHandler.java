

public class EventHandler {
	
	public Login user;
	private static Kommunikation komm;
	
	public EventHandler()
	{
		komm = new Kommunikation();
	}
	
	
	public void test(String username, String password)
	{
		komm.requestLogin(new String[]{"login",username,password});
	}

}
