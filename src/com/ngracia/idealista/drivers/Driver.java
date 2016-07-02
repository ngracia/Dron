package com.ngracia.idealista.drivers;

import com.ngracia.idealista.Utilities.ArrayUtil;
import com.ngracia.idealista.entities.UrbanizacionOrigen;
import com.ngracia.idealista.entities.Urbanizaciones;
import com.ngracia.idealista.enums.EnumDirecciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.ngracia.idealista.enums.EnumDirecciones.getRandom;

/**
 * Created by Nestor Gracia on 2/06/2016.
 */
public class Driver {
    static Urbanizaciones[][] urbanizaciones;
    static String CoordenadasX;
    static String CoordenadasY;

    public String obtenerIdentificadorUrbanizacion(String x, String y){
        String urb = null;
        try {
            urb = urbanizaciones[Integer.parseInt(x)][Integer.parseInt(y)].getId();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return urb;
    }

    public String ObtenerAdyacente(String urbId, EnumDirecciones direccion){
        Urbanizaciones urbObj = null;
        try{
            found:
            for(int i = 0; i < urbanizaciones.length; i++) {
                for (int j = 0; j < urbanizaciones.length; j++) {
                    if (urbanizaciones[i][j].getId() == urbId) {
                        urbObj = urbanizaciones[i][j];
                        break found;
                    }
                }
            }

            switch (direccion){
                case Arriba:
                    urbId = moverArriba(urbObj);
                    break;
                case Abajo:
                    urbId = moverAbajo(urbObj);
                    break;
                case Derecha:
                    urbId = moverDerecha(urbObj);
                    break;
                case Izquierda:
                    urbId = moverIzquierda(urbObj);
                    break;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return urbId;
    }

    private String moverArriba(Urbanizaciones urbObj){
        String result = urbObj.getId();
        try{
            String x = String.valueOf(Integer.parseInt(urbObj.getX()) - 1);
            String y = urbObj.getY();

            if(Integer.parseInt(x) >= 0){
                if (!esUrbanizacionVisitada(obtenerIdentificadorUrbanizacion(x,y))){
                    marcarVistadaPosicionActual(urbObj);
                    result = obtenerIdentificadorUrbanizacion(x, y);
                    marcarPosicionActual(result);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return result;
    }

    private String  moverAbajo(Urbanizaciones urbObj){

        String urbanizacion = urbObj.getId();
        try {
            String x = String.valueOf(Integer.parseInt(urbObj.getX()) + 1);
            String y = urbObj.getY();

            if(Integer.parseInt(x) < urbanizaciones.length){
                if (!esUrbanizacionVisitada(obtenerIdentificadorUrbanizacion(x,y))){
                    marcarVistadaPosicionActual(urbObj);
                    urbanizacion = obtenerIdentificadorUrbanizacion(x, y);
                    marcarPosicionActual(urbanizacion);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return urbanizacion;
    }

    private String moverDerecha(Urbanizaciones urbObj){

        String urbanizacion = urbObj.getId();
        String x = urbObj.getX();
        String y = String.valueOf(Integer.parseInt(urbObj.getY()) + 1);

        if(Integer.parseInt(y) < urbanizaciones.length){
            if (!esUrbanizacionVisitada(obtenerIdentificadorUrbanizacion(x,y))){
                marcarVistadaPosicionActual(urbObj);
                urbanizacion = obtenerIdentificadorUrbanizacion(x, y);
                marcarPosicionActual(urbanizacion);
            }
        }

        return urbanizacion;
    }

    private String moverIzquierda(Urbanizaciones urbObj) {
        String urbanizacion = urbObj.getId();
        try {
            String x = urbObj.getX();
            String y = String.valueOf(Integer.parseInt(urbObj.getY()) - 1);

            if (Integer.parseInt(y) >= 0) {
                if (!esUrbanizacionVisitada(obtenerIdentificadorUrbanizacion(x,y))){
                    marcarVistadaPosicionActual(urbObj);
                    urbanizacion = obtenerIdentificadorUrbanizacion(x, y);
                    marcarPosicionActual(urbanizacion);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return urbanizacion;
    }

    private void marcarVistadaPosicionActual(Urbanizaciones urbObj){
        try{
            urbObj.setVisitada(true);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private boolean esUrbanizacionVisitada(String urbId){
        boolean vistada = false;
        try{
            found1: for(int i = 0; i < urbanizaciones.length; i++) {
                for (int j = 0; j < urbanizaciones.length; j++) {
                    if (urbanizaciones[i][j].getId() == urbId) {
                        vistada = urbanizaciones[i][j].isVisitada();
                        break found1;
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return vistada;
    }

   private void marcarPosicionActual(String urbId) {
       Urbanizaciones urb = null;
       try{
           found1:
           for (int i = 0; i < urbanizaciones.length; i++) {
               for (int j = 0; j < urbanizaciones.length; j++) {
                   if (urbanizaciones[i][j].getId() == urbId) {
                       urb = urbanizaciones[i][j];
                       break found1;
                   }
               }
           }
       }catch (Exception ex){
           ex.printStackTrace();
       }
   }

    public List<String> obtenerUrbanizaciones(String x, String y, String rango){
        List<String> listaUrb = new ArrayList<>();
        String urbActual = null;
        int areaTotal = 0;

        try{

            //Tamaño area n(rango) veces 9
            areaTotal = 9 * Integer.parseInt(rango);

            //Construimos Matriz
            urbanizaciones = ArrayUtil.buildArray(rango);

            //Inicializamos la matriz
            ArrayUtil.InitializeArray(urbanizaciones);

            //Establecemos urb origen
            UrbanizacionOrigen urbanizacionOrigen = ArrayUtil.getStartingPoint(rango);
            urbanizaciones[Integer.parseInt(urbanizacionOrigen.getX())][Integer.parseInt(urbanizacionOrigen.getY())].setOrigen(true);

            //Obtener urbId actual y guardarlo en mi listas de urb recorridas
            urbActual = obtenerIdentificadorUrbanizacion(urbanizacionOrigen.getX(), urbanizacionOrigen.getY());
            listaUrb.add(urbActual);

            //movimientos
            EnumDirecciones randomEnumDirecciones = getRandom();

            do {
                urbActual = ObtenerAdyacente(urbActual, randomEnumDirecciones);
                Collections.sort(listaUrb);
                if(Collections.binarySearch(listaUrb, urbActual) >= 0){
                    randomEnumDirecciones = getRandom();
                }else{
                    listaUrb.add(urbActual);
                }

            }while(listaUrb.size() < areaTotal);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return listaUrb;
    }
}