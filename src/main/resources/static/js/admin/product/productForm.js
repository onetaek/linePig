//--------------variable--------------
const productImagesBox = document.querySelector('#product-images-box');//상품 이미지 박스
const productOptionsBox = document.querySelector('#product-options-box');//옵션 박스
const productSpecialsBox = document.querySelector('#product-specials-box');//특이사항 박스
const productDetailsBox = document.querySelector('#product-details-box');//세주정보 박스
const productDetailImagesBox = document.querySelector('#product-detail-images-box');//상세이미지 박스

//---------------element---------------
const productImageInputElement = `<div class="mb-3 product-image-box">
                                <label class="form-label">이미지 파일</label>
                                <div style="display:flex;">
                                    <input class="form-control product-image-sequence" style="width:70px;margin-right:10px;" type="number"/>
                                    <input class="form-control bg-dark product-sequence product-image-input" type="file">
                                    <button onclick="removeProductImageBox(this)" type="button" class="btn btn-sm btn-sm-square btn-outline-primary m-2">
                                        <i class="fa fa-times "></i>
                                    </button>
                                </div>
                            </div>`//제품 이미지 요소
const optionInputElement = `<div class="product-option-box mb-5 row d-flex justify-content-between align-items-center ">
                            <div class="col-xl-11">
                                <div class="row col-xl-12 mb-3">
                                    <div class="row col-xl-6">
                                        <label class="col-sm-3 col-form-label">옵션값(Ko)</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control product-value-ko">
                                        </div>
                                    </div>
                                    <div class="row col-xl-6">
                                        <label class="col-sm-3 col-form-label">옵션값(En)</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control product-value-en">
                                        </div>
                                    </div>
                                </div>
                                <div class="row col-xl-12 mb-3">
                                    <div class="row col-xl-6">
                                        <label class="col-sm-3 col-form-label">가격(Ko)</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control product-price-ko">
                                        </div>
                                    </div>
                                    <div class="row col-xl-6">
                                        <label class="col-sm-3 col-form-label">가격(En)</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control product-price-en">
                                        </div>
                                    </div>
                                </div>
                                <div class="row col-xl-12 mb-3">
                                    <div class="row col-xl-6">
                                        <label class="col-sm-3 col-form-label">가격설명(ko)</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control product-price-description-ko">
                                        </div>
                                    </div>
                                    <div class="row col-xl-6">
                                        <label class="col-sm-3 col-form-label">가격설명(En)</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control product-price-description-en">
                                        </div>
                                    </div>
                                </div>
                                <div class="row col-xl-12 mb-3">
                                    <div class="row col-xl-6">
                                        <label class="col-sm-3 col-form-label">재고량</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control product-stock-quantity">
                                        </div>
                                    </div>
                                    <div class="row col-xl-6">
                                        <label class="col-sm-3 col-form-label">순서</label>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control product-sequence">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button onclick="removeProductOptionBox(this)" type="button" class="col-sm-1 btn btn-sm btn-sm-square btn-outline-primary m-2">
                                <i class="fa fa-times"></i>
                            </button>
                        </div>`; //제품 옵션 값 요소
const specialTextAreaElement = `<div class="product-special-box mb-5 row d-flex justify-content-between align-items-center">
                        <div class="row col-xl-1">
                            <input type="number" class="form-control product-sequence">
                        </div>
                        <div class="col-xl-10 row d-flex justify-content-between align-items-center">
                            <div class="form-floating col-xl-6 mb-3">
                                    <textarea class="form-control product-special-ko" placeholder="Leave a comment here"
                                              style="height: 150px;"></textarea>
                                <label class="ms-3">특이사항(Ko)</label>
                            </div>
                            <div class="form-floating col-xl-6 mb-3">
                                    <textarea class="form-control product-special-en" placeholder="Leave a comment here"
                                              style="height: 150px;"></textarea>
                                <label class="ms-3">특이사항(En)</label>
                            </div>
                        </div>
                        <button onclick="removeSpecialBox(this)" type="button" class="btn btn-sm btn-sm-square btn-outline-primary col-xl-1">
                            <i class="fa fa-times "></i>
                        </button>
                    </div>`;//제품 특이사항 요소
