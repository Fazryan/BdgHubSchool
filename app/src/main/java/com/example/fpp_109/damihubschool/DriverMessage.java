package com.example.fpp_109.damihubschool;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;


public class DriverMessage extends AppCompatActivity  {
    //int SIGN_IN_REQUEST_CODE = 1;
    FirebaseAuth mAuth;
    ImageButton back,cm,call,profil;
    TextView tx;
    ImageView tx2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_message);
        displayMessages();
        mAuth = FirebaseAuth.getInstance();
        //if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            //startActivityForResult(
                    //AuthUI.getInstance()
                            //.createSignInIntentBuilder()
                            //.build(),
                    //SIGN_IN_REQUEST_CODE
            //);
        //} else {
            //Toast.makeText(this,
                    //"Hello " + FirebaseAuth.getInstance()
                            //.getCurrentUser()
                            //.getDisplayName(),
                    //Toast.LENGTH_LONG)
                    //.show();
            //displayMessages();
        //}

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(getApplicationContext(),gpsorangtua.class);
                startActivity(in);
            }
        });



        tx=findViewById(R.id.tx);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(getApplicationContext(),profilDriver.class);
                startActivity(in);
            }
        });








        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = findViewById(R.id.input);
                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue(new MessageModel(input.getText().toString(),
                                mAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );
                input.setText("");
            }
        });
        dialContactPhone("");
    }



    private void dialContactPhone(final String phoneNumber) {
        cm=findViewById(R.id.cm);
        cm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
        }
        });
    }

    public void displayMessages(){
        ListView listOfMessages = findViewById(R.id.list_of_messages);

        FirebaseListAdapter<MessageModel> adapter = new FirebaseListAdapter<MessageModel>(this, MessageModel.class,
                R.layout.list_item, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, MessageModel model, int position) {
                TextView messageText = v.findViewById(R.id.message_text);
                TextView messageUser = v.findViewById(R.id.message_user);
                TextView messageTime = v.findViewById(R.id.message_time);
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

    //@Override
    //protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //    super.onActivityResult(requestCode, resultCode, data);
    //
    //    if(requestCode == SIGN_IN_REQUEST_CODE) {
    //        if(resultCode == RESULT_OK) {
    //            Toast.makeText(this,
    //                    "Success!",
    //                    Toast.LENGTH_LONG)
    //                    .show();
    //            displayMessages();
    //        } else {
    //            Toast.makeText(this,
    //                    "Please try again later.",
    //                    Toast.LENGTH_LONG)
    //                    .show();
    //            finish();
    //        }
    //    }
    //}

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.menu_main, menu);
    //    return true;
    //}
    //
    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
    //    if(item.getItemId() == R.id.menu_sign_out) {
    //        AuthUI.getInstance().signOut(this)
    //                .addOnCompleteListener(new OnCompleteListener<Void>() {
    //                    @Override
    //                    public void onComplete(@NonNull Task<Void> task) {
    //                        Toast.makeText(DriverMessage.this,
    //                                "signed out.",
    //                                Toast.LENGTH_LONG)
    //                                .show();
    //                        finish();
    //                    }
    //                });
    //    }
    //    return true;
    //}
}
