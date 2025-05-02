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
 * Verify if the filled information on quotation page is corrctly displayed in plan page
 * 
 * @pass The filled information is same as in plan page
 * @fail They have different amount
 */

// Quotation page input button
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')
// Quotation page plate number
String plateNumber = GlobalVariable.rules_carPlate['default']
String inputedPostcode = '11500'
// Input fields and text after the input button
Map inputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC'), ('inputText') : '900101070110']]]

// Open Application
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

// Quotation Page fill information
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('inputButton') : inputButton
		, ('inputScenario') : inputScenario, ('plateNumber') : plateNumber, ('postcode') : inputedPostcode], FailureHandling.STOP_ON_FAILURE)

boolean isPlateNumberTally = plateNumber == WebUI.getText(findTestObject('Motorcar and Motorcycle/Plan Page/txt_Vehicle Details_Plate Number', [('vehicle_detail'): 'Vehicle Details', ('plate_number'): 'Plate Number']))

boolean isPostcodeTally = inputedPostcode == WebUI.getText(findTestObject('Motorcar and Motorcycle/Plan Page/txt_Vehicle Details_Postcode', [('vehicle_detail'): 'Vehicle Details', ('vehicle_postcode'): 'Vehicle Postcode']))

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert isPlateNumberTally && isPostcodeTally

