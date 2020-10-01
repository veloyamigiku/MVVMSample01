package com.example.mvvmsample01;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class InfoDialogFragment extends DialogFragment {

    public interface Interface {
        void pushPositiveButton(String tag);
    }

    private static final String ARGS_MESSAGE = "message";
    private static final String ARGS_BUTTON_TITLE = "buttonTitle";
    private static final String ARGS_TAG = "tag";
    private Interface intf;

    public InfoDialogFragment() {}

    public static InfoDialogFragment newInstance(
            String message,
            String buttonTitle,
            String tag) {
        InfoDialogFragment dialogFragment = new InfoDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_MESSAGE, message);
        args.putString(ARGS_BUTTON_TITLE, buttonTitle);
        args.putString(ARGS_TAG, tag);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.intf = (Interface) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String message = getArguments().getString(ARGS_MESSAGE);
        String buttonTitle = getArguments().getString(ARGS_BUTTON_TITLE);
        String tag = getArguments().getString(ARGS_TAG);
        builder
                .setMessage(message)
                .setPositiveButton(buttonTitle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intf.pushPositiveButton(tag);
                    }
                });
        return builder.create();
    }
}
