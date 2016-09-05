package py.com.daas.testmanager;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import py.com.daas.testmanager.util.TestImage;

/**
 *
 * @author Derlis Argüello
 */
public class TestMain {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        TestImage test = new TestImage();
        test.run();
        
    }
    
}