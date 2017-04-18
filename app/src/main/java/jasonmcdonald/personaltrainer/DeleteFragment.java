package jasonmcdonald.personaltrainer;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;



/**
 * Created by Administrator on 4/7/2017.
 */

public class DeleteFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.delete_dialog)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int integer){
                                //TODO: delete the customer who was long pressed in customersActivity
                                Intent intent = new Intent(getActivity(),CustomersActivity.class);
                                startActivity(intent);
                            }
                        })
                .setNegativeButton(android.R.string.cancel, null)
                .create();

    }


}
