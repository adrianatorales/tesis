/*
 * Tesis Arguello Balbuena
 * Derechos Reservados 2015
 */
package py.com.tesisrgb.models;

import java.util.Arrays;

/**
 *
 * @author 
 */
public class PixelWeight {
    private int[] pixel;
    private double weight;
    private int posicionX;
    private int posicionY;

    public PixelWeight(){}
    
    public PixelWeight(int[] pixel, double weight, Pixel posicion) {
        this.pixel = pixel;
        this.weight = weight;
        this.posicionX=posicion.getX();
        this.posicionY=posicion.getY();
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
    
    

    public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	@Override
	public String toString() {
		return "PixelWeight [pixel=" + Arrays.toString(pixel) + ", weight=" + weight + ", posicionX=" + posicionX
				+ ", posicionY=" + posicionY + "]";
	}

}