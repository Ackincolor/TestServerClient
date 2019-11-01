package com.ackincolor.testserverclient.client;

import android.content.Intent;

import com.ackincolor.testserverclient.MainActivity;
import com.ackincolor.testserverclient.Talkie;
import com.ackincolor.testserverclient.ui.TalkieUi;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class VoiceClient extends Thread {

    private String ip;
    private Integer port;
    private MainActivity ma;

    public VoiceClient(String ip, Integer port, MainActivity ma) {
        this.ip = ip;
        this.port = port;
        this.ma = ma;
    }

    @Override
    public void run() {
        try(Socket s = new Socket(this.ip,this.port)){
            //demmarrage de la communication
            Talkie t = (Talkie)ma.getApplication();
            t.setSocket(s);
            Intent talk = new Intent(ma.getApplicationContext(), TalkieUi.class);
            ma.startActivity(talk);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
