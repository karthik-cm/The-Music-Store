<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertAttribute name="ms_libs" />

<style>
	input.ms-password {padding:5px; width: 30%}
	img.cross-img {width: 2.5%; margin-left: 6px; cursor: pointer; border-radius: 50%;}
</style>

<div id="file-upload-content" style="padding-bottom: 30px;">
	<tiles:insertAttribute name="ms_header" />
	
	<div class="ms-content-div ms-file-upload-content-div" style="padding: 50px;">
		<h3 style="text-decoration: underline; margin-top: 50px;"><b> File Upload :- </b></h3>
		
		<!-- File Upload Navigation -->
		<ul class="nav nav-tabs file-upload-nav-tabs" style="padding-top: 25px; width: 80%;">
			<li class="active">
				<a href="#" class="fileuploadhome bold"> Home </a>
			</li>
			<li>
				<a href="#" class="uploadfile bold"> Upload File </a>
			</li>
 			<li>
				<a href="#" class="viewfile bold"> View File </a>
			</li>
		</ul>
		
		
		<div class="main-file-upload-home-div" style="padding-bottom: 50px;">
		
			<!-- File Upload Home -->
			<div class="file-upload-home-div" style="padding-top: 20px; padding-bottom: 50px;">
				<p class="margin-bottom-15"> This is File Upload section. It has mainly 3 sections : </p>
				<ol>
					<li class="margin-bottom-10"> Upload File : Here you can add upload File details.
					<li class="margin-bottom-10"> View File : Here you can view/download File details.
				</ol>
			</div>
			
			
			<!-- Upload File -->
			<div id="upload-file-div" class="upload-file-div margin-20 hide" style="width: 75%;">
				<form id="file-upload-form" name="file-upload-form" class="file-upload-form ms-form" enctype="multipart/form-data">
					<div class="error-msg hide" style="margin-bottom: 20px;"></div>
					
					<table id="upload-file-table" class="table table-bordered ms-table white-bg margin-bottom-5">
						<thead>
							<tr>
								<th>Section</th>
								<th>Action</th>
								<th>Additional Info</th>
								<th>Clear</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<tr id="row_1">
								<td>1</td>
								<td>
									<a id="browseFile_1" class="browseFile_1 bold underline" onclick="browseFile($(this))"> Browse File </a>
									<input type="file" id="files_1" class="files_1 hide" name="files" onchange="selectFile($(this))">
									<label for="fileNames" id="fileNames_1" class="fileNames_1 block"></label>
								</td>
								<td>
									<textarea placeholder="Enter Comments" id="comments_1" name="comments_1" style="padding: 5px; height: 60px; resize: none;"></textarea>
								</td>
								<td>
									<a class="clear-file underline bold" onclick="clearFile($(this))"> Clear </a>
								</td>
								<td>N.A</td>
							</tr>
						</tbody>
					</table>
					
					<div class="margin-bottom-20">
						<a class="bold underline" onclick="addMoreUploadDiv()"> Add More </a>
					</div>
					
					<div class="margin-bottom-10">
						<button type="button" class="btn btn-success" onclick="uploadFiles()" style="width: 15%;"> Upload </button>
						<!-- <button type="button" class="btn btn-danger clear" onclick="clearFiles()" style="width: 15%; margin-left: 10px;"> Clear </button> -->
					</div>
					
					
					<div style="margin-top: 35px;">
						<span class="bold block"> Note: </span>
						<span class="bold block"> 1. Please upload files of type PNG/JPEG/PDF only </span>
						<span class="bold block"> 2. Max file size allowed is 10 MB </span>
					</div>
				</form>
			</div>
			
			
			<!-- View File -->
			<div id="view-file-div" class="view-file-div margin-top-20 hide">
				<h5 class="bold margin-bottom-25 underline"> View file details with Download and Delete option :- </h5>
				
				<form id="view-file-form" name="view-file-form" class="view-file-form ms-form">
					<div class="error-msg hide" style="margin-bottom: 20px;"></div>
					
					<table id="view-file-table" class="table table-bordered ms-table white-bg margin-bottom-5">
						<thead>
							<tr>
								<th>Sl No</th>
								<th>File Id</th>
								<th>File Name</th>
								<th>File Type</th>
								<th>File Size (MB)</th>
								<th>Comments</th>
								<th>Created Date</th>
								<th>Download</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</form>
			</div>
			
			<div class="ms-loader align-center hide" style="margin-top: 20px;">
				<img alt="Loading..." src="ui-resources/gifs/loading.gif">
			</div>
		</div>
	</div>
	
 	<tiles:insertAttribute name="ms_contents" />
 	
	<tiles:insertAttribute name="ms_footer" />

</div>


<script type="text/javascript">
var filesArr = [];

