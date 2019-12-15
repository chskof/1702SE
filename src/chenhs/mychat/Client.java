package chenhs.mychat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * �����ҿͻ���
 * 
 * @author chs
 */
public class Client {
	/*
	 * Socket��װ��TCPͨѶЭ��
	 * 
	 */
	private Socket socket;

	/*
	 * ���췽����������ʼ���ͻ���
	 * 
	 * @throws IOExpection
	 * 
	 * @throws UnkonwnHostException
	 */
	public Client() throws UnknownHostException, IOException {
		/*
		 * ʵ����Socket�Ĺ��̾�������Զ�˼�����Ĺ��̣���Ҫ�������������� ����1�� ����˵�IP��ַ��Ϣ ����2�� ����˵Ķ˿�
		 * ͨ��IP�����ҵ�����˵ļ������ͨ���˿ڿ������ӵ������ڷ���� ������ϵ����ó���
		 */
		System.out.println("�������ӷ�����������");

		socket = new Socket("localhost", 8088);

		System.out.println("�����ӷ���ˣ�");
	}

	/**
	 * �ͻ��˿�ʼ�����ķ���
	 */
	public void start() {

		try {
			// ����������ȡ����˷�������Ϣ�� �߳�
			Runnable handler = new ServerHandler();
			Thread t = new Thread(handler);
			t.start();
			
			Scanner scan = new Scanner(System.in);

			/*
			 * Socket�ṩ������ OutputStream getOutputStream();
			 * �÷������Ի�ȡһ���������ͨ�������д�������ݻᷢ�͵�Զ�˼����
			 */

			//Ϊ�˸�Զ�˷�����Ϣ ��Socket��ȡһ����Զ�˷�����Ϣ������� 
			//��װ��һ������ָ��������ַ������ Ȼ���ٰ�װ��һ�����Զ�ˢ�µ�PrintWriter
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			String nickName = scan.nextLine();
			pw.write(nickName);

			// ���һ�η�����Ϣ��ʱ��
			long last = System.currentTimeMillis();

			while (true) {
				String message = scan.nextLine();
				if (System.currentTimeMillis() - last >= 1000) {
					pw.println(message);
					last = System.currentTimeMillis();
				} else {
					System.out.println("������Ϣ�ٶȹ��죡");
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Client client = new Client();
			client.start();
		} catch (Exception e) {
			System.out.println("�ͻ�������ʧ�ܣ�");
			e.printStackTrace();
		}
	}

	/**
	 * ���ڲ��ഴ��һ���̣߳����߳�����ѭ����ȡ����˷��� ������ÿһ����Ϣ�������������̨
	 */
	private class ServerHandler implements Runnable {

		public void run() {
			try {
				//  Ϊ�˶�ȡ����˷���������Ϣ ���ⲿ���socket��ȡ������
				//  Ȼ���װ���ַ������� �ٰ�װ�����Խ�����ַ����������� 
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

				String message = null;
				while ((message = br.readLine()) != null) {
					System.out.println(message);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}