/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.tesisrgb.generics;

import py.com.daas.imagestorage.models.RgbImage;
import py.com.daas.imagestorage.models.Window;
import py.com.tesisrgb.models.Pixel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Derlis Argüello
 */
public abstract class TesisRGBBasicAbstract extends BasicFilterAbstract {

    public List<Window> windowsList = new ArrayList<>();
    public int roiWindow; // Roi coordinate x, y

    public TesisRGBBasicAbstract(int roiWindow, String filter, RgbImage rgbImage, Pixel[] se) {
        super(filter, rgbImage, se);
        this.roiWindow = roiWindow;
    }

    // Se divide en ventanas la imagen
    public void setWindowsList() throws Exception {
        windowsList = rgbImageJpaController.getWindowsList(rgbImage.getIdRgbImage(), roiWindow);
    }

    public double[] getWeightFromDatabase(Window window){
        return weight.getWeightFromDatabase(window);
    }
    
    public double[] getRealWeight(Pixel p) {
        // VARIABLES
        int cSize = channels.length;
        double[] weight;

        //obtenemos el indice de ventanas que toca la ventana del elemento estructurante
        List<Integer> wIndexes = getProcessedWindow(p);

        //ajuste, si toca mas de una ventana se hace
        if (wIndexes.size() > 1) {
            int hSize = channels[0].getHistogramSize();
            int[][] channelHistogram = new int[cSize][hSize];

            //se suman los histogramas de todas las ventanas que tocan al EE
            int numPixels = calculateUnifiedHistogram(wIndexes, channelHistogram);

            weight = calculateWeight(channelHistogram, numPixels);

            //logger.debug("weight={}", Arrays.toString(weight));

        } else {
            Window touchingWindow = windowsList.get(wIndexes.get(0));
            weight = getWeightFromDatabase(touchingWindow);
        }

        return weight;
    }
    
    /**
     * obtain the window/windows index/es that touch the se
     * @param p actual pixel of the se
     * @return list of indexes of windows that touch the se
     */
    public List<Integer> getProcessedWindow(Pixel p){
        List<Integer> wIndexes = new ArrayList<>();
        Pixel pixel = new Pixel(0, 0);
        
        //ITERATE STRUCTURE ELEMENT
        for (Pixel sePixel : se) {
            pixel.setX(p.getX() + sePixel.getX());
            pixel.setY(p.getY() + sePixel.getY());
            for (Window window : windowsList) {
                //logger.debug("window={}", window.toString());
                //se proceso una ventana completa
                if ( ( window.getRoi().contains(pixel.getX(), pixel.getY()) ) ){
                    //si el pixel cae en una ventana ya procesada se ignora
                    if(!wIndexes.contains(windowsList.indexOf(window))) {
                        //add the window o windows to be processed
                        wIndexes.add(windowsList.indexOf(window));
                    }
                    //un pixel solo puede estar en una ventana
                    break;
                }
            }
        }
        
        //logger.debug("wIndexes={}", wIndexes.toString());
        
        return wIndexes;
    }

    /**
     * This function obtains a single histogram which is the sum of
     * all histograms of windows corresponding to wIndexes.
     * It modifies the values inside the reference channelHistogram
     * @param wIndexes
     * @return total number of pixels in all windows passed
     */
    public int calculateUnifiedHistogram(List<Integer> wIndexes, int[][] channelHistogram){
        int numPixels = 0;
        int hSize = channels[0].getHistogramSize();
        //se suman los histogramas de todas las ventanas que tocan al EE
        for (Integer wIndex : wIndexes) {
            Window currentWindow = windowsList.get(wIndex);
            numPixels += currentWindow.getTotalPixels();
            for (int channel = 0; channel < channels.length; channel++) {
                channels[channel].setRoi(currentWindow.getRoi());
                int[] currentHistogram = channels[channel].getHistogram();
                for (int intensity = 0; intensity < hSize; intensity++) {
                    channelHistogram[channel][intensity] += currentHistogram[intensity];
                }
            }
        }
        return numPixels;
    }

}