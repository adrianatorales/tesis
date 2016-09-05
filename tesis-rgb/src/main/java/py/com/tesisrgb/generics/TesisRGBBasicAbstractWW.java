/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.tesisrgb.generics;

import ij.gui.Roi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import py.com.daas.imagestorage.models.RgbImage;
import py.com.daas.imagestorage.models.Window;
import py.com.tesisrgb.models.Pixel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Derlis Argüello
 */
public abstract class TesisRGBBasicAbstractWW extends BasicFilterAbstract {

    static final Logger logger = LoggerFactory.getLogger(TesisRGBBasicAbstractWW.class);

    public List<Window> windowsList = new ArrayList<>();
    public int roiWindow; // Roi coordinate x, y
    private int seSideSize;
    private int xYOffset;
    private int cSize;
    private int hSize;

    public TesisRGBBasicAbstractWW(int roiWindow, String filter, RgbImage rgbImage, Pixel[] se) {
        super(filter, rgbImage, se);
        this.roiWindow = roiWindow;
        seSideSize = (int) Math.sqrt(se.length);
        xYOffset = seSideSize / 2;
        cSize = channels.length;
        hSize = channels[0].getHistogramSize();
    }

    public double[] getRealWeight(Pixel p) {
        // VARIABLES
        double[] weight;
        int[][] channelHistogram = new int[cSize][hSize];
        int numPixels = 0;
        //obtenemos la region del EE y su histograma, para cada canal
        Roi roi = new Roi(p.getX() - xYOffset, p.getY() - xYOffset, seSideSize, seSideSize);
        for(int i = 0; i < cSize; i++){
            channels[i].setRoi(roi);
            channelHistogram[i] = channels[i].getHistogram();
            numPixels = channels[i].getPixelCount();
        }

        weight = calculateWeight(channelHistogram, numPixels);

        return weight;
    }

    // Se divide en ventanas la imagen
    public void setWindowsList() throws Exception {
        //i dont do anything :)
    }

}