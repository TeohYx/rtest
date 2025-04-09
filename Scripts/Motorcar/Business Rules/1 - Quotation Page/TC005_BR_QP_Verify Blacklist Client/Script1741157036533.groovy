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

def blacklistIC = GlobalVariable.rules_blacklistedCustomer
String error = GlobalVariable.validation_UnderwritingError['blacklistedCustomer']

def reminderObj = findTestObject('Object Repository/Motorcar and Motorcycle/Reminder Page/dyvalidation_Error_text', [
	('text') : error])

def errorLog = []
blacklistIC.each { _, ic ->
	inputScenario = [
		('inputField') : [
				[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ('inputText') : ic],
		]
	]
	
	WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)
	WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [
		('inputScenario'): inputScenario], FailureHandling.STOP_ON_FAILURE)
	
	boolean isPresent = WebUI.verifyElementPresent(reminderObj, 10, FailureHandling.OPTIONAL)
	
	if (!isPresent){
		errorLog.add(ic)
		WebUI.takeFullPageScreenshot()
	}
	
	WebUI.closeBrowser()
}

String log = "Failed Cases (blacklisted customer still able to proceed): " + errorLog.join(', ')

assert errorLog.isEmpty() : log
