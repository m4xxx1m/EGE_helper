package ru.maximivanov.ege_helper;

import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

public class FooterFragment extends Fragment {
    public FooterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.footer_fragment, container, false);
    }

    public void changeImg(byte num) {
        TextView textView;
        switch (num) {
            case 1:
                textView = Objects.requireNonNull(getView())
                        .findViewById(R.id.home_button);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.home_icon_2, 0, 0);
                break;
            case 2:
                textView = Objects.requireNonNull(getView())
                        .findViewById(R.id.test_button);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.test_icon_2, 0, 0);
                break;
            case 3:
                textView = Objects.requireNonNull(getView())
                        .findViewById(R.id.theory_button);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.theory_icon_2, 0, 0);
                break;
            case 4:
                textView = Objects.requireNonNull(getView())
                        .findViewById(R.id.statistic_button);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.statistic_icon_2, 0, 0);
                break;
            case 5:
                textView = Objects.requireNonNull(getView())
                        .findViewById(R.id.settings_button);
                textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.settings_icon_2, 0, 0);
                break;
        }
    }
}