package Client;

public class Cl_Consumer extends Thread {
	private String inbound;
	private String tag;
	private Cl_Main main;
	private Cl_Transmit transmit;
	
	public Cl_Consumer(Cl_Main main, Cl_Transmit transmit){
		this.main = main;
		this.transmit = transmit;
	}
	public void run(){
		while(true){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			inbound = transmit.takeFromQueue();
			if(inbound != ""){
				System.out.println("Tagged info: " + inbound);
				tag = inbound.substring(0,5);
				inbound = inbound.replace(tag, "");
				System.out.println("Tag: " + tag);
				System.out.println("info: " + inbound);
				
				if(tag.equals(":DAT:")){
					main.login.updHashed(inbound);
				}else if(tag.equals(":ACT:")){
					main.updEventArea(inbound);
				}else if(tag.equals(":INV:")){
					main.updList(inbound);
				}else if(tag.equals(":CHA:")){
					main.updChatArea(inbound);
				}else if(tag.equals(":LAB:")){
					main.updLabels(inbound);
				}
			}
		}
	}
}
