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
import com.kms.katalon.core.util.KeywordUtil

String covid = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "covidValidation.covid", "EN")

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Quotation Form'), [('TCarea') : 2, ('TCplan') : 1, ('TCtrip'): 1], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/link_FindOutMore'))

element = findTestObject('Travel/TripCare360/English/Plan Page/dyvalidation_CovidTab_covidValidation', [('covidValidation') : covid])
boolean isPresent = WebUI.verifyElementVisible(element, FailureHandling.OPTIONAL)

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()

println(element)
assert isPresent : KeywordUtil.markFailed("The Covid Tab does not expanded. ${element}")