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
    console.log("updateMemberStatus = ",updateMemberStatus)

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

function onClickSellerCreate() {
    const selectedMemberRadioBtn = document.querySelector('input[name="selectMember"]:checked');
    const memberInfo = selectedMemberRadioBtn.closest('.memberInfo');
    const memberName = memberInfo.querySelector('.name').textContent;
    const memberId = selectedMemberRadioBtn.getAttribute("data-id");
    if(confirm(`${memberName}님을(를) 판매자로 등록하시겠습니까?(판매자 등록페이지로 이동)`)) {
        window.location.href = `/admins/sellers/new?memberId=${memberId}`;
    }
}

function onclickMemberStatusUpdateApi() {
    if(confirm("선택하신 회원의 상태를 수정하시겠습니까?")) {
        const url = `/api/admins/members/${selectedMemberId}`;
        const requestBody = {
            "status":updateMemberStatus.value
        }
        fetch(url,{
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            if(response.ok){
                return response.json()
            }
        }).then((data) =>{
            console.log("data :",data);
            alert("선택하신 회원의 상태를 변경하는데 성공하였습니다");
            window.location.reload();
        }).catch(error => {
            console.log("error :",error);
        })
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