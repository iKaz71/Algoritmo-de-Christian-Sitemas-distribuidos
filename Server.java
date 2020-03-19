import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 *
 * @author iKaz71 Santos Chris
 */
public class Server {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket server = new ServerSocket(12345);
            System.out.println("El Servidor se Encuentra en el Puerto 12345");
            while(true){
                Socket client = server.accept();    
                DataInputStream in = new DataInputStream(client.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                Random rand= new Random();
                Thread.sleep(rand.nextInt(4000));
                out.writeLong(System.currentTimeMillis());
                out.flush();
                out.close();
                client.close();
            }
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
