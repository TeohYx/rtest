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

proceedButton = findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_Pay Now')

/**
 * Verify if different scenario will give its relevant result
 * 
 * @param buttonAndErrorObject Map The button and its warning message
 * @param buttonObject TestObject The button to tick, so that its warning message will not present
 * 
 */
def inputTick(Map buttonAndErrorObject, def buttonObject) {
	def itemToClick = []
	def warningMessage = []

	for(button in buttonObject) { 
		WebUI.enhancedClick(button)
		
		itemToClick.add(button)
		warningMessage.add(buttonAndErrorObject[button])
		
		buttonAndErrorObject.remove(button)

	}
	
	WebUI.enhancedClick(proceedButton)

	buttonAndErrorObject.each { _, error ->
		WebUI.verifyElementPresent(error, 3)
	}
	
	if(buttonAndErrorObject.isEmpty()) {
		for(warning in warningMessage) {
			println(warning)
			WebUI.verifyElementNotPresent(warning, 3)
		}
	} else {
		for(b in itemToClick) {
			WebUI.enhancedClick(b)
		}
	}
}

/**
 * Check for 4 scenarios:
 * 1. When both does not tick, should show both error (1)
 * 2. When one of it ticked, error should show on the one that didn (2)
 * 3. When both tick, no error is shown (1)
 *
 * @validation Verify if different scenario will give its relevant result
 * @pass Error message not shown when its button that is required is ticked; error message present when its button that is required is not ticked.
 * @fail Error message showing even when its button is ticked; error message not showing even when its button is not ticked.
 */
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC006_RM_PF_Direct to Declaration Page Specific'), [:], FailureHandling.STOP_ON_FAILURE)

TestObject declarationObj = findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_Declaration')
TestObject paymentObj = findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_FPX')
TestObject declarationError = findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/wrnmsg_Declaration Error')
TestObject paymentError = findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/wrnmsg_Payment Error')

Map buttonAndError = [:]
	
buttonAndError[declarationObj] = declarationError
buttonAndError[paymentObj] = paymentError

inputTick(buttonAndError.clone(), [])
inputTick(buttonAndError.clone(), [declarationObj])
inputTick(buttonAndError.clone(), [paymentObj])
inputTick(buttonAndError.clone(), [declarationObj, paymentObj])

WebUI.takeScreenshot()
WebUI.closeBrowser()
