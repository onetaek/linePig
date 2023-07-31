function onClickLogoutBtn() {

    const url = '/api/admins/logout';

    fetch(url,{
        method:'POST'
    }).then((response) => {
        if(response.ok) {
            window.location.href='/admins/login';
        } else {
            throw new Error('Network response was not ok')
        }
    }).catch(error => {
        alert(error)
    })
}