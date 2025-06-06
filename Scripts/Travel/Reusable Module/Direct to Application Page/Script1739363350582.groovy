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

String buttonBuy = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "button.buy", "EN")

// Area
try {
	area = TCarea
} catch (Exception e) {
	area = 1
}

// Plan type
try {
	plan = TCplan
} catch (Exception e)
{
	plan = 1
}

try {
	trip = TCtrip	
} catch (Exception e) {
	trip = 1
}

//plan class
try {
	packages = TCpackage
} catch (Exception e) {
	packages = 1
}


String departDate = ""
String arrivalDate = ""

try {
	departDate = TCdepartDate.toString()
	arrivalDate = TCarrivalDate.toString()
	
	println("preset: " + [departDate, arrivalDate])
} catch (Exception e) {
	departDate = "1"
	arrivalDate = "28"
	
	println("default: " + [departDate, arrivalDate])
}

println(departDate)
println(arrivalDate)

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Quotation Form'), [('TCarea') : area, ('TCplan') : plan
        , ('TCtrip') : trip, ('TCdepartDate') : departDate, ('TCarrivalDate') : arrivalDate], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementClickable(findTestObject('Travel/TripCare360/English/Plan Page/button_SilverPlan'), 10)
WebUI.delay(2)

if (packages == 1) {
    WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/button_SilverPlan'))
} else if (packages == 2) {
    WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/button_GoldPlan'))
} else if (packages == 3) {
    WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/button_PlatinumPlan'))
}

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/dysubmit_Buy_button', [('button') : buttonBuy]))

WebUI.delay(1.5)

