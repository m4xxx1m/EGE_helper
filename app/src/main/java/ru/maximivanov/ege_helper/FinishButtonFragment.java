package ru.maximivanov.ege_helper;

import android.os.Bundle;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FinishButtonFragment extends Fragment {
    private Button button;

    public FinishButtonFragment() {
    }

    public static FinishButtonFragment newInstance(String param1, String param2) {
        FinishButtonFragment fragment = new FinishButtonFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    public Button getButton() {
        return button;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_finish_button, container, false);
        button = v.findViewById(R.id.button_finish);
        return v;
    }
}