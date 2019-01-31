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
import android.widget.Toast;

public class Editar_Contacto extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditText [] et_telefono = new EditText[6];
    EditText [] et_correo = new EditText[6];

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
       recoger_datos();

    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guardar_editar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        recoger_preferencias();
        Toast.makeText(this, getResources().getString(R.string.toast_editar), Toast.LENGTH_LONG).show();
        return false;
    }
    public void recoger_preferencias()
    {
        int contador=1;
        for (int j=0;j<et_correo.length;j++)
        {
            String tipo_telf = "telefono_contacto";
            String tipo_correo = "correo_contacto";
            tipo_telf += contador;
            tipo_correo += contador;
            String temp_telf = et_telefono[j].getText().toString();
            String temp_correo = et_correo[j].getText().toString();
            editor.putString(tipo_telf, temp_telf);
            editor.putString(tipo_correo, temp_correo);
            editor.commit();
            contador++;
        }

    }
    public void recoger_datos()
    {
        String telefono,correo;
        int contador=1;
        for (int j=0;j<et_correo.length;j++)
        {
            String tipo_telf = "telefono_contacto";
            String tipo_correo = "correo_contacto";
            tipo_telf += contador;
            tipo_correo += contador;
            String temp_telf=sp.getString(tipo_telf,null);
            String temp_correo=sp.getString(tipo_correo,null);
            et_telefono[j].setText(temp_telf);
            et_correo[j].setText(temp_correo);
            contador++;
        }
    }
}
