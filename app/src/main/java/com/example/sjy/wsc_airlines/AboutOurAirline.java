package com.example.sjy.wsc_airlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutOurAirline extends AppCompatActivity {
    TextView Info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_our_airline);
        Info=findViewById(R.id.Info_Text);
        for(int i=0;i<300;i++){
            Info.setText(Info.getText()+"ABCDEFG:"+i+"  ");
        }
    }
    public void backClick(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
