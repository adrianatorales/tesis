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
	public ColorProcessor restoredColProcessor;
	private List<PixelWeight> subLista;
	public List<PixelWeight> procesados;
	public PixelWeight medianaOn;
	
	public TesisRGBMedianaAdaptativaCopia(){}
	
	public List<PixelWeight> medianaAdaptativa(List<PixelWeight> orderPixelWeight, 
			ColorProcessor restoredColProcessor, Pixel[] seEight, int width, int height){	

		System.out.println("Entrando en medianaAdaptativa");
		procesados = new ArrayList<PixelWeight>(); 
		Cola cola = new Cola();
			
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
	                	cola.encolar(zxy);
	                	procesados = cola.obtenerCola();
	                	
	                }else{
	                	logger.info("RESTAURA ZMED EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
	            		logger.info("RESTAURA ZMED: "+zmed.getPixel()[0]+","+zmed.getPixel()[1]+","+zmed.getPixel()[2]);
	                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zmed.getPixel());
	                	
	                	medianaOn=new PixelWeight();
	                	medianaOn.setPixel(zmed.getPixel());
	                	medianaOn.setPosicionX(recorrerLista.getPosicionX());
	                	medianaOn.setPosicionY(recorrerLista.getPosicionY());
	                	medianaOn.setWeight(zmed.getWeight());
	                	
	                	cola.encolar(medianaOn);
	                	procesados = cola.obtenerCola();
	                	
	                }
	            }else{
	            	if(zxy.getWeight()>zmin.getWeight() && zmax.getWeight()>zxy.getWeight()){
	            		logger.info("RESTAURA ZXY EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
	            		logger.info("RESTAURA ZXY: "+zxy.getPixel()[0]+","+zxy.getPixel()[1]+","+zxy.getPixel()[2]);
	                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
	                	cola.encolar(zxy);
	                	procesados = cola.obtenerCola();
	            	}else{
	            	
	            		//LA MEDIANA ES UN RUIDO SE HALLA P1 P2 P3 P4
	            		int xxx,yyy;
	            		logger.info("PIXEL ZXY EN CUESTION: "+zxy.getPixel()[0]+","+zxy.getPixel()[1]+","+zxy.getPixel()[2]);
	            		
	            		procesados = cola.obtenerCola();
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
	            		
	            		PixelWeight PUNO = null;
	            		PixelWeight PDOS = null;
	            		PixelWeight PTRES = null;
	            		PixelWeight PCUATRO = null;
	            		
	            		subLista = null;
	    			    subLista = new ArrayList<PixelWeight>();
	            		
	            		if(yyy-1>=0 && xxx-1>=0){
	            		
	            			logger.info("obtengo P1: "+(xxx-1)+","+ (yyy-1));
	            			P1 = restoredColProcessor.getPixel(xxx-1, yyy-1, null);
	            			PUNO = obtenerElementoLista(procesados,xxx-1,yyy-1);
	            			logger.info("obtengo P1: "+P1[0]+","+P1[1]+","+P1[2] );
	            			logger.info("obtengo P1: "+PUNO.getPixel()[0]+","+PUNO.getPixel()[1]+","+PUNO.getPixel()[2]+" ,T: "+PUNO.getWeight());
	            			subLista.add(PUNO);
	            		}
	            		if(yyy-1>=0){
	            			
	            			logger.info("obtengo P2: "+(xxx)+","+ (yyy-1));
	            			P2 = restoredColProcessor.getPixel(xxx, yyy-1, null);
	            			PDOS = obtenerElementoLista(procesados,xxx, yyy-1);
	            			logger.info("obtengo P2: "+P2[0]+","+P2[1]+","+P2[2] );
	            			logger.info("obtengo P2: "+PDOS.getPixel()[0]+","+PDOS.getPixel()[1]+","+PDOS.getPixel()[2]+" ,T: "+PDOS.getWeight());
	            			subLista.add(PDOS);
	            		}
	            		if(xxx-1>=0){
	            		
	            			logger.info("obtengo P3: "+(xxx-1)+","+ yyy);
	            			P3 = restoredColProcessor.getPixel(xxx-1, yyy, null);
	            			PTRES = obtenerElementoLista(procesados,xxx-1, yyy);
	            			logger.info("obtengo P3: "+P3[0]+","+P3[1]+","+P3[2] );
	            			logger.info("obtengo P3: "+PTRES.getPixel()[0]+","+PTRES.getPixel()[1]+","+PTRES.getPixel()[2]+" ,T: "+PTRES.getWeight());
	            			subLista.add(PTRES);
	            			
	            			if(yyy+1<height){
	            				
	            				logger.info("obtengo P4: "+(xxx-1)+","+ (yyy+1));
	            				P4 = restoredColProcessor.getPixel(xxx-1, yyy+1, null);
	            				PCUATRO = obtenerElementoLista(procesados,xxx-1, yyy+1);
	            				logger.info("obtengo P4: "+P4[0]+","+P4[1]+","+P4[2] );
	            				logger.info("obtengo P4: "+PCUATRO.getPixel()[0]+","+PCUATRO.getPixel()[1]+","+PCUATRO.getPixel()[2]+" ,T: "+PCUATRO.getWeight());
	            				subLista.add(PCUATRO);
	            			}
	            		}	
	            		
	            		
	            		if(!subLista.isEmpty()){
	            			if(subLista.size()==1){
	            				logger.info("RESTAURA PUT UN ELEMENTO EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
				            	logger.info("PUT MEDIANA PROCESADOS UN ELEMENTO "+ subLista.get(0).getPixel()[0]+","+ subLista.get(0).getPixel()[1]+","+ subLista.get(0).getPixel()[2]+" ,T: "+subLista.get(0).getWeight() );
				            	
	            				restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), subLista.get(0).getPixel());
	            				
	            				medianaOn=new PixelWeight();
	    	                	medianaOn.setPixel(subLista.get(0).getPixel());
	    	                	medianaOn.setPosicionX(recorrerLista.getPosicionX());
	    	                	medianaOn.setPosicionY(recorrerLista.getPosicionY());
	    	                	medianaOn.setWeight(subLista.get(0).getWeight());
	    	                	
	    	                	cola.encolar(medianaOn);
	    	                	procesados = cola.obtenerCola();
	            				
	            				
	            			}else{
	            				 TesisComparator comparator2 = new TesisComparator(3);
	            				 Collections.sort(subLista, comparator2);
	            				 int medianaProcesados = (int) Math.ceil(subLista.size() / 2);
	            				 PixelWeight medianaProcesadosPixel = subLista.get(medianaProcesados);
	            				
	            				 logger.info("RESTAURA PUT MEDIANA PROCESADOS ELEMENTO EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
	            				 logger.info("PUT MEDIANA PROCESADOS POSICION: "+ medianaProcesados);
	            				 logger.info("PUT MEDIANA PROCESADOS ELEMENTO: "+ medianaProcesadosPixel.getPixel()[0]+","+ medianaProcesadosPixel.getPixel()[1]+ ","+ medianaProcesadosPixel.getPixel()[2]+" ,T: "+medianaProcesadosPixel.getWeight() );
	            				
	            				 restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), medianaProcesadosPixel.getPixel());
	            				 
	            				 medianaOn=new PixelWeight();
		    	                 medianaOn.setPixel(medianaProcesadosPixel.getPixel());
		    	                 medianaOn.setPosicionX(recorrerLista.getPosicionX());
		    	                 medianaOn.setPosicionY(recorrerLista.getPosicionY());
		    	                 medianaOn.setWeight(medianaProcesadosPixel.getWeight());
		    	                	
		    	                 cola.encolar(medianaOn);
		    	                 procesados = cola.obtenerCola();
	            				 
	            			}
	            		}else{
	            				
			            	logger.info("RESTAURA PixelRGBzxy EN LA POSICION xy: "+recorrerLista.getPosicionX()+","+recorrerLista.getPosicionY());
		            		logger.info("RESTAURA PixelRGBzxy: "+zxy.getPixel()[0]+","+zxy.getPixel()[1]+","+zxy.getPixel()[2]);
		                	restoredColProcessor.putPixel(recorrerLista.getPosicionX(), recorrerLista.getPosicionY(), zxy.getPixel());
		                	cola.encolar(zxy);
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
	