$(document).ready(function(){
	$('ul.file-upload-nav-tabs').find('li > a').on('click', function(){
		$(this).parents('li').addClass('active');
		$(this).parents('li').siblings().find('a').css({'color':'black'});
		$(this).css({'color':'#337ab7'});
		
		if($(this).hasClass('fileuploadhome')){
			$('div.file-upload-home-div').removeClass('hide');
			$('div.upload-file-div, div.view-album-div, div.update-album-div').addClass('hide');
		}
		else if($(this).hasClass('uploadfile')){
			$('div.upload-file-div').removeClass('hide');
			$('div.file-upload-home-div, div.view-file-div').addClass('hide');
			fileSectionOnload();
		}
		else if($(this).hasClass('viewfile')){
			$('div.view-file-div').removeClass('hide');
			$('div.file-upload-home-div, div.upload-file-div').addClass('hide');
			viewFileDetails();
		}
		$(this).parents('li').siblings().removeClass('active');
	});
});


function browseFile(obj){
	var index = $(obj).attr('id').split('_')[1];
	$('input#files_'+index+'').trigger('click');
}


function selectFile(obj){
	// Selected files are stored in FileList
	$('form#file-upload-form').find('.error-msg').addClass('hide').text('');
	var index = $(obj).attr('id').split('_')[1];
	
	// var fileList = document.getElementById('files_'+index).files;
	var fileList = $('input#files_'+index)[0].files;
	
	if(fileList != null && fileList != undefined && fileList.length > 0){
		var fileNames = '';
		for(var i=0; i<fileList.length; i++){
			var isFileValid = validateFileAndShowError(fileList[i]);
			if(isFileValid){
				var fileName = fileList[i].name;
				fileNames += '<div class="margin-top-5 file_'+index+'" index="'+index+'">'+fileName+'</div>';
				// fileNames += '<div class="margin-5 file_'+i+'" index="'+i+'">'+fileName+'<img class="cross-img cancel-file_'+i+'" src="ui-resources/images/cancel-button-6.jpg" alt="Cancel" onclick="cancelFile($(this))" /></div>';
			}
			else{
				$(obj).parents('tr[id^="row_"]').find('.clear-file').trigger('click');
			}
		}
		$('label#fileNames_'+index+'').html(fileNames);
	}
}


function fileSectionOnload(){
	$('tr[id^="row_"]').find('input[id^="files_"]').val('');
	$('tr[id^="row_"]').find('label[id^="fileNames_"]').children().remove();
	$('textarea[id^="comments_"]').val('');
}


function clearFile(obj){
	var index = $(obj).parents('tr[id^="row_"]').attr('id').split('_')[1];
	$('tr#row_'+index).find('input#files_'+index+'').val('');
	$('tr#row_'+index).find('label[id^="fileNames_'+index+'"]').children().remove();
	$('textarea#comments_'+index).val('');
	
}


function deleteSection(obj){
	var index = $(obj).parents('tr[id^="row_"]').attr('id').split('_')[1];
	$(obj).parents('tr#row_'+index).nextAll('tr[id^="row_"]').remove();
	$(obj).parents('tr#row_'+index).remove();
}


function addMoreUploadDiv(){
	var tableObj = $('table#upload-file-table');
	var errorMsg = '';
	$('form#file-upload-form').find('.error-msg').addClass('hide').text('');
	
	var tbodyTrLength = $(tableObj).find('tbody > tr').length;
	if(tbodyTrLength >= 1 && tbodyTrLength <= 4){
		var prevFileLength = $(tableObj).find('tbody > tr[id^="row_'+tbodyTrLength+'"]').find('input[id^="files_"]')[0].files.length;
		
		if(prevFileLength != undefined && prevFileLength == 1){
			var index = ++tbodyTrLength;
			var trToAppend = '<tr id="row_'+index+'">'
								+'<td>'+index+'</td>'
								+'<td>'
									+'<a id="browseFile_'+index+'" class="browseFile_'+index+' underline bold" onclick="browseFile($(this))"> Browse File </a>'
									+'<input type="file" id="files_'+index+'" class="files_'+index+' hide" name="files" onchange="selectFile($(this))">'
									+'<label for="fileNames" id="fileNames_'+index+'" class="fileNames_'+index+' block"></label>'
								+'</td>'
								+'<td>'
									+'<textarea placeholder="Enter Comments" id="comments_'+index+'" name="comments_'+index+'" style="padding: 5px; height: 60px; resize: none;"></textarea>'
								+'</td>'
								+'<td>'
									+'<a id="clearFile_'+index+'" class="clearFile_'+index+' underline bold" onclick="clearFile($(this))"> Clear </a>'
								+'</td>'
								+'<td>'
									+'<a id="deleteSection_'+index+'" class="deleteSection_'+index+' underline bold" onclick="deleteSection($(this))"> Delete </a>'
								+'</td>'
							+'</tr>';
			$(tableObj).find('tbody').append(trToAppend);
		}
		else if(prevFileLength >= 2){
			errorMsg = 'Only one file can be uploaded per section';
		}
		else if(prevFileLength == 0){
			errorMsg = 'Section '+tbodyTrLength +' cannot be incomplete. Please upload.';
		}
	}
	else if(tbodyTrLength >= 5){
		errorMsg = 'Max 5 sections per upload';
	}
	
	if(errorMsg != null && errorMsg.length > 0){
		$('form#file-upload-form').find('.error-msg').removeClass('hide').text(errorMsg);
	}
}


