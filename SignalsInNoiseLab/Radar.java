import java.util.Scanner;

/**
 * The model for radar scan and accumulator
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    private boolean[][] currentScan;
    //stores the currentScan array from the previous iteration
    private boolean[][] prevScan;
    
    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulator;
    
    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;

    //Maximum change in x
    private int rowChangeThreshold = 5;
    //Maximum change in y
    private int colChangeThreshold = 5;
    //change in x position
    private int dX;
    //change in y position
    private int dY;
    //multipliers to handle negative dx and/or dy
    private int xMultiplier;
    private int yMultiplier;
    
    //counts to determine value of multiplier
    private int xCount = 0;
    private int yCount = 0;
    
    
    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     * @param   dX      change in X
     * @param   dY      change in Y
     */
    public Radar(int rows, int cols, int dX, int dY)
    {
        // initialize instance variables
        currentScan = new boolean[rows][cols]; // elements will be set to false
        prevScan = new boolean[rows][cols]; // elements will be set to false
        accumulator = new int[rows][cols]; // elements will be set to 0
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Do you want to enter a starting position and velocity?(y/n)");
        //lets user set starting position and dx/dy if they choose "y"
        if (scan.next().equals("y"))
        {
           System.out.println("Enter a starting x position: ");
           monsterLocationCol = scan.nextInt();
           System.out.println("Enter a starting y position: ");
           monsterLocationRow = scan.nextInt();
           System.out.println("Enter a x velocity: ");
           this.dX = scan.nextInt();
           System.out.println("Enter a y velocity: ");
           this.dY = scan.nextInt();
        }     
        //If user chooses "n" starting position is random and dx/dy is set through constructor parameters
        else
        {
           // randomly set the location of the monster (can be explicity set through the
           //  setMonsterLocation method
           monsterLocationRow = (int)(Math.random() * rows);
           monsterLocationCol = (int)(Math.random() * cols);
           this.dX = dX;
           this.dY = dY;
        }
        
        
        noiseFraction = .05;
        numScans= 0;
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public boolean scan()
    {
       // zero the current scan grid
       for(int row = 0; row < currentScan.length; row++)
       {
           for(int col = 0; col < currentScan[0].length; col++)
           {
               currentScan[row][col] = false;
           }
       }
       
       //sets the monster location and updates it based on dy/dx
       setMonsterLocation();
       
       // inject noise into the grid
       injectNoise();
       
       if (numScans > 0)
       {
           accumulate();
        }
        
       //copies elements of currentScan to prevScan
       for(int row = 0; row < currentScan.length; row++)
       {
           for(int col = 0; col < currentScan[0].length; col++)
           {
               if (currentScan[row][col] == true)
               {
                   prevScan[row][col] = true;
                }
                else
                {
                    prevScan[row][col] = false;
                }
           }
       }
           
           
       // keep track of the total number of scans
       numScans++;
       
       //checks if monster is about to go off radar
       if (monsterLocationRow>=99 || monsterLocationRow<=1 || monsterLocationCol>=99 || monsterLocationCol<=1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * tracks each possible velocity and stores the number of times it occurs
     */
    public void accumulate()
    {
       for(int row = 0; row < currentScan.length; row++)
       {
           for(int col = 0; col < currentScan[0].length; col++)
           {
               for(int row2 = 0; row2 < prevScan.length; row2++)
               {
                    for(int col2 = 0; col2 < prevScan[0].length; col2++)
                    {
                        //sets the possible x and y velocity for this iteration
                        int velY = row2-row;
                        int velX = col2-col;
                        
                        
                        if(currentScan[row][col] == true && prevScan[row2][col2] == true && velX<=5 && velY<=5 && velX>=-5 && velY>=-5)
                        {
                            //updates counter and makes velocity positive
                            if (velY<0)
                            {
                                yCount -= 1;
                                velY = Math.abs(velY);
                            }
                            else
                            {
                                yCount += 1;
                            }
                            //updates counter and makes velocity positive
                            if (velX<0)
                            {
                                xCount -= 1;
                                velX = Math.abs(velX);
                            }
                            else
                            {
                                xCount += 1;
                            }
                            //increases if this is a possible velocity
                            accumulator[velY][velX]++;
                        }
                    }
               }
           } 
        }
        
      //sets the multiplier based on the sign of the count
      if (xCount<0)
      {
          this.xMultiplier = 1;
       }
      else
      {
          this.xMultiplier = -1;
        }
      //sets the multiplier based on the sign of the count
      if (yCount<0)
      {
          this.yMultiplier = 1;
       }
      else
      {
          this.yMultiplier = -1;
        }
    
           
       
    }
    /**
     * calculates the velocity based on which one occured most in the accumulator
     */
    public String findVelocity()
    {
        //tracks the highest x and y velocity
        int highestValRow = 0;
        int highestValCol = 0;
        //finds the highest x and y velocity
        for(int row = 1; row < accumulator.length; row++)
        {
            for(int col = 1; col < accumulator[0].length; col++)
            {
                if (accumulator[row][col]>accumulator[highestValRow][highestValCol])
                {
                    highestValRow = row;
                    highestValCol = col;
                }
            }
        }
        //returns out dx and dy
        return "The monsters dx is: " + (highestValCol*this.yMultiplier) + "\nThe monsters dy is: " + (highestValRow*this.xMultiplier);
        
    }
    

    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation()//int row, int col)
    {
        //sets current position to false
        currentScan[monsterLocationRow][monsterLocationCol] = false;
        //updates position
        monsterLocationRow += dY;
        monsterLocationCol += dX;
        //exits method if monster will be off radar
        if (monsterLocationRow>99 || monsterLocationRow<1 || monsterLocationCol>99 || monsterLocationCol<1)
        {
            return;
        }
        //re-set monster with new position
        currentScan[monsterLocationRow][monsterLocationCol] = true;
    }
    
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
    public boolean isDetected(int row, int col)
    {
        return currentScan[row][col];
    }
    
    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        return accumulator[row][col];
    }
    
    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
    public int getNumRows()
    {
        return currentScan.length;
    }
    
    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }
    
    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }
    
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true;
                }
            }
        }
    }
    
}
