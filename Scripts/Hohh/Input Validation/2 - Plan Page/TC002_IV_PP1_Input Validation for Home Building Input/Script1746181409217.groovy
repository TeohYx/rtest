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

WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [:], FailureHandling.STOP_ON_FAILURE)

def homeBuildingInput = findTestObject('Object Repository/Hohh/Plan Page/input_HomeBuildingEstimateCost')

(isPassed, log) = CustomKeywords.'inputValidation.inputValidation.performValidation'(
	homeBuildingInput, 
	[1, 3, 5, 6], 
	[
		3: ['loadWhenClick': true, 'allowedType': "N"],
		5: ['invalidType': "S, UL, LL"],
		6: ['haveSpace': false]
	])

assert isPassed : log