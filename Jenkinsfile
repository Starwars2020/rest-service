#!/usr/bin/groovy

podTemplate(label: 'jenkins-slave-pod', 
  containers: [
    containerTemplate(name: 'git', image: 'alpine/git', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'maven', image: 'maven:3.8.1-jdk-8', command: 'cat', ttyEnabled: true),
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
    def githubCredential = "Starwars2020"
    
    // https://jenkins.io/doc/pipeline/steps/git/
    stage('Clone repository') {
      container('git') {
        // https://gitlab.com/gitlab-org/gitlab-foss/issues/38910
        echo 'Stage#1: Clone repository ..Start'
        checkout([$class: 'GitSCM',
          branches: [[name: '*/*']],
          userRemoteConfigs: [
            [url: 'https://github.com/Starwars2020/rest-service.git', credentialsId: '$githubCredential']
          ],
        ])
        echo 'Stage#1: Clone repository ..End'
      }
    }
    
    //stage('Build a Gradle project') {
    //  container('gradle') {
    //    sh './gradlew clean build'
    //    echo 'Stage#2: Build a Gradle project ..End'
    //  }
    //}
    
    //stage('Build docker image') {
    //  container('docker') {
    //    withDockerRegistry([ credentialsId: "$registryCredential", url: "http://$registry" ]) {
    //      sh "docker build -t $registry/rest-sample-app:1.0 -f ./Dockerfile ."
    //    }
    //    echo 'Stage#3: Build docker image ..End'
    //  }
    //}
    
    //stage('Push docker image') {
    //  container('docker') {
    //    withDockerRegistry([ credentialsId: "$registryCredential", url: "http://$registry" ]) {
    //      docker.image("$registry/rest-sample-app:1.0").push()
    //    }
    //    echo 'Stage#4: Push docker image ..End'
    //  }
    //}
  }
}
