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

try {
	area = TCarea
} catch (Exception e) {
	area = 1
}

try {
	plan = TCplan
} catch (Exception e) {
	plan = 1
}

try {
	trip = TCtrip
} catch (Exception e) {
	trip = 1
}

String area = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "area.${area}", "EN")
String planType = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "planType.${plan}", "EN")
String buttonPlan = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "button.toPlanPage", "EN")

String departDate = ""
String arrivalDate = ""

try {
	departDate = TCdepartDate.toString()
	arrivalDate = TCarrivalDate.toString()
} catch (Exception e) {
	departDate = "1"
	arrivalDate = "28"
}

//println(departDate)
//println(arrivalDate)

int env = GlobalVariable.environment

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

if(trip == 1) {
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_SingleTrip'))
} else if(trip == 2) {
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_AnnualTrip'))
}


if (env == 0) {
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingCountry'))
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectCountryArea_area', [('area') : area]))
} else if (env == 1) {
	String columnName = TCarea.toString()
	
	TestData data = findTestData('CountryArea')
	data.changeSheet('CountryArea')
	
	int nonEmptyRows = 0
	// Get the number of rows of available country
	for (int i = 1; i <= data.getRowNumbers(); i++) {
		String dataInfo = data.getValue(columnName, i)  // Get the value in the first column (Username)
	
		// Check if the username column is not empty
		if (dataInfo != null && dataInfo.trim() != "") {
			nonEmptyRows++
		}
	}
	
	Random rand = new Random()
	int randomRow = rand.nextInt(nonEmptyRows + 1)
	
	String countryName = data.getValue(columnName, randomRow)

	def countryObj = findTestObject('Object Repository/Travel/TripCare360/English/Quotation Page/button_SelectCountry_SIT')

	def countryNameObj = findTestObject('Object Repository/Travel/TripCare360/English/Quotation Page/dybutton_SelectCountryName_SIT_text',
		[('text') : countryName])
		
	WebUI.enhancedClick(countryObj)
	WebUI.enhancedClick(countryNameObj)
	
} else if (env == 2) {
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingCountry'))
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectCountryArea_area', [('area') : area]))
} else {
	assert false : "Invalid selected environment: ${env}"
}

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingDate'))

//Temp, can be improve.
if (departDate == "1" && arrivalDate == "28") {
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))
}

if(arrivalDate.toInteger() < departDate.toInteger()) {
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : departDate]))
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))
	
	if(trip == 1) {
		WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : arrivalDate]))
	}
} else {
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : departDate]))
	
	if(trip == 1) {
		WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : arrivalDate]))
	}
}

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingPerson'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectPlanType_planType', [('planType') : planType]))

//WebUI.delay(1)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dysubmit_GetQuoteNow_button',
	[('button'): buttonPlan]))

WebUI.delay(1)

