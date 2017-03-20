package py.com.tesisrgb.impl;

import java.util.List;

import py.com.tesisrgb.models.PixelWeight;

public class TesisRGBMedianaAdaptativa {
	
	
	public int p,q,m,n,c,r,i,j,smax;
	public int fila, columna, limiteFila, limiteColumna;
	public int [][]x;
	public int zmed;
	public int zmin;
	public int zmax;
	public int centrox;
	public int centroy;
	public int zxy;
	public int A1;
	public int A2;
	public int B1;
	public int B2;
	public int salida;

	
	
	public TesisRGBMedianaAdaptativa(){}
	
	public int[][] medianaAdaptativa(int[][] matrizOrdenadaEscalar)
	{
			
		p=matrizOrdenadaEscalar.length; //obtenemos numero de fila
		q=matrizOrdenadaEscalar[0].length; //obtenemos el numero de columna
		
		m=3;
		n=3;

		c=q-n+1;
		r=p-m+1;
		i=0;
		j=0;
		
		smax=3;
		

		while (i<r && m<=smax && n<=smax){
		       while (j<=c && m<=smax && n<=smax && i<r){ 
				
		           // x=f(i:(i+m-1),j:(j+n-1)); //ABAJO TRADUCCION SUBMATRIZ
		    	   
		    	    x=null;
		    	    x=new int[m][n];
		    	    limiteFila=i+m-1;
		    	    limiteColumna=j+n-1;
		    	    
		    	    int xfil=0, xcol=0;
		    	    
		    	    //OBTENEMOS LA SUBMATRIZ
		            for (fila=i; fila<= limiteFila;fila++){
		            	for (columna=j; columna<= limiteColumna;columna++){
		            		 x[xfil][xcol]=matrizOrdenadaEscalar[fila][columna];
		            		 xcol++;
		            		
		            	}
		            	xfil++;
		            	xcol=0;
		            
		            }
		            
		           
		            
		            zmed =median(x,m,n); //obtener mediana
		            
		            zmin=min(x,m,n); //minimo valor
		           
		            zmax=max(x,m,n); //maximo valor
		           
		            centrox=(int) (Math.ceil(m/2)+1)-1;
		            centroy=(int) (Math.ceil(m/2)+1)-1;
		            
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
		                        if(j==c-1){
		                            i=i+1;
		                            j=0;
		                           
		                        }else{
		                             j=j+1;
		                        }
		                       
		                       
		                    }else{
		                        salida=zmed;
		                        centrox=(int)Math.ceil((i+(i+m-1))/2);
		                        centroy=(int)Math.ceil((j+(j+n-1))/2);
		                        matrizOrdenadaEscalar[centrox][centroy]=salida;
		                        if(j==c-1){
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
		
		
		for (int imp=0;imp<5;imp++){
			for(int jmp=0;jmp<5;jmp++){
				System.out.print(matrizOrdenadaEscalar[imp][jmp] + " ");
			}
		
			System.out.print("\n");
		}
		    
		return matrizOrdenadaEscalar ;

	}
		
    
		public int median(int [][] subMatriz, int m, int n) {
	       int [] vector=new int [m*n];
	       
	       int k=0;
	       for (int l=0; l< m;l++){
	            	for (int g=0; g< n;g++){
	            		vector[k]=subMatriz[l][g];
	            		 k++;
	            	}
	            	
	       }
	       
	       int element = (int) Math.ceil(vector.length / 2);
	        return vector[element];
	       
	    }
		
		
		public int min(int [][] subMatriz, int m, int n) {
	        int element = 0;
	        int [] vector=new int [m*n];
		       
		       int k=0;
		       for (int l=0; l< m;l++){
		            	for (int g=0; g< n;g++){
		            		vector[k]=subMatriz[l][g];
		            		 k++;
		            	}
		            	
		       }
	        return vector[element];
	    }
		
		
		public int max(int [][] subMatriz, int m, int n) {
	        int element = m*n - 1;
	        int [] vector=new int [m*n];
		       
		       int k=0;
		       for (int l=0; l< m;l++){
		            	for (int g=0; g< n;g++){
		            		vector[k]=subMatriz[l][g];
		            		 k++;
		            	}
		            	
		       }
	        return vector[element];
	    }

		
}

		       
	

