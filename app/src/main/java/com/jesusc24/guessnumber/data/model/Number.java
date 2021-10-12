package com.jesusc24.guessnumber.data.model;

import java.io.Serializable;
import java.util.Random;

/**
 * POJO para crear el número aleatorio y guardar el nombre de usuario y el número de intentos
 */
public class Number implements Serializable {
    String nombre;
    int numero, numIntentos;

    public Number(String nombre, int numIntentos) {
        Random rnd = new Random();

        this.nombre = nombre;
        this.numero = rnd.nextInt(100)+1;
        this.numIntentos = numIntentos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumero() {
        return numero;
    }

    public int getNumIntentos() {
        return numIntentos;
    }

    @Override
    public String toString() {
        return "Number{" +
                "nombre='" + nombre + '\'' +
                ", numero=" + numero +
                ", numIntentos=" + numIntentos +
                '}';
    }
}
