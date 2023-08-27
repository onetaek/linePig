function onSubmitBtn(event) {
    console.log("회원가입시작!!")
    event.preventDefault();
    if(
        loginIdValidate()&&
        passwordValidate()&&
        passwordCheckValidate()&&
        nameValidate()&&
        phoneValidate()&&
        emailValidate()
    ){
        console.log("모두 통과");
    } else {
        alert("회원가입 유효성 검사를 실패하였습니다")
    }


    const loginIdInput = document.querySelector('#loginId');
    const passwordInput = document.querySelector('#password');
    const passwordCheckInput = document.querySelector('#passwordCheck');
    const nameInput = document.querySelector('#name');
    const phoneCodeSelect = document.querySelector('#phoneCode');
    const phoneNumberInput = document.querySelector('#phoneNumber');
    const emailInput = document.querySelector('#email');

    fetch('/api/members/join',{
        method: "POST",
        headers : {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            loginId: loginIdInput.value,
            password: passwordInput.value,
            passwordCheck: passwordCheckInput.value,
            name: nameInput.value,
            phoneCode: phoneCodeSelect.value,
            phoneNumber: phoneNumberInput.value,
            email: emailInput.value
        })
    }).then(response =>{
        if(response.ok) {
            alert("회원가입에 성공하였습니다")
            window.location.href="/"
        } else {
            alert("회웝가입에 실패하셨습니다");
        }
    }).catch(error => {
        alert(error)
    })

    return false;
}