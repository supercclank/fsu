import java.io.*;
import java.net.*;
import java.util.Scanner;
public class GameClient2
{
  private String ip;
  private int port;
  private Hand dealersShownCard;
  private Hand playerHand;
  private Scanner s;
  public GameClient2(String ip, int port) {
    this.ip = ip;
    this.port = port;
    s = new Scanner(System.in);
  }

  public String getIp() {
    return ip;
  }

  public int getPort() {
    return port;
  }

  public void setDealers(Hand h) {
    dealersShownCard = h;
  }

  public void setPlayerHand(Hand h) {
    playerHand = h;
  }

  public Hand getPlayerHand() {
    return playerHand;
  }

  public Hand getDealerHand() {
    return dealersShownCard;
  }

  public String prompt() {
    return s.nextLine();
  }

  public void setPort(int port) {
    this.port = port;
  }

 public static void runClient() throws Exception {
      String command = "start";
      String encodedPlayersHand = "";
      String encodedDealerHand = "";
      GameClient client = new GameClient("localhost", 6789);
      String result = "";
      Socket clientSocket = new Socket(client.getIp(), client.getPort());
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      client.setPort(Integer.parseInt(inFromServer.readLine()));
      
      System.out.println(client.getPort());
      clientSocket = new Socket(client.getIp(), client.getPort());
      
      outToServer = new DataOutputStream(clientSocket.getOutputStream());
      inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      


      if(client.getPort() == 6790) {
              outToServer.writeBytes(client.prompt() + "\n");  
      }

      outToServer.writeBytes(command + "\n");
      encodedDealerHand = inFromServer.readLine();
      client.setDealers(Message.unpackMessage(encodedDealerHand));
      System.out.println(encodedDealerHand);
      //good up to here
      command = "go";
      outToServer.writeBytes(command + "\n");
      encodedPlayersHand = inFromServer.readLine();
      System.out.println(encodedPlayersHand);
      client.setPlayerHand(Message.unpackMessage(encodedPlayersHand));
      while(true) {
        command = client.prompt();
        System.out.println(command);
        outToServer.writeBytes(command + "\n");
        if(command.equals("step")) {
          encodedPlayersHand = inFromServer.readLine();
          System.out.println(encodedPlayersHand);
          result = inFromServer.readLine();
          System.out.println(result);
          if (!result.equals("continue")) {
            break;
          }
        } else {
          result = inFromServer.readLine();
          break;
        }
      }
      clientSocket.close();
    }  


public static void main(String argv[]) throws Exception
    {
      runClient();  
    }

}