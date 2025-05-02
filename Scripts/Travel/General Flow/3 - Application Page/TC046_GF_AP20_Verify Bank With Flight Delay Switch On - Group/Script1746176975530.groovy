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
 * Active the flight delay claim switch, but not filling the bank detail. This will failed, but after fill in details, it should pass.
 * 1. Verify save traveller info without fill up bank details -> Negative test
 * 2. Verify save traveller info with fill up bank details -> Positive test
 * 3. Verify save secondary traveller info without fill up bank details -> Negative test
 * 4. Verify save secondary traveller info with fill up bank details -> Positive test
 * 
 * @author 80012455
 *
 */

def flightDelayParam = [
		'departureFlightNumber': 'MH004',
		'returnFlightNumber': 'MH004'
	]
	
def travellerNoBankDetail = [
		'haveBankInfo': false
	]
	
String site = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "currentSite.payment", "EN")
def siteObj = findTestObject('Travel/TripCare360/English/General/dytext_ProveOfCurrentPage_currentSite', [('currentSite') : site])

String delayButton = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "travellerInfo.flightDelay", "EN")
def flightNode = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/dybutton_TravellerFlightSwitch_travellerInfo',
	[('travellerInfo'): delayButton])

String bankName = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "travellerInfo.bankName", "EN")
def wrnBank = findTestObject('Object Repository/Travel/TripCare360/English/AppPage/Warning Message Text/dywrnmsg_TravellerInfo_travellerInfo', 
	[('travellerInfo'): bankName])

def saveChangeObj = findTestObject('Travel/TripCare360/English/AppPage/button_PersonalDetailsSaveChanges')

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCplan') : 5], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Flight delay claim - Group'), [('params') : flightDelayParam], FailureHandling.STOP_ON_FAILURE)

// Main traveller info
WebUI.callTestCase(findTestCase('Travel/Reusable Module/Traveller Info - Adult, Main, Group'), [('params') : travellerNoBankDetail], FailureHandling.STOP_ON_FAILURE)

if (!WebUI.verifyElementPresent(flightNode, 3, FailureHandling.OPTIONAL)
		|| !WebUI.verifyElementPresent(wrnBank, 3, FailureHandling.OPTIONAL)){
			assert false: "There is error message or fight delay switch did not show up."
		}

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_BankName'))
WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/dybutton_BankName_bank',
	[('bank'): CustomKeywords.'utils.Utility.getTravellerInfo'("bank_name", "default")]))

WebUI.setText(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/input_BankAccountNumber'), 
	CustomKeywords.'utils.Utility.getTravellerInfo'("bank_number", "default"))
				
WebUI.enhancedClick(saveChangeObj)
		
assert WebUI.verifyElementNotPresent(saveChangeObj, 3, FailureHandling.OPTIONAL) : "The main traveller info tab did not close, despite all info are filled and button are clicked."

// Secondary traveller info
WebUI.callTestCase(findTestCase('Travel/Reusable Module/Traveller Info - Adult, Secondary, Group'), [('params') : travellerNoBankDetail], FailureHandling.STOP_ON_FAILURE)

if (!WebUI.verifyElementPresent(wrnBank, 3, FailureHandling.OPTIONAL)){
		assert false: "The error message did not show up."
	}

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_BankName'))
WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/dybutton_BankName_bank',
	[('bank'): CustomKeywords.'utils.Utility.getTravellerInfo'("bank_name", "group_second")]))

WebUI.setText(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/input_BankAccountNumber'),
	CustomKeywords.'utils.Utility.getTravellerInfo'("bank_number", "group_second"))

WebUI.enhancedClick(saveChangeObj)

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_ConfirmDetails'))

assert WebUI.verifyElementPresent(siteObj, 3, FailureHandling.OPTIONAL) : "The page does not direct to Payment Page, as its text is not bold"
