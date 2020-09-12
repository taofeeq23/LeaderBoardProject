package com.gads.project.leaderboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

//import com.danc.leaderboardfinal.R;
//import com.danc.leaderboardfinal.SubmissionActivity;

public class SubmitDialog extends DialogFragment implements View.OnClickListener {

    ImageView cancelImage;
    Button submitButton;
    ConfirmDialogListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_submit_pop, null);
        builder.setView(view);

        setRetainInstance(true);

        cancelImage = view.findViewById(R.id.imageCancel);
        submitButton = view.findViewById(R.id.btnSuccess);

        cancelImage.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        return builder.create();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageCancel:
                dismiss();
                break;

            case R.id.btnSuccess:
                mListener.confirmSubmission(true);
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (ConfirmDialogListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }

    public interface ConfirmDialogListener {
        void confirmSubmission(boolean isConfirmed);
    }
}
