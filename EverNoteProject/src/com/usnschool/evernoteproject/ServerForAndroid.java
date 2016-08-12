package com.usnschool.evernoteproject;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerForAndroid {

	public static void main(String[] args) {
		ServerExe servermain = new ServerExe();
		servermain.start();

	}



}

class ServerExe extends Thread{
	ServerSocket server;
	Socket socket ;
	
	public ServerExe()  {

			try {
				server = new ServerSocket(7777);			
	/*			byte[] buffer = new byte[1024];
				int length = 0;
				FileOutputStream fos = new FileOutputStream("evernote.dat");
				while((length = ois.read(buffer)) >0){
					fos.write(buffer, 0, length);
					fos.flush();
				}*/

			} catch (Exception e) {
				e.printStackTrace();
			}
			

		
	}
	
	@Override
	public void run() {
	System.out.println("소켓시작");
		while(true){
			try {
				System.out.println("연결을 기다리고있습니다.");
				socket = server.accept();
				System.out.println("connected");
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				String protocol = ois.readUTF();
				
				if(protocol.equals("1")){
					//HashMap<String, Object> hm = (HashMap<String, Object>)(ois.readObject());
					ArrayList arrsend = (ArrayList)ois.readObject();
					FileOutputStream fos = new FileOutputStream("evernote.dat");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(arrsend);
					oos.flush();
					oos.close();
					fos.close();
					System.out.println("file creating complete");
				}else if(protocol.equals("2")){
					FileInputStream fis = new FileInputStream("evernote.dat");
					ObjectInputStream ois2 = new ObjectInputStream(fis);
					
					//HashMap<String, Object> hm = (HashMap<String,Object>)(ois2.readObject());
					ArrayList arrsend = (ArrayList)ois2.readObject();
					
					OutputStream os = socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					

					oos.writeObject(arrsend);
					oos.flush();
			
					System.out.println("file creating complete2");
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		}


}
}