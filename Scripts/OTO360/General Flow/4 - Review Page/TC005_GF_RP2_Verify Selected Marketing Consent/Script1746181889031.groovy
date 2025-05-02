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

reviewParams = [
	('declaration'): true,
	('marketingConsent'): true,
	('paymentMethod') : findTestObject('Object Repository/OTO360/Review Page/button_FPX')
]

String toPayment = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "submit.toPayment", "EN")

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC004_RM_PF_Direct to Review Page'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC007_RM_F_Review Form'), [('params') : reviewParams], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_submit',
	[('submit'): toPayment]))

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('Object Repository/General/Make Payment/button_PayNetOption'), 10)

WebUI.takeFullPageScreenshot()
WebUI.closeBrowser()
