package com.degree.abbylaura.clientservermodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.*;
import java.net.Socket;
import android.widget.*;

public class MainActivity extends AppCompatActivity {


    EditText emailToServer;
    EditText passwordToServer;
    Button send;
    String username = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailToServer = (EditText) findViewById(R.id.emailToServer);
        passwordToServer = (EditText) findViewById(R.id.passwordToServer);
        send = (Button) findViewById(R.id.button_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailToServer.getText().toString();
                String password = passwordToServer.getText().toString();
                if(email.equals("Email") || email.equals("")){
                    String message = "Invalid or no email address";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                } else if(password.equals("Password") || password.equals("")){
                    String message = "Invalid or no password";
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                } else {
                    Client myClient = new Client(email, password);
                    try {
                        myClient.verify();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



    }


}
