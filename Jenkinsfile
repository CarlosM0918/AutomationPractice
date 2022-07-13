pipeline {
    agent any
    tools{
        maven "mvn"
    }
    stages {
        stage('package'){
          steps{
              sh 'mvn clean install package'
          }
        }
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    post {
        always {
//             script{
//                 BUILD_USER = getBuildUser()
//             }
            slackSend channel: 'jenkins_notifications', message: 'Build Started - ${env.JOB_NAME} build ${env.BUILD_NUMBER} by ${BUILD_USER} \n More info at ${env.BUILD_URL}'
            slackUploadFile channel: 'jenkins_notifications', credentialId: '66cbcabe-28e9-446f-9964-5cb3efde7c9b', filePath: 'TIReport.html', initialComment: 'Report'

//             slackSend channel: 'jenkins_notifications',
//                       color: COLOR_MAP[currentBuild.currentResult],
//                       message: "*${currentBuild.currentResult}:* Job {env.JOB_NAME} build ${env.BUILD_NUMBER} by ${BUILD_USER} \n More info at ${env.BUILD_URL}"
        }
    }
}