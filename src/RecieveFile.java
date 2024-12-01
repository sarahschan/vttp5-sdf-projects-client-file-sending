import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class RecieveFile {
    
    public void receiveFile(Socket serverConn) throws IOException {

        // Input stream to recieve the file
        InputStream is = serverConn.getInputStream();
        DataInputStream dis = new DataInputStream(is);


        // Get name and size of the file
        String fileName = dis.readUTF();
        long fileSize = dis.readLong();


        // Redirect file to upload location
        String filePath = "uploads" + File.separator + fileName;

        // Output stream to write the file
        FileOutputStream fos = new FileOutputStream(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);


        // Create your 4k buffer and write
        byte[] buff = new byte[1024 * 4];
        int readBytes = 0;
        int uploadedBytes = 0;
        while ((readBytes = dis.read(buff)) > 0 ) {
            bos.write(buff, 0, readBytes);
        }

        // while (uploadedBytes < fileSize) {
        //     readBytes = dis.read(buff);
        //     bos.write(buff, 0, readBytes);

        //     uploadedBytes += readBytes;
        // }

    }
    
}
