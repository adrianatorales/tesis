package py.com.tesisrgb.generics;

import ij.process.ColorProcessor;

import java.util.ArrayList;
import java.util.List;

import py.com.tesisrgb.models.PixelWeight;

public class TesisRGBMedianaAdaptativa {
	
	
	public int p,q,m,n,c,r,i,j,smax;
	public int fila, columna, limiteFila, limiteColumna;
	public PixelWeight x;
	public PixelWeight zmed;
	public PixelWeight zmin;
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

	
	
	public TesisRGBMedianaAdaptativa(){}
	
	public  List<PixelWeight> medianaAdaptativa(List<PixelWeight> orderPixelWeight, ColorProcessor restoredColProcessor)
	{
			
		int mayorx=0; //COLUMNA MAYOR
		int mayory=0; //FILA MAYOR
		int index=0;
		
		for (PixelWeight recorrerLista : orderPixelWeight){
			if (recorrerLista.getPosicionX()>mayorx)
				mayorx=recorrerLista.getPosicionX();
			
			if (recorrerLista.getPosicionY()>mayory)
				mayory=recorrerLista.getPosicionY();
			
		}
		p=mayory+1; //obtenemos numero de fila
		q=mayorx+1; //obtenemos el numero de columna
		
		/***ventana que va a crecer hasta 7***/
		m=3;
		n=3;

		
		c=q-n+1;
		r=p-m+1;
		
		i=0;
		j=0;
		
		/***ventana maxima****/
		smax=7;
		

		while (i<=r && m<=smax && n<=smax){
		       while (j<=c && m<=smax && n<=smax && i<=r){ 
				
		           // x=f(i:(i+m-1),j:(j+n-1)); //ABAJO TRADUCCION SUBMATRIZ
		    	   
		    	    x=null;
		    	    //x=new int[m][n];
		    	    limiteFila=i+m-1;
		    	    limiteColumna=j+n-1;
		    	    
		    	    subListaOrderPixelWeight = new ArrayList<PixelWeight>();
		            for (i=0; i<= limiteFila;i++){
		            	for (j=0; j<= limiteColumna;j++){	
		            		subListaOrderPixelWeight.add(obtenerElementoLista(orderPixelWeight,i,j));
		            	}
		            }
		            
		           
		            
		            zmed =median(subListaOrderPixelWeight,m,n); //obtener peso mediana
		            
		            zmin=min(subListaOrderPixelWeight,m,n); //minimo valor
		           
		            zmax=max(subListaOrderPixelWeight,m,n); //maximo valor
		           
		            centrox=(int) Math.ceil(m/2)+1;
		            centroy=(int) Math.ceil(m/2)+1;
		            
		            centrox=centrox-1;
		            centroy=centroy-1;
		                		
		            for (PixelWeight centro: subListaOrderPixelWeight){  
				       if(centro.getPosicionX()==centroy && centro.getPosicionY()==centrox){
				    	   zxy=centro;
				    	   break;
				       }
				     
		            }		
		            		
		            		
		          
		            A1=zmed.getWeight()-zmin.getWeight();
		           
		            A2=zmed.getWeight()-zmax.getWeight();
		            
		            
		            if (A1>0){
		                if(A2<0){
		                    B1=zxy.getWeight()-zmin.getWeight();
		                    B2=zxy.getWeight()-zmax.getWeight();
		                   
		                    if(B1>0 && B2<0){
		                        centrox=(int)Math.ceil((i+(i+m-1))/2);
		                        centroy=(int)Math.ceil((j+(j+n-1))/2);
		                        salida=zxy;
		                        index=0;
		                        for (PixelWeight centro: orderPixelWeight){ 
		     				       if(centro.getPosicionX()==centrox && centro.getPosicionY()==centroy){
		     				    	   centro=salida;
		     				    	   //orderPixelWeight.set(index, centro);
		     				    	   restoredColProcessor.putPixel(centrox, centroy, centro.getPixel());
		     				    	   break;
		     				       }
		     				       index++;
		     		            }
		                        //matrizOrdenadaEscalar[centrox][centroy]=salida;
		                        
		                        if(j==c){
		                            i=i+1;
		                            j=1;
		                           
		                        }else{
		                             j=j+1;
		                        }
		                       
		                       
		                    }else{
		                        salida=zmed;
		                        centrox=(int)Math.ceil((i+(i+m-1))/2);
		                        centroy=(int)Math.ceil((j+(j+n-1))/2);
		                        //matrizOrdenadaEscalar[centrox][centroy]=salida;
		                        index=0;
		                        for (PixelWeight centro: orderPixelWeight){ 
		     				       if(centro.getPosicionX()==centrox && centro.getPosicionY()==centroy){
		     				    	   centro=salida;
		     				    	   //orderPixelWeight.set(index, centro);
		     				    	   restoredColProcessor.putPixel(centrox, centroy, centro.getPixel());
		     				    	   break;
		     				       }
		     				       index++;
		     		            }
		                        if(j==c){
		                            i=i+1;
		                            j=1;
		                            
		                        }else{
		                             j=j+1;
		                        }
		                    }
		                }
		            }else{
		                    m=m+2;
		                    n=n+2;
		                    
		                    c=q-n+1;
		                    r=p-m+1;
		            }
		           
		       
		       }
		}
		    
		return orderPixelWeight ;

	}
		
