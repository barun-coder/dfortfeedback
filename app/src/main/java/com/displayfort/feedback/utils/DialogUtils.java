package com.displayfort.feedback.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.displayfort.feedback.R;

/**
 * Created by Husain on 26/04/2019 12:41.
 * SportsInCode
 */
public class DialogUtils {

    public static void showAlertDialog(Context context, String textMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(textMsg);
        builder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


        AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }

    public static void showAlertDialog(Context context, String textMsg, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(textMsg);
        builder.setCancelable(false)
                .setPositiveButton(R.string.ok, onClickListener);
        AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }


    public static void showAlertDialog(Context context, String title, String textMsg, int left, int right, DialogInterface.OnClickListener onClickLeftListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(textMsg);
        builder.setTitle(title);
        builder.setCancelable(false)
                .setPositiveButton(left, onClickLeftListener);
        builder.setNegativeButton(right, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }

    public static void showAlertDialog(Context context, String title, String textMsg, int left, int right, DialogInterface.OnClickListener onClickLeftListener, DialogInterface.OnClickListener onClickRightListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(textMsg);
        builder.setTitle(title);
        builder.setCancelable(false)
                .setPositiveButton(left, onClickLeftListener);
        builder.setNegativeButton(right, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }
}
