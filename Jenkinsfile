#!groovy

node {

    stage('Checkout') {
        deleteDir()
        checkout scm
    }

    stage('build') {
        sh './gradlew build -x test'
    }

    parallel x: {
        stage('Unit tests') {
            sh './gradlew -Dtest.single=AppTest test'
        }
    },
   y: {
        stage('code analysis and coco') {
            def g = tool 'GRADL'
            env.PATH = "${g}/bin:${env.path}"
            sh 'gradle sonarqube -x test'
        }
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