		/****Funcion que retorna la mediana de la lista*******/
		public PixelWeight median( List<PixelWeight> SubListaOrderPixelWeight , int m, int n) {
	       
	       List<PixelWeight>subListaOrdenada= ordenarLista (SubListaOrderPixelWeight);
	       int element = (int) Math.ceil(subListaOrdenada.size() / 2);
	       
	       return subListaOrdenada.get(element);
	       
	    }
		
		/****Funcion obtiene el elemento de la fila y la columna
		 * que se le pasa*******/
		public PixelWeight obtenerElementoLista(List<PixelWeight> orderPixelWeight, int fila, int columna){
			
			
			for (PixelWeight recorrerLista : orderPixelWeight){
				if (recorrerLista.getPosicionX()==columna && recorrerLista.getPosicionY()==fila){
					return recorrerLista;
				}	
			}
			return null;
		}

		/****Funcion que ordena la lista de menor a mayor*******/
		public List<PixelWeight> ordenarLista (List<PixelWeight> orderPixelWeight){
			ordenadoOrderPixelWeight = null;
			
			ordenadoOrderPixelWeight = new ArrayList<PixelWeight>();
			
			PixelWeight menorPeso;
			menorPeso=orderPixelWeight.get(0);
			
			
			while (ordenadoOrderPixelWeight.size()!=orderPixelWeight.size()){
				menorPeso=obtenerMenorPeso(ordenadoOrderPixelWeight,orderPixelWeight);
				ordenadoOrderPixelWeight.add(menorPeso);
			}
			return ordenadoOrderPixelWeight;
		}
		
		/***Obtiene el menor continuo. Recibe
		 * la lista ordenada que ya contiene y
		 * la lista completa desordenada***/
		
		public PixelWeight obtenerMenorPeso (List<PixelWeight> OrdenadoOrderPixelWeight, List<PixelWeight> orderPixelWeight){
			
			descartarGuardado = null;
			
			descartarGuardado = new ArrayList<PixelWeight>();
			
			PixelWeight menor;
			
			if(OrdenadoOrderPixelWeight!=null && OrdenadoOrderPixelWeight.size()>0){
				for (PixelWeight recorrerLista : orderPixelWeight){
					if (!hayEnLista(OrdenadoOrderPixelWeight, recorrerLista)){
						descartarGuardado.add(recorrerLista);
					}
				}
				menor= descartarGuardado.get(0);
				
			}else{	
				menor= orderPixelWeight.get(0);
				descartarGuardado=orderPixelWeight;
			}	
			
			for (PixelWeight recorrerListaGuardado : descartarGuardado){
				if (recorrerListaGuardado.getWeight()<menor.getWeight()){
					menor=recorrerListaGuardado;
				}
			}
			
			return menor;
		}
		
		/***Verifica si ya hay ese pixel en la
		 * lista ordenada
		 * ****/
		
		public boolean hayEnLista (List<PixelWeight> OrdenadoOrderPixelWeight, PixelWeight buscarPixel){
			for (PixelWeight recorrerLista : OrdenadoOrderPixelWeight){
				if (recorrerLista.equals(buscarPixel))
					return true;
			}
			return false;
		}
		
		/****Funcion que retorna el menor de la lista*******/
		public PixelWeight min(List<PixelWeight> SubListaOrderPixelWeight, int m, int n) {
			List<PixelWeight>subListaOrdenada= ordenarLista (SubListaOrderPixelWeight);
			
			return subListaOrdenada.get(0);
	    }
		
		
		/****Funcion que retorna el mayor de la lista*******/
		public PixelWeight max(List<PixelWeight> SubListaOrderPixelWeight, int m, int n) {
			List<PixelWeight>subListaOrdenada= ordenarLista (SubListaOrderPixelWeight);
			
			return subListaOrdenada.get(subListaOrdenada.size()-1);
	    }
	    
		
}
