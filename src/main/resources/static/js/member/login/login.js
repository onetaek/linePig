let loginBtn = document.querySelector("#login-btn");
let loginForm = document.querySelector("#login-form");


let kakaoLoginBtn = document.querySelector("#kakao-login-btn");

kakaoLoginBtn.addEventListener("click",()=>{

    loginForm.method="POST";
    loginForm.action="/oauth/kakao/login";
    loginForm.submit();

});