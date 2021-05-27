package com.example.finalpareeksha;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    EditText examcode;
    TextView datte,time1,time2;
    Button starttime,duration,selectdate,next;
    Calendar calendar;
    String daatte,ttime1,ttime2,timee11,timee12,timee21,timee22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        examcode=(EditText)findViewById(R.id.editTextTextPersonName5);
        starttime=(Button)findViewById(R.id.button12);
        duration=(Button)findViewById(R.id.button13);
        datte=(TextView)findViewById(R.id.textView22);
        time1=(TextView)findViewById(R.id.textView23);
        time2=(TextView)findViewById(R.id.textView24);
        selectdate=(Button)findViewById(R.id.button11);
        next=(Button)findViewById(R.id.button14);
        calendar=Calendar.getInstance();
        String subject=getIntent().getStringExtra("id");
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd MM yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                daatte=sdf.format(calendar.getTime());
                datte.setText("DATE : "+daatte);
                selectdate.setVisibility(View.GONE);
                datte.setVisibility(View.VISIBLE);

            }

        };
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetailsActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(DetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String timee=selectedHour+":"+selectedMinute;
                        timee11=String.valueOf(selectedHour);
                        timee12=String.valueOf(selectedMinute);
                        ttime1=timee.trim();
                        time1.setText("START TIME : "+ttime1);
                        starttime.setVisibility(View.GONE);
                        time1.setVisibility(View.VISIBLE);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        duration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTimee = Calendar.getInstance();
                int hourr = mcurrentTimee.get(Calendar.HOUR_OF_DAY);
                int minutee = mcurrentTimee.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(DetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker,int selectedHourr, int selectedMinutee) {
                        String timeeee=selectedHourr+":"+selectedMinutee;
                        timee21=String.valueOf(selectedHourr);
                        timee22=String.valueOf(selectedMinutee);
                        ttime2=timeeee.trim();
                        time2.setText("END TIME : "+ttime2);
                        duration.setVisibility(View.GONE);
                        time2.setVisibility(View.VISIBLE);

                    }
                }, hourr, minutee, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=examcode.getText().toString();
                if (code.isEmpty()){
                    examcode.setError("Exam Code Required");
                }
                if (daatte==null){
                    Toast.makeText(DetailsActivity.this, "Please Select Date of Exam", Toast.LENGTH_SHORT).show();
                }
                if (ttime1==null){
                    Toast.makeText(DetailsActivity.this, "Please Select Exam Start Time", Toast.LENGTH_SHORT).show();
                }
                if (ttime2==null){
                    Toast.makeText(DetailsActivity.this, "Please Select Exam End Time", Toast.LENGTH_SHORT).show();
                }
                if (!code.isEmpty() && daatte!=null && ttime1!=null && ttime2!=null){
                    Intent details=new Intent(DetailsActivity.this,AdminActivity.class);
                    details.putExtra("examcode",code);
                    details.putExtra("date",daatte);
                    details.putExtra("starttime1",timee11);
                    details.putExtra("endtime1",timee12);
                    details.putExtra("starttime2",timee21);
                    details.putExtra("endtime2",timee22);
                    details.putExtra("subject",subject);
                    startActivity(details);
                }
            }
        });



    }
}