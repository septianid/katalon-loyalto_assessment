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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent

response = WS.sendRequest(findTestObject('Get Single User'))

email = WS.getElementPropertyValue(response, 'data.email')

RequestObject reqRegister = findTestObject('Register User')

def body = '{"email":"'+email+'", "password":"pistol"}'

println(body)

reqRegister.setBodyContent(new HttpTextBodyContent(body, 'UTF-8', 'application/json'))

response2 = WS.sendRequest(reqRegister)

def id = WS.getElementPropertyValue(response2, 'id')

String expectedToken = 'QpwL5tke4Pnpja7X'+id

WS.verifyElementPropertyValue(response2, 'id', GlobalVariable.id)
WS.verifyElementPropertyValue(response2, 'token', expectedToken)

