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
public class MenuFragment extends Fragment {

    @BindView(R.id.btn_relative)
    Button relativeBtn;

    @BindView(R.id.btn_linear)
    Button linearebtn;

    @BindView(R.id.btn_contraint)
    Button constraintebtn;

    @BindView(R.id.btn_frame)
    Button framebtn;

    @BindView(R.id.btn_grid)
    Button gridbtn;

    @BindView(R.id.btn_table)
    Button tablebtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    public void changeFragment(Fragment fragment, String fragmentTag){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(fragmentTag);
        Log.d("Menu Fragment", "changeFragment: " + fragmentTag);

        // if we are not using butterknife
//        linearebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        // Commit the transaction
        transaction.commit();
    }

    @OnClick(R.id.btn_relative)
    public void onClickBtnRelative(){
        RelativeFragment relativeFragment = new RelativeFragment();
        changeFragment(relativeFragment, relativeFragment.TAG);

    }

    @OnClick(R.id.btn_linear)
    public void onClickBtnLinear(){
        LinearFragment linearFragment = new LinearFragment();
        changeFragment(linearFragment, linearFragment.TAG);

    }

    @OnClick(R.id.btn_contraint)
    public void onClickBtnConstraint(){
        ConstraintFragment constraintFragment = new ConstraintFragment();
        changeFragment(constraintFragment, constraintFragment.TAG);

    }


    @OnClick(R.id.btn_frame)
    public void onClickBtnFrame(){
        FrameFragment frameFragment = new FrameFragment();
        changeFragment(frameFragment, frameFragment.TAG);

    }


    @OnClick(R.id.btn_grid)
    public void onClickBtnGrid(){
        GridFragment gridFragment = new GridFragment();
        changeFragment(gridFragment, gridFragment.TAG);

    }


    @OnClick(R.id.btn_table)
    public void onClickBtnTable(){
        TableFragment tableFragment = new TableFragment();
        changeFragment(tableFragment, TableFragment.TAG);

    }

}
