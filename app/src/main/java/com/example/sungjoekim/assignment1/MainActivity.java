package com.example.sungjoekim.assignment1;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageButton imgBtn;
    int year_x, month_x, day_x;
    static final int DILOG_ID = 0;

    private EditText editText;
    private CheckBox chkBox;
    private Spinner spinner;
    private RadioGroup radioGp;
    private RadioButton radioXS, radioS, radioM, radioL, radioXL, radioXXL;

    private SeekBar skBarPant, skBarShirt, skBarShoe;
    private TextView txtViewPantSize, txtViewShirtSize, txtViewShoeSize;
    private TextView birthText;
    private String strRadio, strBirthDay;
    private int pantSizeValue, shirtSizeValue, shoeSizeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Display center-horizontal App Title of "The Roster"
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        //Select spineer
        addSelectSpinner();
        //Load data from stored file and display in the textEdit
        loadSavedPreferences();
        //Check Box
        addListenerOnCheckBox();
       //Calendar Picker Dialogue
        showDialogOnButtonClick();
        //Radio Button
        addListenerOnRadioButton();
        //SeekBar Pant
        addListenerSeekBarSet();

    }

    void SetCustomTitle(String title){
        TextView textViewTitle = (TextView)findViewById(R.id.mytitle);
        textViewTitle.setText(title);
    }

    public void addListenerSeekBarSet(){

        skBarPant = (SeekBar)findViewById(R.id.seekBarPant);
        skBarShirt = (SeekBar)findViewById(R.id.seekBarShirt);
        skBarShoe = (SeekBar)findViewById(R.id.seekBarShoe);

        txtViewPantSize = (TextView)findViewById(R.id.txtViewPant);
        txtViewShirtSize = (TextView)findViewById(R.id.txtViewShirt);
        txtViewShoeSize = (TextView)findViewById(R.id.txtViewShoe);

        skBarPant.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int proValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                proValue = progress;
                txtViewPantSize.setText("(" + proValue + ")");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pantSizeValue = proValue;
            }
        });

        skBarShirt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int proValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                proValue = progress;
                txtViewShirtSize.setText("(" + proValue + ")");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                shirtSizeValue = proValue;
            }
        });

        skBarShoe.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int proValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                proValue = progress + 4;
                txtViewShoeSize.setText("(" + proValue + ")");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                shoeSizeValue = proValue - 4;
            }
        });
    }

    public void addSelectSpinner(){

        spinner =(Spinner)findViewById(R.id.status_spinner);
        String[] list = getResources().getStringArray(R.array.status_arrays);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,list);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg = (ViewGroup) view;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void addListenerOnRadioButton(){

        radioXS = (RadioButton)findViewById(R.id.rbXS);
        radioS = (RadioButton)findViewById(R.id.rbS);
        radioM = (RadioButton)findViewById(R.id.rbM);
        radioL = (RadioButton)findViewById(R.id.rbL);
        radioXL = (RadioButton)findViewById(R.id.rbXL);
        radioXXL = (RadioButton)findViewById(R.id.rbXXL);

        radioGp = (RadioGroup)findViewById(R.id.radioGroup);
        radioGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rbXS:
                        strRadio = radioXS.getText().toString();
                        break;
                    case R.id.rbS:
                        strRadio = radioS.getText().toString();
                        break;
                    case R.id.rbM:
                        strRadio = radioM.getText().toString();
                        break;
                    case R.id.rbL:
                        strRadio = radioL.getText().toString();
                        break;
                    case R.id.rbXL:
                        strRadio = radioXL.getText().toString();
                        break;
                    case R.id.rbXXL:
                        strRadio = radioXXL.getText().toString();
                        break;
                }
            }
        });
    }

    public void addListenerOnCheckBox(){
        chkBox = (CheckBox)findViewById(R.id.checkBox);
        chkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                }
            }
        });
    }

    public void showDialogOnButtonClick(){

        //initialize the date and assign to each variables
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        imgBtn = (ImageButton)findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DILOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DILOG_ID){
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            year_x = year;
            month_x = monthOfYear;
            day_x = dayOfMonth;

            strBirthDay= year_x + "/" + month_x + "/" + day_x;
            birthText = (TextView)findViewById(R.id.txtViewBirthday);
            birthText.setText(strBirthDay);
        }
    };

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void savePreferences(String key, boolean value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private void savePreferences(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void savePreferences(String key, int value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void loadSavedPreferences(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean checkBoxValue = sharedPreferences.getBoolean("checkBox", false);
        String strName = sharedPreferences.getString("userName","");
        int selectedEyeColor =0;
        selectedEyeColor = sharedPreferences.getInt("eyeColor", 0);
        strBirthDay = sharedPreferences.getString("txtBirthDay", "");
        strRadio = sharedPreferences.getString("radioButton", "");
        pantSizeValue = sharedPreferences.getInt("pantSize", 0);
        shirtSizeValue = sharedPreferences.getInt("shirtSize", 0);
        shoeSizeValue = sharedPreferences.getInt("shoeSize",0);

        chkBox = (CheckBox)findViewById(R.id.checkBox);
        editText = (EditText)findViewById(R.id.editText);
        birthText = (TextView)findViewById(R.id.txtViewBirthday);
        skBarPant = (SeekBar)findViewById(R.id.seekBarPant);
        skBarShirt = (SeekBar)findViewById(R.id.seekBarShirt);
        skBarShoe = (SeekBar)findViewById(R.id.seekBarShoe);
        txtViewPantSize = (TextView)findViewById(R.id.txtViewPant);
        txtViewShirtSize = (TextView)findViewById(R.id.txtViewShirt);
        txtViewShoeSize = (TextView)findViewById(R.id.txtViewShoe);
        radioGp = (RadioGroup)findViewById(R.id.radioGroup);
        radioXS = (RadioButton)findViewById(R.id.rbXS);
        radioS = (RadioButton)findViewById(R.id.rbS);
        radioM = (RadioButton)findViewById(R.id.rbM);
        radioL = (RadioButton)findViewById(R.id.rbL);
        radioXL = (RadioButton)findViewById(R.id.rbXL);
        radioXXL = (RadioButton)findViewById(R.id.rbXXL);
        spinner = (Spinner)findViewById(R.id.status_spinner);

        editText.setText(strName);

        if(checkBoxValue){
            chkBox.setChecked(true);
        }else{
            chkBox.setChecked(false);
        }

        spinner.setSelection(selectedEyeColor);

        birthText.setText(strBirthDay);

        radioGp.clearCheck();

        if(strRadio.equalsIgnoreCase(radioXS.getText().toString())){
            radioXS.setChecked(true);
        }else if(strRadio.equalsIgnoreCase(radioS.getText().toString())){
            radioS.setChecked(true);
        }else if(strRadio.equalsIgnoreCase(radioM.getText().toString())){
            radioM.setChecked(true);
        }else if(strRadio.equalsIgnoreCase(radioL.getText().toString())){
            radioL.setChecked(true);
        }else if(strRadio.equalsIgnoreCase(radioXL.getText().toString())){
            radioXL.setChecked(true);
        }else if(strRadio.equalsIgnoreCase(radioXXL.getText().toString())){
            radioXXL.setChecked(true);
        }

        skBarPant.setProgress(pantSizeValue);
        txtViewPantSize.setText("(" + pantSizeValue + ")");

        skBarShirt.setProgress(shirtSizeValue);
        txtViewShirtSize.setText("(" + shirtSizeValue + ")");

        skBarShoe.setProgress(shoeSizeValue);
        txtViewShoeSize.setText("(" + (shoeSizeValue + 4) + ")");
    }

    public void SaveAllConfiguration(View v){
        savePreferences("userName",editText.getText().toString());
        savePreferences("checkBox", chkBox.isChecked());
        savePreferences("eyeColor", spinner.getSelectedItemPosition());
        savePreferences("radioButton", strRadio);
        savePreferences("txtBirthDay", strBirthDay);
        savePreferences("pantSize", pantSizeValue );
        savePreferences("shirtSize", shirtSizeValue );
        savePreferences("shoeSize", shoeSizeValue );
        finish();
    }
}