## 글로벌 쇼핑몰 사이트(Lingpig)

### linePig cloud type 배포링크(devoloper version으로 구현되지 않은 기능이 존재합니다.)
+ 사용자 페이지 : https://port-0-linepig-jvpb2alnioh8cn.sel5.cloudtype.app/
  + [사용자(샘플) 계정] -> 회원가입 번거로울 경우 사용
  + ID : sample
  + PW : test1234!
+ 관리자 페이지 : https://port-0-linepig-jvpb2alnioh8cn.sel5.cloudtype.app/admins
  + [관리자 계정]
  + id : linepig
  + pw : linepig1!@

## 프로젝트 소개 및 주제
+ 프로젝트 명칭 : Linepig
+ 개발인원 : 1명
+ 다국어 기능을 지원하는 쇼핑몰 사이트로 유저들 제품을 구매 뿐만 아니라 구매자가 되어 상품을 판매할 수 있는 사이트입니다.
+ 관리자 페이지의 강력한 기능을 통해 웹사이트를 효과적으로 컨트롤할 수 있습니다.
+ 현재는 서버사이드 랜더링으로 웹애플리케이션이 구현되어있지만, 사용자 경험을 클라이언트 사이드 랜더링으로 리팩터링중입니다.
+ 개발중인 단계로 구현되지 않은 기능들이 존재합니다.

## 기술 스택
+ 개발언어 : Java, JavaScript
+ DB : H2 Database, MariaDB, JPA, QueryDsl
+ 프레임워크 : Spring boot, Vue.js, AdminLTE
+ 베포 : AWS EC2, Cloud Type, Git, Github Action

## ERD (Entity Relationshop Diagram)
+ ERD Cloud 링크 : https://www.erdcloud.com/d/r3E6MAQL56Ywv2iaa

+ 전제 ERD
![전체erd](https://github.com/onetaek/linepig-BE/assets/86419261/1334012e-1689-4706-9858-72983ae1fead)

+ 제품 ERD
![제품erd](https://github.com/onetaek/linepig-BE/assets/86419261/1e870289-7ca2-4b5d-acc7-ac9d41bbd2ec)

+ 회원, 장바구니 ERD
![제품_장바구나erd](https://github.com/onetaek/linepig-BE/assets/86419261/de2dcd13-af32-4d79-8d87-97edbaf70ee8)

+ 주문 ERD
![주문erd](https://github.com/onetaek/linepig-BE/assets/86419261/2cd498cc-50e2-4a5c-ab6f-16e097492d1f)

+ 게시판 ERD
![게시판erd](https://github.com/onetaek/linepig-BE/assets/86419261/4b5417f2-45ad-4c1e-bd14-0250b10f19e5)


## 상태정의도
+ 제품 상태 정의도

![제품상태정의도](https://github.com/onetaek/linepig-BE/assets/86419261/9cb2334b-b0e0-4909-a54d-1c874f168cf6)

+ 주문 상태정의도

![주문상태정의도](https://github.com/onetaek/linepig-BE/assets/86419261/5e7ed8f6-7304-4df4-8f6f-7b8fd1bba901)

+ 환불 상태정의도

![환불상태정의도](https://github.com/onetaek/linepig-BE/assets/86419261/8ac0d742-3bb3-4be3-9529-4ce065c8894c)


## 사이트 주요화면
+ 사용자 페이지
  + 메인 페이지
![메인페이지](https://github.com/onetaek/linepig-BE/assets/86419261/daaed37c-8c85-46c9-9757-95761b1917e2)

  + 로그인 페이지
  
![로그인페이지](https://github.com/onetaek/linepig-BE/assets/86419261/5473278b-5990-40d3-ad6c-0acdf981523d)

  + 제품상세 페이지
![상세페이지1](https://github.com/onetaek/linepig-BE/assets/86419261/b22d58e1-2318-43f2-a0aa-ae20c018c609)
![상세페이지2](https://github.com/onetaek/linepig-BE/assets/86419261/36f8a3b3-0fc4-482a-8cf2-e2766dabd7a5)

+ 관리자 페이지
  + 제품목록 페이지
![관리자 상품 리스트](https://github.com/onetaek/linepig-BE/assets/86419261/64286697-6319-49ae-b7f4-205d1f89f8ed)

  + 제품등록 페이지
 ![제품등록1](https://github.com/onetaek/linepig-BE/assets/86419261/7a1358f3-437a-4daf-99c1-00aa6788edf4)
![제품등록2](https://github.com/onetaek/linepig-BE/assets/86419261/1fc3f8b7-5aeb-431f-9439-a6c3ea275c47)

  + 회원목록 페이지
 ![회원목록](https://github.com/onetaek/linepig-BE/assets/86419261/3034dd69-bd25-4440-8591-2a7dd7b01861)

  + 판매자등록 페이지
 ![판매자등록](https://github.com/onetaek/linepig-BE/assets/86419261/4febd8c7-a403-4a8b-8457-f8ccac7a289c)

  + 게시글작성 페이지
![게시글 작성](https://github.com/onetaek/linepig-BE/assets/86419261/db9e91c5-18fe-4927-b0aa-9c03755489cb)
