package py.com.tesisrgb.generics;

import ij.process.ColorProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;

import py.com.tesisrgb.models.Pixel;
import py.com.tesisrgb.models.PixelWeight;
import py.com.tesisrgb.models.TesisComparator;

public class TesisRGBMedianaAdaptativaCopia {
	
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(TesisRGBMedianaAdaptativa.class);
	public int p,q,m,n,c,r,i,j,smax;
	public int fila, columna, limiteFila, limiteColumna;
	public PixelWeight x;
	public PixelWeight zmed;
	public PixelWeight zmin;
	public PixelWeight elementoP;
	public PixelWeight zminAuxiliar;
	public PixelWeight zmaxAuxiliar;
	public PixelWeight zmedAuxiliar;
	public PixelWeight zmax;
	public int centrox;
	public int centroy;
	public PixelWeight zxy;
	public Double A1;
	public Double A2;
	public Double B1;
	public Double B2;
	public PixelWeight salida;
	private List<PixelWeight> subListaOrderPixelWeight;
	private List<PixelWeight> ordenadoOrderPixelWeight;
	private List<PixelWeight> descartarGuardado;
	public ColorProcessor restoredColProcessor;

	
	
	public TesisRGBMedianaAdaptativaCopia(){}
	
	public List<PixelWeight> medianaAdaptativa(List<PixelWeight> orderPixelWeight, 
			ColorProcessor restoredColProcessor, Pixel[] seEight, int width, int height){	

		System.out.println("Entrando en medianaAdaptativa");
		
			
		smax=3;
		//para k=3
		int x,y;
		
		subListaOrderPixelWeight = new ArrayList<PixelWeight>();
		for (PixelWeight recorrerLista : orderPixelWeight){
			subListaOrderPixelWeight = new ArrayList<PixelWeight>();
			zxy = recorrerLista;
			zmin=null;
			for (Pixel sePixel : seEight) {
	            x = recorrerLista.getPosicionX() + sePixel.getX();
	            y = recorrerLista.getPosicionY() + sePixel.getY();
	            //verificamos si esta en la ventana del elemento estructurante
	            if (x > -1 && x < width && y > -1 && y < height) {
	            	elementoP = obtenerElementoLista(orderPixelWeight,x,y);
	            	subListaOrderPixelWeight.add(elementoP);
	              if(zmin==null){
	            	zmin = elementoP;
	            	zmax = elementoP;
	              }else{
	            	  zminAuxiliar = elementoP;
	            	  if (zminAuxiliar.getWeight() < zmin.getWeight())
	            		  zmin = zminAuxiliar;
	            	  zmaxAuxiliar=elementoP;
	            	  if (zmaxAuxiliar.getWeight() > zmax.getWeight())
	            		  zmax = zmaxAuxiliar;
	              }
	            }
	        }//TERMINA LOS 8 VECINOS POR PIXEL
			
			TesisComparator comparator = new TesisComparator(3);
	        //ordenamos por peso
	        Collections.sort(subListaOrderPixelWeight, comparator);
	        System.out.println(subListaOrderPixelWeight.toString());
	        int element = (int) Math.ceil(subListaOrderPixelWeight.size() / 2);
		    zmed = subListaOrderPixelWeight.get(element);
		       			
		    A1=zmed.getWeight()-zmin.getWeight();    
            A2=zmed.getWeight()-zmax.getWeight();
            
            subListaOrderPixelWeight=null;
        
            if(A1>0 && A2<0){
            	B1=zxy.getWeight()-zmin.getWeight();    
                B2=zxy.getWeight()-zmax.getWeight();
                
                if(B1>0 && B2<0){
                	System.out.println("PUT ZXY");
                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
                }else{
                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zmed.getPixel());
                	System.out.println("PUT ZMED");
                }
            }else{
            	logger.info("aumentar tamanho ventana");
            	System.out.println("aumentar tamanho ventana");
            }
            	
		
		}//primer for
		
		System.out.println("Saliendo en medianaAdaptativa");
		
		return   orderPixelWeight;
}
	
	
	public PixelWeight obtenerElementoLista(List<PixelWeight> orderPixelWeight, int x, int y){
		
		
		for (PixelWeight recorrerLista : orderPixelWeight){
			if (recorrerLista.getPosicionX()==x && recorrerLista.getPosicionY()==y){
				return recorrerLista;
			}	
		}
		return null;
	}
		
}
	