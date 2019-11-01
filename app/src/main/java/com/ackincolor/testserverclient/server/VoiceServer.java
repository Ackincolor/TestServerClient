package com.ackincolor.testserverclient.server;

import android.content.Intent;

import com.ackincolor.testserverclient.MainActivity;
import com.ackincolor.testserverclient.Talkie;
import com.ackincolor.testserverclient.ui.TalkieUi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class VoiceServer extends Thread{
    private Integer port;
    private MainActivity ma;
    private boolean running = true;

    public VoiceServer(Integer port, MainActivity ma) {
        this.port = port;
        this.ma = ma;
    }

    @Override
    public void run() {
        //creation et ecout d'un socket.
        try(ServerSocket ss = new ServerSocket(this.port)){
            while(running){
                Socket s = ss.accept();
                Talkie t = (Talkie)ma.getApplication();
                t.setSocket(s);
                //demarrage du transfert
                System.out.println("Connexion entrante");
                Intent talk = new Intent(ma.getApplicationContext(), TalkieUi.class);
                ma.startActivity(talk);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
