package py.com.tesisrgb.generics;

import java.util.ArrayList;
import java.util.List;


import py.com.tesisrgb.models.PixelWeight;

public class Cola {
	private int fin;
	public List<PixelWeight> procesados;
	
	public Cola() {
		super();
		procesados = new ArrayList<PixelWeight>();
		fin = 0;
	}

	public boolean colaVacia () {
		if (procesados.size()==0) {
			return true;
		}else{
			return false;
		}
	}

	public void encolar ( PixelWeight o ) {
		if(procesados.size()==0)
			procesados.add(fin, o);
		else{
			fin=(procesados.size()-1)+1;
			procesados.add(fin, o);
		}
		
		/*if(procesados.size()>4){
			desencolar();
		}*/
		
	}

	public void desencolar () {

		PixelWeight retorno;

		try {

			if(colaVacia())
				throw new Exception();

			else {
		
				retorno = procesados.get(0);
				procesados.remove(0);
				fin--;
				//return retorno;
			}
		} catch(Exception ex) {
	
				System.out.println("ERROR: la cola esta vacía");
	
				//return null;

		}
	}

	public List<PixelWeight> obtenerCola(){
		return procesados;
	}
	
	public int getSize() {
		return (fin);
	}
	
}
