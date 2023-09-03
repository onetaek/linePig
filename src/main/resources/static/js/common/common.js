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


