

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RadarViewerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RadarViewerTest
{
    /**
     * Default constructor for test class RadarViewerTest
     */
    public RadarViewerTest()
    {
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
    public void testRadarViewerMain()
    throws InterruptedException
    {
        final int ROWS = 100;
        final int COLS = 100;
        RadarViewer testViewer = new RadarViewer();
        testViewer.main(new String[] {});
        Radar radar = new Radar(ROWS, COLS, 1, 1);
        assertEquals(radar.findVelocity(), "The monsters dx is: 1 \nThe monsters dy is: ");
    }
}
