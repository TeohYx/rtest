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
 ** When over sum insured amount over 600000, it shall direct to error page instead of plan page.
 */

// Quotation page plate number
String plateNumberNormal = GlobalVariable.rules_carStatus['overSumInsured']
def errorPage = GlobalVariable.validation_UnderwritingError['sumInsuredExceed']

def reminderObj = findTestObject('Object Repository/Motorcar and Motorcycle/Reminder Page/dyvalidation_Error_text', [
	('text') : errorPage])

// Open Application
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

// Quotation Page fill information
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('plateNumber'):plateNumberNormal], FailureHandling.STOP_ON_FAILURE)

boolean isPresent = WebUI.verifyElementPresent(reminderObj, 10, FailureHandling.OPTIONAL)

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()

assert isPresent : "The error page is not about exceeding sum insured amount, unable to verify pass"

