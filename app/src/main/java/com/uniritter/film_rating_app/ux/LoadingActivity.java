package com.uniritter.film_rating_app.ux;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.uniritter.film_rating_app.R;
import com.uniritter.film_rating_app.ui.TelaPrincipal_Activity;

public class LoadingActivity extends AppCompatActivity {
    private static final int SPLASH_DELAY = 3000; // Tempo de exibição da splash screen em milissegundos (3 segundos)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Defina o layout da splash screen
        setContentView(R.layout.activity_loading);

        // Usando um Handler para atrasar a abertura da próxima atividade
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Iniciar a próxima atividade após o tempo de atraso
                Intent intent = new Intent(LoadingActivity.this, TelaPrincipal_Activity.class);
                startActivity(intent);

                // Finalizar a atividade da splash screen para que não seja possível voltar a ela pressionando o botão "Voltar"
                finish();
            }
        }, SPLASH_DELAY);
    }
}