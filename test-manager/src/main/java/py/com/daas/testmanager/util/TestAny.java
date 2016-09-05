/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.testmanager.util;

import py.com.daas.imagestorage.models.RgbImage;
import py.com.tesisrgb.generics.BasicFilterAbstract;
import py.com.tesisrgb.impl.*;
import py.com.tesisrgb.models.Pixel;

import static py.com.daas.testmanager.util.TestConstants.Filters.TesisRGB.ConVentanas.*;
import static py.com.daas.testmanager.util.TestConstants.Filters.TesisRGB.SinVentanas.*;

/**
 *
 * @author Thelma
 */
public class TestAny {
    
    public static BasicFilterAbstract getFilterMethod(int windowCount, String filterName,
                                                      String filterType, RgbImage rgbImage, Pixel[] seEight){
        
        BasicFilterAbstract test;

        switch (filterName){
            case TESIS_RGB_MEAN:
                test = new TesisRGBMean(windowCount, filterType, rgbImage, seEight);
                break;
            case TESIS_RGB_VARIANCE:
                test = new TesisRGBVariance(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_MODE:
                test = new TesisRGBMode(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_MODE2:
                test = new TesisRGBMode2(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_MIN:
                test = new TesisRGBMin(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_MAX:
                test = new TesisRGBMax(windowCount, filterType,  
                        rgbImage, seEight);
                break;
            case TESIS_RGB_ENTROPY:
                test = new TesisRGBEntropy(windowCount, filterType,  
                        rgbImage, seEight);
                break;
            case TESIS_RGB_SMOOTHNESS:
                test = new TesisRGBSmoothness(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_MEAN_WW:
                test = new TesisRGBMeanWW(windowCount, filterType, rgbImage, seEight);
                break;
            case TESIS_RGB_VARIANCE_WW:
                test = new TesisRGBVarianceWW(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_MODE_WW:
                test = new TesisRGBModeWW(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_MIN_WW:
                test = new TesisRGBMinWW(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_MAX_WW:
                test = new TesisRGBMaxWW(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_ENTROPY_WW:
                test = new TesisRGBEntropyWW(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_SMOOTHNESS_WW:
                test = new TesisRGBSmoothnessWW(windowCount, filterType,
                        rgbImage, seEight);
                break;
            case TESIS_RGB_MODE2_WW:
                test = new TesisRGBMode2WW(windowCount, filterType,
                        rgbImage, seEight);
                break;
            default:
                test = null;
                break;
        }
        
        return test;
    }
}