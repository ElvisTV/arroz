package com.elvistezen.arroz.ui.gallery;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.elvistezen.arroz.R;
import com.elvistezen.arroz.databinding.FragmentGalleryBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.LoadAdError;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private RewardedAd rewardedAd;
    private MediaPlayer mediaPlayer;
    private TextView textPromocion;
    private Button saveButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Reproduce el sonido
        playSound();

        // Inicializar el SurfaceView animado de estrellas
        StarSurfaceView starSurfaceView = root.findViewById(R.id.star_surface_view);

        // Inicializar los elementos ocultos
        textPromocion = root.findViewById(R.id.text_promocion);
        saveButton = root.findViewById(R.id.save_button);

        // Ocultar los elementos al iniciar
        textPromocion.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);

        // Obtener el EditText
        EditText passwordEditText = root.findViewById(R.id.password);

        // Agregar un TextWatcher para escuchar los cambios en el EditText
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No se necesita hacer nada antes de que el texto cambie
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Verificar si la palabra "excelente" ha sido ingresada
                if (charSequence.toString().equalsIgnoreCase("excelente")) {
                    // Mostrar los elementos ocultos
                    textPromocion.setVisibility(View.VISIBLE);
                    saveButton.setVisibility(View.VISIBLE);
                } else {
                    // Ocultar los elementos si la palabra no es "excelente"
                    textPromocion.setVisibility(View.GONE);
                    saveButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No se necesita hacer nada después de que el texto ha cambiado
            }
        });

        // Obtener datos del bundle
        Bundle args = getArguments();
        if (args != null) {
            String author = args.getString("author");
            String phrase = args.getString("phrase");
            String meaning = args.getString("meaning");

            // Mostrar los datos
            binding.textAutor.setText(author);
            binding.textFrase.setText(phrase);
            binding.textDetalle.setText(meaning);
        }

        // Cargar el anuncio de Rewarded
        loadRewardedAd();

        return root;
    }

    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(requireContext(), "ca-app-pub-5800756673263633/1308343356", adRequest,
                new RewardedAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Manejar el error
                    }
                });
    }

    private void playSound() {
        if (getContext() != null) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.sonido);
            if (mediaPlayer != null) {
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mp -> mp.release());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        binding = null;
    }
}
