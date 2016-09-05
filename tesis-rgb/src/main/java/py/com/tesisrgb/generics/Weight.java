package py.com.tesisrgb.generics;

import py.com.daas.imagestorage.models.Window;

/**
 * Created by Thelma on 05/06/2016.
 */
public interface Weight {
    double[] calculateWeight(int[][] channelHistogram, int numPixels);
    double[] getWeightFromDatabase(Window window);
}
