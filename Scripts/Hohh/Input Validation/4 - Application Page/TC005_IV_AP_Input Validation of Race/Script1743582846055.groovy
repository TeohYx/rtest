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

def noDeclare = findTestObject('Object Repository/Hohh/Declaration Page/dybutton_Declaration_text',
	[('text') : GlobalVariable.dyobj_ORDP001['yesDeclare']])

def raceInput = findTestObject('Object Repository/Hohh/Application Page/dydropdown_Details_text',
	[('text') : GlobalVariable.dyobj_ORAP001['race']])
def warningMessageTrigger = findTestObject('Object Repository/Hohh/ORM003_dysubmit_Submit_text',
	[('text'): GlobalVariable.dyobj_Submit_ORM003['application']])
def warningMessageLocator = findTestObject('Object Repository/Hohh/Application Page/dywarningmessage_Input_text',
	[('text') : GlobalVariable.inputValidation_InputTitle['race']])

String emptyWarningMessage = GlobalVariable.inputValidation_Empty['race']

WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC003_RM_PF_Direct to Declaration Page'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(noDeclare)

// Input Validation
(isPassed, log) = CustomKeywords.'inputValidation.inputValidation.performValidation'(
	raceInput,
	[1, 4],
	[
		4: ['warningMessageTrigger': warningMessageTrigger, 'warningMessageLocator': warningMessageLocator, 'expectedWarningText': emptyWarningMessage],
	])

assert isPassed : log