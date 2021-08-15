/* pipeline 변수 설정 */
def DOCKER_IMAGE_NAME = "localhost:5000/rest-sample-app"
def DOCKER_IMAGE_TAGS = "1.0"
def NAMESPACE = "jenkins"
def VERSION = "${env.BUILD_NUMBER}"
def dockerHubCred = ""
def appImage      
def DATE = new Date();

podTemplate(label: 'jenkins-slave', 
            containers: [
                containerTemplate(name: 'gradle', image: 'gradle:5.6-jdk8', command: 'cat', ttyEnabled: true),
                containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
				containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.15.3', command: 'cat', ttyEnabled: true)
            ],
            volumes: [ 
                hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'), 
            ]) {
    node('jenkins-slave') {
        stage('Checkout'){
            checkout scm
        }
        
        stage('Build'){
            container('gradle'){
                sh 'chmod 755 gradlew'
                sh './gradlew clean build'
            }
        }
        
        stage('Docker Build'){
            container('docker'){
                withRegistry('https://localhost:5000') {
                    build("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAGS}")
                    push("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAGS}")
                }
            }
        }
        
        //stage('Docker Push'){
        //    container('docker'){
        //        script {
        //            docker.withRegistry('https://localhost:5000', dockerHubCred){
        //                appImage.push("${env.BUILD_NUMBER}")
        //                appImage.push("latest")
        //            }
        //        }
        //    }
        //}
    }
    
}