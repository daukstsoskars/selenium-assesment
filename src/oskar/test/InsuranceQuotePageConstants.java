package oskar.test;

import org.openqa.selenium.By;
/**
 *Class contains constants for components located on the page (Ex: Header, footer, forms, billboards, etcm)
 * 
 * @author oskars dauksts (dauksts.oskars@gmail.com)
 * 
 */

public final class InsuranceQuotePageConstants {
	public static final By pageLocator = By.xpath("//body");	//unique identifier that identifies the target page to be tested
	public static final String pageLocatorDescription = "AAA Life Insurance Company Page";
	
	//Constant defines the traditional term quote form
	public static final By traditionalTermQuote = By.id("quoteForm");
}
