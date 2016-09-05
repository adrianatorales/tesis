/*
 * Tesis Arguello Balbuena
 * Derechos Reservados 2015 - 2016
 */
package py.com.daas.testmanager.models;

/**
 *
 * @author daasalbion
 */
public class RGBDiscreto {
    private double r, g, b;

    public RGBDiscreto(int R, int G, int B) {
        this.r = R/255;
        this.g = G/255;
        this.b = B/255;
    }
    
    public RGBDiscreto(int[] RGB) {
        this.r = RGB[0]/255;
        this.g = RGB[1]/255;
        this.b = RGB[2]/255;
    }
    
    public RGBDiscreto(RGB rgb) {
        this.r = (double)rgb.getR()/(double)255;
        this.g = (double)rgb.getG()/(double)255;
        this.b = (double)rgb.getB()/(double)255;
    }

    public double getR() {
        return r;
    }

    public void setR(double R) {
        this.r = R;
    }

    public double getG() {
        return g;
    }

    public void setG(double G) {
        this.g = G;
    }

    public double getB() {
        return b;
    }

    public void setB(double B) {
        this.b = B;
    }

    @Override
    public String toString() {
        return "RGBDiscreto{" + "R=" + r + ", G=" + g + ", B=" + b + '}';
    }
    
    public double[] getRGB(){
        double [] RGB = {r,g,b};
        return RGB;
    }
    
}