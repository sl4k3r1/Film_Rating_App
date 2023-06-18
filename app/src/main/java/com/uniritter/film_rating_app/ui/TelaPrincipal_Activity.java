package com.uniritter.film_rating_app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;

import com.uniritter.film_rating_app.R;
import com.uniritter.film_rating_app.adapter.FilmesAdapter;
import com.uniritter.film_rating_app.model.Filme;
import com.uniritter.film_rating_app.model.SessionManager;
import com.uniritter.film_rating_app.web_request.WebFilmesAtuais;
import com.uniritter.film_rating_app.web_request.WebFilmesMelhoresAvaliados;
import com.uniritter.film_rating_app.web_request.WebFilmesPopulares;
import com.uniritter.film_rating_app.web_request.WebFilmesVindoAi;

import java.util.List;

public class TelaPrincipal_Activity extends AppCompatActivity {
    private RecyclerView recyclerViewAtuais;
    private RecyclerView recyclerViewPopulares;
    private RecyclerView recyclerViewMelhoresAvaliados;
    private RecyclerView recyclerViewVindoAi;
    private FilmesAdapter adapterAtuais;
    private FilmesAdapter adapterPopulares;
    private FilmesAdapter adapterMelhoresAvaliados;
    private FilmesAdapter adapterVindoAi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregaRecyclerViewFilmesAtuais();
        carregaRecyclerViewFilmesPopulares();
        carregaRecyclerViewFilmesMelhoresAvaliados();
        carregaRecyclerViewFilmesVindoAi();
    }

    public void carregaRecyclerViewFilmesAtuais() {
        recyclerViewAtuais = findViewById(R.id.recyclerViewMaisRecentes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewAtuais.setLayoutManager(layoutManager);

        WebFilmesAtuais webFilmesAtuais = new WebFilmesAtuais();
        webFilmesAtuais.carregarFilmesAtuais(filmes -> {
            new Handler(Looper.getMainLooper()).post(() -> {
                adapterAtuais = new FilmesAdapter(filmes);
                recyclerViewAtuais.setAdapter(adapterAtuais);

                adapterAtuais.setOnItemClickListener(filme -> {
                    if (isLoggedIn()) {
                        Intent intent = new Intent(TelaPrincipal_Activity.this, DetalhesFilmeActivity.class);
                        intent.putExtra("filme", filme);
                        startActivity(intent);
                    } else {
                        redirectToLogin();
                    }
                });
            });
        });
    }

    public void carregaRecyclerViewFilmesPopulares() {
        recyclerViewPopulares = findViewById(R.id.recyclerViewPopulares);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopulares.setLayoutManager(layoutManager);

        WebFilmesPopulares webFilmesPopulares = new WebFilmesPopulares();
        webFilmesPopulares.carregarFilmesPopulares(filmes -> {
            new Handler(Looper.getMainLooper()).post(() -> {
                adapterPopulares = new FilmesAdapter(filmes);
                recyclerViewPopulares.setAdapter(adapterPopulares);

                adapterPopulares.setOnItemClickListener(filme -> {
                    if (isLoggedIn()) {
                        Intent intent = new Intent(TelaPrincipal_Activity.this, DetalhesFilmeActivity.class);
                        intent.putExtra("filme", filme);
                        startActivity(intent);
                    } else {
                        redirectToLogin();
                    }
                });
            });
        });
    }

    public void carregaRecyclerViewFilmesMelhoresAvaliados() {
        recyclerViewMelhoresAvaliados = findViewById(R.id.recyclerViewMelhoresAvaliados);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMelhoresAvaliados.setLayoutManager(layoutManager);

        WebFilmesMelhoresAvaliados webFilmesMelhoresAvaliados = new WebFilmesMelhoresAvaliados();
        webFilmesMelhoresAvaliados.carregarFilmesMelhoresAvaliados(filmes -> {
            new Handler(Looper.getMainLooper()).post(() -> {
                adapterMelhoresAvaliados = new FilmesAdapter(filmes);
                recyclerViewMelhoresAvaliados.setAdapter(adapterMelhoresAvaliados);

                adapterMelhoresAvaliados.setOnItemClickListener(filme -> {
                    if (isLoggedIn()) {
                        Intent intent = new Intent(TelaPrincipal_Activity.this, DetalhesFilmeActivity.class);
                        intent.putExtra("filme", filme);
                        startActivity(intent);
                    } else {
                        redirectToLogin();
                    }
                });
            });
        });
    }

    public void carregaRecyclerViewFilmesVindoAi() {
        recyclerViewVindoAi = findViewById(R.id.recyclerViewVindoAi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewVindoAi.setLayoutManager(layoutManager);

        WebFilmesVindoAi webFilmesVindoAi = new WebFilmesVindoAi();
        webFilmesVindoAi.carregarFilmesWebFilmesVindoAi(filmes -> {
            new Handler(Looper.getMainLooper()).post(() -> {
                adapterVindoAi = new FilmesAdapter(filmes);
                recyclerViewVindoAi.setAdapter(adapterVindoAi);

                adapterVindoAi.setOnItemClickListener(filme -> {
                    if (isLoggedIn()) {
                        Intent intent = new Intent(TelaPrincipal_Activity.this, DetalhesFilmeActivity.class);
                        intent.putExtra("filme", filme);
                        startActivity(intent);
                    } else {
                        redirectToLogin();
                    }
                });
            });
        });
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
            if (!isLoggedIn()) {
                Intent intent = new Intent(this, TelaLoginActivity.class);
                startActivity(intent);
            }
        } else if (itemId == R.id.perfil) {
            if (isLoggedIn()) {
                Intent intent = new Intent(this, TelaPerfilActivity.class);
                startActivity(intent);
            } else {
                redirectToLogin();
            }
        } else if (itemId == R.id.sair) {
            if (isLoggedIn()) {
                Intent intent = new Intent(this, TelaPerfilDadosActivity.class);
                startActivity(intent);
            } else {
                redirectToLogin();
            }
        }

        return true;
    }

    private boolean isLoggedIn() {
        SessionManager sessionManager = new SessionManager(this);
        return sessionManager.isLoggedIn();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(this, TelaLoginActivity.class);
        startActivity(intent);
    }
}