function uploadFiles(){
	// var formObj = document.getElementById('file-upload-form'); // javascript Object
	var formObj = $('form#file-upload-form')[0];
	var formData = new FormData(formObj); // FormData
	
	$('form#file-upload-form').find('.error-msg').addClass('hide').text('');
	
	if(formData != null && formData != undefined){
		var filesLength = $('input[id^="files_"]')[0].files.length;
		if(filesLength > 0){
			// tuadsec
			$.ajax({
				type : 'POST',
				url : 'uploadFile',
				data : formData, 
				enctype : 'multipart/form-data',
				contentType : false,
				processData : false,
				success : function(response, wrapper){
					var status = response;
					if(status != null && status != undefined){
						showPopUp('upload_file', status);
					}
				},
				error : function(response, wrapper){
					showPopUp('upload_file', 'N');
				}
			});
		}
		else{
			$(formObj).find('.error-msg').removeClass('hide').text('Select atleast one file to Upload');
		}
	}
	else{
		$(formObj).find('.error-msg').removeClass('hide').text('Select atleast one file to Upload');
	}
}


function viewFileDetails(){
	$.ajax({
		type : 'GET',
		url : 'viewFile',
		success : function(response, wrapper){
			var viewFileDetailsJson = $.parseJSON(response);
			var viewFileTable = $('table#view-file-table');
			var trTdObj = '';
			
			if(viewFileDetailsJson != null && viewFileDetailsJson.length > 0){
				$.each(viewFileDetailsJson, function(index, obj){
					trTdObj += '<tr class="row_'+parseInt(index+1)+'" fileId="'+obj.file_id+'">';
					trTdObj += '<td>' +parseInt(index+1) +'</td>';
					trTdObj += '<td>' +obj.file_id +'</td>';
					trTdObj += '<td id="fileName_'+index+'" value='+obj.file_name+'>' +obj.file_name +'</td>';
					trTdObj += '<td id="fileType_'+index+'" value='+obj.file_type+'>' +obj.file_type +'</td>';
					trTdObj += '<td id="fileSize_'+index+'" value='+obj.file_size+'>' +(obj.file_size/(1024*1024)).toFixed(2) +'</td>';
					trTdObj += '<td id="comments_'+index+'" >' +obj.comments +'</td>';
					trTdObj += '<td id="createdDate_'+index+'">' +obj.created_date +'</td>';
					trTdObj += '<td><a id="dowloadFile_'+index+'" onclick="downloadFile($(this))">Download</td>';
					trTdObj += '<td><a id="deleteFile_'+index+'" onclick="deleteFile($(this))">Delete</td>';
					trTdObj += '</tr>';
				});
			}
			else{
				trTdObj = '<tr> <td class="align-center bold" colspan="9"> No records found </tr>';
			}
			$(viewFileTable).find('tbody').html(trTdObj);
			applyPagination($(viewFileTable).attr('id'));
		},
		error : function(response, wrapper){
			var trTdObj = '<tr> <td class="align-center bold" colspan="9"> No records found </tr>';
			$('table#view-file-table').find('tbody').html(trTdObj);
		}
	});
}


function downloadFile(obj){
	var fileid = $(obj).parents('tr').attr('fileid');
	
	if(fileid != null && fileid != undefined){
		var form = document.createElement('form');
		form.method='POST';
		form.action='downloadFile';
		
		var inputField = document.createElement('input');
		inputField.type='hidden';
		inputField.name='fileid';
		inputField.value=fileid;
		
		form.appendChild(inputField);
		
		document.body.appendChild(form);
		// $('form#download-file-form').submit();
		
		form.submit();
	}
}


function deleteFile(obj){
	var fileid = $(obj).parents('tr').attr('fileid');
	
	if(fileid != null && fileid != undefined){
		$.ajax({
			type : 'POST',
			url : 'deleteFile',
			data : {
				'fileid' : fileid
			},
			success : function(response, wrapper){
				var status = response;
				if(status != null && status != undefined){
					showPopUp('delete_file', status);
				}
			},
			error : function(response, wrapper){
				showPopUp('delete_file', 'N');
			}
		});
	}
	
}


function clearFiles(){
	// document.getElementById('files').value = '';
	$('input[id^="files"]').val('');
	$('div.upload-file-div').find('label[id^="fileNames"]').html('');
}

</script>