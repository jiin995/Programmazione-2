package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
	
	public static void main(String[] args) {

		try{
			byte buffer[]=new byte[10];
			DatagramPacket pkt=new DatagramPacket(buffer, buffer.length);
			
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
			
			DatagramSocket socket=new DatagramSocket(23233);
			
			pkt.setAddress(InetAddress.getLocalHost());
			pkt.setPort(32333);
			
			String s=in.readLine();
			pkt.setData(s.getBytes());
			
			
			socket.send(pkt);
			
			DatagramPacket pk=new DatagramPacket(buffer, buffer.length);

			socket.receive(pk);
			System.out.println("Ricevuto messaggio da "+pk.getAddress()+"Port :"+pk.getPort());
			String msg=new String(pk.getData());
			System.out.println(msg);
			
			socket.close();
			
			
		}catch(SocketException s){
			s.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}


}
