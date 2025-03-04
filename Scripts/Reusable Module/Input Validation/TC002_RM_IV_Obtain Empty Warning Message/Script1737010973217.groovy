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

String classAttribute = ""

try {
	classAttribute = WebUI.getAttribute(findTestObject('Motorcar and Motorcycle/General/text_PageTitle', [('section') : 'Details & Add Ons']), 'class')
} catch (Exception e) {
	classAttribute = 'quotation'
}

String currentTab = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/validation_CurrentTab'), FailureHandling.OPTIONAL)

def hasClass = ['fw-bold'].any({ def className ->
		classAttribute.contains(className)
	})

if (!hasClass) {
	WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/button_Get Quote Now'))
} else {
	if (currentTab.contains("Bank Account Details")) {
		WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/4 - Bank Accounts Details/button_Save Bank Details'))
	} else if (currentTab.contains("personal details")) {
		WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/3 - Personal Details/button_Save Personal Details'))
	} else if (currentTab.contains("Share your car details") || currentTab.contains("Share your motorcycle details")) {
		WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/2 - Motorcycle Details/button_Save Motorcycle Details'))
	}
}

try{
	WebUI.verifyElementPresent(objectLocator, 3, FailureHandling.OPTIONAL)
} catch (e){
	return ""
}

String warningText = WebUI.getText(objectLocator)
return warningText


