podTemplate(label: 'jenkins-slave-pod', 
  containers: [
    containerTemplate(name: 'git', image: 'alpine/git', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'gradle', image: 'gradle:7.1-jdk8', command: 'cat', ttyEnabled: true),
    //containerTemplate(name: 'maven', image: 'maven:3.8.1-jdk-8-alpine', command: 'cat', ttyEnabled: true),
    //containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
  ],
  volumes: [ 
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'), 
  ]
) {
    node('jenkins-slave-pod') {
        stage('Checkout'){
            container('git'){
                checkout scm
				echo "Checkout ...End"
            }
        }
        stage('gradle build'){
            container('gradle'){
                sh "./gradlew clean build"
				echo "gradle build ...End"
            }
        }
    }    
}