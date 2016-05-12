package com.wmartinez.devep.registro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Detalle extends AppCompatActivity{

    private static final int OK_RESULT_CODE = 1;
    private TextView inputName, inputEmail, inputTelephone, inputDescription;
    private TextView inputDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        inputName = (TextView)findViewById(R.id.tvNombre);
        inputDate = (TextView)findViewById(R.id.tvFecha);
        inputTelephone = (TextView)findViewById(R.id.tvTelefono);
        inputEmail =(TextView)findViewById(R.id.tvEmail);
        inputDescription = (TextView)findViewById(R.id.tvDescripcion);
        Bundle bundleDatos = getIntent().getExtras();

        inputName.setText(bundleDatos.getCharSequence("Nombre"));
        inputDate.setText(bundleDatos.getCharSequence("Fecha"));
        inputTelephone.setText(bundleDatos.getCharSequence("Telefono"));
        inputEmail.setText(bundleDatos.getCharSequence("Email"));
        inputDescription.setText(bundleDatos.getCharSequence("Descripcion"));
    }

    public void onBtnEditar (View view){
        returnParams();
    }

    protected void returnParams(){
        Intent iMain = new Intent();
        iMain.putExtra("Nombre", inputName.getText().toString());
       //iMain.putExtra("Fecha", inputDate.getText().toString());
        iMain.putExtra("Telefono", inputTelephone.getText().toString());
        iMain.putExtra("Email", inputEmail.getText().toString());
        iMain.putExtra("Descripcion", inputDescription.getText().toString());
        setResult(OK_RESULT_CODE , iMain);
        finish();
    }
}
