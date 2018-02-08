pipeline {
	agent any
  tools {
      maven 'mvn 3.5'
  }
	stages {
		stage('Build') {
			steps {
				maven {
					goals('clean install -U')
				}
			}
		}
	}
}
