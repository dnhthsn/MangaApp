package com.example.mangaapp.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.mangaapp.R;

public class Utility {
    public static class Notice{
        public static void ShowDialog(Context context){
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout._dialog);
            Button btnOk = dialog.findViewById(R.id.btn_ok);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
}
