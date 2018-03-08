package oskar.test;

import org.openqa.selenium.By;

import oskar.framework.Step;

/**
 * This class will test all the databoxes located on the form
 * 
 * @author oskars dauksts (dauksts.oskars@gmail.com)
 */

public class InsuranceQuoteFormTest {
	public void verifyAll() {
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.componentLocator,
				InsuranceQuoteFormConstants.componentLocatorDescription, 5)) {
			
			zipCodeTextBoxTest();
			genderDropDownBoxTest();
			MMDDYYYYDropDownTest();
			areYouAAAmemberTest();
			emailTextBoxTest();
			bodyDescriptionTest();
			useOfNicotineRadioButtonTest();
			coverageAmountDropDownTest();
			termLenghtDropDownTest();
		}
	}
	
	/**
	 * Following method performs testing on zip code textbox
	 */
	public void zipCodeTextBoxTest() {
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.zipCodeInputLocator, "Zip Code input textbox", 5)) {
			
			//Assuming location services are turned on in the browser check that zipcode box is already filled out
			String zipInputBoxTextContent = Step.Extract.getContentUsingJS(InsuranceQuoteFormConstants.zipCodeInputLocator, "Zip code text box");
			if(zipInputBoxTextContent.isEmpty()) {
				Step.Failed("Zip code text box is empty");
			}
			else {
				Step.Passed("Zip code text box is populated with the location of the user");
			}
			
			//Special character input
			Step.Wait.forSeconds(1);
			Step.Action.typeText(InsuranceQuoteFormConstants.zipCodeInputLocator, "Zip code input field", "!@#$%");
			Step.Action.click(InsuranceQuoteFormConstants.seeYourQuoteButtonLocator, "See your quote");
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.zipCodeInValidInputLocator, "Zip code text box shows as invalid", 10);
			
			//Invalid zip code
			Step.Action.typeText(InsuranceQuoteFormConstants.zipCodeInputLocator, "Zip code input field", "00000");
			Step.Action.click(InsuranceQuoteFormConstants.seeYourQuoteButtonLocator, "See Your Quote button");
			Step.Action.click(InsuranceQuoteFormConstants.seeYourQuoteButtonLocator, "See Your Quote button");
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.zipCodeInValidInputLocator, "Zip code text box shows as invalid", 10);
			
			//Overflow test
			Step.Action.typeText(InsuranceQuoteFormConstants.zipCodeInputLocator, "Zip code text box", "4800999");
			zipInputBoxTextContent = Step.Extract.getContentUsingJS(InsuranceQuoteFormConstants.zipCodeInputLocator, "Zip code text box");
			if(zipInputBoxTextContent.length() > 5) {
				Step.Failed("Zip code text box contains more than 5 digits");
			}
			else {
				Step.Passed("Zip code text box does not accept more than 5 digits");
			}
			
			//Submit empty zip code textbox
			Step.Action.clear(InsuranceQuoteFormConstants.zipCodeInputLocator, "Zip code input field");
			Step.Action.click(InsuranceQuoteFormConstants.seeYourQuoteButtonLocator, "See Your Quote button");
			Step.Action.click(InsuranceQuoteFormConstants.seeYourQuoteButtonLocator, "See Your Quote button");
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.zipCodeInValidInputLocator, "Zip code text box shows as invalid", 10);
			
			//Valid input
			Step.Action.typeText(InsuranceQuoteFormConstants.zipCodeInputLocator, "Zip code input field", "48009");
			Step.Action.click(InsuranceQuoteFormConstants.seeYourQuoteButtonLocator, "See your quote button");
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.zipCodeValidInputLocator, "zip code field displays as valid", 10);
		}
	}
	
	/**
	 * Following method performs testing on gender drop-down
	 */
	public void genderDropDownBoxTest() {
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.genderDropDownLocator, "Gender drop-down", 5)) {
			dropDownTest(InsuranceQuoteFormConstants.genderDropDownLocator, InsuranceQuoteFormConstants.defaultGenderLocator, 
					InsuranceQuoteFormConstants.genderValidSelectionLocator,InsuranceQuoteFormConstants.genderInvalidSelectionLocator, "Select", "Male");
		}
	}
	
	/**
	 * Following method performs testing on Month, Day, Year of birth drop-down
	 */
	public void MMDDYYYYDropDownTest() {
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.monthOfBirthDropDownLocator, "Month of birth drop-down", 5)) {
			dropDownTest(InsuranceQuoteFormConstants.monthOfBirthDropDownLocator,InsuranceQuoteFormConstants.defaultMonthOfBirthDropDownLocator,
					InsuranceQuoteFormConstants.monthOfBirthValidSelectionLocator, InsuranceQuoteFormConstants.monthOfBirthInvalidSelectionLocator, "MM", "June");
		}
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.dayOfBirthDropDownLocator, "Day of birth drop-down", 5)) {
			dropDownTest(InsuranceQuoteFormConstants.dayOfBirthDropDownLocator, InsuranceQuoteFormConstants.defaultDayOfBirthDropDownLocator,
					InsuranceQuoteFormConstants.dayOfBirthValidSelectionLocator,InsuranceQuoteFormConstants.dayOfBirthInvalidSelectionLocator, "DD", "13");
		}
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.yearOfBirthDropDownLocator, "Year of Birth drop-down", 5)) {
			dropDownTest(InsuranceQuoteFormConstants.yearOfBirthDropDownLocator, InsuranceQuoteFormConstants.defaultYearOfBirthDropDownLocator,
					InsuranceQuoteFormConstants.yearOfBirthValidSelectionLocator, InsuranceQuoteFormConstants.yearOfBirthInvalidSelectionLocator, "YYYY", "1990");
		}
	}
	
	/**
	 * Following method performs testing on two radio buttons that answer the question "Are you a AAA member?"
	 */
	public void areYouAAAmemberTest() {
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.isAAAmemberYesLocator, "AAA member (yes radio button)", 5) &&
				Step.Wait.forElementVisible(InsuranceQuoteFormConstants.isAAAmemberNoLocator, "AAA member (no radio button)", 5)){
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.isAAAmemberYesInvalidLocator, "Radio button 'Yes' invalid (your quote button has already been pressed before)", 5);
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.isAAAmemberNoInvalidLocator, "Radio button 'No' invalid (your quote button has already been pressed before)", 5);
			
			Step.Action.click(InsuranceQuoteFormConstants.isAAAmemberYesLocator, "AAA member (yes radio button)");
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.isAAAmemberYesValidLocator, "Radio button 'Yes' is now valid", 5);
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.isAAAmemberNoValidLocator, "Radio button 'No' is now valid", 5);
		}
	}
	
	/**
	 * Following method performs testing on the email textbox
	 */
	public void emailTextBoxTest() {
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.emailInputLocator, "Email text box", 5)) {
			Step.Action.typeText(InsuranceQuoteFormConstants.emailInputLocator, "Email text input box", "test@test.com");
		}
	}
	
	/**
	 * Following method performs testing on Height drop-downs and Weight textbox
	 */
	public void bodyDescriptionTest() {
		//Height in feet drop-down test
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.heightInFeetDropDownLocator, "Height feet drop-down", 5)) {
			dropDownTest(InsuranceQuoteFormConstants.heightInFeetDropDownLocator, InsuranceQuoteFormConstants.defaultHeightFeetDropDownLocator, 
					InsuranceQuoteFormConstants.heightInFeetValidDropDownLocator, InsuranceQuoteFormConstants.heightInFeetInvalidDropDownLocator, "FT.", "6");
		}
		//Height in inches drop-down test
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.heightInInchesDropDownLocator, "Height inches drop-down", 5)) {
			dropDownTest(InsuranceQuoteFormConstants.heightInInchesDropDownLocator, InsuranceQuoteFormConstants.defaultHeightInchesDropDownLocator,
					InsuranceQuoteFormConstants.heightInInchesValidDropDownLocator, InsuranceQuoteFormConstants.heightInInchesInvalidDropDownLocator, "IN.", "4");
		}
		//Weight textbox test
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.weightInputLocator, "Weight input box", 5)) {
			String weightPlaceHolderLBS = Step.Extract.getContent(InsuranceQuoteFormConstants.weightInputLocator, "Weight placeholder", "placeholder");
			
			if(weightPlaceHolderLBS.contentEquals("LBS.")) {
				Step.Passed("Weight text box displays 'LBS.' as a placeholder");
			}
			else {
				Step.Failed("Weight text box does not display 'LBS.' as a placeholder");
			}
			
			//overflow test
			Step.Action.typeText(InsuranceQuoteFormConstants.weightInputLocator, "Weight input box", "2345");
			String weightSpecified = Step.Extract.getContentUsingJS(InsuranceQuoteFormConstants.weightInputLocator, "Weight text box content");
			if(weightSpecified.length() > 3) {
				Step.Failed("Weight text box accepts more than 3 digits");
			}
			else {
				Step.Passed("Weight text box accepts no more than 3 digits");
			}
		}
	}
	
	/**
	 * Following method performs testing on two radio buttons that answer "Have you used nicotine in the last 12 months?"
	 */
	public void useOfNicotineRadioButtonTest() {
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.nicotineUseYesLocator, "Nicotine use (YES button)", 5) &&
				Step.Wait.forElementVisible(InsuranceQuoteFormConstants.nicotineUseNoLocator, "Nicotine use (NO button)", 5)){
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.nicotineUseYesInvalidInputLocator, "Nicotine 'yes' is not selected by default", 5);
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.nicotineUseNoInvalidInputLocator, "Nicotine 'no' is not selected by default", 5);
			
			Step.Action.click(InsuranceQuoteFormConstants.nicotineUseYesLocator, "Nicotine use 'YES'");
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.nicotineUseYesValidInputLocator, "Nicotine 'yes' selection", 5);
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.nicotineUseNoValidInputLocator, "Nicotine 'no' is no longer showing an error since 'yes' was clicked", 5);
		}
	}
	
	/**
	 * Following method performs testing on coverage amount drop-down
	 */
	public void coverageAmountDropDownTest() {
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.coverageAmountDropDownLocator, "Coverage Amount drop-down", 5)) {
			dropDownTest(InsuranceQuoteFormConstants.coverageAmountDropDownLocator, InsuranceQuoteFormConstants.defaultCoverageAmountLocator,
					InsuranceQuoteFormConstants.coverageDropDownValidInputLocator, null, "$500,000", "$250,000");
		}
	}
	
	/**
	 * Following method performs testing on Term Length drop-down
	 */
	public void termLenghtDropDownTest() {
		if(Step.Wait.forElementVisible(InsuranceQuoteFormConstants.termLenghtDropDownLocatorLocator, "Term Lenght drop-down", 5)) {
			dropDownTest(InsuranceQuoteFormConstants.termLenghtDropDownLocatorLocator, InsuranceQuoteFormConstants.defaultTermLenghtLocator, 
					InsuranceQuoteFormConstants.termLenghtDropDownValidInputLocator, InsuranceQuoteFormConstants.termLenghtInvalidDropDownLocator, "Select", "10 Years");
		}
	}
	
	/**
	 * 
	 * @param dropDownElement - drop-down box you are testing
	 * @param defaultDropDownElement - drop-down element's placeholder
	 * @param validDropDownStatus - element when information entered in the textbox is correct
	 * @param invalidDropDownStatus - element when information entered in the textbox is incorrect
	 * @param defaultDropDownText - text that dropdown displays by default
	 * @param optionToSelect - text option that you want to select from the drop-down
	 */
	public void dropDownTest(By dropDownElement, By defaultDropDownElement, By validDropDownStatus, By invalidDropDownStatus,
			String defaultDropDownText, String optionToSelect) {
		String dropDownText = Step.Extract.getContent(defaultDropDownElement, "default status of the drop-down", "textContent");

		if(dropDownText.contentEquals(defaultDropDownText)) {
			Step.Passed("drop-down displays " + defaultDropDownText + " by default");
			Step.Action.click(InsuranceQuoteFormConstants.seeYourQuoteButtonLocator, "See your quote");
			Step.Wait.forElementVisible(InsuranceQuoteFormConstants.dropDownInvalidInputLocator, "Drop-down displays invalid input", 10);
		}
		else {
			Step.Failed("drop-down displays " + dropDownText + " by default");
		}
		
		Step.Action.selectByValue(dropDownElement, "Drop-down", optionToSelect);
		
		String selectedElementXpath = "//option[contains(text(),'%s')]";
		By selectedOption = By.xpath(String.format(selectedElementXpath, optionToSelect));
		
		dropDownText = Step.Extract.getContent(selectedOption, "Selected drop-down option", "textContent");
		
		if(dropDownText.contains(optionToSelect)) {
			Step.Passed("Drop-down displays selected option " + optionToSelect);
			if(validDropDownStatus != null) {
				Step.Wait.forElementVisible(validDropDownStatus, "drop-down displays valid input", 10);
			}
		}
		else {
			Step.Failed("Drop-down does not display selected option: " + optionToSelect);
		}
	}
	
}
