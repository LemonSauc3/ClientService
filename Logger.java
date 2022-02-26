/*
* FILE : Logger.java
* PROJECT : SENG2040 - Assignment #3
* PROGRAMMER : Riley Boyd, Travis Fiander
* FIRST VERSION : 2022-02-15
* DESCRIPTION : The functions in this class allow a connection to be made to the server that is writen in C#
*       Which initializes the connection and allows the main program, to communicate any errors to the server
*       to be logged, so that debugging can be easier.
*/

// Imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/*
* NAME : Logger
* PURPOSE : This class connects to the server, so that the server can Log. This class will send the msg statement,
*       which includes the severity of the log, as well as what is happening. There is no main program, so you 
*       have to attach this to another class with a main function to be able to run the program.
*/
public class Logger {
    
    // Private Data Members
    private String IP;
    private int PORT;
    private Socket sock;
    private boolean connected;

    /* 
    * Fuction: logger(String IPAddy, int Port)
    * Description: Constructor for the class, sets up the connection to the server
    * Parameters: String IPAddy, int Port
    * Return: None
    */
    public Logger(String IPAddy, int Port) throws IOException {
        // Constructor to initialize components of the logger
        this.setIP(IPAddy);
        this.setPORT(Port);
        connected = Connection();
        if (!connected) {
            System.out.println("Connection not aquired\n");
            finalize();
        }
    }

    /* 
    * Fuction: finalize()
    * Description: overloaded function to call the destructor (garbage collector) to destroy the connection so that
    *       the server does not hand or crash
    * Parameters: 
    * Return: void
    */
    public void finalize() throws IOException {
        // Cleaning up and lose ends, and closing the socket
        this.sock.close();
    }

    /* 
    * Fuction: Connection()
    * Description: This initializes the connection to the server.
    * Parameters: None
    * Return: boolean
    */
    public boolean Connection() {
        // Initializing the connection to the server
        boolean success = true;
        try {
            sock = new Socket(getIP(), getPORT());
        }
        catch (Exception e) {
            success = false;
        }
        return success;
    }

    /* 
    * Fuction: Log(String alert, String msg)
    * Description: This file will use the PrintWriter to send the msg to the server, based on the connection
    *       so that the log can be stamped on the server end and put in the log file
    * Parameters: String alert, String msg
    * Return: void
    */
    public void Log(String alert, String msg) throws IOException {
        // Call the connection and send the combined string to the logger
        String data = "[ " + alert + " ] " + msg;
        PrintWriter pw = new PrintWriter(this.sock.getOutputStream(), true);
        pw.println(data);
        pw.flush();

    }

    // Getters and Setters for the IP and PORT
    public int getPORT() {
        return PORT;
    }

    public void setPORT(int pORT) {
        this.PORT = pORT;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String iP) {
        this.IP = iP;
    }
}
