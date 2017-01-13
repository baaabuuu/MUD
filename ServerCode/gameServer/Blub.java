package gameServer;

public class Blub {
	private static int port = 1234;
	public static void main(String argv[]) {
		
		//listener waits for incoming connections, then hands them off to the sender.
		Listener listener = new Listener("mainListener", port);
		listener.start();
		
	}
}