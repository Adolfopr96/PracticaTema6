package com.example.adolfo.practicatema6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Editar_Contacto extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String telefono,correo;
    EditText [] et_telefono = new EditText[6];
    EditText [] et_correo = new EditText[6];

    int contador=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__contacto);
        et_telefono[0] = findViewById(R.id.et1_llamar_editar);
        et_telefono[1] = findViewById(R.id.et2_llamar_editar);
        et_telefono[2] = findViewById(R.id.et3_llamar_editar);
        et_telefono[3] = findViewById(R.id.et4_llamar_editar);
        et_telefono[4]= findViewById(R.id.et5_llamar_editar);
        et_telefono[5] = findViewById(R.id.et6_llamar_editar);

        et_correo[0] = findViewById(R.id.et1_correo_editar);
        et_correo[1] = findViewById(R.id.et2_correo_editar);
        et_correo[2] = findViewById(R.id.et3_correo_editar);
        et_correo[3] = findViewById(R.id.et4_correo_editar);
        et_correo[4] = findViewById(R.id.et5_correo_editar);
        et_correo[5] = findViewById(R.id.et6_correo_editar);
       sp= getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
       editor= sp.edit();

    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guardar_editar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("APP1", "AQUÍ");
        recoger_telefonos();
        recoger_correos();
        return true;
    }
    public void recoger_telefonos()
    {
        int contador=1;
        String temp;
        for (int j=0; j<=5;j++)
        {
            String tipo="telefono_contacto";
            tipo+=contador;
            temp= et_telefono[j].getText().toString();
            editor.putString(tipo, temp);
            editor.commit();
            contador++;
        }
    }
    public void recoger_correos()
    {
        int contador=1;
        String temp;
        for (int j=0; j<=5;j++)
        {
            String tipo="correo_contacto";
            tipo+=contador;
            temp= et_correo[j].getText().toString();
            editor.putString(tipo, temp);
            editor.commit();
            contador++;
        }
    }

}
