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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

/**
 * Verify the correct direct when click on 'Yes, I want to declare' in declaration page.
 *
 * @pass The 'Fill in the details of your declarable items' are present
 * @fail The 'Fill in the details of your declarable items' are not present
 */

def noDeclare = findTestObject('Object Repository/Hohh/Declaration Page/dybutton_Declaration_text',
	[('text') : GlobalVariable.dyobj_ORDP001['noDeclare']])
def validateApplicationPage = findTestObject('Object Repository/Hohh/ORM001_dyvalidation_CurrentPage_text',
	[('text') : GlobalVariable.dyobj_CurrentPage_ORM001['application']])

WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC003_RM_PF_Direct to Declaration Page'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(noDeclare)

String elementClass = WebUI.getAttribute(validateApplicationPage, 'class')

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert elementClass.contains('fw-bold') : "The page did not redirect to ${GlobalVariable.dyobj_CurrentPage_ORM001['application']}"



