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

String warningMessage = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("Travel", "wrnmsg.empty", "EN")

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 3, ('TCplan') : 1, ('TCtrip') : 1
	, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoDepartureFlightNumber'), 'MH370')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_DepartureFlightDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_NextMonthFlight'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : '1']))

WebUI.setText(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/input_ReturnFlightNumber'), 'MH17')

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ReturnFlightDate'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_NextMonthFlight'))

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/General/dybutton_Date_date', [('date') : '28']))

WebUI.scrollToElement(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'), 0)

WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_ConfirmDetails'))

WebUI.verifyElementPresent(findTestObject('Travel/TripCare360/English/AppPage/dywrnmsg_BankAccountNo_wrnmsg', [('wrnmsg') : warningMessage]), 
    5)

WebUI.takeFullPageScreenshot(FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

