package com.example.finalpareeksha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResultActivity extends AppCompatActivity {

    TextView marks,name;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        name=(TextView)findViewById(R.id.textView11);
        marks=(TextView)findViewById(R.id.textView20);
        FirebaseUser marker=FirebaseAuth.getInstance().getCurrentUser();
        String markss=getIntent().getStringExtra("marks");
        String gmail=marker.getEmail();
        marks.setText(markss);
        name.setText(gmail);
        logout=(Button)findViewById(R.id.button21);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //authout.signOut();
                Intent out=new Intent(ResultActivity.this,HomeActivity.class);
                startActivity(out);
                finish();
            }
        });

    }
}