// adapted from http://www.cs.indiana.edu/classes/a348/CTED/moduleFour/lectures/Apr22.html

import java.net.*;                                              // for networking  
import java.io.*;                                               // for I/O  
 
class Server {                                                 
  public static void main(String[] args) {                        
    try {                                                        
      ServerSocket serverSocket = new ServerSocket(49900, 10);  // create a ServerSocket on port 49900  
      while (true) {
        process(serverSocket.accept());                         // process incoming call when it comes 
      }
      //serverSocket.close();                                     // close the ServerSocket  
    } catch (Exception e) { 
      System.out.println("Error in main: " + e);                // report the Exception  
    } 
  } 

  static void process(Socket incoming) {                        // process function receives an incoming  
    try {                                                       // Socket object that manages the call  
      BufferedReader in  = new BufferedReader(
                             new InputStreamReader(
                                   incoming.getInputStream())); // now I can read from it through in  

      PrintWriter    out =   new PrintWriter(
                                   incoming.getOutputStream()); // and I can write to it through out  
 
      out.println("Hello, this is Echo.\nEnter BYE to exit.");  // initial greeting and briefing  
      out.flush();                                              // make it appear immediately (flush the buffer)  
 
      boolean done = false;                                     // conversation is only starting  
      while (!done) {                                           // while we're not finished  
        String str = in.readLine();                             // get a line from the remote client  
        out.println("Echo: " + str);                            // send it back immediately preceded by Echo:  
        out.flush();                                            // make it appear immediately (flush the buffer)  
        if (str.trim().equals("BYE"))                           // then check the line from the user: if BYE then  
          done = true;                                          // we're done  
      }                                                         // and that's our dialoop  
      incoming.close();                                         // when done hang up  
    } catch (Exception e) {                                     // catch the Exception  
      System.out.println("Error in process: " + e);             // report the error  
    } 
  } 
} 