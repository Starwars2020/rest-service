def label = "jenkins-slave-pod"

def console(msg) {
  echo msg
}

console("=========== START ==========")

podTemplate(
	label: label, 
	containers: [
		containerTemplate(name: "git", image: "openjdk:8-jdk-alpine", ttyEnabled: true, command: "cat")
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
				container("git") {
					checkout scm
				}
			}
		} catch(e) {
			currentBuild.result = "FAILED"
		}
	}
}
console("=========== END ==========")