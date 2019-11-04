package com.ackincolor.testserverclient;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ackincolor.testserverclient.client.VoiceClient;
import com.ackincolor.testserverclient.server.VoiceServer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView serverIp;
    private EditText serverPort,serverIp2,serverPort2;
    private Button connect,startServer,startrecording;

    private VoiceClient vc;
    private VoiceServer vs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.serverIp = findViewById(R.id.serverip);
        this.serverIp2 = findViewById(R.id.serverip2);
        this.serverPort = findViewById(R.id.serverport);
        this.serverPort2 = findViewById(R.id.serverport2);

        this.connect = findViewById(R.id.connect);
        this.startServer = findViewById(R.id.startServer);
        this.startrecording = findViewById(R.id.startrecording);

        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        final String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        this.serverIp.setText(ip);

        this.vc = new VoiceClient(ip, null,this);
        this.vs = new VoiceServer(null,this);

        this.connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //connection
                Integer port = Integer.parseInt(serverPort2.getText().toString());
                String ip = serverIp2.getText().toString();
                if(port > 0 && !ip.equals("") ){
                    vc.setPort(port);
                    vc.setIp(ip);
                    vc.start();
                }else{
                    Toast.makeText(getApplicationContext(),"Erreur de saisie",Toast.LENGTH_LONG);
                }
            }
        });
        this.startServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //demarrage de l'ecoute
                Integer port = Integer.parseInt(serverPort.getText().toString());
                if(port > 0){
                    vs.setPort(port);
                    vs.start();
                }else{
                    Toast.makeText(getApplicationContext(),"Erreur de saisie",Toast.LENGTH_LONG);
                }
            }
        });
        this.startrecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"START RECORDING",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
