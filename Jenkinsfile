pipeline {
    agent any
    triggers {
        cron 'H 9 * * 0-6'
        pollSCM 'H * * * *'
    }
    tools{
        maven "mvn"
    }
    stages {
        stage('package'){
          steps{
              bat 'mvn clean compile install'
          }
        }
        stage('Build') {
            steps {
                bat 'mvnw.cmd clean test'
            }
        }
    }
    post {
        always {
            slackSend channel: 'jenkins_notifications',
                        message: 'Build Started - ' + env.JOB_NAME + ' build ' + env.BUILD_NUMBER + '\n Report Link: '+ env.BUILD_URL + 'execution/node/3/ws/TIReport.html'
                    //   message: 'Build Started - ${env.JOB_NAME} build ${env.BUILD_NUMBER} by ${BUILD_USER} \n More info at ' + env.BUILD_URL + 'execution/node/3/ws/TIReport.html'
            slackUploadFile channel: 'jenkins_notifications', credentialId: '66cbcabe-28e9-446f-9964-5cb3efde7c9b', filePath: 'TIReport.html', initialComment: 'Report'
        }
    }
}