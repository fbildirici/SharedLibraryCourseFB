package com.example.sharedlibrary

import groovy.json.JsonSlurper
import groovy.json.JsonOutput

class Util {

    static String getGreeting(String name) {
        return "Hello, ${name}!"
    }

    static def getJsonResponse(String url) {
        def response = new URL(url).text
        return new JsonSlurper().parseText(response)
    }

    static def sendHttpPost(String url, String payload) {
        def connection = new URL(url).openConnection()
        connection.setRequestMethod("POST")
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        def writer = new OutputStreamWriter(connection.outputStream)
        writer.write(payload)
        writer.flush()
        def responseCode = connection.getResponseCode()
        def response = connection.inputStream.text
        writer.close()
        return response
    }
}

