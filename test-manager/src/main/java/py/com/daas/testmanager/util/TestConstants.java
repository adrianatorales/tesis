/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.testmanager.util;

/**
 *
 * @author Thelma
 */
public class TestConstants {
    
    public static class Ruidos  {
        
        public static class Gaussian{
            public static final String NAME = "gaussian";
            public static String[] ALLOWED_FILTERS = {"Median"};
        }
        
        public static class Impulsive{
            public static final String NAME = "impulsive";
            public static String[] ALLOWED_FILTERS = {"Median"};
        }
        
        public static class Poisson{
            public static final String NAME  = "poisson";
            public static String[] ALLOWED_FILTERS = {"Median"};
        }
        
        public static class Speckle{
            public static final String NAME = "speckle";
            public static String[] ALLOWED_FILTERS = {"Median"};
        }
    
        public static class Salt{
            public static final String NAME = "salt";
            public static String[] ALLOWED_FILTERS = {"Min"};
        }
        
        public static class Pepper{
            public static final String NAME = "pepper";
            public static String[] ALLOWED_FILTERS = {"Max"};
        }
        
    }
    
    public static class Filters {
        
        public static class TesisRGB{
            
            public static class ConVentanas{
                public static final String TESIS_RGB_ENTROPY = "TesisRGBEntropy";
                public static final String TESIS_RGB_MEAN = "TesisRGBMean";
                public static final String TESIS_RGB_VARIANCE = "TesisRGBVariance";
                public static final String TESIS_RGB_ENERGY = "TesisRGBEnergy";
                public static final String TESIS_RGB_MODE = "TesisRGBMode";
                public static final String TESIS_RGB_MAX = "TesisRGBMax";
                public static final String TESIS_RGB_MIN = "TesisRGBMin";
                //public static final String TESIS_RGB_SKEWNESS = "TesisRGBSkewness";
                public static final String TESIS_RGB_KURTOSIS = "TesisRGBKurtosis";
                //public static final String TESIS_RGB_THIRD_CENTRAL_MOMENT = "TesisRGBThirdCentralMoment";
                public static final String TESIS_RGB_FOURTH_CENTRAL_MOMENT = "TesisRGBFourthCentralMoment";
                public static final String TESIS_RGB_SKEWNESS_INVERSE = "TesisRGBSkewnessInverse";
                public static final String TESIS_RGB_THIRD_CENTRAL_MOMENT_INVERSE = "TesisRGBThirdCentralMomentInverse";
                public static final String TESIS_RGB_KURTOSIS_INVERSE = "TesisRGBKurtosisInverse";
                public static final String TESIS_RGB_FOURTH_CENTRAL_MOMENT_INVERSE = "TesisRGBFourthCentralMomentInverse";
                public static final String TESIS_RGB_FIFTH_CENTRAL_MOMENT = "TesisRGBFifthCentralMoment";
                public static final String TESIS_RGB_SIXTH_CENTRAL_MOMENT = "TesisRgbSixthCentralMoment";
                public static final String TESIS_RGB_SEVENTH_CENTRAL_MOMENT = "TesisRgbSeventhCentralMoment";
                public static final String TESIS_RGB_EIGHTH_CENTRAL_MOMENT = "TesisRgbEighthCentralMoment";
                public static final String TESIS_RGB_SMOOTHNESS = "TesisRGBSmoothness";
                public static final String TESIS_RGB_MODE2 = "TesisRGBMode2";
            }

            public static class SinVentanas{
                public static final String TESIS_RGB_ENTROPY_WW = "TesisRGBEntropyWW";
                public static final String TESIS_RGB_MEAN_WW = "TesisRGBMeanWW";
                public static final String TESIS_RGB_VARIANCE_WW = "TesisRGBVarianceWW";
                public static final String TESIS_RGB_MODE_WW = "TesisRGBModeWW";
                public static final String TESIS_RGB_MAX_WW = "TesisRGBMaxWW";
                public static final String TESIS_RGB_MIN_WW = "TesisRGBMinWW";
                public static final String TESIS_RGB_SMOOTHNESS_WW = "TesisRGBSmoothnessWW";
                public static final String TESIS_RGB_MODE2_WW = "TesisRGBMode2WW";
            }
        }

        public static class EstadoDelArte{
            public static final String ALPHA_LEX = "AlphaLexicographical";
            public static final String ALPHA_MODULUS_LEX = "AlphaModulusLexicographical";
            public static final String LEX = "Lexicographical";
            public static final String MARGINAL = "Marginal";
            public static final String BITMIXING = "MedianBitMixingImprovedFilter";
            public static final String EUCLIDEAN = "MedianEuclideanFilter";
            public static final String HSI_LEX = "HsiLexicographical";
            public static final String HSI_ALPHA_LEX = "HsiAlphaLexicographical";
            public static final String METRIC_OF_DISTANCE = "MetricOfDistance";
        }
        
        public static class TesisCMYK{
            public static class ConVentanas{
                public static final String TESIS_CMYK_MEAN = "TesisCMYKMean";
                public static final String TESIS_CMYK_MODE = "TesisCMYKMode";
                public static final String TESIS_CMYK_MIN = "TesisCMYKMin";
                public static final String TESIS_CMYK_MAX = "TesisCMYKMax";
                public static final String TESIS_CMYK_ENTROPY = "TesisCMYKEntropy";
                public static final String TESIS_CMYK_ENERGY = "TesisCMYKEnergy";
            }
        }

        public static class TesisCMY{
            public static class ConVentanas{
                public static final String TESIS_CMY_MEAN = "TesisCMYMean";
                public static final String TESIS_CMY_MODE = "TesisCMYMode";
                public static final String TESIS_CMY_MIN = "TesisCMYMin";
                public static final String TESIS_CMY_MAX = "TesisCMYMax";
                public static final String TESIS_CMY_ENERGY = "TesisCMYEnergy";
                public static final String TESIS_CMY_ENTROPY = "TesisCMYEntropy";       
            }
        }
    }
    
    
    
}