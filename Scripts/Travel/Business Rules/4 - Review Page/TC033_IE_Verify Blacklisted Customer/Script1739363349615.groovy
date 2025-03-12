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

def nameAndIC = GlobalVariable.rules_blacklistedCustomer
String ethnicity = 'Chinese'
String email = 'yeexian_tyx@hotmail.com'
String mobile = '01139519168'
String buildingName = 'qweqww'
String areaName = 'qweqeqw'
String postcode = '81800'

def errorLog = []

for (info in nameAndIC) {
	def params = [('TCIC') : info.value, ('TCFullName') : info.key
		, ('TCEthnicity') : ethnicity, ('TCEmail') : email, ('TCMobileNumber') : mobile, ('TCBuildingName') : buildingName
		, ('TCAreaName') : areaName, ('TCPostcode') : postcode]
	
	int planType = CustomKeywords.'inputValidation.validation.assignPlanTypeBasedOnIC'(info.value)
	println(info.value)
	println(planType)
	WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 2, ('TCplan') : planType, ('TCtrip') : 1
		, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))
	
	WebUI.callTestCase(findTestCase('Travel/Reusable Module/Fill Personal Details Information Overload'), [('TCparams') : params], FailureHandling.STOP_ON_FAILURE)
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_BankName'))
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_BankName_Malayan Banking Berhad'))
	
	WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_BankAccountNumber'), '123456789012')
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'))
	WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Review Page/rdoFPXPayment'), 3)
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/rdoFPXPayment'))
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/button_Declaration'))
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/dysubmit_ProceedPayment_name', [('name') : GlobalVariable.dyobj_ProceedPayment]))
	TestObject objVerification = findTestObject('Object Repository/Travel/TripCare360/English/Review Page/validation_BlacklistSite')
	
	//v
	boolean isPresent = WebUI.verifyElementPresent(objVerification, 10, FailureHandling.OPTIONAL)
	
	String url = WebUI.getUrl()
	boolean isEqual = WebUI.verifyEqual(url, GlobalVariable.rules_blacklistSiteRedirection, FailureHandling.OPTIONAL)
	
	if (!isPresent){
		errorLog.add("(${info.key}, ${info.value}): Validation of blacklist site not appearing.")
	} else if (!isEqual) {
		errorLog.add("(${info.key}, ${info.value}): The blacklist site is validated, but it is not directed to proper url. Directed url: ${url}")
	}
	
	WebUI.closeBrowser()
}

String error = errorLog.join("\n")
assert errorLog.isEmpty() : error
