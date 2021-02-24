package com.example.multimediademoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileDetailsActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
//    private FirebaseDatabase firebaseDatabase;

    TextView Name;
    TextView Email;
    TextView Id;
     Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        firebaseAuth=FirebaseAuth.getInstance();

//        firebaseDatabase=FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());


        Name=findViewById(R.id.nametxt);
        Email=findViewById(R.id.emailtxt);
        Id=findViewById(R.id.idtxt);
        logout=findViewById(R.id.btnlogout);

        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(ProfileDetailsActivity.this);
        if (account!=null){
            String personName=account.getDisplayName();
            String email=account.getEmail();
            String personId=account.getId();
            Uri personPhoto=account.getPhotoUrl();

            Name.setText ("Person name     " +personName);
            Email.setText("Email details   "  +email);
            Id.setText("Id details         "  +personId);
//            Glide.with(this).load(personPhoto).into(photo);

        }
        else {

        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(ProfileDetailsActivity.this, LoginActivity.class));
            }
        });


    }
}
