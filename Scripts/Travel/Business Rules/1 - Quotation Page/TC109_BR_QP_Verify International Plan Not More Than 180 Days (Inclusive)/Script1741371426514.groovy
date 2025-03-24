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

int maximumDayPass = GlobalVariable.rules_dateSelection['internationalMaximumPass'].toInteger()
int maximumDayFail = GlobalVariable.rules_dateSelection['internationalMaximumFail'].toInteger()

String area = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "area.2", "EN")

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

// Get today's date
today = LocalDate.now()

futureDatePass = today.plusDays(maximumDayPass - 1)
futureDateFail = today.plusDays(maximumDayFail - 1)

println(futureDatePass)
println(futureDateFail)
nextMonth = 0
def cases = [true, false]

def checkCase(boolean isPass) {
	if (isPass) {

		println(futureDatePass.dayOfMonth)
		WebUI.verifyElementClickable(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : futureDatePass.dayOfMonth]))
	} else {
		int failedNextMonth = 0
		if (futureDateFail.monthValue != futureDatePass.monthValue) {
			failedNextMonth = futureDateFail.monthValue - futureDatePass.monthValue
		}
		
		for (int i=0; i<failedNextMonth; i++) {
			WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))
		}
		
		WebUI.verifyElementNotClickable(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : futureDateFail.dayOfMonth]))
	}
}

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/Quotation Page/button_SingleTrip'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingCountry'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectCountryArea_area', [('area') : area]))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : today.dayOfMonth]))

if (futureDatePass.monthValue != today.monthValue) {
	nextMonth = futureDatePass.monthValue - today.monthValue
}

for (int i=0; i<nextMonth; i++) {
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))
}

cases.each { testCase ->

	
	checkCase(testCase)
}

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()


