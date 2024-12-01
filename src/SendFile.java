import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendFile {

    public void upload(Socket clientSoc, File file) throws IOException {

        // Input stream to read the file
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        // Output stream to send the file
        OutputStream os = clientSoc.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);


        // Using DataOutputStream's methods, send the file name and size
        dos.writeUTF(file.getName());
        dos.writeLong(file.length());



        // Create your 4k buffer and write
        byte[] buff = new byte[1024 * 4];
        int bytesRead = 0;
        while ((bytesRead = bis.read(buff)) > 0) {
            dos.write(buff, 0, bytesRead);
        }
        
        // Flush and close
        dos.flush();
        dos.close();
    }
    
}
