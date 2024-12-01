import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/*
 *  Here is the "server", where connections to clients are established
 *  For the actual recieving (reading and writing) of the file, we will
 *      call the recieveFile method from the RecieveFile class
 */

 
public class FileUploadServer {
    
    // java -cp classes FileUploadServer 3000
    public static void main(String[] args) throws IOException {

        // Define the port
        int port = Integer.parseInt(args[0]);
        
        // Open the port
        ServerSocket server = new ServerSocket(port);
        System.out.printf("File upload server listening on port %d\n", port);

        while (true) {

            // Create server's socket and accept connections
            Socket serverConn = server.accept();

            // Recieve the file
            RecieveFile recieveFile = new RecieveFile();
            recieveFile.receiveFile(serverConn);

            // Close the connection
            serverConn.close();

        }
        


    }
}
