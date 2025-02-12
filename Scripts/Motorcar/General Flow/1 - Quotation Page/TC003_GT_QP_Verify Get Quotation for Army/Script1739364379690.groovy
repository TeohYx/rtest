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
 * Test for the flow on quotation page with Army ID
 *
 */

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:])

WebUI.setText(findTestObject('Motorcar and Motorcycle/Quotation Page/input_Plate Number'), GlobalVariable.rules_carPlate['default'])
WebUI.setText(findTestObject('Motorcar and Motorcycle/Quotation Page/input_Postcode'), GlobalVariable.Postcode)
WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Quotation Page/label_ID Type_Army'))

WebUI.setText(findTestObject('Motorcar and Motorcycle/Quotation Page/input_NRIC'), GlobalVariable.InsuredNRIC)

WebUI.setText(findTestObject('Motorcar and Motorcycle/Quotation Page/input_Army ID Number'), 'RF/123456')
WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Quotation Page/button_Get Quote Now'))

String classAttribute = WebUI.getAttribute(findTestObject('Motorcar and Motorcycle/General/text_PageTitle', [('section') : 'Details & Add Ons']), 'class')

def hasClass = ['fw-bold'].any({ def className ->
		classAttribute.contains(className)
	})

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert hasClass : 'The page is not at declaration page as its text is not bold'
