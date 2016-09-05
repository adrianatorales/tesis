package py.com.tesisrgb.generics;

import py.com.daas.imagestorage.models.Window;

/**
 * Created by Thelma on 05/06/2016.
 */
public class Max implements Weight {
    public double[] calculateWeight(int[][] channelHistogram, int numPixels){
        double[] max = new double[channelHistogram.length];
        for (int channel = 0; channel < channelHistogram.length; channel++) {
            for (int intensity = channelHistogram[0].length - 1; intensity >= 0; intensity--) {
                if(channelHistogram[channel][intensity] != 0){
                    max[channel] = intensity;
                    break;
                }
            }
        }
        return max;
    }

    public double[] getWeightFromDatabase(Window window){
        return window.getMaximum();
    }

}
