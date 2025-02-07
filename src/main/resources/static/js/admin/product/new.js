//--------------variable--------------
const productImagesBox = document.querySelector('#productImagesBox');//상품 이미지 박스
const productOptionMainBox = document.querySelector('#productOptionMainBox');//옵션 박스
const productSpecialsBox = document.querySelector('#productSpecialsBox');//특이사항 박스
const productDetailsBox = document.querySelector('#productDetailsBox');//세주정보 박스
const productDetailImagesBox = document.querySelector('#productDetailImagesBox');//상세이미지 박스

//---------------element---------------
const productImageInputElement = `<div class="mb-3 productImageBox">
                                <label class="form-label">이미지 파일</label>
                                <div style="display:flex;">
                                    <input class="form-control bg-dark productImageInput" type="file">
                                    <button onclick="removeProductImageBox(this)" type="button" class="btn btn-sm btn-sm-square btn-outline-primary m-2">
                                        <i class="fa fa-times "></i>
                                    </button>
                                </div>
                            </div>`//제품 이미지 요소
const optionInputElement = `<div class="productOptionBox">
                        <h6 class="mb-4">제품 옵션</h6>
                            <div class="row d-flex justify-content-between mb-3 productOptionNameBox">
                                <label for="name" class="col-sm-2 col-form-label">옵션명</label>
                                <div class="row col-sm-9">
                                    <input type="text" class="productOptionNameInput form-control">
                                </div>
                                <button onclick="removeProductOptionBox(this)" type="button" class="col-sm-1 btn btn-sm btn-sm-square btn-outline-primary m-2">
                                    <i class="fa fa-times "></i>
                                </button>
                            </div>
                        <div class="ms-5 productOptionValueMainBox">
                            <h6 class="mb-4">제품 옵션값</h6>
                            <div class="productOptionValuesBox">
                                <!--javascript로 productOptionValueBox 계속 추가해줌-->
                            </div>
                            <button onclick="createProductOptionValueBox(this)" class="btn btn-outline-primary w-100 m-2" type="button">제품 옵션값 추가</button>
                        </div>
                    </div>`//제품 옵션 요소
const optionValueInputElement = `<div class="row d-flex justify-content-between mb-3 productOptionValueBox">
                                    <label for="name" class="col-sm-2 col-form-label">옵션값</label>
                                    <div class="row col-sm-9">
                                        <input type="text" class="productOptionValueInput form-control">
                                    </div>
                                    <button onclick="removeProductOptionValueBox(this)" type="button" class="col-sm-1 btn btn-sm btn-sm-square btn-outline-primary m-2">
                                        <i class="fa fa-times"></i>
                                    </button>
                                </div>`; //제품 옵션 값 요소
const specialTextAreaElement = `<div class="productSpecialBox row d-flex justify-content-between align-items-center">
                        <div class="form-floating mb-3 col-sm-11">
                                <textarea class="form-control productSpecial" placeholder="Leave a comment here"
                                          style="height: 150px;"></textarea>
                            <label class="ms-3">Comments</label>
                        </div>
                        <button onclick="removeSpecialBox(this)" type="button" class="btn btn-sm btn-sm-square btn-outline-primary col-sm-1">
                            <i class="fa fa-times "></i>
                        </button>
                    </div>`;//제품 특이사항 요소
const productDetailInputElement = `<div class="productDetailBox row d-flex justify-content-between align-items-center mb-3 ">
                        <div class="row col-xl-4">
                            <label for="name" class="col-sm-3 col-form-label">속성명</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control">
                            </div>
                        </div>
                        <div class="row col-xl-7">
                            <label for="nameDescription" class="col-sm-2 col-form-label">속성값</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control">
                            </div>
                        </div>
                        <button onclick="removeProductDetailBox(this)" type="button" class="btn btn-sm btn-sm-square btn-outline-primary col-xl-1">
                            <i class="fa fa-times "></i>
                        </button>
                    </div>`;//제품 세부정보 요소
const productDetailImageInputElement = `<div class="mb-3 productDetailImageBox">
                                <label class="form-label">상세 이미지 파일</label>
                                <div style="display:flex;">
                                    <input class="form-control bg-dark productImage" type="file">
                                    <button onclick="removeProductDetailImageBox(this)" type="button" class="btn btn-sm btn-sm-square btn-outline-primary m-2">
                                        <i class="fa fa-times "></i>
                                    </button>
                                </div>
                            </div>`//제품 상세 이미지 요소

//---------------init------------------
createProductImageBox();
createProductOptionBox();
createSpecialBox();
createProductDetailImageBox();
createProductDetailBox();

// --------------methods---------------

function createProductImageBox() {
    productImagesBox.append(createTemplateElement(productImageInputElement));
}

