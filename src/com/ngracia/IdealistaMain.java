package com.ngracia;

import com.ngracia.idealista.drivers.Driver;
import com.ngracia.idealista.entities.DatosTeclado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Nestor Gracia on 2/06/2016.
 */
public class IdealistaMain {
    public static void main(String [] args) {
        try {

            IdealistaMain idealistaMain = new IdealistaMain();
            DatosTeclado datosTeclado = idealistaMain.typeData();

            Driver driver = new Driver();
            List<String> listaUrb = driver.obtenerUrbanizaciones(datosTeclado.getX(), datosTeclado.getY(), datosTeclado.getRange());

            System.out.println(listaUrb);

        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public DatosTeclado typeData() {
        DatosTeclado keyboadEntry = new DatosTeclado();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Introduce coordenada X:");
            keyboadEntry.setX(br.readLine());
            System.out.println("Introduce coordenada X");
            keyboadEntry.setY(br.readLine());
            System.out.println("Introduce rango");
            keyboadEntry.setRange(br.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyboadEntry;
    }
}
