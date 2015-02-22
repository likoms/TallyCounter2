package com.likoms.tallycounter2.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.likoms.tallycounter2.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private TextView counter;
    private TextView counter2;
    private Button nextButton;
    private Button nextButton2;
    private TextView result;
    //private TextView result2;
    private Button resetButton;
    private Integer limit=100;
//TODO button which get a number of game limit

    public PlaceholderFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View rootView = inflater.inflate(R.layout.fragment_tally_main, container, false);
        this.counter = (TextView) rootView.findViewById(R.id.counter);
        this.counter2 = (TextView) rootView.findViewById(R.id.counter2);
        this.nextButton = (Button) rootView.findViewById(R.id.next);
        this.result = (TextView) rootView.findViewById(R.id.textViewWon);
        //this.result2 = (TextView) rootView.findViewById(R.id.textViewWon2);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(counter.getText().toString());

                if(index>=limit){
                    result.setText("Red Win !!");
                    //result2.setText("Player one Win !!");
                    index=0;

                }else{
                    counter.setText((index + 1) + "");
                }
            }
        });
        this.nextButton2 = (Button) rootView.findViewById(R.id.next2);
        nextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(counter2.getText().toString());
                if(index>=limit){
                    result.setText("Blue Win !!");
                   // result2.setText("Player one Win !!");
                    index=0;
                }else{
                    counter2.setText((index + 1) + "");
                }
            }
        });

        //Dialog
        builder.setMessage(R.string.confirm_dialog)
                .setPositiveButton(R.string.yes_option_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        counter.setText("0");
                        counter2.setText("0");
                        result.setText("");
                       // result2.setText("");
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel_option_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        //End of dialog
        this.resetButton = (Button) rootView.findViewById(R.id.buttonReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the AlertDialog object and return it
                AlertDialog alert = builder.create();
                alert.show();
                //counter.setText("0");
            }
        });

        return rootView;
    }



}