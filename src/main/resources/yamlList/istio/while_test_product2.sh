while : ; do curl -X POST \
 http://169.56.70.76:31380/demo/template/v2/members/123/targets/1005/devices \
 -H 'Authorization: Bearer eyJhbGciOiJub25lIn0.eyJtYnJfc2VxIjoiMTAwMTAifQ.' \
 -H 'Content-Type: application/json' \
 -H 'Postman-Token: 584171e2-2301-4290-a830-c55328f90e73' \
 -H 'cache-control: no-cache' \
 -d '{
  "name":"Room1 Light",
  "id":"SHRoomLight",
  "uuid":"d770a77e-fc9c-48a7-af16-a4fa7e656166",
  "connectionId":"OPEN_HTTP_001PTL001_1000000346",
  "creator":"10150553",
  "target":{
     "sequence":1005,
     "id": "homeSvc04"
  },
  "model":{
     "sequence":1000000622,
      "name": "삼성 홈 IoT 스피커"
  }
}'; sleep 1; done
