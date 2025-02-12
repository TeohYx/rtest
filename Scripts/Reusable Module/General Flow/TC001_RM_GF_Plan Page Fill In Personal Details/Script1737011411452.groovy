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

String name = 'Tan Sing Ma'
String email = 'yeexian_tyx@hotmail.com'
String phone = '1139519168'
String address1 = 'qwert'
String address2 = '12345'
String postcode = '11500'


WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/button_Personal Details'))

WebUI.setText(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Name'), name)

WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Ethnicity'))

WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Ethnicity_Chinese'))

WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Marital Status'))

WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Marital Status_Single'))

WebUI.setText(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Email'), email)

WebUI.setText(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Mobile Number'), phone)

WebUI.setText(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Home Address 1'), address1)

WebUI.setText(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Home Address 2'), address2)

WebUI.setText(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/input_Postcode'), postcode)

WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Save Personal Details'))

WebUI.delay(1)

