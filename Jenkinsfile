def NAMESPACE = "jenkins"
def dockerRegistry = "localhost:5000"
def dockerRegistryCredential = ""
def githubRepository = "https://github.com/Starwars2020/rest-service.git"
def githubCredential = "github-api-token"
def dockerImageName = "localhost:5000/rest-sample-app"
def dockerImageTags = "1.0"
def DATE = new Date();

podTemplate(
        label: 'jenkins-slave', 
        containers: [
            containerTemplate(name: 'git', image: 'alpine/git', command: 'cat', ttyEnabled: true),
            containerTemplate(name: 'gradle', image: 'gradle:5.6-jdk8', command: 'cat', ttyEnabled: true),
            containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
            containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.15.3', command: 'cat', ttyEnabled: true)
        ],
        volumes: [ 
            hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'), 
        ]) {
    node('jenkins-slave') {
        stage('Clone repository') {
            container('git') {
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/*']],
                    userRemoteConfigs: [
                        [url: '$githubRepository', credentialsId: '$githubCredential']
                    ],
                ])
            }
        }
	
        stage('Build a Gradle project'){
            container('gradle'){
                sh 'chmod 755 gradlew'
                sh './gradlew clean build'
            }
        }

        stage('Build docker image') {
            container('docker') {
                withDockerRegistry([ credentialsId: "$dockerRegistryCredential", url: "http://$dockerRegistry" ]) {
                    sh "docker build -t ${dockerImageName}:${dockerImageTags} -f ./Dockerfile ."
                }
            }
        }

        stage('Push docker image') {
            container('docker') {
                withDockerRegistry([ credentialsId: "$dockerRegistryCredential", url: "http://$dockerRegistry" ]) {
                    docker.image("$dockerImageName:$dockerImageTags").push()
                }
            }
        }
    }
}