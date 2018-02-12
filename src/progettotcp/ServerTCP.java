/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettotcp;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ServerTCP {
    
    ServerSocket sSocket;
    Socket conn;
    final int port = 2000;
    DataInputStream in;
    DataOutputStream out;

    public ServerTCP(){
        
    }
    
    
    public void connetti(){
        try {
            sSocket = new ServerSocket(port);
            System.out.println("Server in attesa di connessione");
            conn = sSocket.accept();
            System.out.println("Connessione stabilita");
            in = new DataInputStream(conn.getInputStream());
            out = new DataOutputStream(conn.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (sSocket!=null) sSocket.close();
        } catch (IOException ex) {
            System.err.println("Errore nella chiusura della connessione!");
        }
    }
    
    public void comunica(){
        try {
            String rx = in.readUTF();
            System.out.println("Hai ricevuto la stringa: " + rx);
            out.writeUTF("messaggio ricevuto");
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connessione chiusa");
    }
    
    
    public static void main(String[] args){
        ServerTCP s = new ServerTCP();
        s.connetti();
        s.comunica();
    }
        
    
}

