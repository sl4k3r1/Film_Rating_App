package com.uniritter.film_rating_app.web_request;

import android.util.Log;

import com.uniritter.film_rating_app.dao.FilmeRecomendadosDAO;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebFilmesRecomendados {
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/%s/recommendations?language=pt-BR&page=1";
    private static final String ACCEPT_HEADER = "application/json";
    private static final String AUTHORIZATION_HEADER = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMTFlZWMwN2QyYzkzNGJkNDU4MzhlMzRlNGQ1NzlkYiIsInN1YiI6IjY0NTU0ZTY1Y2NiMTVmMDExOWRkNTdhZSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ._1DM5S9nxWDK6XOSW4dWGS-DdX6Us0CnUAytEe1qLU4";

    public static void carregarFilmesWebFilmesRecomendados(CallBack callBack, int numeroFilme) {
        String url = String.format(BASE_URL, numeroFilme);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", ACCEPT_HEADER)
                .addHeader("Authorization", AUTHORIZATION_HEADER)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    try {
                        FilmeRecomendadosDAO filmesRecomendadosDAO = new FilmeRecomendadosDAO();
                        filmesRecomendadosDAO.carregarFilmesRecomendadosDoJson(jsonResponse);
                        callBack.filmes(filmesRecomendadosDAO.getFilmes());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("WebFilmesRecomendados", "Erro na resposta do servidor: " + response.code());
                }
            }
        });
    }
}