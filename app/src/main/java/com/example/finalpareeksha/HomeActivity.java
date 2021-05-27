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
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    EditText email,password;
    Button login,signup,forgot;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email=(EditText)findViewById(R.id.editTextTextPersonName);
        password=(EditText)findViewById(R.id.editTextTextPassword);
        login=(Button)findViewById(R.id.button);
        forgot=(Button)findViewById(R.id.button4);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forr=new Intent(HomeActivity.this,ForgotpasswordActivity.class);
                startActivity(forr);
            }
        });
        signup=(Button)findViewById(R.id.button3);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newhere=new Intent(HomeActivity.this,SignupActivity.class);
                startActivity(newhere);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaill=email.getText().toString();
                String pass=password.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passwordPattern= "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
                if (emaill.isEmpty()){
                    email.setError("Email ID Required");
                }
                if (pass.isEmpty()){
                    password.setError("Password Required");
                }
                if (!emaill.matches(emailPattern)){
                    email.setError("Enter Valid Email ID");
                }
                if (emaill.equals("Admin@gmail.com") && pass.equals("Admin@10")){
                    Intent add=new Intent(HomeActivity.this,SubjectActivity.class);
                    Toast.makeText(HomeActivity.this, "Welcome Admin", Toast.LENGTH_LONG).show();
                    startActivity(add);
                    finish();
                }
                if (!pass.matches(passwordPattern)){
                    password.setError("Password must contain minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
                }
                if (!(emaill.isEmpty() && pass.isEmpty() && emaill.equals("Admin@gmail.com") && pass.equals("Admin@10")) && pass.matches(passwordPattern) && emaill.matches(emailPattern)){
                    mAuth=FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(emaill,pass).addOnCompleteListener(HomeActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent complete=new Intent(HomeActivity.this,ExamcodeActivity.class);
                                startActivity(complete);
                                finish();
                            }else {
                                Toast.makeText(HomeActivity.this, "Student Not Found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
    /*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent done=new Intent(HomeActivity.this,FinalActivity.class);
            startActivity(done);
            finish();
        }
    } */
}