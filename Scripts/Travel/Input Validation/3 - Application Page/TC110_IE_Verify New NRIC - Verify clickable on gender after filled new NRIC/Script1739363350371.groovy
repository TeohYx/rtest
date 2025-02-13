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
import org.openqa.selenium.support.ui.WebDriverWait

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 3, ('TCplan') : 1, ('TCtrip') : 1
	, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btn_Male'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_PassportOrIdentityNumber'), '000928070605')

previousMaleButtonColor = WebUI.getCSSValue(findTestObject('Travel/TripCare360/English/AppPage/btn_Male'), 'background-color')
previousFemaleButtonColor = WebUI.getCSSValue(findTestObject('Travel/TripCare360/English/AppPage/btn_Female'), 'background-color')

String NRIC = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/text_PassportOrIdentityNumber'), 'value')

int NRICGender = CustomKeywords.'applicationPage.verification.verifyNRICwithGender'(NRIC)

switch(NRICGender) {
	case 0:
		WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btn_Male'))
		
		// To make sure the color rendered correctly
		WebUI.delay(0.5)
		
		maleButtonColor = WebUI.getCSSValue(findTestObject('Travel/TripCare360/English/AppPage/btn_Male'), 'background-color')
		assert previousFemaleButtonColor == GlobalVariable.gender_button_color && maleButtonColor == GlobalVariable.gender_button_color : "Female button does not switch to male button"
		break;
	case 1:
		WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btn_Female'))
		
		// To make sure the color rendered correctly
		WebUI.delay(0.5)
		
		femaleButtonColor = WebUI.getCSSValue(findTestObject('Travel/TripCare360/English/AppPage/btn_Female'), 'background-color')
		assert previousMaleButtonColor == GlobalVariable.gender_button_color && femaleButtonColor == GlobalVariable.gender_button_color : "Male button does not switch to female button"
		break;
}

WebUI.closeBrowser()


