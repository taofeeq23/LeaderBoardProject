package com.gads.project.leaderboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

//import com.danc.leaderboardfinal.R;

public class SuccessDialog extends DialogFragment {
    private static final String TAG = "SuccessDialog";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater1 = getActivity().getLayoutInflater();
        builder.create();

        View view = inflater1.inflate(R.layout.custom_success_pop, null);
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
                } catch (Exception e){
                    Log.d(TAG, "run: Success Error " + e.getMessage());
                }
            }
        };
        thread.start();
    }
}