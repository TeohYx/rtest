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

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 3, ('TCplan') : 1, ('TCtrip') : 1
	, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

//WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), GlobalVariable.alphabet_Smallcase)
WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), '000928070605')

String nric_text = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), "value") ?: ""

String dob_text = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoDateOfBirth'), "value") ?: ""

(date1, date2) = CustomKeywords.'applicationPage.verification.verifyNRICwithDOB'(nric_text, dob_text)

System.out.println(date1 + " and " + date2)

String[] dateMessage = ['day', 'month', 'year']

WebUI.closeBrowser()

for (int i=0; i<date1.length; i++) {
	assert date1[i] == date2[i] : "${dateMessage[i]} does not match"
}

