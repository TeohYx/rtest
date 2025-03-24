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

String area = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "area.4", "EN")
String planType = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "planType.4", "EN")
String buttonPlan = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "button.toPlanPage", "EN")
String buttonBuy = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "button.buy", "EN")
String buttonPayment = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "button.proceedPayment", "EN")
String universityStudent = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "travellerInfo.universityStudent", "EN")
String policyNumber = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "summary.policyNumberTitle", "EN")
String policyNumberPrefix = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "summary.policyNumberPrefix", "EN")

// Quotation
WebUI.callTestCase(findTestCase('Travel/Reusable Module/Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_SingleTrip'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingCountry'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectCountryArea_area', [('area') : area]))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : '1']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : '28']))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingPerson'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectPlanType_planType', [('planType') : planType]))

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/Quotation Page/dysubmit_GetQuoteNow_button',
	[('button'): buttonPlan]))

WebUI.delay(1)

//Plan
WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/Plan Page/button_SilverPlan'), 0)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/button_SilverPlan'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/dysubmit_Buy_button', [('button') : buttonBuy]))
WebUI.delay(1)
//Application
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), '980421045116')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoFullName'), 'TestingName')

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEthnicity'), 'Indian', false)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), 'yeexian_tyx@hotmail.com')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_MobileNumber'), '137447138')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoBuildingName'), 'Testing')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoAreaName'), 'Testing')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_Postcode'), '81300')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))
WebUI.delay(1)
//child 
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EDITChild1'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), '050507012008')

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoFullName'), 'TestChild')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/dybutton_UniversityStudentYes_travellerInfo', [('travellerInfo') : universityStudent]))

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEthnicity'), 'Indian', false)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_chkIsSameMailingAddress'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'), 0)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'))
WebUI.delay(1)
// Review
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/rdoFPXPayment'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/button_Declaration'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/dysubmit_ProceedPayment_button', [('button') : buttonPayment]))
WebUI.delay(1)
// Payment
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_PayNetOption'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_ContinueMPay'))

WebUI.setText(findTestObject('Travel/TripCare360/English/Payment Page/input_UserId'), GlobalVariable.dummy_userId)

WebUI.setText(findTestObject('Travel/TripCare360/English/Payment Page/input_Password'), GlobalVariable.dummy_userPassword)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_SignIn'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_Confirm'))

WebUI.delay(10)

// Transaction
WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/validation_YourPaymentIsSuccessful'), 10)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/dyvalidation_TransactionSummary_title,code', 
	[('title') : policyNumber, 
		('code') : policyNumberPrefix]), 3)

WebUI.delay(3)

WebUI.takeFullPageScreenshot(FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

