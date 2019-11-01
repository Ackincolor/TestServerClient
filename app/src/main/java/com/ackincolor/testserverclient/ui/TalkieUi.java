package com.ackincolor.testserverclient.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ackincolor.testserverclient.R;
import com.ackincolor.testserverclient.Talkie;
import com.ackincolor.testserverclient.communication.Talking;

import java.net.Socket;

public class TalkieUi extends AppCompatActivity {

    private Socket s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talkie);
        //recuperation du socket
        Talkie t = (Talkie)getApplication();
        this.s = t.getSocket();

        //starting thread
        Talking talking = new Talking(this.s);
        talking.start();

    }
}
