package com.example.finalpareeksha;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {
    EditText question,option1,option2,option3,option4;
    RadioGroup radioGroup1;
    DatabaseReference mDatabaseReference;
    RadioButton radioButton,radioButton1,radioButton2,radioButton3,radioButton4;
    Button submit;
    int count=1;
    TextView q;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        String date=getIntent().getStringExtra("date");
        String startime1=getIntent().getStringExtra("starttime1");
        String endtime1=getIntent().getStringExtra("endtime1");
        String startime2=getIntent().getStringExtra("starttime2");
        String endtime2=getIntent().getStringExtra("endtime2");
        String code=getIntent().getStringExtra("examcode");
        String subject=getIntent().getStringExtra("subject");
        q=(TextView)findViewById(R.id.textView15);
        mDatabaseReference= FirebaseDatabase.getInstance().getReference("Database");
        mDatabaseReference.child("Subject").child(subject).child(code).child("starttime1").setValue(startime1);
        mDatabaseReference.child("Subject").child(subject).child(code).child("endtime1").setValue(endtime1);
        mDatabaseReference.child("Subject").child(subject).child(code).child("starttime2").setValue(startime2);
        mDatabaseReference.child("Subject").child(subject).child(code).child("endtime2").setValue(endtime2);
        mDatabaseReference.child("Subject").child(subject).child(code).child("date").setValue(date);
        mDatabaseReference.child("Subject").child(code).setValue(subject);
        question=(EditText)findViewById(R.id.editTextTextPersonName9);
        option1=(EditText)findViewById(R.id.editTextTextPersonName10);
        option2=(EditText)findViewById(R.id.editTextTextPersonName11);
        option3=(EditText)findViewById(R.id.editTextTextPersonName12);
        option4=(EditText)findViewById(R.id.editTextTextPersonName13);
        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup);
        radioButton1=(RadioButton)findViewById(R.id.radioButton);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton3);
        radioButton4=(RadioButton)findViewById(R.id.radioButton4);
        submit=(Button)findViewById(R.id.button5);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String finalquetion=question.getText().toString();
                String finaloption1=option1.getText().toString();
                String finaloption2=option2.getText().toString();
                String finaloption3=option3.getText().toString();
                String finaloption4=option4.getText().toString();
                String counter=String.valueOf(count);
                int radio=radioGroup1.getCheckedRadioButtonId();
                radioButton=findViewById(radio);
                if (radioButton==null){
                    Toast.makeText(AdminActivity.this, "Select Correct Answer", Toast.LENGTH_SHORT).show();
                    if (finalquetion.isEmpty()){
                        question.setError("Question Required");
                    }
                    if (finaloption1.isEmpty()){
                        option1.setError("Option A Required");
                    }
                    if (finaloption2.isEmpty()){
                        option2.setError("Option B Required");
                    }
                    if (finaloption3.isEmpty()){
                        option3.setError("Option C Required");
                    }
                    if (finaloption4.isEmpty()){
                        option4.setError("Option D Required");
                    }
                }else {
                    String ans1=radioButton.getText().toString();
                    if (!finalquetion.isEmpty() && !finaloption1.isEmpty() && !finaloption2.isEmpty() && !finaloption3.isEmpty() && !finaloption4.isEmpty() && !ans1.isEmpty()) {
                        if (count<=5){
                            if (ans1.equals("A")) {
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").setValue(finalquetion);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").setValue(finaloption1);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").setValue(finaloption2);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").setValue(finaloption3);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").setValue(finaloption4);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("ans").setValue(finaloption1);
                                question.getText().clear();
                                option1.getText().clear();
                                option2.getText().clear();
                                option3.getText().clear();
                                option4.getText().clear();
                                radioGroup1.clearCheck();
                                count = count + 1;
                                q.setText("Q" + count);
                            }
                            if (ans1.equals("B")) {
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").setValue(finalquetion);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").setValue(finaloption1);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").setValue(finaloption2);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").setValue(finaloption3);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").setValue(finaloption4);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("ans").setValue(finaloption2);
                                question.getText().clear();
                                option1.getText().clear();
                                option2.getText().clear();
                                option3.getText().clear();
                                option4.getText().clear();
                                radioGroup1.clearCheck();
                                count = count + 1;
                                q.setText("Q" + count);
                            }
                            if (ans1.equals("C")) {
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").setValue(finalquetion);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").setValue(finaloption1);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").setValue(finaloption2);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").setValue(finaloption3);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").setValue(finaloption4);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("ans").setValue(finaloption3);
                                question.getText().clear();
                                option1.getText().clear();
                                option2.getText().clear();
                                option3.getText().clear();
                                option4.getText().clear();
                                radioGroup1.clearCheck();
                                count = count + 1;
                                q.setText("Q" + count);
                            }
                            if (ans1.equals("D")) {
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").setValue(finalquetion);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").setValue(finaloption1);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").setValue(finaloption2);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").setValue(finaloption3);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").setValue(finaloption4);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("ans").setValue(finaloption4);
                                question.getText().clear();
                                option1.getText().clear();
                                option2.getText().clear();
                                option3.getText().clear();
                                option4.getText().clear();
                                radioGroup1.clearCheck();
                                count = count + 1;
                                q.setText("Q" + count);
                            }
                        }
                        if (count==6){
                            if (ans1.equals("A")) {
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").setValue(finalquetion);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").setValue(finaloption1);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").setValue(finaloption2);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").setValue(finaloption3);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").setValue(finaloption4);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("ans").setValue(finaloption1);
                                Intent success=new Intent(AdminActivity.this,SubjectActivity.class);
                                Toast.makeText(AdminActivity.this, "Exam Created Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(success);
                                finish();
                            }
                            if (ans1.equals("B")) {
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").setValue(finalquetion);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").setValue(finaloption1);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").setValue(finaloption2);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").setValue(finaloption3);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").setValue(finaloption4);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("ans").setValue(finaloption2);
                                Intent success=new Intent(AdminActivity.this,SubjectActivity.class);
                                Toast.makeText(AdminActivity.this, "Exam Created Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(success);
                                finish();
                            }
                            if (ans1.equals("C")) {
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").setValue(finalquetion);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").setValue(finaloption1);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").setValue(finaloption2);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").setValue(finaloption3);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").setValue(finaloption4);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("ans").setValue(finaloption3);
                                Intent success=new Intent(AdminActivity.this,SubjectActivity.class);
                                Toast.makeText(AdminActivity.this, "Exam Created Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(success);
                                finish();
                            }
                            if (ans1.equals("D")) {
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").setValue(finalquetion);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").setValue(finaloption1);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").setValue(finaloption2);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").setValue(finaloption3);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").setValue(finaloption4);
                                mDatabaseReference.child("Subject").child(subject).child(code).child("Questions").child(counter).child("ans").setValue(finaloption4);
                                Intent success=new Intent(AdminActivity.this,SubjectActivity.class);
                                Toast.makeText(AdminActivity.this, "Exam Created Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(success);
                                finish();
                            }
                        }
                    }
                    if (ans1.isEmpty()){
                        Toast.makeText(AdminActivity.this, "Please Select Correct Answer", Toast.LENGTH_SHORT).show();
                    }
                    if (finalquetion.isEmpty()){
                        question.setError("Question Required");
                    }
                    if (finaloption1.isEmpty()){
                        option1.setError("Option A Required");
                    }
                    if (finaloption2.isEmpty()){
                        option2.setError("Option B Required");
                    }
                    if (finaloption3.isEmpty()){
                        option3.setError("Option C Required");
                    }
                    if (finaloption4.isEmpty()){
                        option4.setError("Option D Required");
                    }
                }
            }
        });

    }
}