/* JS file for Music Store Common functions 
 * @Author Karthik CM */


// typeof x == 'undefined'
// x == undefined


/** REGEX */
var ALPHA_REGEX = new RegExp(/^[a-zA-Z ]*$/);
var NUMBER_REGEX = new RegExp(/^[0-9]*$/);
var ALPHA_NUMERIC_REGEX = new RegExp(/^[a-zA-Z0-9 ]*$/);
var ALPHA_NUMERIC_REGEX_2 = new RegExp(/^[a-zA-Z0-9_]*$/);
var ALPHA_NUMERIC_REGEX_3 = new RegExp(/^[a-zA-Z0-9_ ]*$/);
var ALPHA_NUMERIC_REGEX_4 = new RegExp(/^[a-zA-Z0-9.-_ ]*$/); 
var EMAIL_ID_REGEX = new RegExp(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/);
var CONTACT_NO_REGEX = new RegExp(/^(7|8|9)[0-9]{9,9}$/); // new RegExp(/^(6|7|8|9){1}[0-9]{9}$/) Indian Mobile no
var FILE_TYPE_REGEX = new RegExp(/(\jpg|\jpeg|\png|\pdf)$/);


/** KEY CONSTANTS / OBJECTS */
var GL_USERNAME_LENGTH = 4;



var MS_FORM_VALDTN_OBJ = {
	'AL' : [ALPHA_REGEX, 'Should contain Alphabets only.'],
	'NI' : [NUMBER_REGEX, 'Should contain Numbers only.'],
	'AN' : [ALPHA_NUMERIC_REGEX, 'Should contain Alphabets or Numbers only'],
	'AN2' : [ALPHA_NUMERIC_REGEX_2, 'Should contain Alphabets, Numbers or Underscore only'],
	'AN3' : [ALPHA_NUMERIC_REGEX_3, 'Should contain Alphabets, Numbers, Underscores or Spaces only'],
	'EM' : [EMAIL_ID_REGEX, 'EmailID format is wrong. Eg. someone@gmail.com'],
	'CO' : [CONTACT_NO_REGEX, 'Contact number format is wrong. Eg. 9999999999']
};


var MS_FUNC_MSG = {
	'update_profile' : ['The profile details are updated successfully', 'The profile details could not be updated. Please try again after sometime.'],	
	'change_password' : ['The password has been updated successfully', 'The password could not be updated. Please try again after sometime.'],
		
	'add_album' : ['The new Album is created successfully', 'The new Album create failed. Please try again after sometime.'],
	'update_album' : ['The selected Album is updated successfully', 'The selected Album could not be updated. Please try again after sometime.'],
	'delete_album' : ['The selected Album is removed successfully', 'The selected Album could not be removed. Please try again after sometime.'],
	
	'add_track' : ['The new Track is added successfully for Album', 'The new Track could not be added. Please try again after sometime.'],
	'update_track' : ['The selected Track is updated successfully', 'The selected Track could not be updated. Please try again after sometime.'],
	'delete_track' : ['The selected Track is removed successfully', 'The selected Track could not be removed. Please try again after sometime.'],

	'upload_file' : ['The selected File is uploaded successfully', 'The selected File could not be uploaded. Please try again after sometime.'],
	'delete_file' : ['The selected File is deleted successfully', 'The selected File could not be deleted. Please try again after sometime.']
};


$(document).ready(function(){
	// Show / Hide Password
	$('span.glyphicon-eye-close, span.glyphicon-eye-open').on('click', function(){
		if($(this).hasClass('glyphicon-eye-close')){
			$(this).prev('input.ms-password').attr('type','text');
		}else{
			$(this).prev('input.ms-password').attr('type','password');
		}
		$(this).toggleClass('glyphicon-eye-close glyphicon-eye-open');
	});
});


// Toggle logout modal
function toggleLogoutPopup(action){
	if(action == 'show'){
		$('div#logout-popup').css({'display':'block'});
	}else{
		$('div#logout-popup').css({'display':'none'});
	}
}


// Scroll web page to top 
function scrollModalToTop(obj){
	$(obj).animate({
		scrollTop: 0 
	});
}


// Scroll given element to view
function scrollToDiv(element){
	element.scrollIntoView({behavior : 'smooth'});
}


// Check given object valid or not
function isEmptyObj(obj){
	if(obj != null && obj != '' && obj != undefined){
		return false;
	}else{
		return true;
	}
}


