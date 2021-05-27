package com.example.finalpareeksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpasswordActivity extends AppCompatActivity {

    EditText emailid;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        emailid=(EditText)findViewById(R.id.editTextTextPersonName7);
        send=(Button)findViewById(R.id.button5);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=emailid.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (em.isEmpty()){
                    emailid.setError("Email Required");
                }
                if (!em.matches(emailPattern)){
                    emailid.setError("Enter Valid Email");
                }
                if (!em.isEmpty() && em.matches(emailPattern)){
                    FirebaseAuth.getInstance().sendPasswordResetEmail(em).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ForgotpasswordActivity.this, "Reset Link Send", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ForgotpasswordActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}