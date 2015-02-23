package com.likoms.tallycounter2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.likoms.tallycounter2.fragment.PlaceholderFragment;


public class TallyMain extends ActionBarActivity {

    private PlaceholderFragment placeholderFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tally_main);
        if (savedInstanceState == null) {
            placeholderFragment = new PlaceholderFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, placeholderFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                placeholderFragment.reset();
                return true;
            case R.id.settings:
                showSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU){
            openOptionsMenu();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showSettings() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final NumberPicker numberPicker = new NumberPicker(this);
        final EditText inputLimit = new EditText(this);
        inputLimit.setInputType(InputType.TYPE_CLASS_NUMBER);
        numberPicker.setMaxValue(50);
        numberPicker.setMinValue(10);
        numberPicker.setWrapSelectorWheel(false);
        alert.setMessage("Edit limit");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        placeholderFragment.reset();
                        placeholderFragment.setLimit(numberPicker.getValue());
                    }
                }
        );
        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                }
        );
        alert.setView(numberPicker);
        alert.create().show();
    }

}
