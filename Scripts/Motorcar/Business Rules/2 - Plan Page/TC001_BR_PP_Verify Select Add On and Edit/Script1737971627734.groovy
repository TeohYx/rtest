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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

/*
 * Process to replicate:
 * 1. Fill up all information - Personal Details and Bank Account Details
 * 2. Select all add on
 * 3. Edit one of the motorcycle details
 * 4. Click to Declaration Page
 * 
 * It previously causing unexpected error
 */
def addOn = GlobalVariable.rules_addOn
TestObject dynamicDetailsView = findTestObject('Motorcar and Motorcycle/Plan Page/button_Motorcycle Details View', [('detail_text'): GlobalVariable.obj_plan_detailsView])
String inputEngineNumber = 'F101022222'

// Direct to plan page, and fill in personal as well as bank account details
WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC005_RM_PF_Direct to Plan Page Specific'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Reusable Module/General Flow/TC001_RM_GF_Plan Page Fill In Personal Details'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Reusable Module/General Flow/TC002_RM_GF_Plan Page Fill In Bank Account Details'), [:], 
    FailureHandling.STOP_ON_FAILURE)

// Click on Add On product
for (addon in addOn) {
	WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_AddOn', [('addon') : addon]))
}

WebUI.enhancedClick(dynamicDetailsView)

WebUI.setText(findTestObject('Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/input_Engine Number'), inputEngineNumber)

WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/button_To Declaration Page'))

String classAttribute = WebUI.getAttribute(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/txt_Declaration and Payment'), 'class')

def hasClass = ['fw-bold'].any({ def className ->
        classAttribute.contains(className)
    })

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert hasClass : KeywordUtil.markError('The page is not at declaration page as its text is not bold')

