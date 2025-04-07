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

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Filled Form - Myself'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'), 0)

WebUI.delay(1)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/Review Page/link_ImportantNotice'))

WebUI.switchToWindowIndex(1)

String url = WebUI.getUrl()

Boolean isMatch = url.contains('ImportantNotice_Eng_Ins-ac0fd20ec2e0ee8bcba92c6874792163.pdf')

WebUI.delay(5)

WebUI.takeFullPageScreenshot(FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

assert isMatch : "The pdf is incorrect: The url: ${url}"