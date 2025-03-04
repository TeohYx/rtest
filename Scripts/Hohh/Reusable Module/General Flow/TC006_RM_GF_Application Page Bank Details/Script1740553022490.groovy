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
 * 1. Bank name
 * 2. Saving account no
 * 
 * def defaultParams =  [
	('bankName') : bankName,
	('savingAccount') : GlobalVariable.dummy_BankDetails['savingNumber']
]
 */

String bankName = GlobalVariable.dummy_BankDetails['bankName']

def defaultParams = [
	('bankName') : bankName,
	('savingAccount') : GlobalVariable.dummy_BankDetails['savingNumber']
]

params = defaultParams + params

def bankNameObj = findTestObject('Object Repository/Hohh/Application Page/dydropdown_Details_text',
	[('text') : GlobalVariable.dyobj_ORAP001['bankName']])
def bankNameSelection = findTestObject('Object Repository/Hohh/Application Page/dyselection_Details_text',
	[('text') : params['bankName']])
def savingAccountInput = findTestObject('Object Repository/Hohh/Application Page/input_BankDetailAccountNumber')

WebUI.enhancedClick(bankNameObj)
WebUI.enhancedClick(bankNameSelection)

WebUI.setText(savingAccountInput, params['savingAccount'])
