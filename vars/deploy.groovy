// vars/deploy.groovy

def call() {
  sh 'mvn deploy'
}
