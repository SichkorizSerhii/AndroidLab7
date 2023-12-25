package edu.nuzp.lab7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DrugInstructionsFragment extends Fragment {
    public static final String ARG_DRUG_NAME = "drug_name";

    public DrugInstructionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drug_instructions, container, false);

        TextView drugDetailsTextView = view.findViewById(R.id.drugDetailsTextView);

        String drugName = getArguments().getString(ARG_DRUG_NAME);

        int resId = getResources().getIdentifier(drugName, "string", requireActivity().getPackageName());
        String drugInstructions = getResources().getString(resId);

        drugDetailsTextView.setText(drugInstructions);

        return view;
    }
}


