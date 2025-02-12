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
import org.seleniumhq.jetty9.util.thread.SerializedExecutor.ErrorHandlingTask as Keys
import com.kms.katalon.core.util.KeywordUtil

/*
 * Domestic Plan does not covered any adventurous addon or covid.
 *
 */
def errorMessage = []

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Plan Page'), [('TCarea') : 1, ('TCplan') : 1, ('TCtrip'): 1], FailureHandling.STOP_ON_FAILURE)

Boolean haveNoCovid = WebUI.verifyElementNotPresent(findTestObject('Travel/TripCare360/English/AppPage/button_AddonCovid19'),
	3, FailureHandling.OPTIONAL)
Boolean haveNoAdventurous = WebUI.verifyElementNotPresent(findTestObject('Travel/TripCare360/English/AppPage/button_AddonAdventurousActivities'),
	3, FailureHandling.OPTIONAL)

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()

if (!(haveNoCovid)) {
    errorMessage.add('The covid add on is exists, where it should not be')
}

if (!(haveNoAdventurous)) {
    errorMessage.add('The adventurous add on is exists, where it should not be.')
}

assert errorMessage.isEmpty() : KeywordUtil.markFailed(errorMessage.join(';'))

