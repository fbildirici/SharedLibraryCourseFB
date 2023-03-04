import groovy.json.JsonSlurperClassic
import groovy.json.JsonOutput
import org.apache.http.HttpEntity
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.ContentType
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

def call(String url, Map headers = [:], String method = 'GET', String body = null) {
  CloseableHttpClient httpClient = HttpClientBuilder.create().build()
  try {
    if (method == 'GET') {
      HttpGet request = new HttpGet(url)
      headers.each { k, v -> request.addHeader(k, v) }
      CloseableHttpResponse response = httpClient.execute(request)
      return response
    } else if (method == 'POST') {
      HttpPost request = new HttpPost(url)
      headers.each { k, v -> request.addHeader(k, v) }
      request.entity = new StringEntity(body, ContentType.APPLICATION_JSON)
      CloseableHttpResponse response = httpClient.execute(request)
      return response
    }
  } catch (Exception ex) {
    println ex
  } finally {
    httpClient.close()
  }
}

def closeIssues(String jiraUrl, String jiraUser, String jiraToken, String gitTag) {
  def issuesUrl = "${jiraUrl}/rest/api/2/search?jql=resolution=Fixed%20AND%20fixVersion=${gitTag}"
  def authHeader = "Basic " + "${jiraUser}:${jiraToken}".getBytes().encodeBase64().toString()
  def headers = [
      "Authorization": authHeader,
      "Content-Type": "application/json"
  ]
  def response = call(issuesUrl, headers)
  def responseJson = new JsonSlurperClassic().parseText(EntityUtils.toString(response.getEntity()))
  def issues = responseJson.issues.collect { issue -> issue.key }
  
  issues.each { issue ->
    def issueUrl = "${jiraUrl}/rest/api/2/issue/${issue}/transitions"
    def transitionData = [
      transition: [
        id: '2'
      ]
    ]
    def transitionJson = JsonOutput.toJson(transitionData)
    call(issueUrl, headers, 'POST', transitionJson)
  }
}
