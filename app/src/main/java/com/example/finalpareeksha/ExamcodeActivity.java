package com.example.finalpareeksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExamcodeActivity extends AppCompatActivity {

    EditText examcode;
    Button code;
    DatabaseReference databaseReferencode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examcode);
        examcode=(EditText)findViewById(R.id.editTextTextPersonName6);
        code=(Button)findViewById(R.id.button15);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codee=examcode.getText().toString();
                if (codee.isEmpty()){
                    examcode.setError("Exam code Required");
                }else{
                    databaseReferencode= FirebaseDatabase.getInstance().getReference("Database").child("Subject").child(codee);
                    databaseReferencode.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                String sub=snapshot.getValue().toString();
                                Intent coder=new Intent(ExamcodeActivity.this,RuleActivity.class);
                                coder.putExtra("code",codee);
                                coder.putExtra("subject",sub);
                                startActivity(coder);
                            }else{
                                Toast.makeText(ExamcodeActivity.this, "Exam Code Invalid", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}