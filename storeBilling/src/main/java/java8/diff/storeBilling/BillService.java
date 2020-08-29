package java8.diff.storeBilling;
import java.text.DecimalFormat;

public class BillService {
	private String convertToString(double value)
	{
		DecimalFormat decimalFormat=new DecimalFormat("0.00");
		 return decimalFormat.format(value);
	}
	public String itemPurchasedPayable(Item item)
	{
		double amountPayable=item.getRate()*item.getQtyOrdered();
		return convertToString(amountPayable);
	}
	public String saleTaxCalculation(double amount)
	{
		return convertToString(amount*0.125);
	}
	public String discountCalculation(double amount)
	{
		double value=0.00;
		if(amount>=5000.00)
			value=amount*0.1;
		return convertToString(value);
	}
}
