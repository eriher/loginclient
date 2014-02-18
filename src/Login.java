

import java.io.Serializable;

public class Login implements Serializable{
	private static final long serialVersionUID = 8242531434630342834L;
	private String username;
	private String password;
	
	public Login(String name, String pass)
	{
		username = name;
		password = pass;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}	
}