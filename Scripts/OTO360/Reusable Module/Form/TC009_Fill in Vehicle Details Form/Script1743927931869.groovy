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
 * Vehicle Details Form
 * @author 80012455
 *
 * 1. Info
 * 	- Display vehicle details
 *  - Click on "Continue" button to Plan Page
 *
 * -------------------------------------------------------------------------
 * params = {

   }
 *
 */

Map defaultValues = [ // Original parameter names
	:
]

try {
	params = (defaultValues + params)
}
catch (Exception e) {
	params = defaultValues
}

String proceedPlan = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "submit.toPlan", "EN")
String vehicleEligible = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "text.vehicleEligible", "EN")

WebUI.verifyElementPresent(findTestObject('Object Repository/OTO360/Quotation Page/dyvalidate_quotationForm_text',
	[('text'): vehicleEligible]), 3)

