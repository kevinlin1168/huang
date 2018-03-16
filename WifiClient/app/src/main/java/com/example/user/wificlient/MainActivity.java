package com.example.user.wificlient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonconnect,buttondisconnect,buttonA,buttonF,buttonC,buttonL,buttonS,buttonR;
    private Button buttonG,buttonB,buttonD,button9,buttonE,buttonK,button5,button1,button6;
    private Button button3,button0,button4,button7,button2,button8;
    private EditText Address,Port;
    private BufferedWriter bw;            //取得網路輸出串流
    private String Ip;
    private Thread thread;                //執行緒
    private Socket clientSocket = new Socket();        //客戶端的socket
    private InetAddress serverIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Address = findViewById(R.id.Ipaddress);
        Port = findViewById(R.id.Portnumber);
        buttonconnect =  findViewById(R.id.Connect);
        buttonconnect.setOnClickListener(this);
        buttondisconnect = findViewById(R.id.buttondisconnect);
        buttondisconnect.setOnClickListener(this);
        buttonA = findViewById(R.id.buttonA);
        buttonA.setOnClickListener(this);
        buttonF =  findViewById(R.id.buttonF);
        buttonF.setOnClickListener(this);
        buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);
        buttonL =  findViewById(R.id.buttonL);
        buttonL.setOnClickListener(this);
        buttonS = findViewById(R.id.buttonS);
        buttonS.setOnClickListener(this);
        buttonR = findViewById(R.id.buttonR);
        buttonR.setOnClickListener(this);
        buttonG = findViewById(R.id.buttonG);
        buttonG.setOnClickListener(this);
        buttonB =  findViewById(R.id.buttonB);
        buttonB.setOnClickListener(this);
        buttonD =findViewById(R.id.buttonD);
        buttonD.setOnClickListener(this);
        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(this);
        buttonE =  findViewById(R.id.buttonE);
        buttonE.setOnClickListener(this);
        buttonK =  findViewById(R.id.buttonK);
        buttonK.setOnClickListener(this);
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button1 =  findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);
        button3 =  findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button0 =  findViewById(R.id.button0);
        button0.setOnClickListener(this);
        button4 =  findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == buttonconnect.getId())
        {
            thread=new Thread(Connection);                //賦予執行緒工作
            thread.start();
        }
        else if (view.getId() == buttondisconnect.getId())
        {
            try {
                bw.close();
                clientSocket.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            if(clientSocket.isConnected() == true)
            {
                if(view.getId() == buttonA.getId())
                    Sendword("A");
                else if (view.getId() == buttonF.getId())
                    Sendword("F");
                else if (view.getId() == buttonC.getId())
                    Sendword("C");
                else if (view.getId() == buttonL.getId())
                    Sendword("L");
                else if (view.getId() == buttonS.getId())
                    Sendword("S");
                else if (view.getId() == buttonR.getId())
                    Sendword("R");
                else if (view.getId() == buttonG.getId())
                    Sendword("G");
                else if (view.getId() == buttonB.getId())
                    Sendword("B");
                else if (view.getId() == buttonD.getId())
                    Sendword("D");
                else if (view.getId() == button9.getId())
                    Sendword("9");
                else if (view.getId() == buttonE.getId())
                    Sendword("E");
                else if (view.getId() == buttonK.getId())
                    Sendword("K");
                else if (view.getId() == button5.getId())
                    Sendword("5");
                else if (view.getId() == button1.getId())
                    Sendword("1");
                else if (view.getId() == button6.getId())
                    Sendword("6");
                else if (view.getId() == button3.getId())
                    Sendword("3");
                else if (view.getId() == button0.getId())
                    Sendword("0");
                else if (view.getId() == button4.getId())
                    Sendword("4");
                else if (view.getId() == button7.getId())
                    Sendword("7");
                else if (view.getId() == button2.getId())
                    Sendword("2");
                else if (view.getId() == button8.getId())
                    Sendword("8");
            }
        }
    }
    private Runnable Connection=new Runnable(){
        @Override
        public void run() {

        try {
            Ip = Address.getText().toString();

            serverIp = InetAddress.getByName(Ip);
            int serverPort = Integer.getInteger(Port.getText().toString());
            clientSocket = new Socket(serverIp, serverPort);
            bw = new BufferedWriter( new OutputStreamWriter(clientSocket.getOutputStream()));
        }
        catch(Exception e){
            //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
            e.printStackTrace();
            Log.e("text","Socket連線="+e.toString());
            finish();    //當斷線時自動關閉房間
        }
        }

        };
    private void Sendword(String word)
    {
        try {
            bw.write(word+"\n");
            bw.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
