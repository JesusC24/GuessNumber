package com.jesusc24.guessnumber.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.jesusc24.guessnumber.R;
import com.jesusc24.guessnumber.data.model.Number;
import com.jesusc24.guessnumber.databinding.ActivityPlayBinding;

/**
 * Activity donde se hace el juego, a través de un EditText pide un número al usuario y comprueba si es o no el generado aleatoriamente,
 * mostando un mensaje si el número es mayor o menor.
 * Tiene dos Buttom para comprobar o borrar la información que se muestra en ese momento en la activity
 *
 * Tiene dos métodos para poder guardar y restaurar el estado de la activity en caso de giro de la pantalla
 */
public class PlayActivity extends AppCompatActivity {

    ActivityPlayBinding binding;
    int intentos = 1;
    Number numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //1. Recogemos el intent
        Intent intent = getIntent();
        //2. Recogemos la información
        numero = (Number) intent.getExtras().getSerializable("adivinarNumero");

        binding.btComprobar.setOnClickListener(view -> {
            if(validoNumero()) {
                if(Integer.parseInt(binding.edNumero.getText().toString()) == numero.getNumero()) {
                    mandarIntent("Intentos", String.valueOf(intentos));
                }

                intentos++;

                if(intentos >= numero.getNumIntentos()) {
                    mandarIntent("Numero", String.valueOf(numero.getNumero()));
                }

                if(Integer.parseInt(binding.edNumero.getText().toString()) > numero.getNumero()) {
                    binding.tvMayorOMenor.setText(getResources().getString(R.string.msgNumMayor));
                } else {
                    binding.tvMayorOMenor.setText(getResources().getString(R.string.msgNumMenor));
                }
            }
        });

        binding.btBorrar.setOnClickListener(view -> {
            binding.tvMayorOMenor.setText("");
            binding.edNumero.setText("");
        });
    }

    private boolean validoNumero() {
        try {
            Integer num = Integer.parseInt(binding.edNumero.getText().toString());
        } catch (Exception e) {
            showMessage(getResources().getString(R.string.msgErrorEntero));
            return false;
        }

        return true;
    }


    private void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void mandarIntent(String key, String value) {
        Bundle bundle = new Bundle();
        //1. Creamos bundle
        bundle.putString(key, value);
        bundle.putString("Nombre", numero.getNombre());
        //2. Enviar intent con el bundle
        Intent intentCreado = new Intent(this, EndPlayActivity.class);
        intentCreado.putExtras(bundle);
        startActivity(intentCreado);
    }

    /**
     * Método para guardar el estado actual de la activity
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tvMayorOMenor", binding.tvMayorOMenor.getText().toString());
    }

    /**
     * Método para restaurar el estado actual de la activity
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        binding.tvMayorOMenor.setText(savedInstanceState.getString("tvMayorOMenor"));
    }
}