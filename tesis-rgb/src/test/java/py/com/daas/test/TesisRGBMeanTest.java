/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.daas.imagestorage.models.RgbImage;
import py.com.daas.imagestorage.utils.RgbImageJpaController;
import py.com.tesisrgb.generics.BasicFilterAbstract;
import py.com.tesisrgb.impl.TesisRGBMean;
import py.com.tesisrgb.models.Pixel;

/**
 *
 * @author Derlis Argüello
 */
public class TesisRGBMeanTest {
    
    static final Logger logger = LoggerFactory.getLogger(TesisRGBMeanTest.class);
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
        RgbImage rgbImage = rgbImageJpaController.findRgbImage(1);
        
        tesisRGB = new TesisRGBMean(4, "Median", rgbImage, seEight);
        
        tesisRGB.run();
        
        logger.info(tesisRGB.toString());
//        
//        tesisRGB = new TesisRGBMean(2, "Max", rgbImage, seEight);
//        
//        tesisRGB.run();
//        
//        tesisRGB = new TesisRGBMean(2, "Min", rgbImage, seEight);
//        
//        tesisRGB.run();
    }
    
}
