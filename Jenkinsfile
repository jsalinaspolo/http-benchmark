library 'zopa-pipelines'

pipeline {
    agent {
        kubernetes {
            label 'pod-dind'
            defaultContainer 'dind'
        }
    }

    environment {
        VERSION = zersion("-l")
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
    }

    stages {
        stage('Compile') {
            steps {
                gradle {
                    sh 'apt-get update && apt-get -y install maven'
                    sh 'mvn package assembly:single'
                }
            }
        }

        stage('Publish') {
            when { branch 'master' }
            steps {
                publish("http-benchmark", "Dockerfile", ".")
                sh "git tag v${env.VERSION}"
                gitPushTags()
            }
        }

        stage('Deploy to UAT') {
            when { branch 'master' }
            steps {
                deploy(
                        manual: false,
                        environment: "staging",
                        namespace: "product",
                        applications: ["http-benchmark"]
                )
            }
        }
    }

    post {
        always { junitReports() }
        success { prSuccess() }
        unsuccessful { prFailure() }
        cleanup { cleanup() }
    }
}
