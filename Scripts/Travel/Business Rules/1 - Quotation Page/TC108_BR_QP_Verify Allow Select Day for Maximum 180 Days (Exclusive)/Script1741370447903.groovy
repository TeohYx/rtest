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

int maximumDayPass = GlobalVariable.rules_dateSelection['departMaximumPass'].toInteger()
int maximumDayFail = GlobalVariable.rules_dateSelection['departMaximumFail'].toInteger()

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

// Get today's date
today = LocalDate.now()

departDatePass = today.plusDays(maximumDayPass)
departDateFail = today.plusDays(maximumDayFail)
nextMonth = 0
def cases = [true, false]

def tripType = [
		findTestObject('Object Repository/Travel/TripCare360/English/Quotation Page/button_SingleTrip'),
		findTestObject('Object Repository/Travel/TripCare360/English/Quotation Page/button_AnnualTrip')
	]


def checkCase(boolean isPass) {
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingDate'))
	
	if (isPass) {
		if (departDatePass.monthValue != today.monthValue) {
			nextMonth = departDatePass.monthValue - today.monthValue
		}
	
		for (int i=0; i<nextMonth; i++) {
			WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))
		}
		
		WebUI.verifyElementClickable(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : departDatePass.dayOfMonth]))
	} else {
		if (departDateFail.monthValue != today.monthValue) {
			nextMonth = departDateFail.monthValue - today.monthValue
		}
		
		for (int i=0; i<nextMonth; i++) {
			WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_NextMonth'))
		}
		
		WebUI.verifyElementNotClickable(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : departDateFail.dayOfMonth]))
	}
}

println(departDatePass)
println(departDateFail)

cases.each { testCase ->
	def random = new Random()
	int randomValue = random.nextInt(tripType.size())
	
	int areaValue = 0
	if (randomValue == 1) {
		areaValue = random.nextInt(4) + 1
	} else {
		areaValue = random.nextInt(3) + 1
	}
	
	WebUI.enhancedClick(tripType[randomValue])
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dropdown_TravellingCountry'))
	
	WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dybutton_SelectCountryArea_area', [('area') : GlobalVariable.dyobj_area[areaValue]]))
	
	checkCase(testCase)
}

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()


