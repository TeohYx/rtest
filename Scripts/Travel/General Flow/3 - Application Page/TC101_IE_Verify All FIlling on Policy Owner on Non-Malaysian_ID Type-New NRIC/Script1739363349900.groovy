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

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 1, ('TCplan') : 1, ('TCtrip') : 1
	, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_Select Nationality'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_NationalityNorthKorean'))

WebUI.selectOptionByValue(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoIDType'), '2', true)

passport = 'AR123412345'

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), passport)

textName = "TEOH YEE XIAN"

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoFullName'), textName)

textVerify = WebUI.getText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoFullName'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoDateOfBirth'), '28092000')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_Male'))

WebUI.selectOptionByValue(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEthnicity'), '2', true)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), 'yeexian_tyx@hotmail.com')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_MobileNumber'), '1139519168')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoBuildingName'), '602-D, Jalan Air Itam, 11500')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoAreaName'), 'Ayer Itam, Pulau Pinang')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_Postcode'), '11500')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

textVerify2 = WebUI.getText(findTestObject('Travel/TripCare360/English/AppPage/dyytext_TravellerInfo_name', [('name') : passport]))

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert passport == textVerify2 : 'Some information is not filled correctly'
	





