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
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.common.WebUiCommonHelper

String policyNumberPrefix = GlobalVariable.obj_product
Boolean isCorrectPolicy = false

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC004_RM_PF_Direct to Payment Page'), [:], FailureHandling.STOP_ON_FAILURE)

List<WebElement> elements = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/Motorcar and Motorcycle/Payment Page/txt_Product'), 3)

def policyNumberList = []
elements.each { element -> 
	String policyNumber = element.getText()

	if (policyNumber.contains(policyNumberPrefix)) {
		isCorrectPolicy = true
	}	
	
	policyNumberList.add(policyNumber)
}
WebUI.takeScreenshot()
WebUI.closeBrowser()

assert isCorrectPolicy : "The policy is incorrect. \nPolicy number: ${policyNumberList}"