import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver

String departureFlightButton = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "flight.departureFlight", "EN")
String returnFlightButton = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "flight.returnFlight", "EN")

// Obj Repo
def flightSwitch = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Flight Delay Claim/button_FlightDelaySwitch')
def departureFlightNumber = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Flight Delay Claim/input_DepartureFlightNumber')
def departureFlight = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Flight Delay Claim/dybutton_Flight_flight',
	[('flight'): departureFlightButton])
def returnFlight = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Flight Delay Claim/dybutton_Flight_flight',
	[('flight'): returnFlightButton])
def returnFlightNumber = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Flight Delay Claim/input_ReturnFlightNumber')

def defaultParams = [
		'isOn': true,
		'departureFlightNumber': 'MH004',
		'departureFlightDate': '',
		'returnFlightNumber': 'MH004',
		'returnFlightDate': ''
	]

try {
	params = defaultParams + params
} catch (Exception e) {
	params = defaultParams
}

def departureDate = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Flight Delay Claim/button_DepartureDate')
def returningDate = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Flight Delay Claim/button_ReturnDate')

WebDriver driver = DriverFactory.getWebDriver()
JavascriptExecutor js = (JavascriptExecutor)driver
def changeDateEvent(JavascriptExecutor js, WebElement date, String newDate) {
	js.executeScript("""
	    arguments[0].value = arguments[1];
	    // Trigger change events
	    var event = new Event('change', { bubbles: true });
	    arguments[0].dispatchEvent(event);
	""", date, newDate)
}

String switchText = WebUI.getText(flightSwitch)

if (!params['isOn'] && switchText == "Yes") {
	WebUI.enhancedClick(flightSwitch)
	return
}

// Switch the flight and click on depart flight
if (switchText == "No") {
	WebUI.enhancedClick(flightSwitch)
}

//Departure
WebUI.enhancedClick(departureFlight)

// Execute js on date picker
WebElement departDate = WebUiCommonHelper.findWebElement(departureDate, 30)
WebUI.setText(departureFlightNumber, params['departureFlightNumber'])

if (params['departureFlightDate'] != '') {
	WebUI.enhancedClick(departureDate)
	changeDateEvent(js, departDate, params['departureFlightDate'])
}

//Return
WebUI.enhancedClick(returnFlight)

WebElement returnDate = WebUiCommonHelper.findWebElement(returningDate, 30)
WebUI.setText(returnFlightNumber, params['returnFlightNumber'])

if (params['returnFlightDate'] != '') {
	WebUI.enhancedClick(returningDate)
	changeDateEvent(js, returnDate, params['returnFlightDate'])
}
