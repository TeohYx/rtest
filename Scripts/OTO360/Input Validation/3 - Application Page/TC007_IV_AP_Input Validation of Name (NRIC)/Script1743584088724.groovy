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

String placeholderText = "E.G. : ADAM BIN BAKRI"

def inputObj = findTestObject('Object Repository/OTO360/Quotation Page/input_NRIC')

String toReview = CustomKeywords.'utils.Utility.getDynamicRepoInfo'('OTO360', 'submit.toReview', 'EN')
def warningMessageTrigger = findTestObject('Object Repository/OTO360/General/dybutton_submit_submit', [('submit') : toReview])

String warningInputTitle = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "detail.name", "EN")
def warningMessageByInputLocator = findTestObject('Object Repository/General/dywrnmsg_WarningMessageByInputName_detail',
	[('detail'): warningInputTitle])

def validScenario = ['Teoh Yee Xian'] // 1normal, 1minimum edge, 1maximum edge
def invalidScenario = ['Teo'] // 1 failed minimum, 1 failed maximum

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC003_RM_PF_Direct to Application Page'), [:], FailureHandling.STOP_ON_FAILURE)

// Input Validation
(isPassed, log) = CustomKeywords.'inputValidation.inputValidation.performValidation'(
	inputObj,
	[1, 2, 3, 4, 5, 6, 7],
	[
		2: ['placeholderText': placeholderText],
		3: ['allowedType': "LL, UL"],
		4: ['warningMessageTrigger': warningMessageTrigger, 'warningMessageLocator': warningMessageByInputLocator],
		5: ['invalidType': "S, N", 'allowedSymbol': "@-'/.,"],
		6: ['haveSpace': true],
		7: ['warningMessageLocator': warningMessageByInputLocator, 'warningMessageTrigger': warningMessageTrigger, 'validScenario': validScenario, 'invalidScenario': invalidScenario]
	])

assert isPassed : log

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()