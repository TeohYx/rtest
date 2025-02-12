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
 * Fill in information in Motorcar quotation with different scenario.
 *
 * @param plateNumber String plate number
 * @param inputButton TestObject The button for ID Type
 * @param inputScenario Map The mapping for the filling on necessary input in order to proceed to plan page
 * 			['inputField' : [['inputObject': , 'inputText': ]], 'buttonField' : [[ 'buttonClick': , 'buttonSelection': ]]] 
 */

if (inputButton == null || inputScenario == null) {
	KeywordUtil.markFailed("One of the input are missing in 'findTestCase' line")
	return
}

WebUI.setText(findTestObject('Motorcar and Motorcycle/Quotation Page/input_Plate Number'), plateNumber)

WebUI.setText(findTestObject('Motorcar and Motorcycle/Quotation Page/input_Postcode'), '11500')

WebUI.enhancedClick(inputButton)

inputScenario.each { key, value -> 
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

WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Quotation Page/button_Get Quote Now'))

if (WebUI.verifyElementPresent(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/popup_Additional Coverage'), 3, FailureHandling.OPTIONAL)) {
	WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/button_Additional Coverage No thanks'))
}

WebUI.delay(3)

