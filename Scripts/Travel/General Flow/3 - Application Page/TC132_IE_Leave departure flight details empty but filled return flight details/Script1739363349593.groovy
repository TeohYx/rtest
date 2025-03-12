import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testobject.SelectorMethod.XPATH

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

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 2, ('TCplan') : 1, ('TCtrip') : 1
	, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/input_ReturnFlightNumber'), "ABC1234")

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ReturnFlightDate'))
WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_depart_flight_date'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'))

String first_warning_message = WebUI.getText(findTestObject('Travel/TripCare360/English/AppPage/Warning Message Text/wrnmsg_Departure Flight Number'))
String second_warning_message = WebUI.getText(findTestObject('Travel/TripCare360/English/AppPage/Warning Message Text/wrnmsg_Departure Flight Date'))

WebUI.closeBrowser()

assert first_warning_message == GlobalVariable.errorMessage_empty && second_warning_message == GlobalVariable.errorMessage_empty

