#!groovy

node('master') {

    stage('Checkout') {
        deleteDir()
        checkout scm
    }

    stage('build') {
        sh './gradlew clean build -x test'
    }

    stage('Unit tests') {
        sh './gradlew test --tests *AppTest.1* --debug'
    }

    stage('More Unit tests') {
        sh './gradlew test --tests *AppTest.2* --debug'
    }

    /*stage('Tests') {
        parallel 'Unit tests': {
            node('master') {

                sh './gradlew test --tests *AppTest.1*'
            }
        },
                'More Unit tests': {
                    node('remote') {
                        sh './gradlew test --tests *AppTest.2*'
                    }
                }
    }*/

    stage('Code analysis and coco') {
        def g = tool 'GRADL'
        env.PATH = "${g}/bin:${env.path}"
        sh './gradlew sonarqube -x test'
    }

    stage('dockerize-remove') {

        sh './gradlew composeDown -S'
    }

    stage('dockerize & compose app server and mock server ') {

        sh './gradlew composeUp -S'
    }

    stage('Integration Test') {

        sh './gradlew -Dtest.single=AppIntegrationTest test --debug'
    }
}