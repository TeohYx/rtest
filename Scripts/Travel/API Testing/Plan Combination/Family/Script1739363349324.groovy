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
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

/*
 * Number of test cases scenario
 * = Number of plans (rows) under PlanType 4 in 'Plan' sheet of 'PlanCombination'
 * 		+ Number of additional week (DateClass 5 in 'Plan' sheet of 'PlanCombination')
 * 			* (Number of additional week (DateClass 5 in 'DateDuration' sheet of 'PlanCombination' - 1)
 * 		+ Number of adventurous activities (rows) under PlanType 4 in 'Adventurous Activities' sheet of 'PlanCombination'
 * 		+ Number of covid (rows) under PlanType 4 in 'Covid' sheet of 'PlanCombination'
 * 			* (Number of additional week (DateClass 5 in 'DateDuration' sheet of 'PlanCombination' - 1)
 * = 58 + [9 * (2 - 1)] + 9 + 15 + [3 * (2 - 1)]
 * = 67 + 9 + 18
 * = 94
 *
 */

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

int planType = 4

def cPObj = CustomKeywords.'applicationPage.CombinationPrice.initiateCombinationPrice'(planType)

CustomKeywords.'applicationPage.CombinationPrice.countTestCase'(cPObj)

CustomKeywords.'applicationPage.CombinationPrice.planPriceChecker'(cPObj)

KeywordUtil.logInfo("Total test case: $cPObj.totalCaseCount")
KeywordUtil.logInfo("Test case succeed: $cPObj.successCount")
KeywordUtil.logInfo("Test case failed: $cPObj.failedCount")
KeywordUtil.logInfo("Error Log: $cPObj.errorLog")
KeywordUtil.logInfo("Fail Log: $cPObj.failedLog")

WebUI.closeBrowser()

assert cPObj.totalCaseCount == cPObj.successCount : "There are ${cPObj.failedCount} failed cases. \nError Log: ${cPObj.errorLog} \nFailed Log: ${cPObj.failedLog}"

