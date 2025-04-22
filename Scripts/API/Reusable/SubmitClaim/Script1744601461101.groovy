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
import com.kms.katalon.core.testobject.impl.HttpFileBodyContent
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.FormDataBodyParameter
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.impl.HttpFormDataBodyContent

def getGenerateTokenBody(def params) {
//	println("THIS is: " + params)
//	String ic = params['Nric']
	def body =
	"""
	{
	    "username": "SMILE",
	    "clientId": "812e3dc3-c44e-4624-9bc8-c9ee0afbdb73",
	    "clientSecret": "S05YabiF73vIQmUusRlM0ivMj52z7sG7FtjTqF_rC1k",
	    "nric": "${params['Nric']}",
	    "countryCode": "MYS"
	}
	"""
	
	return body
}

def getParameterBodyText(def params) {
	def body = 
	"""
	{
	   "smileAppPhone":"${params['smileAppPhone']}",
	   "smileAppEmail":"${params['smileAppEmail']}",
	   "claimSubmittedDate":"${params['claimSubmittedDate']}",
	   "claimer":{
	      "Nric":${params['Nric']},
	      "FullName":"${params['FullName']}",
	      "idType":"${params['idType']}",
	      "dateOfBirth":"${params['dateOfBirth']}",
	      "maritalStatus":"${params['maritalStatus']}",
	      "noOfChildren":"${params['noOfChildren']}",
	      "monthlyIncome":"${params['monthlyIncome']}",
	      "educationLevel":"${params['educationLevel']}",
	      "race":"${params['race']}",
	      "insuredSinceDate":"${params['insuredSinceDate']}",
	      "religion":"${params['religion']}",
	      "nationality":"${params['nationality']}",
	      "staffFlag":"${params['staffFlag']}",
	      "title":${params['title']},
	      "addressLine1":"${params['addressLine1']}",
	      "city":"${params['city']}",
	      "stateOrProvince":"${params['stateOrProvince']}",
	      "addressPostcode":"${params['addressPostcode']}",
	      "country":"${params['country']}",
	      "phone":"${params['phone']}"
	   },
	   "submitStp":${params['submitStp']},
	   "policies":[
	      {
	         "IsManual":${params['IsManual']},
	         "PolicyNumber":"${params['PolicyNumber']}",
	         "PolicyName":"${params['PolicyName']}",
	         "CoverageNo":"${params['CoverageNo']}",
	         "CoverageType":"${params['CoverageType']}",
	         "InsuredName":"${params['InsuredName']}",
	         "contractType":"${params['contractType']}",
	         "riskNumber":${params['riskNumber']},
	         "NumOfDependant":${params['NumOfDependant']}
	      }
	   ],
	   "reserveFields": ${params['reserveFields']},
	   "haveClaimAgainstOtherParty":${params['haveClaimAgainstOtherParty']},
	   "claimAgainstOtherPartyRemarks":"${params['claimAgainstOtherPartyRemarks']}",
	   "incidentDate":"${params['incidentDate']}",
	   "eventType": "${params['eventType']}",
	   "damageLostItemName":${params['damageLostItemName']},
	   "flightNo":"${params['flightNo']}",
	   "newFlightNo":"${params['newFlightNo']}",
	   "flightDestination":"${params['flightDestination']}",
	   "departureDate":"${params['departureDate']}",
	   "departureTime":"${params['departureTime']}",
	   "arrivalDate":"${params['arrivalDate']}",
	   "arrivalTime":"${params['arrivalTime']}",
	   "baggageArrivalDate":"${params['baggageArrivalDate']}",
	   "baggageArrivalTime":"${params['baggageArrivalTime']}",
	   "newDepartureDate":"${params['newDepartureDate']}",
	   "newDepartureTime":"${params['newDepartureTime']}",
	   "returnDate":"${params['returnDate']}",
	   "originalReturnDate":"${params['originalReturnDate']}",
	   "baggageNum":${params['baggageNum']},
	   "flightdirection":${params['flightdirection']},
	   "bankKey":"${params['bankKey']}",
	   "bankName":"${params['bankName']}",
	   "bankNumber":"${params['bankNumber']}",
	   "bankHolderName":"${params['bankHolderName']}",
	   "paymentCode":"${params['paymentCode']}",
	   "causeTypeCode":"${params['causeTypeCode']}",
	   "lossTypeCode":"${params['lossTypeCode']}",
	   "documents":${params['documents']}
	}
	"""
	
	return body
}

