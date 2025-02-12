package applicationPage

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

import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.webui.driver.DriverFactory
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

import internal.GlobalVariable

public class API {
	// Function to make API calls
	@Keyword
	def callApi(String link, String requestBody) {
		JavascriptExecutor js = (JavascriptExecutor)DriverFactory.getWebDriver()
		def response = js.executeScript('''
        var xhr = new XMLHttpRequest();
        xhr.open("POST", arguments[0], false);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(arguments[1]);
        return {status: xhr.status, responseText: xhr.responseText};
    ''', link, requestBody)

		//		println("API Response: " + response)
		return response
	}

	// Helper functions to parse API response
	@Keyword
	def getApiStatus(Map responseMap) {
		def apiStatus = responseMap.get("status")
		//		println("apiStatus : ${apiStatus}")
		return apiStatus
	}

	@Keyword
	def getApiMessage(Map responseMap) {
		def jsonMap = responseMap.get("responseText")
		//		println("Api Details : " + jsonMap)
		def jsonResponse = (Map)new JsonSlurper().parseText(jsonMap)
		def message = jsonResponse.get("message")
		//		println("Message : ${message}")
		return message
	}

	@Keyword
	def getApiData(Map responseMap) {
		def jsonMap = responseMap.get("responseText")
		//		println("Api Details : " + jsonMap)
		def jsonResponse = (Map)new JsonSlurper().parseText(jsonMap)
		def data = jsonResponse.get("data")
		//		println("Data : ${data}")
		return data
	}
}
