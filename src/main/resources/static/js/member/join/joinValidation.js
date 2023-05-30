let loginIdInput = document.querySelector("#loginId");//로그인 아이디 input
let passwordInput = document.querySelector("#password");//비밀번호 input
let passwordCheckInput = document.querySelector("#passwordCheck");//비밀번호 확인 input
let nameInput = document.querySelector("#name");//이름 input
let phoneCodeSelect = document.querySelector("#phoneCode");//핸드폰 국제 번호 input
let phoneNumberInput = document.querySelector("#phoneNumber");//핸드폰 앞자리 input
let emailInput = document.querySelector("#email");//email input

const loginIdError = document.querySelector("#loginId-error");//로그인 에러 메시지
const passwordError = document.querySelector("#password-error");//비밀번호 에러 메시지
const passwordCheckError = document.querySelector("#password-check-error");//비밀번호 확인 에러 메시지
const nameError = document.querySelector("#name-error");//이름 에러 메시지
const phoneError = document.querySelector("#phone-error");//핸드폰 번호 에러 메시지
const emailError = document.querySelector("#email-error");//이메일 에러 메시지
let loginIdDuplicate = document.querySelector("#loginId-duplicate");

const joinBtn = document.querySelector("#join-btn");

//국제코드 select option 추가
countryCode.forEach((element)=>{
    // console.log(element["country"])
    // console.log(element["code"])
    const optionHtml = `<option value="${element["code"]}">${element["country"]}</option>`;
    phoneCodeSelect.appendChild(myCreateElement(optionHtml));
})

//유효성 검사 시작

//빈 값을 허용하지 않고 최소 6자 이상이면서 영어만 허용
let repExpLoginId = /^[a-z]{6,}$/;
//띄어쓰기 없는 8~15자의 영문 대/소문자, 숫자 또는 특수문자 조합
let regExpPassword = /^[a-zA-Z0-9!@#$%^&*()]{8,15}$/;
// 이름: 빈 값을 허용하지 않음
let regExpName = /^.+$/;
// 핸드폰번호
let regExpPhone = /^\d{10,11}$/;
// 이메일 검사
let regExpEmail = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;


//아이디 유효성 검사
function loginIdValidate(){
    if (!repExpLoginId.test(loginIdInput.value)) {// 유효성 검사 실패
        loginIdError.classList.add("active");
        return false;
    }else{//유효성 검사 통과
        loginIdError.classList.remove("active");
        return true;
    }
}
//비밀번호 유효성 검사
function passwordValidate(){
    if (!regExpPassword.test(passwordInput.value)) {// 유효성 검사 실패
        passwordError.classList.add("active");
        return false;
    }else{//유효성 검사 통과
        passwordError.classList.remove("active");
        return true;
    }
}

//비밀번호 확인 유효성 검사
function passwordCheckValidate(){
    if(passwordInput.value !== passwordCheckInput.value){// 유효성 검사 실패
        passwordCheckError.classList.add("active");
        return false;
    }else{//유효성 검사 통과
        passwordCheckError.classList.remove("active");
        return true;
    }
}

//이름 유효성 검사
function nameValidate(){
    if (!nameInput.value){
        nameError.classList.add("active");
        return false;
    }else{
        nameError.classList.remove("active");
        return true;
    }
}

//핸드폰 유효성 검사
function phoneValidate(){
    if(!regExpPhone.test(phoneNumberInput.value)){
        console.log("실패")
        phoneError.classList.add("active");
        return false;
    }else{
        console.log("성공")
        phoneError.classList.remove("active");
        return true;
    }
}

//이메일 유효성 검사
function emailValidate(){
    if(!regExpEmail.test(emailInput.value)){
        emailError.classList.add("active");
        return false;
    }else{
        emailError.classList.remove("active");
        return true;
    }
}
//아이디 중복 검사
function loginIdDuplicateCheck(){
    fetch("/api/members/check-loginId",{
        method:"post",
        body:JSON.stringify({
            loginId : loginIdInput.value
        }),
        headers: {
            "Content-type": "application/json"
        }
    }).then((response)=>{
        console.log("상태코드",response.status);
        if(response.status === 409){
            loginIdDuplicate.classList.add("active");
        } else if(response.status === 200){
            loginIdDuplicate.classList.remove("active");
        }else{
            alert("예상치 못한 응답");
        }
    })
}


loginIdInput.addEventListener("focusout",()=>{
    if(loginIdValidate()){
        loginIdDuplicateCheck();
    }
});
passwordInput.addEventListener("focusout",()=>{
   passwordValidate();
});
passwordCheckInput.addEventListener("focusout",()=>{
   passwordCheckValidate();
});
nameInput.addEventListener("focusout",()=>{
   nameValidate();
});
phoneNumberInput.addEventListener("focusout",()=>{
   phoneValidate();
});
emailInput.addEventListener("focusout",()=>{
   emailValidate();
});

joinBtn.addEventListener("click",()=>{
    console.log("클릭!");
    console.log(loginIdValidate());
    console.log(passwordValidate());
    console.log(passwordCheckValidate());
    console.log(nameValidate());
    console.log(phoneValidate());
    console.log(emailValidate());
    if(
        loginIdValidate()&&
        passwordValidate()&&
        passwordCheckValidate()&&
        nameValidate()&&
        phoneValidate()&&
        emailValidate()
    ){
        console.log("모두 통과");
        document.querySelector("#join-form").submit();
    }
});