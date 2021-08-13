GLOBAL_POD_TEMPLATE_TEST='''
apiVersion: v1
kind: Pod
spec:
  tolerations:
  - key: "builder"
    operator: "Equal"
	value: "true"
	effect: "NoSchedule"
  containers:
  - name: test
    image: ubuntu:latest
	command:
	- sleep
	args:
	- infinity
  - name: test2
    image: ubuntu:latest
	command:
	- sleep
	args:
	- infinity
'''
def steps =[:]

podTemplate(
    yaml:GLOBAL_POD_TEMPLATE_TEST,
    nodeSelector:'minikube',
	workspaceVolume:hostPathWorkspaceVolume("/data/workspace")
) 
{
    node(POD_LABEL) {
        stage('Build') {
            stages["Build test container"] = {
                container("test") {
                    sh "echo hello from $POD_CONTAINER"
                }
            }
            stages["Build test2 container"] = {
                container("test2") {
                    sh "echo hello from $POD_CONTAINER"
                }
            }
			parallel steps
        }
    }
}