function removeProductImageBox(obj) {
    let productImageBox = $(obj).closest('.productImageBox');
    productImageBox.remove();
}

function createProductOptionBox() {
    productOptionMainBox.append(createTemplateElement(optionInputElement));
}

function createProductOptionValueBox(obj) {
    console.log("제품 옵션 값 추가")
    let productOptionValueMainBox = $(obj).closest('.productOptionValueMainBox');
    let productOptionValuesBox = productOptionValueMainBox.find('.productOptionValuesBox');
    productOptionValuesBox.append(createTemplateElement(optionValueInputElement));
}

function removeProductOptionBox(obj) {
    console.log("옵션 제거 클릭!")
    let productOptionBox = $(obj).closest('.productOptionBox');
    productOptionBox.remove();
}

function removeProductOptionValueBox(obj) {
    let productOptionValueBox = $(obj).closest('.productOptionValueBox');
    productOptionValueBox.remove();
}

function createSpecialBox() {
    productSpecialsBox.append(createTemplateElement(specialTextAreaElement));
}

function removeSpecialBox(obj) {
    let productOptionBox = $(obj).closest('.productSpecialBox');
    productOptionBox.remove();
}

function createProductDetailImageBox() {
    productDetailImagesBox.append(createTemplateElement(productDetailImageInputElement));
}

function removeProductDetailImageBox(obj) {
    let productDetailImageBox = $(obj).closest('.productDetailImageBox');
    productDetailImageBox.remove();
}

function createProductDetailBox() {
    productDetailsBox.append(createTemplateElement(productDetailInputElement));
}

function removeProductDetailBox(obj) {
    let productDetailBox = $(obj).closest('.productDetailBox');
    productDetailBox.remove();
}

function createFormData() {
    const formData = new FormData();

    //제품 이미지
    const productImageInputs = productImagesBox.querySelectorAll('.productImageInput');
    productImageInputs.forEach(input => {
        const file = input.files[0];
        formData.append('productImages',file);
    })

    //제품 정보
    const productName = document.querySelector('#productName');//제품명
    const productNameDescription = document.querySelector('#productNameDescription');//부재
    const productPrice = document.querySelector('#productPrice');//가격
    const productPriceDescription = document.querySelector('#productPriceDescription');//가격설명
    formData.append('name',productName.value);
    formData.append('nameDescription',productNameDescription.value);
    formData.append('price',productPrice.value);
    formData.append('priceDescription',productPriceDescription.value);

    //제품 옵션명
    const productOptionBoxes = productOptionMainBox.querySelectorAll('.productOptionBox');
    productOptionBoxes.forEach(productOptionBox => {
        const productOptionNameInput = productOptionBox.querySelector('.productOptionNameInput');
        let optionObject = {
            optionName : productOptionNameInput.value,
            productOptionItems : []
        }

        //제품 옵션 값
        const productOptionValueInputs = productOptionBox.querySelectorAll('.productOptionValueInput');
        productOptionValueInputs.forEach(productOptionValueInput => {
            const optionValue = productOptionValueInput.value;
            optionObject.productOptionItems.push(optionValue);
        });
        formData.append('productOptions',JSON.stringify(optionObject))
    })

    //특이사항
    const productSpecialTextAreas = productImagesBox.querySelectorAll('.productSpecial');
    productSpecialTextAreas.forEach(textArea => {
        const productSpecialObject = {
            value : textArea.value
        }
        formData.append('productImages',JSON.stringify(productSpecialObject));
    })

    //세부정보
    const productDetailBoxes = productImagesBox.querySelectorAll('.productDetailBox');
    productSpecialTextAreas.forEach(textArea => {
        const productSpecialObject = {
            value : textArea.value
        }
        formData.append('productImages',JSON.stringify(productSpecialObject));
    })

    //제품 상세이미지
    const productDetailImageInputs = productImagesBox.querySelectorAll('.productDetailImageInput');
    productDetailImageInputs.forEach(input => {
        const file = input.files[0];
        formData.append('productDetailImages',file);
    })

    // FormData 내용 확인 (개발자 도구의 Network 탭에서 확인 가능)
    for (let pair of formData.entries()) {
        console.log(pair[0], pair[1]);
    }

    return formData;
}

function submitProductBtn() {
    if (confirm("제품을 등록하시겠습니까?")) {
        const formData = createFormData();
        const uri = `/api/admins/products`;

        fetch(uri,{
            method : 'POST',
            body: formData,
        }).then(response => {
            if(response.ok) {
                alert('서버와 통신에 성공했습니다.');
                return response.json();
            } else {
                throw new Error('Network response was not ok');
            }
        }).then(data => {
            console.log(data);
        }).catch(error => {
            console.log('error : ',error);
        })

    }
}