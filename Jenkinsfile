#!groovy

node {
   stage('Checkout') {
       deleteDir()
     checkout scm
   }
   stage ('build') {
       sh './gradlew build -x test'
   }
    stage ('code analysis incl test, coco') {
        def g = tool 'GRADL'
        env.PATH = "${g}/bin:${env.path}"
        sh 'gradle sonarqube'
    }
    stage ('dockerize') {
        steps {
            sh './gradlew composeDown'
        }
        steps {
            sh './gradlew composeUp'
        }
    }
}