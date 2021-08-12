def label = "jenkins-slave"

def console(msg) {
  echo msg
}

console("=========== START ==========")

podTemplate(
  label: label, 
  containers: [
    containerTemplate(name: 'gradle', image: 'gradle:6.5.1', command: 'cat', ttyEnabled: true),
	containerTemplate(name: "docker", image: "docker:stable", ttyEnabled: true, command: "cat")
  ],
  volumes: [
    hostPathVolume(mountPath: "/usr/bin/docker", hostPath: "/usr/bin/docker"),
    hostPathVolume(hostPath: "/var/run/docker.sock", mountPath: "/var/run/docker.sock")
  ]
) 
{
  node(label) {
    def dockerRegistry = "https://localhost:5000"
    def org = "localhost:5000"
    def credential = ""
    def image = "rest-sample-app"
    def tag = "1.0"
	
    try {
      stage("Checkout") {
        console("== START: checkout==")
        container("gradle") {
          sh './gradlew clean build'
        }
      }
      stage("Build & Push image") {
        console("== START: build/push image==")
        container("docker") {
          docker.withRegistry("${dockerRegistry}", "${credential}") {
            sh "docker build -t ${image}:${tag} ."
            sh "docker push ${image}:${tag}"
          }
        }
      }
    } catch(e) {
      currentBuild.result = "FAILED"
    }
  }
}
console("=========== END ==========")