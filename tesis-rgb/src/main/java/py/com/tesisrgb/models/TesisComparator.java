/*
 * Tesis Arguello Balbuena
 * Derechos Reservados 2015 - 2016
 */
package py.com.tesisrgb.models;

import java.util.Comparator;

/**
 *
 * @author daasalbion
 */
public class TesisComparator implements Comparator<PixelWeight>{
    
    //cantidad de veces que se opta por canal
    public long [] chooseChannel;
    public long valorReducido = 0;
    
    public TesisComparator(int cSize) {
        this.chooseChannel = new long[cSize];
    }

    @Override
    public int compare(PixelWeight o1, PixelWeight o2) {
        if (o1.getWeight() == o2.getWeight()) {
            //pesos iguales, se va a decidir en el lexicografico
            int [] color1 = o1.getPixel();
            int [] color2 = o2.getPixel();

            for (int i = 0; i < color2.length; i++) {
                if ( color1[i] < color2[i] ) {
                    chooseChannel[i]++;
                    return -1;
                } else if ( color1[i] > color2[i] ) {
                    chooseChannel[i]++;
                    return 1;
                }
            }
            return 0;
        }else if ( o1.getWeight() < o2.getWeight() ) {
            valorReducido++;
            return -1;
        } else {
            valorReducido++;
            return  1;
        }
    }   
}