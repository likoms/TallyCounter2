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


    public PlaceholderFragment() {
    }


    // TODO add preference for setting that value
    private Integer DEFAULT_LIMIT = 10;
    private final static int DEFAULT_INCREMENT = 1;
    private final static int DEFAULT_VALUE = 0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tally_main, container, false);
        this.counter = (TextView) rootView.findViewById(R.id.counter);
        this.counter2 = (TextView) rootView.findViewById(R.id.counter2);
        this.nextButton = (Button) rootView.findViewById(R.id.next);
        this.nextButton2 = (Button) rootView.findViewById(R.id.next2);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (incrementElement(counter)){
                    if(draw(nextButton,nextButton2)){
                        onGameFinished(getActivity().getString(R.string.draw));
                    }else{
                        onGameFinished(getActivity().getString(R.string.red_win));
                    }
                }
            }
        });

        nextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (incrementElement(counter2)){
                    if(draw(nextButton,nextButton2)){
                        onGameFinished(getActivity().getString(R.string.draw));
                    }else{
                        onGameFinished(getActivity().getString(R.string.blue_win));
                    }
                }
            }
        });

        return rootView;
    }

    private void onGameFinished(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok_option_dialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int clickedId) {
                reset();
            }
        });
        builder.setTitle(getActivity().getString(R.string.game_over));
        builder.create().show();
    }


    /**
     * @param counter
     * @return true if user should win
     */
    private boolean incrementElement(TextView counter) {
        int index = Integer.parseInt(counter.getText().toString());
        if (index == DEFAULT_LIMIT) {
            return true;
        }
        counter.setText((index + DEFAULT_INCREMENT) + "");
        return false;
    }

    public void reset() {
        counter.setText(DEFAULT_VALUE + "");
        counter2.setText(DEFAULT_VALUE + "");
    }
    private boolean draw(Button nextButton, Button nextButton2){
        int p = Integer.parseInt(counter.getText().toString());
        int p2 = Integer.parseInt(counter2.getText().toString());
        if(nextButton.isPressed() && nextButton2.isPressed() && p==DEFAULT_LIMIT && p2==DEFAULT_LIMIT){
            return true;
        }
        return false;
    }

}





