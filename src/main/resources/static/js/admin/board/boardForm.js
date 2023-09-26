function submitBoardButton() {

    const boardTitleKoInput = document.querySelector('.board-title-ko-input');
    const boardTitleEnInput = document.querySelector('.board-title-en-input');
    const boardWriterInput = document.querySelector('.board-writer-input');
    const boardCategorySelect = document.querySelector('.board-category-select');
    const boardStatusSelect = document.querySelector('.board-status-select');
    const boardIsTopSelect = document.querySelector('.board-is-top-select');
    const boardSequenceInput = document.querySelector('.board-sequence-input');

    console.log("editorKo = ", editorKo.getData())
    console.log("editorEn = ", editorEn.getData())
    const requestBody = {
        titleKo: boardTitleKoInput.value,
        titleEn: boardTitleEnInput.value,
        category: boardCategorySelect.value,
        status: boardStatusSelect.value,
        writer: boardWriterInput.value,
        isTop: boardIsTopSelect.value,
        sequence: boardSequenceInput.value,
        contentKo: editorKo.getData(),
        contentEn: editorEn.getData(),
    }

    console.log("requestBody = ",requestBody)

    if (confirm("게시글을 등록하시겠습니까?")) {
        fetch("/api/admins/boards", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            if (response.ok) {
                alert("게시글 등록에 성공하였습니다.")
                window.location.href="/admins/boards"
            }
        }).catch(error => {
            alert(`error : ${error}`);
        })
    }
}