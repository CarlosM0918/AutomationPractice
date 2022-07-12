def COLOR_MAP = [
    'SUCCESS': 'good',
    'FAILURE': 'danger'
]

def getBuildUser(){
    return currentBuild.rawBuild.getCause(Cause.UserIdCause).getUserId()
}

pipeline {
    agent any
    enviroment {
        BUILD_USER = ''
    }
    tools{
        maven "mvn"
    }
    stages {
//         stage('Compile and validate'){
//             steps{
//                 sh 'mvn clean compile validate'
//             }
//         }
        stage('package'){
          steps{
            sh 'cat .gitignore'
//               sh 'mvn clean install package'
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
            script{
                BUILD_USER = getBuildUser()
            }

            slackSend channel: 'jenkins_notifications',
                      color: COLOR_MAP[currentBuild.currentResult],
                      message: "*${currentBuild.currentResult}:* Job {env.JOB_NAME} buld ${env.BUILD_NUMBER} by ${BUILD_USER} \n More info at ${env.BUILD_URL}"
        }
    }
}