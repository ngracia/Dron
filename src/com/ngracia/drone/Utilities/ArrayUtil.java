package com.ngracia.drone.Utilities;
import com.ngracia.drone.entities.UrbanizacionOrigen;
import com.ngracia.drone.entities.Urbanizaciones;

/**
 * Created by Nestor Gracia on 2/06/2016.
 */
public class ArrayUtil {

    public static Urbanizaciones[][] construirMatriz(String rango){
        Urbanizaciones[][] urbanizaciones = null;

        try{
            int arraySize = 2 * Integer.parseInt(rango) + 1;
            urbanizaciones = new Urbanizaciones[arraySize][arraySize];
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return urbanizaciones;
    }

    public static UrbanizacionOrigen obtenerPuntoDePartida(String rango){
        UrbanizacionOrigen arrayResultHelper = new UrbanizacionOrigen();
        try{
            arrayResultHelper.setX(rango);
            arrayResultHelper.setY(rango);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return arrayResultHelper;
    }

    public static void InicializarMatriz(Urbanizaciones[][] urbanizaciones){
        try{
            int urbNumber = 1;
            for(int i = 0; i < urbanizaciones.length ; i++){
                for(int j = 0; j < urbanizaciones.length ; j++){
                    Urbanizaciones urb = new Urbanizaciones();
                    urb.setId( "Urb" + urbNumber);
                    urbanizaciones[i][j] = urb;
                    urbanizaciones[i][j].setX(String.valueOf(i));
                    urbanizaciones[i][j].setY(String.valueOf(j));
                    urbNumber +=1;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
