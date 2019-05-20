### 처음 빌드하기 전 참고

빌드하기전 resource/yamfilelist에서 아래 명령어 순서대로 실행

1. Configure Map 생성

```
kubectl create -f postgres-configmap.yaml 
```

2. DB 생성


```
# create process

$ kubectl apply -n kkim -f postgres-configmap.yaml
$ kubectl apply -n kkim -f postgres-storage.yaml 
$ kubectl apply -n kkim -f postgres-deployment.yaml 
$ kubectl apply -n kkim -f postgres-service.yaml 
   


# delete process
$ kubectl delete -n kkim -f postgres-configmap.yaml
$ kubectl delete -n kkim -f postgres-storage.yaml 
$ kubectl delete -n kkim -f postgres-deployment.yaml 
$ kubectl delete -n kkim -f postgres-service.yaml 
```


3. DB 확인

```
$ kubectl get pod -n kkim
을 입력하여 잘 생성되었나 확인

$ kubectl exec -ti $POD_NAME -n kkim bash
을 입력하여 접속해보자.

$ ps -ef 

$ useradd admin123

$ su admin123

$ psql testdb
를 통해 프로세스 확인


create process
$ kubectl -n kkim create -f zookeeper-storage.yaml
$ kubectl -n kkim create -f zookeeper-service-hs.yaml
$ kubectl -n kkim create -f zookeeper-service-cs.yaml
$ kubectl -n kkim create -f zookeeper-podDisruptionBudget.yaml
$ kubectl -n kkim create -f zookeeper-statefulset.yaml

delete process
$ kubectl -n kkim delete service zookeeper-service-hs.yaml
$ kubectl -n kkim delete service zookeeper-service-cs.yaml
$ kubectl -n kkim delete configmap postgres-config
$ kubectl -n kkim delete persistentvolumeclaim postgres-pv-claim
$ kubectl -n kkim delete persistentvolume postgres-pv-volume

```

4. Configmap 실행

```
$ kubectl apply -n kkim -f configmap.yaml

```








