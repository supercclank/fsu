import java.io.*;
import java.net.*;

public class GameServer
{
   private BlackJackGame game;
   private int listenPort;
   public GameServer(int listenPort) {
      this.listenPort = listenPort;
   }

   public int getPort() {
      return listenPort;
   }

   public void startGame(int people, String ip) throws Exception{
      game = new BlackJackGame(people, ip);
   }

   public BlackJackGame getGame() {
      return game;
   }

   public static void main(String argv[]) throws Exception
      {
         GameServer server = new GameServer(6789);
         ServerSocket welcomeSocket = new ServerSocket(6789);
         boolean gameNotStarted = true;
         String clientRequest;
         BufferedReader inFromClient;       
         Socket connectionSocket = null;
         DataOutputStream outToClient = null;
         BlackJackGame game;
         String messageToClient;
         Hand  dealersTwo;
         BlackJackPlayer p;
         while(true)
         {
            while(gameNotStarted) {
               connectionSocket = welcomeSocket.accept();
               inFromClient =
                  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
               outToClient = new DataOutputStream(connectionSocket.getOutputStream());
               clientRequest = inFromClient.readLine();
               if (clientRequest.equals("start")) {
                  gameNotStarted = false;
               }
            }
            server.startGame(1,"a");
            game = server.getGame();
            p = new BlackJackPlayer("a", 1);
            game.addPlayer(p);
            game.firstStep();
            dealersTwo = p.getDealersTwo();
            messageToClient = Message.makeMessage(dealersTwo);
            outToClient.writeBytes(messageToClient + "\n");
            while(true) {
                inFromClient =
                  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
               outToClient = new DataOutputStream(connectionSocket.getOutputStream());
               clientRequest = inFromClient.readLine();
               if (clientRequest.equals("go")) {
                  messageToClient = Message.makeMessage(p.getHand());
                  outToClient.writeBytes(messageToClient + "\n");
                  break;
               }
            }
            while(!server.getGame().isOver()) {
               inFromClient =
                  new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
               outToClient = new DataOutputStream(connectionSocket.getOutputStream());
               clientRequest = inFromClient.readLine();
               if (clientRequest.equals("step")) {
                  game.stepGame();
                  messageToClient = Message.makeMessage(p.getHand());
                  outToClient.writeBytes(messageToClient + "\n");
                  if(!server.getGame().isOver()){
                    outToClient.writeBytes("continue" + "\n");
                  } 
               } else {
                  break;
               }
            }

            p.stick();
            messageToClient = "" + p.isWinner();
            outToClient.writeBytes(messageToClient + "\n");
            gameNotStarted = true;
         }
      }
}