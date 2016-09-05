/*
 * Tesis Arguello Balbuena
 * Derechos Reservados 2015 - 2016
 */
package py.com.daas.testmanager.models;

import ij.process.ByteProcessor;
import ij.process.ColorProcessor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author daasalbion
 */
public class Metrics {
    
    private ColorProcessor original, result;
    private int height, width, cLength;
    private ByteProcessor [] channelsOriginal, channelsResult;
    
    double[] maeHsv = new double[3];
    double euclideanDistance;
    
    public Metrics ( ColorProcessor original, ColorProcessor result ) {
        
        this.original = original;
        this.result = result;
        this.width = original.getWidth();
        this.height = original.getHeight();
        
        cLength = original.getNChannels();
        channelsOriginal = new ByteProcessor[cLength];
        channelsResult = new ByteProcessor[cLength];
        
        for (int i = 0; i < cLength; i++) {
            //cargas los canales de la imagen por separado en channel
            channelsOriginal[i] = original.getChannel(i + 1, channelsOriginal[i]);
            channelsResult[i] = result.getChannel(i + 1, channelsResult[i]);
        }
    }
    
    public ColorProcessor getOriginal() {
        return original;
    }

    public void setOriginal(ColorProcessor original) {
        this.original = original;
    }

    public ColorProcessor getResult() {
        return result;
    }

    public void setResult(ColorProcessor result) {
        this.result = result;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    /*
        Calculo de Mean Absolute Error
    */
    public double mae() {
        
        double mae = 0;
        int absDifference, originalPixel, resultPixel;

        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                //para cada espacio de color
                for (int i = 0; i < cLength; i++) {
                    originalPixel = channelsOriginal[i].get(n, m);
                    resultPixel = channelsResult[i].get(n, m);
                    absDifference = Math.abs( originalPixel - resultPixel );
                    mae +=  absDifference;
                }
            }
        }
        
        mae = mae/(double)(height*width*cLength);
        
