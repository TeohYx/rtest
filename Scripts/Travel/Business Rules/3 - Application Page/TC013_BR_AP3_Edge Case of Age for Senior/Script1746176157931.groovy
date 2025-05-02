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

int individualPlan = 2

String ageRange = GlobalVariable.rules_Senior
String dobText = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "travellerInfo.dob", "EN")
String todayDay = CustomKeywords.'utils.Utility.getTodayDate'(3) //Get today's day value
String arrivalDay = CustomKeywords.'utils.Utility.getSpecificDate'(1, 3) //Get arrival day value, which is plue 1 day

def warningMessageDOBObj = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Warning Message Text/dywrnmsg_TravellerInfo_travellerInfo',
			[('travellerInfo') : dobText])

def agePeriod = ageRange.split('-')

def resultCase = [
			'positive': [],
			'negative': []
		]
agePeriod.each { ageRegex ->
	def result = CustomKeywords.'utils.Utility.obtainAgeEdgeCaseAgeToIC'(ageRegex)
	
	resultCase.each { cases, _ ->
		if (cases == 'positive') {
			resultCase[cases].addAll(result[cases])
		} else if (cases == 'negative') {
			resultCase[cases].addAll(result[cases])
		}
	}
}

println(resultCase)

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'),
	[('TCarea') : 2, ('TCplan') : individualPlan, ('TCtrip'): 1, ('TCpackage'): 1, ('TCdepartDate'): todayDay, ('TCarrivalDate'): arrivalDay],
	FailureHandling.STOP_ON_FAILURE)

def errorLog = []
resultCase.each { cases, ics ->
	ics.each { ic -> 
		WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'), FailureHandling.OPTIONAL)
		
		WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), ic)
		
		WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges'))
		
		if (cases == 'positive') {
			boolean isNotPresent = WebUI.verifyElementNotPresent(warningMessageDOBObj, 3, FailureHandling.OPTIONAL)
			
			if (!isNotPresent) {
				String text = WebUI.getText(warningMessageDOBObj)
				
				errorLog.add("${cases} cases. Error present with valid IC (${ic}). Warning message: ${text}")
			}
		} else if (cases == 'negative') {
			boolean isPresent = WebUI.verifyElementPresent(warningMessageDOBObj, 3, FailureHandling.OPTIONAL)
			
			if (!isPresent) {
				errorLog.add("${cases} cases. Error not present with invalid IC (${ic})")
			}
		} else {
			errorLog.add("Unknown case: ${cases}; IC: ${ic}")
		}
	}
}

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()

String log = errorLog.join('\n')
assert errorLog.isEmpty() : log
