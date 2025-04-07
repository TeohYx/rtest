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
 * Renewal Email Form
 * @author 80012455
 *
 * 1. Info
 *  - Display current coverage tab
 *  - Input email
 *  - Tick email promotion
 *  - Click "Remind me" to validation tab
 *
 * -------------------------------------------------------------------------
 * params = {
		emailForRenewal: String (Required)
   }
 *
 */
def infoColumn = 'default'

Map defaultValues = [ // Original parameter names
	('emailForRenewal') : CustomKeywords.'utils.Utility.getOTOInfo'('emailForRenewal', infoColumn)
]

try {
	params = (defaultValues + params)
}
catch (Exception e) {
	params = defaultValues
}

String emailRenewalButton = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "submit.sendEmailRenewal", "EN")
String emailReceivedText = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "text.receivedRequest", "EN")
String vehicleNotEligible = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "text.vehicleNotEligible", "EN")

WebUI.verifyElementPresent(findTestObject('Object Repository/OTO360/Quotation Page/dyvalidate_quotationForm_text',
	[('text'): vehicleNotEligible]), 3)

WebUI.setText(findTestObject('Object Repository/OTO360/Quotation Page/input_email'), params['emailForRenewal'])
WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_submit',
	[('submit'): emailRenewalButton]))

WebUI.verifyElementPresent(findTestObject('Object Repository/OTO360/Quotation Page/dyvalidate_quotationForm_text',
	[('text'): emailReceivedText]), 3)