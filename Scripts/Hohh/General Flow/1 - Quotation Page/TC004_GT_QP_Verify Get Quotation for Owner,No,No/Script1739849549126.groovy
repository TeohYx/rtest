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
 * Test for the flow on quotation page with Owner -> Yes for unoccupied for 90days -> Yes for suffered
 *
 * @params: Map params
 * 				[('ownerClass') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
						[('text') : 'text', ('class') : 'class']), 
					('propertyType') : findTestObject('Object Repository/Hohh/Quotation Page/button_PropertyTypeLanded'), 
					('numOfStorey') : 1,
					('constructionType') : findTestObject('Object Repository/Hohh/Quotation Page/button_ConstructionTypeFullBrick'), 
					('unoccupied') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
						[('text') : 'text', ('class') : 'class']), 
					('suffered') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
						[('text') : 'text', ('class') : 'class'])
				]
 *
 *
 */

def params = [('ownerClass') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
        [('text') : GlobalVariable.dyobj_ORQP003['quotationClass'], ('class') : GlobalVariable.dyobj_ORQP001['owner']])
			, ('unoccupied') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
        [('text') : GlobalVariable.dyobj_ORQP003['unoccupied'], ('class') : GlobalVariable.dyobj_ORQP001['unoccupiedNo']])
			, ('suffered') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
        [('text') : GlobalVariable.dyobj_ORQP003['suffered'], ('class') :  GlobalVariable.dyobj_ORQP001['sufferedNo']])]
			
def validationObj = findTestObject('Object Repository/Hohh/ORM001_dyvalidation_CurrentPage_text',
	[('text') : GlobalVariable.dyobj_CurrentPage_ORM001['plan']])
				
//Start
WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

String elementClass = WebUI.getAttribute(validationObj, 'class')

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert elementClass.contains('fw-bold') : "The page did not redirect to ${GlobalVariable.dyobj_CurrentPage_ORM001['plan']}"

