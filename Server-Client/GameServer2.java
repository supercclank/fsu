import java.io.*;
import java.net.*;
public class GameServer2
{
   private BlackJackGame game;
   private ServerSocket serverSockets[];
   private Socket clientSockets[];
   private BufferedReader[] inFromClient;
   private String[] clientRequest; 
   private DataOutputStream outToClient[];
   private int[] sockNums;
   private int startingSocket = 6789;
   private int numPlayers;
   private GameServer2 server;
   private BlackJackPlayer[] players;
   private String messageToClient;
   private Hand  dealersTwo;
   public GameServer2(int listenPort, int players, Socket clientSocket, ServerSocket serverSocket) throws Exception{
      serverSockets = new ServerSocket[players];
      clientSockets = new Socket[players];
      sockNums = new int[players];
      inFromClient = new BufferedReader[players];
      clientRequest = new String[players];
      outToClient = new DataOutputStream[players];
      serverSockets[0] = serverSocket;
      clientSockets[0] = clientSocket;
      sockNums[0] = listenPort + 1;
      for(int x = 1; x < players; x++ ) {
         serverSockets[x] = new ServerSocket(1 + x + listenPort);
         sockNums[x] = 1 + x + listenPort;
      }

   }

   public GameServer2(){

   }

   public void nuke() {
      game = null;
      serverSockets = null;
      clientSockets = null;
      inFromClient = null;
      clientRequest = null; 
      outToClient = null;
      sockNums = null;
   }

   public ServerSocket[] getSockets() {
      return serverSockets;
   }

   public int[] getSocketsNums() {
      return sockNums;
   }

   public Socket[] getClientSockets() {
      return clientSockets;
   }

   public void startGame(int people, String ip) throws Exception{
      game = new BlackJackGame(people, ip);
   }

   public BlackJackGame getGame() {
      return game;
   }

   public void initializeBuffers() throws Exception{
      for (int x = 0; x < inFromClient.length; x++) {
         inFromClient[x] = new BufferedReader(new InputStreamReader(clientSockets[x].getInputStream()));
         clientRequest[x] = "";
         outToClient[x] = new DataOutputStream(clientSockets[x].getOutputStream()); 
      }
   }

   public DataOutputStream[] outToClients() {
      return outToClient;
   }

   public BufferedReader[] inFromClients() {
      return inFromClient;
   }

   public String[] getRequests() {
      return clientRequest;
   }

   public void closeSockets() throws Exception {
      for(int x = 0; x < serverSockets.length; x++ ) {
         serverSockets[x].close();
         clientSockets[x].shutdownOutput();
      }
   }

   public void runServer() throws Exception{
         
           
            ServerSocket welcomeSocket = new ServerSocket(startingSocket);
            Socket connectionSocket = welcomeSocket.accept();
            
            BufferedReader inFromPlayer1 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));  
            DataOutputStream outToPlayer1 = new DataOutputStream(connectionSocket.getOutputStream());
            outToPlayer1.writeBytes((startingSocket + 1) + "\n");

            welcomeSocket.close();
            connectionSocket.close();

            
            welcomeSocket = new ServerSocket(startingSocket + 1);
            connectionSocket = welcomeSocket.accept();

            inFromPlayer1 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));;  
            outToPlayer1 = new DataOutputStream(connectionSocket.getOutputStream());


            String clientRequestPlayer1 = inFromPlayer1.readLine();
            numPlayers = Integer.parseInt(clientRequestPlayer1);
            server = new GameServer2(6789,numPlayers, connectionSocket, welcomeSocket);
            int numLeft = numPlayers;
            for(int x = 1; x < numLeft; x++) {
               welcomeSocket = new ServerSocket(startingSocket);
               connectionSocket = welcomeSocket.accept();
               inFromPlayer1 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));;  
               outToPlayer1 = new DataOutputStream(connectionSocket.getOutputStream());
               outToPlayer1.writeBytes(server.getSocketsNums()[x] + "\n");
               server.getClientSockets()[x] = server.getSockets()[x].accept();
               connectionSocket.close();
               welcomeSocket.close();
            }
            server.initializeBuffers();
            server.startGame(numPlayers,"a");
            game = server.getGame();
            players = new BlackJackPlayer[numPlayers];
            for(int x = 0; x < players.length; x++) {
               players[x] = new BlackJackPlayer(""+x, x + 1);
               game.addPlayer(players[x]);
            }
            game.firstStep();
            dealersTwo = players[0].getDealersTwo();
            messageToClient = Message.makeMessage(dealersTwo);
            server.initializeBuffers();
            for(int x = 0; x < numPlayers; x++) {
               server.outToClients()[x].writeBytes(messageToClient + "\n");
            }
            //after everyone got dealers hand
               server.initializeBuffers();
               for(int x = 0; x < numPlayers; x++) {
                  server.getRequests()[x] = server.inFromClients()[x].readLine();
               }
               server.initializeBuffers();
               for(int x = 0; x < numPlayers; x++) {
                  messageToClient = Message.makeMessage(((BlackJackPlayer)game.getPlayers()[x + 1]).getHand());
                  server.outToClients()[x].writeBytes(messageToClient + "\n");
               } 
            //this is funky fix probably
            while(!server.getGame().isOver()) {
               server.initializeBuffers();
               for(int x = 0; x < numPlayers; x++) {
                  server.getRequests()[x] = server.inFromClients()[x].readLine();
               }
               for(int x = 0; x < numPlayers; x++) {
                  if (!(server.getRequests()[x].equals("step"))) {
                     players[x].stick();
                  }
               }
               game.stepGame();
               server.initializeBuffers();
               for(int x = 0; x < numPlayers; x++) {
                  messageToClient = Message.makeMessage(((BlackJackPlayer)game.getPlayers()[x + 1]).getHand());
                  server.outToClients()[x].writeBytes(messageToClient + "\n");
               }
               server.initializeBuffers();
               if(!server.getGame().isOver()) {
                  for(int x =0; x < numPlayers; x++) {
                     server.outToClients()[x].writeBytes("continue" + "\n");
                  }
               } else {
                  for(int x =0; x < numPlayers; x++) {
                     server.outToClients()[x].writeBytes("stop" + "\n");
                  }
               }
            }

            for(int x = 0; x < numPlayers; x++) {
                  messageToClient = Message.makeMessage(((BlackJackPlayer)game.getPlayers()[x + 1]).getHand());
                  server.outToClients()[x].writeBytes(messageToClient + "\n");
            }
            server.initializeBuffers();
            for(int x = 0; x < numPlayers; x++) {
                messageToClient = "" + Message.makeMessage(((ComputerBlackJackPlayer)(game.getPlayers())[0]).getHand());
                server.outToClients()[x].writeBytes(messageToClient + "\n");
            }
            for(int x = 0; x < numPlayers; x++) {
               messageToClient = "" + players[x].isWinner();
               server.outToClients()[x].writeBytes(messageToClient + "\n");
            }
          server.closeSockets();
          server.nuke();
          System.out.println("NUKED");

   }
   public static void main(String argv[]) throws Exception
      {
         while(true) {
                     GameServer2 start = new GameServer2();
                     start.runServer();
                 }
      }
}  