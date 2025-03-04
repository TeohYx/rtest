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

// After fill in the input, check if the text contains space. 
// If no space, plus no warning message present, test pass.

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 3, ('TCplan') : 1, ('TCtrip') : 1
	, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), "yeexian_tyx @hotmail.com")

String email_text = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), "value") ?: ""

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

WebUI.closeBrowser()

assert !email_text.contains(" ")