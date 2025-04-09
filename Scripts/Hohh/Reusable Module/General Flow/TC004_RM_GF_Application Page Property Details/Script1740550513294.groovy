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
	('propertyAddress1') : GlobalVariable.dummy_PropertyDetails1['address1'],
	('propertyAddress2') : GlobalVariable.dummy_PropertyDetails1['address2'],
	('propertyCity') : "",
	('propertyPostcode') : GlobalVariable.dummy_PropertyDetails1['postcode'],
]
 */

def defaultParams = [
	('propertyAddress1') : GlobalVariable.dummy_PropertyDetails1['address1'],
	('propertyAddress2') : GlobalVariable.dummy_PropertyDetails1['address2'],
	('propertyCity') : "",
	('propertyPostcode') : GlobalVariable.dummy_PropertyDetails1['postcode']
]

params = defaultParams + params

def address1Input = findTestObject('Object Repository/Hohh/Application Page/input_PropertyDetailsAddress1')
def	address2Input = findTestObject('Object Repository/Hohh/Application Page/input_PropertyDetailsAddress2')
def cityInput = findTestObject('Object Repository/Hohh/Application Page/input_PropertyDetailsCity')
def postcodeInput = findTestObject('Object Repository/Hohh/Application Page/input_PropertyDetailsPostcode')

WebUI.setText(address1Input, params['propertyAddress1'])
WebUI.setText(address2Input, params['propertyAddress2'])
WebUI.setText(cityInput, params['propertyCity'])
//WebUI.setText(postcodeInput, params['propertyPostcode'])

