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

// When verify, check if the inputed text are exactly same, and pass if all of them are false, as well as warning message occurred. 

String[] sample_email = ['yeexian__tyx@hotmail.com', 'yeexian_tyx@@hotmail.com', 'yeexian--tyx@hotmail.com', 'yeexian..tyx@hotmail.com']
String email = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "travellerInfo.email", "EN")

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 3, ('TCplan') : 1, ('TCtrip') : 1
	, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

for (int i=0; i<sample_email.length; i++) {
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))
	
	WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), sample_email[i])
	
	String email_text = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), "value") ?: ""
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

	if (email_text != sample_email[i] && WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/AppPage/Warning Message Text/dywrnmsg_TravellerInfo_travellerInfo', 
		[('travellerInfo') : email]), 3)) {
		WebUI.closeBrowser()
	}
	
	assert email_text == sample_email[i] && WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/AppPage/Warning Message Text/dywrnmsg_TravellerInfo_travellerInfo', 
		[('travellerInfo') : email]), 3)
}


