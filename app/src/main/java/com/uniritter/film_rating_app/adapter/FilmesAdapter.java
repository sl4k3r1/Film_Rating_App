package com.uniritter.film_rating_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uniritter.film_rating_app.R;
import com.uniritter.film_rating_app.model.Filme;

import java.util.List;

public class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.ViewHolder> {

    private List<Filme> filmes;
    private OnItemClickListener onItemClickListener;

    public FilmesAdapter(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icone_filme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Filme filme = filmes.get(position);
        holder.bind(filme);
    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Filme filme);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagemFilme;
        private TextView nomeFilme;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemFilme = itemView.findViewById(R.id.imagemfilme);
            nomeFilme = itemView.findViewById(R.id.nome_filme);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        Filme filme = filmes.get(position);
                        onItemClickListener.onItemClick(filme);
                    }
                }
            });
        }

        public void bind(Filme filme) {
            String posterPath = filme.getPosterPath();
            String title = filme.getTitle();

            // Use a biblioteca Glide para carregar a imagem do filme
            Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500" + posterPath)  // Use a URL completa da imagem
                    .into(imagemFilme);

            nomeFilme.setText(title);
        }
    }
}