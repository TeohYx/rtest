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
 * Fill in information in Hohh quotation with different scenario.
 *
 * 1. Fill in postcode
 * 2. Check on the postcode
 * 3. Select Yes/No for flooding experience
 * 4. Owner/Tenant
 * 5. Date (leave it by default)
 * 6. Click PropertyType
 * 7. Select Landed/Non-landed
 * 		- If landed, can choose no. of storey
 * 8. Select FullBrick/PartialBrick
 * 9. Select Yes/No for unoccupied
 * 10. Select Yes/No for suffered
 *
 */
def defaultParams = [('ownerClass') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
						[('text') : GlobalVariable.dyobj_ORQP003['quotationClass'], ('class') : GlobalVariable.dyobj_ORQP001['owner']]), 
					('propertyType') : findTestObject('Object Repository/Hohh/Quotation Page/button_PropertyTypeLanded'), 
					('numOfStorey') : 1,
					('constructionType') : findTestObject('Object Repository/Hohh/Quotation Page/button_ConstructionTypeFullBrick'), 
					('unoccupied') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
						[('text') : GlobalVariable.dyobj_ORQP003['unoccupied'], ('class') : GlobalVariable.dyobj_ORQP001['unoccupiedNo']]), 
					('suffered') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
						[('text') : GlobalVariable.dyobj_ORQP003['suffered'], ('class') : GlobalVariable.dyobj_ORQP001['sufferedNo']]),
					('postcode'): GlobalVariable.dummy_postcode,
					('experienceFlood'): GlobalVariable.dyobj_ORQP001['experienceFloodNo']
				]

params = defaultParams + params

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)
println(params['ownerClass'])

String postcode = params['postcode']
WebUI.setText(findTestObject('Object Repository/Hohh/Quotation Page/dyinput_postcode_text',
	[('text'): GlobalVariable.dyobj_ORQP003['postcode']]), postcode)
WebUI.enhancedClick(findTestObject('Object Repository/Hohh/Quotation Page/dybutton_checkFlood_text,button',
	[('text'): GlobalVariable.dyobj_ORQP003['postcode'], ('button'): GlobalVariable.dyobj_ORQP001['checkFlood']]))

WebUI.enhancedClick(findTestObject('Object Repository/Hohh/Quotation Page/dybutton_experienceFlood_text,class',
	[('text'): GlobalVariable.dyobj_ORQP003['experienceFlood'], ('class'): params['experienceFlood']]))

WebUI.enhancedClick(params['ownerClass'])
WebUI.enhancedClick(findTestObject('Object Repository/Hohh/Quotation Page/dybutton_getQuotation_text', [('text') : GlobalVariable.dyobj_ORQP001['propertyType']]))
WebUI.enhancedClick(params['propertyType'])

if (WebUI.verifyElementHasAttribute(params['propertyType'], 'checked', 10, FailureHandling.OPTIONAL)) {
	numOfClick = params['numOfStorey'] - 1
	
	while (numOfClick != 0) {
		WebUI.enhancedClick(findTestObject('Object Repository/Hohh/Quotation Page/img_AddStorey'))
		numOfClick -= 1
	}
}

WebUI.enhancedClick(params['constructionType'])
WebUI.enhancedClick(params['unoccupied'])
WebUI.enhancedClick(params['suffered'])

WebUI.enhancedClick(findTestObject('Object Repository/Hohh/ORM003_dysubmit_Submit_text', [('text') : GlobalVariable.dyobj_Submit_ORM003['quotation']]))
WebUI.delay(3)