// Validate form and show error 
function validateFormAndShowError(formId){
	var formObj = $('form#'+formId);
	var isFormValid = true;
	
	if(formObj != null && formObj.length > 0){
		$(formObj).find('input:text, select, textarea, input:radio').each(function(ind, value){
			var element = $(this);
			var elementType = $(element).hasClass('select') ? 'select' : $(this).attr('type');
			var elementVal = $(this).val();
			var elementCtype = $(this).attr('ctype');
			
			if(elementType != null && elementType != undefined && elementCtype != null && elementCtype != undefined){
				// json.a.  OR  json['a'].
				
				if(MS_FORM_VALDTN_OBJ[elementCtype] !== undefined){
					var pattern = MS_FORM_VALDTN_OBJ[elementCtype][0];
					var errorMsg = MS_FORM_VALDTN_OBJ[elementCtype][1];
					
					if(elementType == 'text'){
						if(!pattern.test(elementVal)){
							$(element).after('<span class="error-msg block form-val-error-msg padding-bottom-10" style="font-size: smaller;">' +errorMsg +'</span>');
							isFormValid = false;
						}
					}
					else if(elementType == 'select' && elementVal == null || elementVal == '' || elementVal == '--Select--'){
						$(element).after('<span class="error-msg block form-val-error-msg padding-bottom-10" style="font-size: smaller;"> Invalid option </span>');
						isFormValid = false;
					}
				}
			}
		});
	}
	return isFormValid;
}


// Validate selected file and show error
function validateFileAndShowError(file){
	$('form#file-upload-form').find('.error-msg').addClass('hide').text('');
	var errorMsg = '';
	
	if(file != null && file != undefined){
		var fullFileName = file.name;
		var fileSize = file.size;
		var fileType = file.type;
		
		var fileNameArr = fullFileName.split('.');
		var fileName = '', fileExt = '';
		var fileType = fileType.split('/')[1];
		
		if(fileNameArr != null && fileNameArr != undefined && fileNameArr.length > 0){
			fileName = fileNameArr[0];
			fileExt = fileNameArr[1];
			if(fileNameArr.length > 2){
				errorMsg = 'Invalid filename - '+fullFileName;
			}
		}
		else if(ALPHA_NUMERIC_REGEX_4.test(fileName)){
			// errorMsg = 'Filename should contain Alphabets, Numbers, Underscores or Spaces';
			errorMsg = 'Filename cannot contain Special characters';
		}
		else if(fileName.length > 100){
			errorMsg = 'Filename length should not exceed 100 characters';
		}
		else if(fileExt != fileType && !FILE_TYPE_REGEX.test(fileType)){
			errorMsg = 'Invalid file type. Only JPG/PNG/PDF allowed';
		}
		else if(fileSize == 0){
			errorMsg = 'File size cannot be empty';
		}
		else if(fileSize / (1024*1024) > 10){
			errorMsg = 'File size should not exceed 10 MB';
		}
		
		if(errorMsg != null && errorMsg.length > 0){
			$('form#file-upload-form').find('.error-msg').removeClass('hide').text(errorMsg);
			return false;
		}
		return true;
	}
	return false;
}


// Fire a JavaScript function (triggerFunction) when a user finishes typing instead of on key up ?
function fireEventAfterUserInput(triggerFunction){
	if(typeof triggerFunction == 'function'){
		var timer = null;
		clearTimeout(timer);
		timer = setTimeout(triggerFunction, 500);
	}
}


// Perform given action 
function performAction(action){
	window.location.href = action;
}


// To apply pagination to given table
function applyPagination(tableId){
	$('table.ms-table').next('div.ms-pager').remove();
	
	$('#'+tableId).simplePagination({
		// the number of rows to show per page
		perPage: 5,

		// CSS classes to custom the pagination
		containerClass: 'ms-pager',
		previousButtonClass: 'ms-pager-prev',
		nextButtonClass: 'ms-pager-next',

		// text for next and prev buttons
		previousButtonText: 'Previous',
		nextButtonText: 'Next',

		// initial page
		currentPage: 1
	});
}


// Toggle image loader (Loading...)
function toggleLoader(action){
	if(action == 'show'){
		$('div.ms-content-div').find('.ms-loader').removeClass('hide');
	}
	else if(action == 'hide'){
		$('div.ms-content-div').find('.ms-loader').addClass('hide');
	}
}


// Show msg in Pop up
function showInfoPopUp(type, status){
	if(status != null && status == 'Y'){
		var successMsg = MS_FUNC_MSG[type][0];
		if(successMsg != null && successMsg.length > 0){
			$('div.ms-info-popup').find('.success-msg').text(successMsg).removeClass('hide');
		}
	}
	else if(status != null && status == 'N'){
		var errorMsg = MS_FUNC_MSG[type][1];
		if(errorMsg != null && errorMsg.length > 0){
			$('div.ms-info-popup').find('.error-msg').text(errorMsg).removeClass('hide');
		}
	}
	$('div#ms-info-popup').modal('show');
}

