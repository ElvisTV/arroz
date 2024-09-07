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
        String[] phrases = {
                "Frase 1", "Frase 2", "Frase 3", "Frase 4", "Frase 5",
                "Frase 6", "Frase 7", "Frase 8", "Frase 9", "Frase 10",
                "Frase 11", "Frase 12", "Frase 13", "Frase 14", "Frase 15",
                "Frase 16", "Frase 17", "Frase 18", "Frase 19", "Frase 20"
        };

        for (String phrase : phrases) {
            phraseList.add(new Phrase(phrase));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
