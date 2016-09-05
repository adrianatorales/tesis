package py.com.tesisrgb.generics;

import py.com.daas.imagestorage.models.Window;

/**
 * Created by Thelma on 05/06/2016.
 */
public class Entropy implements Weight {
    public double[] calculateWeight(int[][] channelHistogram, int numPixels){
        double[] entropy = new double[channelHistogram.length];
        for (int channel = 0; channel < channelHistogram.length; channel++) {
            for (int intensity = 0; intensity < channelHistogram[0].length; intensity++) {
                if(channelHistogram[channel][intensity] != 0){
                    double prob = (double)channelHistogram[channel][intensity]/(double)(numPixels);
                    entropy[channel] -= (prob * (Math.log(prob) / Math.log(2)));
                }
            }
        }
        return entropy;
    }

    public double[] getWeightFromDatabase(Window window){
        return window.getEntropy();
    }

}
