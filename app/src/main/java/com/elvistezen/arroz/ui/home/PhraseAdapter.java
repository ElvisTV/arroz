package com.elvistezen.arroz.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.elvistezen.arroz.R;
import java.util.List;

public class PhraseAdapter extends RecyclerView.Adapter<PhraseAdapter.PhraseViewHolder> {
    private List<Phrase> phraseList;

    public PhraseAdapter(List<Phrase> phraseList) {
        this.phraseList = phraseList;
    }

    @NonNull
    @Override
    public PhraseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new PhraseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhraseViewHolder holder, int position) {
        holder.textViewPhrase.setText(phraseList.get(position).getPhrase());
    }

    @Override
    public int getItemCount() {
        return phraseList.size();
    }

    public static class PhraseViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPhrase;

        public PhraseViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPhrase = itemView.findViewById(R.id.textViewPhrase);
        }
    }
}
