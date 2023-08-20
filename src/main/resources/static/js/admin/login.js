function onSubmitBtn(event){
    event.preventDefault();

    const loginIdInput = document.querySelector('#loginId');
    const passwordInput = document.querySelector('#password');

    const requestBody = {
        loginId : loginIdInput.value,
        password : passwordInput.value,
    }

    const url = "/api/admins/login"
    fetch(url,{
        method : "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(requestBody)
    }).then(response => {
        if(response.ok) {
            window.location.href="/admins";
        } else {
            throw new Error('Network response was not ok');
        }
    }).catch(error => {
        alert(error)
    })

}