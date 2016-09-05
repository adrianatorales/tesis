/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.testmanager.models;

/**
 *
 * @author Thelma
 */
public class LUV {
    private double l; //L* Lightness [0, 100]
    private double u; //u* [+-100]
    private double v; //v* [+-100]
    private PuntoBlanco puntoBlanco;

    public LUV() {
    }
    
    public LUV(PuntoBlanco puntoBlanco){
        this.puntoBlanco = puntoBlanco;
    }

    public double getL() {
        return l;
    }

    public void setL(double l) {
        this.l = l;
    }

    public double getU() {
        return u;
    }

    public void setU(double u) {
        this.u = u;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public PuntoBlanco getPuntoBlanco() {
        return puntoBlanco;
    }

    public void setPuntoBlanco(PuntoBlanco puntoBlanco) {
        this.puntoBlanco = puntoBlanco;
    }
    
    public void fromXYZ(XYZ xyz){
        
        double X = xyz.getX();
        double Y = xyz.getY();
        double Z = xyz.getZ();
        
        double var_U = ( 4 * X ) / ( X + ( 15 * Y ) + ( 3 * Z ) );
        double var_V = ( 9 * Y ) / ( X + ( 15 * Y ) + ( 3 * Z ) );
        double var_Y = Y / 100;
        
        if ( var_Y > 0.008856 ) 
            var_Y = Math.pow( var_Y , 1.0/3.0 );
        else                    
            var_Y = ( 7.787 * var_Y ) + ( 16 / 116 );
        
        //si los valores son 0 de origen quedan ceros
        if ( X == 0 && Y == 0) {
            var_U = 0;
            var_V = 0;
        }

        double ref_X =  95.047;        //Observer= 2°, Illuminant= D65
        double ref_Y = 100.000;
        double ref_Z = 108.883;

        double ref_U = ( 4 * ref_X ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );
        double ref_V = ( 9 * ref_Y ) / ( ref_X + ( 15 * ref_Y ) + ( 3 * ref_Z ) );

        l = ( 116 * var_Y ) - 16;
        u = 13 * l * ( var_U - ref_U );
        v = 13 * l * ( var_V - ref_V );   
        
        Double mierda = new Double(u);
                
        if(mierda.isNaN()){
            System.out.println("Mirar que m...");
        }
        
    }
    
    private double hallarUPrima(XYZ punto){
        return (4 * punto.x / (punto.x + (15 * punto.y) + (3 * punto.z)));
    }
    
    private double hallarVPrima(XYZ punto){
        return (9 * punto.y) / (punto.x + (15 * punto.y) + (3 * punto.z));
    }
    
    
    public XYZ toXYZ(LUV luv){
        
        XYZ xyz = new XYZ();
        double un = hallarUPrima(luv.puntoBlanco);
        double vn = hallarVPrima(luv.puntoBlanco);
        double uprima = ( luv.getU() / (double)( 13*luv.getL() ) ) + un;
        double vprima = ( luv.getV() / (double)( 13*luv.getL() ) ) + vn;
        
        if(luv.getL() <= 8)
            xyz.y = (double)(luv.puntoBlanco.getY()*luv.getL()*Math.pow(((double)3/(double)29), 3));
        else
            xyz.y = (double)(luv.puntoBlanco.getY()* Math.pow(((double)(luv.getL() + 16)/(double)116), 3));
            
        xyz.x = xyz.y * ( (double)( 9*uprima )/(double)( 4*vprima ) );
        xyz.x = xyz.y * ( (double)( 12 - 3*uprima - 20*vprima ) / (double)( 4*vprima ) );

        return xyz;
    }
}