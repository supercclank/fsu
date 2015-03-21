// code adapted from http://www.cs.indiana.edu/classes/a348/CTED/moduleFour/lectures/Apr22.html

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            // assuming either Server or ServerForMultClient is run on
            Socket con = new Socket("127.0.0.1", 49900);                    
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            PrintWriter out = new PrintWriter(con.getOutputStream(), true);
            for (int i = 0; i < 10; i++) {
                System.out.println(in.readLine());
                out.println("I am " + args[0] + " " + i);
                out.flush();
                Thread.sleep(1000);
            }
            out.println("BYE");
            out.flush();
        } catch (Exception e) {
            System.out.println("E: " + e);
        }
    }                                
}