![그림1](https://user-images.githubusercontent.com/110139421/208946150-485d6973-cc79-4e2d-bcba-cd30af127b6e.png)

# &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SSAFIT

## 기획의도

> \#온라인 PT \#나만의 루틴으로

온라인 PT라는 점이 외부요인에 영향을 받지 않고 자신의 스케줄대로 운동을 할 수 있도록 해준다. 또한 PT 영상을 패키지로 묶어 판매하지 않아 사용자가 원하는 부위, 운동목적으로 PT 영상을 구매하여 운동할 수 있다.

<br>

## 개요

구매한 PT영상으로 사용자는 자신만의 ‘운동 루틴’(플레이리스트)을 생성할 수 있다. 혹여나 루틴 짜기에 어려움을 겪는 사용자를 위해 회원가입 시 기재한 운동목적, 팔로우, 판매순 등을 고려하여 맞춤 PT를 추천한다. 전역적으로 유지되는 영상 재생창 덕분에 사용자가 플랫폼을 이용하는데 불편함을 덜할 수 있도록 하였다.

또한 일반 사용자는 간단한 등록 절차를 통해 트레이너로 전환 가능한데, 자신의 PT 영상을 등록하여 판매할 수 있고 정산 금액을 확인할 수 있다.

<br>

## 구현 기능

### 기본 기능

#### User

1. 회원가입

   - 아이디, 이메일, 닉네임, 핸드폰 번호 중복 검사

2. 회원정보 수정
3. 회원 상세보기(마이페이지)
4. 로그인

   - JWT를 이용한 로그인 처리

5. 로그아웃

   - 유효시간을 60분으로 설정하여 자동 로그아웃 처리

6. 팔로워 & 팔로잉
   - 유저 간 팔로워 & 팔로잉
   - 일반 사용자와 일반 사용자 & 일반 사용자와 트레이너

#### Video

1. 영상 검색

   - 10개의 카테고리로 원하는 운동 부위별 영상 검색
   - 제목+트레이너명(채널명), 제목, 트레이너명(채널명)으로 검색
   - 조회수, 등록일, 좋아요수, 가격높은순, 가격낮은순 기준으로 정렬하여 검색

2. 영상 상세보기

   - 영상 조회수, 좋아요수 확인 가능
   - 운동 부위, 가격, 등록일자, 해당 트레이너의 다른 PT 영상 확인

3. 영상 좋아요
4. 영상 댓글
   - 댓글 작성
   - 댓글 수정
   - 댓글 삭제

### 주요 기능

1. 회원 맞춤 PT 추천

   - 최신순, 인기순(총 판매순), 팔로우한 트레이너, 사용자의 운동목적 4가지 카테고리로 회원 맞춤 PT 추천

2. 전역적으로 유지되는 영상 재생창

   - PT 영상을 모달창으로 재생
   - 모달창으로 띄운 PT 영상은 전역적으로 유지되어 모든 페이지에서 시청 가능

3. 일반 사용자와 트레이너

   - 일반 사용자: 유저 검색, 트레이너 전환, 플레이리스트 관리, 좋아요 목록
   - 트레이너: 영상 등록하기, 정산금액 확인, 등록한 영상 목록 \* 트레이너의 경우, 일반 사용자 모드와 트레이너 모드 모두 이용 가능

4. 플레이리스트(운동 루틴 리스트)

   - 일반 사용자가 구매한 PT 영상들을 원하는 조합과 순서로 자유롭게 생성

5. PT 영상 업로드

   - 트레이너 전용 페이지에서 영상 등록 가능
   - 영상 업로드 시 썸네일 생성

6. 장바구니 및 결제하기

7. 코치 검색
   - 등록된 모든 코치들 조회
   - 코치 상세보기(팔로워 수, 팔로잉 수, 경력, 홍보용 영상, SNS 연결, 팔로우하기)

<br>

## Tech

<img src="https://img.shields.io/badge/SPRING BOOT-6DB33F?style=flat-square&logo=springboot&logoColor=white" style="height:2rem"/> <img src="https://img.shields.io/badge/VUEJS-black?style=flat-square&logo=vue.js&logoColor=4FC08D" style="height:2rem"/> <img src="https://img.shields.io/badge/MYSQL-4479A1?style=flat-square&logo=mysql&logoColor=white" style="height:2rem"/>

<br>

## Tool

<img src="https://img.shields.io/badge/GIT-F05032?style=flat-square&logo=git&logoColor=white" style="height:2rem"/> <img src="https://img.shields.io/badge/GITHUB-181717?style=flat-square&logo=github&logoColor=white" style="height:2rem"/> <img src="https://img.shields.io/badge/NOTION-000000?style=flat-square&logo=notion&logoColor=white" style="height:2rem"/> <img src="https://img.shields.io/badge/MATTERMOST-0058CC?style=flat-square&logo=mattermost&logoColor=white" style="height:2rem" />

<br>

## API

<img src="https://img.shields.io/badge/YOUTUBE-FF0000?style=flat-square&logo=youtube&logoColor=white" style="height:2rem" /> <img src="https://img.shields.io/badge/i'mport-1890ff?style=flat-square&logo=i'mport&logoColor=white" style="height:2rem"/>

- YouTube Data API v3
  - 트레이너 개인 페이지 내 홍보용 영상
- i'mport

  - 결제

<br>

## ECT

- Codepen 오픈소스 활용
