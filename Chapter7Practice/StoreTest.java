

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a description of test class StoreTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StoreTest
{
    /** description of instance variable x (add comment for each instance variable) */
    private int x;

    /**
     * Default constructor for objects of class StoreTest
     */
    public StoreTest()
    {
        // initialise instance variables
        x = 0;
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    @Test
    public void testAddSale()
    {
        Store testStore = new Store();
        testStore.addSale("john",15.84); 
        testStore.addSale("jack",11.92); 
        testStore.addSale("nate",25.13); 
        String bestCustomer = testStore.nameOfBestCustomer();
        assertEquals("nate", bestCustomer);
    }
        
}
