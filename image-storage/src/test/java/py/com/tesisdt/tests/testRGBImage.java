/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.tesisdt.tests;

import ij.ImagePlus;
import ij.process.ColorProcessor;
import py.com.tesisdt.imagestorage.models.RgbImage;
import py.com.tesisdt.imagestorage.utils.RgbImageJpaController;
import py.com.tesisdt.imagestorage.utils.WindowMgr;

import java.util.Arrays;

/**
 *
 * @author Derlis Argüello
 */
public class testRGBImage {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String basePath = "C:\\images\\test\\noisy\\lena_ruido_gaussiano_1.png";
        //String basePath = "C:\\images\\lena\\cube.png";
        int sLength = basePath.length();
        String extension = basePath.substring(sLength-3, sLength);
        // TODO code application logic here
        ImagePlus imgOriginal = new ImagePlus( basePath );
        ColorProcessor colImgOriginal = (ColorProcessor) imgOriginal.getProcessor();

        RgbImage image = new RgbImage(colImgOriginal, extension);
        image.setNoiseProbability(null);
        image.setDescription(1.0);
        WindowMgr windowMgr = new WindowMgr(image, Arrays.asList(1,2,4,8));
        windowMgr.setWindowsList();
        RgbImageJpaController rgbImageJpaController  = new RgbImageJpaController();
        //rgbImageJpaController.create(image);
    }
    
}
