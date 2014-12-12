public class ArrayOperations
{
public static void main(String[] args)
{
double[] array = {8,4,5,21,7,9,18,2,100};
System.out.println("Number of items in array: " + array.length);
System.out.println("First array item: " + array[0]);
System.out.println("Last array item: " + array[8]);
System.out.println("Last array item: " + array[array.length -1]);
for(int i=0;i<array.length;i++)
{
System.out.println(array[i]);
}
for(int i=0;i<array.length;i++)
{
System.out.println("Value at index " + i + " equals: " +array[i]);
}
for(int i=array.length-1;i>=0;i--)
{
System.out.println(array[i]);
}

for(double i : array)
{
System.out.println(i);
}
}
}