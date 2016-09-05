package py.com.tesisrgb.generics;

import py.com.daas.imagestorage.models.Window;

/**
 * Created by Thelma on 05/06/2016.
 */
public class Min implements Weight {
    public double[] calculateWeight(int[][] channelHistogram, int numPixels){
        double[] min = new double[channelHistogram.length];
        for (int channel = 0; channel < channelHistogram.length; channel++) {
            for (int intensity = 0; intensity < channelHistogram[0].length; intensity++) {
                if(channelHistogram[channel][intensity] != 0){
                    min[channel] = intensity;
                    break;
                }
            }
        }
        return min;
    }

    public double[] getWeightFromDatabase(Window window){
        return window.getMinimum();
    }

}
