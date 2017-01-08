/**
 * 
 */
package raspi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author joelmanning
 *
 */
public class Communications extends Thread {
	
	private static String HOST = "10.10.72.4";
	private static int PORT = 6000;
	
	private Socket s;
	private OutputStream out;
	private InputStream in;
	private BufferedReader br;
	private PrintWriter pw;
	
	public Communications(){
		try {
			s = new Socket(HOST, PORT);
			out = s.getOutputStream();
			in = s.getInputStream();
			br = new BufferedReader(new InputStreamReader(in));
			pw = new PrintWriter(out);
		} catch(UnknownHostException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		super.run();
		pw.println("Hello World");
		pw.flush();
		System.out.println("Sent Hello World");
		while(true){
			try {
				String line = br.readLine();
				if(line != null){
					System.out.println(line);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
