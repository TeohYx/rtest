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

def params = TCparams

// Define default values map with ALL possible variable names
Map defaultValues = [
	// Original parameter names
	TCIC: GlobalVariable.dummy_IC,
	TCFullName: GlobalVariable.dummy_fullName,
	TCEthnicity: GlobalVariable.dummy_ethnicity,
	TCEmail: GlobalVariable.dummy_email,
	TCMobileNumber: GlobalVariable.dummy_mobile,
	TCBuildingName: GlobalVariable.dummy_areaName,
	TCAreaName: GlobalVariable.dummy_areaName,
	TCPostcode: GlobalVariable.dummy_postcode,
	TCNation: GlobalVariable.dummy_nationality,
]

// Merge with provided parameters
def finalParams = defaultValues + params

println(finalParams)
KeywordUtil.logInfo("Mapped data: ${finalParams}")

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_Select Nationality'))
WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/dybutton_ChooseNationality_nation', [('nation') : finalParams.TCNation]))
println(finalParams.TCIC)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), finalParams.TCIC)

if (WebUI.verifyElementPresent(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/dylabel_TravellerInformation_text', [('text') : 'Passport']), 3, FailureHandling.OPTIONAL)) {
	WebUI.setText(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/input_TravellerInfoDateOfBirth'), '28092000') //HARDCODE	
	WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_Male'))
}

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoFullName'), finalParams.TCFullName)

WebUI.selectOptionByLabel(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEthnicity'), finalParams.TCEthnicity, false)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoEmail'), finalParams.TCEmail)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_MobileNumber'), finalParams.TCMobileNumber)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoBuildingName'), finalParams.TCBuildingName)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoAreaName'), finalParams.TCAreaName)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_Postcode'), finalParams.TCPostcode)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))