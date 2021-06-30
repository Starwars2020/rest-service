def label = "jenkins-slave-pod"

def console(msg) {
  echo msg
}

console("=========== START ==========")

podTemplate(
  label: label, 
  containers: [
    containerTemplate(name: 'gradle', image: 'gradle:latest', command: 'cat', ttyEnabled: true)
	],
  volumes: [
    hostPathVolume(hostPath: "/var/run/docker.sock", mountPath: "/var/run/docker.sock")
	]
) 
{
  node(label) {
    try {
      stage("Checkout") {
        console("== START: checkout==")
        container("gradle") {
          sh './gradlew clean build'
        }
      }
    } catch(e) {
      currentBuild.result = "FAILED"
    }
  }
}
console("=========== END ==========")