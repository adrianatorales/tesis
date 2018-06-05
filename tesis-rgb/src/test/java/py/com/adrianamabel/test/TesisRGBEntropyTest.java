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

    
    public static void main(String[] args) throws Exception{

    	List<Pixel[]> estructurantes = new ArrayList<Pixel[]>(); 
    	
        int[] rEight = {-1, -1, -1, 0, 0, 0, 1, 1, 1};        
        int[] cEight = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
        Pixel[] seEight = new Pixel[rEight.length];

        for (int i = 0; i < rEight.length; i++) {
            seEight[i] = new Pixel(rEight[i], cEight[i]);
        }
        
        //PARA 5
        
        int[] rEight5 = {-2,-2,-2,-2,-2, -1,-1,-1,-1,-1,  0,0,0,0,0, 1,1,1,1,1, 2,2,2,2,2};        
        int[] cEight5 = {-2,-1, 0, 1, 2, -2,-1, 0, 1, 2, -2,-1,0,1,2,-2,-1,0,1,2,-2,-1,0,1,2};
        Pixel[] seEight5 = new Pixel[rEight5.length];

        for (int i = 0; i < rEight5.length; i++) {
            seEight5[i] = new Pixel(rEight5[i], cEight5[i]);
        } 
        
        //PARA 7
        int[] rEight7 = {-3,-3,-3,-3,-3,-3,-3, -2,-2,-2,-2,-2,-2,-2, -1,-1,-1,-1,-1,-1,-1,  0,0,0,0,0,0,0, 1,1,1,1,1,1,1, 2,2,2,2,2,2,2, 3,3,3,3,3,3,3};        
        int[] cEight7 = {-3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3, -3,-2,-1,0,1,2,3};
        Pixel[] seEight7 = new Pixel[rEight7.length];

        for (int i = 0; i < rEight7.length; i++) {
            seEight7[i] = new Pixel(rEight7[i], cEight7[i]);
        } 

        //PARA 9
        int[] rEight9 = {-4,-4,-4,-4,-4,-4,-4,-4,-4 ,-3,-3,-3,-3,-3,-3,-3,-3,-3, -2,-2,-2,-2,-2,-2,-2,-2,-2, -1,-1,-1,-1,-1,-1,-1,-1,-1,  0,0,0,0,0,0,0,0,0, 1,1,1,1,1,1,1,1,1, 2,2,2,2,2,2,2,2,2, 3,3,3,3,3,3,3,3,3, 4,4,4,4,4,4,4,4,4};        
        int[] cEight9 = {-4,-3,-2,-1,0,1,2,3,4, -4,-3,-2,-1,0,1,2,3,4, -4,-3,-2,-1,0,1,2,3,4, -4,-3,-2,-1,0,1,2,3,4, -4,-3,-2,-1,0,1,2,3,4, -4,-3,-2,-1,0,1,2,3,4, -4,-3,-2,-1,0,1,2,3,4, -4,-3,-2,-1,0,1,2,3,4, -4,-3,-2,-1,0,1,2,3,4};
        Pixel[] seEight9 = new Pixel[rEight9.length];

        for (int i = 0; i < rEight9.length; i++) {
            seEight9[i] = new Pixel(rEight9[i], cEight9[i]);
        } 
        
        //PARA 11
        
        int[] rEight11 = {-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4 ,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3, -2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2, -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,  0,0,0,0,0,0,0,0,0,0,0, 1,1,1,1,1,1,1,1,1,1,1, 2,2,2,2,2,2,2,2,2,2,2, 3,3,3,3,3,3,3,3,3,3,3, 4,4,4,4,4,4,4,4,4,4,4, 5,5,5,5,5,5,5,5,5,5,5};        
        int[] cEight11 = {-5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5, -5,-4,-3,-2,-1,0,1,2,3,4,5};
        Pixel[] seEight11 = new Pixel[rEight11.length];

        for (int i = 0; i < rEight11.length; i++) {
               seEight11[i] = new Pixel(rEight11[i], cEight11[i]);
        } 
		
        		
        //PARA 13
        
        //PARA 15
        
        
        //PARA 17
        
        estructurantes.add(seEight);//0
        estructurantes.add(seEight5); //1
        estructurantes.add(seEight7);//2
        estructurantes.add(seEight9);//3
        estructurantes.add(seEight11);//4
        
        BasicFilterAbstract tesisRGB;
        RgbImageJpaController rgbImageJpaController = new RgbImageJpaController();
        //RgbImage rgbImage = rgbImageJpaController.findRgbImage(5); //linux
        RgbImage rgbImage = rgbImageJpaController.findRgbImage(173); 
      
        
        
        tesisRGB = new TesisRGBEntropy(1, "Median", rgbImage, seEight);
        ColorProcessor colImgOriginal=rgbImage.getColorProcessor();
        
        String pathRestoredImg = "C://Users//Silvia Torales//Documents//TESIS//images//impulsive//restored//TesisRGBMode2"; //linux
        
       // String pathRestoredImg = "C://Users//toralead//Desktop//Tesis//gaussian//restored";
        
        try {
         
         String pathRestoredMethodImg = pathRestoredImg;
         String imgName = "img_ruido";
         ColorProcessor colImgNoiseRestored = tesisRGB.run();
         
         
         RgbImage rgbImageSinRuido = rgbImageJpaController.findRgbImage(1); 
         ColorProcessor colImgSinRuido=rgbImageSinRuido.getColorProcessor();
         
         Metrics metricas = new Metrics(colImgSinRuido, colImgNoiseRestored);
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
