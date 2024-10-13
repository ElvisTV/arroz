package com.elvistezen.arroz.ui.gallery;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

import android.util.Log;


public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private RewardedAd rewardedAd;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Reproduce el sonido
        //playSound();

        // Inicializar el SurfaceView animado de estrellas
        StarSurfaceView starSurfaceView = root.findViewById(R.id.star_surface_view);

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
        //loadRewardedAd();

        // Configurar el clic en el botón save_button
        binding.saveButton.setOnClickListener(v -> showRewardedAd());

        return root;
    }

    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        //RewardedAd.load(requireContext(), "ca-app-pub-3940256099942544/5224354917", adRequest, // de prueba
        RewardedAd.load(requireContext(), "ca-app-pub-5800756673263633/3921345047", adRequest,
                new RewardedAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        //Log.d("AdLoadError", loadAdError.toString());
                        //Toast.makeText(requireContext(), loadAdError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showRewardedAd() {

        loadRewardedAd();

        if (rewardedAd != null) {
            rewardedAd.show(requireActivity(), new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Manejar la recompensa del usuario
                }
            });
        } else {
            Toast.makeText(requireContext(), "Intentalo más tarde. En este momento no hay videos disponibles", Toast.LENGTH_SHORT).show();
        }
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
