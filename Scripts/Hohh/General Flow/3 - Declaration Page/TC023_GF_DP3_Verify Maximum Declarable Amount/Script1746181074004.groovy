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
 * Verify the correct calculation of maximum declarable amount 
 *
 * @pass The Maximum Declarable Amount are 1/3 of the amount stated in Home Content at plan page
 * @fail The Maximum Declarable Amount are not 1/3 of the amount stated in Home Content at plan page
 */

public void enterText(TestObject to, String text) {
	WebUI.clearText(to)
	WebUI.sendKeys(to, Keys.chord(Keys.CONTROL, 'a'));
	WebUI.sendKeys(to, text);
}

String homeContentCost = GlobalVariable.dummy_HomeContentCost[0]

def homeContentInput = findTestObject('Object Repository/Hohh/Plan Page/input_HomeContentEstimateCost')
def elementToClickOut = findTestObject('Object Repository/Hohh/Plan Page/dybox_GetEstimateCostBlock_text',
	[('text') : GlobalVariable.dyobj_ORPP002['contentBlock']])

def toDeclarationPage = findTestObject('Object Repository/Hohh/ORM003_dysubmit_Submit_text',
	[('text') : GlobalVariable.dyobj_Submit_ORM003['plan']])
def yesDeclare = findTestObject('Object Repository/Hohh/Declaration Page/dybutton_Declaration_text',
	[('text') : GlobalVariable.dyobj_ORDP001['yesDeclare']])
def maximumDeclarableObj = findTestObject('Object Repository/Hohh/Declaration Page/dytext_CalculationFigure_title',
	[('title') : GlobalVariable.dyobj_ORDP003['maximumDeclarable']])

WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [:], FailureHandling.STOP_ON_FAILURE)

enterText(homeContentInput, homeContentCost)

WebUI.enhancedClick(elementToClickOut)

WebUI.enhancedClick(toDeclarationPage)
WebUI.enhancedClick(yesDeclare)

WebUI.verifyElementPresent(maximumDeclarableObj, 3)
double maximumDeclarableAmount = WebUI.getText(maximumDeclarableObj).replaceAll("[^\\d.]", "").toDouble()

double calculatedMaximumDeclarable = (homeContentCost.toDouble() / 3).round()

assert calculatedMaximumDeclarable == maximumDeclarableAmount : "The calculated maximum declaration does not same as the displayed amount. Actual: ${calculatedMaximumDeclarable}; Expected: ${maximumDeclarableAmount}"
