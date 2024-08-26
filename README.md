# What I didn't know 
## 1. 스프링 웹 개발
- 정적 컨텐츠
    - 서버가 파일을 그대로 내려주는 것
    - resource > static 폴더에 html 파일을 만들어 html을 구성하면 http://localhost:8080/<html파일명>.html으로 접근 가능
        - 해당 url이 들어오면 내장 톰캣 서버가 받아서 스프링에게 전달함 -> 컨트롤러에서 찾음 -> 맵핑 메소드가 없으면 html파일을 찾는
- MVC와 템플릿 엔진
    - 템플릿 엔진 : html을 서버에서 프로그래밍 해서 동적으로 html을 바꿔서 내려주는 것
    - Model(화면에 관련된 것들을 담아서 뷰에 전), View(화면에 관련된 일), Controller(비즈니스 로직) 로 분리
    - http://localhost:8080/<html파일 이름>으로 넘기면 -> 톰캣이 스프링에게 전달 -> 스프링이 컨트롤러에게 전달 -> 컨트롤러 안의 메소드 호출 -> viewResolver(템플릿 엔진을 연결해줌)가 <html 파일 이름>의 파일을 찾아서 처리함 -> 받은 데이터를 변환해서 클라이언트에게 전달
    - 메소드의 return 값이 스프링이 찾을 html 파일 이름 
- API
    - JSON 타입의 데이터 구조 포맷으로 클라이언트에 데이터를 전달
    - React, vue등 데이터만 전달하면 화면은 클라이언트가 알아서 정리

## 2. Test
- junit을 이용
    - main메소드나 컨트롤러를 통한 테스트는 오래 걸리고 반복 실행하기 어려움
- 테스트코드에서 메소드 실행 순서는 보장할 수 없음
    - 따라서 순서와 상관없이 메소드 별로 따로 테스트 코드를 만들어야 함 → 테스트 별로 의존관계가 없어야 함
    - 테스트 하나 끝나면 공용 데이터나 저장소를 비워줘야 함
- 테스트 코드는 과감하게 한국어로 적어도 된다 → 빌드될 때 코드에 포함되지 않으니까! 
- given - when - then
    - given :  무엇인가가 주어짐(ex : 객체 만들기)
    - when : 테스트할 메소드를 실행시켰을 때
    - then : 결과가 이게 나와야 한다.

## 3. Spring Bean and Spring Container? 
### 사전지식 
- 어노테이션이 있어야지 스프링이 관리할 수 있다.
- @Autowired : 스프링 빈에 등록된 객체를 생성자를 통해 해당 객체에 넣어줌(의존관계 주입) 


### 스프링 빈 (Spring Bean)
- **정의**: 스프링 컨테이너에 의해 관리되는 객체. 스프링 애플리케이션 컨텍스트에 의해 생성되고, 초기화되며, 관리되는 자바 객체입니다.
- **특징**:
  - **생성**: 스프링 애플리케이션 컨텍스트가 시작될 때, 초기화되고 생성.
  - **관리**: 스프링 컨테이너가 관리.
  - **주입**: 의존성 주입(Dependency Injection)을 통해 스프링 빈은 다른 빈에 주입. -> @Autowired로 할 수 있음. autowired를 쓰려면 스프링 빈에 등록이 되어 있어야 함  
  - **설정**:
    - **자바 설정 클래스(@Configuration)** -> 클래스에 @Configuration으로 등록하고 거기에다가 @Bean 어노테이션을 통해 Service나 repository 클래스를 빈에 등록함 
    - **컴포넌트 스캔(@Component, @Service, @Repository, @Controller 등)** -> 해당 어노테이션이 있으면 자동으로 스프링 빈을 등록
      - @Controller, @Service, @Repository는 모두 @Component 어노테이션을 가지고 있기 때문에, 스프링이 딱 올라올 때 컴포넌트 어노테이션을 스캔하고 스프링 빈에 자동 등록. 컨테이너에 등록한다. 
      - **@Autowired**는 DI 즉, 연관관계를 만들어줌(컨트롤러가 서비스를, 서비스가 리포지토리를 사용할 수 있게 되는 것) 

### 스프링 컨테이너 (Spring Container)
- **정의**: 스프링 빈을 생성, 관리, 그리고 의존성을 주입하는 역할.
- **특징**:
  - **역할**: 애플리케이션의 시작 시 빈을 생성하고, 빈 간의 의존성을 관리하며, 빈의 생명주기를 관리
  - **타입**:
    - **ApplicationContext**: 스프링의 주요 컨테이너로, 빈을 생성하고 관리하며, 애플리케이션 전반에 걸쳐 공유됩니다.
    - **BeanFactory**: 더 가벼운 컨테이너로, 기본적인 빈 생성 및 관리 기능을 제공합니다. `ApplicationContext`의 상위 인터페이스입니다.

### 관계
- **빈과 컨테이너의 관계**: 스프링 빈은 스프링 컨테이너에 의해 관리됩니다. 즉, 스프링 컨테이너가 빈을 생성하고, 초기화하며, 필요한 의존성을 주입합니다. 빈은 스프링 컨테이너의 관리 대상입니다.

정리하자면, 스프링 빈은 스프링 컨테이너에 의해 관리되는 객체이며, 스프링 컨테이너는 이러한 빈을 생성하고 관리하는 역할을 수행합니다.(= 스프링 빈이 스프링 컨테이너에 등록된다) 


- 우리가 하는 것은 **컴포넌트 스캔과 의존관계 설정**을 사용!

## 4. get과 post는 같은 url이여도 상관없나?
- get : 그냥 url을 통해 직접 치는 것 (주로 조회)
- post : 데이터를 form에 넣어서 전달하는 것 

## 5. h2 다운로드
https://herojoon-dev.tistory.com/117 -> 설치방법
https://bestinu.tistory.com/41 -> 확인해야 하는 것


## 6. @Transactional
- 테스트 클래스에 @Transactional annotation이 붙는다면, 트랙젝션을 시작하고 테스트가 끝나면 롤백해줌 -> 즉, 반복적으로 테스트 실행이 가능하다(인위적으로 db 내용을 지울 필요가 없다)
- Service 클래스 등에서 사용한다면 데이터를 넣거나 변경할 때 사용함 

## 7. JPA
- JPA는 인터페이스 -> 구현체가 hibernate임
- JPA는 객체와 ORM기술임(Object Relational Mapping)
- **객체 지향 코드와 관계형 데이터베이스를 매핑하여 상호 작용을 쉽게 해주는 기술**
- Spring boot jpa != Spring data jpa
