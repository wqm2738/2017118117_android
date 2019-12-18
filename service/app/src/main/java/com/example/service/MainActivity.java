package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int UPDATE_TEXT=1;
    private TextView text;
    private  int counter;
    private  Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    //text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView) findViewById(R.id.text);
        Button changeText=(Button)findViewById(R.id.change_text);
        changeText.setOnClickListener(this);
    }

    @Override
    public  void onClick(View v){
        DemoThread thread=new  DemoThread();
        Log.d("子进程号：",""+thread.getId());
        try {
            thread.start();
        }catch (Exception e){

        }
        Log.d("子线程号：",""+Thread.currentThread().getId());
        text.setText("我是："+counter);
        switch (v.getId()){
            case R.id.change_text:
            new Thread(new Runnable(){
                @Override
                public  void  run(){
                    Message message=new Message();
                    message .what=UPDATE_TEXT;
                    //handler.sendMessage(message);
                }
            }).start();
            break;
            default:
            break;
        }
    }
    class DemoThread extends Thread {
        public void run() {
            try{
                Thread.sleep(200);
            }catch (Exception e){

            }
            counter++;
        }
    }
}
