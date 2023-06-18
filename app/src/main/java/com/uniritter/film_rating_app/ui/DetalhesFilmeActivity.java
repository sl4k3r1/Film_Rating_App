package com.uniritter.film_rating_app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uniritter.film_rating_app.R;
import com.uniritter.film_rating_app.adapter.FilmesAdapter;
import com.uniritter.film_rating_app.model.Filme;
import com.uniritter.film_rating_app.web_request.CallBack;
import com.uniritter.film_rating_app.web_request.WebFilmesRecomendados;

import java.util.List;

public class DetalhesFilmeActivity extends AppCompatActivity {

    private ImageView imagemFilme;
    private TextView tituloFilme;
    private TextView descricaoFilme;
    private TextView notaFilme;
    private RecyclerView recyclerViewRecomendados;
    private FilmesAdapter adapterRecomendados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        imagemFilme = findViewById(R.id.filmeImagem);
        tituloFilme = findViewById(R.id.titulo_filme);
        descricaoFilme = findViewById(R.id.resumoFilme);
        notaFilme = findViewById(R.id.text_view_progress);
        recyclerViewRecomendados = findViewById(R.id.recyclerViewRecomendados);


        Filme filme = getIntent().getParcelableExtra("filme");

        if (filme != null) {
            exibirDetalhesFilme(filme);

            int numeroFilme = filme.getId();
            WebFilmesRecomendados.carregarFilmesWebFilmesRecomendados(new CallBack() {
                @Override
                public void filmes(List<Filme> filmes) {
                    runOnUiThread(() -> exibirFilmesRecomendados(filmes));
                }
            }, numeroFilme);
        }
    }

    private void exibirDetalhesFilme(Filme filme) {
        // Carregar a imagem do filme usando Glide
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + filme.getPosterPath())
                .into(imagemFilme);

        // Exibir o título, descrição e nota do filme
        tituloFilme.setText(filme.getTitle());
        descricaoFilme.setText(filme.getOverview());
        notaFilme.setText(String.valueOf(filme.getVoteAverage()));

        // Ajustar o tamanho do texto adaptativamente
        TextViewCompat.setAutoSizeTextTypeWithDefaults(tituloFilme, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(descricaoFilme, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        TextViewCompat.setAutoSizeTextTypeWithDefaults(notaFilme, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
    }

    private void exibirFilmesRecomendados(List<Filme> filmes) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRecomendados.setLayoutManager(layoutManager);
        adapterRecomendados = new FilmesAdapter(filmes);
        recyclerViewRecomendados.setAdapter(adapterRecomendados);

        adapterRecomendados.setOnItemClickListener(new FilmesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Filme filme) {
                Intent intent = new Intent(DetalhesFilmeActivity.this, DetalhesFilmeActivity.class);
                intent.putExtra("filme", (Parcelable) filme);
                startActivity(intent);
                finish();
            }
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
            Intent intent = new Intent(this, TelaLoginActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.perfil) {
            Intent intent = new Intent(this, TelaPerfilActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.sair) {
            Intent intent = new Intent(this, TelaPerfilDadosActivity.class);
            startActivity(intent);
        }

        return true;
    }
}