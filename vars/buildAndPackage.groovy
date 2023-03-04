// vars/buildAndPackage.groovy

def call() {
  sh 'mvn clean package'
}
