package com.example.fragmentpractice;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class BlankFragment extends Fragment {
    private static final String RADIO_BUTTON_SELECT = "select";
    int mRadioButtonChoice;
    RadioButtonChoiceListener listener;

    // private static BlankFragment blankFragment;
    public static BlankFragment getInstance(int choice) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(RADIO_BUTTON_SELECT, choice);
        fragment.setArguments(args);
        return fragment;
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    public interface RadioButtonChoiceListener{
        void radioButtonChoice(int radioButtonIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_blank, container, false);
        final RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        if (getArguments().containsKey(RADIO_BUTTON_SELECT)) {
            mRadioButtonChoice=getArguments().getInt(RADIO_BUTTON_SELECT);
            if (mRadioButtonChoice != -1) {
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView = view.findViewById(R.id.textView_fragment);
                switch (index) {
                    case 0:
                        mRadioButtonChoice=0;
                        listener.radioButtonChoice(mRadioButtonChoice);
                        textView.setText("Да");
                        break;
                    case 1:
                        mRadioButtonChoice=1;
                        listener.radioButtonChoice(mRadioButtonChoice);
                        textView.setText("Нет");
                        break;

                        default:
                            mRadioButtonChoice=-1;
                            break;
                }
            }
        });
    return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RadioButtonChoiceListener) {
            listener = (RadioButtonChoiceListener) context;
        }
    }
}
