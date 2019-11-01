package com.ackincolor.testserverclient.communication;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Talking extends Thread {
    private Socket s;
    private boolean running = true;

    static final int sampleFreq = 16000;
    private int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
    static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    static final int streamType = AudioManager.STREAM_MUSIC;
    static final int audioMode = AudioTrack.MODE_STREAM;
    int minBufSize = AudioRecord.getMinBufferSize(sampleFreq, channelConfig, audioEncoding);

    public Talking(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try{

            byte [] audioBuffer = new byte[4096];

            //creates input stream readers to read incoming data
            BufferedInputStream myBis = new BufferedInputStream(this.s.getInputStream());
            DataInputStream myDis = new DataInputStream(myBis);
            AudioTrack myAudioTrack = new AudioTrack(streamType, sampleFreq, channelConfig, audioEncoding, minBufSize, audioMode);
            //InputStreamReader isr = new InputStreamReader(s.getInputStream());

            OutputStream os = s.getOutputStream();
            while(running && myDis.available()>0){
                /*isr.read(buffer);
                System.out.println(new String(buffer));*/
                os.write("OK".getBytes());
                os.flush();
                audioBuffer[audioBuffer.length] = myDis.readByte();
                myAudioTrack.play();
                myAudioTrack.write(audioBuffer, 0, audioBuffer.length);
            }
            myDis.close();
            myBis.close();
            Log.d("DEBUG","closing reader");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
