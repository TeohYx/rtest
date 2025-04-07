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
 * Quotation Form
 * @author 80012455
 * 
 * 1. Info
 * - ID Type (Required)
 * - NRIC (Required)
 * - Vehicle Number (Required)
 * - Passport (selected ID Type:PASSPORT)
 * - DoB (selected ID Type:PASSPORT)
 * - Nationality (selected ID Type:PASSPORT)
 * - ID No (selected ID Type:ARMY/POLICE)
 * 
 * 2. Click on "Apply Now" button
 * 2.1 If the vehicle number have no purchase history on OTO360
 * 	- Display vehicle details
 * 2.2 If the vehicle number have purchase history on OTO360
 *  - Display current coverage tab
 * 
 * -------------------------------------------------------------------------
 * Data - By the default the data is set to follow 2.1 section
 * 
 * params = {
  		idType: MYKAD (String),
  		ic: 000928070605 (String),
  		vehicleNumber: VOA0005 (String),
  		passport: (Optional) (String),
  		nationality: (Optional) (String),
  		dateOfBirth: (Optional) (String),
  		armyIdNo: (Optional) (String),
  		policeIdNo: (Optional) (String),
  		column_set: (Optional) (String)
   }
 * 
 */
def infoColumn = 'default'

Map defaultValues = [ // Original parameter names
    	('idType') : CustomKeywords.'utils.Utility.getOTOInfo'('idType', infoColumn), 
		('ic') : CustomKeywords.'utils.Utility.getOTOInfo'('ic', infoColumn), 
		('vehicleNumber') : CustomKeywords.'utils.Utility.getOTOInfo'('vehicleNumber', infoColumn), 
		('passport') : CustomKeywords.'utils.Utility.getOTOInfo'('passportNo', infoColumn), 
		('nationality') : CustomKeywords.'utils.Utility.getOTOInfo'('nationality', infoColumn), 
		('dateOfBirth') : CustomKeywords.'utils.Utility.getOTOInfo'('dateOfBirth', infoColumn), 
		('armyIdNo') : CustomKeywords.'utils.Utility.getOTOInfo'('armyIdNo', infoColumn), 
		('policeIdNo') : CustomKeywords.'utils.Utility.getOTOInfo'('policeIdNo', infoColumn)
	]
//println(defaultValues)
try {
    params = (defaultValues + params)
//	println("param try: ${params}")
}
catch (Exception e) {
    params = defaultValues
	
//	println("param catch: ${params}")
} 

String quotationFormValidate = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "text.quotationForm", "EN")
String idTypeButton = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "quotation.IDType", "EN")
String idTypeSelection = null
String applyQuotation = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "submit.quotationCheck", "EN")
String nationalityButton = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "quotation.nationality", "EN")
String nationalitySelection = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "quotationType.local", "EN")

//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Object Repository/OTO360/Quotation Page/dyvalidate_quotationForm_text',
	[('text'): quotationFormValidate]), 10)

println(params)
if (params['idType'] == "MYKAD") {
	// select id type
	idTypeSelection = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "quotationType.mykad", "EN")
	
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_IDType_quotation',
		[('quotation'): idTypeButton]))
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_IDTypeSelection_quotation,quotationType',
		[('quotation'): idTypeButton, ('quotationType'): idTypeSelection]))
	
	WebUI.setText(findTestObject('Object Repository/OTO360/Quotation Page/input_NRIC'), params['ic'])
} else if (params['idType'] == "PASSPORT") {
	idTypeSelection = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "quotationType.passport", "EN")
	
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_IDType_quotation',
		[('quotation'): idTypeButton]))
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_IDTypeSelection_quotation,quotationType',
		[('quotation'): idTypeButton, ('quotationType'): idTypeSelection]))
	
	
	WebUI.setText(findTestObject('Object Repository/OTO360/Quotation Page/input_passportNumber'), params['passportNo'])
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_nationality_quotation', 
		[('quotation'): nationalityButton]))
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_nationalitySelection_quotation,quotationType',
		[('quotation'): nationalitySelection]))
	WebUI.setText(findTestObject('Object Repository/OTO360/Quotation Page/input_dateOfBirth'), params['dateOfBirth'])
} else if (params['idType'] == "ARMY") {
	idTypeSelection = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "quotationType.army", "EN")
	
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_IDType_quotation',
		[('quotation'): idTypeButton]))
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_IDTypeSelection_quotation,quotationType',
		[('quotation'): idTypeButton, ('quotationType'): idTypeSelection]))
	
	
	WebUI.setText(findTestObject('Object Repository/OTO360/Quotation Page/input_NRIC'), params['ic'])
	WebUI.setText(findTestObject('Object Repository/OTO360/Quotation Page/input_IDNumber'), params['armyIdNo'])
} else if (params['idType'] == "POLICE") {
	idTypeSelection = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "quotationType.police", "EN")
	
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_IDType_quotation',
		[('quotation'): idTypeButton]))
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/Quotation Page/dybutton_IDTypeSelection_quotation,quotationType',
		[('quotation'): idTypeButton, ('quotationType'): idTypeSelection]))
	
	
	WebUI.setText(findTestObject('Object Repository/OTO360/Quotation Page/input_NRIC'), params['ic'])
	WebUI.setText(findTestObject('Object Repository/OTO360/Quotation Page/input_IDNumber'), params['policeIdNo'])
}

WebUI.setText(findTestObject('Object Repository/OTO360/Quotation Page/input_vehicleNumber'), params['vehicleNumber'])

WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_submit',
	[('submit'): applyQuotation]))

WebUI.delay(3)