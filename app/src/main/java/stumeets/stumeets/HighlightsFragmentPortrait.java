package stumeets.stumeets;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * This is the fragments containing the highlights of the application, in portrait form.
 */
public class HighlightsFragmentPortrait extends Fragment {


    public HighlightsFragmentPortrait() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_highlights_fragment_portrait, container, false);
    }


}
