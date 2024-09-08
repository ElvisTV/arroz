package com.elvistezen.arroz.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elvistezen.arroz.databinding.FragmentHomeBinding;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private PhraseAdapter phraseAdapter;
    private List<Phrase> phraseList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        // Inflar la vista con ViewBinding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar RecyclerView
        recyclerView = binding.recyclerView; // Usar el RecyclerView desde el ViewBinding
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inicializar lista de frases
        phraseList = new ArrayList<>();
        loadPhrases();

        // Configurar el adaptador
        phraseAdapter = new PhraseAdapter(phraseList);
        recyclerView.setAdapter(phraseAdapter);

        return root;
    }

    // Método para cargar las frases
    private void loadPhrases() {
        Phrase[] phrases = {
                new Phrase(1, "Autor 1", "Phrase 1", "Significado 1"),
                new Phrase(2, "Autor 2", "Phrase 2", "Significado 2"),
                new Phrase(3, "Autor 3", "Phrase 3", "Significado 3"),
                new Phrase(4, "Autor 4", "Phrase 4", "Significado 4"),
                new Phrase(5, "Autor 5", "Phrase 5", "Significado 5"),
                new Phrase(6, "Autor 6", "Phrase 6", "Significado 6"),
                new Phrase(7, "Autor 7", "Phrase 7", "Significado 7"),
                new Phrase(8, "Autor 8", "Phrase 8", "Significado 8"),
                new Phrase(9, "Autor 9", "Phrase 9", "Significado 9"),
                new Phrase(10, "Autor 10", "Phrase 10", "Significado 10")
        };

        for (Phrase phrase : phrases) {
            phraseList.add(phrase);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
