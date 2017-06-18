package controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import sensor.ObserverSensor;

public class SensorProxy implements ObserverSensor {
	
	private int port;
	private int sensorId;
	
	public SensorProxy(int sid,int p){
		port=p;
		sensorId=sid;
	}

	public void notifyReading(int temp){
		try{
			
			Socket socket=new Socket("127.0.0.1",port);
			
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			
			out.writeInt(temp);
			out.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	

}
