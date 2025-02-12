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
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

/*
 * Check 3 motorcycle variant and make sure each of them have different pricing. 
 * 
 * If both show different price, the test case pass, else fail.
 * 
 */
totalPayableAmount = []

int attempt = 3

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC005_RM_PF_Direct to Plan Page Specific'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < attempt; i++) {
    WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/button_Motorcycle Details View', [('detail_text') : GlobalVariable.obj_plan_detailsView]))
    WebUI.enhancedClick(findTestObject('Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/button_Motorcycle Variant'))
    WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/dropdown_Vehicle Variant', [('index') : i + 1]))
    WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/button_Save Motorcycle Details'))
    totalPayableAmount.add(WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Total Payable')))
}

WebUI.takeScreenshot()
WebUI.closeBrowser()

// If equal, means the list did not contains duplicate number, hence true. Else the number will be different
assert totalPayableAmount.size() == totalPayableAmount.toSet().size() : KeywordUtil.markError("The list contains same amount of data: ${totalPayableAmount.size()}")

