/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progettotcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ClientTCP {
    
    Socket conn = null;
    String address = "localhost";
    final int port = 2000;
    DataInputStream in;
    DataOutputStream out;

    public ClientTCP(){
    }
    
    public void connetti(){
        try{
            conn = new Socket(address, port);
            System.out.println("Connessione aperta");
            in = new DataInputStream(conn.getInputStream());
            out = new DataOutputStream(conn.getOutputStream());
        }
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }

        catch(IOException e2){//
            System.err.println(e2);
            e2.printStackTrace();
        }
    }
    
    public void comunica(){
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Messaggio da inviare al server: ");
            String msg = input.next();
            out.writeUTF(msg);
            String rx = in.readUTF();
            System.out.println("Il server ha risposto: " + rx);
            conn.close();
            System.out.print("Connessione chiusa");
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        ClientTCP c = new ClientTCP();
        c.connetti();
        c.comunica();
    }
    
}