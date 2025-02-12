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

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_PassportOrIdentityNumber'), TCIC)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_FullName'), TCFullName)

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/dropdown_Ethnicity'), TCEthnicity, false)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_Email'), TCEmail)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_MobileNumber'), TCMobileNumber)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_BuildingName'), TCBuildingName)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_AreaName'), TCAreaName)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/text_Postcode'), TCPostcode)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))