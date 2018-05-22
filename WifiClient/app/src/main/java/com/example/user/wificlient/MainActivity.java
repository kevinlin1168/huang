package com.example.user.wificlient;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonconnect,buttondisconnect,buttonA,buttonF,buttonC,buttonL,buttonS,buttonR,buttonstart;
    private Button buttonG,buttonB,buttonD,button9,buttonE,buttonK,button5,button1,button6;
    private Button button3,button0,button4,button7,button2,button8;
    private EditText Address,Port,ShowAddress,ShowPort;
    private BufferedWriter bw;            //取得網路輸出串流
    private BufferedReader br=null;             //取得網路輸入串流
    private String Ip,recvword="";
    private Thread thread,thread2;                //執行緒
    private Socket clientSocket = new Socket();        //客戶端的socket
    private InetAddress serverIp;
    private TextView txv,txv2;
    private int serverPort;
    private Handler mHandler = new Handler(){
        int i = 0;
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:
                    txv2.append("recv:"+recvword+"\n");
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);

        buttonconnect =  findViewById(R.id.Connect);
        buttonconnect.setOnClickListener(this);
        buttondisconnect = findViewById(R.id.buttondisconnect);
        buttondisconnect.setOnClickListener(this);
        ShowAddress = findViewById(R.id.showIpaddress);
        ShowPort = findViewById(R.id.showPortnumber);
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
        txv = findViewById(R.id.txv);
        txv.setMovementMethod(ScrollingMovementMethod.getInstance());
        txv2 = findViewById(R.id.txv2);
        txv2.setMovementMethod(ScrollingMovementMethod.getInstance());*/

        setContentView(R.layout.login);
        buttonstart = findViewById(R.id.Start);
        buttonstart.setOnClickListener(this);
        Address = findViewById(R.id.Ipaddress);
        Port = findViewById(R.id.Portnumber);
    }
    public void setview2(View view){
        setContentView(R.layout.activity_main);

        buttonconnect =  findViewById(R.id.Connect);
        buttonconnect.setOnClickListener(this);
        buttondisconnect = findViewById(R.id.buttondisconnect);
        buttondisconnect.setOnClickListener(this);
        ShowAddress = findViewById(R.id.showIpaddress);
        ShowPort = findViewById(R.id.showPortnumber);
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
        txv = findViewById(R.id.txv);
        txv.setMovementMethod(ScrollingMovementMethod.getInstance());
        txv2 = findViewById(R.id.txv2);
        txv2.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void onClick(View view) {
        Log.e("fuck6",String.valueOf(clientSocket.isConnected()));
        if(view.getId() == buttonstart.getId())
        {
            thread=new Thread(Connection);                //賦予執行緒工作
            thread.start();

            Log.e("fuck1","On clicked");
            try{
            Thread.sleep(3000);}
            catch(Exception e)
            {}
            if(clientSocket.isConnected() == true)
            {
                thread2=new Thread(receivable);                //賦予執行緒工作
                thread2.start();
                Log.e("fuck2","connected");
                setview2(view);
                ShowAddress.setText(Ip);
                ShowPort.setText(String.valueOf(serverPort));
            }
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
            Log.e("fuck3",String.valueOf(clientSocket.isConnected()));
            if(clientSocket.isConnected() == true)
            {
                Log.e("fuck4","sending");//send ni ma
                if(view.getId() == buttonA.getId())
                {
                    Sendword("A");
                    Log.e("fuck5","A");
                }
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
                serverPort = Integer.parseInt(Port.getText().toString());
                clientSocket = new Socket(serverIp, serverPort);
                bw = new BufferedWriter( new OutputStreamWriter(clientSocket.getOutputStream()));
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            }
            catch(Exception e){
                //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
                e.printStackTrace();
                Log.e("text","Socket連線="+e.toString());
                finish();    //當斷線時自動關閉房間
            }
        }

    };
    private Runnable receivable=new Runnable(){
        @Override
        public void run() {
            while(true) {
                try {
                    recvword = br.readLine();
                    if (recvword != null) {
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    //當斷線時會跳到catch,可以在這裡寫上斷開連線後的處理
                    e.printStackTrace();
                    Log.e("text", "Socket連線=" + e.toString());
                    finish();    //當斷線時自動關閉房間
                }
            }
        }

    };
    private void Sendword(String word)
    {
        try {
            bw.write(word+"\n");
            txv.append("send:"+word+"\n");
            bw.flush();
            Log.e("finish","send finish");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
