package com.example.korean_translator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class EditSection extends AppCompatDialogFragment {

    EditText $ETHan, $ETRom;

    editSection Listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.edit_section_page,null);

        builder.setView(v);
                builder.setTitle("Save Unknown Word");
                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String han = $ETHan.getText().toString();
                String rom = $ETRom.getText().toString();

                if (han.isEmpty() || rom.isEmpty()){
                    Toast.makeText(getActivity (), "Not saved. Set word first.", Toast.LENGTH_SHORT).show();
                }else {
                    Listener.setData(han, rom);
                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                }
            }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /* // */
                    }
                });
                $ETHan = v.findViewById(R.id.ETHan);
                $ETRom = v.findViewById(R.id.ETRom);
                return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
            Listener = (editSection)context;
    }

    public interface editSection{
       void setData(String han, String rom);
    }
}