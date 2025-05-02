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
 * Information to be filled:
 * 
 * 1. Address 1
 * 2. Address 2
 * 3. City (Optional)
 * 4. Postcode
 * 
 * def defaultParams =  [
	('isTick') : false,
	('address1') : GlobalVariable.dummy_MailingAddress1['address1'],
	('address2') : GlobalVariable.dummy_MailingAddress1['address2'],
	('city') : "",
	('postcode') : GlobalVariable.dummy_MailingAddress1['postcode'],
]
 */

def defaultParams = [
	('isTick') : false,
	('mailingAddress1') : GlobalVariable.dummy_MailingAddress1['address1'],
	('mailingAddress2') : GlobalVariable.dummy_MailingAddress1['address2'],
	('mailingCity') : GlobalVariable.dummy_MailingAddress1['city'],
	('mailingPostcode') : GlobalVariable.dummy_MailingAddress1['postcode'],
]

params = defaultParams + params

def isTickObj = findTestObject('Object Repository/Hohh/Application Page/button_SameMailingAddress')
def address1Input = findTestObject('Object Repository/Hohh/Application Page/input_MailingAddress1')
def	address2Input = findTestObject('Object Repository/Hohh/Application Page/input_MailingAddress2')
def cityInput = findTestObject('Object Repository/Hohh/Application Page/input_MailingAddressCity')
def postcodeInput = findTestObject('Object Repository/Hohh/Application Page/input_MailingAddressPostcode')

if (params['isTick']) {
	WebUI.enhancedClick(isTickObj)
} else {
	WebUI.setText(address1Input, params['mailingAddress1'])
	WebUI.setText(address2Input, params['mailingAddress2'])
	WebUI.setText(cityInput, params['mailingCity'])
	WebUI.setText(postcodeInput, params['mailingPostcode'])
}
