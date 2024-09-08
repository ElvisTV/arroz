package com.elvistezen.arroz.ui.home;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elvistezen.arroz.R;
import com.elvistezen.arroz.databinding.FragmentHomeBinding;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private PhraseAdapter phraseAdapter;
    private List<Phrase> phraseList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        phraseList = new ArrayList<>();
        loadPhrases();

        phraseAdapter = new PhraseAdapter(phraseList, phrase -> {
            // Navegar a GalleryFragment y pasar la frase seleccionada
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            Bundle bundle = new Bundle();
            bundle.putString("author", phrase.getAuthor());
            bundle.putString("phrase", phrase.getPhrase());
            bundle.putString("meaning", phrase.getMeaning());
            navController.navigate(R.id.nav_gallery, bundle);
        });
        recyclerView.setAdapter(phraseAdapter);

        return root;
    }

    private void loadPhrases() {
        Phrase[] phrases = {
                new Phrase(1, "Autor 1", "Phrase 1", "Significado 1"),
                new Phrase(2 , "Autor 2  ", "Phrase 2 ", "Significado 2   "),
                new Phrase(3 , "Autor 3  ", "Phrase 3", "Significado 3   "),
                new Phrase(4 , "Autor 4  ", "Phrase 4", "Significado 4   "),
                new Phrase(5 , "Autor 5  ", "Phrase 5", "Significado 5   "),
                new Phrase(6 , "Autor 6  ", "Phrase 6", "Significado 6   "),
                new Phrase(7 , "Autor 7  ", "Phrase 7", "Significado 7   "),
                new Phrase(8 , "Autor 8  ", "Phrase 8", "Significado 8   "),
                new Phrase(9 , "Autor 9  ", "Phrase 9", "Significado 9   "),
                new Phrase(10, "Autor 10 ", "Phrase 10", "Significado 10"),
                new Phrase(11, "Autor 11 ", "Phrase 11", "Significado 11"),
                new Phrase(12, "Autor 12 ", "Phrase 12", "Significado 12"),
                new Phrase(13, "Autor 13 ", "Phrase 13", "Significado 13"),
                new Phrase(14, "Autor 14 ", "Phrase 14", "Significado 14"),
                new Phrase(15, "Autor 15 ", "Phrase 15", "Significado 15"),
                new Phrase(16, "Autor 16 ", "Phrase 16", "Significado 16"),
                new Phrase(17, "Autor 17 ", "Phrase 17", "Significado 17"),
                new Phrase(18, "Autor 18 ", "Phrase 18", "Significado 18"),
                new Phrase(19, "Autor 19 ", "Phrase 19", "Significado 19"),
                new Phrase(20, "Autor 20 ", "Phrase 20", "Significado 20"),
                new Phrase(21, "Autor 21 ", "Phrase 21", "Significado 21")
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
