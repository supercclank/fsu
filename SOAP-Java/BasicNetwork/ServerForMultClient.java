// code adapted from http://www.cs.indiana.edu/classes/a348/CTED/moduleFour/lectures/Apr22.html

import java.net.*; 
import java.io.*; 
 
 class ServerForMultClient {
  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(49900, 10); 
      while (true) {
        process(serverSocket.accept()); 
      } 
      // serverSocket.close(); 
    } catch (Exception e) { 
      System.out.println("Error in main: " + e); 
    } 
  } 
  static void process(Socket incoming) { 
    Operator operator = new Operator(incoming); 
    operator.start(); 
  } 
} 
 
class Operator extends Thread { 
  Socket incoming; 
  Operator(Socket call) { 
    incoming = call; 
  } 
  public void run() { 
    try { 
      BufferedReader in  = new BufferedReader(
                             new InputStreamReader(
                               incoming.getInputStream())); 
 
      PrintWriter    out =   new PrintWriter(
                                   incoming.getOutputStream()); 
 
      out.println("Hello, this is Echo.\nEnter BYE to exit."); 
      out.flush();  
 
      boolean done = false; 
      while (!done) {
        String str = in.readLine(); 
        out.println("Echo: " + str); 
        out.flush(); 
        if (str.trim().equals("BYE")) 
          done = true; 
      } 
      incoming.close(); 
    } catch (Exception e) { 
      System.out.println("Error in process: " + e); 
    } 
  }
}