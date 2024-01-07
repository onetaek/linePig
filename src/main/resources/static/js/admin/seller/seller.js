//--------------variable--------------
const sellerExtendsBox = document.querySelector('#sellerExtendsBox');//판매자 속성 박스


//---------------element---------------
const sellerExtendInputElement = `<div class="sellerExtendBox row col-12 d-flex justify-content-between mb-3">
                        <div class="row col-2">
                            <label class="col-4 col-form-label">순서</label>
                            <div class="col-8">
                                <input type="number" class="form-control sellerExtendSequenceInput"
                                       th:placeholder="순번">
                            </div>
                        </div>
                        <div class="row col-4">
                            <label class="col-2 col-form-label">이름</label>
                            <div class="col-10">
                                <input type="text" class="form-control sellerExtendNameInput"
                                       th:placeholder="|속성명을 입력하시오 ex)회사명|">
                            </div>
                        </div>
                        <div class="row col-6">
                            <label class="col-1 col-form-label">값</label>
                            <div class="col-11">
                                <input type="text" class="form-control sellerExtendValueInput"
                                       th:placeholder="|속성값을 입력하시오 ex)삼성전자|">
                            </div>
                        </div>
                        <button onclick="removeSellerExtendBox(this)" type="button" class="col-sm-1 btn btn-sm btn-sm-square btn-outline-primary">
                            <i class="fa fa-times "></i>
                        </button>
                    </div>`;


//---------------init------------------
createSellerExtendBox();


// --------------methods---------------
function createSellerExtendBox() {//판매자 속성 추가
    sellerExtendsBox.append(createTemplateElement(sellerExtendInputElement));
}

function removeSellerExtendBox(obj) {//판매자 속성 삭제
    let sellerExtendBox = $(obj).closest('.sellerExtendBox');
    sellerExtendBox.remove();
}

function submitSellerBtn() {//판매자 등록

    const memberSelect = document.querySelector('#memberSelect');
    const memberId = memberSelect.value;

    if(confirm("선택하신 회원을 판매자로 등록하시겠습니까?")) {
        let requestBody = {
            sellerExtends: [

            ]
        }

        const sellerExtendBoxes = document.querySelectorAll('.sellerExtendBox');

        sellerExtendBoxes.forEach(sellerExtendBox => {
            const sellerExtendSequenceInput = sellerExtendBox.querySelector('.sellerExtendSequenceInput');
            const sellerExtendNameInput = sellerExtendBox.querySelector('.sellerExtendNameInput');
            const sellerExtendValueInput = sellerExtendBox.querySelector('.sellerExtendValueInput');
            requestBody.sellerExtends.push({
                sequence: sellerExtendSequenceInput.value,
                name: sellerExtendNameInput.value,
                value: sellerExtendValueInput.value,
            })
        })
        const uri = `/api/admins/members/${memberId}/sellers`;

        console.log("uri =",uri);
        console.log("requestBody =",requestBody);

        fetch (uri,{
            method : 'POST',
            body : JSON.stringify(requestBody),
            headers : {
                "Content-type" : "application/json"
            }
        }).then(response =>{
            if (response.ok){
                alert('서버와 통신에 성공하였습니다.');
            } else{
                throw new Error('Network response was not ok')
            }
        }).then(data =>{
            console.log(data)
        }).catch(error => {
            console.log('error : ',error);
        })
    }
}