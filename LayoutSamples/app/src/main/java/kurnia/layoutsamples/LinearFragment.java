package kurnia.layoutsamples;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class LinearFragment extends Fragment {

    public final static String TAG = "Linear";

    @BindView(R.id.btn_frame)
    Button btnFrame;

    public LinearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_linear, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_frame)
    public void callAnotherFragment(){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        FrameFragment frameFragment = new FrameFragment();
        Bundle args = new Bundle();
        args.putInt("num", 5);
        frameFragment.setArguments(args);

        transaction.replace(R.id.fragment_container2, frameFragment);
        transaction.addToBackStack(frameFragment.TAG);

        transaction.commit();
    }

}
