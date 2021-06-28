docker-env:
	eval $$(minikube docker-env)
	kubectl port-forward --namespace kube-system $$(kubectl get po -n kube-system | grep kube-system-registry-v0 | awk '{print $$1;}') 5000:5000 &
docker-build:
	docker build -t localhost:5000/rest-sample-app:1.0 .
	docker images localhost:5000/rest-sample-app:1.0
	docker push localhost:5000/rest-sample-app:1.0
	@echo "\n"
create:
	@kubectl create -f rest-sample-app-deployment.yaml
	@kubectl create -f rest-sample-app-service.yaml
	@kubectl create -f rest-sample-app-ingress.yaml
	@echo "\n"
delete:
	@kubectl delete -f rest-sample-app-deployment.yaml
	@kubectl delete -f rest-sample-app-service.yaml
	@kubectl delete -f rest-sample-app-ingress.yaml
	@echo "\n"
admission:
	kubectl delete validatingwebhookconfiguration ingress-nginx-admission
kong-test:
	kubectl port-forward -n kong svc/kong-control-plane 8001:8001 &
	kubectl port-forward -n kong svc/kong-ingress-data-plane 8000:8000 &
	@echo "\n"
test:
	@minikube service list -n default