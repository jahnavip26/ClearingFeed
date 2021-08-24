'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');


function uploadSingleFile(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/csv/upload");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
       
		
        if(xhr.status == 200) {
            singleFileUploadSuccess.style.display = "none";
           
           singleFileUploadSuccess.innerHTML = "<p>"+(response.noOfValidTXN)+"</p><p>"+(response.noOfInvalidTXN)+"</p><br><p>"+(response.message)+"</p>";
         singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadError.style.display = "none";
            singleFileUploadError.innerHTML = "<p>"+(response && response.message)+"<p>" || "Some Error Occurred";
            singleFileUploadError.style.display = "block";

        }
    }

    xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event){
    document.querySelector('.submit-btn').classList.add('myClass');
    var files = singleFileUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0]);
    event.preventDefault();
}, true);

