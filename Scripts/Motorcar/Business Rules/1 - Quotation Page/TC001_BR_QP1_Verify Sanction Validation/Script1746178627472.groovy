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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

// Quotation page plate number
String plateNumber = GlobalVariable.rules_carPlate['default']
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_Passport')
String section = 'Details & Add Ons'
def failedCountry = []
def blacklistCountry = GlobalVariable.rules_BlacklistedCountry

for (def country : blacklistCountry) {
    String countryText = country.toUpperCase()

    Map inputScenario = [
		('inputField') : [
				[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_Passport'), ('inputText') : 'A12341233'],
				[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_Date Of Birth'), ('inputText') : '28091990']
			],
		('buttonField') : [
			[
				('buttonClick') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/button_Select Nationality')
                , ('buttonSelection') : findTestObject('Motorcar and Motorcycle/Quotation Page/dyoption_Select Country_country', [('country') : countryText])
			]
		]
	]

    WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), 
		[
			('plateNumber') : plateNumber,
			('inputButton') : inputButton,
			('inputScenario') : inputScenario
		], FailureHandling.STOP_ON_FAILURE)

    Boolean hasClass = false

    if (WebUI.verifyElementPresent(findTestObject('Object Repository/Motorcar and Motorcycle/General/text_PageTitle', [('section') : section]), 
        3, FailureHandling.OPTIONAL)) {
        String elementClass = WebUI.getAttribute(findTestObject('Object Repository/Motorcar and Motorcycle/General/text_PageTitle', [('section') : section]), 
            'class')

        hasClass = elementClass.contains('fw-bold')
    }
    
    if (hasClass) {
        failedCountry.add(country)
    }
    
	WebUI.takeScreenshot()
    WebUI.closeBrowser()
}

assert failedCountry.isEmpty() : "The blacklisted country still able to access to plan page. Failed case: ${failedCountry}"
