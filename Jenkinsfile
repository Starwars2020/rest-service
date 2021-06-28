podTemplate(label: 'jenkins-slave-pod', 
  containers: [
    //containerTemplate(name: 'git', image: 'ubuntu/20.10', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'git', image: 'alpine/git', command: 'cat', ttyEnabled: true),
    //containerTemplate(name: 'maven', image: 'maven:3.6.2-jdk-8', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'maven', image: 'ubuntu/20.10', command: 'cat', ttyEnabled: true),
	containerTemplate(name: 'gradle', image: 'gradle:latest', command: 'cat', ttyEnabled: true), 
    containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true)
  ],
  volumes: [ 
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock') 
  ]
)

{
    node('jenkins-slave-pod') { 
        def registry = "localhost:5000"
        def registryCredential = ""
        def githubCredential = "Github-Account"

        // https://jenkins.io/doc/pipeline/steps/git/
        stage('Clone repository') {
            container('git') {
                // https://gitlab.com/gitlab-org/gitlab-foss/issues/38910
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/*']],
                    userRemoteConfigs: [
                        [url: 'https://github.com/Starwars2020/rest-service.git', credentialsId: '$githubCredential']
                    ],
                ])
            }
        }
        
        stage('Build a Gradle project') {
            container('gradle') {
                sh './gradlew clean build'
            }
        }

        stage('Build docker image') {
            container('docker') {
                withDockerRegistry([ credentialsId: "$registryCredential", url: "http://$registry" ]) {
                    sh "docker build -t $registry/rest-sample-app:1.0 -f ./Dockerfile ."
                }
            }
        }

        stage('Push docker image') {
            container('docker') {
                withDockerRegistry([ credentialsId: "$registryCredential", url: "http://$registry" ]) {
                    docker.image("$registry/rest-sample-app:1.0").push()
                }
            }
        }
    }   
}
