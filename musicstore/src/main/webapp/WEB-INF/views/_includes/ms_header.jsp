<%@page import="com.musicstore.constants.MusicStoreConstants"%>
<%@page import="com.musicstore.utils.SessionUtil"%>
<%@page import="org.json.JSONObject"%>

<% 
	JSONObject userDetails = (JSONObject) SessionUtil.getAttribute(request, MusicStoreConstants.MS_USER_DETAILS);
%>

<!-- Header -->
<div class="w3-panel">
	<div style="float: right; padding-right: 15px;">
		<h5 id="welcome-msg" class="bold"></h5>
		<h5 id="last-login-time" class="bold"></h5>
	</div>
	<div style="margin: auto; width: 50%;">
		<!-- <img alt="" src="/musicstore/ui-resources/images/dashboard/Music-icon.jpg"> -->
  		<h1 class="bold" align="center" style="text-decoration: underline;"> THE MUSIC STORE </h1>
  	</div>
</div>
	

<!-- Links -->
 <div class="ms-menu-items">
	<ul class="nav nav-tabs nav-justified">
		<!-- Home -->
		<li>
			<a href="#home" class="ms-menu-list profile-home" onclick="goToDashboard()"> Home </a>
		</li>
		
		
		<!-- Album -->
		<li class="dropdown">
			<a href="#" class="ms-menu-list album dropdown-toggle" data-toggle="dropdown"> Album <span class="caret"></span></a>
			<ul class="dropdown-menu" style="margin-left: 100px; width: 75%;">
	 			<li><a href="#" class="album-home" onclick="goToAlbum(1)"> Album Home </a></li>
	 			<li class="divider"></li>
	 			<li><a href="#" class="add-album" onclick="goToAlbum(2)"> Add Album </a></li>
	 			<li class="divider"></li>
	 			<li><a href="#" class="view-album" onclick="goToAlbum(3)"> View Album </a></li>
	 			<li class="divider"></li>
	 			<li><a href="#" class="edit-album" onclick="goToAlbum(4)"> Edit Album </a></li>
	 		</ul>
		</li>
		
		
		<!-- Track -->
		<li class="dropdown">
			<a href="#" class="ms-menu-list track dropdown-toggle" data-toggle="dropdown"> Track <span class="caret"></span></a>
			<ul class="dropdown-menu" style="margin-left: 100px; width: 75%;">
	 			<li><a href="#" class="track-home" onclick="goToTrack(1)"> Track Home </a></li>
	 			<li class="divider"></li>
	 			<li><a href="#" class="add-track" onclick="goToTrack(2)"> Add Track </a></li>
	 			<li class="divider"></li>
	 			<li><a href="#" class="view-track" onclick="goToTrack(3)"> View Track </a></li>
	 			<li class="divider"></li>
	 			<li><a href="#" class="edit-track" onclick="goToTrack(4)"> Edit Track </a></li>
	 		</ul>
		</li>
		
		
		<!-- Files -->
		<li class="dropdown">
			<a href="#" class="ms-menu-list files" onclick="goToFile()"> File </a>
		</li>
		
		<li class="dropdown">
			<a href="#" class="ms-menu-list dropdown-toggle" data-toggle="dropdown"> Options <span class="caret"></span></a>
			<ul class="dropdown-menu" style="margin-left: 100px; width: 15%;">
					<li><a href="#" class="profile-details" onclick="goToProfile()"> Profile Details </a></li>
					<li class="divider"></li>
					<li><a href="#" class="change-pwd" onclick="goToChangePwd()"> Change Password </a></li>
					<li class="divider"></li>
	  				<li><a href="#" class="logout" onclick="toggleLogoutPopup('show')"> LogOut </a></li>
				</ul>
		</li>
	</ul>
</div>

<script>
$(document).ready(function(){
	// var userDetails = $.parseJSON('${MS_USER_DETAILS}');
	var userDetails = <%= userDetails %>;
	loadUserDetails(userDetails);
});
</script>