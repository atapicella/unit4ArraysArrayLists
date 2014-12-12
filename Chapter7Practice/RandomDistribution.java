import java.util.Scanner;

public class RandomDistribution
{
public static void main(String[] args)
{
Scanner in = new Scanner(System.in);
System.out.print("How many random numbers do you want to generate? ");
int num1 =in.nextInt();
System.out.print("What is the number of values for each random draw? ");
int num2=in.nextInt();
int[] array = new int[num1];
for(int i =0; i<num1-1;i++)
{
array[i] = (int) (Math.random() * num2);
}
for( int i =0; i< num2; i++)
{
int counter = 0;
for(int j =0; j<num1-1;j++)
{
if(array[j] == i)
{
counter++;
}
}
System.out.println(i+" " + counter);
}
}
}