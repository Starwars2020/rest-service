podTemplate(label: 'docker-build', 
  containers: [
    containerTemplate(name: 'git', image: 'alpine/git', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'maven', image: 'maven:3.8.1-jdk-8-alpine', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
  ],
  volumes: [ 
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'), 
  ]
) {
    node('docker-build') {
        //def dockerHubCred = <your_dockerhub_cred>
        //def appImage
        
        stage('Checkout'){
            container('git'){
                echo 'Step#1'
				checkout scm
            }
        }
        
        //stage('Build'){
        //    container('docker'){
        //        script {
        //            appImage = docker.build("<your-dockerhub-id>/node-hello-world")
        //        }
        //    }
        //}
        
        //stage('Push'){
        //    container('docker'){
        //        script {
        //            docker.withRegistry('https://registry.hub.docker.com', dockerHubCred){
        //                appImage.push("${env.BUILD_NUMBER}")
        //                appImage.push("latest")
        //            }
        //        }
        //    }
        //}
    }    
}