package com.example.finalpareeksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectActivity extends AppCompatActivity {

    Button oop,dsa,dbms,cpp,python;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        oop=(Button)findViewById(R.id.button6);
        dsa=(Button)findViewById(R.id.button7);
        dbms=(Button)findViewById(R.id.button8);
        cpp=(Button)findViewById(R.id.button9);
        python=(Button)findViewById(R.id.button10);
        oop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oopp=new Intent(SubjectActivity.this,DetailsActivity.class);
                oopp.putExtra("id","oop");
                startActivity(oopp);
            }
        });
        dsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oopp1=new Intent(SubjectActivity.this,DetailsActivity.class);
                oopp1.putExtra("id","dsa");
                startActivity(oopp1);
            }
        });
        dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oopp2=new Intent(SubjectActivity.this,DetailsActivity.class);
                oopp2.putExtra("id","dbms");
                startActivity(oopp2);
            }
        });
        cpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oopp3=new Intent(SubjectActivity.this,DetailsActivity.class);
                oopp3.putExtra("id","c++");
                startActivity(oopp3);
            }
        });
        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oopp4=new Intent(SubjectActivity.this,DetailsActivity.class);
                oopp4.putExtra("id","python");
                startActivity(oopp4);
            }
        });
    }
}