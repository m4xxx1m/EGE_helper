package ru.maximivanov.ege_helper;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;

public class FragmentDialog extends DialogFragment implements DialogInterface.OnClickListener {
    int what;
    String title;
    String text;
    String posBut;
    String negBut;
    Context context;
    public static final int ABOUT = 0;
    public static final int WIPE_DATA = 1;

    public FragmentDialog(int what, Context context) {
        this.what = what;
        this.context = context;
        switch (what) {
            case ABOUT:
                title = context.getString(R.string.about_title);
                text = context.getString(R.string.about_text);
                posBut = context.getString(R.string.ok);
                break;
            case WIPE_DATA:
                title = context.getString(R.string.are_you_sure);
                posBut = context.getString(R.string.yes);
                negBut = context.getString(R.string.no);
                break;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (title != null) {
            builder.setTitle(title);
            builder.setPositiveButton(posBut, this);
        }
        if (negBut != null) {
            builder.setNegativeButton(negBut, this);
        }
        if (text != null) {
            builder.setMessage(text);
        }
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (what == WIPE_DATA && which == DialogInterface.BUTTON_POSITIVE) {
            ActivityManager manager = ((ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE));
            Toast.makeText(context, getString(R.string.data_wiped), Toast.LENGTH_LONG).show();
            manager.clearApplicationUserData();
        }
    }
}