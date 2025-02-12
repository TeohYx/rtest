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

//WebUI.enhancedClick(findTestObject('TripCare360/English/Quoation Page/btnLanguageEN_QP'), FailureHandling.STOP_ON_FAILURE)
WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Quotation Page/text_FrequentlyAskedQuestions'), 3)

WebUI.verifyTextPresent('Frequently Asked Questions', false)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_LanguageEN'))

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Quotation Page/button_LanguageMY'), 3)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Quotation Page/text_Soalan-SoalanLazim'), 3)

WebUI.verifyTextPresent('Soalan-Soalan Lazim', false)

WebUI.takeFullPageScreenshot()

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_LanguageMY'))

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Quotation Page/button_LanguageEN'), 3)

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Quotation Page/text_FrequentlyAskedQuestions'), 3)

WebUI.verifyTextPresent('Frequently Asked Questions', false)

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

