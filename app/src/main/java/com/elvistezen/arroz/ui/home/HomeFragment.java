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
                new Phrase(1,  "Theodore Roosevelt", "Cree que puedes y estarás a mitad de camino.", "La confianza en uno mismo es fundamental para alcanzar cualquier meta."),
                new Phrase(2 , "Nelson Mandela", "Todo parece imposible hasta que se hace.","Los grandes retos pueden parecer insuperables, pero con perseverancia se logran."),
                new Phrase(3 , "Mahatma Gandhi", "Sé el cambio que deseas ver en el mundo.", "El cambio global empieza con nuestras propias acciones cotidianas."),
                new Phrase(4 , "Confucio", "No importa lo lento que vayas, siempre y cuando no te detengas.", "El progreso es progreso, sin importar el ritmo. Lo importante es seguir adelante."),
                new Phrase(5 , "George Eliot", "Nunca es demasiado tarde para ser quien podrías haber sido.", "No importa la edad o las circunstancias, siempre hay tiempo para reinventarse."),
                new Phrase(6 , "Peter Drucker", "La mejor manera de predecir el futuro es crearlo.", "No debemos esperar que el futuro suceda; debemos trabajar para construirlo."),
                new Phrase(7 , "Napoleon Hill", "No esperes. El momento nunca será perfecto.", "No existe un momento ideal para comenzar algo nuevo. La clave es actuar ya."),
                new Phrase(8 , "Robert Collier", "El éxito es la suma de pequeños esfuerzos repetidos día tras día.", "Las pequeñas acciones constantes son las que realmente nos acercan a nuestras metas."),
                new Phrase(9 , "Dalai Lama", "La felicidad no es algo hecho. Viene de tus propias acciones.", "La felicidad es un resultado directo de nuestras elecciones y actitudes diarias."),
                new Phrase(10, "Henry Ford", "El fracaso es una gran oportunidad para empezar otra vez con más inteligencia.", "El fracaso es una gran oportunidad para empezar otra vez con más inteligencia."),
                new Phrase(11, "Muhammad Ali", "Cuanto más duro trabajo, más suerte tengo.", "El esfuerzo constante es la clave para atraer oportunidades y crear suerte."),
                new Phrase(12, "Roy T. Bennett", "No temas equivocarte, teme no intentarlo.", "El verdadero fracaso no está en cometer errores, sino en nunca haberlo intentado."),
                new Phrase(13, "Vidal Sassoon", "No tengas miedo de renunciar a lo bueno para ir por lo grandioso.", "No hay atajos para el éxito. Siempre requiere esfuerzo, trabajo duro y dedicación."),
                new Phrase(14, "Michael Jordan", "Tienes que esperar cosas de ti mismo antes de poder hacerlas.", "La fe en nuestras propias habilidades es el primer paso para lograr algo grande."),
                new Phrase(15, "Jim Rohn", "La disciplina es el puente entre las metas y los logros.", "La disciplina diaria es lo que convierte los sueños en realidad. Sin ella, las metas siguen siendo deseos."),
                new Phrase(16, "Sócrates", "El secreto del cambio es enfocar toda tu energía, no en luchar contra lo viejo, sino en construir lo nuevo", "El cambio positivo proviene de concentrarse en el futuro y en lo que podemos crear, no en lo que hemos dejado atrás"),
                new Phrase(17, "Robert T. Kiyosaki", "El fracaso derrota a los perdedores e inspira a los ganadores.", "Los fracasos son oportunidades para aprender y mejorar. Los vencedores los ven como parte del camino hacia el éxito."),
                new Phrase(18, "Maya Angelou", "El éxito es amar la vida y atreverte a vivirla.", "Vivir plenamente y con valentía es la verdadera definición de éxito."),
                new Phrase(19, "Samuel Johnson", "Las grandes obras no son hechas con fuerza, sino con perseverancia", "La constancia, más que la fuerza, es lo que permite lograr cosas extraordinarias."),
                new Phrase(20, "F. Scott Fitzgerald", "Nuestras vidas se definen por las oportunidades, incluso las que perdemos.", "Cada oportunidad, tomada o no, moldea el curso de nuestras vidas. Debemos estar atentos a las que se presentan."),
                new Phrase(21, "John Wooden", "Haz de cada día tu obra maestra.", "No esperes para hacer algo extraordinario en el futuro. Aprovecha cada día para crear algo valioso.")
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
