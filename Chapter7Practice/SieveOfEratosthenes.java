import java.util.Scanner;
public class SieveOfEratosthenes
{
    
    
    /*
    public SieveOfEratosthenes()
    {
        SieveOfEratosthenes sieve = new SieveOfEratosthenes();
    }
    */
    public static void main(String[] args)
    {
        int currentSize = 0;
        int[] list;
        Scanner scan = new Scanner(System.in);
        System.out.print("Find all prime numbers from 2 - ");
        int limit = scan.nextInt();
        currentSize = limit;
        list = new int[limit-1];
        
        for (int i = 0; i<=limit-2; i++)
        {
            list[i] = i+2;
        }
        int index = 0;
        int position = 0;
        for (int i = list[index]; i*i<=limit; i=list[index])
        {
           //SieveOfEratosthenes.removeElementAtIndex(position+i);       
           list[position+i] = list[currentSize-2];
           currentSize--;
           index++;
        }
        for (int i=0;i<=list.length-1;i++)
        {
            System.out.println(list[i]);
        }
    }
    /*
    public void removeElementAtIndex(int index)
    {
        this.list[index] = this.list[this.currentSize - 1];
        this.currentSize--;
    }
    */
}
