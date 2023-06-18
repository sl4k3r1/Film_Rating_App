package com.uniritter.film_rating_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.uniritter.film_rating_app.R;

public class TelaPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.login) {
            Intent intent = new Intent(this, TelaLoginActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.perfil) {
            // Abrir a tela de perfil
        } else if (itemId == R.id.sair) {
            Intent intent = new Intent(this, TelaPerfilDadosActivity.class);
            startActivity(intent);
        }

        return true;
    }
}