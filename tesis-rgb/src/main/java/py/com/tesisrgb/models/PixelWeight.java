/*
 * Tesis Arguello Balbuena
 * Derechos Reservados 2015
 */
package py.com.tesisrgb.models;

import java.util.Arrays;

/**
 *
 * @author daasalbion
 */
public class PixelWeight {
    private int[] pixel;
    private double weight;

    public PixelWeight(int[] pixel, double weight) {
        this.pixel = pixel;
        this.weight = weight;
    }

    public int[] getPixel() {
        return pixel;
    }

    public void setPixel(int[] pixel) {
        this.pixel = pixel;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PixelWeight{" + "pixel=" + Arrays.toString(pixel) + ", weight=" + weight + '}';
    }

}