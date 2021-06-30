def label = "jenkins-slave-pod"

def console(msg) {
  echo msg
}

console("=========== START ==========")

podTemplate(
	label: label, 
	containers: [
		containerTemplate(name: "docker", image: "docker:stable", ttyEnabled: true, command: "cat")
	],
	volumes: [
		hostPathVolume(hostPath: "/var/run/docker.sock", mountPath: "/var/run/docker.sock"),
		hostPathVolume(hostPath: "/etc/docker/certs.d", mountPath: "/etc/docker/certs.d")
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