package chenhs.mychat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * �����ҷ����
 * @author chs
 *
 */
public class Server {
	/*
	 * ���з���˵�ServerSocket��������Ҫ���ã�
	 * 1���������˿ڣ��ͻ��˾���ͨ���ö˿������˽������ӵ�
	 * 2����������ķ���˿ڣ�һ��һ���ͻ���ͨ���ö˿�����������
	 *    ServerSocket�ͻ���������һ��Socket��ÿͻ���ͨѶ
	 */
	
	private ServerSocket server;
	
	//�ü�������������пͻ��˵������ �Ա���Թ㲥��Ϣ
	private List<PrintWriter> allOut;
	
	//���췽�� ������ʼ���ͻ���
	public Server(){
		try {
			/* ��ʼ��ServerSocket��ͬʱ��ָ��Ҫ����ķ���˿ڣ����ö˿��Ѿ�
			 * ����������ռ�ã����׳��쳣
			 */
			server = new ServerSocket(8088);
			
			allOut = new ArrayList<PrintWriter>();
			
		} catch (IOException e) {
			System.out.println("�������˿ڿ��ܱ�ռ��!");
			e.printStackTrace();
		}
	}
	
	public void start(){
		try{
		/*
		 * �����˿ڣ��ȴ��ͻ�������
		 * ServerSocket��accept����
		 * ��һ�����������������Ǽ�������˿ڣ�ֱ��һ���ͻ���ͨ���ö˿����ӣ�
		 * �Ż᷵��һ��Socketʵ����ͨ�����Socket����������ӵĿͻ���ͨѶ
		 */
		while(true){
			System.out.println("�ȴ��ͻ������ӡ�����");
			
			Socket socket = server.accept();
			
			System.out.println("һ���ͻ��������ˣ�");
			
			//����һ���߳�������ͻ��˽���
			ClientHandler handler = new ClientHandler(socket);
			Thread t = new Thread(handler);
			t.start();
		}
	  }catch(Exception e){
		  e.printStackTrace();
	  }
		
	}
	
	
	public static void main(String[] args){
		try {
			Server server = new Server();
			server.start();
		} catch (Exception e) {
			System.out.println("���������ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
	
	// �����ڲ��� ���������߳� ���߳�������ָ���Ŀͻ��˽��н���
	private class ClientHandler implements Runnable{
		
		//��ǰ�߳�ͨ�����Socket��ָ���ͻ��˽���
		private Socket socket;
		
		//��ǰ�ͻ��� ��ip��ַ��Ϣ
		private String host;
		
		//����Ĺ��췽�� ��ʼ�� ÿһ���ͻ��˵�socket��ip��ַ
		public ClientHandler(Socket socket){
			//����Ҫ���յ�ÿһ���ͻ��� �������ʼ��
			this.socket = socket;
			
			//��ȡ�ͻ��˵�ip��ַ
			InetAddress address = socket.getInetAddress();
			host = address.getHostAddress();  //�õ�ip��ַ���ַ�����ʽ
		}
		
		public void run(){
			PrintWriter pw = null;
			try {
				System.out.println(host+"�����ˣ�");
				
				// Ϊ�˶�ȡ�ͻ��˵ķ�������Ϣ��ͨ��socket��ȡ��������
				// Ȼ���װ���ַ�����������ٰ�װ���ַ����������
				BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream(),"UTF-8"));
				
				// Ϊ�˽���Ϣ���͸��ͻ��ˣ�ͨ��socket��ȡ�������
				// Ȼ���װ�����ַ�������ַ���������ڰ�װ�������Զ�ˢ�»��������ַ����������
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
				
				
				//�Ѹÿͻ��˵���������빲����allOUt
				synchronized(allOut){
					allOut.add(pw);
				}
				 
				String message = null;
				
				/*��ȡ�ͻ��˷��͹�������Ϣbr.readLine() 
				 * �����ڿͻ��˶Ͽ�����ʱ���������еĽ��Ҳ��ͬ:
				 * linux�Ŀͻ��˶Ͽ�����ʱ���÷����᷵��nullֵ
				 * windows�Ŀͻ��Ͽ�����ʱ���÷�����ֱ���׳��쳣
				 */
				while((message = br.readLine())!= null){
//					System.out.println(host+"˵��"+message);
//					pw.println(host+"˵��"+message);
					synchronized(allOut){
						for(PrintWriter o :allOut){
							o.println(host+"˵��"+message);
						}
					}
				}
			} catch (Exception e) {
				
			}finally{
				//����ͻ��϶Ͽ����Ӻ�Ĳ���
				//���ÿͻ��ϵ�[�����]�ӹ�������ɾ��
				synchronized(allOut){
					allOut.remove(pw);
				}
				try {
					//�ر�Socket��Դ
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(host+"�����ˣ�");
			}
		}
	}
}
