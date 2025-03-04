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

import org.openqa.selenium.Keys as Keys
import internal.GlobalVariable

//TODO - Add minimum and maximum checking -- For declaration value amount  

public class inputValidation {
	/**
	 * 
	 * @param testingMethod [
	 1: 'inputFieldPresent', (objectLocator) 
	 2: 'placeholderVisible', (objectLocator, [placeholderText (r)]) 
	 3: 'maxLength', (objectLocator, [loadWhenClick, allowedType (r)]) 
	 4: 'emptyInput', (objectLocator, [warningMessageTrigger (r), warningMessageLocator (r), expectedWarningText (r)])
	 5: 'allowedDataType', (objectLocator, [invalidType (r), testString, allowedSymbol])
	 6: 'spacing', (objectLocator, [haveSpace (r)])
	 7: 'characterLength', (objectLocator, [warningMessageLocator (r), warningMessageTrigger (r), validScenario (r), invalidScenario (r)])
	 8: 'ICFormat', (objectLocator, [NRIC (r)])
	 ]
	 * @param methodParams [
	 func1 : [:],
	 func2 : [:]
	 ]
	 * @return [
	 isPassed,
	 log
	 ]
	 */
	@Keyword
	def performValidation(TestObject objectLocator, def testingMethod = [], Map methodParams = [:]) {
		def passedCase = []
		def failedCase = []
		def errorCase = []

		def methodIndex = [
			1: 'inputFieldPresent',
			2: 'placeholderVisible',
			3: 'maxLength',
			4: 'emptyInput',
			5: 'allowedDataType',
			6: 'spacing',
			7: 'characterLength',
			8: 'ICFormat'
		]

		if (testingMethod.isEmpty()) {
			testingMethod = methodIndex.keySet() as List
		}

		// Run each specify function
		testingMethod.each { method ->
			String methodName = methodIndex[method]

			Map params = methodParams[method] ?: [:]

			/**
			 * result = [
			 * 		'status': 'pass',
			 * 		'text': ''
			 * 	]
			 * 
			 */
			Map result = this."$methodName"(objectLocator, params)

			String responseText = result['text']
			String response = "${methodName}: ${responseText}"

			if (result['status'] == 'pass') {
				passedCase.add(response)
			} else if (result['status'] == 'fail') {
				failedCase.add(response)
			} else if (result['status'] == 'error') {
				errorCase.add(response)
			} else {
				response = "${methodName}: the return status are not pass or fail, lead to error."
				errorCase.add(response)
			}
		}

		// Print result
		String passedLog = !passedCase.isEmpty() ? "Passed: " + passedCase.join('\n') : ""
		String failedLog = !failedCase.isEmpty() ? "Failed: " + failedCase.join('\n') : ""
		String errorLog = !errorCase.isEmpty() ? "Error: " + errorCase.join('\n') : ""

		String log = "${passedLog}\n${failedLog}\n${errorLog}"

		boolean isPassed = failedCase.isEmpty() && errorCase.isEmpty()
		println(log)
		return [isPassed, log]
	}

	/**
	 * @param objectLocator For checking its present
	 */
	def inputFieldPresent(def objectLocator, Map params = [:]) {
		def defaults = [
			:
		]

		def options = defaults + params

		def requiredParameter = []

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}


		//		WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/button_Get Quote Now'))

		boolean isPresent = WebUI.verifyElementPresent(objectLocator, 3, FailureHandling.OPTIONAL)

