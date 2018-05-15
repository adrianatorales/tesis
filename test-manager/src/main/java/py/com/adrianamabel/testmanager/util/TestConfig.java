/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.adrianamabel.testmanager.util;

import py.com.adrianamabel.testmanager.util.TestConstants.Ruidos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static py.com.adrianamabel.testmanager.util.TestConstants.Filters.TesisRGB.ConVentanas.TESIS_RGB_MODE2;


public class TestConfig {

    public boolean GUARDAR_IMAGENES = true;

    public int[] LISTA_VENTANAS = {1};
    
    public Class[] RUIDOS = {
    		//Ruidos.Gaussian.class,
            //Ruidos.Speckle.class
            Ruidos.Impulsive.class
            //Ruidos.Poisson.class,
            //Ruidos.Salt.class,
            //Ruidos.Pepper.class
    };
    
    public double PROBABILIDAD_RUIDO_FROM = 0.245;
    public double PROBABILIDAD_RUDO_TO = 0.245;
    public double PROBABILIDAD_RUIDO_STEP = 0.245;
    public int PROBABILIDAD_RUIDO_CANT = (int)(PROBABILIDAD_RUDO_TO/PROBABILIDAD_RUIDO_STEP);
    
    public int CANTIDAD_HILOS_TESTS = 10;
    
    public int[][] ORDERS_RGB = {
        {0, 1, 2},
        {0, 2, 1},
        {1, 0, 2},
        {1, 2, 0},
        {2, 0, 1},
        {2, 1, 0}
    };
    
  

    public int INDEX_IMAGENES_FROM = 99;
    public int INDEX_IMAGENES_TO = 99;
    
    public String BASE_PATH = "C:/Users/Silvia Torales/Documents/TESIS/images/";
    public String PATH_ORIGINAL_IMAGE = BASE_PATH + "test";
    public String NOISY_PATH_SUFFIX = "/noisy";
    public String RESTORED_PATH_SUFFIX = "/restored";
    public String EXTENSION = "jpg";
    
    public int ALPHA = 10;
            
    public int N_SE_FROM = 3;
    public int N_SE_TO = 3;
    public int N_SE_STEP = 2;
    
    //se puede inicializar filtros aqui indicando los nombres de los filtros (clase TestConstants)
    //List<String> FILTROS = Arrays.asList(TESIS_RGB_ENTROPY, TESIS_RGB_MEAN, TESIS_RGB_S_DEVIATION);
    List<String> FILTROS = null;
    
    public String NOMBRE_ARCHIVO_LOG = "ProbandoModa2";
    
    //se ignorara una corrida si esta en filtrosPasados Y en ruidosPasados Y en indexPasados
    public List<String> filtrosPasados = Arrays.asList();
    public List<String> ruidosPasados = Arrays.asList();
    public List<Integer> indexPasados = Arrays.asList();
    
    //matices de referencia
    public double[] REF_HUES = {0, 90, 180, 270};
    
    //alpha para hsi alpha lex (del 0 al 1)
    public double ALPHA_HSI = 10;
    
    public TestConfig() throws IllegalArgumentException, IllegalAccessException{
        
        if(FILTROS == null){
            List<String> nombresFiltros = new ArrayList<>();
            nombresFiltros.add(TESIS_RGB_MODE2);
            //nombresFiltros.add(TESIS_RGB_MODE2_WW);
            FILTROS = nombresFiltros;
        }
    }





}