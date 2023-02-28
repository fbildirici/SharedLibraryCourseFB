# Shared Library Course

Welcome to the Jenkins Shared Library Course! In this course, we will dive deep into the world of Jenkins Shared Libraries and explore how they can help you automate your build and deployment processes. Jenkins Shared Libraries allow you to define reusable code that can be shared across multiple pipelines, providing a simple and efficient way to manage your Jenkins pipelines.


## Corporate Examples for FB

This project is a starting point for developers who want to create a process flow using Jenkins. The Jenkins shared library is a library that brings together common functionality that can then be used in Jenkins projects. It also contains the developer's own exercises for an enterprise project.


## Usage

To use this library, add the following lines to the Jenkinsfile in your Jenkins project:

```groovy
@Library('sharedlibexample') _

pipeline {
    agent any
    stages {
        stage('ex:printresult') {
            steps {
                echo 'Hello to Shared Library Repo'
                mySharedLibraryFunction()
            }
        }
    }
}

```

## Contributing

To contribute to this project, please create a branch on GitHub and then submit a pull request. If you have any feedback or suggestions, please open a topic in the "Issues" tab.


## License

[MIT](https://choosealicense.com/licenses/mit/)
