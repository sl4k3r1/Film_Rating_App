package com.uniritter.film_rating_app.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.uniritter.film_rating_app.R;
import com.uniritter.film_rating_app.model.SessionManager;

public class TelaPerfilDadosActivity extends AppCompatActivity {

    private TextView textoNomedeUsuario, textoUsuarioEmail;
    private Button bt_deslogar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_dados);

        iniciarComponentes();

        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Desconecte o usuário do Firebase
                FirebaseAuth.getInstance().signOut();

                // Limpe a preferência compartilhada e defina o estado de login como falso
                SessionManager sessionManager = new SessionManager(TelaPerfilDadosActivity.this);
                sessionManager.logout();

                // Redirecione para a tela de login
                startActivity(new Intent(TelaPerfilDadosActivity.this, TelaPrincipal_Activity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    textoNomedeUsuario.setText(documentSnapshot.getString("nome"));
                    textoUsuarioEmail.setText(email);
                }
            }
        });
    }

    private void iniciarComponentes() {
        textoNomedeUsuario = findViewById(R.id.textoNomedeUsuario);
        textoUsuarioEmail = findViewById(R.id.textoUsuarioEmail);
        bt_deslogar = findViewById(R.id.bt_deslogar);
    }
}