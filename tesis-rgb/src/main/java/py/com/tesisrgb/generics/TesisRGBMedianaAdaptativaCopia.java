package py.com.tesisrgb.generics;

import ij.process.ColorProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;

import py.com.tesisrgb.models.Pixel;
import py.com.tesisrgb.models.PixelWeight;
import py.com.tesisrgb.models.TesisComparator;

public class TesisRGBMedianaAdaptativaCopia {
	
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(TesisRGBMedianaAdaptativaCopia.class);
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
		boolean aumentarVentana=true;
		
		
		//while (aumentarVentana && smax<4){
			logger.info("Comenzando de nuevo");
			aumentarVentana=false;
			subListaOrderPixelWeight = new ArrayList<PixelWeight>();
			int posicion=0;
			Iterator<PixelWeight> it=orderPixelWeight.iterator();
			while (it.hasNext()){
			//for (PixelWeight recorrerLista : orderPixelWeight){
				PixelWeight recorrerLista=it.next();
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
			       			    
	            subListaOrderPixelWeight=null;
	        
	            if(zmed.getWeight()>zmin.getWeight() && zmax.getWeight()>zmed.getWeight()){
	                if(zxy.getWeight()>zmin.getWeight() && zmax.getWeight()>zxy.getWeight()){
	                	System.out.println("PUT ZXY");
	                	logger.info("PUT ZXY");
	                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
	                	
	                }else{
	                	System.out.println("PUT ZMED");
	                	logger.info("PUT ZMED");
	                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zmed.getPixel());
	                	
	                }
	            }else{
	            	if(zxy.getWeight()>zmin.getWeight() && zmax.getWeight()>zxy.getWeight()){
	                	System.out.println("PUT ZXY2");
	                	logger.info("PUT ZXY2");
	                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
	            	}else{
	            	
	            		//LA MEDIANA ES UN RUIDO SE HALLA P1 P2 P3 P4
	            		int xxx,yyy;
	            		
	            		System.out.println("zmed. "+zmed.toString());
	            		System.out.println("zxy. "+zxy.toString());
	            		System.out.println("zmin. "+zmin.toString());
	            		System.out.println("zmax. "+zmax.toString());
	            		
	            		yyy=zxy.getPosicionY();
	            		xxx=zxy.getPosicionX();
	            		
	            		System.out.println("xxx. "+xxx);
	            		System.out.println("yyy. "+xxx);
	            		int[] P1 = null;
	            		int[] P2 = null;
	            		int[] P3 = null;
	            		int[] P4 = null;
	            		int Rojo=0 , Verde=0, Azul=0;
	            		int suma=0;
	            		
	            		int[] PixelRGB = null;
	            		
	            		if(yyy-1>0 && xxx-1>0){
	            			P1 = restoredColProcessor.getPixel(xxx-1, yyy-1, null);
	            			suma++;
	            		}
	            		if(yyy-1>0){
	            			P2 = restoredColProcessor.getPixel(xxx, yyy-1, null);
	            			suma++;
	            		}
	            		if(xxx-1>0){
	            			P3 = restoredColProcessor.getPixel(xxx-1, yyy, null);
	            			suma++;
	            			
	            			if(yyy+1<=height){
	            				P4 = restoredColProcessor.getPixel(xxx-1, yyy+1, null);
	            				suma++;
	            			}
	            		}	
	            		
	            		if(P1!=null){
	            			Rojo = P1[0];
	            			Verde = P1[1];
	            			Azul = P1[2];
	            		}
	            		
	            		if(P2!=null){
	            			Rojo = Rojo + P2[0];
	            			Verde = Verde + P2[1];
	            			Azul = Azul + P2[2];
	            		}
	            		
	            		if(P3!=null){
	            			Rojo = Rojo + P3[0];
	            			Verde = Verde + P3[1];
	            			Azul = Azul + P3[2];
	            		}
	            		
	            		if(P4!=null){
	            			Rojo = Rojo + P4[0];
	            			Verde = Verde + P4[1];
	            			Azul = Azul + P4[2];
	            		}
	            		
	            		System.out.println("suma. "+suma);
	            		
	            		if(suma>0){
		            		Rojo = (int) Math.ceil(Rojo/suma);
		            		Verde = (int) Math.ceil(Verde/suma);
		            		Azul = (int) Math.ceil(Azul/suma);
		            		
		            		PixelRGB = new int[3];
		            		PixelRGB[0] = Rojo;
		            		PixelRGB[1] = Verde;
		            		PixelRGB[2] = Azul;
		            		
		            		
			            	logger.info("PUT PixelRGB");
			            	System.out.println("PUT PixelRGB");
			            	
			            	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), PixelRGB);
		            		
	            		}else{
	            			logger.info("PUT PixelRGBzxy");
			            	System.out.println("PUT PixelRGBzxy");
			            	
			            	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
	            		}
	            			
	            		
	            		
	            		
		            	
	            	}
		            	
	            }
	            
	            posicion++;
			
			}//primer for
	//	}//while
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
	