/*
 * Tesis Arguello Balbuena
 * Derechos Reservados 2015 - 2016
 */
package py.com.daas.testmanager.models;

public class HSV {
    private double h; //HUE va de 0 a 360 (usaremos 0 como indefinido)
    private double s; //SATURATION va del 0 al 1
    private double v; //VALUE va del 0 al 1
    
    private final int R = 0;
    private final int G = 1;
    private final int B = 2;

    public HSV() {
        this.h = this.s = this.v = 0;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }
    
    public void fromRGB(int[] rgb){
        //encontramos el maximo y el minimo de las componentes r, g, b
        double[] RGB = new double[rgb.length];
        int i = 0;
        for (double channel : rgb) {
            RGB[i] = (double)channel/(double)256;
            i++;
        }
        double MAX = findMaxRgb(RGB);
        double MIN = findMinRgb(RGB);
        double r = RGB[R];
        double g = RGB[G];
        double b = RGB[B];
        //Primero hallamos el hue (h)
        if(MAX == MIN){
            this.h = 0;
        } else if (MAX == r && g >= b){
            this.h = 60*((g - b)/(MAX - MIN)) + 0;
        } else if (MAX == r && g < b){
            this.h = 60*((g - b)/(MAX - MIN)) + 360;
        } else if (MAX == g){
            this.h = 60*((b - r)/(MAX - MIN)) + 120;
        } else if (MAX == b){
            this.h = 60*((b - r)/(MAX - MIN)) + 240;
        }
        //Ahora la saturacion (s)
        if(MAX == 0){
            this.s = 0;
        } else{
            this.s = 1 - MIN/MAX;
        }
        //Ahora el valor (v)
        v = MAX;
    }
    
    private double findMaxRgb(double[] rgb){
        double max = 0;
        if(rgb[R] > max){
            max = rgb[R];
        }
        if(rgb[G] > max){
            max = rgb[G];
        }
        if(rgb[B] > max){
            max = rgb[B];
        }
        return max;
    }
    
    private double findMinRgb(double[] rgb){
        double min = 1;
        if(rgb[R] < min){
            min = rgb[R];
        }
        if(rgb[G] < min){
            min = rgb[G];
        }
        if(rgb[B] < min){
            min = rgb[B];
        }
        return min;
    }
    
    public static double hueDifference(double hue1, double hue2){
        double resultado = Math.abs(hue1 - hue2);
        if(resultado <= 180){
            return resultado;
        }
        return Math.abs(360 - resultado);
    }
}
