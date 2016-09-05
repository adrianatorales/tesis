/*
 * Tesis Arguello Balbuena
 * Derechos Reservados 2015 - 2016
 */
package py.com.daas.testmanager.models;

/**
 *
 * @author daasalbion
 */
public class RGB {
    
    private int r, g, b;

    public RGB() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }
    
    public RGB(int R, int G, int B) {
        this.r = R;
        this.g = G;
        this.b = B;
    }
    
    public RGB(int [] RGB) {
        this.r = RGB[0];
        this.g = RGB[1];
        this.b = RGB[2];
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    public int[] getRGB(){
        int [] RGB = {r,g,b};
        return RGB;
    }
    
}