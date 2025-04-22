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

def params = [
		ic: "990101020101",
		policyNumber: "PU028655",
		reserveField: 
		[
			"""{
	            "subType": null,
	            "premiumClass": "TS2",
	            "reserveCode": "TD",
	            "destination": null,
	            "reserveAmount": 0
            }"""
		],
		eventType: "Travel Delay",
		paymentCode: "FD",
		causeTypeCode: "FLD",
		lossTypeCode: "FLD",
		document: 
		[
			"""
	        {
	            "documentType": "Flight Itinerary",
	            "fileName": "0120.jpg"
	        },
	        {
	            "documentType": "Flight Itinerary",
	            "fileName": "0121.jpg"
	        }
			"""
	    ]
	]

WebUI.callTestCase(findTestCase('API/Reusable/SubmitClaim'), [('params'):params], FailureHandling.STOP_ON_FAILURE)

