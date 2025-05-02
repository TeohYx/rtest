import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.github.fge.jsonschema.library.Keyword
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
 * Verify if the sum of all the content in Contents Calculator, and make sure it is within the range of [min, max]
 * 
 * Scenario: 
 * 1. Total content amount : min (edge case)
 * 2. Total content amount : max (edge case)
 * *Note: The data will rounded based on the last significant 3 digits
 */

Map contentsInformation = GlobalVariable.dyobj_ORPPA_ContentsCalculatorInput
def contentsCalculatorLink = findTestObject('Object Repository/Hohh/Plan Page/dylink_ProceedToCostCalculator_text', 
	[('text') : GlobalVariable.dyobj_ORPP001['homeContent']])
def rangeOfContentAmountObj = findTestObject('Object Repository/Hohh/Plan Page/dytext_ContentsCalculatorRange_text',
	[('text') : GlobalVariable.dyobj_ORPP003['contentRange']])

def confirmationObj = findTestObject('Object Repository/Hohh/Plan Page/dyinput_ContentsCalculator_input', 
		[('input') : contentsInformation['livingRoom']])

def totalAmountObj = findTestObject('Object Repository/Hohh/Plan Page/text_TotalPremiumAfterDiscount')

def confirmAmountObj = findTestObject('Object Repository/Hohh/ORM002_dybutton_CommonButton_text',
		[('text') : GlobalVariable.dyobj_ORPP001['contentConfirm']])

// Start
WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(contentsCalculatorLink)

String rangeText = WebUI.getText(rangeOfContentAmountObj)
def rangeOfAmount = rangeText.findAll(/\d{1,3}(?:,\d{3})*/).collect { it.replace(',', '').toInteger() }

def errorLog = []
// Run min and max value for each case testing

def listInputAmount = CustomKeywords.'utils.Utility.amountDistribution'(rangeOfAmount, contentsInformation.size(), true)
	
rangeOfAmount.eachWithIndex { amount, i ->
	def totalListInputAmount = listInputAmount[i].sum().toInteger()
	println(listInputAmount[i])
	println([totalListInputAmount, amount])
	assert totalListInputAmount == amount : "The distribute amount that to be fill in the Contents input are not equals to the minimum amount of content amount."
	
	contentsInformation.eachWithIndex { entry, index ->
		def inputObject = findTestObject('Object Repository/Hohh/Plan Page/dyinput_ContentsCalculator_input',
			[('input') : entry.value])
		String inputAmount = listInputAmount[i][index].toString()
		
		WebUI.setText(inputObject, inputAmount)
		
		if (index == listInputAmount[i].size() - 1) {
			WebUI.enhancedClick(confirmationObj)
		}
	}
	
	text = WebUI.getText(totalAmountObj)
	totalAmount = text.split('RM')[-1].trim() 
	
	boolean isClickable = WebUI.verifyElementClickable(confirmAmountObj, FailureHandling.OPTIONAL)
	
	if (!isClickable) {
		errorLog.add("The object '${confirmAmountObj}' is not clickable when it shoudnt be. Amount inputted: ${totalAmount}")
	}	
}

log = errorLog.join("\n")
assert errorLog.isEmpty() : log


