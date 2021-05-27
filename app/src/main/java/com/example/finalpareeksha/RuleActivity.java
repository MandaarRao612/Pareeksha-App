package com.example.finalpareeksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RuleActivity extends AppCompatActivity {

    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        next=(Button)findViewById(R.id.button16);
        String data=getIntent().getStringExtra("code");
        String data2=getIntent().getStringExtra("subject");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rule=new Intent(RuleActivity.this,FinalActivity.class);
                rule.putExtra("code",data);
                rule.putExtra("subject",data2);
                startActivity(rule);
            }
        });
    }
}