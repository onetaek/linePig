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

function changeLanguage(selectElement) {
    console.log("언어 변경");
    fetch(`/api/userLanguage/${selectElement.value}`,{
        method:"PATCH"
    }).then((response) => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert("Network response was not ok");
        }
    })
}