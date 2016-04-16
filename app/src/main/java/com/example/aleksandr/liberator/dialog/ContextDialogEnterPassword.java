package com.example.aleksandr.liberator.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.activity.DiagnosticActivity;
import com.example.aleksandr.liberator.activity.SettingsAppActivity;
import com.example.aleksandr.liberator.static_params.StaticParams;
import com.example.aleksandr.liberator.utils.Utils;

/**
 * Created by AleksandrP on 16.04.2016.
 */
public class ContextDialogEnterPassword extends AlertDialog {


    public ContextDialogEnterPassword(final Context mContext) {
        super(mContext);

        // Init dialog view
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.context_dialog_password, null);


        // Init dialog list
        final EditText mTextPassword = (EditText) view.findViewById(R.id.et_enter_password);
        Button mButtonOk = (Button) view.findViewById(R.id.bi_ok_password);

        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.disableButton(v);
                if (mTextPassword.getText().toString().equals(StaticParams.PASSWORD_)) {
                    Intent intent = new Intent(mContext, DiagnosticActivity.class);
                    mContext.startActivity(intent);
                } else {
                    Snackbar.make(v, "Пароль не верный!", Snackbar.LENGTH_SHORT).show();
                }
                cancel();
            }
        });

    }
}