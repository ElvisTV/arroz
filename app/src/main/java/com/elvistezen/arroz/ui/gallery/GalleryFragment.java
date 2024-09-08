package com.elvistezen.arroz.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.elvistezen.arroz.databinding.FragmentGalleryBinding;


public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtener los datos desde el bundle
        Bundle args = getArguments();
        if (args != null) {
            String author = args.getString("author");
            String phrase = args.getString("phrase");
            String meaning = args.getString("meaning");

            // Mostrar los datos
            binding.textGallery.setText("Autor: " + author + "\n\nFrase: " + phrase + "\n\nSignificado: " + meaning);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
