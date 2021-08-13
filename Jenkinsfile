pipeline {
    agent { node { label 'GLOBAL_POD_TEMPLATE_TEST' } }
    stages {
        stage('Build') {
            paralle {
                stage('Build test container') {
                    steps {
                        container('test') {
                            sh "echo hello from $POD_CONTAINER"
                        }
                    }
                }
                stage('Build test2 container') {
                    steps {
                        container('test2') {
                            sh "echo hello from $POD_CONTAINER"
                        }
                    }
                }
            }
        }
    }
}
