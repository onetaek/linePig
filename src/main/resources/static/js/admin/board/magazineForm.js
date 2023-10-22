async function submitMagazineButton() {

    const boardTitleKoInput = document.querySelector('.board-title-ko-input');
    const boardTitleEnInput = document.querySelector('.board-title-en-input');
    const boardSubTitleKoInput = document.querySelector('.board-sub-title-ko-input');
    const boardSubTitleEnInput = document.querySelector('.board-sub-title-en-input');
    const boardWriterInput = document.querySelector('.board-writer-input');
    const boardStatusSelect = document.querySelector('.board-status-select');
    const boardSequenceInput = document.querySelector('.board-sequence-input');
    const boardImageInput = document.querySelector('.board-image-input');

    const requestBody = {
        titleKo: boardTitleKoInput.value,
        titleEn: boardTitleEnInput.value,
        subTitleKo: boardSubTitleKoInput.value,
        subTitleEn: boardSubTitleEnInput.value,
        status: boardStatusSelect.value,
        boardImage: await createUploadFile(boardImageInput),
        writer: boardWriterInput.value,
        sequence: boardSequenceInput.value,
        contentKo: editorKo.getData(),
        contentEn: editorEn.getData(),
    }

    console.log("requestBody = ", requestBody)

    if (confirm("메거진을 등록하시겠습니까?")) {
        fetch("/api/admins/boards/magazine", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            if (response.ok) {
                alert("메거진 등록에 성공하였습니다.")
                window.location.href = "/admins/boards?category=MAGAZINE"
            }
        }).catch(error => {
            alert(`error : ${error}`);
        })
    }
}