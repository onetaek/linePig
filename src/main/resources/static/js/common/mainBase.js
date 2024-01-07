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



const homeMenu = document.querySelector('#nav-menu-wrap .home h2');
const shippingMenu = document.querySelector('#nav-menu-wrap .products h2');
const infoMenu = document.querySelector('#nav-menu-wrap .infos h2');
const noticeMenu = document.querySelector('#nav-menu-wrap .boards h2');
const contactMenu = document.querySelector('#nav-menu-wrap .contacts h2');
const magazineMenu = document.querySelector('#nav-menu-wrap .magazines h2');
const underLine = document.querySelector("#under-line");

const currentUri = extractPathFromUri(window.location.href);
let currentOffsetLeft = undefined;
let currentOffsetWidth = undefined;
let currentOffsetTop = undefined;

switch (currentUri) {
    case "/":
        currentOffsetLeft = homeMenu.offsetLeft + "px";
        currentOffsetWidth = homeMenu.offsetWidth + "px";
        currentOffsetTop = homeMenu.offsetTop + homeMenu.offsetHeight + "px";
        break;
    case "/products":
        currentOffsetLeft = shippingMenu.offsetLeft + "px";
        currentOffsetWidth = shippingMenu.offsetWidth + "px";
        currentOffsetTop = shippingMenu.offsetTop + shippingMenu.offsetHeight + "px";
        break;

    case "/infos":
        currentOffsetLeft = infoMenu.offsetLeft + "px";
        currentOffsetWidth = infoMenu.offsetWidth + "px";
        currentOffsetTop = infoMenu.offsetTop + infoMenu.offsetHeight + "px";
        break;

    case "/boards":
        currentOffsetLeft = noticeMenu.offsetLeft + "px";
        currentOffsetWidth = noticeMenu.offsetWidth + "px";
        currentOffsetTop = noticeMenu.offsetTop + noticeMenu.offsetHeight + "px";
        break;

    case "/contacts":
        currentOffsetLeft = contactMenu.offsetLeft + "px";
        currentOffsetWidth = contactMenu.offsetWidth + "px";
        currentOffsetTop = contactMenu.offsetTop + contactMenu.offsetHeight + "px";
        break;

    case "/magazines":
        currentOffsetLeft = magazineMenu.offsetLeft + "px";
        currentOffsetWidth = magazineMenu.offsetWidth + "px";
        currentOffsetTop = magazineMenu.offsetTop + magazineMenu.offsetHeight + "px";
        break;

    default:
        currentOffsetLeft = homeMenu.offsetLeft + "px";
        currentOffsetWidth = homeMenu.offsetWidth + "px";
        currentOffsetTop = homeMenu.offsetTop + homeMenu.offsetHeight + "px";
}

underLine.style.left = currentOffsetLeft
underLine.style.width = currentOffsetWidth
underLine.style.top = currentOffsetTop

const menus = document.querySelectorAll('#nav-menu-wrap li h2');
menus.forEach(menu => menu.addEventListener("mouseenter",(e) => menuMouseEnter(e)));
function menuMouseEnter(e) {
    underLine.style.left = e.currentTarget.offsetLeft + "px";
    underLine.style.width = e.currentTarget.offsetWidth + "px";
    underLine.style.top = e.currentTarget.offsetTop + e.currentTarget.offsetHeight + "px";
}
const navMenuWrap = document.querySelector('#nav-menu-wrap');
navMenuWrap.addEventListener("mouseleave", menuMouseLeave)

function menuMouseLeave() {
    setTimeout(function() {
        underLine.style.left = currentOffsetLeft;
        underLine.style.width = currentOffsetWidth;
        underLine.style.top = currentOffsetTop;
    }, 500); // 1000 밀리초(1초) 후에 함수를 실행
}