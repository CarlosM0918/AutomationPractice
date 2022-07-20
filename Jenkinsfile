pipeline {
    agent any
    triggers {
        cron 'H 9 * * 0-6'
        pollSCM 'H * * * *'
    }
    tools {
      maven 'mvn'
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvnw.cmd clean test -P SmokeSuite'
            }
        }
    }
    post {
        always {
            slackSend channel: 'jenkins_notifications',
                        message: 'Build Started - ' + env.JOB_NAME + ' build ' + env.BUILD_NUMBER + '\n Report Link: '+ env.BUILD_URL + 'execution/node/3/ws/TIReport.html'
            slackUploadFile channel: 'jenkins_notifications', filePath: 'TIReport.html', initialComment: 'Report'
        }
    }
}