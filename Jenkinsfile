podTemplate(label: 'docker-build', 
  containers: [
    containerTemplate(name: 'git', image: 'alpine/git', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'gradle', image: 'gradle:latest', command: 'cat', ttyEnabled: true),
    containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
  ],
  volumes: [ 
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'), 
  ]
) {
    node('docker-build') {
        def dockerHubCred = ""
        def appImage
        
        stage('Checkout'){
            container('git'){
                checkout scm
            }
        }
        
        stage('Gradle Build') {
            withGradle {
                sh './gradlew clean build'
            }
        }

        stage('Docker Build'){
            container('docker'){
                script {
                    appImage = docker.build("localhost:5000/rest-sample-app:1.0")
                }
            }
        }
        
        stage('Test'){
            container('docker'){
                script {
                    appImage.inside {
                        sh 'echo "hello"'
                    }
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