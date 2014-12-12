import java.util.ArrayList;

public class Store
{
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    
    public void addSale(String customerName, double amount)
    {
        customers.add(new Customer(customerName, amount));
        
    }
    public String nameOfBestCustomer()
    {
        double highestVal = 0;
        int bestCustomerIndex = 0;
        for (int i = 0; i<customers.size(); i++)
        {
            if (customers.get(i).getAmount()>highestVal)
            {
                highestVal = customers.get(i).getAmount();
                bestCustomerIndex = i;
            }
         }
        return customers.get(bestCustomerIndex).getName();
    }
}
