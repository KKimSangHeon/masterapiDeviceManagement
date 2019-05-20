create process
kubectl -n kkim create -f zookeeper-storage.yaml
kubectl -n kkim create -f zookeeper-service-hs.yaml
kubectl -n kkim create -f zookeeper-service-cs.yaml
kubectl -n kkim create -f zookeeper-podDisruptionBudget.yaml
kubectl -n kkim create -f zookeeper-statefulset.yaml

delete process
kubectl -n kkim delete service zookeeper-service-hs.yaml
kubectl -n kkim delete service zookeeper-service-cs.yaml
kubectl -n kkim delete configmap postgres-config
kubectl -n kkim delete persistentvolumeclaim postgres-pv-claim
kubectl -n kkim delete persistentvolume postgres-pv-volume


service "zk-hs" created
service "zk-cs" created
poddisruptionbudget "zk-pdb" created
statefulset "zk" created
