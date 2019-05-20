본 프로젝트는 외부 프로젝트 시 제공할 MasterAPI 기반 템플릿입니다.

### [개발환경]
JDK 1.8<br>
Spring 4.1.4, Spring Boot 2.1.4 <br>
ORM : Mybatis 3.2.8<br>

### [프로젝트 구조]
- api : 제공하는 api는 버전별 별도의 디렉토리를 가지며 controller, service, mapper interface, vo 등이 포함됩니다. api가 다른 모듈에 http call을 하는 경우 해당 디렉토리에 위치합니다. (core.service)
- base : api에서 사용되는 exception, filter, interceptor, exceptionHandler 등이 위치합니다.
- config : api에서 필요한 설정이 위치합니다. 

### [기동 시 프로퍼티 설정]
프로젝트의 properties 디렉토리 아래에 사이트별 프로터피 파일을 관리합니다.<br>
로컬에서 war 기동 시 VM argument로 -Dproperties.path={프로퍼티 파일의 경로} 추가 후 기동합니다.
![properties.jpg](/yobi/files/706)

### [swagger 사용]
API Docs로 swagger를 사용합니다.
Swagger 주소 : IP주소:포트번호/context-root/swagger
- 포트번호는 프로퍼티의 server.port를 통해 설정가능합니다.
- context-root 는 프로퍼티의 server.context-path를 통해 설정 가능합니다.(Jboss에 올릴 경우, jboss-web.xml의 context-root를 변경합니다.)
- context-root 변경 시 WEB_INF/views/swagger-ui/index.jsp 파일의 swagger ui 리소스 주소를 변경해야 합니다.
![swagger 수정.jpg](/yobi/files/705)
### [DDL 관리]
API 관련 DDL은 프로젝트의 DDL 폴더의 XX.sql 파일에 관리합니다.
