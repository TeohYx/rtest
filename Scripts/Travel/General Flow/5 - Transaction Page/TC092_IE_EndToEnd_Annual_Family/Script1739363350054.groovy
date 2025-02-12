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

// Quotation
WebUI.callTestCase(findTestCase('Travel/Reusable Module/Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_AnnualTrip'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingCountry'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectCountryArea_area', [('area') : 'Domestic']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/CALANDER DATES/dybutton_Date_date', [('date') : '1']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/CALANDER DATES/dybutton_Date_date', [('date') : '28']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingPerson'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectPlanType_plan', [('plan') : 'family']))

WebUI.enhancedClick(findTestObject('Object Repository/TripCare360/English/Quoation Page/btnGet Quote Now'))
WebUI.delay(1)
//Plan
WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/Plan Page/button_SilverPlan'), 0)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/button_SilverPlan'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/submit_Buy'))

//Application
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_PassportOrIdentityNumber'), '980421045116')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_FullName'), 'TestingName')

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/dropdown_Ethnicity'), 'Indian', false)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_Email'), 'krisnavenii.rrajan@etiqa.com.my')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_MobileNumber'), '137447138')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_BuildingName'), 'Testing')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_AreaName'), 'Testing')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_Postcode'), '81300')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

//child

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/a_EDIT_Child1'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_PassportOrIdentityNumber'), '050507012008')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_FullName'), 'TestChild')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btnUniversityStudentYes'))

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/dropdown_Ethnicity'), 'Indian', false)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/chkIsSameMailingAddress'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'), 0)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'))


// Review
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/rdoFPXPayment'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/chkDeclaration'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/btnProceed Payment'))

// Payment
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/btn_PayNet Option'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_Continue MPay'))

WebUI.setText(findTestObject('Travel/TripCare360/English/Payment Page/txt_UserId'), '1111')

WebUI.setText(findTestObject('Travel/TripCare360/English/Payment Page/txt_Password'), '1111')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/btn_Sign in'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/btnn_Confirm'))

WebUI.delay(5)

// Transaction
WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/lbl_Your Payment is Successful'), 3)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/lbl_Policy Number'), 3)

WebUI.delay(3)

WebUI.takeFullPageScreenshot(FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()