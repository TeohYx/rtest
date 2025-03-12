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
 * 18-80 
 * 
 */

String ageRange = GlobalVariable.rules_Holder
resultCase = CustomKeywords.'utils.Utility.obtainAgeEdgeCaseAgeToICRange'(ageRange)

def warningMessage = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/wrnmsg_NRIC')

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)


def errorLog = []
resultCase.each { cases, ics ->
	ics.each { ic ->
		
		WebUI.setText(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ic)
		
		WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/button_Get Quote Now'))
		
		
		if (cases == 'positive') {
			boolean isNotPresent = WebUI.verifyElementNotPresent(warningMessage, 3, FailureHandling.OPTIONAL)
			
			if (!isNotPresent) {
				String text = WebUI.getText(warningMessage)
				
				errorLog.add("${cases} cases. Error present with valid IC (${ic}). Warning message: ${text}")
			}
		} else if (cases == 'negative') {
			boolean isPresent = WebUI.verifyElementPresent(warningMessage, 3, FailureHandling.OPTIONAL)
			
			if (!isPresent) {
				errorLog.add("${cases} cases. Error not present with invalid IC (${ic})")
			}
		} else {
			errorLog.add("Unknown case: ${cases}; IC: ${ic}")
		}
	}
}

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()

println(resultCase)
String log = errorLog.join('\n')
assert errorLog.isEmpty() : log
