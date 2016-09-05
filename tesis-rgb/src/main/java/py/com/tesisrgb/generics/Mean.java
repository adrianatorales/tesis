package py.com.tesisrgb.generics;

import py.com.daas.imagestorage.models.Window;

/**
 * Created by Thelma on 05/06/2016.
 */
public class Mean implements Weight {

    public double[] calculateWeight(int[][] channelHistogram, int numPixels) {
        double[] firstMoment = new double[channelHistogram.length];
        for (int channel = 0; channel < channelHistogram.length; channel++) {
            for (int intensity = 0; intensity < channelHistogram[0].length; intensity++) {
                firstMoment[channel] += (intensity) * channelHistogram[channel][intensity];
            }
            firstMoment[channel] = firstMoment[channel] / (double) numPixels;
        }
        return firstMoment;
    }

    public double[] getWeightFromDatabase(Window window){
        return window.getMean();
    }

}
