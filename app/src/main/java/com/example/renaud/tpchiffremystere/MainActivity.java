package com.example.renaud.tpchiffremystere;

import android.content.Intent;
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
    int numMystere;//numéro mystere généré par la class random
    int cptTest;//nombre de test effectué
    String essais;// String pour le setText du nombre d'essais restants
    final static int NBDETEST = 5;//nombre de test a faire

    Button activityMainBtnValider;
    Button activityMainBtnReset;
    EditText activityMainTfNumTest;
    TextView activityMainTvPlusMoins;
    TextView activityMainTvEssai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadMainActivity();//lancer l'activity
    }

    /**
     * lancer l'activity, attribution au variables, liaison XML a Java,
     * création de l'écouteur du bouton valider
     */
    public void loadMainActivity() {
        setContentView(R.layout.activity_main);

        cptTest = 0;//Mise a 0 du compteur
        numMystere = rdm.nextInt(100) + 1;//Attribution du numéro a trouver

        activityMainBtnValider = (Button) findViewById(R.id.activityMainBtnValider);
        activityMainBtnReset = (Button) findViewById(R.id.activityMainBtnReset);
        activityMainTfNumTest = (EditText) findViewById(R.id.activityMainTfNumTest);
        activityMainTvPlusMoins = (TextView) findViewById(R.id.activityMainTvPlusMoins);
        activityMainTvEssai = (TextView) findViewById(R.id.activityMainTvEssai);

        essais = (NBDETEST - cptTest) + " essais restants";//texte afficher dans activityMainTvEssai
        activityMainTvEssai.setText(essais);//texte afficher dans activityMainTvEssai

        activityMainBtnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    numTest = Integer.parseInt(activityMainTfNumTest.getText().toString());//test si la donné reçu est bien un int
                    verificationNumSup100(numTest);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Entrez un nombre entre 1 et 100 inclus", Toast.LENGTH_LONG).show();//erreur si la donné reçu n'est pas un int
                }
            }
        });
    }

    /**
     * Verifie si le numéro recu est bien comprit entre 1 et 100 inclus
     * @param numTest le numéro saisie
     */
    public void verificationNumSup100(int numTest){
        if(numTest > 100 || numTest == 0){
            Toast.makeText(MainActivity.this, "Entrez un nombre entre 1 et 100 inclus", Toast.LENGTH_LONG).show();//erreur si le nombre n'est pas situé entre 1 et 100 inclus
        } else {
            verificationNum(numTest);
        }
    }

    /**
     * Vérifie si le numéro est plus grand ou plus petit ou égale
     * @param numTest le numéro saisie
     */
    public void verificationNum(int numTest) {
        String resultat = "";
        cptTest++;
        if (numTest < numMystere) {
            resultat = verificationCptPlus();
        } else if (numTest > numMystere) {
            resultat = verificationCptMoins();
        } else {
            gagner();//si numTest est égale a numMystere
        }
        essais = (NBDETEST - cptTest) + " essais restants";
        activityMainTvEssai.setText(essais);
        activityMainTvPlusMoins.setText(resultat);
    }

    /**
     * Verifie si le nombre d'essais n'est pas expiré pour le cas PLUS
     * @return resultat
     */
    public String verificationCptPlus() {
        String resultat;
        if (cptTest < NBDETEST) {
            resultat = "PLUS";
        } else{
            resultat = perdu();//si le nombre d'essais est expiré
        }
        return resultat;
    }

    /**
     * Verifie si le nombre d'essais n'est pas expiré pour le cas MOINS
     * @return resultat
     */
    public String verificationCptMoins() {
        String resultat;
        if (cptTest < NBDETEST) {
            resultat = "MOINS";
        } else{
            resultat = perdu();//si le nombre d'essais est expiré
        }
        return resultat;
    }

    /**
     * Renvoie le texte de victoire, change la couleur et invoque la fonction reset()
     */
    public void gagner() {
        Intent i = new Intent(MainActivity.this, Gagner.class);
        i.putExtra("cptTest", Integer.toString(cptTest));
        startActivity(i);
    }

    /**
     * Renvoie le texte de défaite et invoque la fonction reset()
     * @return resultat
     */
    public String perdu() {
        String resultat = "PERDU !!\n La bonne réponse était " + numMystere;
        reset();
        return resultat;
    }

    /**
     * Désactive le bouton valider et rajoute le bouton Rejouer qui permet de relancer
     * loadMainActivity pour recommener une partie
     */
    public void reset() {
        activityMainBtnValider.setEnabled(false);
        activityMainBtnReset.setVisibility(View.VISIBLE);
        activityMainBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMainActivity();
            }
        });
    }
}