		if (isPresent) {
			//			String warningText = WebUI.getText(objectLocator)
			//			return warningText
			return [
				'status': 'pass'
			]
		} else {
			return  [
				'status': 'fail',
				'text': "${objectLocator} not present."
			]
		}
	}

	/**
	 * @param objectLocator For checking the present of placeholder
	 * @param params [
			'placeholderText': required 
		]
	 */
	def placeholderVisible(def objectLocator, Map params = [:]) {
		def defaults = [
			'placeholderText': null
		]

		def options = defaults + params

		def requiredParameter = [
				options.placeholderText
			]

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}

		String placeholder = ""
		try {
			placeholder = WebUI.getAttribute(objectLocator, 'placeholder')
		} catch (Exception e){
			return  [
				'status': 'error',
				'text': "${objectLocator} placeholder not valid."
			]
		}

		boolean isEqual = WebUI.verifyEqual(placeholder, options.placeholderText, FailureHandling.OPTIONAL)

		if (isEqual) {
			return [
				'status': 'pass'
			]
		} else {
			return  [
				'status': 'fail',
				'text': "${objectLocator} placeholder not valid."
			]
		}
	}

	/**
	 * @param objectLocator For checking the max length
	 * @param params [
			'loadWhenClick': optional,
			'allowedType': required
		]
	 */
	def maxLength(def objectLocator, Map params = [:]) {
		
		println("here")
		def defaults = [
			'loadWhenClick': false,
			'allowedType': null
		]

		def options = defaults + params

		def requiredParameter = [options.allowedType]

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}

		int maxLength = 0
		String maxLengthString = ""

		try {
			maxLengthString = WebUI.getAttribute(objectLocator, 'maxlength') ?: ""
		} catch (Exception e){
			return  [
				'status': 'error',
				'text': "${objectLocator} do not have maxlength attribute."
			]
		}

		if (maxLengthString.length() != 0) {
			maxLength = Integer.parseInt(maxLengthString)
		}

		String possibleText = ""
		def allowedTypeList = options.allowedType.split(',')
		allowedTypeList.each { type ->
			if (type == 'N') {
				possibleText += '1'
			} else if (type == 'S'){
				possibleText += '@'
			} else if (type == 'UL') {
				possibleText += 'A'
			} else if (type == 'LL') {
				possibleText += 'a'
			}
		}

		if (maxLength == 0) {
			return [
				'status': 'error',
				'text': "No maxlength attribute found."
			]
		}

		int inputTextLength = 0

		possibleText.toList().eachWithIndex { text, index ->
			assert index < possibleText.length() : "Bug detected, the field cannot be filled."

			String plateNumberText = String.valueOf(text).repeat(maxLength + 10)

			if (options.loadWhenClick) {
				WebUI.clearText(objectLocator)
				WebUI.sendKeys(objectLocator, Keys.chord(Keys.CONTROL, 'a'));
				WebUI.sendKeys(objectLocator, plateNumberText);
			} else {
				WebUI.setText(objectLocator, plateNumberText)
			}
			String inputText = WebUI.getAttribute(objectLocator, 'value')
			inputTextLength = inputText.length()
		}


		boolean isEqual = WebUI.verifyEqual(maxLength, inputTextLength)

		if (isEqual) {
			return [
				'status': 'pass'
			]
		} else {
			return  [
				'status': 'fail',
				'text': "The length input exceed max length allowed. Max length: ${maxLength}; Input length: ${inputTextLength}"
			]
		}
	}

	/**
	 * @param objectLocator For input
	 * @param params [
			warningMessageTrigger: required,
			warningMessageLocator: required,
			expectedWarningText: required
		]
	 */
	def emptyInput(def objectLocator, Map params = [:]) {
		def defaults = [
			warningMessageTrigger: null,
			warningMessageLocator: null,
			expectedWarningText: null
		]

		def options = defaults + params

		def requiredParameter = [
			options.warningMessageTrigger,
			options.warningMessageLocator,
			options.expectedWarningText
		]

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}

		WebUI.setText(objectLocator, "")
		WebUI.enhancedClick(options.warningMessageTrigger)
		
		String warningText = WebUI.getText(options.warningMessageLocator)
		

		boolean isEqual = WebUI.verifyEqual(warningText, options.expectedWarningText)

		if (isEqual) {
			return [
				'status': 'pass'
			]
		} else {
			return [
				'status': 'fail',
				'text': "Warning message for empty input not exists. Warning message: ${warningText}; Expected message: ${options.expectedWarningText}"
			]
		}
	}

	/**
	 * @param objectLocator For input
	 * @param params [
	 invalidType: required,    Invalid data type that should not appear on the input
	 testString: optional,
	 allowedSymbol: optional
	 ]
	 */
	def allowedDataType(def objectLocator, Map params = [:]) {
		//		TestObject textObject = findTestObject('Object Repository/Motorcar and Motorcycle/Quotation Page/input_Plate Number')

		//		(validation, errorMessage) = CustomKeywords.'inputValidation.validation.verifyValidData'('S', textObject)
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

		def defaults = [
			invalidType: null,
			testString: "123!@#ABCabc",
			allowedSymbol: null
		]

		def options = defaults + params

		def requiredParameter = [
			options.invalidType
		]

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}

		String allowedSymbol = options.allowedSymbol ?: ""
		String testString = options.testString + allowedSymbol

		println(testString)

		WebUI.setText(objectLocator, testString)
		String text = WebUI.getAttribute(objectLocator, 'value')

		String[] scenarios = options.invalidType.split(',')

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
							'status': 'fail',
							'text': "Invalid symbol given: ${c}",
						]
					}
				}
			} else if (scenario == "UL") {
				String symbol = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

				for (String c: text.toCharArray()) {
					if (symbol.indexOf(c) != -1) {
						return [
							'status': 'fail',
							'text': "Invalid alphabet given: ${c}"
						]
					}
				}
			} else if (scenario == "LL") {
				String symbol = "abcdefghijklmnopqrstuvwxyz"

				for (String c: text.toCharArray()) {
					if (symbol.indexOf(c) != -1) {
						return [
							'status': 'fail',
							'text': "Invalid alphabet given: ${c}"
						]
					}
				}
			} else if (scenario == "N") {
				String symbol = "0123456789"

				for (String c: text.toCharArray()) {
					if (symbol.indexOf(c) != -1) {
						return [
							'status': 'fail',
							'text': "Invalid number given: ${c}"
						]
					}
				}
			} else {
				return [
					'status': 'fail',
					'text': "Invalid scenario: ${scenario}"
				]
			}
		}
		return [
			'status': 'pass'
		]
	}

	/**
	 * @param objectLocator For input
	 * @param [
	 haveSpace: required   Specify if the input allow spacing
	 ]
	 */
	def spacing(def objectLocator, Map params = [:]) {
		def defaults = [
			haveSpace: null
		]

		def options = defaults + params

		def requiredParameter = [options.haveSpace]

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}

		WebUI.setText(objectLocator, ' ')

		int textCount = WebUI.getAttribute(objectLocator, "value").length()

		//Perform XNOR to check the true scenario of:
		// haveSpace is true and space is inputted, or
		// haveSpace is false and space is not inputted.

		//haveSpace textCount
		//true	0(false)	false
		//true	1(true)	true
		//false	0(false)	true
		//false	1(true)	false

		if (options.haveSpace == (textCount == 1)) {
			return [
				'status': 'pass'
			]
		} else if (options.haveSpace && !(textCount == 1)){
			// Allow space, but cannot input space
			return [
				'status': 'fail',
				'text': 'Space is allowed, but cannot input space'
			]
		} else if (options.haveSpace && !(textCount == 1)){
			// Not allow space, but can input space
			return [
				'status': 'fail',
				'text': 'Space is not allowed, but can input space'
			]
		} else {
			return [
				'status': 'error'
			]
		}
	}

	/**
	 * Get input string and verify if it meet the correct character length, by testing both correct and wrong scenario
	 * @param TextObject objectLocator input field locator
	 * @param params [
			warningMessageTrigger: required,
			warningMessageLocator: required,
			validScenario: required,
			invalidScenario: required
		]
	 */
	def characterLength(def objectLocator) {
		def defaults = [
			warningMessageTrigger: null,
			warningMessageLocator: null,
			validScenario: null,
			invalidScenario: null
		]
		def options = defaults + params

		def requiredParameter = [
			options.warningMessageTrigger,
			options.warningMessageLocator,
			options.validScenario,
			options.invalidScenario
		]

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}

		def testScenario = []
		
		testScenario.addAll(options.validScenario)
		testScenario.addAll(options.invalidScenario)
			

		def validLog = []
		def invalidLog = []


		for (int i=0; i<testScenario.length; i++) {
			WebUI.setText(objectLocator, testScenario[i])
			String text = WebUI.getAttribute(objectLocator, 'value')

			WebUI.enhancedClick(options.warningMessageTrigger)

			if (i==0) {
				boolean isNotPresent = WebUI.verifyElementNotPresent(options.warningMessageLocator, 3, FailureHandling.OPTIONAL)

				if (isNotPresent) {
					validLog.add("${options.validScenario} is passed")
				} else {
					invalidLog.add("${options.validScenario} is failed")
				}
			} else {
				boolean isPresent = WebUI.verifyElementPresent(options.warningMessageLocator, 3, FailureHandling.OPTIONAL)

				if (isPresent) {
					validLog.add("${options.invalidScenario} is passed")
				} else {
					invalidLog.add("${options.invalidScenario} is failed")
				}
			}
		}

		String validString = validLog.join('\n')
		String invalidString = invalidLog.join('\n')

		if (invalidLog.isEmpty()) {
			return [
				'status': 'pass'
			]
		} else {
			return [
				'status': 'fail',
				'text': invalidString
			]
		}
	}
	
	def ICFormat(def objectLocator, String NRIC) {
	//TODO
		WebUI.callTestCase(findTestCase('Travel/Reusable Module/Direct to Application Page'), [('TCarea') : 3, ('TCplan') : 1, ('TCtrip') : 1
			, ('TCpackage') : 1], FailureHandling.STOP_ON_FAILURE)
		
		WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))
		
		//WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), GlobalVariable.alphabet_Smallcase)
		WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), '000928070605')
		
		String nric_text = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), "value") ?: ""
		
		String dob_text = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/input_TravellerInfoDateOfBirth'), "value") ?: ""
		
		/* If the IC year is larger than the current year, the year in DOB will be 19' instead of 20.
		 * For example, 101010071110 (10) in IC with current year (2024) will result in 2010 as year in DOB.
		 *
		 * Returns: Lists of two date: date from NRIC and date from DOB in format of list: [day, month, year]
		 */
		String date1 = NRICInput.substring(0, 6)

		String year = date1.substring(0, 2)
		String originalYear = convertYear(year)
		String month = date1.substring(2, 4)
		String day = date1.substring(4, 6)

		String[] splitdate1 = [day, month, originalYear]
		String[] date2 = DOB.split("/")

		return [splitdate1, date2]
		
		
		System.out.println(date1 + " and " + date2)
		
		String[] dateMessage = ['day', 'month', 'year']
		
		WebUI.closeBrowser()
		
		for (int i=0; i<date1.length; i++) {
			assert date1[i] == date2[i] : "${dateMessage[i]} does not match"
		}
		
		//adassa
		
		WebUI.enhancedClick(findTestObject('Travel/TripCare360/English/AppPage/button_EditPolicyOwner'))
		
		WebUI.enhancedClick(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_Male'))
		
		WebUI.setText(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), '000928070605')
		
		String NRIC = WebUI.getAttribute(findTestObject('Travel/TripCare360/English/AppPage/input_PassportOrIdentityNumber'), 'value')
		
		maleButtonColor = WebUI.getCSSValue(findTestObject('Object Repository/Travel/TripCare360/English/AppPage/button_Male'), 'background-color')
		
		femaleButtonColor = WebUI.getCSSValue(findTestObject('Travel/TripCare360/English/AppPage/button_Female'),
			'background-color')
		
		int NRICGender = CustomKeywords.'applicationPage.verification.verifyNRICwithGender'(NRIC)
		
		WebUI.closeBrowser()
		
		println(maleButtonColor)
		println(femaleButtonColor)
		switch(NRICGender) {
			case 0:
				assert femaleButtonColor == GlobalVariable.validation_button_color
			case 1:
				assert maleButtonColor == GlobalVariable.validation_button_color
		}
		
	}
}
