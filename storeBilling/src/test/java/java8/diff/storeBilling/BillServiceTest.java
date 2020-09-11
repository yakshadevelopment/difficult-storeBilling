package java8.diff.storeBilling;
import static org.junit.Assert.*;
import static java8.diff.storeBilling.TestUtils.*;
import org.junit.Test;

public class BillServiceTest {
	
	@Test
	public void testItemPurchasedPayable() throws Exception {
		Item item=new Item("Oil",37.87,5.00);
		String value=new BillService().itemPurchasedPayable(item);
		TestUtils.yakshaAssert(TestUtils.currentTest(),value.equals("189.35")?"true":"false",TestUtils.businessTestFile);
	}

	@Test
	public void testSaleTaxCalculation() throws Exception{
		String value=new BillService().saleTaxCalculation(189.35);
		TestUtils.yakshaAssert(TestUtils.currentTest(),value.equals("23.67")?"true":"false",TestUtils.businessTestFile);
		}

	@Test
	public void testDiscountCalculation() throws Exception{
		String value1=new BillService().discountCalculation(5000.00);
		String value2=new BillService().discountCalculation(4999.00);
		TestUtils.yakshaAssert(TestUtils.currentTest(),value1.equals("500.00")?"true":"false",TestUtils.businessTestFile);
		TestUtils.yakshaAssert(TestUtils.currentTest(),value2.equals("0.00")?"true":"false",TestUtils.businessTestFile);
	}
	@Test
    public void testExceptionConditon() throws Exception{
	 yakshaAssert(currentTest(),true,boundaryTestFile);
      }

	@Test
	public void testBoundaryCondition() throws Exception {
	  yakshaAssert(currentTest(),true,exceptionTestFile);
   }


}
