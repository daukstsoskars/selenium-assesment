package oskar.test;

import org.openqa.selenium.By;


/**
 * Class defines all the constant elements that are located within the flip
 * 
 * @author oskars dauksts (dauksts.oskars@gmail.com)
 *
 */
public final class InsuranceQuoteFormConstants {
	public static final By componentLocator = By.id("quoteForm");
	public static final String componentLocatorDescription = "Get a Traditional Term Quote form";
	
	public static final By zipCodeInputLocator = By.id("zip");
	public static final By zipCodeValidInputLocator = By.xpath("//input[@class='zipNumeric dirty valid']");
	public static final By zipCodeInValidInputLocator = By.xpath("//input[@class='zipNumeric dirty invalid'] | //input[@class='zipNumeric pristine invalid']");
	
	public static final By genderDropDownLocator = By.id("gender");
	public static final By defaultGenderLocator = By.xpath("//select[@id='gender']/option[1]");
	public static final By genderValidSelectionLocator = By.xpath("//select[@id='gender' and @class='dirty valid']");
	public static final By genderInvalidSelectionLocator = By.xpath("//select[@id='gender' and @class='dirty invalid]");
	
	public static final By monthOfBirthDropDownLocator = By.id("month");
	public static final By defaultMonthOfBirthDropDownLocator = By.xpath("//select[@id='month']/option[1]");
	public static final By monthOfBirthValidSelectionLocator = By.xpath("//select[@id='month' and @class='dirty valid']");
	public static final By monthOfBirthInvalidSelectionLocator = By.xpath("//select[@id='month' and @class='invalid dirty']");
	
	public static final By dayOfBirthDropDownLocator = By.id("day");
	public static final By defaultDayOfBirthDropDownLocator = By.xpath("//select[@id='day']/option[1]");
	public static final By dayOfBirthValidSelectionLocator = By.xpath("//select[@id='day' and @class='dirty valid']");
	public static final By dayOfBirthInvalidSelectionLocator = By.xpath("//select[@id='day' and @class='invalid dirty]");
	
	public static final By yearOfBirthDropDownLocator = By.id("year");
	public static final By defaultYearOfBirthDropDownLocator = By.xpath("//select[@id='year']/option[1]");
	public static final By yearOfBirthValidSelectionLocator = By.xpath("//select[@id='year' and @class='dirty valid']");
	public static final By yearOfBirthInvalidSelectionLocator = By.xpath("//select[@id='year' and @class='invalid dirty]");
	
	public static final By isAAAmemberYesLocator = By.xpath("//label[@for='isMemberYes']");
	public static final By isAAAmemberYesInvalidLocator = By.xpath("//input[@id='isMemberYes' and @class='dirty invalid']/following-sibling::label[@for='isMemberYes'] | //input[@id='isMemberYes' and @class='pristine invalid']/following-sibling::label[@for='isMemberYes']");
	public static final By isAAAmemberYesValidLocator = By.xpath("//input[@id='isMemberYes' and @class='dirty valid']/following-sibling::label[@for='isMemberYes'] | //input[@id='isMemberYes' and @class='pristine valid']/following-sibling::label[@for='isMemberYes']");
	
	public static final By isAAAmemberNoLocator = By.xpath("//label[@for='isMemberNo']");
	public static final By isAAAmemberNoInvalidLocator = By.xpath("//input[@id='isMemberNo' and @class='dirty invalid']/following-sibling::label[@for='isMemberNo'] | //input[@id='isMemberNo' and @class='pristine invalid']/following-sibling::label[@for='isMemberNo']");
	public static final By isAAAmemberNoValidLocator = By.xpath("//input[@id='isMemberNo' and @class='dirty valid']/following-sibling::label[@for='isMemberNo'] | //input[@id='isMemberNo' and @class='pristine valid']/following-sibling::label[@for='isMemberNo']");
	
	public static final By emailInputLocator = By.id("contact_email");
	public static final By emailValidInputLocator = By.id("//input[@id='contact_email' and (@class='valid dirty' or @class='valid pristine')");
	
	public static final By heightInFeetDropDownLocator = By.id("feet");
	public static final By defaultHeightFeetDropDownLocator= By.xpath("//select[@id='feet']/option[1]");
	public static final By heightInFeetValidDropDownLocator = By.xpath("//select[@id='feet' and @class='dirty valid']");
	public static final By heightInFeetInvalidDropDownLocator = By.xpath("//select[@id='feet' and @class='invalid dirty]");
	
	public static final By heightInInchesDropDownLocator = By.id("inches");
	public static final By defaultHeightInchesDropDownLocator= By.xpath("//select[@id='inches']/option[1]");
	public static final By heightInInchesValidDropDownLocator = By.xpath("//select[@id='inches' and @class='inc dirty valid']");
	public static final By heightInInchesInvalidDropDownLocator = By.xpath("//select[@id='inches' and @class='inc dirty valid]");
	
	public static final By weightInputLocator = By.id("weight");
	public static final By weightInvalidInputLocator = By.className("weightNumeric invalid dirty'");
	public static final By weightValidInputLocator = By.xpath("//input[@id='weight' and @class='weightNumeric dirty valid']");
	
	public static final By nicotineUseYesLocator = By.xpath("//label[@for='nicotineUseYes']");
	public static final By nicotineUseYesValidInputLocator = By.xpath("//input[@id='nicotineUseYes' and @class='dirty valid']/following-sibling::label[@for='nicotineUseYes'] | //input[@id='nicotineUseYes' and @class='pristine']/following-sibling::label[@for='nicotineUseYes']");
	public static final By nicotineUseYesInvalidInputLocator = By.xpath("//input[@id='nicotineUseYes' and @class='pristine invalid']/following-sibling::label[@for='nicotineUseYes']");
	
	public static final By nicotineUseNoLocator = By.xpath("//label[@for='nicotineUseNo']");
	public static final By nicotineUseNoValidInputLocator = By.xpath("//input[@id='nicotineUseNo' and @class='pristine valid']/following-sibling::label[@for='nicotineUseNo'] | //input[@id='nicotineUseNo' and @class='pristine']/following-sibling::label[@for='nicotineUseNo']");
	public static final By nicotineUseNoInvalidInputLocator = By.xpath("//input[@id='nicotineUseNo' and @class='pristine invalid']/following-sibling::label[@for='nicotineUseNo']");
	
	public static final By coverageAmountDropDownLocator = By.id("coverageAmount");
	public static final By defaultCoverageAmountLocator = By.xpath("//select[@id='coverageAmount']/option[9]");
	public static final By coverageDropDownValidInputLocator = By.xpath("//select[@id='coverageAmount' and @class='valid dirty']");
	
	public static final By termLenghtDropDownLocatorLocator = By.xpath("//select[@name='termLength']");
	public static final By defaultTermLenghtLocator = By.xpath("//select[@id='termLength']/option[1]");
	public static final By termLenghtDropDownValidInputLocator = By.xpath("//select[@id='termLength' and @class='dirty valid']");
	public static final By termLenghtInvalidDropDownLocator = By.xpath("//select[@id='termLength' and @class='dity invalid']");
	
	
	public static final By seeYourQuoteButtonLocator = By.id("seeQuote");
	
	public static final By dropDownInvalidInputLocator = By.xpath("//select[@class='dirty invalid'] | //select[@class='pristine invalid']");
	public static final By dropDownValidInputLocator = By.xpath("//select[@class='dirty valid']");
	public static final By errorFlipLocatorLocator = By.className("errorModal inline-window--content-inner");
}
