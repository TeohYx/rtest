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
 * This will direct the page to plan page with:
 * - NRIC selection
 * - IC: 900101070110
 * - plate number: AAA3000 (global variable)
 * - postcode: 11500
 * 
 */

// Variable
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')
String plateNumber = GlobalVariable.rules_carPlate['default']

println(plateNumber)
Map inputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ('inputText') : '900101070110']]]

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('inputButton') : inputButton
	, ('inputScenario') : inputScenario, ('plateNumber') : plateNumber], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Motorcar and Motorcycle/General/text_PageTitle', [('section') : 'Details & Add Ons']), 10)
String classAttribute = WebUI.getAttribute(findTestObject('Motorcar and Motorcycle/General/text_PageTitle', [('section') : 'Details & Add Ons']), 'class')

def hasClass = ['fw-bold'].any({ def className ->
		classAttribute.contains(className)
	})

assert hasClass : "Page does not directed to plan page"