def testt() {
	def body = 
	"""
	{"smileAppPhone":"+60122372273","smileAppEmail":"xianchitan@gmail.com","claimSubmittedDate":"2025-04-17","claimer":{"Nric":"990101020101","FullName":"TAN CHI XIAN","idType":"New IC","dateOfBirth":"1998-08-29T00:00:00.000Z","maritalStatus":"Single","noOfChildren":"0","monthlyIncome":"UP TO RM999","educationLevel":"","race":"MALAY","insuredSinceDate":"2021-09-16T00:00:00.000Z","religion":"Islam","nationality":"MALAYSIAN","staffFlag":"Y","title":null,"addressLine1":"No 10 Jln 13\nTmn Dato Harun\nJln Klang Lama Petaling Jaya\n46000 Selangor","city":"","stateOrProvince":"","addressPostcode":"46000","country":"MALAYSIA","phone":"0122372273"},"submitStp":true,"policies":[{"IsManual":true,"PolicyNumber":"P0022809","PolicyName":"","CoverageNo":"","CoverageType":"","InsuredName":"","contractType":"","riskNumber":1,"NumOfDependant":0}],"reserveFields":[{"subType":null,"premiumClass":"TS3","reserveCode":"CT","destination":null,"reserveAmount":0.0}],"haveClaimAgainstOtherParty":false,"claimAgainstOtherPartyRemarks":"","incidentDate":"2025-04-17","eventType":"Curtailment","damageLostItemName":null,"flightNo":"MHF0001","newFlightNo":"","flightDestination":"-","departureDate":"","departureTime":"","arrivalDate":"","arrivalTime":"","baggageArrivalDate":"","baggageArrivalTime":"","newDepartureDate":"","newDepartureTime":"","returnDate":"","originalReturnDate":"","baggageNum":1,"bankKey":"","bankName":"","bankNumber":"","bankHolderName":"TAN CHI XIAN","paymentCode":"DT","causeTypeCode":"FLM","lossTypeCode":"FLM","documents":[{"documentType":"Flight Itinerary","fileName":"Flight12.JPEG"},{"documentType":"Photo Damaged Baggage","fileName":"b6.JPEG"}]}
	"""
	
	return body
}

def defaultParam = [
   "smileAppPhone":"+60122372273",
   "smileAppEmail":"xianchitan@gmail.com",
   "claimSubmittedDate":"2025-04-15",
   "Nric":900928070605,
   "FullName":"TAN CHI XIAN",
   "idType":"New IC",
   "dateOfBirth":"1998-08-29T00:00:00.000Z",
   "maritalStatus":"Single",
   "noOfChildren":"0",
   "monthlyIncome":"UP TO RM999",
   "educationLevel":"",
   "race":"MALAY",
   "insuredSinceDate":"2021-09-16T00:00:00.000Z",
   "religion":"Islam",
   "nationality":"MALAYSIAN",
   "staffFlag":"Y",
   "title":null,
   "addressLine1":"No 10 Jln 13\nTmn Dato Harun\nJln Klang Lama Petaling Jaya\n46000 Selangor",
   "city":"",
   "stateOrProvince":"",
   "addressPostcode":"46000",
   "country":"MALAYSIA",
   "phone":"0122372273",
   "submitStp":false,
   "IsManual":true,
   "PolicyNumber":"PU028680",
   "PolicyName":"",
   "CoverageNo":"",
   "CoverageType":"",
   "InsuredName":"",
   "contractType":"",
   "riskNumber":1,
   "NumOfDependant":0,
   "reserveFields": 
		[
			"""{
	            "subType": "Baggage Delay",
	            "premiumClass": "TS3",
	            "reserveCode": "BD",
	            "destination": "USA",
	            "reserveAmount": 350.0
            }"""
		],
   "haveClaimAgainstOtherParty":false,
   "claimAgainstOtherPartyRemarks": "",
   "incidentDate":"2025-04-26",
   "eventType": "Baggage Delay",
   "damageLostItemName":null,
   "flightNo":"MHF0001",
   "newFlightNo":"",
   "flightDestination":"-",
   "departureDate":"2025-04-25",
   "departureTime":"00:20:00",
   "arrivalDate":"2025-04-28",
   "arrivalTime":"02:30:00",
   "baggageArrivalDate":"2025-04-28",
   "baggageArrivalTime":"08:40:00",
   "newDepartureDate":"",
   "newDepartureTime":"",
   "returnDate":"",
   "originalReturnDate":"",
   "baggageNum":1,
   "flightdirection":1,
   "bankKey":"",
   "bankName":"",
   "bankNumber":"",
   "bankHolderName":"TAN CHI XIAN",
   "paymentCode":"BD",
   "causeTypeCode":"BAD",
   "lossTypeCode":"BAD",
   "documents":
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

try {
	params = defaultParam + params
} catch (Exception e) {
	params = defaultParam
}
println(params)
//System.in.read()
String ic = params['Nric']

// generate_token
def generateTokenBody = getGenerateTokenBody(params)
TestObject generateTokenRequest = findTestObject('Object Repository/API/Claim/generate_token')
def generateTokenResponse = CustomKeywords.'api.GetResponseFromRequest.POSTRequest'(generateTokenRequest, generateTokenBody)

def generateTokenJson = new JsonSlurper().parseText(generateTokenResponse.getResponseText())
String token = generateTokenJson.data.token

// submit_claim
String parameterText = getParameterBodyText(params)
//String parameterText = testt()

println(parameterText)

RequestObject submitClaimRequest = findTestObject('Object Repository/API/Claim/submit_claim', [('variable'): parameterText, ('auth'): token])

def submitClaimResponse = CustomKeywords.'api.GetResponseFromRequest.POSTRequestBasic'(submitClaimRequest)

// verify integration
WS.verifyElementPropertyValue(submitClaimResponse, "succeeded", true)
