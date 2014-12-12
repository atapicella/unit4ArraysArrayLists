
public class ReplaceWithLargestNeighbor
{
    public static void ReplaceWithLargestNeighbor(int[] arr)
    {
        int[] tempArray = new int[arr.length];
        tempArray[0] = arr[0];
        tempArray[tempArray.length-1] = arr[arr.length-1];
        
        for (int i = 1; i<arr.length - 1; i++)
        {
            if (arr[i-1] > arr[i+1])
            {
                tempArray[i] = arr[i-1];
            }
            else
            {
                tempArray[i] = arr[i+1];
            }
    }

}
