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

try {
	infoColumn = TCparams['column_set']
} catch (Exception e) {
	infoColumn = "child_university"
}

Map defaultValues = [
	// Original parameter names
	TCIC: CustomKeywords.'utils.Utility.getTravellerInfo'("ic", infoColumn),
	TCPassport: CustomKeywords.'utils.Utility.getTravellerInfo'("passport", infoColumn),
	TCFullName: CustomKeywords.'utils.Utility.getTravellerInfo'("full_name", infoColumn),
	TCEthnicity: CustomKeywords.'utils.Utility.getTravellerInfo'("ethnicity", infoColumn),
	TCEmail: CustomKeywords.'utils.Utility.getTravellerInfo'("email", infoColumn),
	TCMobileNumber: CustomKeywords.'utils.Utility.getTravellerInfo'("mobile_number", infoColumn),
	TCBuildingName: CustomKeywords.'utils.Utility.getTravellerInfo'("building_name", infoColumn),
	TCAreaName: CustomKeywords.'utils.Utility.getTravellerInfo'("area_name", infoColumn),
	TCPostcode: CustomKeywords.'utils.Utility.getTravellerInfo'("postcode", infoColumn),
	TCNation: CustomKeywords.'utils.Utility.getTravellerInfo'("nationality", infoColumn),
	isStudent: CustomKeywords.'utils.Utility.getTravellerInfo'("is_student", infoColumn),
	isSameAsOwner: false
]

try {
	finalParams = defaultValues + TCparams
} catch (Exception e) {
	finalParams = defaultValues
}

String travellerInfo = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "travellerInfo.passport", "EN")
def isStudent = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_IsStudent')
def isNotStudent = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_isNotStudent')

println(finalParams)
KeywordUtil.logInfo("Mapped data: ${finalParams}")

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

if (finalParams.isStudent == "Yes") {
	WebUI.enhancedClick(isStudent)
} else {
	WebUI.enhancedClick(isNotStudent)
}

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEthnicity'), finalParams.TCEthnicity, false)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), finalParams.TCEmail)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_MobileNumber'), finalParams.TCMobileNumber)

if (finalParams.isSameAsOwner &&
	WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoBuildingName'), 'disabled') == null) {
		WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_SameAsPolicyOwner'))
	} else {
		WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoBuildingName'), finalParams.TCBuildingName)
		
		WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoAreaName'), finalParams.TCAreaName)
		
		WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_Postcode'), finalParams.TCPostcode)
	}

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))