package jasonmcdonald.personaltrainer;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Administrator on 4/7/2017.
 */

public class LogoutFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.log_out_dialog)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int integer){
                                Intent intent = new Intent(getActivity(),LoginActivity.class);
                                startActivity(intent);
                                }
                        })
                .setNegativeButton(android.R.string.cancel, null)
                .create();

    }


}
