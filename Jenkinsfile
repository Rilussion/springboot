#!groovy

node {

    stage('Checkout') {
        deleteDir()
        checkout scm
    }

    stage('build') {
        sh './gradlew build -x test'
    }

    stage('Static tests') {

            parallel(
                    stage('Unit tests'){
                        sh './gradlew -Dtest.single=AppTest test'
                    },
                    stage('Code analysis and coco'){
                        def g = tool 'GRADL'
                        env.PATH = "${g}/bin:${env.path}"
                        sh 'gradle sonarqube -x test'
                    }
            )
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