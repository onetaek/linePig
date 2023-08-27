let loginBtn = document.querySelector("#login-btn");
let loginForm = document.querySelector("#login-form");


let kakaoLoginBtn = document.querySelector("#kakao-login-btn");

kakaoLoginBtn.addEventListener("click",()=>{

    loginForm.method="POST";
    loginForm.action="/oauth/kakao/login";
    loginForm.submit();

});

//회원 로그인
function onSubmitBtn(event) {
    event.preventDefault();

    const loginIdInput = document.querySelector('#loginId');
    const passwordInput = document.querySelector('#password');

    const requestBody = {
        loginId : loginIdInput.value,
        password : passwordInput.value,
    }

    const url = "/api/members/login"
    fetch(url,{
        method : "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(requestBody)
    }).then(response => {
        if(response.ok) {
            window.location.href="/";
        } else {
            throw new Error('Network response was not ok');
        }
    }).catch(error => {
        alert(error)
    })
}