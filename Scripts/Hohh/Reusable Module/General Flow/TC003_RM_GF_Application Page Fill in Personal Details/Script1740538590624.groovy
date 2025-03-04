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
 * 1. Name (as per NRIC)
 * 2. ID Type
 * 3. NRIC no.
 * 4. Race
 * 5. Mobile number
 * 6. Email
 * 
 * By default, MYKAD is selected
 * 
 * def defaultParams = [
	('name') : GlobalVariable.dummy_PersonalDetailsMYPR['name'],
	('idType') : idType,
	('nric') : GlobalVariable.dummy_PersonalDetailsMYPR['nric'],
	('race') : race,
	('mobileNumber') : GlobalVariable.dummy_PersonalDetailsMYPR['phoneNumber'],
	('email') : GlobalVariable.dummy_PersonalDetailsMYPR['email'],
	('dateOfBirth') : ""
]
 */

String race = GlobalVariable.dummy_PersonalDetailsMYKAD['race']
String idType = GlobalVariable.dummy_PersonalDetailsMYKAD['idType']
String nationality = GlobalVariable.dummy_PersonalDetailsMYKAD['nationality']

def defaultParams = [
	('name') : GlobalVariable.dummy_PersonalDetailsMYKAD['name'],
	('idType') : idType,
	('nric') : GlobalVariable.dummy_PersonalDetailsMYKAD['nric'],
	('nationality') : nationality,
	('race') : race,
	('phoneNumber') : GlobalVariable.dummy_PersonalDetailsMYKAD['phoneNumber'],
	('email') : GlobalVariable.dummy_PersonalDetailsMYKAD['email'],
	('dateOfBirth') : ""
]

params = defaultParams + params

def nameObjField = findTestObject('Object Repository/Hohh/Application Page/input_Name')
def idTypeObj = findTestObject('Object Repository/Hohh/Application Page/dydropdown_Details_text',
	[('text') : GlobalVariable.dyobj_ORAP001['idType']])
def idTypeSelection = findTestObject('Object Repository/Hohh/Application Page/dyselection_Details_text',
	[('text') : params['idType']])
def nricOrPassportInput = findTestObject('Object Repository/Hohh/Application Page/input_NRICOrPassport')
def nationalityObj = findTestObject('Object Repository/Hohh/Application Page/dydropdown_Details_text',
	[('text') : GlobalVariable.dyobj_ORAP001['nationality']])
def nationalitySelection = findTestObject('Object Repository/Hohh/Application Page/dyselection_Details_text',
	[('text') : params['nationality']])
def raceObj = findTestObject('Object Repository/Hohh/Application Page/dydropdown_Details_text',
	[('text') : GlobalVariable.dyobj_ORAP001['race']])
def raceSelection = findTestObject('Object Repository/Hohh/Application Page/dyselection_Details_text',
	[('text') : params['race']])
def dateOfBirthInput = findTestObject('Object Repository/Hohh/Application Page/input_DOB')
def mobileNumberInput = findTestObject('Object Repository/Hohh/Application Page/input_MobileNumber')
def emailInput = findTestObject('Object Repository/Hohh/Application Page/input_Email')

WebUI.setText(nameObjField, params['name'])
WebUI.enhancedClick(idTypeObj)
WebUI.enhancedClick(idTypeSelection)
WebUI.setText(nricOrPassportInput, params['nric'])

if (params['idType'] != "MYKAD") {
	WebUI.enhancedClick(nationalityObj)
	WebUI.enhancedClick(nationalitySelection)
}

if (params['idType'] == "PASSPORT") {
	WebUI.setText(dateOfBirthInput, params['dateOfBirth'])
}

WebUI.enhancedClick(raceObj)
WebUI.enhancedClick(raceSelection)

WebUI.setText(mobileNumberInput, params['phoneNumber'])
WebUI.setText(emailInput, params['email'])




