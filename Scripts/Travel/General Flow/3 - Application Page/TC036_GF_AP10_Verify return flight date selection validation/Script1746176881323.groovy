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

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 2, ('TCplan') : 1, ('TCtrip') : 1
        , ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Fill Personal Details Information'), [('TCIC') : ic, ('TCFullName') : fullName
        , ('TCEthnicity') : ethnicity, ('TCEmail') : email, ('TCMobileNumber') : mobile, ('TCBuildingName') : buildingName
        , ('TCAreaName') : areaName, ('TCPostcode') : postcode], FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/AppPage/input_DepartureFlightNumber'), 0)

WebUI.delay(1)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_DepartureFlightNumber'), 'MH370')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/input_DepartureFlightDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : '1']))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_ReturnFlightNumber'), 'MH17')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/input_ReturnFlightDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : '28']))

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

