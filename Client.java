import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author iKaz71 Santos Chris
 */
public class Client {
    public static void main(String[] args) { 
        try{
            Socket client = new Socket("localhost",12345);
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            
            String hora = "16:00:00";
            String msg = "La Hora del servidor Es la misma del pc en el que se ejcuta el Servidor";
            long t0 = System.currentTimeMillis();
            System.out.println("Ordenar");
            out.writeUTF(msg);
            long serverTime = in.readLong();
            Random rand= new Random();
            Thread.sleep(rand.nextInt(4000));
            long t1 = System.currentTimeMillis();
            System.out.println("-----------Respuesta----------------");
            long diff = t1 - t0;
            long time = serverTime + (diff/2);
           
            DateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
            String send = formatHora.format(new Date(t0));
            String servidor = formatHora.format(new Date(serverTime));
            String recive = formatHora.format(new Date(t1));
            String nova = formatHora.format(new Date(time));
            
            DateFormat formatMin = new SimpleDateFormat("mm:ss");
            System.out.println("Tiempo de Solicitud --> "+ send);
            System.out.println("Tiempo de Respuesta --> " + recive);
            System.out.println("Hora del Sevidor    --> "+ servidor);
            System.out.println("Tiempo del Algoritmo--> " + nova);
            System.out.println("Diferencia de Tiempo--> " + formatMin.format(new Date(diff)));

            in.close();
            System.out.println("Conección Finalizada");
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
