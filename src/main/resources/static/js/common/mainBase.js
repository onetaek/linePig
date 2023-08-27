function onClickLogoutBtn() {
    fetch("/api/members/logout",{
        method:"POST"
    }).then((response) =>{
        if(response.ok) {
            window.location.href="/";
        }else {
            throw new Error('Network response was not ok');
        }
    })
}