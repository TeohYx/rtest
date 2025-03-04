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

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_SingleTrip'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingCountry'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectCountryArea_area', [('area') : 'Domestic']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : '1']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : '28']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingPerson'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectPlanType_plan', [('plan') : 'Just myself']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/submit_GetQuoteNow'))

WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/Plan Page/button_SilverPlan'), 0)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/button_SilverPlan'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/dysubmit_Buy_title', [('title') : GlobalVariable.dyobj_Buy]))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), '980421045116')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoFullName'), 'TestingName')

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEthnicity'), 'Indian', false)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), 'krisnavenii.rrajan@etiqa.com.my')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_MobileNumber'), '137447138')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoBuildingName'), 'Testing')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoAreaName'), 'Testing')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_Postcode'), '81300')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

