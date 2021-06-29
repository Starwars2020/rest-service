#!/usr/bin/groovy

podTemplate(label: 'jenkins-slave-pod', 
  containers: [
    containerTemplate(name: 'git', image: 'alpine/git', command: 'cat', ttyEnabled: true),
    //containerTemplate(name: 'maven', image: 'maven:3.8.1-jdk-8-alpine', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
  ],
  volumes: [ 
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'), 
  ]
) {
    node('jenkins-slave-pod') {
        //def dockerHubCred = <your_dockerhub_cred>
        //def appImage
        
        stage('Checkout'){
            container('git'){
                checkout scm
            }
        }
    }    
}