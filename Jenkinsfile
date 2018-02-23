pipeline {
	agent any
	environment {
		releaseVersion = sh(returnStdout: true, script: 'git describe --abbrev=0 --tags')
	}
  tools {
      maven 'mvn 3.5'
  }
	stages {
		stage('Build') {
			steps {
				sh "echo ${releaseVersion}"
				sh "echo ${env.releaseVersion}"
				sh 'mvn clean install -U'
			}
		}
	}
	post {
		success {
			withCredentials([string(credentialsId: 'e78cedc6-d1c0-4ff4-9fbb-fb65f7190c5d', variable: 'SLACK_WEBHOOK')]) {
				sh "curl -X POST -H 'Content-type: application/json' --data '{\"text\":\"gennyproject/qwanda:${env.BRANCH_NAME}-${env.BUILD_NUMBER} successfully built! :goodstuff:\"}' ${SLACK_WEBHOOK}"
			}
		}
		failure {
			withCredentials([string(credentialsId: 'e78cedc6-d1c0-4ff4-9fbb-fb65f7190c5d', variable: 'SLACK_WEBHOOK')]) {
				sh "curl -X POST -H 'Content-type: application/json' --data '{\"text\":\"🚨🚨 gennyproject/qwanda:${env.BRANCH_NAME}-${env.BUILD_NUMBER} failed to build/push! 🚨🚨\"}' ${SLACK_WEBHOOK}"
			}
		}
	}
}
