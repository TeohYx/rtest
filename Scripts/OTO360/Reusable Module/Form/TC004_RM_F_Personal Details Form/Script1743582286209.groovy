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
 * Personal Details Form
 * @author 80012455
 *
 * 1. Info
 * - Name (Required)
 * - Select race (Required)
 * - Select gender (if ID Type not MYKAD)
 * - Mobile number (Required)
 * - Email (Required)
 * - Address 1 (Required)
 * - Address 2 (Required)
 * - City
 * - Postcode (Required)
 *
 * -------------------------------------------------------------------------
 * Data - By the default the data is set to choose on silver plan
 *
 * params = {
		  name : String,
		  race : String,
		  gender : String,
		  mobileNumber : String,
		  email: String,
		  address1: String,
		  address2: String,
		  city: String,
		  postcode: String
   }
 *
 */
def infoColumn = 'default'

Map defaultValues = [ // Original parameter names
		('name') : CustomKeywords.'utils.Utility.getOTOInfo'('name', infoColumn),
		('race') : CustomKeywords.'utils.Utility.getOTOInfo'('race', infoColumn),
		('gender') : CustomKeywords.'utils.Utility.getOTOInfo'('gender', infoColumn),
		('mobileNumber') : CustomKeywords.'utils.Utility.getOTOInfo'('mobileNumber', infoColumn),
		('email') : CustomKeywords.'utils.Utility.getOTOInfo'('email', infoColumn),
		('address1') : CustomKeywords.'utils.Utility.getOTOInfo'('address1', infoColumn),
		('address2') : CustomKeywords.'utils.Utility.getOTOInfo'('address2', infoColumn),
		('city') : CustomKeywords.'utils.Utility.getOTOInfo'('city', infoColumn),
		('postcode') : CustomKeywords.'utils.Utility.getOTOInfo'('postcode', infoColumn),
	]

try {
	params = (defaultValues + params)
}
catch (Exception e) {
	params = defaultValues
}

String raceDropdown = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "detail.race", "EN")
String raceDropdownSelection = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "detail.chinese", "EN")
String gender = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "detail.gender", "EN")
String ic = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "detail.ic", "EN")

WebUI.setText(findTestObject('Object Repository/OTO360/Application Page/input_name'), params['name'])
WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Application Page/dyinput_detailDropdownWithoutIdentifier_detail',
	[('detail'): raceDropdown]))
WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Application Page/dyinput_detailDropdownSelectionWithoutIdentifier_detail,selection',
	[('detail'): raceDropdown, ('selection'): raceDropdownSelection]))

if (WebUI.verifyElementNotPresent(findTestObject('Object Repository/OTO360/Application Page/dyinput_detailWithoutIdentifier_detail',
	[('detail'): ic]), 3, FailureHandling.OPTIONAL)) {
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Application Page/dyinput_genderMale_detail',
		[('detail'): gender]))
}

WebUI.setText(findTestObject('Object Repository/OTO360/Application Page/input_mobileNumber'), params['mobileNumber'])
WebUI.setText(findTestObject('Object Repository/OTO360/Application Page/input_email'), params['email'])
WebUI.setText(findTestObject('Object Repository/OTO360/Application Page/input_address1'), params['address1'])
WebUI.setText(findTestObject('Object Repository/OTO360/Application Page/input_address2'), params['address2'])

if (params['city'] != "") {
	WebUI.setText(findTestObject('Object Repository/OTO360/Application Page/input_city'), params['city'])
}

WebUI.setText(findTestObject('Object Repository/OTO360/Application Page/input_postcode'), params['postcode'])
