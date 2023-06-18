package com.uniritter.film_rating_app.dao;

import android.util.Log;

import com.uniritter.film_rating_app.model.Filme;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FilmesAtuaisDAO {
    private List<Filme> filmes;

    public FilmesAtuaisDAO() {
        filmes = new ArrayList<>();
    }

    public void carregarFilmesAtuaisDoJson(String jsonResponse) throws JSONException {

        if (jsonResponse != null) {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < ((JSONArray) jsonArray).length(); i++) {
                JSONObject filmeJson = jsonArray.getJSONObject(i);
                int id = filmeJson.getInt("id");
                String originalLanguage = filmeJson.getString("original_language");
                String originalTitle = filmeJson.getString("original_title");
                String overview = filmeJson.getString("overview");
                double popularity = filmeJson.getDouble("popularity");
                String posterPath = filmeJson.getString("poster_path");
                String releaseDate = filmeJson.getString("release_date");
                String title = filmeJson.getString("title");
                boolean video = filmeJson.getBoolean("video");
                double voteAverage = filmeJson.getDouble("vote_average");
                int voteCount = filmeJson.getInt("vote_count");

                Filme filme = new Filme(id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount);
                Log.i("", "carregarFilmesAtuaisDoJson: "+ filme);
                salvarFilme(filme);
            }
        }
    }

    public void salvarFilme(Filme filme) {
        filmes.add(filme);
    }

    public List<Filme> getFilmes() {
        return filmes;
    }
}
