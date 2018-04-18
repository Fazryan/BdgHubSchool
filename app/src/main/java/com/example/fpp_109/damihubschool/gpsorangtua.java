package com.example.fpp_109.damihubschool;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;

import gojay.Widget.ToastProgress;



public class gpsorangtua extends AppCompatActivity {

    private ActionBarDrawerToggle mToogle;
    TextView chat, profil, call;
    private View v, myLoc;
    private GoogleMap.OnMyLocationButtonClickListener callmyloc;
    boolean movetomylocation = false;
    private GoogleMap gmaps;
    private ToastProgress tprog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsorangtua);


        DrawerLayout mDrawer = findViewById(R.id.drawer);
        mToogle = new ActionBarDrawerToggle(this, mDrawer, R.string.open, R.string.close);
        mDrawer.addDrawerListener(mToogle);
        mToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chat = findViewById(R.id.chatDiscus);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DriverMessage.class);
                startActivity(intent);
            }
        });
        profil = findViewById(R.id.ProDriv);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), profilDriver.class);
                startActivity(in);
            }
        });


        dialContactPhone("");


    }




    private void dialContactPhone ( final String phoneNumber){
        call = findViewById(R.id.Calll);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
                case R.id.menuLogout:

                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(this, gpsorangtua.class));

                    break;
            }
            return mToogle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

        }
        public void total (MenuItem item){
            Intent intent = new Intent(this, Profil.class);
            startActivity(intent);
        }
        public void quit (MenuItem item){
            AlertDialog.Builder builder = new AlertDialog.Builder(gpsorangtua.this);
            builder.setMessage("Yakin Ingin Keluar?");
            builder.setCancelable(true);
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    FirebaseAuth.getInstance().signOut();
                    finish();

                }
            });
            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }



    }



