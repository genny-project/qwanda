pipeline {
	agent any
  tools {
      maven 'Maven 3.5.2'
  }
	stage('Build') {
		steps {
			mvn 'clean install -U'
		}
	}
}
