package py.com.tesisrgb.impl;

public class ProbarMedianaAdaptativa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TesisRGBMedianaAdaptativa prueba =new TesisRGBMedianaAdaptativa();
		
		int[][]  matriz = {{1, 2, 3, 4, 5},{7, 8, 9, 10, 12},{14, 15, 16, 17, 18},{19, 20, 31, 34, 35},{36, 43, 49, 56, 64 } };
		
		int i,j;
		
		for (i=0;i<5;i++){
			for(j=0;j<5;j++){
				System.out.print(matriz[i][j] + " ");
			}
		
			System.out.print("\n");
		}
				
	  prueba.medianaAdaptativa(matriz)	;
				
			
		
	}

}
