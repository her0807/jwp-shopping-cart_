# 장바구니

장바구니 미션 저장소

## ✏️ Code Review Process

[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https:github.com/next-step/nextstep-docs/tree/master/codereview)

## 요구사항

### 회원 가입을 구현한다.

- [x] 회원 가입을 구현한다.
- > **✅ - 필수(필수는 모두 NULL여부를 검증한다.) / ☑ - 선택**
- **✅ 이메일 (email)**
    - [x] 이메일은 고유하다.
    - 검증규칙 (regex)
        - 기존 이메일들과 중복 검증 후, 동일한 이메일이 있으면 예외를 발생시킨다.
        - 아래 정규식에 맞지 않을 경우 예외를 발생시킨다.
      ```java
      [0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.([a-zA-Z])+
      ```

- **✅ 비밀번호 (password)**
    - [x] 비밀번호는 해시화 해서 저장한다.
    - 검증규칙 : 8글자 이상 20글자 이하로 제한한다, 영문, 특수문자, 숫자 최소 1개씩 포함
      ```java
       ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$
      ```

- **✅ 프로필 이미지 (profileImageUrl)**
    - 검증규칙 : URL
    - 프로필 이미지는 [그라바타](https:ko.gravatar.com/)
      사용 [http:gravatar.com/avatar/:randomNumber?d=identicon](http:gravatar.com/avatar/1?d=identicon)
    - 검증 규칙(regex)


- **✅ 이름 (name)**
    - 검증규칙 : 2글자 이상, 5글자 이하, 한글만
        ```java
        "^([ㄱ-ㅎ|가-힣]).{2,5}$"
        ```
- **☑ 성별 (gender)**
    - 검증규칙 : “male” or “female” or “undefined” (ENUM)
    - enum으로 입력값이 유효한지 검증한다.

- **☑ 생년월일**
    - `YYYY-MM-DD`
        - 위의 날짜 포맷으로 입력을 받는다.
    - 검증규칙: 연도는 2000년대 까지 유효하다. 월은 01~12까지 가능하다, 일은 01~31까지 가능하다.

- **✅ 연락처 (contact)**
    - 검증규칙 : 숫자만 11자리, 하이픈 없이 (xxxxxxxxx ~ xxxxxxxxxxx)

- **✅ 주소지**
    - **✅ 주소** - address
        - 검증규칙 : not empty
    - **☑ 상세주소** - detailAddress
        - 검증규칙 : not empty
    - **✅ 우편번호** - zonecode
        - 검증규칙 : 숫자 5자리 (**[새 우편번호는 5자리…8월 1일부터 사용](https:www.korea.kr/news/policyNewsView.do?newsId=148798638))**

- **✅ 가입일자** (createdAt)
    - **검증규칙** : Datetime (서버기준)

- **✅ 약관 동의 여부** - boolean
    - 검증규칙: 해당 값이 true인지 확인한다.

### 로그인 기능을 구현한다.

- [x] 유효한 이메일인지 검증한다.
    - 검증규칙 (regex)
        - 기존 이메일들과 중복 검증 후, 동일한 이메일이 있으면 예외를 발생시킨다.
        - 아래 정규식에 맞지 않을 경우 예외를 발생시킨다.
           ```java
           [0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.([a-zA-Z])+
           ```  
- [x] 비밀번호가 일치한지 검증한다.
- [x] 로그인에 성공하면 엑세스 토큰을 발급한다.

### 회원정보 조회 기능을 구현한다.

- [x] 요청한 유저의 엑세스 토큰이 유효한지 검증한다.
- [x] 엑세스 토큰이 유효할시 회원정보를 반환한다.

### 회원정보 수정

- [x] 요청한 유저의 엑세스 토큰이 유효한지 검증한다.
- [x] 이메일과 생일, 프로필 이미지는 수정할 수 없다.
- [x] 이메일과 생일, 프로필을 제외한 나머지 정보는 수정할 수 있다.

### 회원 탈퇴

- [x] 요청한 유저의 엑세스 토큰이 유효한지 검증한다.
- [x] 요청이 들어오면, 회원 탈퇴를 명시적으로(delete_at)을 true 로 변경한다.

### 리팩터링
- [x] 권한 체크 여부의 역할을 interceptor 로 분리
- [ ] 상황에 맞는 exception 처리 
  - [ ] bedRequestException 처리
- [ ] 로그인 시 검증 로직 확인하기 
- [x] customerId 사용하는 로직으로 변경
- [ ] repository 계층 도입


