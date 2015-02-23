package com.likoms.tallycounter2.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    // TODO fix Dialog Alert

    private Integer DEFAULT_LIMIT = 10;
    private final static int DEFAULT_INCREMENT = 1;
    private final static int DEFAULT_VALUE = 0;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_placeholder_fragment, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                reset();
                return true;
            case R.id.settings:
                showSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_tally_main, container, false);
        this.counter = (TextView) rootView.findViewById(R.id.counter);
        counter.setRotation(180);
        this.counter2 = (TextView) rootView.findViewById(R.id.counter2);
        this.nextButton = (Button) rootView.findViewById(R.id.next);
        nextButton.setRotation(180);
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
    private void showSettings(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        final EditText inputLimit = new EditText(getActivity());
        inputLimit.setInputType(InputType.TYPE_CLASS_NUMBER);

        alert.setView(inputLimit);
        alert.setMessage("Edit limit");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String valueLimit = inputLimit.getText().toString().trim();
                    DEFAULT_LIMIT= Integer.parseInt(valueLimit);
                }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
        alert.create().show();
    }

}





