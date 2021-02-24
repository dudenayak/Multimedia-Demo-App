package com.example.multimediademoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MusicProfileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Profile");

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

         //int
        firebaseAuth=FirebaseAuth.getInstance();
        songlist=findViewById(R.id.songlist);
        arrayList= new ArrayList<>();
//        arrayList.add(new Music("Fihaal","Prank",R.raw.fillahal));
        arrayList.add(new Music("TumHiAnna","Arjit Singh",R.raw.tum));
        arrayList.add(new Music("Haiyo","Arjit Singh",R.raw.haiyo));
        arrayList.add(new Music("Kina Sona","Arjit Singh",R.raw.kinasona));


        adapter=new CustomMusicAdapter(this,R.layout.custom_music_item,arrayList);
        songlist.setAdapter(adapter);


    }

    private void checkUserStatus(){
        //get current user
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if (user!=null){
            //user in signed in stay here
        }
        else{
            //user not signed in,go to main activity
            startActivity(new Intent(MusicProfileActivity.this,LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        //check on start of app
        checkUserStatus();
        super.onStart();
    }

    /* inflate option menu*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflating menu
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* handle menu option click*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get item id
        int id=item.getItemId();
        if(id==R.id.profile_details){
//            firebaseAuth.signOut();
//            checkUserStatus();

            Intent intent=new Intent(MusicProfileActivity.this,ProfileDetailsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
