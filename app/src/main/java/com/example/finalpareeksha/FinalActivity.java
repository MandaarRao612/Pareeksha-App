package com.example.finalpareeksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FinalActivity extends AppCompatActivity {

    Button next,submit;
    TextView time,question;
    RadioButton radioButton,radioButton1,radioButton2,radioButton3,radioButton4;
    RadioGroup radioGroup1;
    DatabaseReference databaseReferencefinal;
    int count=2;
    int marks=0;
    Calendar calendar;
    String datee;
    SimpleDateFormat simpleDateFormat,simpleDateFormat2,simpleDateFormat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        String code=getIntent().getStringExtra("code");
        String subject=getIntent().getStringExtra("subject");
        time=(TextView)findViewById(R.id.textView10);
        question=(TextView)findViewById(R.id.textView9);
        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup2);
        radioButton1=(RadioButton)findViewById(R.id.radioButton5);
        radioButton2=(RadioButton)findViewById(R.id.radioButton6);
        radioButton3=(RadioButton)findViewById(R.id.radioButton7);
        radioButton4=(RadioButton)findViewById(R.id.radioButton8);
        next=(Button)findViewById(R.id.button25);
        submit=(Button)findViewById(R.id.button24);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd MM yyyy");
        simpleDateFormat2=new SimpleDateFormat("HH");
        simpleDateFormat3=new SimpleDateFormat("mm");
        datee=simpleDateFormat.format(calendar.getTime());
        databaseReferencefinal= FirebaseDatabase.getInstance().getReference("Database");
        Date date=new Date();
        String data=String.valueOf(simpleDateFormat2.format(date));
        int hour=Integer.parseInt(data);
        String data2=String.valueOf(simpleDateFormat3.format(date));
        int min=Integer.parseInt(data2);
        databaseReferencefinal.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String h1=snapshot.child("Subject").child(subject).child(code).child("starttime1").getValue().toString();
                String m1=snapshot.child("Subject").child(subject).child(code).child("endtime1").getValue().toString();
                String h2=snapshot.child("Subject").child(subject).child(code).child("starttime2").getValue().toString();
                String m2=snapshot.child("Subject").child(subject).child(code).child("endtime2").getValue().toString();
                String d=snapshot.child("Subject").child(subject).child(code).child("date").getValue().toString();
                int mm1=Integer.parseInt(m1);
                int mm2=Integer.parseInt(m2);
                int hh1=Integer.parseInt(h1);
                int hh2=Integer.parseInt(h2);
                if (!d.equals(datee)){
                    Intent done=new Intent(FinalActivity.this,HomeActivity.class);
                    Toast.makeText(FinalActivity.this, "Today is not the Exam", Toast.LENGTH_SHORT).show();
                    startActivity(done);
                    finish();}
                if (hh2<hour || mm2<min){
                    Intent done=new Intent(FinalActivity.this,HomeActivity.class);
                    Toast.makeText(FinalActivity.this, "Exam time out", Toast.LENGTH_SHORT).show();
                    startActivity(done);
                    finish();
                }
                if (mm1>min || hh1>hour){
                    Intent done=new Intent(FinalActivity.this,HomeActivity.class);
                    Toast.makeText(FinalActivity.this, "Exam Not started Yet!", Toast.LENGTH_SHORT).show();
                    startActivity(done);
                    finish();}
                String quetion=snapshot.child("Subject").child(subject).child(code).child("Questions").child("1").child("Q").getValue().toString();
                String option1=snapshot.child("Subject").child(subject).child(code).child("Questions").child("1").child("a").getValue().toString();
                String option2=snapshot.child("Subject").child(subject).child(code).child("Questions").child("1").child("b").getValue().toString();
                String option3=snapshot.child("Subject").child(subject).child(code).child("Questions").child("1").child("c").getValue().toString();
                String option4=snapshot.child("Subject").child(subject).child(code).child("Questions").child("1").child("d").getValue().toString();
                int mm=0;
                int hh=0;
                int start=mm2-min;
                long duration = TimeUnit.MINUTES.toMillis(start);

                new CountDownTimer(duration, 1000) {
                    @Override
                    public void onTick(long l) {
                        String sDuration= String.format(Locale.ENGLISH,"%02d min : %02d sec",TimeUnit.MILLISECONDS.toMinutes(l),TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                        time.setText(sDuration);
                    }

                    @Override
                    public void onFinish() {
                        int radio=radioGroup1.getCheckedRadioButtonId();
                        radioButton=findViewById(radio);
                        if (radioButton==null){
                            Toast.makeText(FinalActivity.this, "Exam Time is Over", Toast.LENGTH_SHORT).show();
                            Intent sub=new Intent(FinalActivity.this,ResultActivity.class);
                            String markss=String.valueOf(marks);
                            sub.putExtra("marks",markss);
                            startActivity(sub);
                            finish();
                        }else {
                            String ans1=radioButton.getText().toString();
                            String counterr=String.valueOf(count-1);
                            String ans=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counterr).child("ans").getValue().toString();
                            if (ans1.equals(ans)){
                                marks=marks+1;
                            }
                            Intent sub=new Intent(FinalActivity.this,ResultActivity.class);
                            String markss=String.valueOf(marks);
                            sub.putExtra("marks",markss);
                            startActivity(sub);
                            finish();
                        }
                    }
                }.start();

                question.setText("Q1"+quetion);
                radioButton1.setText(option1);
                radioButton2.setText(option2);
                radioButton3.setText(option3);
                radioButton4.setText(option4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReferencefinal.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String counter=String.valueOf(count);
                        int radio=radioGroup1.getCheckedRadioButtonId();
                        radioButton=findViewById(radio);
                        if (radioButton==null){
                            Toast.makeText(FinalActivity.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                        }else {
                            String ans1=radioButton.getText().toString();
                            String counterr=String.valueOf(count-1);
                            String ans=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counterr).child("ans").getValue().toString();
                            if (ans1.equals(ans)){
                                marks=marks+1;
                            }
                            Intent sub=new Intent(FinalActivity.this,ResultActivity.class);
                            String markss=String.valueOf(marks);
                            sub.putExtra("marks",markss);
                            startActivity(sub);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReferencefinal.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String counter=String.valueOf(count);
                        int radio=radioGroup1.getCheckedRadioButtonId();
                        radioButton=findViewById(radio);
                        if (radioButton==null){
                            Toast.makeText(FinalActivity.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                        }else {
                            String ans1=radioButton.getText().toString();
                            if (count==5){
                                String quetion=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").getValue().toString();
                                String option1=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").getValue().toString();
                                String option2=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").getValue().toString();
                                String option3=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").getValue().toString();
                                String option4=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").getValue().toString();
                                String counterr=String.valueOf(count-1);
                                String ans=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counterr).child("ans").getValue().toString();
                                if (ans1.equals(ans)){
                                    marks=marks+1;
                                }
                                question.setText("Q"+counter+" "+quetion);
                                radioButton1.setText(option1);
                                radioButton2.setText(option2);
                                radioButton3.setText(option3);
                                radioButton4.setText(option4);
                                next.setVisibility(View.GONE);
                                radioGroup1.clearCheck();
                            }
                            if (count<=4){
                                String quetion=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("Q").getValue().toString();
                                String option1=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("a").getValue().toString();
                                String option2=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("b").getValue().toString();
                                String option3=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("c").getValue().toString();
                                String option4=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counter).child("d").getValue().toString();
                                String counterr=String.valueOf(count-1);
                                String ans=snapshot.child("Subject").child(subject).child(code).child("Questions").child(counterr).child("ans").getValue().toString();
                                if (ans1.equals(ans)){
                                    marks=marks+1;
                                }
                                question.setText("Q"+counter+" "+quetion);
                                radioButton1.setText(option1);
                                radioButton2.setText(option2);
                                radioButton3.setText(option3);
                                radioButton4.setText(option4);
                                radioGroup1.clearCheck();
                                count=count+1;

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }
}