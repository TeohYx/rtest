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
 * 1. Information on Quotation Page
 * 2. Information on Plan Page
 * 3. Information on Application Page
 * 4. Direct to Payment Page
 *
 */

def noDeclaration = findTestObject('Object Repository/Hohh/Declaration Page/dybutton_Declaration_text', 
	[('text') : GlobalVariable.dyobj_ORDP001['noDeclare']])
def proceedReview = findTestObject('Object Repository/Hohh/ORM003_dysubmit_Submit_text',
	[('text') : GlobalVariable.dyobj_Submit_ORM003['application']])
def agreedDeclaration = findTestObject('Object Repository/Hohh/Review Page/button_Declaration')
def agreedMarketing = findTestObject('Object Repository/Hohh/Review Page/button_MarketingConsent')
def payment = findTestObject('Object Repository/Hohh/Review Page/button_FPX')
def toPayment = findTestObject('Object Repository/Hohh/ORM003_dysubmit_Submit_text',
	[('text') : GlobalVariable.dyobj_Submit_ORM003['review']])

String race = GlobalVariable.dummy_PersonalDetailsMYKAD['race']
String idType = GlobalVariable.dummy_PersonalDetailsMYKAD['idType']
String bankName = GlobalVariable.dummy_BankDetails['bankName']

def defaultParams = [
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
	('name') : GlobalVariable.dummy_PersonalDetailsMYKAD['name'],
	('idType') : idType,
	('nric') : GlobalVariable.dummy_PersonalDetailsMYKAD['nric'],
	('nationality') : "",
	('race') : race,
	('phoneNumber') : GlobalVariable.dummy_PersonalDetailsMYKAD['phoneNumber'],
	('email') : GlobalVariable.dummy_PersonalDetailsMYKAD['email'],
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
	('savingAccount') : GlobalVariable.dummy_BankDetails['savingNumber'],
	//Review Page
	('marketing') : false,
	('paymentMethod') : payment,
]

try {
	params = (defaultParams + params)
}
catch (MissingPropertyException e) {
	params = defaultParams
}

WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC004_RM_PF_Direct to Review Page'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(agreedDeclaration)

if (params['marketing']) {
	WebUI.enhancedClick(agreedMarketing)
}

WebUI.enhancedClick(params['paymentMethod'])
WebUI.enhancedClick(toPayment)

WebUI.callTestCase(findTestCase('Hohh/Reusable Module/General Flow/TC007_RM_GF_Make Payment Using FPX'), [:], FailureHandling.STOP_ON_FAILURE)


