package com.uniritter.film_rating_app.web_request;

import com.uniritter.film_rating_app.model.Filme;

import java.util.List;

public interface CallBack {
    public void filmes(
        List<Filme> filmes
    );
}
