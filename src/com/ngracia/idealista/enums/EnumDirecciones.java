package com.ngracia.idealista.enums;

/**
 * Created by Nestor Gracia on 01/07/2016.
 */
public enum EnumDirecciones {
    Arriba,
    Abajo,
    Izquierda,
    Derecha;

    public static EnumDirecciones getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
