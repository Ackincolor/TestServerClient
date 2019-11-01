package com.ackincolor.testserverclient;

import android.app.Application;

import java.net.Socket;

public class Talkie extends Application {
    private Socket socket;

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
