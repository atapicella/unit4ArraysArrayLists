public class RotateRight
{
    public static void rotateRight(int[] arr)
    {
        int last = arr.length - 1;
        int temp = arr[last];
        
        for (int i = last; i>0; i--)
        {
            arr[i] = arr[i-1];
        }
        
        arr[0] = temp;
    }
}