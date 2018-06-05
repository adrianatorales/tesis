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
	private List<int[]> subLista;
	
	
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
	                	logger.info("RESTAURA ZXY EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
	            		logger.info("RESTAURA ZXY: "+zxy.getPixel()[0]+","+zxy.getPixel()[1]+","+zxy.getPixel()[2]);
	                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
	                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
	                	
	                }else{
	                	logger.info("RESTAURA ZMED EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
	            		logger.info("RESTAURA ZMED: "+zmed.getPixel()[0]+","+zmed.getPixel()[1]+","+zmed.getPixel()[2]);
	                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zmed.getPixel());
	                	
	                }
	            }else{
	            	if(zxy.getWeight()>zmin.getWeight() && zmax.getWeight()>zxy.getWeight()){
	            		logger.info("RESTAURA ZXY EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
	            		logger.info("RESTAURA ZXY: "+zxy.getPixel()[0]+","+zxy.getPixel()[1]+","+zxy.getPixel()[2]);
	                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
	            	}else{
	            	
	            		//LA MEDIANA ES UN RUIDO SE HALLA P1 P2 P3 P4
	            		int xxx,yyy;
	            		logger.info("PIXEL ZXY EN CUESTION: "+zxy.getPixel()[0]+","+zxy.getPixel()[1]+","+zxy.getPixel()[2]);
	            		
	            		
	            		System.out.println("zmed. "+zmed.toString());
	            		System.out.println("zxy. "+zxy.toString());
	            		System.out.println("zmin. "+zmin.toString());
	            		System.out.println("zmax. "+zmax.toString());
	            		
	            		yyy=zxy.getPosicionY();
	            		xxx=zxy.getPosicionX();
	            		
	            		logger.info("xxx. "+xxx);
	            		logger.info("yyy. "+yyy);
	            		int[] P1 = null;
	            		int[] P2 = null;
	            		int[] P3 = null;
	            		int[] P4 = null;
	            		
	            		subLista = null;
	    			    subLista = new ArrayList<int[]>();
	            		
	            		if(yyy-1>=0 && xxx-1>=0){
	            		
	            			logger.info("obtengo P1: "+(xxx-1)+","+ (yyy-1));
	            			P1 = restoredColProcessor.getPixel(xxx-1, yyy-1, null);
	            			logger.info("obtengo P1: "+P1[0]+","+P1[1]+","+P1[2] );
	            			subLista.add(P1);
	            		}
	            		if(yyy-1>=0){
	            			
	            			logger.info("obtengo P2: "+(xxx)+","+ (yyy-1));
	            			P2 = restoredColProcessor.getPixel(xxx, yyy-1, null);
	            			logger.info("obtengo P2: "+P2[0]+","+P2[1]+","+P2[2] );
	            			subLista.add(P2);
	            		}
	            		if(xxx-1>=0){
	            		
	            			logger.info("obtengo P3: "+(xxx-1)+","+ yyy);
	            			P3 = restoredColProcessor.getPixel(xxx-1, yyy, null);
	            			logger.info("obtengo P3: "+P3[0]+","+P3[1]+","+P3[2] );
	            			subLista.add(P3);
	            			
	            			if(yyy+1<=height){
	            				
	            				logger.info("obtengo P4: "+(xxx-1)+","+ (yyy+1));
	            				P4 = restoredColProcessor.getPixel(xxx-1, yyy+1, null);
	            				logger.info("obtengo P4: "+P4[0]+","+P4[1]+","+P4[2] );
	            				subLista.add(P4);
	            			}
	            		}	
	            		
	            		
	            		if(!subLista.isEmpty()){
	            			if(subLista.size()==1){
	            				logger.info("RESTAURA PUT UN ELEMENTO EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
				            	logger.info("PUT ZMED UN ELEMENTO "+ subLista.get(0)[0]+","+ subLista.get(0)[1]+","+ subLista.get(0)[2] );
				            	
	            				restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), subLista.get(0));
	            			}else{
	            				
	            				int elementMediana = (int) Math.ceil(subLista.size() / 2);
	            				logger.info("RESTAURA PUT ZMED ELEMENTO EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
	            				logger.info("PUT ZMED POSICION: "+ elementMediana);
	            				logger.info("PUT ZMED ELEMENTO: "+ subLista.get(elementMediana)[0]+","+ subLista.get(elementMediana)[1]+ ","+ subLista.get(elementMediana)[2] );
	            				
	            				restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), subLista.get(elementMediana));
	            			}
	            		}else{
	            				
			            	logger.info("RESTAURA PixelRGBzxy EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
		            		logger.info("RESTAURA PixelRGBzxy: "+zxy.getPixel()[0]+","+zxy.getPixel()[1]+","+zxy.getPixel()[2]);
		                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
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
	