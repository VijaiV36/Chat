// Name : Vijai Veeraragavan
// UTA ID: 1001670965
// NetID: vxv0965

// References:
// 1.https://www.geeksforgeeks.org/multi-threaded-chat-application-set-1/
// 2.https://www.geeksforgeeks.org/multi-threaded-chat-application-set-2/
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DSProject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import static DSProject.Server.username;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Vijai.V
 */
public class Server extends javax.swing.JFrame {
    static String username;// String to store the username
    static InetAddress ip;// Storing the Inet Address.
    static Socket soc;//Name of the Socket
    static DataOutputStream dos;//Name of the DataOutputStream
    static DataInputStream dis;//Name of the DataInputStream
    final static int ServerPort = 7654; // initializing the Server Port Number.

    
    static ServerSocket serversocket;// Name of the Server Socket
    static int i = 0; // used to iterate each client.
    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Stop Server");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null,"Server is gonna stop!");
        System.out.println("Server Shutdown");
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed
        static Vector<ClientHandler> ar = new Vector<>();// used to handle each and every client active.
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException{
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
       try{
        ArrayList<String> userd=new ArrayList<String>();// ArrayList to store the list of available users
        serversocket = new ServerSocket(ServerPort);// bind the socket
        while(true)
        {  
            soc = serversocket.accept();// The serversocket is now listening for connections 
            jTextArea1.append("\nNew client request received : " + soc); 
            jTextArea1.append(""+soc.getInetAddress());
            // obtain input and output streams 
            dis = new DataInputStream(soc.getInputStream()); 
            dos = new DataOutputStream(soc.getOutputStream()); 
          
            jTextArea1.append("\nCreating a new handler for this client..."); 
            username = dis.readUTF();
            if(userd.contains(username)){
                dos.writeUTF("already exits");
            }
            else{
            System.out.println(username);
            userd.add(username);
            // displaying the Client's Socket details.
            jTextArea1.append("\nNew client request received : \n " + soc+ " "+username);
            jTextArea1.append("\n"+" Connected..."+"\n" );
            jTextArea1.append("\nCreating a new handler for this client...Adding this client to active client list");
            
            // Assigning a ClientHandler for the client;
            ClientHandler mtch = new ClientHandler(soc,"client " + i, dis, dos,username);
            Thread t = new Thread(mtch); // Assigning a thread for each client.
            jTextArea1.append("\nAdding this client to active client list"); 
            
            // add this client to active clients list 
            ar.add(mtch); 
  
            // start the thread. 
            t.start();         
            i++;// for next client
            
            // Displaying the list of Avalible Users to the Server.
            jTextArea1.append("\nList of available users\n");
            for(String s: userd)
            {
                jTextArea1.append(s+"\n");
            }
        }
        }
       }
       catch(Exception e){} 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
class ClientHandler implements Runnable{
Scanner scn = new Scanner(System.in); 
    private String name; //Name of the Client
    final DataInputStream dis; //Name of the DataInputStream
    final DataOutputStream dos; // Name of the DataOutputStream
    Socket soc; // Name of the socket
    boolean isloggedin; // to checkwhether the client is loggedin or not
    String userName;// UserName of the given Client
    String received;// String to store the message from the Client.
    
// The Following Constructor is used to handle a particular client's Socket, Stream Details.
    public ClientHandler(Socket s, String name, DataInputStream dis, DataOutputStream dos, String user) {
        this.dis = dis; 
        this.dos = dos; 
        this.name = user; 
        this.soc = s; 
        this.isloggedin=true; 
        this.userName=user;
    }
    @Override
    public void run() {
        // While the User is logged into the system, this loop executes.
       while (this.isloggedin)  
        {   
            try
            {
                
                // receive the message from the client after an operations is performed. 
                received = dis.readUTF(); // the message received is read and then printed on to console output.
                System.out.println("received msg:");
                //Set the DataFormat for the Time and Date.
                 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();// Assigning the local date and time.
                // In case, a client logs out of the system.
                // loggedin is set to false, The following messages are displayed on the Output Window
                // and the socket is closed.
                if(received.equals("logout")){ 
                    System.out.println("Client "+userName+" requested logout");
                    System.out.println("Client "+userName+ " disconnected");
                    this.isloggedin=false; 
                    this.soc.close(); 
                    break; 
                }
                // A StringTokenizer is used to differentiate between the message and the recipent(s).
              StringTokenizer st = new StringTokenizer(received, "#"); // # is used to differentiate.
                String MsgToSend = st.nextToken(); // the message
                String recipient = st.nextToken();   // the recipient(s)
                // search for the recipient in the connected devices list. 
                // ar is the vector storing client of active users. 
                if(recipient.equals("all"))
                {
                    for(ClientHandler mc:Server.ar)
                    {
                    // if the recipients are found in the list, writes on its output streams
                    // only the recipients other than sender get the "1-N" message
                    if(!mc.name.equals(this.name))
                        mc.dos.writeUTF(this.name+" : "+MsgToSend+"\n This message was sent as * 1-N *"); 
                    
                    }
                    // The HttpMessage Containing the Message in HTTP FORMAT if STATUS IS OK.
                    String httpmsg="Request Method : POST\nHost :"+soc+"\nUser-Agent : Mozilla/5.0 (Windows 10.0; Win64; x64; rv:47:0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103"
                        +"\nStatus Code : 200\nStatus: HTTP/1.0 200 OK "
                        + "\nContent-type : text/html"
                        +"Content-length : "+received.length() +"\nDate : "+dtf.format(now)+"\nContent : "+userName+" : "+received;
                System.out.println(httpmsg);
                }
                else
                {    
                    int j=0;
                    for (ClientHandler mc : Server.ar)  
                { 
                    // if the recipient is found, write on its 
                    // output stream
                    
                    if (mc.name.equals(recipient) && mc.isloggedin==true)  
                    { 
                        mc.dos.writeUTF(this.name+" : "+MsgToSend+"\n This message was sent as * 1-1 *");j++;
                    // The HttpMessage Containing the Message in HTTP FORMAT if STATUS IS OK for 1-1 .
                        String httpmsg="Request Method : POST\nHost :"+soc+"\nUser-Agent : Mozilla/5.0 (Windows 10.0; Win64; x64; rv:47:0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103"
                        +"\nStatus Code : 200\nStatus: HTTP/1.0 200 OK "
                        + "\nContent-type : text/html"
                        +"Content-length : "+received.length() +"\nDate : "+dtf.format(now)+"\nContent : "+userName+" : "+received;
                System.out.println(httpmsg);
                
                        break; 
                    }
                }
                    if(j==0)
                    {
                   // If the recipient does not match the ones present in the list of recipients.
                    // The HttpMessage Containing the Message in HTTP FORMAT if STATUS IS  Not OK.
                    String httpmsg="Request Method : POST\nHost :"+soc+"\nUser-Agent : Mozilla/5.0 (Windows 10.0; Win64; x64; rv:47:0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103"
                        +"\nStatus Code : 404\nStatus: HTTP/1.0 404 Not Found "
                        + "\nContent-type : text/html"
                        +"Content-length : "+received.length() +"\nDate : "+dtf.format(now)+"\nContent : "+userName+" : "+received;
                System.out.println(httpmsg);
                    }
                }
                
         
            }
            catch(Exception e){}
            } 
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        }     
    }
}
