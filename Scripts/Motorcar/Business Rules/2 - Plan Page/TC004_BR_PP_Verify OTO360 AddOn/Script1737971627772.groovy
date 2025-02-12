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
 ** OTO360 add on only available for 18-70 yo holder.
 * Verify the proper display of OTO360
 * 1. If holder is below 18 or above 70, the OTO360 should not be displayed. (check by its present of tick box)
 * 2. If holder is in between 18-70, the OTO360 should be displayed.
 * 
 */

// Quotation page input button

def randomAge = new Random()

int minimumAge = GlobalVariable.rules_HolderAge['minimum']
int maximumAge = GlobalVariable.rules_HolderAge['maximum']

// Method : Random
//int underAge = 1 + randomAge.nextInt(minimumAge - 1) // 1 - 17
//int legalAge = minimumAge + randomAge.nextInt(maximumAge - minimumAge + 1) // 18 - 80
//int aboveAge = maximumAge + randomAge.nextInt(30) // 81 - 110

// Method : Edge case
int minimumFailedAge = minimumAge - 1
int minimumPassedAge = minimumAge
int maximumFailedAge = maximumAge + 1
int maximumPassedAge = maximumAge

Map ageLists = [
		'illegal' : [minimumFailedAge, maximumFailedAge],
		'legal' : [minimumPassedAge, maximumPassedAge]
	]

TestObject addOnObj = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_AddOn', [('addon') : 'Accident Protection for You and Your Passenger'])
	
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')

// Quotation page plate number
String plateNumber = GlobalVariable.rules_carPlate['default']

def errorLog = []

ageLists.each { key, ageList -> 
	ageList.each { age -> 
		def edgeCalendar = Calendar.getInstance()
		edgeCalendar.add(Calendar.YEAR, -age)
		ageDate = edgeCalendar.clone()
		
		NRIC = CustomKeywords.'inputValidation.validation.inputNRICandProceed'(ageDate)
		
		// Input fields and text after the input button
		Map inputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ('inputText') : NRIC]]]
		
		// Open Application
		WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)
		
		// Quotation Page fill information
		WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('inputButton') : inputButton
				, ('inputScenario') : inputScenario, ('plateNumber') : plateNumber], FailureHandling.STOP_ON_FAILURE)
		
		/*
		 * 1. If underage, the user cannot surpass quotation page, as the NRIC will have error message
		 * 2. If testing on negative case (illegal), the specific addOn will not present
		 * 3. If testing on positive case (legal), the specific addOn will present
		 */
		if (age < minimumAge) {
			isError = WebUI.verifyElementPresent(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/wrnmsg_NRIC'), 3, FailureHandling.OPTIONAL)
			if (!isError) {
				errorLog.add("${NRIC} is below age but it is not blocked during Quotation page")
			}
		} else if (key == 'illegal') {
			isNotPresent = WebUI.verifyElementNotPresent(addOnObj, 3, FailureHandling.OPTIONAL)
			if (!isNotPresent) {
				errorLog.add("${NRIC} is not in legal age but the OTO360 still present.")
			}
		} else if (key == 'legal') {
			isPresent = WebUI.verifyElementPresent(addOnObj, 3, FailureHandling.OPTIONAL)
			if (!isPresent) {
				errorLog.add("${NRIC} is in legal age but the OTO360 still absent.")
			}
		}
		
		WebUI.takeScreenshot()
		WebUI.closeBrowser()
	}
}

assert errorLog.isEmpty() : KeywordUtil.markError(errorLog.join('\n'))
