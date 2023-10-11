function onClickBoardUpdateButton() {

}

function onClickBoardDeleteButton() {
    const selectedRadio = document.querySelector('input[name="select"]:checked');
    if (!selectedRadio) {
        alert("게시글을 선택해주세요.")
        return;
    }
    const id = selectedRadio.getAttribute('data-id');
    if (confirm("게시글을 삭제하시겠습니까?")) {
        fetch(`/api/admins/boards/${id}`,{
            method:"DELETE",
            headers: {
                "Content-Type":"application/json"
            },
        }).then((response) =>{
            console.log("response = ",response)
            if (response.ok) {
                alert("선택하신 게시글을 삭제하였습니다")
                window.location.reload()
            } else {
                return response.json()
            }
        }).catch(error => {
            alert(`error : ${error}`);
        })
    }
}