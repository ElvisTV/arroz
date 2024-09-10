package com.elvistezen.arroz.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.elvistezen.arroz.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.LoadAdError;




import com.elvistezen.arroz.databinding.FragmentGalleryBinding;


public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private RewardedAd rewardedAd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar el SurfaceView animado de estrellas
        StarSurfaceView starSurfaceView = root.findViewById(R.id.star_surface_view);


        // Obtener datos del bundle
        Bundle args = getArguments();
        if (args != null) {
            String author = args.getString("author");
            String phrase = args.getString("phrase");
            String meaning = args.getString("meaning");

            // Mostrar los datos
            binding.textAutor.setText("El Titulo es: " + author );
            binding.textFrase.setText("La Frase es: " + phrase );
            binding.textDetalle.setText("El Significado es: " + meaning );
        }

        // Cargar el anuncio de Rewarded
        loadRewardedAd();

        // Configurar el clic en el botón save_button
        binding.saveButton.setOnClickListener(v -> showRewardedAd());

        return root;
    }


    private void loadRewardedAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(requireContext(), "ca-app-pub-5800756673263633/1240301845", adRequest,
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

    private void showRewardedAd() {
        if (rewardedAd != null) {
            rewardedAd.show(requireActivity(), new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Manejar la recompensa del usuario
                }
            });
        } else {
            // El anuncio no está listo
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
