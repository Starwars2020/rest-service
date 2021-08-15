def DOCKER_IMAGE_NAME = "localhost:5000/rest-sample-app"
def DOCKER_IMAGE_TAGS = "1.0"
def NAMESPACE = "jenkins"
def appImage      
def registry = "localhost:5000"
def registryCredential = ""
def DATE = new Date();

podTemplate(
    label: 'jenkins-slave', 
    containers: [
      containerTemplate(name: 'gradle', image: 'gradle:5.6-jdk8', command: 'cat', ttyEnabled: true),
      containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
      containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.15.3', command: 'cat', ttyEnabled: true)
    ],
    volumes: [ 
      hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'), 
    ]) {
  node('jenkins-slave') {
    stage('Checkout') {
      checkout scm
    }
	
    stage('Build'){
      container('gradle'){
        sh 'chmod 755 gradlew'
        sh './gradlew clean build'
      }
    }

    stage('Build docker image') {
      container('docker') {
        withDockerRegistry([ credentialsId: "$registryCredential", url: "http://$registry" ]) {
          sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAGS} -f ./Dockerfile ."
        }
      }
    }

    stage('Push docker image') {
      container('docker') {
        withDockerRegistry([ credentialsId: "$registryCredential", url: "http://$registry" ]) {
          docker.image("$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAGS").push()
        }
      }
    }

        //stage('Docker Build'){
        //    container('docker'){
        //        withRegistry('https://localhost:5000') {
        //            appImage = docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAGS}")
        //            appImage.push()
        //        }
        //    }
        //}
        
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