const productDetailInputElement = `<div class="product-detail-box mb-5 row d-flex justify-content-between align-items-center mb-3 ">
                        <div class="row col-xl-1 mb-3">
                            <input type="number" class="form-control product-sequence">
                        </div>
                        <div class="row col-xl-10 mb-3">
                            <div class="row col-xl-12 mb-3">
                                <div class="row col-xl-5">
                                    <label class="col-sm-4 col-form-label">속성명(Ko)</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control product-detail-name-ko">
                                    </div>
                                </div>
                                <div class="row col-xl-7">
                                    <label class="col-sm-3 col-form-label">속성값(Ko)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control product-detail-value-ko">
                                    </div>
                                </div>
                            </div>
                            <div class="row col-xl-12 mb-3">
                                <div class="row col-xl-5">
                                    <label class="col-sm-4 col-form-label">속성명(En)</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control product-detail-name-en">
                                    </div>
                                </div>
                                <div class="row col-xl-7">
                                    <label class="col-sm-3 col-form-label">속성값(En)</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control product-detail-value-en">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button onclick="removeProductDetailBox(this)" type="button" class="btn btn-sm btn-sm-square btn-outline-primary col-xl-1 mb-3">
                            <i class="fa fa-times "></i>
                        </button>
                    </div>`;//제품 세부정보 요소
