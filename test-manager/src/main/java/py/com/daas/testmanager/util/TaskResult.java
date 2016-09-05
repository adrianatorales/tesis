/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.testmanager.util;

import ij.process.ColorProcessor;
import py.com.tesisrgb.generics.BasicFilterAbstract;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Derlis Argüello
 */
public class TaskResult {
    private BasicFilterAbstract basicAbstract;
    private ColorProcessor colProcessor;
    private double indice;
    private double varianza;
    private int ventanas;

    public TaskResult(BasicFilterAbstract basicAbstractt, double indice, double varianza, int ventanas) {
        this.indice = indice;
        this.varianza = varianza;
        this.basicAbstract = basicAbstractt;
        this.ventanas = ventanas;
    }

    public ColorProcessor getColProcessor() {
        return colProcessor;
    }

    public void setColProcessor(ColorProcessor colProcessor) {
        this.colProcessor = colProcessor;
    }

    public BasicFilterAbstract getBasicAbstract() {
        return basicAbstract;
    }
    
    @Override
    public String toString() {
        try {
            //usamos reflexion porque no puedo obtener los datos de la clase actual en caso contrario
            Field filterNameF = basicAbstract.getClass().getField("filterName");
            Field decisionValorReducidoF = basicAbstract.getClass().getField("reducedValue");
            Field decisionCompF = basicAbstract.getClass().getField("decisionByComp");
            
            String filterName = (String)filterNameF.get( basicAbstract );
            double decisionValorReducido = (double)decisionValorReducidoF.get( basicAbstract );
            double[] decisionComp = (double[])decisionCompF.get( basicAbstract );

            return indice + ", " + varianza + ", " + filterName + ", " + decisionValorReducido + ", " + decisionComp[0] + ", " + decisionComp[1] + ", " + decisionComp[2] + ", " + ventanas;
        } catch (SecurityException ex) {
            Logger.getLogger(TaskResult.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TaskResult.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TaskResult.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(TaskResult.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}