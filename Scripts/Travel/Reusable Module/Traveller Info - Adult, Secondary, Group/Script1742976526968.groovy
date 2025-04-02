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
import com.kms.katalon.core.util.KeywordUtil

// Define default values map with ALL possible variable names
try {
	infoColumn = params['column_set']
} catch (Exception e) {
	infoColumn = "group_second"
}

Map defaultValues = [
	// Original parameter names
	TCIC: CustomKeywords.'utils.Utility.getTravellerInfo'("ic", infoColumn),
	TCPassport: CustomKeywords.'utils.Utility.getTravellerInfo'("passport", infoColumn),
	TCFullName: CustomKeywords.'utils.Utility.getTravellerInfo'("full_name", infoColumn),
	TCEthnicity: CustomKeywords.'utils.Utility.getTravellerInfo'("ethnicity", infoColumn),
	TCEmail: CustomKeywords.'utils.Utility.getTravellerInfo'("email", infoColumn),
	TCMobileNumber: CustomKeywords.'utils.Utility.getTravellerInfo'("mobile_number", infoColumn),
	TCNation: CustomKeywords.'utils.Utility.getTravellerInfo'("nationality", infoColumn),
	TCBankName: CustomKeywords.'utils.Utility.getTravellerInfo'("bank_name", infoColumn),
	TCBankNumber: CustomKeywords.'utils.Utility.getTravellerInfo'("bank_number", infoColumn),
	TCFlightDelay: CustomKeywords.'utils.Utility.getTravellerInfo'("include_flight_delay", infoColumn),
	haveBankInfo: true
]

try {
	// Merge with provided parameters
	finalParams = defaultValues + params
} catch (Exception e) {
	finalParams = defaultValues
}

String travellerInfo = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "travellerInfo.passport", "EN")
String delayButton = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "travellerInfo.flightDelay", "EN")

println(finalParams)
KeywordUtil.logInfo("Mapped data: ${finalParams}")

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_SecondEdit'))

// Nationality
WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_Select Nationality'))
WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/dyybutton_ChooseNationality_nation', [('nation') : finalParams.TCNation]))
println(finalParams.TCIC)

// New NRIC / passport
if (finalParams.TCNation != "MALAYSIAN") {
	IDSelection = finalParams.TCPassport == "" ? 0 : 1
	WebUI.selectOptionByIndex(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/input_TravellerInfoIDType'), IDSelection)
}

String IDInput = finalParams.TCPassport == "" ? finalParams.TCIC : finalParams.TCPassport

// New NRIC / passport input
WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), IDInput)

// Case of passport
if (WebUI.verifyElementPresent(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/dylabel_TravellerInformation_travellerInfo', [('travellerInfo') : travellerInfo]), 3, FailureHandling.OPTIONAL)) {
	String dob = CustomKeywords.'utils.Utility.icToDOB'(finalParams.TCIC)
	WebUI.setText(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/input_TravellerInfoDateOfBirth'), dob) 
	
	if (finalParams.TCIC.toInteger() % 2 == 0) {
		WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_Female'))
	} else {
		WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_Male'))
	}
}

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoFullName'), finalParams.TCFullName)

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEthnicity'), finalParams.TCEthnicity, false)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), finalParams.TCEmail)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_MobileNumber'), finalParams.TCMobileNumber)

def flightNode = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/dybutton_TravellerFlightSwitch_travellerInfo',
	[('travellerInfo'): delayButton])
String currentNode = WebUI.getText(flightNode)

if (finalParams.TCFlightDelay == "Yes") {
	if (currentNode == "No") {
		WebUI.enhancedClick(flightNode)
	}
	
	if (finalParams.haveBankInfo) {
		WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_BankName'))
		WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/dybutton_BankName_bank',
			[('bank'): finalParams.TCBankName]))
		
		WebUI.setText(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/input_BankAccountNumber'), finalParams.TCBankNumber)
	}
} else {
	if (currentNode == "Yes") {
		WebUI.enhancedClick(flightNode)
	}
}

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))