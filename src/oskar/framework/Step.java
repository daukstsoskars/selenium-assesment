package oskar.framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * Purpose of this class is to simplify the use of raw selenium and convert it to readable text
 * 
 * @author oskars dauksts (dauksts.oskars@gmail.com)
 *
 */
public final class Step {

	static int PASS = 0;
	static int FAIL = 0;
	
	final static WebDriver driver = DriverSetUp.driver;
	final static JavascriptExecutor js = (JavascriptExecutor) driver;

	public static final class Action {
		
		public static boolean click(WebElement element, String elementDescription) {
			try {
				element.click();
			} catch (NullPointerException e) {
				Failed("Click -" + elementDescription + " Element parameter was null");
				return false;
			}
			catch (WebDriverException e) {
				Failed("Click - " + elementDescription + e.getMessage());
				return false;
			}
			Passed("Click " + elementDescription);
			return true;
		}
		
		public static boolean click(By locator, String elementDescription) {
			return click(convertByToElement(locator),elementDescription);
		}
		
		public static boolean typeText(WebElement element, String elementDescription, String textToType) {
			try {
				element.sendKeys(Keys.CONTROL + "a");
				element.sendKeys(Keys.DELETE);
				element.sendKeys(textToType);
				try {
					Thread.sleep(1000);
				}
				catch (Exception e) {}
			}
			catch (NullPointerException | NoSuchElementException e) {
				Failed(elementDescription + " - Not found");
				return false;
			}
			catch (InvalidElementStateException e) {
				Failed(elementDescription + "- element not interractable");
				return false;
			}
			catch (StaleElementReferenceException e) {
				Failed(elementDescription + "- No longer located in the DOM");
				return false;
			}
			Passed("Typing " + textToType);
			return true;
		}
		
		public static boolean typeText(By locator, String elementDescription, String textToType) {
			return typeText(convertByToElement(locator), elementDescription, textToType);
		}
		
		public static boolean typeTextReal(WebElement element, String elementDescription, String textToType, final long delayMs) {
			try {
				element.sendKeys(Keys.CONTROL + "a");
				element.sendKeys(Keys.DELETE);
				element.click();
				Step.Wait.forSeconds(1);
				for(final char c : textToType.toCharArray()) {
					element.sendKeys("" + c);
					Thread.sleep(delayMs);
				}
			} catch (NullPointerException | NoSuchElementException e) {
				Failed(elementDescription + " - Not found");
				return false;
			}
			catch (InvalidElementStateException e) {
				Failed(elementDescription + " - element not interractable");
				return false;
			}
			catch (StaleElementReferenceException e) {
				Failed(elementDescription + " - No longer located in the DOM");
				return false;
			}
			catch (InterruptedException e) {
				Failed("Internal error has occured");
				return false;
			}
			Passed("Typing " + textToType);
			return true;
		}
		
		public static boolean typeTextReal(By locator, String elementDescription, String textToType, final long delayMs) {
			return typeTextReal(convertByToElement(locator), elementDescription, textToType, delayMs);
		}
		
		public static boolean selectByText(WebElement element, final String elementDescription, final String valueToSelect) {
			Select selectGender = null;
			
			try {
				selectGender = new Select(element);
				selectGender.selectByVisibleText(valueToSelect);
			} catch (UnexpectedTagNameException e) {
				Failed(elementDescription + "- Element had an unexpected tag name");
				return false;
			}
			catch (NullPointerException | NoSuchElementException e) {
				Failed(elementDescription + " - Not found");
				return false;
			}
			Passed("Selecting " + valueToSelect);
			return true;
		}
		public static boolean selectByValue(By locator, final String elementDescription, final String valueToSelect) {
			return selectByText(convertByToElement(locator), elementDescription, valueToSelect);
		}
		
		public static boolean clear(WebElement element, String elementDescription) {
			try {
				element.clear();
			}
			catch (NullPointerException | NoSuchElementException e) {
				Failed(elementDescription + "- Not found");
				return false;
			}
			catch (InvalidElementStateException e) {
				Failed(elementDescription + "- element not interractable");
				return false;
			}
			Passed("Clearing text from " + elementDescription);
			Step.Wait.forSeconds(1);
			return true;
		}
		public static boolean clear(By locator, String elementDescription) {
			return clear(convertByToElement(locator),elementDescription);
		}
	}

