// vars/sonarqubeAnalysis.groovy

def call(String sonarqubeServerName) {
  withSonarQubeEnv(sonarqubeServerName) {
    sh 'mvn sonar:sonar'
  }
}
