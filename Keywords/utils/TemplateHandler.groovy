package utils

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

import com.kms.katalon.core.configuration.RunConfiguration
import groovy.text.SimpleTemplateEngine

import com.kms.katalon.core.testobject.ConditionType

public class TemplateHandler {
	// Process template with variables
	String process(String template, Map binding) {
		def engine = new SimpleTemplateEngine()
		return engine.createTemplate(template).make(binding).toString()
	}

	@Keyword
	def getXPath(String objectName, String binding) {
		TestData data = findTestData('ObjectRepository')
		data.changeSheet('Object Repository')

		for (int i=1; i<=data.getRowNumbers(); i++) {
			if (data.getValue('objectName', i) == objectName) {
				String dataXPath = data.getValue('xpath', i)
				String dataBinding = data.getValue('binding', i)
				Map bindingMap = [:]
				bindingMap[dataBinding] = binding

				String xpathResult = process(dataXPath, bindingMap)

				def xpathLocator = new TestObject('dynamicObject').addProperty('xpath', ConditionType.EQUALS, xpathResult)
				println(xpathLocator.xpaths)
				return xpathLocator
			}
		}
	}
}
