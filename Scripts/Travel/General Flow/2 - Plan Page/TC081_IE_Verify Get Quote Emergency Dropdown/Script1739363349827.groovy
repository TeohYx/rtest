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

WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Plan Page'), [('TCarea') : 2, ('TCplan') : 1, ('TCtrip') : 1],
	FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/Plan Page/dydropdown_PlanBenefits_name', [('name') : GlobalVariable.dyobj_Plan_BenefitsDropdown['emergencyServices']]))

WebUI.verifyElementPresent(findTestObject('Object Repository/Travel/TripCare360/English/Plan Page/dyvalidation_PlanBenefits_name', [('name') : GlobalVariable.dyobj_Plan_BenefitsValidation['emergencyServices']]), 
    3)

WebUI.takeFullPageScreenshot(FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