        return mae;
    }
    
    /*
     *  Calculo de Mean Square Error
    */
    public double mse() {
        
        double mse = 0;
        int originalPixel, resultPixel;
        double difference;

        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                //para cada espacio de color
                for (int i = 0; i < cLength; i++) {
                    originalPixel = channelsOriginal[i].get(n, m);
                    resultPixel = channelsResult[i].get(n, m);
                    difference = Math.pow( ( originalPixel - resultPixel ), 2 );
                    mse +=  difference;
                }
            }
        }
        
        mse = mse/(double)(height*width*cLength);
        
        
        return mse;
    }
    
    /*
     *  Calculo de Normalized Mean Square Error
    */
    public double nmse() {
        
        double nmse = 0;
        int originalPixel, resultPixel;
        double numeratorSum = 0;
        double denominatorSum = 0;

        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                //para cada espacio de color
                for (int i = 0; i < cLength; i++) {
                    originalPixel = channelsOriginal[i].get(n, m);
                    resultPixel = channelsResult[i].get(n, m);
                    numeratorSum += Math.pow( ( originalPixel - resultPixel ), 2 );
                    denominatorSum += Math.pow( ( originalPixel ), 2 );
                }
            }
        }
        
        nmse = (double)numeratorSum/(double)denominatorSum;
        
        return nmse;
    }
    
    public double pearsonCorrelation() {
        double pearson;
        int[] originalPixel = new int[cLength];
        int[] resultPixel = new int[cLength];
        double numeratorSum = 0;
        double[] denominatorSumOrig = new double[cLength];
        double[] denominatorSumResult = new double[cLength];
        double[] originalMean = new double[cLength];
        double[] resultMean = new double[cLength];

        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                //para cada espacio de color
                for (int i = 0; i < cLength; i++) {
                    originalPixel[i] = channelsOriginal[i].get(n, m);
                    resultPixel[i] = channelsResult[i].get(n, m);
                    originalMean[i] = originalMean[i] + originalPixel[i];
                    resultMean[i] = resultMean[i] + resultPixel[i];
                }
            }
        }
        
        for (int i = 0; i < cLength; i++) {
            originalMean[i] = originalMean[i]/(double)(height*width);
            resultMean[i] = resultMean[i]/(double)(height*width);
        }
        
        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                //para cada espacio de color
                double[] x = new double[cLength];
                double[] y = new double[cLength];
                for (int i = 0; i < cLength; i++) {
                    originalPixel[i] = channelsOriginal[i].get(n, m);
                    resultPixel[i] = channelsResult[i].get(n, m);
                    x[i] = originalPixel[i] - originalMean[i];
                    y[i] = resultPixel[i] - resultMean[i];
                    numeratorSum = numeratorSum + x[i]*y[i];
                    denominatorSumOrig[i] = denominatorSumOrig[i] + Math.pow(x[i], 2);    
                    denominatorSumResult[i] = denominatorSumResult[i] + Math.pow(x[i], 2);
                }
            }
        }
        
        double denominator = 0;
        for (int i = 0; i < cLength; i++) {
            denominatorSumOrig[i] = Math.sqrt(denominatorSumOrig[i]); 
            denominatorSumResult[i] = Math.sqrt(denominatorSumResult[i]); 
            denominator = denominator + denominatorSumOrig[i]*denominatorSumResult[i];
        }
        
        pearson = (double)numeratorSum/(double)denominator;
        return pearson;
    }
    
    public double tanimotoMeasure() {
        double resultado;
        int originalPixel, resultPixel;
        double numeratorSum = 0;
        double denominatorSum = 0;
        double euclideanDistance;

        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                euclideanDistance = 0;
                for (int i = 0; i < cLength; i++) {
                    originalPixel = channelsOriginal[i].get(n, m);
                    resultPixel = channelsResult[i].get(n, m);
                    euclideanDistance += Math.pow( ( originalPixel - resultPixel ), 2 );
                    numeratorSum += originalPixel*resultPixel;
                }
                denominatorSum += Math.sqrt(euclideanDistance);
            }
        }
        
        resultado = (double)numeratorSum/(double)(denominatorSum + numeratorSum);
        return resultado;
    }
    
    
    public double stochasticSignChange() {
        int originalPixel;
        int resultPixel;
        //int[] difPixel = new int[cLength];
        int difPixel;
        int signChangeCounter = 0;
        int[] colorAnterior = new int[cLength];

        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                for (int i = 0; i < cLength; i++) {
                    originalPixel = channelsOriginal[i].get(n, m);
                    resultPixel = channelsResult[i].get(n, m);
                    difPixel = originalPixel - resultPixel;
                    //Si su multiplicacion es negativa son de signos opuestos.
                    //La primera vez nunca se detectara como cambio de signo 
                    //porque la multiplicacion siempre dara cero.
                    if(colorAnterior[i]*difPixel < 0){
                        signChangeCounter++;
                    }
                    colorAnterior[i] = difPixel;
                }
            }
        }
        return (double)signChangeCounter;
    }
    
    public double minimumRatio() {
        double resultado = 0;
        int originalPixel;
        int resultPixel;
        
        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                for (int i = 0; i < cLength; i++) {
                    originalPixel = channelsOriginal[i].get(n, m);
                    resultPixel = channelsResult[i].get(n, m);
                    double a = (double)originalPixel/(double)resultPixel;
                    double b = (double)resultPixel/(double)originalPixel;
                    resultado += Math.min(a, b);
                }
            }
        }
        return (double)resultado/(double)(height*width*cLength);
    }
    
    
    public double energyJPD() {
        double resultado = 0;
        int originalPixel;
        int resultPixel;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                double p = 0;
                for (int f = 0; f < height; f++) {
                    for (int c = 0; c < width; c++) {
                        for (int k = 0; k < cLength; k++) {
                            originalPixel = channelsOriginal[k].get(c, f);
                            resultPixel = channelsResult[k].get(c, f);
                            if(originalPixel == i && resultPixel == j){
                                p++;
                            }
                        }
                    }
                }
                p = (double)p / (double)(height*width);
                p = Math.pow(p, 2);
                resultado += p;
            }
        }
        return resultado;
    }
    
    private void calculateMAEEuclidean(){
        double totalDistance = 0;
        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                //para cada espacio de color
                int[] rgbOriginal = new int[cLength];
                int[] rgbResult = new int[cLength];
                double euclideanDistanceCurrent = 0;
                for (int i = 0; i < cLength; i++) {
                    rgbOriginal[i] = channelsOriginal[i].get(n, m);
                    rgbResult[i] = channelsResult[i].get(n, m);
                    euclideanDistanceCurrent += Math.pow(rgbOriginal[i] - rgbResult[i], 2);
                }
                totalDistance += Math.sqrt(euclideanDistanceCurrent);
            }
        }
        this.euclideanDistance = totalDistance/(double)(height*width);
    }
    
    private double calculateOnlySimilarityMetrics(){
        //momentos: la primera dimension es el canal y la segunda es el momento
        double[][] momentosImagenOriginal = new double[3][3];
        double[][] momentosImagenRestaurada = new double[3][3];
        double cantPixels = height*width;
        //iteramos los canales
        double resultado = 0;
        for (int i = 0; i < cLength; i++) {
            //hallamos el primer momento
            for (int m = 0; m < height; m++) {
                for (int n = 0; n < width; n++) {
                    int intensityOriginal = channelsOriginal[i].get(n, m);
                    int intensityRestored = channelsResult[i].get(n, m);
                    momentosImagenOriginal[i][0] += intensityOriginal;
                    momentosImagenRestaurada[i][0] += intensityRestored;
                }
            }
            momentosImagenOriginal[i][0] = momentosImagenOriginal[i][0]/cantPixels;
            momentosImagenRestaurada[i][0] = momentosImagenRestaurada[i][0]/cantPixels;
            
            //hallamos el segundo y tercer momento
            for (int m = 0; m < height; m++) {
                for (int n = 0; n < width; n++) {
                    int intensityOriginal = channelsOriginal[i].get(n, m);
                    int intensityRestored = channelsResult[i].get(n, m);
                    momentosImagenOriginal[i][1] += Math.pow(intensityOriginal - momentosImagenOriginal[i][0], 2);
                    momentosImagenRestaurada[i][1] += Math.pow(intensityRestored - momentosImagenRestaurada[i][0], 2);
                    momentosImagenOriginal[i][2] += Math.pow(intensityOriginal - momentosImagenOriginal[i][0], 3);
                    momentosImagenRestaurada[i][2] += Math.pow(intensityRestored - momentosImagenRestaurada[i][0], 3);
                }
            }
            momentosImagenOriginal[i][1] = Math.sqrt(momentosImagenOriginal[i][1]/cantPixels);
            momentosImagenRestaurada[i][1] = Math.sqrt(momentosImagenRestaurada[i][1]/cantPixels);
            
            momentosImagenOriginal[i][2] = Math.cbrt(momentosImagenOriginal[i][2]/cantPixels);
            momentosImagenRestaurada[i][2] = Math.cbrt(momentosImagenRestaurada[i][2]/cantPixels);
            
            resultado += Math.abs(momentosImagenOriginal[i][0] - momentosImagenRestaurada[i][0]);
            resultado += Math.abs(momentosImagenOriginal[i][1] - momentosImagenRestaurada[i][1]);
            resultado += Math.abs(momentosImagenOriginal[i][2] - momentosImagenRestaurada[i][2]);
        }
        return resultado;
    }

    
    
    //nuevas metricas de desimilaridad
    /*
     *  Minkowski-form distance L_p
    */
    public double minkowskiFormDistance(double p) {
        
        double resultValue = 0, difference;
        int originalPixel, resultPixel;

        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                //para cada espacio de color
                for (int i = 0; i < cLength; i++) {
                    originalPixel = channelsOriginal[i].get(n, m);
                    resultPixel = channelsResult[i].get(n, m);
                    difference = Math.pow( ( Math.abs(originalPixel - resultPixel) ), p );
                    resultValue +=  difference;
                }
            }
        }
        
        resultValue = Math.pow(resultValue, 1/p);
        
        return resultValue;
    }
    /*
     *  Minkowski-form distance L_p
    */
    public double WeightedMeanVariance() {
        
        double resultValue = 0, difference;
        int[] meanOriginal = new int[cLength];
        int[] meanResult = new int[cLength];
        double[] deviationOriginal = new double[cLength];
        double[] deviationResult = new double[cLength];
        int numPixels = width*height;

        int cSize = cLength;
        int hSize = channelsOriginal[0].getHistogramSize();
        int[][] channelHistogramOriginal = new int[cSize][hSize];
        int[][] channelHistogramResult = new int[cSize][hSize];

        for (int channel = 0; channel < cSize; channel++) {
            channelHistogramOriginal[channel] = channelsOriginal[channel].getHistogram();
            channelHistogramResult[channel] = channelsResult[channel].getHistogram();
            
            for (int intensity = 0; intensity < hSize; intensity++) {
                //se calcula la sumatoria de intensidad por valor de histograma de esa intensidad
                meanOriginal[channel] += (intensity) * channelHistogramOriginal[channel][intensity];
                meanResult[channel] += (intensity) * channelHistogramResult[channel][intensity];
            }
            
            meanOriginal[channel] = meanOriginal[channel]/numPixels;
            meanResult[channel] = meanResult[channel]/numPixels;
            
            for (int intensity = 0; intensity < hSize; intensity++) {
                //se calcula la sumatoria de intensidad por valor de histograma de esa intensidad
                deviationOriginal[channel] += Math.pow( (intensity - meanOriginal[channel]), 2 ) * channelHistogramOriginal[channel][intensity];
                deviationResult[channel] += Math.pow( (intensity - meanResult[channel]), 2 ) * channelHistogramResult[channel][intensity];
            }
            
            deviationOriginal[channel] = Math.sqrt(deviationOriginal[channel]/numPixels);
            deviationResult[channel] = Math.sqrt(deviationResult[channel]/numPixels);
        
        }
        
        //resultValue = Math.abs(meanOriginal - meanResult)
        
        return resultValue;
    }
    
    /*
     *  Calculo de Normalize Color Difference
    */
    public double ncd() {
        
        double ncd = 0;
        double numeratorSum = 0;
        double denominatorSum = 0;
        //diferencia cuadrada
        double qdl, qdu, qdv, ql, qu, qv;
        
        for (int m = 0; m < height; m++) {
            for (int n = 0; n < width; n++) {
                
                //se convierte a xyz la imagen original
                int ro = channelsOriginal[0].get(n, m);
                int go = channelsOriginal[1].get(n, m);
                int bo = channelsOriginal[2].get(n, m);
                
                //se convierte a xyz la imagen filtrada
                int rr = channelsResult[0].get(n, m);
                int gr = channelsResult[1].get(n, m);
                int br = channelsResult[2].get(n, m);
                
                //rgb
                RGB rgbo = new RGB(ro, go, bo);
                RGB rgbr = new RGB(rr, gr, br);
                
                XYZ xyzo = new XYZ(rgbo);
                XYZ xyzr = new XYZ(rgbr);
                
                PuntoBlanco puntoBlanco = new PuntoBlanco(PuntoBlanco.Iluminantes.D65.nombre);
                LUV luvo = new LUV(puntoBlanco);
                LUV luvr = new LUV(puntoBlanco);
                
                luvo.fromXYZ(xyzo);
                luvr.fromXYZ(xyzr);
                //hallamos el cuadrado de la diferencia
                qdl = Math.pow((luvo.getL() - luvr.getL()), 2);
                qdu = Math.pow((luvo.getU() - luvr.getU()), 2);
                qdv = Math.pow((luvo.getV() - luvr.getV()), 2);
                //hallamos el cuadrado de los pixeles originales
                ql = Math.pow(luvo.getL(), 2);
                qu = Math.pow(luvo.getU(), 2);
                qv = Math.pow(luvo.getV(), 2);
                
                numeratorSum += Math.sqrt(qdl + qdu + qdv);
                denominatorSum += Math.sqrt(ql  + qu + qv);
            }
        }
        
        ncd = numeratorSum/denominatorSum;
        
        return ncd;
    }
    
    public double metricOfSimilarityM2(){
        RGBHistogram rGBHistogramOriginal = new RGBHistogram(original);
        RGBHistogram rGBHistogramResult = new RGBHistogram(result);
        double l2 = 0;
        //se hace primero el que tiene ruido puesto es que probablemente contenga mas colores
        Set setResult = rGBHistogramResult.getColorCumulativeCountMap().entrySet();
        Iterator iterator = setResult.iterator();
        int vhc, vic;
        while(iterator.hasNext()) {
            Map.Entry<String, Integer> ic = (Map.Entry<String, Integer>)iterator.next();
            vic = ic.getValue();
            if (rGBHistogramOriginal.getColorCumulativeCountMap().containsKey(ic.getKey()) ) {
                vhc = rGBHistogramOriginal.getColorCumulativeCountMap().get(ic.getKey());
                //ya no se computa posteriormente
                rGBHistogramOriginal.getColorCumulativeCountMap().remove(ic.getKey());
            } else { 
                vhc = 0; 
            }
            
            l2 += Math.pow(vhc - vic, 2);
        }
        //se termina comparando con los colores que sobran en el histograma original
        Set setOriginal = rGBHistogramOriginal.getColorCumulativeCountMap().entrySet();
        Iterator iterator2 = setOriginal.iterator();
        
        while(iterator2.hasNext()) {
            Map.Entry<String, Integer> hc = (Map.Entry<String, Integer>)iterator2.next();
            vhc = hc.getValue();
            if (rGBHistogramResult.getColorCumulativeCountMap().containsKey(hc.getKey()) ) {
                vic = rGBHistogramOriginal.getColorCumulativeCountMap().get(hc.getKey());
            } else { 
                vic = 0; 
            }
            
            l2 += Math.pow(vhc - vic, 2);
        }
        
        l2 = Math.sqrt(l2);
        
        return l2;
    }
    
    @Override
    public String toString() {
        double ncd = this.ncd();
        double m1;
        double m2;
        m1 = calculateOnlySimilarityMetrics();
        m2 = metricOfSimilarityM2();
        
//        double pearson = this.pearsonCorrelation();
//        double minimumRatio = this.minimumRatio();
//        double tanimoto = this.tanimotoMeasure();
//        double signChange = this.stochasticSignChange();
//        //double energy = this.energyJPD();
//        return mae + ", " + nmse + ", " + pearson + ", " + minimumRatio + ", " + tanimoto + ", " + signChange;
//        
        this.calculateMAEEuclidean();
        double mae = mae();
        //return maeHsv[0] + ", " + maeHsv[1] + ", " + maeHsv[2] + ", " + euclideanDistance;
//        double minkowski1 = this.minkowskiFormDistance(1);
//        double minkowski2 = this.minkowskiFormDistance(100);
//        return minkowski1 + ", " + minkowski2;
        
        
        
        return maeHsv[0] + ", " + maeHsv[1] + ", " + maeHsv[2] + ", " + euclideanDistance + ", " + ncd + ", " + m1 + ", " + m2 + ", " + mae;
    }
    
}
