start-minikube: delete-minikube
	minikube start --driver=virtualbox --memory=4096 --cpus=2 --disk-size=20g

delete-minikube:
	minikube delete

build:
	@eval $$(minikube docker-env) ;\
	docker build -t apps/reader:0.0.1 reader/ ;\
	docker build -t apps/writer:0.0.1 writer/ ;\
	docker build -t mysql/master:0.1 mysql/mysql-master/ ;\
	docker build -t mysql/slave:0.1 mysql/mysql-slave/ ;\

create:
	kubectl create secret generic db-password --from-literal PASS=$$(tr -dc A-Za-z0-9 </dev/urandom | head -c 13 ; echo '')
	kubectl create secret generic db-replication-password --from-literal PASS=$$(tr -dc A-Za-z0-9 </dev/urandom | head -c 13 ; echo '')
	kubectl create -f k8s/

delete:
	kubectl delete -f k8s/
	kubectl delete secret db-password
	kubectl delete secret db-replication-password

link:
	minikube service grafana-lb

all: start-minikube build create

clean: delete-minikube