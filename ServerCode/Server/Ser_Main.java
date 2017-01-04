package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException{
		//Initialize initial server objects
		ServerSocket srv = null;
		//Try using socket 8080 and set boolean serverActive true
		try {
			srv = new ServerSocket(8080);
		} catch (IOException e) {
			System.out.println("Socket error!");
			System.exit(0);
		}
		int n = 0;
		Socket s = null;
		while(true){
			try{
				s = srv.accept();
			}catch(IOException ex){
				System.out.println("Error accepting from socket");
				System.exit(0);
			}
			Game g = new Game(s, n);
			n++;
		}
	}
}