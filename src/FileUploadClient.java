/*
 *  Here is the "client", where connections to the server is made
 *  The for the actual sending (reading and writing) of the file, we will
 *      call the sendFile method from the SendFile class
*/

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileUploadClient {
    
    // java -cp classes FileUpload localhost:3000 files/polarbear.jpg
    public static void main(String[] args) throws UnknownHostException, IOException {

    // Define your localhost and port, from terminal input when file is run
    String[] terms = args[0].split(":");
    String host = terms[0];
    int port = Integer.parseInt(terms[1]);

    

    // Read the file and get details
    File file = new File(args[1]);                                     // Create file object with directory provided during program run
    if (!(file.exists() || file.isFile())) {                              // If file doesn't exist or is not a file
        System.err.printf("%s - Invalid file", args[1]);     // Error
        System.exit(-1);                                            // Method to terminate a running program. "-1" indicates that an error/abnormal termination occured
    }

    String fileName = file.getName();
    long fileSize = file.length();

    System.out.printf("Uploading file %s of size %d bytes", fileName, fileSize);



    // Create the client's socket
    Socket clientSoc = new Socket(host, port);

    // SendFile class instantiated, call upload method. Provide socket and file to the method
    SendFile sendFile = new SendFile();
    sendFile.upload(clientSoc, file);


    // Close the socket
    clientSoc.close();


    }

}
