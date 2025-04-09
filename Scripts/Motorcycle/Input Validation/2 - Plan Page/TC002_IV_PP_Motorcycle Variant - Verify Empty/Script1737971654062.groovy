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

// Quotation page input button
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')
// Quotation page plate number
String plateNumber = GlobalVariable.rules_carPlate['default']
// Input fields and text after the input button
Map inputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ('inputText') : '900101070110']]]
TestObject warningMessage = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/wrnmsg_Motorcycle Variant')
TestObject saveItemLocator = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/button_Save Motorcycle Details')


// Open Application
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

// Quotation Page fill information
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('inputButton') : inputButton
		, ('inputScenario') : inputScenario, ('plateNumber') : plateNumber], FailureHandling.STOP_ON_FAILURE)

// Plan Page
WebUI.verifyElementPresent(findTestObject('Motorcar and Motorcycle/General/text_PageTitle', [('section') : 'Details & Add Ons']), 10)
classAttribute = WebUI.getAttribute(findTestObject('Motorcar and Motorcycle/General/text_PageTitle', [('section') : 'Details & Add Ons']), 'class')

assert classAttribute.contains('fw-bold')

WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/button_Motorcycle Details View', [('detail_text') : GlobalVariable.text_MotorDetails]))

WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/button_Motorcycle Variant Close'))

//WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/button_Save Motorcycle Details'))

String warningText = WebUI.callTestCase(
	findTestCase('Reusable Module/Input Validation/TC002_RM_IV_Obtain Empty Warning Message'), 
	[('objectLocator') : warningMessage, ('saveItemLocator') : saveItemLocator], 
	FailureHandling.STOP_ON_FAILURE
	)

WebUI.verifyEqual(warningText, GlobalVariable.errorMessage_MotorcycleVariant)

WebUI.closeBrowser()

