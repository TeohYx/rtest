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

/*
 * 1. Precondition (setup steps before execution)
 * 2. Test Steps
 * 3. Verification Steps (Assertions)
 * 4. Cleanup setups
 * 
 */

/*
 * Open Browser
 * WebUI.openBrowser('')
 * WebUI.navigateToUrl('')
 * 
 * After button is click
 * WebUI.waitForElementPresent() or waitForElementVisible() - instead of delay()
 * 
 * Use Test Data for Reusability
 * findTestData('').getValue()
 * 
 * Use Keyword for Repetitive Actions, or TestCase 
 * CustomKeywords.'customKeywords.dummy.dummy'('', '')
 * 
 * Use dynamic object repository
 * 
 * Implement Logging for Debugging
 * KeywordUtil.logInfo('')
 * 
 * Verification (Assertion)
 * VerifyTextPresent
 * VerifyElementAttribute
 * VerifyEqual
 * KeywordUtil()
 * (avoid using assert, as it is not reveal in Katalon TestObs)
 * 
 * 
 * Cleanup
 * WebUI.closeBrowser()
 * 
 * ** If change of page causing error (stale element etc)
 * try:
 * 1. Any wait function
 * 2. delay(1)
 * 
 * 
 * SETTING FOR PROJECT WEBUI
 * Delay between action : 0ms
 * Smart wait : Disabled
 * Smart locator : Disabled
 * Time capsule : Disabled
 * 
 * 
 * 
 * 
 */