	public static final class Browser {
		public static boolean navigateTo(String url, final int timeOutSec) {
			try {
				driver.get(url);
				driver.manage().timeouts().pageLoadTimeout(timeOutSec, TimeUnit.SECONDS);
			}
			catch (TimeoutException e) {
				Failed("Navigating to " + url);
				return false;
			}
			Passed("Navigating to " + url);
			return true;
		}
	}

	public static final class Verify {
		
		public static boolean isVisible(final By locator, final String elementDescription) {
			WebElement element = null;
			try {
				element = driver.findElement(locator);
			} catch (NullPointerException | NoSuchElementException e) {
				Failed(elementDescription + " - Not found");
				return false;
			}
			catch (StaleElementReferenceException ex) {
				Failed(elementDescription + " - No longer located in the DOM");
				return false;
			}
			
			if(element.isDisplayed() && element.isEnabled()) {
				Passed(elementDescription + " is Visible ");
			}
			else {
				Failed("Assertion failed");
				return false;
			}
			return true;
		}
		
		
		public static boolean content(final By locator, final String elementDescription, final String attribute, final String comparand) {
			WebElement element = driver.findElement(locator);
			
			final String contentText = Extract.getContent(element, elementDescription, attribute);
			if(contentText.contains(comparand)) {
				if(comparand.isEmpty()) {
					Passed(elementDescription + " is Empty");
				}
				else {
					Passed(elementDescription + " contains " + comparand);
				}
			}
			else {
				Failed(elementDescription + " does not contain " + comparand);
				return false;
			}
			return true;
		}
	}
	
	public static final class Wait {
		
		public static boolean forSeconds(int seconds) {
			final long millis = (long) (seconds * 1000);
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				Failed("Wait for "+ millis);
				return false;
			}
			Passed("Wait for "+ seconds + " second(s)");
			return true;
		}
		
		public static boolean forElementVisible(final By locator, final String elementDescription, final int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
			catch (TimeoutException e) {
				Failed("Wait for element visible " + elementDescription);
				return false;
			}
			Passed("Wait for element visible " + elementDescription);
			return true;
		}
		
	}
	
	public static final class Extract {
		
		public static final String getContent(WebElement element, String elementDescription, final String attribute) {
			String context = "";
			try {
				context = element.getAttribute(attribute);
			} catch (NullPointerException | NoSuchElementException e) {
				Failed(elementDescription + "- Not found");
				return "NOT_FOUND";
			}
			catch (StaleElementReferenceException ex) {
				Failed(elementDescription + "- No longer located in the DOM");
				return "NOT_FOUND";
			}
			
			Passed("Extracted " + context + " from " + elementDescription);
			return context;
		}
		public static final String getContent(By locator, String elementDescription, final String attribute) {
			
			return getContent(convertByToElement(locator), elementDescription, attribute);
		}
		
		public static final String getContentUsingJS(WebElement element, String elementDescription) {
			String textContent = "";
			try {
				textContent = (String) js.executeScript("return arguments[0].value;", element);
			}
			catch (Exception e) {
				Failed("Unable to retrieve content from: " + elementDescription);
			}
			Passed("Content extracted '"+ textContent + "' from " + elementDescription);
			return textContent;
			
		}
		public static final String getContentUsingJS(By locator, String elementDescription) {
			WebElement element = driver.findElement(locator);
			return getContentUsingJS(element, elementDescription);
		}
	}
	
	public final static WebElement convertByToElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (NullPointerException | NoSuchElementException e) {
			return null;
		}
		catch (StaleElementReferenceException ex) {
			return null;
		}
		return element;
	}
	
	//Below methods are used for logging to console
	/**
	 * 
	 * @param stepDescription - description of the step you are performing
	 */
	public final static void Passed(String stepDescription) {
		System.out.println("PASSED: " + stepDescription);
		System.out.println(getExecutedLineInfo());
		System.out.println("");
		PASS++;
	}
	
	public final static void Failed (String stepDescription) {
		System.err.println(" FAILED : " + stepDescription);
		System.out.println(getExecutedLineInfo());
		System.out.println("");
		FAIL++;
	}
	
	public final static String getExecutedLineInfo() {
		int lineNumber = Thread.currentThread().getStackTrace()[4].getLineNumber();
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
		String className = Thread.currentThread().getStackTrace()[4].getClassName();
		String result = className + " > " + methodName + " > " + "Line " + lineNumber;
		return result;
	}
}
