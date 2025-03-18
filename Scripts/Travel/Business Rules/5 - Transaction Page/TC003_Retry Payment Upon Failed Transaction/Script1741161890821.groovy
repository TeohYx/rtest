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

//** very hard code, should improve
WebUI.callTestCase(findTestCase('Travel/Reusable Module/Filled Form - Myself'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'), 0)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/rdoFPXPayment'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/button_Declaration'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/dysubmit_ProceedPayment_name', [('name') : GlobalVariable.dyobj_ProceedPayment]))
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_PayNetOption'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_ContinueMPay'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/dybutton_Cancel_cancel', [('cancel') : GlobalVariable.dyobj_CancelButton]))

WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/img_TransactionUnsuccessful'), 
    3)

WebUI.takeFullPageScreenshot()

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/dybutton_TryAgain_text', [('text') : GlobalVariable.dyobj_TryAgainButton]))

WebUI.setText(findTestObject('Object Repository/Travel/TripCare360/English/Payment Page/input_RetrieveYourProposal'), GlobalVariable.tempIC)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/dybutton_Cancel_cancel', [('cancel') : GlobalVariable.dyobj_RetrieveButton]))

//retry
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/rdoFPXPayment'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/dysubmit_ProceedPayment_name', [('name') : GlobalVariable.dyobj_ProceedPayment]))
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_PayNetOption'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_ContinueMPay'))

WebUI.setText(findTestObject('Travel/TripCare360/English/Payment Page/input_UserId'), GlobalVariable.dummy_userId)

WebUI.setText(findTestObject('Travel/TripCare360/English/Payment Page/input_Password'), GlobalVariable.dummy_userPassword)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_SignIn'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/button_Confirm'))

WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/validation_YourPaymentIsSuccessful'), 10)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/dyvalidation_PolicyNumber_text,code', 
	[('text') : GlobalVariable.dyobj_PolicyNumber, 
		('code') : GlobalVariable.dyobj_ProductCode]), 3)

WebUI.delay(3)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

