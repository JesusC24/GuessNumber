package com.jesusc24.guessnumber.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Region;
import android.os.Bundle;
import android.widget.Toast;

import com.jesusc24.guessnumber.R;
import com.jesusc24.guessnumber.data.model.Number;
import com.jesusc24.guessnumber.databinding.ActivityConfigBinding;

/**
 * Primera activity en mostrarse
 * Pide los datos del nombre y el número de intentos para poder empezar el juego
 * y se lo manda a la activity PlayActivity
 */
public class ConfigActivity extends AppCompatActivity {

    ActivityConfigBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfigBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btJugar.setOnClickListener(view -> {
            //1. Creo un objeto bundle
            Bundle bundle = new Bundle();
            //2. Añadir numero al bundle
            //2. Primero comprobamos que tanto el nombre de Usuario como el Número de intentos sean válidos
            if(validoNombreUsuario() && validoNumIntentos()) {
                Number numero = new Number(binding.edUser.getText().toString(), Integer.parseInt(binding.edNumIntentos.getText().toString()));
                bundle.putSerializable("adivinarNumero", numero);
                //3. Creamos el Intent y lo mandamos con el Bundle
                Intent intent = new Intent(this, PlayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }


    /**
     * Sirve para validar que un número es un int
     * @return
     */
    private boolean validoNumIntentos() {
        try {
            Integer num = Integer.parseInt(binding.edNumIntentos.getText().toString());
        } catch (Exception e) {
            showMessage(getResources().getString(R.string.msgErrorEntero));
            return false;
        }

        return true;
    }

    /**
     * Sirve para validar que no se ha introducido un mensaje vacío
     * @return
     */
    private boolean validoNombreUsuario() {
        if(binding.edUser.getText().toString().isEmpty()) {
            showMessage(getResources().getString(R.string.msgCadenaVacia));
            return false;
        } else {
            return true;
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}