/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.tesisrgb.impl;

import py.com.daas.imagestorage.models.RgbImage;
import py.com.tesisrgb.generics.Smoothness;
import py.com.tesisrgb.generics.TesisRGBBasicAbstractWW;
import py.com.tesisrgb.generics.Weight;
import py.com.tesisrgb.models.Pixel;

/**
 *
 * @author Derlis Argüello
 * @Nomenclatura: [Metodo][Orden] 
 * @Ejemplo: [TesisRGB][Mean]
 */
public class TesisRGBSmoothnessWW extends TesisRGBBasicAbstractWW {

    public TesisRGBSmoothnessWW(int roiWindow, String filter, RgbImage rgbImage, Pixel[] se) {
        super(roiWindow, filter, rgbImage, se);
        setFilterName("TesisRGBSmoothnessWW");
    }

    @Override
    public Weight getWeight() {
        return new Smoothness();
    }
}