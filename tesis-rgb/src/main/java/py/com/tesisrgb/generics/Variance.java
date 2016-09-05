package py.com.tesisrgb.generics;

import py.com.daas.imagestorage.models.Window;

/**
 * Created by Thelma on 05/06/2016.
 */
public class Variance implements Weight {
    public double[] calculateWeight(int[][] channelHistogram, int numPixels){
        double[] firstMoment = new Mean().calculateWeight(channelHistogram, numPixels);
        double[] variance = new double[firstMoment.length];
        for (int channel = 0; channel < firstMoment.length; channel++) {
            for (int intensity = 0; intensity < channelHistogram[0].length; intensity++) {
                double centeredDifference = (double)intensity - firstMoment[channel];
                variance[channel] += Math.pow(centeredDifference, 2) * (double)channelHistogram[channel][intensity];
            }
            variance[channel] = variance[channel] / (double)numPixels;
        }
        return variance;
    }

    public double[] getWeightFromDatabase(Window window){
        return window.getVariance();
    }

}
