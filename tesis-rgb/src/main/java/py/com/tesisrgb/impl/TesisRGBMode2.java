/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.tesisrgb.impl;

import py.com.daas.imagestorage.models.RgbImage;
import py.com.tesisrgb.generics.Mode2;
import py.com.tesisrgb.generics.TesisRGBBasicAbstract;
import py.com.tesisrgb.generics.Weight;
import py.com.tesisrgb.models.Pixel;

import java.util.List;

/**
 *
 * @author Derlis Argüello
 * @Nomenclatura: [Metodo][Orden] 
 * @Ejemplo: [TesisRGB][Mean]
 */
public class TesisRGBMode2 extends TesisRGBBasicAbstract {

    public TesisRGBMode2(int roiWindow, String filter, RgbImage rgbImage, Pixel[] se) {
        super(roiWindow, filter, rgbImage, se);
        setFilterName("TesisRGBMode");
    }

    @Override
    public Weight getWeight() {
        return new Mode2();
    }

    //sobrecargamos este método para permitir que no se busque nada de la base de datos
    public double[] getRealWeight(Pixel p) {
        // VARIABLES
        int cSize = channels.length;
        double[] weight;

        //obtenemos el indice de ventanas que toca la ventana del elemento estructurante
        List<Integer> wIndexes = getProcessedWindow(p);

        //ajuste, si toca mas de una ventana se hace
        int hSize = channels[0].getHistogramSize();
        int[][] channelHistogram = new int[cSize][hSize];

        //se suman los histogramas de todas las ventanas que tocan al EE
        int numPixels = calculateUnifiedHistogram(wIndexes, channelHistogram);

        weight = calculateWeight(channelHistogram, numPixels);


        return weight;
    }
}