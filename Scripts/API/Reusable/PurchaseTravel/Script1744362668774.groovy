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
import groovy.json.JsonSlurper

def today = LocalDate.now()
def future = today.plusDays(5)

String todayDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
String futureDate = future.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

def defaultParam = [
		tripType: "1",
		planType: "1",
		area: "1",
		planClass: 1,
		todayDate: todayDate,
		futureDate: futureDate,
		ic: '950701010101',
		dob: '01/07/1995',
		accountHolder: "Teoh Yee Xian",
		accountNo: "123412341235",
		bankId: 26
	]

try {
	params = defaultParam + params
} catch (Exception e) {
	params = defaultParam
}

// Form variable
String tripType = params['tripType']
String planType = params['planType']
String area = params['area']
int planClass = params['planClass']
todayDate = params['todayDate']
futureDate = params['futureDate']
ic = params['ic']
dob = params['dob']

println(tripType)
/**
 * API to purchase travel:
 * 1. ProductPremiumRate/GetTravelPlanPriceBreakdown (to inquire the product premium price (after discount))
 * 2. travel/CreateProposal (to create a proposal based on the premium price) -> gives proposal id
 * 3. InforcePolicy (to inforce the proposal id) -> gives policy number
 * 4. Integration/List (to check if the policy number is inforced) -> confirm product inforced (purchased and register in system)
 */


def getTravelPlanPriceBreakdownBody(String tripType, String planType, String area, int planClass, String todayDate, String futureDate) {
	
	def body = 
	"""
	{
	    "productCode": "BPT",
	    "tripType": "${tripType}",
	    "planType": "${planType}",
	    "area": "${area}",
	    "planClass": ${planClass},
	    "adultCount": 1,
	    "childCount": 0,
	    "startDate": "${todayDate}",
	    "endDate": "${futureDate}",
	    "selectedAddOnId": [],
	    "language": "en",
	    "isStaff": false,
	    "agentType": 0,
	    "countryCode": "MYS",
	    "isMAEM2U": false
	}
	"""
	
	return body
} 

def getCreateProposalBody(
		String tripType, 
		String planType, 
		String area, 
		int planClass, 
		String todayDate, 
		String futureDate, 
		String checkPremium,
		String ic,
		String dob
	) {
	
	def body = 
	"""
	{
	  "productCode": "BPT",
	  "tripType": "${tripType}",
	  "planType": "${planType}",
	  "area": "${area}",
	  "planClass": ${planClass},
	  "adultCount": 1,
	  "childCount": 0,
	  "startDate": "${todayDate}",
	  "endDate": "${futureDate}",
	  "selectedAddOnId": [],
	  "checkPremium": ${checkPremium},
	  "isRoundTrip": false,
	  "marketingConsent": false,
	  "declaration": true,
	  "proposer": {
	    "address": {
	      "addressLine1": "NO999",
	      "addressLine2": "TMN999",
	      "city": "",
	      "postcode": "56000",
	      "state": "KUALA LUMPUR"
	    },
	    "birthDate": "${dob}",
	    "email": "yeexian.teoh@etiqa.com.my",
	    "ethnicity": 2,
	    "gender": "M",
	    "home": "",
	    "identityNumber": "${ic}",
	    "identityType": "1",
	    "lastName": "",
	    "mobile": "01139519168",
	    "name": "Teoh Yee Xian",
	    "nationality": 136,
	    "office": "",
	    "salutation": "",
	    "type": 1
	  },
	  "insured": [],
	  "departureFlightNumber": "",
	  "departureDate": "",
	  "returnFlightNumber": "",
	  "returnDate": "",
	  "accountHolder": "Teoh Yee Xian",
	  "accountNo": "123412341235",
	  "bankId": 26,
	  "isPartner": false,
	  "referral": "",
	  "language": "en",
	  "payMode": "FPX",
	  "isStaff": false,
	  "countryCode": "MYS",
	  "affiliate": {},
	  "sourceAppId": 1
	}
	"""
	
	return body
}

def getInforcePolicyBody(String encodedOrderId) {
	
	def body = 
	"""
	{
	  "encodedOrderId": "${encodedOrderId}",
	  "checkPayment": false,
	  "sendEmail": true,
	  "sendSMS": false,
	  "hasPolicyPack": false,
	  "countryCode": "MYS"
	}
	"""
}

// get_travel_plan_price_breakdown
def travelPlanPriceBreakdownBody = getTravelPlanPriceBreakdownBody(tripType, planType, area, planClass, todayDate, futureDate)
TestObject priceBreakdownRequest = findTestObject('Object Repository/API/Travel/get_travel_plan_price_breakdown')

def priceBreakdownResponse = CustomKeywords.'api.GetResponseFromRequest.POSTRequest'(priceBreakdownRequest, travelPlanPriceBreakdownBody)

def checkPremiumJson = new JsonSlurper().parseText(priceBreakdownResponse.getResponseText())
String checkPremium = checkPremiumJson.data.totalPremium
println("Premium Rate after Discount: ${checkPremium}")

// create_proposal
def createProposalBody = getCreateProposalBody(tripType, planType, area, planClass, todayDate, futureDate, checkPremium, ic, dob)
TestObject createProposalRequest = findTestObject('Object Repository/API/Travel/create_proposal')

def createProposalResponse = CustomKeywords.'api.GetResponseFromRequest.POSTRequest'(createProposalRequest, createProposalBody)

def createProposalJson = new JsonSlurper().parseText(createProposalResponse.getResponseText())
String proposalID = createProposalJson.data
println("Proposal ID: ${proposalID}")

// inforce_policy
def inforcePolicyBody = getInforcePolicyBody(proposalID)
TestObject inforcePolicyRequest = findTestObject('Object Repository/API/Travel/inforce_policy')

def inforcePolicyResponse = CustomKeywords.'api.GetResponseFromRequest.POSTRequest'(inforcePolicyRequest, inforcePolicyBody)

def inforcePolicyJson = new JsonSlurper().parseText(inforcePolicyResponse.getResponseText())
String policyNumber = inforcePolicyJson.data[0]
println("Policy Number: ${policyNumber}")

// check_integration
TestObject integrationRequest = findTestObject('Object Repository/API/Travel/check_integration',
	[('policyNumber'): policyNumber])

def integrationResponse = CustomKeywords.'api.GetResponseFromRequest.GETRequest'(integrationRequest)

// verify integration
WS.verifyElementPropertyValue(integrationResponse, "succeeded", true)

//def returnItem = [
//		'ic': ic,
//		'policyNumber': policyNumber
//	]
//
//return returnItem

GlobalVariable.ic = ic
GlobalVariable.policyNumber = policyNumber
