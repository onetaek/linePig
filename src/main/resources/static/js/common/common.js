/**
 * appendChild에 사용할 수 있는 element template를 만들어주는 메서드
 * @param html
 * @returns {Element}
 */
function createTemplateElement(html){
    const template = document.createElement("template");
    template.innerHTML = html.trim();
    return template.content.firstElementChild;
}

/**
 * null값 처리를 해주는 메서드
 * @param data
 * @returns {*|string}
 * @private
 */
function _fnToNull(data) {
    // undifined나 null을 null string으로 변환하는 함수.
    if (String(data) == 'undefined' || String(data) == 'null') {
        return '';
    } else {
        return data;
    }
}

/**
 * 하나의 파일 객체를 생성해주는 메서드
 * @param file
 * @returns {Promise<unknown>}
 */
function createUploadFileObj(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();

        reader.onload = (event) => {
            const dataIndex = event.target.result.indexOf(',') + 1;
            const base64 = event.target.result.substring(dataIndex);
            resolve({
                fileBase64: base64,
                fileName: file.name,
                contentType: file.type,
                sequence: undefined,
            });
        };

        reader.onerror = (error) => {
            reject(error);
        };

        reader.readAsDataURL(file);
    });
}

/**
 * createUploadFileObj를 map을 이용해서 여러 파일로 만들어주는 메서드
 * @param fileInputs
 * @returns {Promise<*[]|Awaited<*>[]>}
 */
async function createUploadFiles(fileInputs) {
    try {
        const files = [];
        for (const fileInput of fileInputs) {
            if (fileInput.files && fileInput.files.length > 0) {
                files.push(fileInput.files[0]);
            }
        }
        return await Promise.all(files.map(file => createUploadFileObj(file)));
    } catch (error) {
        console.log('uploadFile을 생성하는동안 에러가 발생했습니다 : ', error);
        return [];
    }

}

async function createUploadFile(fileInput) {
    try {
        let file = null;
        if (fileInput.files && fileInput.files.length > 0) {
            file = fileInput.files[0];
        }
        return await createUploadFileObj(file);
    } catch (error) {
        console.log('uploadFile을 생성하는동안 에러가 발생했습니다 : ', error);
        return [];
    }
}

/**
 * localhost:8080/boards?category=NOTCIE
 * localhost:8080/products/2
 * 위와 같은 URL 이있을 때
 * /boards, /products 를 반환해주는 함수
 * @param uri
 * @returns {string}
 */
function extractPathFromUri(uri) {
    // "://" 이후의 문자열로 시작하여 경로 부분을 추출합니다.
    const pathStartIndex = uri.indexOf("://");

    if (pathStartIndex !== -1) {
        // "://" 이후의 문자열로부터 경로를 추출합니다.
        const pathWithoutProtocol = uri.substring(pathStartIndex + 3); // 3은 "://".length와 같습니다.
        console.log("pathWithoutProtocol = ",pathWithoutProtocol)

        // URI에서 '?' 문자 이후의 부분을 제거합니다.
        const pathWithoutQuery = pathWithoutProtocol.split('?')[0];
        console.log("pathWithoutQuery = ",pathWithoutQuery)

        // URI를 '/' 문자로 분할하여 경로 부분을 얻습니다.
        const pathSegments = pathWithoutQuery.split('/');
        console.log("pathSegments = ",pathSegments)

        // 첫 번째 경로 부분을 반환합니다 (주로 루트 경로).
        if (pathSegments.length > 0) {
            // 슬래시를 추가한 후 반환합니다.
            return '/' + pathSegments[1];
        }
    }

    // 경로가 없는 경우 루트 경로를 반환합니다.
    return '/';
}
