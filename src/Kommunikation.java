

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
	private static ObjectInputStream in;
	private static ObjectOutputStream out;
	
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
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void logout()
	{
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Object communicate(Object[] cmnd)
	{
		Object result = null;
		try {
			out.writeObject(cmnd);
			try {
				result = in.readObject();
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
}
