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
 * Required field to fill in Plan Page: 
 * ## Vehicle Details
 * 1. Name
 * 2. Ethnicity
 * 3. Marital Status
 * 4. Email
 * 5. Mobile Number
 * 6. Home Address1
 * 7. Home Address2
 * 8. Postcode
 * 
 * ## Bank Account Details
 * 9. Bank Name
 * 10. Account Number
 * 
 * @param plateNumber String plate number
 * @param quotationInputButton TestObject The button for ID Type
 * @param quotationInputScenario Map The mapping for the filling on necessary input in order to proceed to plan page
 * 			['inputField' : [['inputObject': , 'inputText': ]], 'buttonField' : [[ 'buttonClick': , 'buttonSelection': ]]] 
 * @param planPersonalDetailsInputScenario Map The mapping for the filling on necessary input in order to proceed to plan page
 * 			['inputField' : [['inputObject': , 'inputText': ]], 'buttonField' : [[ 'buttonClick': , 'buttonSelection': ]]] 
 * @param planBankAccountDetailsInputScenario Map The mapping for the filling on necessary input in order to proceed to plan page
 * 			['inputField' : [['inputObject': , 'inputText': ]], 'buttonField' : [[ 'buttonClick': , 'buttonSelection': ]]] 
 * 
 */

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), 
	[
		('inputButton') : quotationInputButton, 
		('inputScenario') : quotationInputScenario, 
		('plateNumber') : plateNumber
	], FailureHandling.STOP_ON_FAILURE)

// Handle Personal Details information
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_Personal Details'))

planPersonalDetailsInputScenario.each { key, value -> 
	switch(key) {
		case "inputField":
			println("inputfield")
			println(value)
			for (item in value) {
				WebUI.setText(item['inputObject'], item['inputText'])
			}
			break
		case "buttonField":
			println("buttonfield")
			println(value)
			for (item in value) {
				WebUI.enhancedClick(item['buttonClick'])
				WebUI.enhancedClick(item['buttonSelection'])
			}
			break
		default:
			KeywordUtil.markFailed("Unrelevant key in inputScenario: ${key}" )
			return
	}
}

WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Save Personal Details'))

WebUI.delay(1)

// Handle Bank Account Details information
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_Bank Account Details'))

planBankAccountDetailsInputScenario.each { key, value -> 
	switch(key) {
		case "inputField":
			println("inputfield")
			println(value)
			for (item in value) {
				WebUI.setText(item['inputObject'], item['inputText'])
			}
			break
		case "buttonField":
			println("buttonfield")
			println(value)
			for (item in value) {
				WebUI.enhancedClick(item['buttonClick'])
				WebUI.enhancedClick(item['buttonSelection'])
			}
			break
		default:
			KeywordUtil.markFailed("Unrelevant key in inputScenario: ${key}" )
			return
	}
}

WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/4 - Bank Accounts Details/button_Save Bank Details'))

WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_To Declaration Page'))

WebUI.delay(3)


