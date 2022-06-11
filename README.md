# welko

인하공전 모바이프로그래밍 WELKO 프로젝트


### 프로젝트의 주제 및 목적

외국인이 한국에 여행 차 방문을 하였을 때, 국내에서 여행지나 볼거리를 모아서 보여주는 서비스입니다.


### 사용 기술

- Android
  - <img src="https://img.shields.io/badge/Android Studio Chipmunk 2021.2.1 Patch 1-3DDC84?style=flat-square&logo=Android Studio&logoColor=white"/> <img src="https://img.shields.io/badge/Java 15-007396?style=flat-square&logo=Java&logoColor=white"/>
    - 강의 시간에 학습하였던 안드로이드 프레임워크를 기반으로 프로젝트를 진행하였습니다.

- Database
  - <img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat-square&logo=Firebase&logoColor=white"/>
    - 여행지 정보를 FireStore 에 저장하고, 안드로이드 프로젝트에서 SDK를 활용하여 호출하고 전송하는 로직을 구현하였습니다.
    - 로그인, 회원가입 시 외부 데이터베이스에 저장 후 불러오는 로직을 구현하기 위해 가비아에서 제공하는 DB 호스팅을 활용하였습니다.
    - 초기에 AWS RDS 를 기반으로 진행하였으나 일일 발생하는 비용이 부담되어 가비아 DB 호스팅으로 이전하였습니다.
    - 해당 데이터베이스를 활용한 백엔드 프로젝트는 (링크)[https://github.com/wkdgus1164/welko-backend.git]에서 확인하실 수 있습니다.

  
### 주요 기능

- 로그인 페이지
    - 이메일과 패스워드 작성 유무를 실시간으로 확인하여 로그인 버튼을 활성화/비활성화 하는 기능을 구현하였습니다.
    - 로그인 버튼을 클릭할 시 바로 백엔드 서버에 요청을 GET 메소드로 전달하여 사용자 검증을 진행하고,
    - 로그인 정보가 일치할 시 메인 화면으로 이동하고, 로그인 정보가 불일치할 시 로그인을 다시 시도하도록 구현하였습니다.


- 회원가입 페이지
    - 이름, 이메일, 패스워드, 패스워드 재입력 작성 유무를 실시간으로 확인하여 회원가입 버튼을 활성화/비활성화 하는 기능을 구현하였습니다.
    - 패스워드와 패스워드 재입력 작성 시 두 입력값이 모두 일치하는지를 실시간으로 확인하여, 두 입력값이 불일치할 시 빨간색 경고 메시지를 표시할 수 있도록 구현하였습니다.
    - 모든 정보를 정상적으로 작성하고 회원가입 버튼을 클릭할 시 해당 사용자가 입력한 회원 정보를 백엔드 서버에 POST 메소드로 전달하여,
    - 이메일 고유값이 중복되지 않을 시 회원가입 처리를 진행하고 해당 정보를 통해 플랫폼에 로그인할 수 있도록 구현하였습니다.


- 메인 페이지
- Firebase FireStore 서비스를 활용하여 여행지 데이터베이스를 생성하였습니다.
- 메인 페이지에서 보여지는 각 정보를 모두 FireStore 데이터베이스에서 호출하여 진행하였습니다.

### 개발 기간

2022.05.23 ~ 2022.06.14