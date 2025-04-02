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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

/**
 * Active the flight delay claim switch, error should show when click on proceed button. After fill in departure number and retry, there should not be error.
 * 
 * @author 80012455
 *
 */

def flightDelayNegativeParam = [
		'departureFlightNumber': '',
		'returnFlightNumber': ''
	]
	
def flightDelayPositiveParam = [
		'departureFlightNumber': 'MH004',
		'returnFlightNumber': 'MH004'
	]

String departFlightWrnmsg = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "wrnmsg.enterDepartureFlight", "EN")
def wrnmsgElement = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Flight Delay Claim/dywrnmsg_DepartureFlightDetails_wrnmsg', 
	[('wrnmsg'): departFlightWrnmsg])

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCplan') : 5], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Flight delay claim - Group'), [('params'): flightDelayNegativeParam], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Traveller Info - Adult, Main, Group'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Traveller Info - Adult, Secondary, Group'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_ConfirmDetails'))

assert WebUI.verifyElementPresent(wrnmsgElement, 3, FailureHandling.OPTIONAL) : "Departure flight warning message not appearing despite not filled."

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Flight delay claim - Group'), [('params'): flightDelayPositiveParam], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_ConfirmDetails'))

assert WebUI.verifyElementNotPresent(wrnmsgElement, 3, FailureHandling.OPTIONAL) : "Departure flight warning message appearing despite the departure info is filled."

