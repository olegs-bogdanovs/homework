## Build and Deploy

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

# Dashboard

Grafana login/password: admin/admin

Dashboard name: Reader/Writer application dashboard

**0.25, 0.5, 0.75, 0.95 percentile values** indicates the percentage of scores that fall below a shown value

**Insert and query count request time graphs** show request time timeseries per selected period 
