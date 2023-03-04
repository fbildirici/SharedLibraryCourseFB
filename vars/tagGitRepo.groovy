// vars/tagGitRepo.groovy

def call(String tagName) {
  sh "git tag -a ${tagName} -m 'Version ${tagName}'"
  sh "git push origin ${tagName}"
}
