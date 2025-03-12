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
	
String ageRange = GlobalVariable.rules_Holder
String addOnRange = GlobalVariable.rules_OTO['holder']

ageLists = CustomKeywords.'utils.Utility.obtainAgeEdgeCaseAgeToICRange'(addOnRange, [('returnType'):3])
holderRange = CustomKeywords.'utils.Utility.obtainAgeRange'(ageRange)
println(holderRange)
TestObject addOnObj = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_AddOn', [('addon') : 'Accident Protection for You and Your Passenger'])

def errorLog = []

println(ageLists)

ageLists.each { key, ageList -> 
	println(ageList.getClass())
	ageList.each { result -> 
		def cleanedData = result[1..-2] // Removes "[" and "]"
		def map = [:]
		cleanedData.split(", ").each { pair ->
			def (k, value) = pair.split(":")
			map[k] = value.isInteger() ? value.toInteger() : value
		}
		
		String NRIC = map['ic']
		int age = map['age']
		
		// Input fields and text after the input button
		Map inputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ('inputText') : NRIC]]]
		
		// Open Application
		WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)
		
		// Quotation Page fill information
		WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [
				('inputScenario') : inputScenario], FailureHandling.STOP_ON_FAILURE)
		
		/*
		 * 1. If underage, the user cannot surpass quotation page, as the NRIC will have error message
		 * 2. If testing on negative case (illegal), the specific addOn will not present
		 * 3. If testing on positive case (legal), the specific addOn will present
		 */
		if (key == 'negative') {
			if(age < holderRange['minimum'] || age >= holderRange['maximum']) {
				isError = WebUI.verifyElementPresent(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/wrnmsg_NRIC'), 3, FailureHandling.OPTIONAL)
				if (!isError) {
					errorLog.add("${NRIC} is not eligible for purchase but it is not blocked during Quotation page")
				}
			} else {
				isNotPresent = WebUI.verifyElementNotPresent(addOnObj, 3, FailureHandling.OPTIONAL)
				if (!isNotPresent) {
					errorLog.add("${NRIC} is not in legal age but the OTO360 still present.")
				}
			}
		} else if (key == 'positive') {
			isPresent = WebUI.verifyElementPresent(addOnObj, 3, FailureHandling.OPTIONAL)
			if (!isPresent) {
				errorLog.add("${NRIC} is in legal age but the OTO360 still absent.")
			}
		}
		
		WebUI.takeScreenshot()
		WebUI.closeBrowser()
	}
}
println(ageLists)
assert errorLog.isEmpty() : errorLog.join('\n')
