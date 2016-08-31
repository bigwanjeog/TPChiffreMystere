package com.example.renaud.tpchiffremystere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Gagner extends AppCompatActivity {

    Button activityGagnerBtnReset;
    TextView activityGagnerTvGG;
    Intent i;// = new Intent(Gagner.this, MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i = new Intent (Gagner.this, MainActivity.class);
        loadActivityGagner();
    }

    public void loadActivityGagner(){
        setContentView(R.layout.activity_gagner);

        activityGagnerBtnReset = (Button) findViewById(R.id.activityGagnerBtnReset);
        activityGagnerTvGG = (TextView) findViewById(R.id.activityGagnerTvGG);
        String texteVictoire = "Bravo !!\nTu as gagné avec le nombre " + (String)getIntent().getExtras().get("numTest");
        activityGagnerTvGG.setText(texteVictoire);

        activityGagnerBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
    }
}
