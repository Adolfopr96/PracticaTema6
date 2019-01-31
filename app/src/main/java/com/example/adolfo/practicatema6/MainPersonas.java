package com.example.adolfo.practicatema6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.Manifest.permission.CALL_PHONE;

public class MainPersonas extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    ImageView [] img = new ImageView[6];
    EditText et_telefono, et_correo;
    Intent i1, i2;
    String correo="";
    String telefono="";
    String tipo;
    String temp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_personas);
        img[0] = findViewById(R.id.imageView1_personas);
        img[1] = findViewById(R.id.imageView2_personas);
        img[2] = findViewById(R.id.imageView3_personas);
        img[3] = findViewById(R.id.imageView4_personas);
        img[4] = findViewById(R.id.imageView5_personas);
        img[5] = findViewById(R.id.imageView6_personas);
        for (int i=0; i<=5;i++)
        {
            registerForContextMenu(img[i]);
        }
        sp =getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        editor = sp.edit();

        img[0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                temp="contacto1";
                return false;
            }
        });
        img[1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                temp="contacto2";
                return false;
            }
        });
        img[2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                temp="contacto3";
                return false;
            }
        });
        img[3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                temp="contacto4";
                return false;
            }
        });
        img[4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                temp="contacto5";
                return false;
            }
        });
        img[5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                temp="contacto6";
                return false;
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_personas, menu);

    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editar, menu);
        return true;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_item1_personas:
                tipo="telefono_";
                tipo+=temp;
                realizar_llamada();
                Log.i("APP1",telefono);
                break;
            case R.id.menu_item2_personas:
                tipo="correo_";
                tipo+= temp;
                enviar_correo();
                Log.i("APP1",correo);
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void realizar_llamada() {
        telefono=sp.getString(tipo,null);
        if(telefono.isEmpty())
        {
            Toast.makeText(this, "ERROR, No hay nº de tef asociado.", Toast.LENGTH_LONG).show();
        }
        else {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Uri call = Uri.parse("tel: " + telefono);
                Intent surf = new Intent(Intent.ACTION_CALL, call);
                startActivity(surf);
            } else {
                requestPermissions(new String[]{CALL_PHONE}, 1);
            }
        }

    }
    public void enviar_correo() {
        correo=sp.getString(tipo, null);
        if (correo.isEmpty())
        {
            Toast.makeText(this, "Error, no hay correo asociado", Toast.LENGTH_LONG).show();
        } else {
            Intent intent_correo = new Intent(Intent.ACTION_SEND);
            intent_correo.setType("text/plain");
            intent_correo.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
            startActivity(intent_correo);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent2 = new Intent(this, Editar_Contacto.class);
        startActivity(intent2);
        return true;
    }
    }

