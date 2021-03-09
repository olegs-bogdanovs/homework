Current setup uses minikube with Virtual Box driver to run K8S locally. 

To run the whole deployment pipeline (minikube start, image build, apply k8s configuration) execute:
```
make all
``` 

To clean environment execute:

```
make clean
```

To open Grafana dashboard execute (dashboard will be opened in a default browser): 

```
make link
```

Grafana login/password: admin/admin
Dashboard name: 