<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertAttribute name="ms_libs" />

<style>
	input.ms-password {padding:5px; width: 30%}
</style>

<div id="change-pwd-content" style="padding-bottom: 30px;">
	<tiles:insertAttribute name="ms_header" />
	
	<div class="ms-content-div ms-change-pwd-content-div" style="padding: 50px;">
		<h3 style="text-decoration: underline; margin-top: 50px;"><b> Change Password :- </b></h3>
		<p class="margin-bottom-20"> Kindly enter below details to update password </p>
		
		<div id="change-pwd-details" class="ms-form-div" style="width: 85%;">
			<form id="change-pwd-form" name="change-pwd-form" class="change-pwd-form ms-form">
				<div id="change-pwd-div" style="margin-left: 10px; margin-bottom: 20px;">
					<span class="error-msg hide"></span>
				</div>
				
				<div class="con margin-left-10">
					<label for="oldpwd"><b> Old Password </b><span class="red-asterisk">*</span></label>
			   	</div>
			   	<div class="con margin-bottom-15 margin-left-10">
			     	<input type="password" placeholder="Enter Old Password" name="oldpwd" class="oldpwd ms-password" required>
			     	<span class="glyphicon glyphicon-eye-close cursor-pointer"></span>
			   	</div>
			   	
			   	<div class="con margin-left-10">
			   		<label for="newpwd"><b> New Password </b><span class="red-asterisk">*</span></label>
			   	</div>
			   	<div class="con margin-bottom-15 margin-left-10">
			     	<input type="password" placeholder="Enter New Password" name="newpwd" class="newpwd ms-password">
			     	<span class="glyphicon glyphicon-eye-close cursor-pointer"></span> 
			   	</div>
			
			   	<div class="con margin-left-10">
			  		<label for="conpwd"><b> Confirm New Password </b><span class="red-asterisk">*</span></label>
			   	</div>
			   	<div class="con margin-bottom-15 margin-left-10">
			     	<input type="password" placeholder="Confirm New Password" name="conpwd" class="conpwd ms-password" required>
			     	<span class="glyphicon glyphicon-eye-close cursor-pointer"></span> 
			   	</div>
			   	
			   	<div class="margin-left-10" style="padding-top: 25px;">
					<button type="button" class="btn btn-success update-profile-btn" onclick="changePassword()" style="width: 15%;"> Submit </button>
					<button type="reset" class="btn btn-info clear" style="width: 15%; margin-left: 10px;"> Clear </button>
				</div>
				
				<div class="ms-loader align-center hide" style="margin-top: 20px;">
					<img alt="Loading..." src="ui-resources/gifs/loading.gif">
				</div>
			</form>
		</div>
	</div>
	
 	<tiles:insertAttribute name="ms_contents" />
 	
	<tiles:insertAttribute name="ms_footer" />
</div>

<script>
/* $(document).ready(function(){
	if(!isEmptyObj(${userDetails})){
		var userDetailsJson = $.parseJSON(JSON.stringify(${userDetails}));
		
		var userDetails = $.parseJSON('${userDetails}');
		loadUserDetails(userDetails);
		
		var firstName = userDetailsJson.first_name;
		var lastName = userDetailsJson.last_name;
		var contactNo = userDetailsJson.contact_no;
		var emailId = userDetailsJson.email_id;
		var userName = userDetailsJson.user_name;
		
		var formObj = $('form#update-profile-form');
		$(formObj).find('input.firstname').val(firstName);
		$(formObj).find('input.lastname').val(lastName);
		$(formObj).find('input.contactno').val(contactNo);
		$(formObj).find('input.emailid').val(emailId);
		$(formObj).find('input.username').val(userName);
		
		// $(formObj).attr('disabled', true).css({'pointer-events': 'none'});
		$(formObj).find('input').attr('disabled', true).css({'pointer-events': 'none'});
	}
}); */


function changePassword(){
	var formObj = $('form#change-pwd-form');
	$(formObj).find('span.error-msg').text('').addClass('hide');
	
	var oldPwd = $(formObj).find('input.oldpwd').val().trim();
	var newPwd = $(formObj).find('input.newpwd').val().trim();;
	var conPwd = $(formObj).find('input.conpwd').val().trim();;
	$(formObj).find('.ms-loader').removeClass('hide');
	var errorMsg = null;
	
	if(oldPwd != null && oldPwd.length > 0 && newPwd != null && newPwd.length > 0 && conPwd != null && conPwd.length > 0){
		if(newPwd.length == conPwd.length && newPwd === conPwd){
			$.ajax({
				type : 'POST',
				url : 'changePwd',
				data : $(formObj).serialize(),
				success : function(response){
					$(formObj).find('.ms-loader').addClass('hide');
					var responseObj = $.parseJSON(response);
					alert(JSON.stringify(responseObj));
					var status = (responseObj != null && responseObj.status != undefined && responseObj.status == 'Y') ? 'Y' : 'N';
					var msg = (responseObj != null && responseObj.respMsg != undefined) ? responseObj.respMsg : responseObj.respMsg;
					
					if(status == 'Y'){
						showPopUp('change_password', status, msg);
					}else{
						$(formObj).find('span.error-msg').text(msg).removeClass('hide');
					}
					$(formObj).find('.ms-loader').addClass('hide');
				},
				error : function(response){
					$('div.update-track-div').find('.ms-loader').addClass('hide');
					showPopUp('change_password', 'N');
				}
			});
		}else{
			errorMsg = 'New password and Confirm password do not match. Please check.';
		}
	}else{
		errorMsg = 'Fields marked with (*) cannot be left blank';
	}
	
	if(errorMsg != null && errorMsg.length > 0){
		$(formObj).find('span.error-msg').text(errorMsg).removeClass('hide');
		$(formObj).find('.ms-loader').addClass('hide');
	}
}
</script>