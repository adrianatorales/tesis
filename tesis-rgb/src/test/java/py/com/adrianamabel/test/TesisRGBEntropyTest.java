/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.adrianamabel.test;

import java.util.ArrayList;
import java.util.List;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ColorProcessor;
import py.com.adrianamabel.imagestorage.models.RgbImage;
import py.com.adrianamabel.imagestorage.utils.RgbImageJpaController;
import py.com.tesisrgb.generics.BasicFilterAbstract;
import py.com.tesisrgb.impl.TesisRGBEntropy;
import py.com.tesisrgb.models.Pixel;
import py.com.tesisrgb.models.PixelWeight;


public class TesisRGBEntropyTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{

    	List<Pixel[]> estructurantes = new ArrayList<Pixel[]>(); 
    	
        int[] rEight = {-1, -1, -1, 0, 0, 0, 1, 1, 1};        
        int[] cEight = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        Pixel[] seEight = new Pixel[rEight.length];

        for (int i = 0; i < rEight.length; i++) {
            seEight[i] = new Pixel(rEight[i], cEight[i]);
        }
        
        //PARA 5
        
        int[] rEight5 = {-2,-2,-2,-2,-2, -1,-1,-1,-1,-1,  0,0,0,0,0, 1,1,1,1,1,1, 2,2,2,2,2};        
        int[] cEight5 = {-2,-1, 0, 1, 2, -2,-1, 0, 1, 2, -2,-1,0,1,2,-2,-1,0,1,2,-2,-1,0,1,2};
        Pixel[] seEight5 = new Pixel[rEight5.length];

        for (int i = 0; i < rEight5.length; i++) {
            seEight5[i] = new Pixel(rEight5[i], cEight5[i]);
        } 
        
        //PARA 7
        int[] rEight7 = {3,-3,-3,-3,-3,-3,-3, -2,-2,-2,-2,-2,-2,-2, -1,-1,-1,-1,-1,-1,-1,  0,0,0,0,0,0,0, 1,1,1,1,1,1,1,1, 2,2,2,2,2,2,2, 3,3,3,3,3,3,3};        
        int[] cEight7 = {-3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3};
        Pixel[] seEight7 = new Pixel[rEight7.length];

        for (int i = 0; i < rEight5.length; i++) {
            seEight7[i] = new Pixel(rEight7[i], cEight7[i]);
        } 

        
        estructurantes.add(seEight);
        estructurantes.add(seEight5); 
        estructurantes.add(seEight7);
        
        
        BasicFilterAbstract tesisRGB;
        RgbImageJpaController rgbImageJpaController = new RgbImageJpaController();
        //RgbImage rgbImage = rgbImageJpaController.findRgbImage(5); //linux
        RgbImage rgbImage = rgbImageJpaController.findRgbImage(255); 
      
        
        
        tesisRGB = new TesisRGBEntropy(1, "Median", rgbImage, estructurantes);
        ColorProcessor colImgOriginal=rgbImage.getColorProcessor();
        
        String pathRestoredImg = "/home/adriana/Documentos/recursos/test/restored"; //linux
        
       // String pathRestoredImg = "C://Users//toralead//Desktop//Tesis//gaussian//restored";
        
        try {
         
         String pathRestoredMethodImg = pathRestoredImg;
         String imgName = "img_ruido";
         ColorProcessor colImgNoiseRestored = tesisRGB.run();
         
         
         RgbImage rgbImageSinRuido = rgbImageJpaController.findRgbImage(251); 
         ColorProcessor colImgSinRuido=rgbImageSinRuido.getColorProcessor();
         
         Metrics metricas = new Metrics(colImgSinRuido, colImgNoiseRestored);
         System.out.println("GUARDANDO IMAGEN RESTAURADA");
         ImagePlus imgPlus = new ImagePlus("TesisRGBMode2", colImgNoiseRestored);
         new FileSaver(imgPlus).saveAsPng(pathRestoredMethodImg + "/" + imgName + "_" + ".jpg"); //linux
          //new FileSaver(imgPlus).saveAsPng(pathRestoredMethodImg + "//" + imgName + "_" + ".jpg");
          
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
