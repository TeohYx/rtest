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

/*
 * When non domestic or non senior are selected:
 * - Both silver, gold and platinum plan are present
 * - Covid are not present, but adventurous activities are present
 * 
 */

String planSite = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "currentSite.plan", "EN")
String applicationSite = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "currentSite.application", "EN")
String buttonBuy = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "button.buy", "EN")

def errorMessage = []

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Plan Page'), [('TCarea') : 2, ('TCplan') : 1, ('TCtrip'): 2], FailureHandling.STOP_ON_FAILURE)

String planClass = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/General/dytext_ProveOfCurrentPage_currentSite', [('currentSite') : planSite]), 
    'class')

assert planClass.contains('fw-bold') : KeywordUtil.markFailed('The page has crushed or facing issues')

if (!(WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Plan Page/button_SilverPlan'), 3, FailureHandling.OPTIONAL))) {
    errorMessage.add('Silver plan does not exists')
}

if (!(WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Plan Page/button_GoldPlan'), 3, FailureHandling.OPTIONAL))) {
    errorMessage.add('Gold plan does not exists')
}

if (!(WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/Plan Page/button_PlatinumPlan'), 3, FailureHandling.OPTIONAL))) {
    errorMessage.add('Platinum plan does not exists')
}

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Plan Page/dysubmit_Buy_button', [('button') : buttonBuy]))

WebUI.delay(2)

String applicationClass = WebUI.getAttribute(findTestObject('Object Repository/Travel/TripCare360/English/General/dytext_ProveOfCurrentPage_currentSite', [('currentSite') : applicationSite]), 
    'class')

assert applicationClass.contains('fw-bold') : 'The page has crushed or facing issues'

if (!(WebUI.verifyElementNotPresent(findTestObject('Travel/TripCare360/English/AppPage/button_AddonCovid19'), 3, FailureHandling.OPTIONAL))) {
    errorMessage.add('Covid add on plan does not exists')
}

if (!(WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/AppPage/button_AddonAdventurousActivities'), 3, FailureHandling.OPTIONAL))) {
    errorMessage.add('Adventurous activities add on plan does not exists')
}

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()

assert errorMessage.isEmpty() : KeywordUtil.markFailed(errorMessage.join(';'))

