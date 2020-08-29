package java8.diff.storeBilling;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter Consumer Number and name in a comma separate format:");
		String consumer=scanner.nextLine();
		String [] consumerDetails=consumer.split(",");
		Date date=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
		String today=dateFormat.format(date);
		System.out.println("Enter purchased item names and quantities in comma separate format:");
		String purchaseItems=scanner.nextLine();
		purchaseItems=purchaseItems.toUpperCase();
		List<Item> purchaseList=new ArrayList<Item>();
		String[] purchaseDetails=purchaseItems.split(",");
		double  totalPurchase=0.0;
	    for(int i=0;i<purchaseDetails.length-1;i=i+2)
	    {
	    	try
	    	{
	    	Item item=Stock.getItem(purchaseDetails[i]);
	    	
	    	if(item==null)
	    		throw new ItemNotFoundException();
	    	double qtyOrdered=Double.parseDouble(purchaseDetails[i+1]);
	    	item.setQtyOrdered(qtyOrdered);
	    	 double amountPayable=Double.parseDouble(new BillService().itemPurchasedPayable(item));
	    	 item.setAmountPayable(amountPayable);
	    	 totalPurchase=totalPurchase+amountPayable;
	    	  purchaseList.add(item); 
	    	}catch(ItemNotFoundException exp){}
	    }// end of loop
       String tax=new BillService().saleTaxCalculation(totalPurchase);
       String discount=new BillService().discountCalculation(totalPurchase);
       double finalPayment=totalPurchase+Double.parseDouble(tax)-Double.parseDouble(discount);
       System.out.println(today);
       System.out.println("Consumer Number:"+consumerDetails[0]);
	   System.out.println("Consumer Name:"+consumerDetails[1]);
	   String output=String.format("%-20s %-20s %-20s %-20s","Item Name","Price","Quantity","Amount");
	   System.out.println(output);
	   for(Item item:purchaseList)
	    	 System.out.println(item);
	    DecimalFormat df=new DecimalFormat("0.00");
	    System.out.println("Total.                "+df.format(totalPurchase));
	    System.out.println("Add:Sales Tax@12.5%.  "+tax);
	    System.out.println("Less:Discount.        "+discount);
	    System.out.println("Final Bill Payment.   "+df.format(finalPayment)+"\n");
	}
}
