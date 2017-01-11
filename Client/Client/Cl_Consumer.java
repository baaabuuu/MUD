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
				tag = inbound.substring(inbound.indexOf(":") + 1, inbound.indexOf(":"));
				inbound.replace(tag, "");
				
				if(tag.equals("ACT")){
					main.updEventArea(inbound);
				}else if(tag.equals("INV")){
					main.updList(inbound);
				}else if(tag.equals("CHAT")){
					main.updChatArea(inbound);
				}else if(tag.equals("LABEL")){
					main.updLabels(inbound);
				}
			}
		}
	}
}
