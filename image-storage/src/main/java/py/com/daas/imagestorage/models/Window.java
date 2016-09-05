/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.imagestorage.models;

import ij.gui.Roi;

import java.util.List;

/**
 *
 * @author Derlis Argüello
 */
public class Window {
    private Roi roi;
    private double[] mean;
    private double[] variance;
    private double[] modes;
    private double[] minimum;
    private double[] maximum;
    private double[] energy;
    private double[] entropy;
    private double[] skewness;
    private double[] kurtosis;
    private double[] thirdCentralMoment;
    private double[] fourthCentralMoment;
    private double[] fifthCentralMoment;
    private double[] sixthCentralMoment;
    private double[] seventhCentralMoment;
    private double[] eighthCentralMoment;
    private double[] smoothness;
    private int totalPixels;

    public Window(List<RgbWindow> wList) {
        //obtenemos la primera ventana como referencia
        RgbWindow dadWindow = wList.get(0);
        int cSize = wList.size();
        this.roi = dadWindow.getRoi();
        this.totalPixels = dadWindow.getPixelCount();
        this.mean = new double[cSize];
        this.variance = new double[cSize];
        this.modes = new double[cSize];
        this.minimum = new double[cSize];
        this.maximum = new double[cSize];
        this.energy = new double[cSize];
        this.entropy = new double[cSize];
        this.skewness = new double[cSize];
        this.kurtosis = new double[cSize];
        this.thirdCentralMoment = new double[cSize];
        this.fourthCentralMoment = new double[cSize];
        this.fifthCentralMoment = new double[cSize];
        this.sixthCentralMoment = new double[cSize];
        this.seventhCentralMoment = new double[cSize];
        this.eighthCentralMoment = new double[cSize];
        this.smoothness = new double[cSize];
        for (int i = 0; i < cSize; i++) {
            this.mean[i] = wList.get(i).getMean();
            this.variance[i] = wList.get(i).getVariance();
            this.modes[i] = wList.get(i).getModes();
            this.minimum[i] = wList.get(i).getMinimum();
            this.maximum[i] = wList.get(i).getMaximum();
            this.energy[i] = wList.get(i).getEnergy();
            this.entropy[i] = wList.get(i).getEntropy();
            this.fifthCentralMoment[i] = wList.get(i).getFifthCentralMoment();
            this.sixthCentralMoment[i] = wList.get(i).getSixthCentralMoment();
            this.seventhCentralMoment[i] = wList.get(i).getSeventhCentralMoment();
            this.eighthCentralMoment[i] = wList.get(i).getEighthCentralMoment();
            this.thirdCentralMoment[i] = wList.get(i).getThirdCentralMoment();
            this.fourthCentralMoment[i] = wList.get(i).getFourthCentralMoment();
            this.skewness[i] = wList.get(i).getSkewness();
            this.kurtosis[i] = wList.get(i).getKurtosis();
            this.smoothness[i] = wList.get(i).getSmoothness();
        }
    }

    public Roi getRoi() {
        return roi;
    }

    public void setRoi(Roi roi) {
        this.roi = roi;
    }

    public double[] getMean() {
        return mean;
    }

    public void setMean(double[] mean) {
        this.mean = mean;
    }

    public double[] getVariance() {
        return variance;
    }

    public void setVariance(double[] variance) {
        this.variance = variance;
    }
    
    public double[] getModes() {
        return modes;
    }

    public void setModes(double[] modes) {
        this.modes = modes;
    }

    public double[] getMinimum() {
        return minimum;
    }

    public void setMinimum(double[] minimum) {
        this.minimum = minimum;
    }

    public double[] getMaximum() {
        return maximum;
    }

    public void setMaximum(double[] maximum) {
        this.maximum = maximum;
    }
    
    public double[] getEnergy() {
        return energy;
    }

    public void setEnergy(double[] energy) {
        this.energy = energy;
    }

    public double[] getEntropy() {
        return entropy;
    }

    public void setEntropy(double[] entropy) {
        this.entropy = entropy;
    }
    
    public int getTotalPixels() {
        return totalPixels;
    }

    public void setTotalPixels(int totalPixels) {
        this.totalPixels = totalPixels;
    }

    public double[] getFifthCentralMoment() {
        return fifthCentralMoment;
    }

    public void setFifthCentralMoment(double[] fifthCentralMoment) {
        this.fifthCentralMoment = fifthCentralMoment;
    }

    public double[] getSixthCentralMoment() {
        return sixthCentralMoment;
    }

    public void setSixthCentralMoment(double[] sixthCentralMoment) {
        this.sixthCentralMoment = sixthCentralMoment;
    }

    public double[] getSeventhCentralMoment() {
        return seventhCentralMoment;
    }

    public void setSeventhCentralMoment(double[] seventhCentralMoment) {
        this.seventhCentralMoment = seventhCentralMoment;
    }

    public double[] getEighthCentralMoment() {
        return eighthCentralMoment;
    }

    public void setEighthCentralMoment(double[] eighthCentralMoment) {
        this.eighthCentralMoment = eighthCentralMoment;
    }

    @Override
    public String toString() {
        return "Window{" + "roi=" + roi + ", mean=" + mean + ", variance=" + variance + ", modes=" + modes + ", minimum=" + minimum + ", maximum=" + maximum + ", energy=" + energy + ", entropy=" + entropy + ", skewness=" + skewness + ", kurtosis=" + kurtosis + ", thirdCentralMoment=" + thirdCentralMoment + ", fourthCentralMoment=" + fourthCentralMoment + ", fifthCentralMoment=" + fifthCentralMoment + ", sixthCentralMoment=" + sixthCentralMoment + ", seventhCentralMoment=" + seventhCentralMoment + ", eighthCentralMoment=" + eighthCentralMoment + ", totalPixels=" + totalPixels + '}';
    }

    public double[] getSkewness() {
        return skewness;
    }

    public void setSkewness(double[] skewness) {
        this.skewness = skewness;
    }

    public double[] getKurtosis() {
        return kurtosis;
    }

    public void setKurtosis(double[] kurtosis) {
        this.kurtosis = kurtosis;
    }

    public double[] getThirdCentralMoment() {
        return thirdCentralMoment;
    }

    public void setThirdCentralMoment(double[] thirdCentralMoment) {
        this.thirdCentralMoment = thirdCentralMoment;
    }

    public double[] getFourthCentralMoment() {
        return fourthCentralMoment;
    }

    public void setFourthCentralMoment(double[] fourthCentralMoment) {
        this.fourthCentralMoment = fourthCentralMoment;
    }

    public double[] getSmoothness() {
        return smoothness;
    }

    public void setSmoothness(double[] smoothness) {
        this.smoothness = smoothness;
    }
}