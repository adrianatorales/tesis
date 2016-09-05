/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.testmanager.models;

/**
 * Esta clase representa a un punto blanco de referencia NORMALIZADO.
 * Hereda de la clase XYZ porque es un punto en ese espacio
 * @author Thelma
 */
public class PuntoBlanco extends XYZ{

    public PuntoBlanco() {
    }
    
    public static class Iluminantes{
        
        public static class D65{
            public static final String nombre = "D65";
            public static final double x = 0.3127;
            public static final double y = 0.3290;
            public static final double z = 0.3583;
        }
        
        public static class E{
            public static final String nombre = "E";
            public static final double x = 0.33333333;
            public static final double y = 0.33333333;
            public static final double z = 0.33333333;
        }
        
        public static class C{
            public static final String nombre = "C";
            public static final double x = 0.310;
            public static final double y = 0.316;
            public static final double z = 0.374;
        }
    }
    
    /*
    * Este constructor crea un objeto de la clase PuntoBlanco segun 
    * el nombre de iluminante que se le pasa como par�metro.
    */
    public PuntoBlanco(String nombreIluminante) {
        if(nombreIluminante.equals(Iluminantes.D65.nombre)){
            this.x = Iluminantes.D65.x;
            this.y = Iluminantes.D65.y;
            this.z = Iluminantes.D65.z;
        } else if(nombreIluminante.equals(Iluminantes.C.nombre)){
            this.x = Iluminantes.C.x;
            this.y = Iluminantes.C.y;
            this.z = Iluminantes.C.z;;
        } else if(nombreIluminante.equals(Iluminantes.E.nombre)){
            this.x = Iluminantes.E.x;
            this.y = Iluminantes.E.y;
            this.z = Iluminantes.E.z;
        }
        normalizarComponentes();
    }
    
    /**
     * Este m�todo normaliza las componentes del punto blanco
     * La normalizaci�n se hace de forma a que la componente Y (correspondiente
     * a la intensidad) valga siempre 100.
     */
    private void normalizarComponentes(){
        double factor = (double)100/z;
        x = x*factor;
        y = y*factor;
        z = z*factor;
    }
}
