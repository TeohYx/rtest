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

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC006_RM_PF_Direct to Declaration Page Specific'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_Declaration'))
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_FPX'))
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Declaration Page/button_Pay Now'))

// Handle Payment steps
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/button_PayNet'))
WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/button_Continue'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/dybutton_Button_button', [('button') : GlobalVariable.dyobj_CancelButton]))

WebUI.delay(10)
WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/img_TransactionUnsuccessful'), 
    3)

WebUI.takeFullPageScreenshot()

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Payment Page/dybutton_TryAgain_text', [('text') : GlobalVariable.dyobj_TryAgainButton]))

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Payment Page/dyvalidation_RetrieveYourProposal_title', [('title'): GlobalVariable.dyobj_RetrieveYourProposal]), 
    10)

WebUI.delay(3)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

