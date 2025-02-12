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

/**
 * Verify the functionality of "Market Value" and "Agreed Value"
 * 
 * @pass "Agreed Value" amount is higher than "Market Value" amount
 * @fail "Agreed Value" is same or lower than "Market Value" amount
 */

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC005_RM_PF_Direct to Plan Page Specific'), [:], FailureHandling.STOP_ON_FAILURE)

// Get market value amount
WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/button_All Riders Or Market Value'))
String marketValue = WebUI.getText(findTestObject('Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Total Payable'))

// Get agreed value amount
WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/button_Single Rider Or Agreed Value'))
String agreedValue = WebUI.getText(findTestObject('Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Total Payable'))

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert agreedValue > marketValue : KeywordUtil.markFailedAndStop("The agreed value is not higher than market value. \nAgreed Value: ${agreedValue}\nMarket Value: ${marketValue}")

