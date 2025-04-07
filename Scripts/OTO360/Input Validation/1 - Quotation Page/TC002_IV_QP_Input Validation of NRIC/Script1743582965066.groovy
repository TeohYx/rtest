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

String idTypeButton = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "quotation.IDType", "EN")
String proceedPlan = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "submit.toPlan", "EN")

String placeholderText = "E.G. : 900719082777"

def icObj = findTestObject('Object Repository/OTO360/Quotation Page/input_NRIC')

String quotationForm = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "text.quotationForm", "EN")
def warningMessageTrigger = [
		icObj,
		findTestObject('Object Repository/OTO360/Quotation Page/dyvalidate_quotationForm_text',
			[('text'): quotationForm])
	]

String warningText = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "wrnmsg.ic", "EN")
String warningInputTitle = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "detail.ic", "EN")
def warningMessageLocator = findTestObject('Object Repository/General/dywrnmsg_WarningMessage_wrnmsg',
	[('wrnmsg'): warningText])
def warningMessageByInputLocator = findTestObject('Object Repository/General/dywrnmsg_WarningMessageByInputName_detail',
	[('detail'): warningInputTitle])

def element = findTestObject('Object Repository/OTO360/Quotation Page/dybutton_IDType_quotation',
		[('quotation'): idTypeButton])

def validScenario = ['000928070605'] // 1normal, 1minimum edge, 1maximum edge
def invalidScenario = ['00092807060'] // 1 failed minimum, 1 failed maximum

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

// Input Validation
(isPassed, log) = CustomKeywords.'inputValidation.inputValidation.performValidation'(
	icObj,
	[1, 2, 3, 4, 5, 6, 7],
	[
		2: ['placeholderText': placeholderText],
		3: ['allowedType': "N"],
		4: ['warningMessageTrigger': warningMessageTrigger, 'warningMessageLocator': warningMessageLocator, 'expectedWarningText': warningText],
		5: ['invalidType': "S,LL,UL"],
		6: ['haveSpace': false],
		7: ['warningMessageLocator': warningMessageByInputLocator, 'warningMessageTrigger': warningMessageTrigger, 'validScenario': validScenario, 'invalidScenario': invalidScenario]
	])

assert isPassed : log

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()