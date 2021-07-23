/*###### JS file for Music Store functions ######
 *#####  @Author Karthik CM ######*/


// Show user entered password
function showPwd(obj){
	$(obj).toggleClass('glyphicon-eye-close glyphicon-eye-open');
	$(obj).prev('input.password').attr('type','text');
}


// Hide user entered password
function hidePwd(obj){
	$(obj).toggleClass('glyphicon-eye-close glyphicon-eye-open');
	$(obj).prev('input.password').attr('type','password');
}


// Toggle password visibility
function togglePasswordVisibility(obj){
	if($(obj).prev('input.ms-password').attr('type') == 'password'){
		$(obj).prev('input.ms-password').attr('type','text');
	}
	else{
		$(obj).prev('input.ms-password').attr('type','password');
	}
}


// Load user details 
function loadUserDetails(userDetails){
	var msUserName = userDetails.first_name +' ' +userDetails.last_name;
	var lastLoginTime = (userDetails.last_login_time != undefined) ? userDetails.last_login_time : 'NA';
	$('#welcome-msg').text('Welcome, '+msUserName);
	$('#last-login-time').text('Last Login on '+lastLoginTime);
}




/********* On Click Redirect **********/

// Go to Dashboard Page
function goToDashboard(){
	performAction('dashboard');
}


// Go to Profile details Page
function goToProfile(){
	// performAction('profile-details');
	/* var portletName = 'ms_profile';
	var targetDiv = 'profile-details-portlet';
	var hideDiv = 'ms-functions';
	load_portlet(portletName, targetDiv, hideDiv); */
	
	performAction('profile');
}


// Go to Change password Page
function goToChangePwd(){
	performAction('changePwd');
}


// Go to Album home
function goToAlbum(id){
	/*var portletName = 'ms_album';
	var targetDiv = 'album-portlet';
	var hideDiv = 'ms-functions';
	load_portlet(portletName, targetDiv, hideDiv);
	postPortletLoadFunc(portletName, id);*/
	
	var view = 'album';
	performAction(view);
	postPortletLoadFunc(view, id)
}


// Go to Track
function goToTrack(id){
	/*var portletName = 'ms_track';
	var targetDiv = 'track-portlet';
	var hideDiv = 'ms-functions';
	load_portlet(portletName, targetDiv, hideDiv);
	postPortletLoadFunc(portletName, id);*/
	
	var view = 'track';
	performAction(view);
	postPortletLoadFunc(view, id)
}


// Go to File upload
function goToFile(){
	var view = 'file';
	performAction(view);
}

/********* On Click Redirect - Ends  **********/




// Check user name availability
function checkUserNameAvailability(type){
	var formObj = (type != null && type.toUpperCase() == 'HOME') ? $('form#new-user-form') : $('form#update-profile-form');
	$(formObj).find('span.check-msg').text('');
	var userName = $(formObj).find('input.username').val().trim();
	
	if(userName != null && userName.length > 0){
		if(userName.length >= GL_USERNAME_LENGTH){
			$.ajax({
				type : 'POST',
				url : 'checkUserNameAvailability',
				data : 'userName='+userName,
				success : function(response){
					var status = response.toString();
					if(status != null && status != undefined){
						if(status == 'Y'){
							$(formObj).find('span.check-msg').text('Available !').addClass('success-msg').removeClass('hide error-msg');
						}
						else{
							$(formObj).find('span.check-msg').text('Already taken !').addClass('error-msg').removeClass('hide success-msg');
						}
					}
					else{
						$(formObj).find('span.check-msg').text('Already taken !').addClass('error-msg').removeClass('hide success-msg');
					}
					$(formObj).find('input.validusername').val(status);
				},
				error : function (response){
					$(formObj).find('span.check-msg').text('System is currently down. Please try after some time.').removeClass('hide success-msg');
					$(formObj).find('input#validUserName').val('N');
				}
			});
		}
		else{
			$(formObj).find('span.check-msg').text('Min '+GL_USERNAME_LENGTH +' characters !').addClass('error-msg').removeClass('hide success-msg');
		}
	}
	else{
		$(formObj).find('span.check-msg').text('Cannot be empty !').addClass('error-msg').removeClass('hide success-msg');
	}
}


// New user sign up 
function newUserSignUp(){
	var isValidForm = true;
	var errMsg = '';
	var formObj = $('form#new-user-form');
	$(formObj).find('div.msg-div').find('.error-msg').text(errMsg).addClass('hide');
	
	var firstName = $(formObj).find('input.firstname').val().trim();
	var lastName = $(formObj).find('input.lastname').val().trim();
	var emailId = $(formObj).find('input.emailid').val().trim();
	var contactNo = $(formObj).find('input.contactno').val().trim();
	var userName = $(formObj).find('input.username').val().trim();
	var validUserName = $(formObj).find('input.validusername').val().trim();
	var password = $(formObj).find('input.password').val().trim();
	var confirmPassword = $(formObj).find('input.confirmpassword').val().trim();
	
	if(firstName.length > 0 && lastName.length > 0 && emailId.length > 0 && contactNo.length > 0 
			&& userName.length > 0 && password.length > 0 && confirmPassword.length > 0){
		
		var isFormValid = validateFormAndShowError($(formObj).attr('id'));
		if(isFormValid){
			$.ajax({
				type : 'POST',
				url : 'signUp',
				data : $(formObj).serialize(),
				success : function(response){
					var responseDetails = $.parseJSON(response);
					var status = responseDetails.status;
					
					if(status != null && status != undefined && status == 'Y'){
						goToDashboard();
					}
					else if(status != null && status != undefined && status == 'N'){
						$('form#login-form').find('.ms-loader').addClass('hide');
						$('div#login-popup').find('.error-msg').text('Invalid username/password. Kindly check.').removeClass('hide');
					}
				},
				error : function (response){
					$('form#login-form').find('.ms-loader').addClass('hide');
					$('#login-popup').find('.error-msg').text('System is currently down. Please try after some time.').removeClass('hide');
				}
			});
		}
	}
	else{
		errMsg = 'Fields marked with (*) are mandatory';
	}
	
	if(errMsg != null && errMsg.length > 0){
		$(formObj).find('div.msg-div').find('.error-msg').text(errMsg).removeClass('hide');
		// scrollToDiv($(formObj).find('div#imgcontainer'));
		scrollModalToTop($(formObj).find('div.modal-body'));
	}
}


