package com.wmartinez.devep.registro;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    protected static final int REQUEST_CODE = 10;
    private EditText inputName, inputEmail, inputTelephone, inputDescription, mEdit;
    private EditText inputDate;
    private  DatePicker pk_Fecha;

    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputName = (EditText)findViewById(R.id.et_name);
        //inputDate = (TextView)findViewById(R.id.pk_Fecha);
        inputTelephone = (EditText)findViewById(R.id.et_telefono);
        inputEmail = (EditText)findViewById(R.id.et_email);
        inputDescription = (EditText)findViewById(R.id.et_descripcion);

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        //inputDate.setText(mm + "/" + dd + "/" + yy);
        populateSetDate(yy, mm, dd);
    }

    public class SelectDataFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }
        @Override
        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm, dd);
        }
    }
    public void onBtnClickFecha (View v) {
        DialogFragment newFragment = new SelectDataFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void populateSetDate(int year, int month, int day) {
        mEdit = (EditText) findViewById(R.id.pk_Fecha);
        mEdit.setText(month + "/" + day + "/" + year);

    }

    public void onBtnClickSiguiente (View v){
        Intent iDetalle = new Intent(MainActivity.this, Detalle.class);
        iDetalle.putExtra("Nombre", inputName.getText().toString());
        iDetalle.putExtra("Fecha", mEdit.getText().toString());
        iDetalle.putExtra("Telefono", inputTelephone.getText().toString());
        iDetalle.putExtra("Email", inputEmail.getText().toString());
        iDetalle.putExtra("Descripcion", inputDescription.getText().toString());
        startActivityForResult(iDetalle, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            Bundle bundleDatos = data.getExtras();
            inputName.setText(bundleDatos.getCharSequence("Nombre"));
            //inputDate.setText(bundleDatos.getCharSequence("Fecha"));
            inputTelephone.setText(bundleDatos.getCharSequence("Telefono"));
            inputEmail.setText(bundleDatos.getCharSequence("Email"));
            inputDescription.setText(bundleDatos.getCharSequence("Descripcion"));
            Toast.makeText(this, "De regreso: " , Toast.LENGTH_LONG).show();
        }
    }
}
