package com.jesusc24.guessnumber.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jesusc24.guessnumber.R;
import com.jesusc24.guessnumber.databinding.ActivityEndPlayBinding;

/**
 * Muestra si el usuario ha acertado o ha fallado.
 * En el caso de aciertos muestra el número de intentos que ha necesitado
 * En el caso de fallo, muestra el número correcto
 */
public class EndPlayActivity extends AppCompatActivity {

    ActivityEndPlayBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEndPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //1. Cogemos el intent
        Intent intent = getIntent();

        if(intent.getExtras().getString("Intentos") != null) {
            binding.tvAciertoFallo.setText(getResources().getString(R.string.tvAcertar) + " "+ intent.getExtras().getString("Nombre"));
            binding.tvNumeroOIntentos.setText(getResources().getString(R.string.tvTotalIntentos));
            binding.tvResultado.setText(intent.getExtras().getString("Intentos"));

        } else if(intent.getExtras().getString("Numero") != null) {
            binding.tvAciertoFallo.setText(getResources().getString(R.string.tvFallo) + " "+ intent.getExtras().getString("Nombre"));
            binding.tvNumeroOIntentos.setText(getResources().getString(R.string.tvNumCorrecto));
            binding.tvResultado.setText(intent.getExtras().getString("Numero"));
        }
    }
}