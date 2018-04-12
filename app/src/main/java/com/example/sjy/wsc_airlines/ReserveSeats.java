package com.example.sjy.wsc_airlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sjy.wsc_airlines.Util.HttpUtil;
import com.example.sjy.wsc_airlines.gson.Ticket;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ReserveSeats extends AppCompatActivity {
    static final int AVAILABLE=0;
    static final int SELECTED=1;
    static final int SELECTING=2;
    Ticket ticket;
    EditText ticketNumber;
    Button getTicketTypeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seats);
        ticketNumber=findViewById(R.id.Ticket_Number_EditText);
        ticketNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        getTicketTypeButton=findViewById(R.id.Get_Ticket_Type_Button);
        getTicketTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    requestTicketInfo(ticketNumber.getText().toString());
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(ReserveSeats.this,"输入机票号码有误",Toast.LENGTH_LONG);
                }
            }
        });
    }

    public void backClick(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * 根据用户等级显示不同座位选项
     */
    public void showSeats(String ticketType){
        if(ticketType.equals("first")){
            Toast.makeText(this,"您是头等舱客户，请选择座位",Toast.LENGTH_SHORT).show();
            //等待加入选座图形及逻辑
        }else if(ticketType.equals("business")){
            Toast.makeText(this,"您是经济舱客户，请选择座位",Toast.LENGTH_SHORT).show();
            //等待加入选座图形及逻辑
        }else if(ticketType.equals("economy")){
            Toast.makeText(this,"对不起，经济舱客户不可自行选择座位",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"未能查找到您的机票，请检查输入是否有误。",Toast.LENGTH_SHORT).show();
        }


    }







    /**
     *  获取机票信息
     */
    public void requestTicketInfo(final String ticketNumber){
        String ticketInfoUrl="http://10.0.2.2:5000/api/ticket/"+ticketNumber;
        HttpUtil.sendOkHttpRequest(ticketInfoUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ReserveSeats.this,"获取机票信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                if(!TextUtils.isEmpty(responseText)){
                    try {
                        Gson gson=new Gson();
                        ticket=gson.fromJson(responseText,Ticket.class);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showSeats(ticket.getTicketType());
                    }
                });
            }
        });
    }
}
