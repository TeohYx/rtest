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
 * This will direct the page to declaration page with:
 * ** Quotation Page
 * - NRIC selection
 * - IC: 900101070110
 * - plate number: AAA3000 (global variable)
 *
 * ** Plan Page
 * * Personal Details
 * - Name: Tan Sing Ma
 * - Email: yeexian_tyx@hotmail.com
 * - Ethnicity: Chinese
 * - Marital Status: Single
 * - Phone: 1139519168
 * - Address1: qwert
 * - Address2: 12345
 * - Postcode: 11500
 *
 * * Bank Account Details
 * - Bank Account: Maybank
 * - Bank Number: 123412341234
 * 
 * * Payment Details
 * - UserID: 1111
 * - Password: 1111
 */

// Variable
//TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')

//String plateNumber = GlobalVariable.rules_carPlate['default']

//Map inputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ('inputText') : '900101070110']]]

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('inputButton') : quotationInputButton
		, ('inputScenario') : quotationInputScenario, ('plateNumber') : plateNumber], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Reusable Module/General Flow/TC001_RM_GF_Plan Page Fill In Personal Details'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Reusable Module/General Flow/TC002_RM_GF_Plan Page Fill In Bank Account Details'), [:],
	FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_To Declaration Page'))
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_Declaration'))
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_FPX'))
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_Pay Now'))
WebUI.delay(3)

// Handle Payment steps
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/button_PayNet'))
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/button_Continue'))

WebUI.setText(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/input_User ID'), '1111')
WebUI.setText(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/input_Password'), '1111')

WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/button_Sign In'))
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/button_Confirm'))
WebUI.delay(10) // To wait the auto redirection to Transaction page

WebUI.delay(3)
