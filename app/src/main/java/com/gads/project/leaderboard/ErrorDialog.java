package com.gads.project.leaderboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

//import com.danc.leaderboardfinal.R;

public class ErrorDialog extends DialogFragment {

    private static final String TAG = "ErrorDialog";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater1 = getActivity().getLayoutInflater();
        View view = inflater1.inflate(R.layout.custom_submit_error_pop, null);
        alertDialog.create();
        setRetainInstance(true);

        autoDismiss();

        return view;

    }

    private void autoDismiss(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2 * 1000);
                    dismiss();
                }
                catch (Exception e){
                    Log.d(TAG, "run: Failed Error" + e.getMessage());
                }
            }
        };
        thread.start();
    }
}