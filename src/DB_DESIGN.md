## Store

| Column | Datatype | Storage Option | Default Value | Comment |
| --- | --- | --- | --- | --- |
| store_seq | int | Primary Key, Not null |  | 매장 일련번호 |
| address | varchar(100) | Not null |  | 매장 주소 |
| name | varchar(50) | Not null |  | 매장 이름 |
| branch_name | varchar(50) | Not null |  | 지점 이름 |
| use_flag | tinyint(1) | Not null |  | 사용 여부 |
| reg_date | datetime | Not null |  | 등록일 |
| update_date | datetime | Not null |  | 수정일 |

<aside>
💡 방탈출 매장 정보
> 직원 친절도, 할인 혜택 등을 추가 생각해봤지만 필요 없을 것 같아서 제외
> 매장 후기를 간단한 코멘트로 구현 고려

</aside>

## Theme

| Column | Datatype | Storage Option | Default Value | Comment |
| --- | --- | --- | --- | --- |
| theme_seq | int | Primary Key, Not null |  | 테마 일련번호 |
|store_seq| int | Not null |  | 매장 일련번호(FK) |
| name| varchar(100) | Not null |  | 테마 이름 |
| price | int | Not null |  | 가격 |
| difficulty_rating | float(3,2) | Not null |  | 매장 표기 난이도 |
| desc | text |  | “” | 테마 설명 |
| use_flag | tinyint(1) | Not null |  | 사용 여부 |
| reg_date | datetime | Not null |  | 등록일 |
| update_date | datetime | Not null |  | 수정일 |


<aside>
💡 방탈출 테마 정보
> 방탈출 사용자 평균 평점, 난이도, 활동성, 공포도를 저장할 방법 고민
> Redis 캐쉬, api 호출을 통한 batch의 형태
> 일단 인스턴스에서 Redis 서버를 띄워 처리해보자

```
Redis

장점

- DB에 Column으로 따로 저장할 필요 없음
    - Review 목록 조회후 계산 → 캐싱 → 반환 객체에 저장 후 전달

단점

- DB 커넥션 설정이 늘어남
- Redis 서버 추가적으로 필요
- 전체 갱신 시간이 일정하지 않음(호출시 개별적으로 갱신이 되기 때문)

API

장점

- 전체 갱신 시간이 일정
- Review 목록 조회후 계산 → DB에 저장 → 반환 객체에 저장된 항목 조회후 전달
    - spring batch는 너무 과한 것 같고 수동 호출이나 cronTab 정도로 효율적으로 해결 가능

단점

- 조회시마다 DB에서 Column들을 조회해서 가져와야함 → 비효율적
```
</aside>

## Review

| Column | Datatype | Storage Option | default value | comment |
| --- | --- | --- | --- | --- |
| review_seq | int | Primary Key, Not null |  | 후기 일련번호 |
| theme_seq | int | unique key, Not null |  | 테마 일련번호(FK) |
| user_seq | int | unique key, Not null |  | 사용자 일련번호(FK) |
| title | varchar(100) | Not null |  | 제목 |
| member_count | int | Not null |  | 인원 |
| success_flag | tinyint(1) | Not null |  | 성공 여부 |
| visit_date | datetime | Not null |  | 방문 일자 |
| review_rating | float(3,2) | Not null |  | 평점 |
| difficulty_rating | float(3,2) | Not null |  | 난이도 |
| activity_rating | float(3,2) | Not null |  | 활동성 |
| fear_rating | float(3,2) | Not null |  | 공포도 |
| memo | text |  | “” | 후기 |
| use_flag | tinyint(1) | Not null |  | 사용 여부 |
| reg_date | datetime | Not null |  | 등록일 |
| update_date | datetime | Not null |  | 수정일 |

<aside>
💡 방탈출 후기
> 사용자가 하나의 테마당 하나의 리뷰만을 쓰게끔 설계
>> theme_seq와 user_seq를 PK로 설정 OR unique key 설정
>>> 나중에 후기에 이미지 추가 기능이 생긴다면 review_seq 필요 → unique key 설정

</aside>

## Image

| Column | Datatype | Storage Option | default value | comment |
| --- | --- | --- | --- | --- |
| image_seq  | int | Primary Key, Not null |  | 이미지 일련번호 |
| type_seq | int | Not null |  | 해당하는 table의 일련번호 |
| table_code | varchar(50) | Not null |  | 테이블 코드(STORE, THEME, (REVIEW), …) |
| image_url | varchar(500) | Not null |  | 이미지 url |
| use_flag | tinyint(1) | Not null |  | 사용 여부 |
| reg_date | datetime | Not null |  | 등록일 |
| update_date | datetime | Not null |  | 수정일 |

<aside>
💡 이미지
> 처음엔 리뷰 이미지도 함께 저장하려 했으나… 요즘 이미지 데이터가 고화질이라 클 것으로 예상되어 요금 문제로 생략 + 사진은 초상권이 존재하기에 문제가 될 수도 있지 않을까…? 하여 생략
> table_code으로 어떤 table의 이미지인지 조회하고 type_seq로 해당 table의 PK값을 확인

</aside>

## user

| Column | Datatype | Storage Option | default value | comment |
| --- | --- | --- | --- | --- |
| user_seq| int | Primary Key, Not null |  | 사용자 일련번호 |
| join_code | varchar(50) | Not null |  | 회원가입 코드(HOME, KAKAO, …) |
| id | varchar(100) | Not null, unique |  |  |
| password | varchar(200) | Not null |  |  |
| nickName | varchar(50) | Not null, unique |  |  |
| use_flag | tinyint(1) | Not null |  | 사용 여부 |
| reg_date | datetime | Not null |  | 등록일 |
| update_date | datetime | Not null |  | 수정일 |

<aside>
💡 사용자
>회원가입 코드는 나중에 기회가 된다면 OAuth 연동을 위해 생성

</aside>