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

/**
 * Information to validate: 
 * 1. Vehicle Number
 * 2. IC
 * 
 */

String ic = CustomKeywords.'utils.Utility.getOTOInfo'('ic', "default")
String vehicleNumber = CustomKeywords.'utils.Utility.getOTOInfo'('vehicleNumber', "default")

String vehicleNumberInputTitle = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "detail.vehicleNumber", "EN")

Map toApplicationParams = [
		('ic') : ic, 
		('vehicleNumber') : vehicleNumber
	]

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC003_RM_PF_Direct to Application Page'), 
	[('params') : toApplicationParams], FailureHandling.STOP_ON_FAILURE)

String displayedVehicleNumber = WebUI.getAttribute(findTestObject('Object Repository/OTO360/Application Page/dyinput_detailWithoutIdentifier_detail',
	[('detail') : vehicleNumberInputTitle]), 'value')
String displayedIc = WebUI.getAttribute(findTestObject('Object Repository/OTO360/Application Page/input_NRIC'), 'value')

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()

assert ic == displayedIc : "IC not tally. Input ic: ${ic}; Displayed ic: ${displayedIc}"
assert vehicleNumber == displayedVehicleNumber : "Vehicle number not tally. Input vehicle number: ${vehicleNumber}; Displayed vehicle number: ${displayedVehicleNumber}"
