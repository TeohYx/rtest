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

/**
 * Verify the correct display of add-ons
 * 
 * For Building+Contents -> Home Building get estimate cost reveal; Home Content get estimate cost reveal
 * For Building only -> Home Building get estimate cost reveal
 * For Contents -> Home Content get estimate cost reveal
 * 
 */

def buildingAndContent = findTestObject('Object Repository/Hohh/Plan Page/button_BuildingAndContents')
def buildingOnly = findTestObject('Object Repository/Hohh/Plan Page/button_BuildingOnly')
def contentsOnly = findTestObject('Object Repository/Hohh/Plan Page/button_ContentsOnly')

def buildingSection = findTestObject('Object Repository/Hohh/Plan Page/dybox_GetEstimateCostBlock_text',
		[('text') : GlobalVariable.dyobj_ORPP002['buildingBlock']])
def contentSection = findTestObject('Object Repository/Hohh/Plan Page/dybox_GetEstimateCostBlock_text',
		[('text') : GlobalVariable.dyobj_ORPP002['contentBlock']])

// Combination
Map protection = [
		'buildingAndContents' : [buildingAndContent, [buildingSection, contentSection]],
		'buildingOnly' : [buildingOnly, [buildingSection]],
		'contentsOnly' : [contentsOnly, [contentSection]]
	]
	
def errorLog = []

// Start
WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [:], FailureHandling.STOP_ON_FAILURE)

protection.each { key, value ->
	WebUI.enhancedClick(value[0])
	
	value[1].each { addons ->
		boolean isPresent = WebUI.verifyElementPresent(addons, 3, FailureHandling.OPTIONAL)	
		
		if (!isPresent) {
			errorLog.add("${key}: Its tab does not showing.")
		}
	}	
}

WebUI.takeScreenshot()
WebUI.closeBrowser()

String log = errorLog.join('\n')
assert errorLog.isEmpty() : log
