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
 * Verify the access to payment page (MPAY) by using information of 'TC006_RM_PF_Direct to Declaration Page Specific'
 *
 * @validation Verify if 'PayNet button' exists
 * @pass 'PayNet button' exists
 * @fail 'PayNet button' not exists (suggesting the page direct to other page)
 */

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC006_RM_PF_Direct to Declaration Page Specific'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Declaration Page/button_Declaration'))
WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Declaration Page/button_FPX'))
WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Declaration Page/button_Pay Now'))

Boolean verifyPayment = WebUI.verifyElementPresent(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/button_PayNet'), 10, FailureHandling.OPTIONAL)

String link = WebUI.getUrl()

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert verifyPayment : "The PayNet does not exist, suggest that the page did not direct to payment page.\nThe link: ${link}"