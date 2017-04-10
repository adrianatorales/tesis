/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.adrianamabel.test;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ColorProcessor;
import py.com.adrianamabel.imagestorage.models.RgbImage;
import py.com.adrianamabel.imagestorage.utils.RgbImageJpaController;
import py.com.tesisrgb.generics.BasicFilterAbstract;
import py.com.tesisrgb.impl.TesisRGBEntropy;
import py.com.tesisrgb.models.Pixel;


public class TesisRGBEntropyTest {

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
        //RgbImage rgbImage = rgbImageJpaController.findRgbImage(5); //linux
        RgbImage rgbImage = rgbImageJpaController.findRgbImage(5); 
        
        tesisRGB = new TesisRGBEntropy(1, "Median", rgbImage, seEight);
        ColorProcessor colImgOriginal=rgbImage.getColorProcessor();
        
        //String pathRestoredImg = "/home/adriana/Documentos/recursos/test/restored"; //linux
        
        String pathRestoredImg = "C://Users//toralead//Desktop//Tesis//gaussian//restored";
        
        try {
         
         String pathRestoredMethodImg = pathRestoredImg;
         String imgName = "img_ruido";
          ColorProcessor colImgNoiseRestored = tesisRGB.run();
          Metrics metricas = new Metrics(colImgOriginal, colImgNoiseRestored);
          System.out.println("GUARDANDO IMAGEN RESTAURADA");
          ImagePlus imgPlus = new ImagePlus("TesisRGBMode2", colImgNoiseRestored);
          //new FileSaver(imgPlus).saveAsPng(pathRestoredMethodImg + "/" + imgName + "_" + ".jpg"); //linux
          new FileSaver(imgPlus).saveAsPng(pathRestoredMethodImg + "//" + imgName + "_" + ".jpg");
          
          System.out.println("METRICA RESULTANTE");
          System.out.println(metricas.toString());
        } catch (InterruptedException ex) {
        }

        
        System.out.println("FIN DEL PROCESO");
        /*
        tesisRGB = new TesisRGBEntropy(2, "Max", rgbImage, seEight);
        
        tesisRGB.run();
        
        tesisRGB = new TesisRGBEntropy(2, "Min", rgbImage, seEight);
        
        tesisRGB.run();
        */
    }
    
}
