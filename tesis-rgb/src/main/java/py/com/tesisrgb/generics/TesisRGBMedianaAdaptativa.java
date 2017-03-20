package py.com.tesisrgb.generics;

import java.util.List;

import py.com.tesisrgb.models.PixelWeight;

public class TesisRGBMedianaAdaptativa {
	
	
	public int p,q,m,n,c,r,i,j,smax;
	public int fila, columna, limiteFila, limiteColumna;
	public int [][]x;
	public PixelWeight zmed;
	public PixelWeight zmin;
	public PixelWeight zmax;
	public int centrox;
	public int centroy;
	public int zxy;
	public int A1;
	public int A2;
	public int B1;
	public int B2;
	public int salida;

	
	
	public TesisRGBMedianaAdaptativa(){}
	
	public int[][] medianaAdaptativa(List<PixelWeight> orderPixelWeight)
	{
			
		int mayorx=0;
		int mayory=0;
		
		for (PixelWeight recorrerLista : orderPixelWeight){
			if (recorrerLista.getPosicionX()>mayorx)
				mayorx=recorrerLista.getPosicionX();
			
			if (recorrerLista.getPosicionY()>mayory)
				mayory=recorrerLista.getPosicionY();
			
		}
		p=mayorx+1; //obtenemos numero de fila
		q=mayory+1; //obtenemos el numero de columna
		
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
		    	    x=new int[m][n];
		    	    limiteFila=i+m-1;
		    	    limiteColumna=j+n-1;
		    	    
		    	    int xfil=0, xcol=0;
		    	    
		    	    
		    	    //Obtenemos una SubLista
		    	    List<PixelWeight> SubListaOrderPixelWeight=null;
		            for (fila=i; i< limiteFila;i++){
		            	for (columna=j; j< limiteColumna;j++){	
		            		SubListaOrderPixelWeight.add(obtenerElementoLista(orderPixelWeight,fila,columna));
		            	}
		            }
		            
		           
		            
		            zmed =median(SubListaOrderPixelWeight,m,n); //obtener peso mediana
		            
		            zmin=min(x,m,n); //minimo valor
		           
		            zmax=max(x,m,n); //maximo valor
		           
		            centrox=(int) Math.ceil(m/2)+1;
		            centroy=(int) Math.ceil(m/2)+1;
		            
		            zxy=x[centrox][centroy];
		          
		            A1=zmed-zmin;
		           
		            A2=zmed-zmax;
		            
		            
		            if (A1>0){
		                if(A2<0){
		                    B1=zxy-zmin;
		                    B2=zxy-zmax;
		                   
		                    if(B1>0 && B2<0){
		                        centrox=(int)Math.ceil((i+(i+m-1))/2);
		                        centroy=(int)Math.ceil((j+(j+n-1))/2);
		                        salida=zxy;
		                        matrizOrdenadaEscalar[centrox][centroy]=salida;
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
		                        matrizOrdenadaEscalar[centrox][centroy]=salida;
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
		    
		return matrizOrdenadaEscalar ;

	}
		
    
		public PixelWeight median( List<PixelWeight> SubListaOrderPixelWeight , int m, int n) {
	       
	       List<PixelWeight>subListaOrdenada= ordenarLista (SubListaOrderPixelWeight);
	       int element = (int) Math.ceil(subListaOrdenada.size() / 2);
	       
	       return subListaOrdenada.get(element-1);
	       
	    }
		
		
		
		public PixelWeight obtenerElementoLista(List<PixelWeight> orderPixelWeight, int fila, int columna){
			
			
			for (PixelWeight recorrerLista : orderPixelWeight){
				if (recorrerLista.getPosicionX()==fila && recorrerLista.getPosicionY()==columna){
					return recorrerLista;
				}	
			}
			return null;
		}

		public List<PixelWeight> ordenarLista (List<PixelWeight> orderPixelWeight){
			List<PixelWeight> OrdenadoOrderPixelWeight=null;
			
			PixelWeight menorPeso;
			menorPeso=orderPixelWeight.get(0);
			
			
			while (OrdenadoOrderPixelWeight.size()!=orderPixelWeight.size()){
				menorPeso=obtenerMenorPeso(OrdenadoOrderPixelWeight,orderPixelWeight);
				OrdenadoOrderPixelWeight.add(menorPeso);
			}
			return OrdenadoOrderPixelWeight;
		}
		
		/***Obtiene el menor continuo. Recibe
		 * la ordenado que ya contiene y
		 * la lista completa desordenada***/
		
		public PixelWeight obtenerMenorPeso (List<PixelWeight> OrdenadoOrderPixelWeight, List<PixelWeight> orderPixelWeight){
			
			List<PixelWeight> descartarGuardado = null;
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
		//
		
		public int min(int [][] subMatriz, int m, int n) {
	        int element = 0;
	        int [] vector=new int [m*n];
		       
		       int k=0;
		       for (int l=0; i< m;i++){
		            	for (int g=0; j< n;j++){
		            		vector[k]=subMatriz[l][g];
		            		 k++;
		            	}
		            	
		       }
	        return vector[element];
	    }
		
		
		public int max(int [][] subMatriz, int m, int n) {
	        int element = subMatriz.length - 1;
	        int [] vector=new int [m*n];
		       
		       int k=0;
		       for (int l=0; i< m;i++){
		            	for (int g=0; j< n;j++){
		            		vector[k]=subMatriz[l][g];
		            		 k++;
		            	}
		            	
		       }
	        return vector[element];
	    }
		
}
