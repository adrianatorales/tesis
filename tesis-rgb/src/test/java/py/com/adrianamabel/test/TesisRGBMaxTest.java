/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.adrianamabel.test;

import py.com.adrianamabel.imagestorage.models.RgbImage;
import py.com.adrianamabel.imagestorage.utils.RgbImageJpaController;
import py.com.tesisrgb.generics.BasicFilterAbstract;
import py.com.tesisrgb.impl.TesisRGBMax;
import py.com.tesisrgb.models.Pixel;

/**
 *
 * @author Derlis Argüello
 */
public class TesisRGBMaxTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{

        // Create 8N Structuring Element.
        int[] rEight = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] cEight = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        Pixel[] seEight = new Pixel[rEight.length];

        for (int i = 0; i < rEight.length; i++) {
            seEight[i] = new Pixel(rEight[i], cEight[i]);
        }
        
        BasicFilterAbstract tesisRGB;
        RgbImageJpaController rgbImageJpaController = new RgbImageJpaController();
        RgbImage rgbImage = rgbImageJpaController.findRgbImage(301);
        
        /*tesisRGB = new TesisRGBMax(2, "Median", rgbImage, seEight);
        
        tesisRGB.run();
        
        tesisRGB = new TesisRGBMax(2, "Max", rgbImage, seEight);
        
        tesisRGB.run();
        
        tesisRGB = new TesisRGBMax(2, "Min", rgbImage, seEight);
        
        tesisRGB.run();*/
    }
    
}
