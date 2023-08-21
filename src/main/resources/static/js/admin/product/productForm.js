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
                                <input type="text" class="form-control productDetailName">
                            </div>
                        </div>
                        <div class="row col-xl-7">
                            <label for="nameDescription" class="col-sm-2 col-form-label">속성값</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control productDetailValue">
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

async function createFormData() {
    let requestBody = {
        productImages:undefined,
        name:undefined,
        nameDescription:undefined,
        price:undefined,
        priceDescription:undefined,
        productOptions:undefined,
        productSpecials:undefined,
        productDetails:undefined,
        productDetailImages:undefined,
        sellerId:undefined,
    }

    //제품 이미지
    const productImageInputs = productImagesBox.querySelectorAll('.productImageInput');
    requestBody.productImages = await createUploadFiles(productImageInputs)

    //제품 정보
    const productName = document.querySelector('#productName');//제품명
    const productNameDescription = document.querySelector('#productNameDescription');//부재
    const productPrice = document.querySelector('#productPrice');//가격
    const productPriceDescription = document.querySelector('#productPriceDescription');//가격설명

    requestBody.name = productName.value;
    requestBody.nameDescription = productNameDescription.value;
    requestBody.price = productPrice.value;
    requestBody.priceDescription = productPriceDescription.value;

    //제품 옵션명
    const productOptionBoxes = productOptionMainBox.querySelectorAll('.productOptionBox');
    let productOptionObjects = []
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
        productOptionObjects.push(optionObject)
    })
    requestBody.productOptions = productOptionObjects;

    //특이사항
    const productSpecialTextAreas = document.querySelectorAll('.productSpecial');
    let productSpecialObjects = []
    productSpecialTextAreas.forEach(textArea => {
        const productSpecialObject = {
            value : textArea.value
        }
        productSpecialObjects.push(productSpecialObject)
    })
    requestBody.productSpecials = productSpecialObjects;

    //세부정보
    const productDetailBoxes = document.querySelectorAll('.productDetailBox');
    let productDetailObjects = []
    productDetailBoxes.forEach(productDetail => {
        const productDetailName = productDetail.querySelector('.productDetailName');
        const productDetailValue = productDetail.querySelector('.productDetailValue');
        const productDetailObject = {
            name : productDetailName.value,
            value : productDetailValue.value,
        }
        productDetailObjects.push(productDetailObject)
    })
    requestBody.productDetails = productDetailObjects;


    //제품 상세이미지
    const productImages = document.querySelectorAll('.productImage');
    requestBody.productDetailImages = await createUploadFiles(productImages)

    //판매자 정보
    const productSellerSelect = document.querySelector('#productSellerSelect');
    requestBody.sellerId = productSellerSelect.value

    console.log("requestBody =",requestBody);
    return requestBody;
}

async function submitProductBtn() {
    if (confirm("제품을 등록하시겠습니까?")) {
        try {
            const requestBody = await createFormData();
            console.log("requestbody =", requestBody);

            const response =
                await fetch(`/api/admins/products`, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestBody)
            });

            if (response.ok) {
                const data = await response.json();
                console.log(data);
                alert('서버와 통신에 성공했습니다.');
            } else {
                throw new Error('Network response was not ok');
            }
        } catch (error) {
            console.log('error : ', error);
        }
    }
}