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
 ** Extreme Weather Coverage add on available for all holder, means holder of 18-80yo; vehicle car of 1-20yo
 *
 * Test case scenario:
 * 1. Year make: 10 (Positive)
 *
 */

TestObject addOnObj = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_CoverageForDamagesAndInjuries')

TestObject CARTbutton = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_CART')
TestObject LOUbutton = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_LOU')

//TestObject smartKeyValidation = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/dyvalidation_QuotationDetailsText_text',
//	[('text'): GlobalVariable.dyvalidation_addOns['smartKey']])
//TestObject childCarValidation = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/dyvalidation_QuotationDetailsText_text',
//	[('text'): GlobalVariable.dyvalidation_addOns['childCar']])

// Quotation page input button
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')

// Quotation page plate number
String plateNumberNormal = GlobalVariable.rules_carStatus['y10']

int yearMakeNormal = GlobalVariable.rules_carYearMake['year10']

Map plateLists = [
	'positive' : [
			['plateNumber': plateNumberNormal, 'yearMake' : yearMakeNormal]
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
			
			if (WebUI.verifyElementPresent(addOnObj, 3, FailureHandling.OPTIONAL)) {
				WebUI.enhancedClick(addOnObj)
			}
			
			/*
			 * 1. If testing on negative case (illegal), the specific addOn will not present
			 * 2. If testing on positive case (legal), the specific addOn will present
			 */
			if (key == 'negative') {
				isSmartKeyNotPresent = WebUI.verifyElementNotPresent(CARTbutton, 3, FailureHandling.OPTIONAL)
				isChildCarNotPresent = WebUI.verifyElementNotPresent(LOUbutton, 3, FailureHandling.OPTIONAL)
				
				if (!isSmartKeyNotPresent || !isChildCarNotPresent) {
					errorLog.add("${plate} is not in legal holder but the addon still absent.")
				}
				
			} else if (key == 'positive') {
				isSmartKeyPresent = WebUI.verifyElementPresent(CARTbutton, 3, FailureHandling.OPTIONAL)
				isChildCarPresent = WebUI.verifyElementPresent(LOUbutton, 3, FailureHandling.OPTIONAL)
				
				if (!isSmartKeyPresent || !isChildCarPresent) {
					errorLog.add("${plate} is in legal holder but the Coverage for Harsh Weather Damage addon still absent.")
				}
			}
		} catch (Exception e) {
			errorLog.add("${plate}: Unexpected error occured")
		}
		
		WebUI.takeScreenshot()
		WebUI.closeBrowser()
	}
}

assert errorLog.isEmpty() : errorLog.join('\n')
