package com.example.adolfo.practicatema6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainBloc extends AppCompatActivity {
    EditText et_bloc;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bloc);
        sp = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        et_bloc = findViewById(R.id.et_bloc);
        et_bloc.setText(sp.getString("et_bloc_texto", ""));

    }
    @SuppressLint("NewApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("et_bloc_texto", et_bloc.getText().toString());
        editor.commit();
        return false;
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_guardar_editar, menu);
        return true;
    }
}
