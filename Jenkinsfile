#!groovy

node {
   stage('Preparation') { 
     checkout scm
   }
   stage('build'){
       def g = tool 'GRADL'
       env.PATH = "${g}/bin:${env.path}"
       sh 'gradle build -x test'
   }
   stage('unit test') {
       def g = tool 'GRADL'
       env.PATH = "${g}/bin:${env.path}"
       sh 'gradle test'
   }
   stage ('code analysis') {
       def g = tool 'GRADL'
       env.PATH = "${g}/bin:${env.path}"
       sh 'gradle sonarqube'
   }
}
