pipeline {
    agent { node { label 'GLOBAL_POD_TEMPLATE' } }
    stages {
        stage('Build'){
		    parallel {
                stage('Build jenkins-slave-1 container') {
				    steps {
				        container('jenkins-slave-1') {
				            sh "echo hello from $POD_CONTAINER"
						}
					}
                }
                stage('Build jenkins-slave-2 container') {
				    steps {
				        container('jenkins-slave-2') {
                            sh "echo hello from $POD_CONTAINER"
						}
					}
                }
			}
        }
    }    
}