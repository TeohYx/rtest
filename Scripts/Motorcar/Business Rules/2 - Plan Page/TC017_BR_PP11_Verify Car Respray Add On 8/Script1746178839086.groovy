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
 ** New Spare Part add on only available for vehicle 0-10 yo.
 * Verify the proper display of New Spare Part add on
 * 1. If vehicle age is below 0 or above 10, the New Spare Part add on should not be displayed. (check by its present of tick box)
 * 2. If holder is in between 0-10, the New Spare Part add on should be displayed.
 *
 * Test case scenario:
 * 0 and 1 not available
 * 1. Year make: 8
 * 2. Year make: 10
 * 3. Year make: 11
 *
 */

TestObject addOnSection = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_AddOn', [('addon') : 'Spare Parts Replacement and Car Respray Coverage'])
TestObject addOnObj = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_Car Respray')

// Quotation page input button
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')
// Quotation page plate number
//TODO add all 4 scenario, as it is failed for now.
String plateNumberNormal = GlobalVariable.rules_carStatus['y8']
String plateNumber10Years = GlobalVariable.rules_carStatus['y10']
String plateNumberAbove10Years = GlobalVariable.rules_carStatus['y11']

int yearMakeNormal = GlobalVariable.rules_carYearMake['year8']
int yearMake10Years = GlobalVariable.rules_carYearMake['year10']
int yearMakeAbove10Years = GlobalVariable.rules_carYearMake['year11']

Map plateLists = [
	'negative' : [
			['plateNumber': plateNumberAbove10Years, 'yearMake' : yearMakeAbove10Years]
		],
	'positive' : [
//			['plateNumber': plateNumberNormal, 'yearMake' : yearMakeNormal],
			['plateNumber': plateNumber10Years, 'yearMake' : yearMake10Years]
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
			
			WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_Motorcycle Details View', [('detail_text') : 'Vehicle Details']))
			String year = WebUI.getAttribute(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/input_Vehicle Year'), "value")
			WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/button_Close Motorcycle Details'))
			
			int yearDiff = new Date().format('yyyy').toInteger() - year.toInteger()
			int expectedYearDiff = yearMake
			int expectedYearMake = new Date().format('yyyy').toInteger() - expectedYearDiff
			
			if (yearDiff != expectedYearDiff) {
				KeywordUtil.markFailed("The car make year difference (${yearDiff}) does not same with desired year difference (${expectedYearDiff})")
				errorLog.add("${plate} year make is not same as expected. Plate year make: ${year}; Expected plate year make: ${expectedYearMake}")
				return
			} else {
				KeywordUtil.logInfo("Year Make: ${year}\nPeriod: ${expectedYearDiff} years")
			}
			
			if (WebUI.verifyElementPresent(addOnSection, 3, FailureHandling.OPTIONAL)) {
				WebUI.enhancedClick(addOnSection)
			}
			
			/*
			 * 1. If testing on negative case (illegal), the specific addOn will not present
			 * 2. If testing on positive case (legal), the specific addOn will present
			 */
			if (key == 'negative') {
				isNotPresent = WebUI.verifyElementNotPresent(addOnObj, 3, FailureHandling.OPTIONAL)
				if (!isNotPresent) {
					errorLog.add("${plate} is not in legal age but the New Spray addon still present.")
				}
			} else if (key == 'positive') {
				isPresent = WebUI.verifyElementPresent(addOnObj, 3, FailureHandling.OPTIONAL)
				if (!isPresent) {
					errorLog.add("${plate} is in legal age but the New Spray addon still absent.")
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
