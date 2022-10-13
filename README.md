# SpringBoot
부동산_실거래가_조회_프로젝트

- 수행 인원

2인

- 개발 환경

JAVA

Mysql

SpringBoot(Maven)

Vue.js

- 목표

우선, 저희 프로젝트는 페이지 이동을 최소화하고, 현재 페이지를 동적으로 작성하여 사용자의 편리함을 가장 최우선으로 생각한 프로젝트입니다.

- 본인이 수행한 역할

DB 설계, REST API 구현, 회원 관련 UI 구현, 거리 비교 logic 구현

- DB 설계

기본적인 DB 설계는 userinfo table과 notice table, 각 유저의 생활 영역을 관리할 userlife table, 관리자만 글을 올릴 수 있는 notice table, 부동산 데이터들을 받아줄 houseinfo table(외 다수)로 구성했습니다. 설계 부분은 페어와 함께 진행했으며, 실제 테이블 구현은 저의 역할이었습니다.

![KakaoTalk_20220728_212623512.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/195557f3-7bce-4b30-b69d-0f95aea1764c/KakaoTalk_20220728_212623512.png)

- 메인 페이지

저희 사이트의 메인 페이지는 아래 사진과 같습니다. 카카오 맵 API를 활용했으며, 좌측의 검색창은 기본적으로 로그인하고 난 후 jwt 토큰 발행 전에는 검색할 수 없게 설정해 주었고, 우측의 아이콘을 누르면 sidebar가 나타나서 기능을 이용 가능하게 하였습니다.

![KakaoTalk_20220728_212801955.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c5dad6b5-2e55-493c-bf88-6b5a97e947e9/KakaoTalk_20220728_212801955.png)

- 사이드 바 활용

회원 가입을 누르면, POST Mapping으로 만들어진 controller에 요청이 들어가게 해서 UserService를 호출하고, UserService에서는 Repository(Mybatis 사용)를 호출해 관련 sql 구문을 mapper에서 가져와 실행하는 방식입니다.

![KakaoTalk_20220728_212829235.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/032d4151-eefd-4117-ab1d-3633e9a99a0c/KakaoTalk_20220728_212829235.png)

아래 그림은 로그인 모달 창입니다. 저희 프로젝트의 컨셉 중 하나로, 메인 페이지를 제외한 모든 창을 BootStrapVue를 통해 모달 창으로 구현했습니다.

![KakaoTalk_20220728_212846872.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f0432473-e0fc-47b9-a986-0ab1085e4a34/KakaoTalk_20220728_212846872.png)

아래 그림은 회원 관리를 할 수 있는 모달 창이며, 해당 창은 userinfo table에 admin을 구별할 수 있는 column을 따로 구현하여 관리자는 여러 회원 정보에 대한 수정, 삭제를 할 수 있는 기능을 구현했습니다.

![KakaoTalk_20220728_213159060.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a1befa34-4d09-4894-a6a0-d39358b02988/KakaoTalk_20220728_213159060.png)

아래 그림은 메인 기능입니다. 회원이 원하는 키워드를 검색하면 그 키워드가 포함된 부동산 데이터가 검색됩니다. 그리고 그 부동산의 매매 내역, 평균 거래가, 회원이 생활 영역(userlife table)으로 지정한 곳과 해당 부동산의 평균 거리, 생활 영역 중 가장 가까운 곳, 가장 먼 곳을 출력해 주었습니다. Houseinfo table의 lat(위도), lng(경도)와 생활 영역의 위도, 경도를 비교하여 거리를 계산해주는 logic을 구현하는 역할을 맡았습니다.

![KakaoTalk_20220728_213331756.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6f1a23ee-e8ab-41c4-a006-8faeec311101/KakaoTalk_20220728_213331756.png)

아래 그림은 마우스 커서로 지도를 클릭했을 때, 장소를 기억해서 생활 영역에 등록해주는 기능입니다. 장소 기억하기를 클릭하게 되면…

![KakaoTalk_20220728_213453580.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/20e88cd2-439c-43ca-ba58-9892a39bc92e/KakaoTalk_20220728_213453580.png)

아래 그림과 같은 모달 창이 나타나면서 userlife table에 이름 데이터, 위도, 경도를 넣어주게 됩니다.

![KakaoTalk_20220728_213237649.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/da5ede4b-69cd-497a-a0bb-f24a16a2ac51/KakaoTalk_20220728_213237649.png)

해당 프로젝트를 진행하면서 제가 client로써 지도 어플리케이션을 사용할 때, ‘어떤 점이 추가되면 더 편했을까?’ 을 최대한 생각하려고 노력했습니다.
