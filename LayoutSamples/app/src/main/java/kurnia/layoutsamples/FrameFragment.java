package kurnia.layoutsamples;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrameFragment extends Fragment {


    TextView textFrame;
    public final static String TAG = "Frame";

    public FrameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frame, container, false);

        textFrame = (TextView) view.findViewById(R.id.text_frame);
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        int num = bundle.getInt("num");

        Log.d(TAG, "num: " + num);
        textFrame.setText(String.valueOf(num));

        return view;
    }

}
