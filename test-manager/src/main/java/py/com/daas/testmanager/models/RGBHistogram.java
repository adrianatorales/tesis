/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.testmanager.models;

import ij.process.ColorProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Derlis Argüello
 */
public class RGBHistogram {
    
    static final Logger logger = LoggerFactory.getLogger(RGBHistogram.class);
    
    private final HashMap<String, Integer> colorCountMap = new HashMap<>();
    private final HashMap<String, Integer> colorCumulativeCountMap = new HashMap<>();

    public RGBHistogram(ColorProcessor imageProcessor) {
        
        String colorMap;
        int[]rgb = new int[imageProcessor.getNChannels()];
        
        for (int y = 0; y < imageProcessor.getHeight(); y++) {
            for (int x = 0; x < imageProcessor.getWidth(); x++) {
                rgb = imageProcessor.getPixel(x, y, rgb);
                //logger.debug("rgbColor = {}", Arrays.toString(rgb));
                colorMap = String.valueOf(rgb[0]) + "_" + String.valueOf(rgb[1]) + "_" + String.valueOf(rgb[2]);
                if (!colorCountMap.containsKey(colorMap)) {
                    colorCountMap.put(colorMap, 1);
                } else {
                    colorCountMap.replace(colorMap, colorCountMap.get(colorMap) + 1);
                }
            }
        }
        
        setCumulativeHistogram();
    }
    
    public void setCumulativeHistogram() {
        Set set = colorCountMap.entrySet();
        Iterator iterator = set.iterator();
        int count = 0;
        while(iterator.hasNext()) {
           Map.Entry<String, Integer> mentry = (Map.Entry<String, Integer>)iterator.next();
           count += mentry.getValue();
           //logger.debug("key = {}, value = {}, count = {}", mentry.getKey(), mentry.getValue(), count);
           colorCumulativeCountMap.put(mentry.getKey(), count);
        }
    }
    
    public String[] getRGBOrder(){
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : colorCountMap.entrySet()) {
            //logger.debug("entry = {}", entry.toString());
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                if(!(entry.getKey().compareTo("0_0_0") == 0 ) && !(entry.getKey().compareTo("255_255_255") == 0)) {
                    maxEntry = entry;
                }
            }
        }
        logger.debug("maxEntry = {}", maxEntry.toString());
        String[] data = maxEntry.getKey().split("_");
        return data;
    }

    public HashMap<String, Integer> getColorCountMap() {
        return colorCountMap;
    }

    public HashMap<String, Integer> getColorCumulativeCountMap() {
        return colorCumulativeCountMap;
    }

}