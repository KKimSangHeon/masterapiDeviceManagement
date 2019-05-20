while : ; do curl -X GET   'http://169.56.70.76:31380/iot/accessControl/v1/target/1005?operationType=create&extrMemberSequence=10010'   -H 'Authorization: myAdmin'   -H 'Postman-Token: 0ccdf9dd-8505-4461-83e7-22159696b50c'   -H 'cache-control: no-cache'   -H 'tokenType: admin'   -d '{
 "id": "iotUserA"
}';  sleep 1; done
