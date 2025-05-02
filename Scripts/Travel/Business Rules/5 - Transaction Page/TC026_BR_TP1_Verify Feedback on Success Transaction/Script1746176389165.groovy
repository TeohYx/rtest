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

String buttonPayment = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "button.proceedPayment", "EN")

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Filled Form - Myself'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'), 0)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/rdoFPXPayment'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/button_Declaration'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/dysubmit_ProceedPayment_button', [('button') : buttonPayment]))
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_PayNetOption'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_ContinueMPay'))

WebUI.setText(findTestObject('Travel/TripCare360/English/Payment Page/input_UserId'), GlobalVariable.dummy_userId)

WebUI.setText(findTestObject('Travel/TripCare360/English/Payment Page/input_Password'), GlobalVariable.dummy_userPassword)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_SignIn'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_Confirm'))

WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/validation_YourPaymentIsSuccessful'), 20)

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/Payment Page/button_Feedback5Star'))

WebUI.setText(findTestObject('Object Repository/Travel/TripCare360/English/Payment Page/input_FeedbackComment'), "Good Experience")

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/Payment Page/button_FeedbackRecommend'))
WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/Payment Page/button_FeedbackSelectMedium'))
WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/Payment Page/dropdown_FeedbackSelectMedium'))
WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/Payment Page/button_FeedbackSubmit'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Travel/TripCare360/English/Payment Page/validation_FeedbackSubmission'), 10)

WebUI.delay(3)

WebUI.takeFullPageScreenshot(FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

