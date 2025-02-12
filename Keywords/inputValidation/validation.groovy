package inputValidation

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

class validation {

	/**
	 * @param scenario def The calendar date in type Calendar object 
	 * @return The 12-digit NRIC string 
	 */
	@Keyword
	def inputNRICandProceed(def scenario) {
		String NRIC = ""
		String year = (scenario.get(Calendar.YEAR) % 100).toString()

		if (year.length() == 1) {
			year = "0" + year
		}

		String month = (scenario.get(Calendar.MONTH) + 1).toString()

		if (month.length() == 1) {
			month = "0" + month
		}

		String day = scenario.get(Calendar.DAY_OF_MONTH).toString()

		NRIC = "${year}${month}${day}070605"

		return NRIC
	}

	/**
	 * Get input string and verify if it meet the correct character length, by testing both correct and wrong scenario
	 * @param textObject TestObject input field locator
	 * @param warningMessageLocator TestObject warning message for the input field of 'textObject'
	 * @param submitLocator TestObject submit locator to validate the input 
	 * @param validScenario String 1 valid scenario
	 * @param invalidScenario String 1 invalid scenario
	 * @return a boolean to indicate whether the PB section of given input field are valid, never false as it is always run into failure instead of return false
	 *
	 */
	@Keyword
	def verifyCharacterLength(TestObject textObject, TestObject warningMessageLocator, TestObject submitLocator, String validScenario, String invalidScenario) {

		String[] testScenario = [
			validScenario,
			invalidScenario
		] as String[]
		println(testScenario)
		for (int i=0; i<testScenario.length; i++) {
			WebUI.setText(textObject, testScenario[i])
			String text = WebUI.getAttribute(textObject, 'value')

			WebUI.enhancedClick(submitLocator)

			if (i==0) {
				WebUI.verifyElementNotPresent(warningMessageLocator, 3, FailureHandling.STOP_ON_FAILURE)
			} else {
				WebUI.verifyElementPresent(warningMessageLocator, 3, FailureHandling.STOP_ON_FAILURE)
			}
		}

		return true
	}

	/**
	 * Get string input and verify if the data type is valid
	 * @param invalidType String Expression for valid data type representing with a letter
	 * 		e.g: "S,UL,LL"
	 * 		e.g: S - symbol; UL - uppercase letter; LL - lowercase letter; N - number
	 *  	e.g: S - `~!@#$%^&*()_+-={}|[]\\;':\"<>?,./
	 *      e.g: UL - ABCDEFGHIJKLMNOPQRSTUVWXYZ
	 *      e.g: LL - abcdefghijklmnopqrstuvwxyz
	 *      e.g: N - 0123456789
	 * @param textObject TestObject input field locator that contain input
	 * @param params Map Mapping of 'testString' - test text and 'allowedSymbol' - allowed symbol/character
	 * @return a boolean to indicate whether the data type of given input is valid
	 *
	 */
	@Keyword
	def verifyValidData(String invalidType, TestObject textObject, Map params = [:]) {
		def defaults = [
			testString: "123!@#ABCabc",
			allowedSymbol: null
		]

		def options = defaults + params

		String allowedSymbol = options.allowedSymbol ?: ""
		String testString = options.testString + allowedSymbol

		println(testString)

		WebUI.setText(textObject, testString)
		String text = WebUI.getAttribute(textObject, 'value')

		String[] scenarios = invalidType.split(',')

		for (String scenario: scenarios) {

			scenario = scenario.trim()

			if (scenario.startsWith("S")) {
				String symbol = "`~%!@#\$^&*()_+-={}|[]\\;':\"<>?,./"

				// Ignore allowed symbol in some case
				for (String c: allowedSymbol.toCharArray()) {
					symbol = symbol.replace(c, '')
				}

				for (String c: text.toCharArray()) {
					if (symbol.indexOf(c) != -1) {
						return [
							false,
							"Invalid symbol given: ${c}"
						]
					}
				}
			} else if (scenario == "UL") {
				String symbol = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

				for (String c: text.toCharArray()) {
					if (symbol.indexOf(c) != -1) {
						return [
							false,
							"Invalid alphabet given: ${c}"
						]
					}
				}
			} else if (scenario == "LL") {
				String symbol = "abcdefghijklmnopqrstuvwxyz"

				for (String c: text.toCharArray()) {
					if (symbol.indexOf(c) != -1) {
						return [
							false,
							"Invalid alphabet given: ${c}"
						]
					}
				}
			} else if (scenario == "N") {
				String symbol = "0123456789"

				for (String c: text.toCharArray()) {
					if (symbol.indexOf(c) != -1) {
						return [
							false,
							"Invalid number given: ${c}"
						]
					}
				}
			} else {
				return [
					false,
					"Invalid scenario: ${scenario}"
				]
			}
		}
		return [true, ""]
	}

	@Keyword
	def verifyLeadingInput(String input, String leadingInput) {
		if(!input.startsWith(leadingInput)) {
			return [
				false,
				"${input} not starts with ${leadingInput}"
			]
		}
		return [true, ""]
	}
}





