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

import java.time.LocalDate
import java.time.format.DateTimeFormatter

def today = LocalDate.now()
def future = today.plusDays(5)

String todayDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
String futureDate = future.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

String todayDateClaim = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
String futureDateClaim = future.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

String name = "Teoh Yee Xian"


def travelParams = [
	tripType: "1",
	planType: "1",
	area: "1",
	planClass: 1,
	todayDate: todayDate,
	futureDate: futureDate,
	ic: '960701010101',
	dob: '01/07/1996',
	accountHolder: name,
	accountNo: "123412341245",
	bankId: 26
]

def params = [
//		ic: "990101020101",
//		policyNumber: "P0022809",
		claimSubmittedDate: todayDateClaim,
		FullName: name,
		incidentDate: todayDateClaim,
		departureDate: todayDateClaim,
		arrivalDate: todayDateClaim,
		baggageArrivalDate: todayDateClaim,
		bankKey: "MBBEMYKL",
		bankName: "Malayan Banking Berhad",
		bankHolderName: name,
		bankNumber: "123412341245",
		reserveFields: 
		[
			"""{
	            "subType": null,
	            "premiumClass": "TS3",
	            "reserveCode": "BD",
	            "destination": null,
	            "reserveAmount": 0
            }"""
		],
		eventType: "Baggage Delay",
		paymentCode: "BD",
		causeTypeCode: "BAD",
		lossTypeCode: "BAD",
		document: 
		[
			"""
		    {
		      "documentType": "Boarding Pass",
		      "fileName": "a.jpg"
		    },
		    {
		      "documentType": "Boarding Pass",
		      "fileName": "b.jpg"
		    }
			"""
	    ]
	]
	
WebUI.callTestCase(findTestCase('API/Reusable/PurchaseTravel'), [('params'):travelParams], FailureHandling.STOP_ON_FAILURE)
//println(returnItem)
// Assign policyNumber to the params
params['Nric'] = GlobalVariable.ic
params['policyNumber'] = GlobalVariable.policyNumber

String dateFormat = CustomKeywords.'utils.Utility.icToDOB'(GlobalVariable.ic, "yyyy-MM-dd")
params['dateOfBirth'] = dateFormat + "T00:00:00.000Z"

WebUI.callTestCase(findTestCase('API/Reusable/SubmitClaim'), [('params'):params], FailureHandling.STOP_ON_FAILURE)

