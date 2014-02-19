

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Kommunikation 
{
	
	private Socket socket;
	private static ObjectInputStream inStream;
	private static ObjectOutputStream outStream;
	
	public Kommunikation()
	{
		connect();
	}

	private void connect()
	{
		InetAddress toAddr = null;
		try 
		{
			toAddr = InetAddress.getByName("127.0.0.1");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}
		try {
			socket = new Socket(toAddr,4444);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			outStream = new ObjectOutputStream(socket.getOutputStream());
			inStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public Object communicate(String[] user)
	{
		Object result = null;
		try {
			outStream.writeObject((String[]) user);
			try {
				result = inStream.readObject();
				}	catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
		}
		catch(SocketException e)
		{
			System.out.println(e.toString());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	/*
	public User requestLogin(String[] user)
	{
		Object result = null;
		try {
			outStream.writeObject((String[]) user);
			try {
				result = inStream.readObject();
				}	catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
		}
		catch(SocketException e)
		{
			System.out.println(e.toString());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		if(result == null)
			System.out.println("Wrong login info");
		return (User) result;
	}

	public Object requestCheckIn() {
		Object result = null;
		try {
			outStream.writeObject(new String[]{"checkin"});
			try {
				result = inStream.readObject();
				}	catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
		}
		catch(SocketException e)
		{
			System.out.println(e.toString());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return (Object) result;

	}
	*/
}
