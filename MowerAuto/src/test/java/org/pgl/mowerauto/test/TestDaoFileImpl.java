package org.pgl.mowerauto.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests to DaoFile implementation. 
 * */
public class TestDaoFileImpl {

    /**
     * Parse file to get a complete operation.
     * */
    @Test
    public void testGetOperation(){
        Assert.fail("Not implmented test.");
    }
    
    /**
     * Test Case : Parse a file with first line which have incorrect format.
     * */
    @Test
    public void testIncorrectHeader(){
        Assert.fail("Not implmented test.");

        //incorrect number
        
        //incorrect type
    }
    
    /**
     * Test Case : Parse a file with a state line (2, 4, 6,...) which have incorrect format.
     * */
    @Test
    public void testIncorrectState(){
        Assert.fail("Not implmented test.");

        //incorrect number
        
        //incorrect type
        
        //incorrect orient
    }
    
    /**
     * Test Case : Parse a file with a instruction line (3, 5, 7,...) which have incorrect format.
     * */
    @Test
    public void testIncorrectInstruction(){
        Assert.fail("Not implmented test.");
        
        //incorrect type
        
    }
    
    /**
     * Test Case : Parse a file with a state line which have not instruction line associated. 
     * */
    @Test
    public void testMissingInstruction(){
        Assert.fail("Not implmented test.");
        
        //missing case
        
    }
    
}
