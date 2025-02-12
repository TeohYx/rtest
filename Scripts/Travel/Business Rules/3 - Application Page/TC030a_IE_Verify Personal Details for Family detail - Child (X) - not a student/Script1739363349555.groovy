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

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_AsiaSingleTrip'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/CALANDER DATES/dybutton_Date_date'))

WebUI.enhancedClick(findTestObject('TripCare360/English/CALANDER DATES/btn_Date28'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingPerson'))

WebUI.enhancedClick(findTestObject('TripCare360/English/Quoation Page/btn_With my family'))

WebUI.enhancedClick(findTestObject('TripCare360/English/Quoation Page/btnGet Quote Now'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/submit_Buy'))

WebUI.delay(3)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_PassportOrIdentityNumber'), '980421045116')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_FullName'), 'testing')

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/dropdown_Ethnicity'), 'Indian', false)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_Email'), 'krisnavenii.rrajan@etiqa.com.my')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_MobileNumber'), '0137447138')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_BuildingName'), 'Testing')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_AreaName'), 'Testing')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_Postcode'), '81800')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/a_EDIT_Child1'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_PassportOrIdentityNumber'), '090507012008')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_FullName'), 'TestChild')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btnUniversityStudenNo'))

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/dropdown_Ethnicity'), 'Indian', false)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/chkIsSameMailingAddress'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btn_selectBank'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_BankName_Malayan Banking Berhad'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_BankAccountNumber'), '123456789012')

WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'), 0)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'))

WebUI.delay(3)

WebUI.takeFullPageScreenshot(FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

