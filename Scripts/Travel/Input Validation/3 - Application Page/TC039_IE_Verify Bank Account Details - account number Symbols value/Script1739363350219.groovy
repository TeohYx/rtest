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

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 3, ('TCplan') : 1, ('TCtrip') : 1
	, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/txtDepartureFlightNumber'), 'MH370')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_DepartureFlightDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btn_nextMonth Flight'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/CALANDER DATES/dybutton_Date_date'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_ReturnFlightNumber'), 'MH17')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ReturnFlightDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btn_nextMonth Flight'))

WebUI.enhancedClick(findTestObject('TripCare360/English/CALANDER DATES/btn_Date28'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/ddl_BankName'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/btn_CIMB'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/txtBankAccountNumber'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/txtBankAccountNumber'), '@#$%^&*')

WebUI.takeFullPageScreenshot(FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

