using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;
using System.IO;
using System.Threading;
using System.Windows.Forms;
using System.Web;

namespace ConsoleApplication1
{
    class Program
    {
        private StreamWriter swSender;
        private StreamReader srReceiver;
        private TcpClient tcpServer;
        private Thread thrMessaging;
        private IPAddress ipAddr;
        private bool Connected;
        private void InitializeConnection()
        {
            // Parse the IP address

            string ipAdress = "localhost";
            ipAddr = IPAddress.Parse(ipAdress);


            // Start a new TCP connections to the chat server
            tcpServer = new TcpClient();
            try
            {
                tcpServer.Connect(ipAddr, 2002);
                swSender = new StreamWriter(tcpServer.GetStream());


                // Start the thread for receiving messages and further communication
                thrMessaging = new Thread(new ThreadStart(ReceiveMessages));
                thrMessaging.Start();
                Connected = true;
            }
            catch (Exception e2)
            {
                MessageBox.Show(e2.ToString());
            }
        }

        private void ReceiveMessages()
        {
            // Receive the response from the server
            srReceiver = new StreamReader(tcpServer.GetStream());
            while (Connected)
            {
                String con = srReceiver.ReadLine();
                string StringMessage = HttpUtility.UrlDecode(con, System.Text.Encoding.UTF8);

                processMessage(StringMessage);



            }
        }

        private void processMessage(String p)
        {
            // do something with the message
        }

        private void SendMessage(String p)
        {
            if (p != "")
            {
                p = HttpUtility.UrlEncode(p, System.Text.Encoding.UTF8);
                swSender.WriteLine(p);
                swSender.Flush();

            }

        }

    }
}
