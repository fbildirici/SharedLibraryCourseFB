// vars/checkoutGit.groovy

def call(String gitUrl, String gitBranch) {
  git branch: gitBranch, url: gitUrl
}
