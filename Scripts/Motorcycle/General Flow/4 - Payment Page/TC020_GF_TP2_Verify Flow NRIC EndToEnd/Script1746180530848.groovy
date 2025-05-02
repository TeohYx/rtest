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
 * Verify the flow of End-to-End motorcar by NRIC selection
 * 
 * @pass The payment text is present; the policy number is present and relevant; the product is present and relevant 
 * @fail One of the pass requirement is fail
 */

// Variable declaration
// Quotation page input button
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')

// Quotation page plate number
String plateNumber = GlobalVariable.rules_carPlate['default']

// Input fields and text after the input button
Map quotationInputScenario = [
	('inputField') : [
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ('inputText') : '900101070110']
		]
	]

// Plan page personal details
Map planPersonalDetailsInputScenario = [
	('inputField') : [
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Name'), ('inputText') : 'Testt'], 
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Email'), ('inputText') : 'yeexian_tyx@hotmail.com'], 
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Mobile Number'), ('inputText') : '1139519168'], 
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Home Address 1'), ('inputText') : 'Testt'], 
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Home Address 2'), ('inputText') : 'Testt'], 
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Postcode'), ('inputText') : '11500']
		], 
	('buttonField') : [
			[('buttonClick') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Ethnicity'), 
				('buttonSelection') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Ethnicity_Chinese')], 
			[('buttonClick') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Marital Status'), 
				('buttonSelection') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Marital Status_Single')]
		]
	]

// Plan page bank details
Map planBankAccountDetailsInputScenario = [
	('inputField') : [
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/4 - Bank Accounts Details/input_Account Number'), ('inputText') : '123412341234']
		], 
	('buttonField') : [
			[('buttonClick') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/4 - Bank Accounts Details/button_Bank Name'), 
				('buttonSelection') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/4 - Bank Accounts Details/button_Bank Name_MayBank')]
		]
	]

// Payment Page details
Map paymentInputScenario = [
	('inputField') : [
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/input_User ID'), ('inputText') : '1111'],
			[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/input_Password'), ('inputText') : '1111']
		],
	]

////// Start here
TestObject paymentMethod = findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_FPX')
	
// Open application
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

// Direct to payment page with given information
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC004_RM_PF_Direct to Payment Page'), 
	[
		('plateNumber') : plateNumber, 
		('quotationInputButton') : inputButton, 
		('quotationInputScenario') : quotationInputScenario, 
		('planPersonalDetailsInputScenario') : planPersonalDetailsInputScenario,
		('planBankAccountDetailsInputScenario') : planBankAccountDetailsInputScenario,
		('paymentInputScenario'): paymentInputScenario,
		('paymentMethod'): paymentMethod
	], FailureHandling.STOP_ON_FAILURE)

isPaymentSuccess = WebUI.verifyElementPresent(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/txt_Payment Success'), 3, FailureHandling.OPTIONAL)

boolean havePolicyNumber = false
if (WebUI.verifyElementPresent(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/txt_Policy Number'), 3, FailureHandling.OPTIONAL)) {
	String policy = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/txt_Policy Number'))
	havePolicyNumber = policy.contains(GlobalVariable.obj_policyNumberPrefix)
}

boolean isCorrectProduct = false
if (WebUI.verifyElementPresent(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/txt_Product'), 3, FailureHandling.OPTIONAL)) {
	String product = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/txt_Product'))
	isCorrectProduct = product == GlobalVariable.obj_product
}

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert isPaymentSuccess && havePolicyNumber && isCorrectProduct : 
	KeywordUtil.markError("""One of the criteria have error:
Present of payment success text: ${isPaymentSuccess}
Correct display of policy number: ${havePolicyNumber}
Correct display of product name: ${isCorrectProduct}""")
	