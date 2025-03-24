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

String buildingName = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "travellerInfo.buildingName", "EN")

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 3, ('TCplan') : 1, ('TCtrip') : 1
	, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_Postcode'), GlobalVariable.validation_symbol)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))

String area_name_text = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/Warning Message Text/dywrnmsg_TravellerInfo_travellerInfo',
	[('travellerInfo') : buildingName]), "value") ?: ""

WebUI.closeBrowser()

assert area_name_text.isEmpty() : "The Area Name field is not empty and contains ${area_name_text}"
