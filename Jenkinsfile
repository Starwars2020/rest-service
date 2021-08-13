def label = "worker-${UUID.randomUUID().toString()}"

def console(msg) {
  echo msg
}

console("=========== START ==========")

podTemplate(
    label: label, 
    containers: [
    containerTemplate(name: "openjdk", image: "openjdk:8-jdk-alpine"),
    containerTemplate(name: "docker", image: "docker:20.10.5")
  ],
  volumes: [
    hostPathVolume(mountPath: "/usr/bin/docker", hostPath: "/usr/bin/docker"),
    hostPathVolume(mountPath: "/var/run/docker.sock", hostPath: "/var/run/docker.sock")
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
        container("openjdk") {
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