// Login to Music Store
function login(){
	$('div#login-popup').find('.error-msg, .success-msg').text('').addClass('hide');
	var formObj = $('form#login-form');
	$(formObj).find('span.form-val-error-msg').remove();
	var username = $(formObj).find('input.username').val();
	var password = $(formObj).find('input.password').val();
	
	if(username != null && username.length > 0 && password != null && password.length > 0){
		// $('form#login-form').submit();
		$('form#login-form').find('.ms-loader').removeClass('hide');
		
		var isFormValid = validateFormAndShowError($(formObj).attr('id'));
		if(isFormValid){
			setTimeout(() => {
				$.ajax({
					type : 'POST',
					url : 'login',
					data : 'username='+username+'&password='+password,
					success : function(response){
						var loginDetails = $.parseJSON(response);
						var status = loginDetails.status;
						if(status != null && status != undefined){
							if(status == 'Y'){
								goToDashboard();
							}else{
								$('div#login-popup').find('.error-msg').text('Invalid username/password. Kindly check.').removeClass('hide');
							}
						}
						else{
							$('div#login-popup').find('.error-msg').text('Invalid username/password. Kindly check.').removeClass('hide');
						}
						$('form#login-form').find('.ms-loader').addClass('hide');
					},
					error : function (response){
						$('form#login-form').find('.ms-loader').addClass('hide');
						$('#login-popup').find('.error-msg').text('System is currently down. Please try after some time.').removeClass('hide');
					}
				});
			}, 1000);
		}
		else{
			$('form#login-form').find('.ms-loader').addClass('hide');
		}
	}
	else{
		$('div#login-popup').find('.error-msg').text('Incomplete details. Please enter username & password.').removeClass('hide');
	}
}


// Logout from Music Store
function logout(){
	performAction('logout');
}


// Post portlet load 
function postPortletLoadFunc(view, id){
	setTimeout(function(){
		if(view != null){
			if(view == 'album' && id != null){
				let albumDiv = 'a.albumhome';
				if(id == 2){
					albumDiv = 'a.addalbum';
				}else if(id == 3){
					albumDiv = 'a.viewalbum';
				}else if(id == 4){
					albumDiv = 'a.updatealbum';
				}
				$('div.album-content').find('li > '+albumDiv).trigger('click');
			}
			else if(view == 'track' && id != null){
				let trackDiv = 'a.trackhome';
				if(id == 2){
					trackDiv = 'a.addtrack';
				}else if(id == 3){
					trackDiv = 'a.viewtrack';
				}else if(id == 4){
					trackDiv = 'a.updatetrack';
				}
				$('div.track-content').find('li > '+trackDiv).trigger('click');
			}
		}
	}, 1500);
}


// Show Pop up with message
function showPopUp(type, status, msg){
	// showInfoPopUp(type, status);
	
	if(status != null && status == 'Y'){
		var successMsg = (msg != null && msg != undefined && msg.length > 0) ? msg : MS_FUNC_MSG[type][0];
		if(successMsg != null && successMsg.length > 0){
			$('div.ms-info-popup').find('.success-msg').text(successMsg).removeClass('hide');
		}
	}
	else if(status != null && status == 'N'){
		var errorMsg = (msg != null && msg != undefined && msg.length > 0) ? msg : MS_FUNC_MSG[type][1];
		if(errorMsg != null && errorMsg.length > 0){
			$('div.ms-info-popup').find('.error-msg').text(errorMsg).removeClass('hide');
		}
	}
	$('div#ms-info-popup').modal('show');
	
	
	$('#ms-info-popup').find('button.close-info-popup').on('click', function(){
		if(type == 'update_profile'){
			goToProfile();
		}
		
		if(type == 'change_password'){
			$('form#change-pwd-form').find('button.clear').trigger('click');
		}
		
		if(type == 'add_album'){
			$('form#add-album-form').find('button.clear').trigger('click');
		}
		else if(type == 'update_album' || type == 'delete_album'){
			$('div.album-content').find('a.updatealbum').trigger('click');
		}
		
		if(type == 'add_track'){
			$('form#add-track-form').find('button.clear').trigger('click');
		}
		else if(type == 'update_track' || type == 'delete_track'){
			$('div.track-content').find('a.updatetrack').trigger('click');
		}
		
		if(type == 'upload_file'){
			$('div.ms-file-upload-content-div').find('a.uploadfile').trigger('click');
		}
		else if(type == 'delete_file'){
			$('div.ms-file-upload-content-div').find('a.viewfile').trigger('click');
		}
	});
}
