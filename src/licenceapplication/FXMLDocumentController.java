/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package licenceapplication;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author gharbi abdelillah
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private TextField addressMAC;
    static String format = "%02X"; // To get 2 char output.
private static String[] getPhysicalAddress() throws Exception{
    try {
        // DHCP Enabled - InterfaceMetric
        Set<String> macs = new LinkedHashSet<String>();

        Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
        while( nis.hasMoreElements() ) {
            NetworkInterface ni = nis.nextElement();
            byte mac [] = ni.getHardwareAddress(); // Physical Address (MAC - Medium Access Control)
            if( mac != null ) {
                final StringBuilder macAddress = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    macAddress.append(String.format("%s"+format, (i == 0) ? "" : ":", mac[i]) );
                    //macAddress.append(String.format(format+"%s", mac[i], (i < mac.length - 1) ? ":" : ""));
                }
                System.out.println(macAddress.toString());
                macs.add( macAddress.toString() );
            }
        }
        return macs.toArray( new String[0] );
    } catch( Exception ex ) {
        System.err.println( "Exception:: " + ex.getMessage() );
        ex.printStackTrace();
    }
    return new String[0];
}
    @FXML
    private Label lblCopier;
    
   
    @FXML
    private void getMAC(ActionEvent event) throws Exception {
     InetAddress localHost = InetAddress.getLocalHost();
    System.out.println("Host/System Name : "+ localHost.getHostName());
    System.out.println("Host IP Address  : "+ localHost.getHostAddress());

    String macs2 [] = getPhysicalAddress();
   
    for( String mac : macs2 ){
         addressMAC.setText(addressMAC.getText()+""
                + ""+mac);
        
    }   
    }

    @FXML
    private void copier(ActionEvent event) {
        StringSelection stringSelection = new StringSelection(addressMAC.getText());
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
        lblCopier.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
