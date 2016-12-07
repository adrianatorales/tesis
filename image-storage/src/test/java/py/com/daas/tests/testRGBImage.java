/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.tests;

import ij.ImagePlus;
import ij.process.ColorProcessor;
import py.com.daas.imagestorage.models.RgbImage;
import py.com.daas.imagestorage.utils.RgbImageJpaController;
import py.com.daas.imagestorage.utils.WindowMgr;

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
        String basePath = "/home/daasalbion/Imágenes/foto_de_perfil.png";
        int sLength = basePath.length();
        String extension = basePath.substring(sLength-3, sLength);
        ImagePlus imgOriginal = new ImagePlus( basePath );
        ColorProcessor colImgOriginal = (ColorProcessor) imgOriginal.getProcessor();
        RgbImage image = new RgbImage(colImgOriginal, extension);
        image.setNoiseName("without_noise");
        image.setNoiseProbability(null);
        image.setDescription(1.0);
        WindowMgr windowMgr = new WindowMgr(image, Arrays.asList(1,2,4,8));
        windowMgr.setWindowsList();
        RgbImageJpaController rgbImageJpaController = new RgbImageJpaController();
        rgbImageJpaController.create(image);
    }
}
