package com.example.finalpareeksha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    EditText emailid,passs,passs2;
    Button signup,login;
    FirebaseAuth mAuth2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emailid=(EditText)findViewById(R.id.editTextTextPersonName2);
        passs=(EditText)findViewById(R.id.editTextTextPassword2);
        passs2=(EditText)findViewById(R.id.editTextTextPassword3);
        signup=(Button)findViewById(R.id.button2);
        mAuth2=FirebaseAuth.getInstance();
        login=(Button)findViewById(R.id.button11);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logged=new Intent(SignupActivity.this,HomeActivity.class);
                startActivity(logged);
                finish();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eml=emailid.getText().toString();
                String ps=passs.getText().toString();
                String ps2=passs2.getText().toString();
                String emaillPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passworddPattern= "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
                if (eml.isEmpty()){
                    emailid.setError("Email Required");
                }
                if (ps.isEmpty()){
                    passs.setError("Password Required");
                }
                if (ps2.isEmpty()){
                    passs2.setError("Password Required");
                }
                if (!eml.matches(emaillPattern)){
                    emailid.setError("Enter Valid Email");
                }
                if (!ps.matches(passworddPattern)){
                    passs.setError("Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
                }
                if (!ps.equals(ps2)){
                    passs2.setError("Password Does not matches");
                    passs.setError("Password Does not matches");
                }
                if (!ps2.matches(passworddPattern)){
                    passs2.setError("Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
                }
                if (!(eml.isEmpty() && ps.isEmpty() && ps2.isEmpty()) && ps.matches(passworddPattern) && ps2.matches(passworddPattern) && eml.matches(emaillPattern) && ps.equals(ps2)){
                    mAuth2.createUserWithEmailAndPassword(eml,ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent created=new Intent(SignupActivity.this,HomeActivity.class);
                                Toast.makeText(SignupActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                startActivity(created);
                                finish();
                            }else {
                                Toast.makeText(SignupActivity.this, "Something went wrong please try again later", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}