pipeline {
	agent any
  tools {
      maven 'mvn 3.5'
  }
	stages {
		stage('Build') {
			steps {
				sh 'mvn clean install -U'
			}
		}
	}
}
