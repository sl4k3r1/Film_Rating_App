package com.uniritter.film_rating_app.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences sharedPreferences;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        // Se a preferência não estiver definida, assume-se que o usuário está logado por padrão
        if (!sharedPreferences.contains("isLoggedIn")) {
            isLoggedIn = true;
            sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply();
        }

        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply();
    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false);
        editor.apply();
    }
}
