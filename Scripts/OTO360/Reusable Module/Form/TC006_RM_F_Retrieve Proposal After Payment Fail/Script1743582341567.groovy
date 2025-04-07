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
 * Bank Details Form
 * @author 80012455
 *
 * 1. Info
 * - NRIC number
 * - Click button to return to review page
 *
 * -------------------------------------------------------------------------
 * Data - By the default the data is set to choose on MayBank (Malayan Banking Berhad)
 *
 * params = {
		  ic: string (Required),
   }
 *
 */

String retrieveProposal = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "submit.retrieveProposal", "EN")

WebUI.setText(findTestObject('Object Repository/General/input_retrieveIc'), params['ic'])

WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_button',
	[('button'): retrieveProposal]))