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
import java.text.DecimalFormat as DecimalFormat

/**
 * Verify if the NCD discount is applied properly
 * 
 * @pass the discounted amount is properly applied
 * @fail the discounted amount is not same as the display amount
 */

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC005_RM_PF_Direct to Plan Page Specific'), [:], FailureHandling.STOP_ON_FAILURE)

DecimalFormat df = new DecimalFormat('#.##')

String NCDText = WebUI.getText(findTestObject('Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_NCD Text'))
String basicPremium = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Basic Premium'))
String grossPremium = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Gross Premium'))
int NCDAmount = Integer.parseInt(NCDText.replaceAll('[^0-9]', ''))
double NCDPercent = NCDAmount / 100
double basicPremiumAmount = Double.parseDouble(basicPremium.replaceAll('[RM,]', ''))
String grossPremiumAmount = grossPremium.replaceAll('[RM,]', '')

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert grossPremiumAmount == df.format(basicPremiumAmount * (1 - NCDPercent))

