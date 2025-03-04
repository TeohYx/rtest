package verifcation

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class verification {
	
	/**
	 * Similar to verifyElementPresent()
	 * @param obj object to verify
	 * @return true or false
	 */
	@Keyword
	def normalVerificationOnElementPresent(TestObject obj) {
		boolean isPresent = WebUI.verifyElementPresent(obj, GlobalVariable.constant_defaultWaitTime, FailureHandling.OPTIONAL)
		
		return isPresent
	}
	
	@Keyword
	def verifyCurrentPage(TestObject obj) {
		
		String elementClass = WebUI.getAttribute(findTestObject('Object Repository/Hohh/ORM001_dyvalidation_CurrentPage_text',
													[('text') : GlobalVariable.dyobj_CurrentPage_ORM001['plan']]), 'class')
		
		return elementClass.contains('fw-bold')
	}
}
