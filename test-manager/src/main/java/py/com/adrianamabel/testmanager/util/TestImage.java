package py.com.adrianamabel.testmanager.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ColorProcessor;
import py.com.adrianamabel.imagestorage.models.Resultado;
import py.com.adrianamabel.imagestorage.models.ResultadoPK;
import py.com.adrianamabel.imagestorage.models.RgbImage;
import py.com.adrianamabel.imagestorage.utils.RgbImageJpaController;
import py.com.adrianamabel.testmanager.models.Metrics;
import py.com.tesisrgb.generics.BasicFilterAbstract;
import py.com.tesisrgb.models.Pixel;




public class TestImage {
    
    static final org.slf4j.Logger logger = LoggerFactory.getLogger(TestImage.class);

    public TestImage() {
    }
    
    public void run() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException {
        try{
            String [] component = {"R", "G", "B"};
            TestConfig config = new TestConfig();
            int[] windowsRoiList = config.LISTA_VENTANAS;
            ObjectMapper mapper = new ObjectMapper();
            ExecutorService pool = Executors.newFixedThreadPool(config.CANTIDAD_HILOS_TESTS);

            //Escribimos la configuracion en un archivo json
            //linux mapper.writeValue(new File("/home/adriana/Documentos/recursos/logs/"+ config.NOMBRE_ARCHIVO_LOG + ".json"), config);
            
            
            mapper.writeValue(new File("C:/Users/Silvia Torales/Documents/TESIS/images/logs/"+ config.NOMBRE_ARCHIVO_LOG + ".json"), config);
            
            
            
            logger.info("#, probabilidad, nombre_metodo, compReducido, comp1, comp2, comp3, ventanas, combinacion, dimension_es, refHue, ruido, maeH, maeS, maeV, maeEuclidean, ncd, metricOfSimilarityM1, metricOfSimilarityM2, mae");

            //por cada imagen original
            RgbImageJpaController rgbMgr = new RgbImageJpaController();
            List<RgbImage> rgbImagetTestList = rgbMgr.getImageTest(config.INDEX_IMAGENES_FROM, config.INDEX_IMAGENES_TO);
            
            for (RgbImage rgbImagetTest : rgbImagetTestList) {
                //get the original image to test
                ColorProcessor colImgOriginal = rgbImagetTest.getColorProcessor();
                //por cada ruido
                for (Class noise : config.RUIDOS) {
                    String noiseName = "";
                    noiseName = (String) noise.getField("NAME").get(null);
                    //obtenemos que tipo de filtro se usara para este ruido ej (min, max, median)
                    String[] tipoDeFiltros = (String[]) noise.getField("ALLOWED_FILTERS").get(null);
                    String basePathNoisyImg = config.BASE_PATH + noiseName + config.NOISY_PATH_SUFFIX;
                    String pathRestoredImg = config.BASE_PATH + noiseName + config.RESTORED_PATH_SUFFIX;
                    String imgName = "img_ruido_" + noiseName;

                    for (String tipoDeFiltro : tipoDeFiltros) {
                        //por cada filtro
                        for (String nombreFiltro : config.FILTROS) {
                            //noiseName = gaussian y description = 1 (imagen 1)
                            List<RgbImage> noiseImageTestList = rgbMgr.getNoiseImageByNoise(noiseName, rgbImagetTest.getDescription());
                            //por defecto la matiz será un valor -1 y se seteará solo en caso de que el método sea HSI_LEX
                            double[] refHues = {-1};
                            //guardamos las imagenes en una carpeta propia con el nombre del metodo
                            String pathRestoredMethodImg = "";
                            if(config.GUARDAR_IMAGENES){
                                 pathRestoredMethodImg =  pathRestoredImg + "/" + nombreFiltro;
                                File file = new File(pathRestoredMethodImg);
                                if ( !file.exists() ) {
                                    if ( !file.mkdir() ){
                                        break;
                                    }else{
                                        System.out.println("Se creo correctamente la carpeta" + pathRestoredMethodImg);
                                    }
                                }else {
                                    System.out.println("Ya existe la carpeta" + pathRestoredMethodImg);
                                }
                            }
                            if(nombreFiltro.equals(TestConstants.Filters.EstadoDelArte.HSI_LEX)
                                    || nombreFiltro.equals(TestConstants.Filters.EstadoDelArte.HSI_ALPHA_LEX)){
                                refHues = config.REF_HUES;
                            }
                            
                            for(double refHue: refHues){

                                int[][] ordenesRgb = {{0, 1, 2}};
                                if(filterUsesCombination(nombreFiltro)){
                                    ordenesRgb = config.ORDERS_RGB;
                                }

                                //por cada combinacion de orden R, G, B
                                for (int[] ordenRGB : ordenesRgb) {
                                    String combinacion = component[ordenRGB[0]] + component[ordenRGB[1]] + component[ordenRGB[2]];

                                    //por cada dimension de elemento estructurante
                                    for (int j = config.N_SE_FROM; j <= config.N_SE_TO; j+=config.N_SE_STEP) {
                                        //calculamos el vector de desplazamientos segun la dimension del SE
                                    	Pixel[] desplazamientosSe = getShiftArray(j);
                                        List<Future<TaskResult>> futures = new ArrayList<Future<TaskResult>>(windowsRoiList.length);
                                        for (RgbImage noisyImageTest : noiseImageTestList) {
                                            //ColorProcessor colImgNoise = noisyImageTest.getColorProcessor();
                                            for (Integer roiWindow : windowsRoiList) {
                                            	System.out.println("Entro a listaventanas");
                                                BasicFilterAbstract filterMethod = TestAny.getFilterMethod(roiWindow, nombreFiltro, tipoDeFiltro,
                                                    noisyImageTest, desplazamientosSe);
                                                TestTask testTask = new TestTask(filterMethod, noisyImageTest.getNoiseProbability(), noisyImageTest.getDescription(), roiWindow);
                                                futures.add(pool.submit(testTask));
                                            }
                                        }

                                        for (Future<TaskResult> taskResult : futures) {
                                            try {

                                                ColorProcessor colImgNoiseRestored = taskResult.get().getColProcessor();
                                                Metrics metricas = new Metrics(colImgOriginal, colImgNoiseRestored);
                                                Resultado resultado = new Resultado();
                                                
                                                
                                                String valoresMetricas[] = metricas.toString().split(",");
                                                String valoresTaskResult[] = taskResult.get().toString().split(",");
                                                   
                                                ResultadoPK resultadoPK = new ResultadoPK();
                                                resultadoPK.setNumeroImagen(Double.parseDouble(valoresTaskResult[0].trim().toString()));
                                                resultadoPK.setProbabilidad(Double.parseDouble(valoresTaskResult[1].trim().toString()));
                                                resultadoPK.setNombreMetodo(valoresTaskResult[2].trim().toString());
                                                resultadoPK.setCombinacion(combinacion);
                                                resultadoPK.setDimensionEs(j);
                                                resultadoPK.setVentanas(valoresTaskResult[7]==null?0:Double.parseDouble(valoresTaskResult[7].trim().toString()));
                                                resultadoPK.setRefHue(refHue);
                                                resultadoPK.setRuido(noiseName);
                                                
                                                
                                                
                                                resultado.setMaeEuclidean(Double.parseDouble((valoresMetricas[3].trim().toString())));
                                                resultado.setNcd(valoresMetricas[4]==null?0:Double.parseDouble((valoresMetricas[4].trim().toString())));
                                                resultado.setMetricOfSimilarityM1(Double.parseDouble((valoresMetricas[5].trim().toString())));
                                                resultado.setMetricOfSimilarityM2(Double.parseDouble((valoresMetricas[6].trim().toString())));
                                                resultado.setMaeMarginal(Double.parseDouble((valoresMetricas[7].trim().toString())));
                                                resultado.setCompReducido(valoresTaskResult[3]==null?0:Double.parseDouble(valoresTaskResult[3].trim().toString()));
                                                resultado.setComp1(valoresTaskResult[4]==null?null:valoresTaskResult[4].trim().toString());
                                                resultado.setComp2(valoresTaskResult[5]==null?null:valoresTaskResult[5].trim().toString());
                                                resultado.setComp3(valoresTaskResult[6]==null?null:valoresTaskResult[6].trim().toString());
                                                
                                                resultado.setId(resultadoPK);
                                                
                                                RgbImageJpaController rgbImageJpaController = new RgbImageJpaController();
                                                rgbImageJpaController.createResultado(resultado);
                                                
                                                
                                                logger.info(taskResult.get().toString() + ", "  + combinacion + ", " + j +  ", " + refHue + ", " + noiseName + ", " + metricas.toString());
                                                if(config.GUARDAR_IMAGENES){
                                                    ImagePlus imgPlus = new ImagePlus(nombreFiltro, colImgNoiseRestored);
                                                    new FileSaver(imgPlus).saveAsPng(pathRestoredMethodImg + "/" + imgName + "_" + taskResult.get().toString() + ".jpg");
                                                }
                                            } catch (InterruptedException ex) {
                                            	System.out.println(ex.getMessage());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            System.out.println("Terminado");
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static List<String> getAllFiltersByType(Class clazz) throws IllegalArgumentException,
            IllegalAccessException{
        List<String> allFields = new ArrayList<>();
        recur(clazz, allFields);
        return allFields;
    }
    
    private static void recur(Class clazz, List<String> lista) 
            throws IllegalArgumentException, IllegalAccessException{
        
        Class[] clases = clazz.getClasses();
        for (Class clase : clases) {
            recur(clase, lista);
        }
        agregarCampos(clazz, lista);
    }
    
    private static void agregarCampos(Class clazz, List<String> camposValores)
            throws IllegalArgumentException, IllegalAccessException{
        
        Field[] campos = clazz.getDeclaredFields();
        for (Field field : campos) {
            camposValores.add((String) field.get(null));
        }
    }
    
    public static boolean filterHasWindows(String filterName) 
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        //obtenemos las clases de los tipos de filtros: median, min, max
        if (getAllFiltersByType(TestConstants.Filters.TesisRGB.ConVentanas.class).contains(filterName)){
            return true;
        }
        
        return false;
    }
    
    public static boolean filterUsesCombination(String filterName) 
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        //obtenemos las clases de los tipos de filtros: median, min, max
        if (getAllFiltersByType(TestConstants.Filters.EstadoDelArte.class)
                .contains(filterName)){
            return !(filterName.equals(TestConstants.Filters.EstadoDelArte.BITMIXING)
                    || filterName.equals(TestConstants.Filters.EstadoDelArte.MARGINAL)
                    || filterName.equals(TestConstants.Filters.EstadoDelArte.HSI_LEX));
        }
        return false;
    }
    
    public Pixel[] getShiftArray(int dimensionSE){
        
        int centro = dimensionSE/2;
        int indexFrom = centro * (-1);
        int indexTo = dimensionSE - (centro + 1);
        int cantidadDesplazamientos = (int)(Math.pow(dimensionSE, 2));
        Pixel[] retorno = new Pixel[cantidadDesplazamientos];
        int counter = 0;
        for (int i = indexFrom; i <= indexTo; i++) {
            for (int j = indexFrom; j <= indexTo; j++) {
//                if(i == 0 && j == 0){
//                    continue;
//                }
                retorno[counter] = new Pixel(i, j);
                counter++;
            }
        }
        
        return retorno;
    }
    
    public static List<String> getAllFilters() throws IllegalArgumentException, IllegalAccessException{
        
        Class tesisRgbConVentanas = TestConstants.Filters.TesisRGB.ConVentanas.class;
        Class tesisRgbSinVentanas = TestConstants.Filters.TesisRGB.SinVentanas.class;
        Class estadoDelArte = TestConstants.Filters.EstadoDelArte.class;
        List<String> nombreFiltros = new ArrayList<>();
        String nombreFiltro;
        List<Class<?>> filtersClass = new ArrayList<Class<?>>();

        filtersClass.add(tesisRgbConVentanas);
        filtersClass.add(tesisRgbSinVentanas);
        filtersClass.add(estadoDelArte);
        
        for(Class clases : filtersClass){
            Field[] filters = clases.getDeclaredFields();
            for (Field field : filters) {
                nombreFiltro = (String) field.get(null);
                nombreFiltros.add(nombreFiltro);
            }
        }

        return nombreFiltros;
    }
    
}