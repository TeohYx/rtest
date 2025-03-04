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

// Quotation page input button
TestObject inputButton = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/label_ID Type_NRIC')

// Quotation page plate number
String plateNumber = GlobalVariable.rules_carPlate['category1']

// Input fields and text after the input button
Map quotationInputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_NRIC')
            , ('inputText') : '900101070110']]]

Map planPersonalDetailsInputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Name')
            , ('inputText') : 'Testt'], [('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Email')
            , ('inputText') : 'yeexian_tyx@hotmail.com'], [('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Mobile Number')
            , ('inputText') : '1139519168'], [('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Home Address 1')
            , ('inputText') : 'Testt'], [('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Home Address 2')
            , ('inputText') : 'Testt'], [('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Postcode')
            , ('inputText') : '11500']], ('buttonField') : [[('buttonClick') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Ethnicity')
            , ('buttonSelection') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Ethnicity_Chinese')]
        , [('buttonClick') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Marital Status'), ('buttonSelection') : findTestObject(
                'Object Repository/Plan Page/3 - Personal Details/button_Marital Status_Single')]]]

Map planBankAccountDetailsInputScenario = [('inputField') : [[('inputObject') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/4 - Bank Accounts Details/input_Account Number')
            , ('inputText') : '123412341234']], ('buttonField') : [[('buttonClick') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/4 - Bank Accounts Details/button_Bank Name')
            , ('buttonSelection') : findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/4 - Bank Accounts Details/button_Bank Name_MayBank')]]]

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC003_RM_PF_Direct to Declaration Page'), [('plateNumber') : plateNumber
        , ('quotationInputButton') : inputButton, ('quotationInputScenario') : quotationInputScenario, ('planPersonalDetailsInputScenario') : planPersonalDetailsInputScenario
        , ('planBankAccountDetailsInputScenario') : planBankAccountDetailsInputScenario], FailureHandling.STOP_ON_FAILURE)

