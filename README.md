# 교내외 시설물 예약 시스템🏢
## 개요
- 교내 시설물에 대한 예약이 아날로그 형식으로, 행정실에 직접 수기로 신청해야 하는 불편함이 있었습니다. 그 외에도 학교 주변 상가들에 학생 손님들이 특정 시간에 많이 몰려 제대로 된 끼니 해결이나 문화 생활이 많이 어려웠습니다. 
- 이를 해결하고자 학교 내의 시설 및 교외 주변 상업 시설에 대한 정보를 이용하여 시설물에 대한 예약 및 주변 상업 시설에 대한 혼잡도를 표시하여 사용자들을 위한 학교 교내외 예약시스템을 구현하게 되었습니다.
### 목적
- 실시간으로 학교 내외의 예약현황 및 혼잡도 실시간으로 표시함으로써 학생 사용자에게는 좀 더 편리한 학교 생활을 할 수 있도록 하였습니다.
- 주변 상가의 점주 사용자들에게는 원할 한 상업 활동과 더 많은 고객 유치를 할 수 있도록 용이한 광고 홍보를 제공하여 고객 유치를 통한 수익증대를 기대 할 수 있도록 하였습니다.

<br><br>
## 개발 Tool
- 개발 언어 : Java
- 개발 환경 : AndroidStudio
- DB : Firebase

## DB ERD

## 서비스 구조
![화면 캡처 2022-09-10 215549](https://user-images.githubusercontent.com/70012637/189484343-eaf04f5b-191c-4eb6-b1e3-b3c75977bec0.png)

<br><br>

## 서비스 구성✏
![화면 캡처 2022-09-10 173126](https://user-images.githubusercontent.com/70012637/189475709-2c08c865-649d-4ebe-b9db-a679b6cb4c8b.png)
![화면 캡처 2022-09-10 173155](https://user-images.githubusercontent.com/70012637/189475668-693c4c7d-498e-4fa6-a907-d8e61308837d.png)
![화면 캡처 2022-09-10 173355](https://user-images.githubusercontent.com/70012637/189475675-aec35484-309d-4f1f-b3db-95dbec979965.png)
![화면 캡처 2022-09-10 173413](https://user-images.githubusercontent.com/70012637/189475689-40df4850-0fe5-43c8-98bb-17d68a4fda45.png)

### 로그인, 회원가입 화면
- Firebase Authentication 기능을 사용하여 로그인 인증 기능 구현 
- 학생 및 업주 각 사용자의 필요한 정보에 맞는 회원가입 및 로그인 기능 구현


### 업주 사용자 관리 화면
- 업주 사용자의 시설 정보 변경 기능 구현
- 해당 업장 혼잡도 선택 기능
- 영업 외 시간일 경우 자동 영업중비중 변경 기능 구현 

### 학생 사용자 페이지
- 메인페이지
  - 왼쪽 상단 Drawer Action Bar를 이용하여 회원 정보, 예약 현항 조회 및 변경 기능 구현
 
#### 교외 시설물 번잡도 조회 페이지

#### 교내 시설물 예약 및 현황 조회 페이지

