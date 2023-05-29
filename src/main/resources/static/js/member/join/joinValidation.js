let loginIdInput = document.querySelector("#loginId");//로그인 아이디 input
let passwordInput = document.querySelector("#password");//비밀번호 input
let passwordCheckInput = document.querySelector("#passwordCheck");//비밀번호 확인 input
let nameInput = document.querySelector("#name");//이름 input
let phone1Select = document.querySelector("#phone1");//핸드폰 국제 번호 input
let phone2Input = document.querySelector("#phone2");//핸드폰 앞자리 input
let phone3Input = document.querySelector("#phone3");//핸드폰 중간자리 input
let phone4Input = document.querySelector("#phone4");//핸드폰 뒷자리 input
let email1Input = document.querySelector("#email1");//email1 input
let email2Input = document.querySelector("#email2");//email2 input

const loginIdError = document.querySelector("#loginId-error");//로그인 에러 메시지
const passwordError = document.querySelector("#password-error");//비밀번호 에러 메시지
const passwordCheckError = document.querySelector("#password-check-error");//비밀번호 확인 에러 메시지
const nameError = document.querySelector("#name-error");//이름 에러 메시지
const phoneError = document.querySelector("#phone-error");//핸드폰 번호 에러 메시지
const emailError = document.querySelector("#email-error");//이메일 에러 메시지


//국제코드 select option 추가
countryCode.forEach((element)=>{

    console.log(element["country"])
    console.log(element["code"])

    const optionHtml = `<option value="${element["code"]}">${element["country"]}</option>`;
    phone1Select.appendChild(myCreateElement(optionHtml));
})

//유효성 검사 시작

//빈 값을 허용하지 않고 최소 6자 이상이면서 영어만 허용
let repExpLoginId = /^[a-zA-Z]{6,}$/;
//띄어쓰기 없는 8~15자의 영문 대/소문자, 숫자 또는 특수문자 조합
let regExpPassword = /^[a-zA-Z0-9!@#$%^&*()]{8,15}$/;
// 이름: 빈 값을 허용하지 않음
let regExpName = /^.+$/;
// 핸드폰번호 앞자리: 빈 값을 허용하지 않음
let regExpPhone2 = /^.+$/;
// 이메일 앞부분 검사
let regExpEmail1 = /^[a-zA-Z0-9._%+-]+$/;
// 이메일 뒷부분 검사
let regExpEmail2 = /^[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

//아이디 유효성 검사
function loginIdValidate(){
    if(repExpLoginId.test(loginIdInput.value)){
        //유효성 검사 실패
        loginIdError.innerText = "아이디는 빈 값을 허용하지 않고 최소 6자 이상이면서 영어만 허용";


        return false;
    }else{

        //유효성 검사 통과
        return true;
    }
}
