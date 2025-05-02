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

String site = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "currentSite.plan", "EN")

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Quotation Form'), [('TCarea') : 1, ('TCplan') : 1, ('TCtrip'): 1], FailureHandling.STOP_ON_FAILURE)

String classValue = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/General/dytext_ProveOfCurrentPage_currentSite', [('currentSite') : site]), 'class')

assert classValue.contains('fw-bold') : 'The page does not direct to Plan Page, as its text is not bold'

