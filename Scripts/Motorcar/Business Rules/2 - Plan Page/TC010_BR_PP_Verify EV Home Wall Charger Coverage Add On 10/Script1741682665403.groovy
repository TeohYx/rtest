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
import com.kms.katalon.core.util.KeywordUtil

/**
 ** EV Home Wall Charger add on only available for electric vehicle.
 * Verify the proper display of New Spare Part add on
 * 1. If vehicle is electric, its add on should present.
 * 2. If vehicle is not electric, its add on should not present.
 *
 * Test case scenario:
 * 1. Electric vehicle
 *
 */

//TestObject addOnSection = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_AddOn', [('addon') : ''])
TestObject addOnObj = null

// Quotation page input button
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')
// Quotation page plate number
//TODO add all 4 scenario, as it is failed for now.
String plateNumberElectric = GlobalVariable.rules_carStatus['electric']



Map plateLists = [
	'positive' : [
			['plateNumber': plateNumberElectric, 'yearMake' : 0]
		]
	]

def errorLog = []

plateLists.each { key, plateList ->
	plateList.each { plate ->
		try {
			plateNumber = plate['plateNumber']
			yearMake = plate['yearMake']
			
			// Input fields and text after the input button
			Map inputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ('inputText') : '900101101010']]]
			
			// Open Application
			WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)
			
			// Quotation Page fill information
			WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('inputButton') : inputButton
					, ('inputScenario') : inputScenario, ('plateNumber') : plateNumber], FailureHandling.STOP_ON_FAILURE)
			
			/*
			 * 1. If testing on positive case (legal), the specific addOn will present
			 */
			if (key == 'negative') {
				
			} else if (key == 'positive') {
				isPresent = WebUI.verifyElementPresent(addOnObj, 3, FailureHandling.OPTIONAL)
				if (!isPresent) {
					errorLog.add("${plate} is electric vehicle but the EV Home Wall Charger addon still absent.")
				}
			}
		} catch (Exception e) {
			errorLog.add("${plate}: Unexpected error occured")
		}

		
		WebUI.takeScreenshot()
		WebUI.closeBrowser()
	}
}

assert errorLog.isEmpty() : KeywordUtil.markError(errorLog.join('\n'))
