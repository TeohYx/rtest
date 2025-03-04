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

/**
 * Fill in information in Hohh quotation with different scenario.
 * 
 * 1. Owner/Tenant
 * 2. Date (leave it by default)
 * 3. Click PropertyType
 * 4. Select Landed/Non-landed
 * 		- If landed, can choose no. of storey
 * 5. Select FullBrick/PartialBrick
 * 6. Select Yes/No for unoccupied
 * 7. Select Yes/No for suffered
 * 
 *
 * @params params [('ownerClass') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class',
						[('text') : GlobalVariable.dyobj_ORQP003['quotationClass'], ('class') : GlobalVariable.dyobj_ORQP001['owner']]),
					('propertyType') : findTestObject('Object Repository/Hohh/Quotation Page/button_PropertyTypeLanded'),
					('numOfStorey') : 1,
					('constructionType') : findTestObject('Object Repository/Hohh/Quotation Page/button_ConstructionTypeFullBrick'),
					('unoccupied') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class',
						[('text') : GlobalVariable.dyobj_ORQP003['unoccupied'], ('class') : GlobalVariable.dyobj_ORQP001['unoccupiedNo']]),
					('suffered') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class',
						[('text') : GlobalVariable.dyobj_ORQP003['suffered'], ('class') : GlobalVariable.dyobj_ORQP001['sufferedNo']])
					]
 */

def defaultParams = [('ownerClass') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class',
							[('text') : GlobalVariable.dyobj_ORQP003['quotationClass'], ('class') : GlobalVariable.dyobj_ORQP001['owner']]),
						('propertyType') : findTestObject('Object Repository/Hohh/Quotation Page/button_PropertyTypeLanded'),
						('numOfStorey') : 1,
						('constructionType') : findTestObject('Object Repository/Hohh/Quotation Page/button_ConstructionTypeFullBrick'),
						('unoccupied') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class',
							[('text') : GlobalVariable.dyobj_ORQP003['unoccupied'], ('class') : GlobalVariable.dyobj_ORQP001['unoccupiedNo']]),
						('suffered') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class',
							[('text') : GlobalVariable.dyobj_ORQP003['suffered'], ('class') : GlobalVariable.dyobj_ORQP001['sufferedNo']])
					]

try {
	params = defaultParams + params
} catch(MissingPropertyException e) {
	params = defaultParams
}

WebUI.callTestCase(findTestCase('Hohh/Reusable Module/General Flow/TC001_RM_GF_QuotationPage Get Quote'), [('params') : params], FailureHandling.STOP_ON_FAILURE)


