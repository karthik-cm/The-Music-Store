<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertAttribute name="ms_libs" />

<style>
	.margin-left-50 {margin-left: 50px;}
	form#update-profile-form > input[type=text] {width: 30%}
</style>


<div id="profile-details-content" style="padding-bottom: 30px;">
	<tiles:insertAttribute name="ms_header" />

	<div class="ms-content-div ms-profile-content-div" style="padding: 60px;">
		<h3 style="text-decoration: underline; margin-top: 50px;"><b> Profile Details :- </b></h3>
		<p class="margin-bottom-25"> Kindly find below profile related details </p>
		
		<div id="user-profile-details" class="ms-form-div" style="width: 85%;">
			<form id="update-profile-form" name="update-profile-form" class="update-profile-form ms-form">
				<div class="con margin-left-50">
					<label for="firstname"><b> First Name </b> </label>
			   	</div>
			   	<div class="con margin-bottom-15 margin-left-50">
			     	<input type="text" placeholder="Enter First Name" name="firstname" class="firstname" style="padding:5px; width: 30%" required>
			   	</div>
			   	
			   	<div class="con margin-left-50">
			   		<label for="lastname"><b> Last Name </b> </label>
			   	</div>
			   	<div class="con margin-bottom-15 margin-left-50">
			     	<input type="text" placeholder="Enter Last Name" name="lastname" class="lastname" style="padding:5px; width: 30%">
			   	</div>
			
			   	<div class="con margin-left-50">
			  		<label for="contactno"><b> Contact No</b> </label>
			   	</div>
			   	<div class="con margin-bottom-15 margin-left-50">
			     	<input type="text" placeholder="Enter Contact No" name="contactno" class="contactno" maxlength="10" style="padding:5px; width: 30%" required>
			   	</div>
			   	
			   	<div class="con margin-left-50">
			   		<label for="emailid"><b> Email ID </b> </label>
			   	</div>
			   	<div class="con margin-bottom-15 margin-left-50">
			     	<input type="text" placeholder="Enter Email Id" name="emailid" class="emailid" style="padding:5px; width: 30%" required>
			   	</div>
			   	
			   	<div class="con margin-left-50">
			  		<label for="username"><b> Username </b> </label>
			   	</div>
			   	<div class="con margin-bottom-15 margin-left-50">
			   		<input type="text" placeholder="Create Username" name="username" class="username" style="padding:5px; width: 30%" required>
			   		
			   		<span id="checkUserNameDiv" class="hide">
			   			<input type="hidden" class="validusername" value="N" />
			   			<!-- <a onclick="checkUserNameAvailability(this)"> Check Availability </a> -->
			   			<span class="check-msg hide"></span>
			   		</span>
			   	</div>
			   	
			   	<div class="margin-left-50" style="padding-top: 35px;">
					<button type="button" class="btn btn-info edit-profile-btn" onclick="editProfileDetails()" style="width: 20%;"> Edit </button>
					<button type="button" class="btn btn-success update-profile-btn hide" onclick="updateProfileDetails()" style="width: 20%; margin-left: 10px;"> Update </button>
					<button type="button" class="btn btn-danger edit-cancel-profile-btn hide" onclick="cancelEditProfileDetails()" style="width: 20%; margin-left: 10px;"> Cancel </button>
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
$(document).ready(function(){
	if(!isEmptyObj(${userDetails})){
		var userDetailsJson = $.parseJSON(JSON.stringify(${userDetails}));
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
		
		// $(formObj).attr('disabled', true).css('pointer-events': 'none');
		$(formObj).find('input').attr('disabled', true).css({'pointer-events':'none'});
	}
	
	var updateProfileFormObj = $('form#update-profile-form');
	fireEventAfterUserInput($(updateProfileFormObj).find('input.username'), checkUserNameAvailability('profile'));
});


function editProfileDetails(){
	var formObj = $('form#update-profile-form');
	$(formObj).find('input').attr('disabled', false).css({'pointer-events': 'auto'});
	$(formObj).find('.update-profile-btn, .edit-cancel-profile-btn, #checkUserNameDiv').removeClass('hide');
}


function cancelEditProfileDetails(){
	var formObj = $('form#update-profile-form');
	$(formObj).find('input').attr('disabled', true).css({'pointer-events': 'none'});
	$(formObj).find('.update-profile-btn, .edit-cancel-profile-btn, #checkUserNameDiv').addClass('hide');
}


function updateProfileDetails(){
	var formObj = $('form#update-profile-form');
	var firstName = $(formObj).find('input.firstname').val();
	var lastName = $(formObj).find('input.lastname').val();
	var contactNo = $(formObj).find('input.contactno').val();
	var emailId = $(formObj).find('input.emailid').val();
	var userName = $(formObj).find('input.username').val();
	
	$(formObj).find('.ms-loader').removeClass('hide');
	
	if(firstName != null && lastName != null && contactNo != null && emailId != null && userName != null){
		$.ajax({
			type : 'POST',
			url : 'updateProfile',
			data : $(formObj).serialize(),
			success : function(response){
				$(formObj).find('.ms-loader').addClass('hide');
				var status = response;
				status = (status != null && status != undefined && status == 'Y') ? 'Y' : 'N';
				showPopUp('update_profile', status);
			},
			error : function(response){
				$('div.update-track-div').find('.ms-loader').addClass('hide');
				showPopUp('update_profile', 'N');
			}
		});
	}
}
</script>
