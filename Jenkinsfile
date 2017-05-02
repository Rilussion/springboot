#!groovy

node {

    stage('Checkout') {
        deleteDir()
        checkout scm
    }

    stage('build') {
        sh './gradlew build -x test'
    }

    stage('Unit tests') {
        sh './gradlew test --tests *AppTest.1*'
    }

    stage('More Unit tests') {
        sh './gradlew test --tests *AppTest.2*'
    }

    /*parallel 'Unit tests': {
        sh './gradlew test --tests *AppTest.1*'
    },
            'More Unit tests': {
                sh './gradlew test --tests *AppTest.2*'
            }*/

    stage('Code analysis and coco') {
        def g = tool 'GRADL'
        env.PATH = "${g}/bin:${env.path}"
        sh 'gradle sonarqube -x test'
    }

    stage('dockerize-remove') {

        sh './gradlew composeDown'
    }

    stage('dockerize & compose app server and mock server ') {

        sh './gradlew composeUp'
    }

    stage('Integration Test') {

        sh './gradlew -Dtest.single=AppIntegrationTest test'
    }
}