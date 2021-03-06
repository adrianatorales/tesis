/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.tesisrgb.impl;

import java.util.List;

import py.com.adrianamabel.imagestorage.models.RgbImage;
import py.com.tesisrgb.generics.Mean;
import py.com.tesisrgb.generics.TesisRGBBasicAbstract;
import py.com.tesisrgb.generics.Weight;
import py.com.tesisrgb.models.Pixel;

/**
 *
 * @author Derlis Argüello
 * @Nomenclatura: [Metodo][Orden] 
 * @Ejemplo: [TesisRGB][Mean]
 */
public class TesisRGBMeanWW extends TesisRGBBasicAbstract {

    public TesisRGBMeanWW(int roiWindow, String filter, RgbImage rgbImage, Pixel[] se) {
        super(roiWindow, filter, rgbImage, se);
        setFilterName("TesisRGBMeanWW");
    }

    @Override
    public Weight getWeight() {
        return new Mean();
    }
}