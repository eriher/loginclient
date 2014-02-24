
/**
 * Sets up connection to server and communicates.
 * 
 * @author Erik Hermansson
 * @version 2014-02-21
 */
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

	/**
	 * initiates connection
	 */
	public void connect()
	{
		/*String ip;
		Integer port;
		FileManagement.getInstance().openReadFile("config.txt");
		String s = FileManagement.getInstance().readFile();
		String[] sArr = s.split("\\n");
		ip = String[0];
		port = String[1];*/
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
	/**
	 *		closes connections
	 */
	public void disconnect()
	{
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @param Object[] 
	 *            Includes command and information.
	 */
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
