// SOAP Client created by Edward Sum Lok Yu
// Trying to base off of SOAPClientExample.java
import javax.xml.soap.*;

public class SOAPClient {

    public static void HandleSOAPResponse(SOAPRequest soapRequest, String soapServerURL) throws Exception {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        SOAPMessage soapResponse = soapConnection.call(soapRequest, soapServerURL);
        System.out.print("Response SOAP Message: ");
        soapMessage.writeTo(System.out);

        soapConnection.close();
    }
}