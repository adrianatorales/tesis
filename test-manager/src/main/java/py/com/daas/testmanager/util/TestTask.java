/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.daas.testmanager.util;

import py.com.tesisrgb.generics.BasicFilterAbstract;

import java.util.concurrent.Callable;

/**
 *
 * @author Derlis Argüello
 */
public class TestTask implements Callable<TaskResult>{
    
    private final TaskResult taskResult;

    public TestTask(BasicFilterAbstract test, Double varianza, Double indice, int ventanas) {
        this.taskResult = new TaskResult(test, indice, varianza, ventanas);
    }

    public TaskResult getTaskResult() {
        return taskResult;
    }
    
    @Override
    public TaskResult call() throws Exception {
        taskResult.setColProcessor(taskResult.getBasicAbstract().run());
        return taskResult;
    }
    
}
