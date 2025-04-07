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

/**
 * Make Payment Form
 * @author 80012455
 *
 * 1. Info
 * - Select PayNet option
 * - Click on continue
 * 
 * 2. Login detail
 * - Fill in user id
 * - Fill in passport
 * 	2.1 Success
 * 		- Click on sign in
 * 	2.2 Fail
 * 		- Click on cancel
 * 3. Confirm page
 * - Click on confirm
 *
 * -------------------------------------------------------------------------
 * Data - By the default the data is set to not success
 *
 * params = {
		  toSuccess: Boolean (required)
   }
 *
 */


Map defaultValues = [ // Original parameter names
		// Set to false as once OTO360 has been purchased, the vehicle number will recorded to as purchased and unable to perform quotation in next attempt
		('toSuccess'): false
	]

try {
	params = (defaultValues + params)
}
catch (Exception e) {
	params = defaultValues
}

String userName = CustomKeywords.'utils.Utility.getCustomInfoRowFromColumn'("BankInfo", "userId", "Set1")
String password = CustomKeywords.'utils.Utility.getCustomInfoRowFromColumn'("BankInfo", "password", "Set1")

WebUI.enhancedClick(findTestObject('Object Repository/General/Make Payment/button_PayNetOption'))

WebUI.enhancedClick(findTestObject('Object Repository/General/Make Payment/button_ContinueMPay'))

// As this is third party site, using hard code is fine due to the must be in English
if (params['toSuccess']) {
	WebUI.setText(findTestObject('Object Repository/General/Make Payment/input_UserId'), userName)
	
	WebUI.setText(findTestObject('Object Repository/General/Make Payment/input_Password'), password)
	
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_submit',
		[('submit'): 'Sign in']))
	WebUI.enhancedClick(findTestObject('Object Repository/General/Make Payment/button_Confirm'))
	WebUI.delay(10)
} else {
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_button',
		[('button'): 'Cancel']))
	if (WebUI.verifyElementPresent(findTestObject('Object Repository/OTO360/General/button_completeTransaction'), 3, FailureHandling.OPTIONAL)) {
		WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/button_completeTransaction'))
	}
	
	WebUI.delay(3)
}




