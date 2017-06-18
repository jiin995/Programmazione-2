//interfaccia necessaria affinche' client e server possano avere un contratto comune

package Dispatcher;

public interface IDispatcher {
	
	
	public void sendCmd(int cmd);
	
	public int getCmd();

	public void close();
}
