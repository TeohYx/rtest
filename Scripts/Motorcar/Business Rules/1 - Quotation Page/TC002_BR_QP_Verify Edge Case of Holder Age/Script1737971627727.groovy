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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

/**
 * Test on edge case of holder age. 
 * 1. Age 17
 * 2. Age 18
 * 3. Age 80
 * 4. Age 81
 * 
 */

// Declare variable
int minimumHolder = GlobalVariable.rules_HolderAge['minimum']
int maximumHolder = GlobalVariable.rules_HolderAge['maximum']

def oldestEdgeCalendar = Calendar.getInstance()
oldestEdgeCalendar.add(Calendar.YEAR, -maximumHolder)

def oldestEdgeCase = oldestEdgeCalendar.clone()	// Oldest case to pass

oldestEdgeCalendar.add(Calendar.DAY_OF_MONTH, -1)

def oldestEdgeFail = oldestEdgeCalendar.clone() // Older case to fail (1 day older to get the eligible age)

def eldestEdgeCalendar = Calendar.getInstance()
eldestEdgeCalendar.add(Calendar.YEAR, -minimumHolder)

def eldestEdgeCase = eldestEdgeCalendar.clone()	// Eldest case to pass

eldestEdgeCalendar.add(Calendar.DAY_OF_MONTH, 1)

def eldestEdgeFail = eldestEdgeCalendar.clone() // Eldest case to fail (1 day younger to get the eligible age)

def passCase = [oldestEdgeCase, eldestEdgeCase]
def failCase = [oldestEdgeFail, eldestEdgeFail]

def positiveScenario = []
def negativeScenario = []

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

for (pass in passCase) {
	NRIC = CustomKeywords.'inputValidation.validation.inputNRICandProceed'(pass)
	
	WebUI.setText(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), NRIC)
	
	WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/button_Get Quote Now'))
	
	Boolean element = WebUI.verifyElementNotPresent(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/wrnmsg_NRIC'), 3, FailureHandling.OPTIONAL)
	
	if (!element) {
		positiveScenario.add(pass.getTime())
	}

}

for (fail in failCase) {
	NRIC = CustomKeywords.'inputValidation.validation.inputNRICandProceed'(fail)
	
	WebUI.setText(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), NRIC)
	WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/button_Get Quote Now'))
	
	Boolean element = WebUI.verifyElementPresent(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/wrnmsg_NRIC'), 3, FailureHandling.OPTIONAL)
	
	if (!element) {
		negativeScenario.add(fail.getTime())
	}

}
WebUI.takeScreenshot()
WebUI.closeBrowser()

def errorLog = []

if (!positiveScenario.isEmpty()) {
	errorLog.add("Positive scenario: ${positiveScenario}")
}
if (!negativeScenario.isEmpty()) {
	errorLog.add("Negative scenario: ${negativeScenario}")
}

assert errorLog.isEmpty() : KeywordUtil.markError("${errorLog.join('\n ')}")