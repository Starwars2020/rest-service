podTemplate(label: 'jenkins-slave-pod',
  containers: [
    containerTemplate(name: 'git', image: 'alpine/git', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'gradle', image: 'gradle:latest', command: 'cat', ttyEnabled: true), 
  ],
  volumes: [ 
    hostPathVolume(mountPath: '/var/jenkins_home/workspace', hostPath: '/home/osboxes/lab/jenkins/workspace')
  ]
) 

{
    node('jenkins-slave-pod') { 
        def registry = "localhost:5000"
        def registryCredential = ""
        def githubCredential = "Starwars2020"

        stage('Clone repository') {
            container('git') {
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

        //stage('Build docker image') {
        //    container('docker') {
        //        withDockerRegistry([ credentialsId: "$registryCredential", url: "http://$registry" ]) {
        //            sh "docker build -t $registry/rest-sample-app:1.0 -f ./Dockerfile ."
        //        }
        //    }
        //}

        //stage('Push docker image') {
        //    container('docker') {
        //        withDockerRegistry([ credentialsId: "$registryCredential", url: "http://$registry" ]) {
        //           docker.image("$registry/rest-sample-app:1.0").push()
        //        }
        //    }
        //}
    }   
}
