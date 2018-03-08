package oskar.test;

import org.testng.annotations.Test;

import oskar.framework.DriverSetUp;
import oskar.framework.Step;
/**
 * This class creates objects for component tests.
 * In this case we are only testing a single form, therefore inside the method
 * testFirstComponent() we are creating an object of the form and run the whole test on it.
 * 
 * @author oskars dauksts (dauksts.oskars@gmail.com)
 *
 */
public class InsuranceQuotePageTest extends DriverSetUp {

	@Test(priority = 1)
	public void initialize() {

		Step.Browser.navigateTo("https://www.aaalife.com/term-life-insurance-quote-input", 5);
		Step.Wait.forElementVisible(InsuranceQuotePageConstants.pageLocator, InsuranceQuotePageConstants.pageLocatorDescription, 15);
	}
	
	@Test(priority = 5, dependsOnMethods = "initialize")
	public void testFirstComponent() {
		InsuranceQuoteFormTest termQuoteForm = new InsuranceQuoteFormTest();
		termQuoteForm.verifyAll();
	}
}
