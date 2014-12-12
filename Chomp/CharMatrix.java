// Implements a 2-D array of characters

public class CharMatrix
{
// Fields:
private char[][] table;
// Constructor: creates a grid with dimensions rows, cols,
// and fills it with spaces
public CharMatrix(int rows, int cols)
{
table = new char[rows][cols];
}

// Constructor: creates a grid with dimensions rows , cols ,
// and fills it with the fill character
public CharMatrix(int rows, int cols, char fill)
{
table = new char[rows][cols];
for (int i = 0; i < table.length; i++)
{
for(int j = 0; j < table[i].length; j++)
{
table[i][j] = fill;
}
}
}

// Returns the number of rows in grid
public int numRows()
{
return table.length;
}

// Returns the number of columns in grid
public int numCols()
{
return table[0].length;
}

// Returns the character at row, col location
public char charAt(int row, int col)
{
return table[row][col];
}

// Sets the character at row, col location to ch
public void setCharAt(int row, int col, char ch)
{
table[row][col] = ch;
}

// Returns true if the character at row, col is a space,
// false otherwise
public boolean isEmpty(int row, int col)
{
if (table[row][col] == 0)
{
return true;
}
else
{
return false;
}
}

// Fills the given rectangle with fill characters.
// row0, col0 is the upper left corner and row1, col1 is the
// lower right corner of the rectangle.
public void fillRect(int row0, int col0, int row1, int col1, char fill)
{
for(int i = row0; i <= row1; i++)
{
for(int j = col0; j <= col1; j++)
{
table[i][j] = fill;
}
}
}

// Fills the given rectangle with SPACE characters.
// row0, col0 is the upper left corner and row1, col1 is the
// lower right corner of the rectangle.
public void clearRect(int row0, int col0, int row1, int col1)
{
for(int i = row0; i <= row1; i++)
{
for(int j = col0; j <= col1; j++)
{
table[i][j] = 0;
}
}
}

// Returns the count of all non-space characters in row 
public int countInRow(int row)
{
int counter = 0;
for(int i = 0; i < table[row].length; i++)
{
if(table[row][i] != 0)
{
counter++;
}
}
return counter;
}

// Returns the count of all non-space characters in col 
public int countInCol(int col)
{
int counter = 0;
for(int i = 0; i < table.length; i++)
{
if(table[i][col] != 0)
{
counter++;
}
}
return counter;
}
}