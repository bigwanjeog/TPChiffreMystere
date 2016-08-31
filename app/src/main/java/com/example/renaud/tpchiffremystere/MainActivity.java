package com.example.renaud.tpchiffremystere;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rdm = new Random();
    int numTest;//numéro entré a tester
    int numMystere = rdm.nextInt(100) + 1;
    int cptTest = 0;//nombre de test effectué
    final static int NBDETEST =4;//nombre de test a faire

    Button activityMainBtnValider;
    EditText activityMainTfNumTest;
    TextView activityMainTvPlusMoins;
    TextView activityMainTvEssai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBtnValider = (Button) findViewById(R.id.activityMainBtnValider);
        activityMainTfNumTest = (EditText) findViewById(R.id.activityMainTfNumTest);
        activityMainTvPlusMoins = (TextView) findViewById(R.id.activityMainTvPlusMoins);
        activityMainTvEssai = (TextView) findViewById(R.id.activityMainTvEssai);

        activityMainTvEssai.setText((NBDETEST + 1 - cptTest) + " essais restants");
        activityMainBtnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    numTest = Integer.parseInt(activityMainTfNumTest.getText().toString());
                    verificationNumSup100(numTest);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Entrez un nombre entre 1 et 100 inclus", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void verificationNumSup100(int numTest){
        if(numTest > 100 || numTest == 0){
            Toast.makeText(MainActivity.this, "Entrez un nombre entre 1 et 100 inclus", Toast.LENGTH_LONG).show();
        } else {
            verificationNum(numTest);
        }
    }

    public void verificationNum(int numTest) {
        if (cptTest < NBDETEST){
            if (numTest < numMystere){
                activityMainTvPlusMoins.setText("PLUS");
            } else if (numTest > numMystere){
                activityMainTvPlusMoins.setText("MOINS");
            } else {
                //TODO recommencer
                activityMainTvPlusMoins.setTextColor(Color.GREEN);
                activityMainTvPlusMoins.setText("GG");
                activityMainBtnValider.setEnabled(false);
            }
        } else{
            //TODO recommencer
            activityMainTvPlusMoins.setText("PERDU !!\n La bonne réponse était " + numMystere);
            activityMainBtnValider.setEnabled(false);
        }
        cptTest++;
        activityMainTvEssai.setText((NBDETEST + 1 - cptTest) + " essais restants");
    }
}
