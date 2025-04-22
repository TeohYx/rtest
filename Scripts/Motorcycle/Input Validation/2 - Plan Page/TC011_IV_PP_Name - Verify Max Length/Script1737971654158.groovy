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
Map inputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC')
            , ('inputText') : '900101070110']]]

// Object for plan page
TestObject objectLocator = findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Name')

// Open Application
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

// Quotation Page fill information
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('inputButton') : inputButton
        , ('inputScenario') : inputScenario, ('plateNumber') : plateNumber], FailureHandling.STOP_ON_FAILURE)

// Plan Page
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_Personal Details'))

(maxLength, inputTextLength) = WebUI.callTestCase(findTestCase('Reusable Module/Input Validation/TC001_RM_IV_Obtain Max Length'), 
    [('objectLocator') : objectLocator], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(maxLength, inputTextLength)

WebUI.closeBrowser()