const productDetailImageInputElement = `<div class="mb-3 product-detail-image-box">
                                <label class="form-label">상세 이미지 파일</label>
                                <div style="display:flex;">
                                    <input class="form-control product-detail-image-sequence" style="width:70px;margin-right:10px;" type="number"/>
                                    <input class="form-control bg-dark product-detail-image" type="file">
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
    let productImageBox = $(obj).closest('.product-image-box');
    productImageBox.remove();
}

function createProductOptionBox() {
    productOptionsBox.append(createTemplateElement(optionInputElement));
}

function removeProductOptionBox(obj) {
    let productOptionBox = $(obj).closest('.product-option-box');
    productOptionBox.remove();
}

function createSpecialBox() {
    productSpecialsBox.append(createTemplateElement(specialTextAreaElement));
}

function removeSpecialBox(obj) {
    let productOptionBox = $(obj).closest('.product-special-box');
    productOptionBox.remove();
}

function createProductDetailImageBox() {
    productDetailImagesBox.append(createTemplateElement(productDetailImageInputElement));
}

function removeProductDetailImageBox(obj) {
    let productDetailImageBox = $(obj).closest('.product-detail-image-box');
    productDetailImageBox.remove();
}

function createProductDetailBox() {
    productDetailsBox.append(createTemplateElement(productDetailInputElement));
}

function removeProductDetailBox(obj) {
    let productDetailBox = $(obj).closest('.product-detail-box');
    productDetailBox.remove();
}

async function createFormData() {
    let requestBody = {
    }

    //판매자 정보
    const productSellerSelect = document.querySelector('#productSellerSelect');
    requestBody.sellerId = productSellerSelect.value

    //제품 정보
    const productNameKo = document.querySelector('#product-name-ko');//제품명
    const productNameEn = document.querySelector('#product-name-en');//제품명
    const productNameDescriptionKo = document.querySelector('#product-name-description-ko');//제품명
    const productNameDescriptionEn = document.querySelector('#product-name-description-en');//제품명
    const productMainSequence = document.querySelector('#product-main-sequence');
    requestBody.nameKo = productNameKo.value;
    requestBody.nameEn = productNameEn.value;
    requestBody.nameDescriptionKo = productNameDescriptionKo.value;
    requestBody.nameDescriptionEn = productNameDescriptionEn.value;
    requestBody.sequence = productMainSequence.value;

    //제품 옵션명
    const productOptionKo = document.querySelector('.product-option-ko');
    const productOptionEn = document.querySelector('.product-option-en');
    requestBody.optionKo = productOptionKo.value;
    requestBody.optionEn = productOptionEn.value;

    //제품 옵션별 정보
    const productOptionBoxes = productOptionsBox.querySelectorAll('.product-option-box');
    let productOptionObjects = []
    productOptionBoxes.forEach(productOptionBox => {
        const productValueKo = productOptionBox.querySelector('.product-value-ko');
        const productValueEn = productOptionBox.querySelector('.product-value-en');
        const productPriceKo = productOptionBox.querySelector('.product-price-ko');
        const productPriceEn = productOptionBox.querySelector('.product-price-en');
        const productPriceDescriptionKo = productOptionBox.querySelector('.product-price-description-ko');
        const productPriceDescriptionEn = productOptionBox.querySelector('.product-price-description-en');
        const productStockQuantity = productOptionBox.querySelector('.product-stock-quantity');
        const productSequence = productOptionBox.querySelector('.product-sequence');

        let productOptionObject = {
            valueKo: productValueKo.value,
            valueEn: productValueEn.value,
            priceKo: productPriceKo.value,
            priceEn: productPriceEn.value,
            priceDescriptionKo: productPriceDescriptionKo.value,
            priceDescriptionEn: productPriceDescriptionEn.value,
            stockQuantity: productStockQuantity.value,
            sequence: productSequence.value,
        }
        productOptionObjects.push(productOptionObject)
    })
    requestBody.productOptions = productOptionObjects;

    //특이사항
    const productSpecialBoxes = document.querySelectorAll('.product-special-box');
    let productSpecialObjects = []
    productSpecialBoxes.forEach(productSpecialBox => {
        const productSpecialKo = productSpecialBox.querySelector('.product-special-ko');
        const productSpecialEn = productSpecialBox.querySelector('.product-special-en');
        const productSequence = productSpecialBox.querySelector('.product-sequence');

        const productSpecialObject = {
            valueKo: productSpecialKo.value,
            valueEn: productSpecialEn.value,
            sequence: productSequence.value,
        }
        productSpecialObjects.push(productSpecialObject)
    })
    requestBody.productSpecials = productSpecialObjects;

    //세부정보
    const productDetailBoxes = document.querySelectorAll('.product-detail-box');
    let productDetailObjects = []
    productDetailBoxes.forEach(productDetail => {
        const productDetailNameKo = productDetail.querySelector('.product-detail-name-ko');
        const productDetailNameEn = productDetail.querySelector('.product-detail-name-en');
        const productDetailValueKo = productDetail.querySelector('.product-detail-value-ko');
        const productDetailValueEn = productDetail.querySelector('.product-detail-value-en');
        const productSequence = productDetail.querySelector('.product-sequence');
        const productDetailObject = {
            nameKo: productDetailNameKo.value,
            nameEn: productDetailNameEn.value,
            valueKo: productDetailValueKo.value,
            valueEn: productDetailValueEn.value,
            sequence: productSequence.value,
        }
        productDetailObjects.push(productDetailObject)
    })
    requestBody.productDetails = productDetailObjects;


    //제품 이미지
    const productImageInputs = productImagesBox.querySelectorAll('.product-image-input');
    requestBody.productImages = await createUploadFiles(productImageInputs)

    console.log("requestBody.productImages =",requestBody.productImages)

    const productImageSequences = await document.querySelectorAll('.product-image-sequence');
    console.log("productImageSequences =",productImageSequences)
    await productImageSequences.forEach((productImageSequence,index) => {
        console.log("index =",index)
        console.log("productImageSequence =",productImageSequence)
        console.log("requestBody.productImages[index] =",requestBody.productImages[index])
        console.log("productImageSequence.value =",productImageSequence.value)
        requestBody.productImages[index].sequence = productImageSequence.value;
    })


    //제품 상세이미지
    const productDetailImageInputs = productDetailImagesBox.querySelectorAll('.product-detail-image');
    requestBody.productDetailImages = await createUploadFiles(productDetailImageInputs)
    const productDetailImageSequences = await document.querySelectorAll('.product-detail-image-sequence');
    await productDetailImageSequences.forEach((productDetailImageSequence,index) => {
        requestBody.productDetailImages[index].sequence = productDetailImageSequence.value;
    })

    console.log("requestBody =", requestBody);
    return requestBody;
}



async function submitProductBtn() {
    if (confirm("제품을 등록하시겠습니까?")) {
        const requestBody = await createFormData();
        try {
            const requestBody = await createFormData();

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