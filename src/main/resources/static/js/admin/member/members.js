const memberStatusUpdatePopup = document.querySelector('#memberStatusUpdatePopup');
let updateMemberLoginId = document.querySelector('#updateMemberLoginId');
let updateMemberName = document.querySelector('#updateMemberName');
let updateMemberStatus = document.querySelector('#updateMemberStatus');


let selectedMemberId, selectedMemberLoginId, selectedMemberName;

function removeSelectedMember() {
    selectedMemberId = undefined;
    selectedMemberLoginId = undefined;
    selectedMemberName = undefined;

    updateMemberLoginId.value = "";
    updateMemberName.value = "";
}

function createSelectedMember() {
    const selectedMemberRadioBtn = document.querySelector('input[name="selectMember"]:checked');
    const memberInfo = selectedMemberRadioBtn.closest('.memberInfo');

    selectedMemberId = selectedMemberRadioBtn.getAttribute("data-id");
    selectedMemberLoginId = memberInfo.querySelector('.loginId');
    selectedMemberName = memberInfo.querySelector('.name');

    console.log("selectedMemberId=",selectedMemberId)
    console.log("selectedMemberLoginId=",selectedMemberLoginId)
    console.log("selectedMemberName=",selectedMemberName)

    updateMemberLoginId.value = selectedMemberLoginId.textContent;
    updateMemberName.value = selectedMemberName.textContent;
}

function onClickMemberStatusUpdate() {
    memberStatusUpdatePopup.classList.add('active');
    createSelectedMember();
}

function onclickMemberStatusUpdateClose() {
    memberStatusUpdatePopup.classList.remove('active');
    removeSelectedMember();
}

function onclickMemberStatusUpdateApi() {
    if(confirm("선택하신 회원의 상태를 수정하시겠습니까?")) {

    }
}

function selectFirstRadioButton() {
    const firstRadioButton = document.querySelector('input[name="selectMember"]');
    if (firstRadioButton) {
        firstRadioButton.checked = true;
    }
}

function validateRadioSelection() {
    const elementsByName = document.getElementsByName('selectMember');
}


// 초기 시작
selectFirstRadioButton();//첫번째 회원 radio 버튼 클릭