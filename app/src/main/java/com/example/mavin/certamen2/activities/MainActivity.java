package com.example.mavin.certamen2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mavin.certamen2.R;

public class MainActivity extends AppCompatActivity {

    private Button buscar;
    private EditText usuario;
    String usuarioBuscar;
    String respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario=(EditText)findViewById(R.id.editTextNombre);
        buscar=(Button)findViewById(R.id.ButtonBuscar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioBuscar = usuario.getText().toString();

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("usuario",usuarioBuscar);
                startActivity(intent);

            }
        });
    }
}
