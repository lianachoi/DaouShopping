# DAOU Shopping

쇼핑몰 상품 주문 관리 서버   
Web Application 개발 경력 채용 기술과제 입니다.   

* [daou-shopping](https://github.com/lianachoi/daou-shopping) - 쇼핑몰 상품 주문 관리 화면으로 이동


## Getting Started


### Prerequisites

아래와 같은 환경에서 제작되었습니다.


* IntelliJ IDEA 2022.1.2
* Java 8 (java version "1.8.0_171")
* SpringBoot 2.7.0 (Spring 5.3.20)
* Maven2 build
* MyBatis 2.2.2
* h2 2.1.212
* lombok 1.18.24


### Installing

인텔리제이 기준으로 진행합니다.

```
File> New> Project from Version Control
현재 저장소 주소를 복사(clone)하여 붙여넣기 합니다.
```

./pom.xml에서 Maven build 진행합니다.

``` 
pom.xml 오른쪽 클릭> Maven 확장> Reload project
```

Lombok 플러그인 설치

```
Settings> Plugins> Marketplace
lombok 검색> install
Settings> Annotation Processors> 'Enable annotation processing' 체크
```

## Running the Server

SpringBoot 내장 WAS를 이용하여 서버를 실행합니다.

```
com.liana.DaouShopping/DaouShoppingApplication.java
해당 파일을 마우스 오른쪽으로 클릭한 후 Run 'DaouShoppingApplication'
```


### H2 Console
서버 시작 후 아래 링크로 접속   
서버 실행 시 table 과 기본 데이터는 생성됩니다.   
http://localhost:8080/h2-console/login.jsp

* 로그인 기능 이용 시 > id: daou / pw: daou22!
* 계좌이체 기능 이용 시 > 계좌번호: 1234 (1,000,000원)
* 입금계좌는 주문번호의 '-' 뒤 여섯글자입니다.   
(ex. 주문번호: 140622000000-123456, 입금계좌: 123456)

# Swagger API 명세
서버 시작 후 아래 링크로 접속   
http://localhost:8080/swagger-ui/index.html

# 도메인 설계
![image](https://user-images.githubusercontent.com/24507556/173575715-51165cf7-b8fd-4acf-80b9-c61b60e75467.png)
저장소 내 /DaouShop ERD.drawio.png

# 요구사항 Flow
![image](https://user-images.githubusercontent.com/24507556/173575787-c16573cb-a556-424c-9a92-c56b5c405c90.png)
저장소 내 /DaouShop FlowChart.drawio.png

### 개발 잔여 건
* 추가 상품 주문
* 카드결제기능
* 결제 환불 기능(진행중)
* 서비스 로직 분리
* 쿠폰/포인트 구매가격 넘지 않게 validation
* 포인트 이력 저장