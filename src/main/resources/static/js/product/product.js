//사이드 결제 배너
let sidebarContent = document.querySelector('#product-name-option-button-wrap');
const productInfoTop = document.querySelector('#product-info-top');

const scrollBarWidth = window.innerWidth - document.documentElement.clientWidth;


const productInfoBottom = document.querySelector('#product-info-bottom');
const productInfoBottomWidth = productInfoBottom.offsetWidth

const rect = productInfoBottom.getBoundingClientRect();
const sourceRightOffset = window.innerWidth - rect.right - scrollBarWidth * 4;


const newWidth = productInfoBottomWidth * 0.25;
sidebarContent.style.width = `${newWidth}px`;
sidebarContent.style.right = sourceRightOffset + 'px';
// Intersection Observer 설정
const options = {
    root: null, // 기본적으로 viewport를 root로 사용합니다.
    rootMargin: "0px", // 기본값
    threshold: 0 // 0이면 전부 다 보이지 않을 때 알림
};

const observer = new IntersectionObserver((entries, observer) => {
    entries.forEach(entry => {
        if (!entry.isIntersecting) {
            sidebarContent.style.display = "block";
            sidebarContent.style.opacity = 1;
            sidebarContent.style.pointerEvents = "auto";
        } else {
            sidebarContent.style.display = "block";
            sidebarContent.style.opacity = 0;
            sidebarContent.style.pointerEvents = "none";
        }
    });
}, options);

// div를 관찰 대상으로 등록
observer.observe(productInfoTop);