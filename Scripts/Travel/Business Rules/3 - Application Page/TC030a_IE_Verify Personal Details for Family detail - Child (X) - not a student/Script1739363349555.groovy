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

String ic = '000928070605'
String fullName = 'Teoh Yee Xian'
String ethnicity = 'Chinese'
String email = 'yeexian_tyx@hotmail.com'
String mobile = '01139519168'
String buildingName = 'qweqww'
String areaName = 'qweqeqw'
String postcode = '81800'

String ic2 = '050507012008'
String fullName2 = 'TestChild'

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_SingleTrip'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingCountry'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_AsiaSingleTrip'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/CALANDER DATES/dybutton_Date_date', [('date') : '1']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/CALANDER DATES/dybutton_Date_date', [('date') : '28']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingPerson'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectPlanType_plan', [('plan') : 'family']))

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/Quotation Page/submit_GetQuoteNow'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/submit_Buy'))

WebUI.delay(3)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Fill Personal Details Information'), [
	('TCIC') : ic,
	('TCFullName') : fullName,
	('TCEthnicity') : ethnicity,
	('TCEmail') : email,
	('TCMobileNumber') : mobile,
	('TCBuildingName') : buildingName,
	('TCAreaName') : areaName,
	('TCPostcode') : postcode
	], FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/AppPage/dytext_TravellerInfo_name', [('name') : fullName.toUpperCase()]), 3)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/AppPage/dytext_TravellerInfo_name', [('name') : ic.toUpperCase()]), 3)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_SecondEdit'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_PassportOrIdentityNumber'), ic2)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_FullName'), fullName2.toUpperCase())

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btnUniversityStudentNo'))

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/dropdown_Ethnicity'), 'Indian', false)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/chkIsSameMailingAddress'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

WebUI.takeFullPageScreenshot(FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

