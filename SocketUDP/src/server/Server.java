package server;

import java.io.IOException;
import java.net.*;

public class Server {
	
	public static void main(String[] args) {

		try{
			byte []buffer=new byte[10];
			DatagramPacket pkt=new DatagramPacket(buffer, buffer.length);
			
			DatagramSocket socket=new DatagramSocket(32333);
			
			socket.receive(pkt);
			
			System.out.println("Ricevuto messaggio da "+pkt.getAddress()+" Port :"+pkt.getPort());
			String msg=new String(pkt.getData());
			System.out.println(msg+"  "+pkt.getData().length);
		
			DatagramPacket pk=new DatagramPacket(buffer, buffer.length);

			pk.setAddress(pkt.getAddress());
			pk.setPort(pkt.getPort());
			
			pk.setData("ciao".getBytes());
			
			socket.send(pk);
			
			socket.close();
			
			
		}catch(SocketException s){
			s.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
