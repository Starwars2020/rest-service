def label = "jenkins-slave-pod"

def console(msg) {
  echo msg
}

console("=========== START ==========")

podTemplate(
	label: label, 
	containers: [
		containerTemplate(name: "git", image: "alpine/git", ttyEnabled: true, command: "cat")
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
				container("docker") {
					checkout([$class: 'GitSCM',
            branches: [[name: '*']],
            extensions: [],
            userRemoteConfigs: [[url: 'https://github.com/jenkinsci/git-plugin']]])
					}
				}
			}
		} catch(e) {
			currentBuild.result = "FAILED"
		}
	}
}
console("=========== END ==========")