/*  
 * Tesis Arguello Balbuena
 * Derechos Reservados 2015 - 2016
 */
package py.com.daas.testmanager.models;

/**
 *
 * @author daasalbion
 * fuente: http://www.easyrgb.com/index.php?X=MATH&H=02#text2
 */
public class XYZ {
    
    protected double x, y, z;
    private double [][] rgbToXYZ = {    
                                        {0.412453,  0.357580,  0.180423},
                                        {0.212671,  0.715160,  0.072169},
                                        {0.019334,  0.119193,  0.950227}
                                    };

    public XYZ() {
        
    }
    
    public XYZ(int[] RGB) {
        
    }
    
    public XYZ(RGB rgb){
        
        RGBDiscreto rgbDiscreto = new RGBDiscreto(rgb);
                
        double R = rgbDiscreto.getR();
        double G = rgbDiscreto.getG();
        double B = rgbDiscreto.getB();
        
        if ( R > 0.04045 ) 
            R = Math.pow(( ( R + 0.055 ) / 1.055 ),  2.4);
        else                   
            R = R / 12.92;
        
        if ( G > 0.04045 ) 
            G = Math.pow( ( ( G + 0.055 ) / 1.055 ), 2.4);
        else                   
            G = G / 12.92;
        if ( B > 0.04045 ) 
            B = Math.pow( ( ( B + 0.055 ) / 1.055 ), 2.4);
        else                   
            B = B / 12.92;

        R = R * 100;
        G = G * 100;
        B = B * 100;
        
        x = (rgbToXYZ[0][0]*R + rgbToXYZ[0][1]*G + rgbToXYZ[0][2]*B);
        y = (rgbToXYZ[1][0]*R + rgbToXYZ[1][1]*G + rgbToXYZ[1][2]*B);
        z = (rgbToXYZ[2][0]*R + rgbToXYZ[2][1]*G + rgbToXYZ[2][2]*B);
    
    }
    
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

}