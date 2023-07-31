function createTemplateElement(html){
    const template = document.createElement("template");
    template.innerHTML = html.trim();
    return template.content.firstElementChild;
}

function _fnToNull(data) {
    // undifined나 null을 null string으로 변환하는 함수.
    if (String(data) == 'undefined' || String(data) == 'null') {
        return '';
    } else {
        return data;
    }
}

function createUploadFileObj(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();

        reader.onload = (event) => {
            const dataIndex = event.target.result.indexOf(',') + 1;
            const base64 = event.target.result.substring(dataIndex);
            resolve({
                fileBase64: base64,
                fileName: file.name,
                contentType: file.type
            });
        };

        reader.onerror = (error) => {
            reject(error);
        };

        reader.readAsDataURL(file);
    });
}

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


