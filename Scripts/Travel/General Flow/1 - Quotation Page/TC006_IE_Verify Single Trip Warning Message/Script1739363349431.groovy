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

Map errorMessage = GlobalVariable.errorMessage_quotationSingleInfo

Boolean isPassed = true

def failedSelection = []

String buttonPlan = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "button.toPlanPage", "EN")

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/button_SingleTrip'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Quotation Page/dysubmit_GetQuoteNow_button',
	[('button'): buttonPlan]))

errorMessage.each({ def selection, def message ->
        KeywordUtil.logInfo("Error message for selection $selection: $message")

        isPresent = WebUI.verifyTextPresent(message, false, FailureHandling.OPTIONAL)

        if (!(isPresent)) {
            isPassed = false

            failedSelection.add([selection, message])
        }
    })

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

String log = failedSelection.join('\n')

assert isPassed : KeywordUtil.logInfo("Some error message is not showing:\n${log}")

