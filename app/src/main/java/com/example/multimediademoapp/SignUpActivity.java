package com.example.multimediademoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText regname,regpass,regemail;
    Button regbtn;
    TextView userLogin;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        regname=findViewById(R.id.namereg);
        regpass=findViewById(R.id.passreg);
        regemail=findViewById(R.id.emailreg);
        regbtn=findViewById(R.id.regbtn);
        userLogin=findViewById(R.id.userreg);

        auth=FirebaseAuth.getInstance();

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    // Upload data to database
                    String user_email=regemail.getText().toString().trim();
                    String user_password=regpass.getText().toString().trim();

                    auth.createUserWithEmailAndPassword(user_email,user_password).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this,"Registration successful",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
                                    }
                                    else
                                    {
                                        Toast.makeText(SignUpActivity.this,"registration failed",Toast.LENGTH_LONG).show();
                                    }

                                }
                            });

                };
            }
        });
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }

    private  Boolean validate(){
        Boolean result=false;
        String name=regname.getText().toString();
        String password=regpass.getText().toString();
        String email=regemail.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"Please enter all details",Toast.LENGTH_LONG).show();
        }
        else{
            result=true;
        }
        return result;
    }
}
