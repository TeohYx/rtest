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
 * Verify success direct to Review Page using MYKAD as IDType
 *
 * @pass The page is in review page, where the "Fill Up Details" tab is bolded
 * @fail The page is in review page, where the "Fill Up Details" tab is not bolded
 * 
 * @params params [ 
		//Quotation
	    ('ownerClass') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
			[('text') : GlobalVariable.dyobj_ORQP003['quotationClass'], ('class') : GlobalVariable.dyobj_ORQP001['owner']]), 
		('propertyType') : findTestObject('Object Repository/Hohh/Quotation Page/button_PropertyTypeLanded'), 
		('numOfStorey') : 1, 
		('constructionType') : findTestObject('Object Repository/Hohh/Quotation Page/button_ConstructionTypeFullBrick'), 
		('unoccupied') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
			[('text') : GlobalVariable.dyobj_ORQP003['unoccupied'], ('class') : GlobalVariable.dyobj_ORQP001['unoccupiedNo']]), 
		('suffered') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class', 
	        [('text') : GlobalVariable.dyobj_ORQP003['suffered'], ('class') : GlobalVariable.dyobj_ORQP001['sufferedNo']]), 
		//Plan 
	    ('protectionType') : '', 
		('homeContentCost') : [], 
		//Application
		// Personal Details
	    ('homeBuildingCost') : [], 
		('addons') : '', 
		('name') : GlobalVariable.dummy_PersonalDetailsMYPR['name'], 
		('idType') : idType, 
		('nric') : GlobalVariable.dummy_PersonalDetailsMYPR['nric'], 
		('race') : race, 
		('phoneNumber') : GlobalVariable.dummy_PersonalDetailsMYPR['phoneNumber'], 
		('email') : GlobalVariable.dummy_PersonalDetailsMYPR['email'], 
		('dateOfBirth') : '', 
		// Property Address
		('propertyAddress1') : GlobalVariable.dummy_PropertyDetails1['address1'], 
		('propertyAddress2') : GlobalVariable.dummy_PropertyDetails1['address2'], 
		('propertyCity') : '', 
		('propertyPostcode') : GlobalVariable.dummy_PropertyDetails1['postcode'], 
		// Mailing Address
		('isTick') : false, 
		('mailingAddress1') : GlobalVariable.dummy_MailingAddress1['address1'], 
		('mailingAddress2') : GlobalVariable.dummy_MailingAddress1['address2'], 
		('mailingCity') : '', 
		('mailingPostcode') : GlobalVariable.dummy_MailingAddress1['postcode'],
		// Bank Account
		('bankName') : bankName,
		('savingAccount') : GlobalVariable.dummy_BankDetails['savingNumber']
	]
 * 
 */

def validationObj = findTestObject('Object Repository/Hohh/ORM001_dyvalidation_CurrentPage_text',
	[('text') : GlobalVariable.dyobj_CurrentPage_ORM001['application']])

def params = GlobalVariable.dummy_PersonalDetailsMYPRNonMalaysian

WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC004_RM_PF_Direct to Review Page'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

String elementClass = WebUI.getAttribute(validationObj, 'class')

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert elementClass.contains('fw-bold') : "The page did not redirect to ${GlobalVariable.dyobj_CurrentPage_ORM001['application']}"
