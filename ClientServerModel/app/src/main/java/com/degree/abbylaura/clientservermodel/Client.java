package com.degree.abbylaura.clientservermodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by abbylaura on 19/02/2018.
 */

public class Client {

    private BufferedReader inFromServer;
    private PrintWriter outToServer;
    String email;
    String password;

    Socket socket;


    public Client(String email, String password){
        super();
        this.password = password;
        this.email = email;
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    public Boolean verify() throws IOException {

        // Make connection and initialize streams
        try {
            socket = new Socket("localhost", 8888);
            inFromServer = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            outToServer = new PrintWriter(socket.getOutputStream(), true);

            // Process all messages from server, according to the protocol.
            while (true) {
                String line = inFromServer.readLine();
                if (line.startsWith("EMAIL")) {
                    outToServer.println(email);
                } else if (line.startsWith("PASSWORD")) {
                    outToServer.println(password);

                    //fromServer.setText(line.substring(8) + "\n");
                } else if (line.startsWith("TRUE")) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }/* finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e);

            }
        }*/

        return false;

    }



}
