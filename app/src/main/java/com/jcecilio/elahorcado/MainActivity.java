package com.jcecilio.elahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int[] idDrawable = {R.drawable.ahorcado_00_, R.drawable.ahorcado_01_, R.drawable.ahorcado_02_,
            R.drawable.ahorcado_03_, R.drawable.ahorcado_04_, R.drawable.ahorcado_05_, R.drawable.ahorcado_06_ };
    private int muneco [] = new int[]{0, 0, 0, 0, 0, 0, 0};

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String mPath = "animales.txt";
    private PalabrasAnimales mNombreAnimales;
    private List<String> mLines;
    private Switch swt;
    private TextView miTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Lee txt e introduce nombre de animal
        final TextView miTexto = findViewById(R.id.texto);
        mNombreAnimales = new PalabrasAnimales(this);
        mLines = mNombreAnimales.readLines(mPath);
        miTexto.setText( mLines.get(25));

        //Texto lo pasamos a invisible
        miTexto.setVisibility(View.INVISIBLE);

        //Igualar nombre a palabra en guiones
        final TextView palabra = findViewById(R.id.palabra);
        String nom = String.valueOf(miTexto.getText());
        String guion = "-";
        String g = "-";
        for (int i = 0; i < nom.length(); i++){
            guion = guion + g;
        }
        palabra.setText(guion);

        //Poner letra y letras usadas
        final TextView letra = findViewById(R.id.letra);
        final TextView usadas = findViewById(R.id.txUsadas);

        //Reseteo de caja cada 2 segundos
        for(int i = 0; i < muneco.length; i++){
            new CountDownTimer(2000, 1000){
                @Override
                public void onTick(long millisUntilFinished) {}
                @Override
                public void onFinish() {
                    letra.setText("");
                    /* Esta función la comento porque me falla el emulador. No se si porque esta mal
                    algo o por que mi ordenador no tira las cuentas atras. Es el mismo error que te
                    comente por Binarizate.*/
                    //ahorcado();
                }
            }.start();
        }

        //Acciones que se realizar al meter una letra.
        letra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                //Letra y nombre animal
                String l = String.valueOf(letra.getText());
                String n = String.valueOf(miTexto.getText());

                //Para letras usadas que no están
                String usada = String.valueOf(usadas.getText());

                String pala = String.valueOf(palabra.getText());

                //Igualar letras a mayus para que no diferencie
                l.toUpperCase();
                n.toUpperCase();

                //Intento de funcion para cambiar los "-" por la latra acertada.
                if (n.contains(l)){
                    for (int j = 0; j < n.length(); j++){
                        pala.replaceAll("-", l);
                        palabra.setText(pala);
                    }
                } else {
                    usada = usada + l;
                    usadas.setText(usada);
                }

            }
        });
    }

    //poner palitos al ahorcado
    public void ahorcado(){
        int s = 1;
        int pos = muneco.length;
        while (muneco[pos] != 0){
            pos = muneco.length;
        }
        ImageView im = (ImageView) findViewById(idDrawable[pos]);
        switch (s){
            case 1:
                im.setBackgroundResource(R.drawable.ahorcado_06_);
                muneco [pos] = 1;
                s++;
                break;
            case 2:
                im.setBackgroundResource(R.drawable.ahorcado_05_);
                muneco [pos] = 1;
                s++;
                break;
            case 3:
                im.setBackgroundResource(R.drawable.ahorcado_04_);
                muneco [pos] = 1;
                s++;
                break;
            case 4:
                im.setBackgroundResource(R.drawable.ahorcado_03_);
                muneco [pos] = 1;
                s++;
                break;
            case 5:
                im.setBackgroundResource(R.drawable.ahorcado_02_);
                muneco [pos] = 1;
                s++;
                break;
            case 6:
                im.setBackgroundResource(R.drawable.ahorcado_01_);
                muneco [pos] = 1;
                s++;
                break;
            case 7:
                im.setBackgroundResource(R.drawable.ahorcado_00_);
                muneco [pos] = 1;
                s++;
                break;
            default:
                break;
        }
    }

    //Poner invisible o visible la palabra.
    public void onclick(View view) {
        swt = (Switch) findViewById(R.id.switchTxt);
        miTxt = (TextView) findViewById(R.id.texto);
        if (view.getId()==R.id.switchTxt){
            if (swt.isChecked()){
                miTxt.setVisibility(View.VISIBLE);
            } else {
                miTxt.setVisibility(View.INVISIBLE);
            }
        }
    }
}