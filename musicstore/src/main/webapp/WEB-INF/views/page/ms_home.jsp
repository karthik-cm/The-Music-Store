<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- <jsp:include page="/WEB-INF/views/_includes/libs.jsp"></jsp:include> --%>
<tiles:insertAttribute name="ms_libs" />

<style>
	.ms-home-img {width:80%;}
</style>


<!-- Home page Content -->
<div id="home" class="ms-home-content" style="width: 100%;">
	<div>
		<h1 align="center" style="text-decoration: underline;"><b> THE MUSIC STORE </b></h1>
	</div>
	
	
	<!-- Quick Links -->
  	<div class="row">
  		<div class="ms-menu-items">
	  		<ul class="nav nav-tabs nav-justified">
	  			<li>
	  				<a href="#home" class="ms-menu-list"> Home </a>
	  			</li>
	  			<li>
	  				<a href="#about" class="ms-menu-list"> About </a>
	  			</li>
	  			<li>
	  				<a href="#contact" class="ms-menu-list"> Contact </a>
	  			</li>
	  			<li>
	  				<a href="#login" id="login" class="ms-menu-list"> Login </a>
	  			</li>
	  		</ul>
	  	</div>
  	</div>
	

	<!-- Images Container -->
	<div class="w3-container align-center" style="margin-top: 50px;">
		<div class="w3-display-container mySlides">
		  <img class="ms-home-img" src="ui-resources/images/home/kgf-2.jpg">
		  <div class="w3-display-topleft w3-container w3-padding-32"></div>
		</div>
		
		<div class="w3-display-container mySlides">
		  <img class="ms-home-img" src="ui-resources/images/home/kgf.jpg">
		  <div class="w3-display-middle w3-container w3-padding-32"></div>
		</div>
		
		<div class="w3-display-container mySlides">
		  <img class="ms-home-img" src="ui-resources/images/home/eminem.jpg">
		  <div class="w3-display-topright w3-container w3-padding-32"></div>
		</div>
		
		<div class="w3-display-container mySlides">
		  <img class="ms-home-img" src="ui-resources/images/home/one-republic.jpg">
		  <div class="w3-display-topright w3-container w3-padding-32"></div>
		</div>
		
		<div class="w3-display-container mySlides">
		  <img class="ms-home-img" src="ui-resources/images/home/coldplay.jpg">
		  <div class="w3-display-topright w3-container w3-padding-32"></div>
		</div>


	<!-- Slideshow : next/previous buttons -->
		<div class="w3-container w3-dark-grey w3-padding w3-xlarge" style="width:80%; margin-left: 132px;">
		  <div class="w3-left" onclick="plusDivs(-1)"><i class="fa fa-arrow-circle-left w3-hover-text-teal"></i></div>
		  <div class="w3-right" onclick="plusDivs(1)"><i class="fa fa-arrow-circle-right w3-hover-text-teal"></i></div>
		
		  <div class="w3-center">
		  	<span class="w3-tag demodots w3-border w3-transparent w3-hover-white" onclick="currentDiv(1)"></span>
		    <span class="w3-tag demodots w3-border w3-transparent w3-hover-white" onclick="currentDiv(2)"></span>
		    <span class="w3-tag demodots w3-border w3-transparent w3-hover-white" onclick="currentDiv(3)"></span>
		    <span class="w3-tag demodots w3-border w3-transparent w3-hover-white" onclick="currentDiv(4)"></span>
		    <span class="w3-tag demodots w3-border w3-transparent w3-hover-white" onclick="currentDiv(5)"></span>
		  </div>
	   	</div>
	</div>


	<!-- Login : PopUp -->
	<div id="login-popup" class="modal" style="display: none;">
		<form class="modal-content animate" id="login-form" name="login-form" style="height:auto; width:45%;">
			<div class="imgcontainer">
				<h3 class="bold"> LOGIN </h3>
		  		<span class="close close-popup" title="Close Modal" style="float:right">&times;</span>
		    		<img src="ui-resources/images/user-img.png" height="100px" width="100px" alt="login-user image">
		  	</div> <br>
		
			<div class="msg-div align-center margin-bottom-15">
		  		<span class="error-msg hide"></span>
		  		<span class="success-msg hide"></span>
		  	</div>
			
			<!-- <div class="con">
				<label for="username"><b> Username </b></label>
			</div> -->
		  	<div class="con align-center margin-bottom-15">
				<input type="text" placeholder="Username" name="username" class="username" ctype="AN2" style="padding:5px;" required>
			</div>
			
			<!-- <div class="con">
				<label for="username"><b> Password </b></label>
			</div> -->
			<div class="con align-center" style="margin-bottom:40px; margin-left: 120px;">
		  		<input type="password" placeholder="Password" name="password" class="password ms-password" style="padding:5px;" required>
		  		<input type="checkbox" onclick="togglePasswordVisibility($(this))"> Show Password 
			</div>
		
			<div class="con align-center">
				<button type="button" class="btn btn-success bold" onclick="login()" style="width: 15%;"> Login </button>
		  		<button type="button" class="btn btn-danger bold close-login-popup" style="width: 15%; margin-left: 5px;"> Cancel </button>
			</div> <br>
			
			<div class="ms-loader align-center hide">
				<img alt="Loading..." src="ui-resources/gifs/loading.gif">
			</div>
			
			
			<!-- Div for New user -->
			<div style="margin-left: 500px; margin-bottom: 15px;">
		  		<a id="newuser"> New User ? </a>
		  	</div>
		</form>
	 </div>


	 <!-- New User Sign Up : Pop Up -->
	 <div id="signup-popup" class="modal" style="display: none;">
	 	<div style="overflow-y: initial !important;">
	  	<form id="new-user-form" name="new-user-form" class="modal-content animate new-user-form" style="height:auto; width:50%;">
	    	
	    	<div class="modal-body" style="height: 450px; overflow-y: auto;">
	    		<div class="imgcontainer">
		    		<h3> NEW USER : SIGN UP </h3>
		      		<span class="close close-popup" title="Close Modal" style="float:right">&times;</span>
		      		<img src="ui-resources/images/user-img.png" height="100px" width="100px" alt="login-user image">
	    		</div>
	    	
	    		<div class="align-center" style="margin-bottom: 35px;">
		    		<h5 class="bold"> Kindly fill below details to create Music Store account.</h5>
		    	</div>
		    	
		    	<div class="msg-div align-center margin-bottom-15">
		    		<span class="error-msg hide"></span>
		    		<span class="success-msg hide"></span>
		    	</div>
		    	
		    	<div class="con margin-left-222">
		   			<label for="firstname"><b> First Name </b> <span class="red-asterisk">*</span></label>
		    	</div>
		    	<div class="con margin-bottom-15 align-center">
		      		<input type="text" placeholder="Enter First Name" name="firstname" class="firstname" maxlength="30" ctype="AL" style="padding:5px;" required>
		    	</div>
		    	
		    	<div class="con margin-left-222">
		    		<label for="lastname"><b> Last Name </b> <span class="red-asterisk">*</span></label>
		    	</div>
		    	<div class="con margin-bottom-15 align-center">
		      		<input type="text" placeholder="Enter Last Name" name="lastname" class="lastname" maxlength="30" ctype="AL" style="padding:5px">
		    	</div>
		
		    	<div class="con margin-left-222">
		   			<label for="contactno"><b> Contact No</b> <span class="red-asterisk">*</span></label>
		    	</div>
		    	<div class="con margin-bottom-15 align-center">
		      		<input type="text" placeholder="Enter Contact No" name="contactno" class="contactno" maxlength="10" ctype="NI" style="padding:5px" required>
		    	</div>
		    	
		    	<div class="con margin-left-222">
		    		<label for="emailid"><b> Email ID </b> <span class="red-asterisk">*</span></label>
		    	</div>
		    	<div class="con margin-bottom-15 align-center">
		      		<input type="text" placeholder="Enter Email Id" name="emailid" class="emailid" maxlength="30" ctype="EM" style="padding:5px" required>
		    	</div>
		    	
		    	<div class="con margin-left-222">
		   			<label for="username"><b> Username </b> <span class="red-asterisk">*</span></label>
		    	</div>
		    	<div class="con margin-bottom-15 margin-left-222">
		      		<input type="text" placeholder="Create Username" name="username" class="username" maxlength="30" ctype="AN2" style="padding:5px" required>
		      		
		      		<span id="checkUserNameDiv">
		      			<input type="hidden" class="validusername" value="N" />
		      			<!-- <a onclick="checkUserNameAvailability(this)"> Check Availability </a> -->
		      			<span class="check-msg hide"></span>
		      		</span>
		      		 
		    	</div>
		    	
		    	<div class="con margin-left-222">
		    		<label for="psw"><b> Password </b> <span class="red-asterisk">*</span></label>
		    	</div>
		    	<div class="con margin-bottom-15 margin-left-222">
		      		<input type="password" placeholder="Create Password" name="password" class="password ms-password" style="padding:5px" required>
		      		<input type="checkbox" onclick="togglePasswordVisibility($(this))"> Show Password 
		    	</div>
		    	
		    	<div class="con margin-left-222">
		    		<label for="psw"><b> Confirm Password </b> <span class="red-asterisk">*</span></label>
		    	</div>
		    	<div class="con margin-bottom-15 margin-left-222">
		      		<input type="password" placeholder="Confirm Password" name="confirmpassword" class="confirmpassword ms-password" style="padding:5px" required>
		      		<input type="checkbox" onclick="togglePasswordVisibility($(this))"> Show Password 
		    	</div>
		
		    	<div class="con align-center margin-top-25 margin-bottom-25">
		    		<button type="button" class="btn btn-success" onclick="newUserSignUp()"> Sign Up </button>
		      		<button type="button" class="btn btn-danger close-signup-popup" style="margin-left: 20px;"> Cancel </button>
		    	</div>
		    	
		    	<!-- Div for Already Signed Up user -->
		    	<div class="margin-bottom-15" style="float: right;">
		    		<a id="login"> Already Have LogIn ? </a>
		    	</div>
	    	</div>
	  	</form>
	 	</div>
	</div>
  
  
   	<!-- About Us -->
  	<div class="w3-row w3-container" id="about">
	    <div class="w3-center w3-padding-64">
	      <span class="w3-xlarge w3-bottombar w3-border-dark-grey w3-padding-16">About Us</span>
	    </div>
	    <div class="w3-col l3 m6 w3-light-grey w3-container w3-padding-16">
	      <h3>Storage</h3>
	      <p>Phasellus eget enim eu lectus faucibus vestibulum. Suspendisse sodales pellentesque elementum.</p>
	    </div>
	
	    <div class="w3-col l3 m6 w3-grey w3-container w3-padding-16">
	      <h3>Branding</h3>
	      <p>Phasellus eget enim eu lectus faucibus vestibulum. Suspendisse sodales pellentesque elementum.</p>
	    </div>
	
	    <div class="w3-col l3 m6 w3-dark-grey w3-container w3-padding-16">
	      <h3>Consultation</h3>
	      <p>Phasellus eget enim eu lectus faucibus vestibulum. Suspendisse sodales pellentesque elementum.</p>
	    </div>
	
	    <div class="w3-col l3 m6 w3-black w3-container w3-padding-16">
	      <h3>Promises</h3>
	      <p>Phasellus eget enim eu lectus faucibus vestibulum. Suspendisse sodales pellentesque elementum.</p>
	    </div>
 	</div>
  

  	<!-- Contact Us -->
  	<div class="w3-center w3-padding-64 align-center" id="contact" style="width: 50%; margin-left: 25%;">
  	<span class="w3-xlarge w3-bottombar w3-border-dark-grey w3-padding-16">Contact Us</span> <br>
		<form class="w3-container" action="" target="_blank" style="margin-top: 15px;">
		    <div class="w3-section">
		      <label> Name </label>
		      <input class="w3-input w3-border w3-hover-border-black" style="width:100%;" type="text" name="Name" required>
		    </div>
		    <div class="w3-section">
		      <label> Email </label>
		      <input class="w3-input w3-border w3-hover-border-black" style="width:100%;" type="text" name="Email" required>
		    </div>
		    <div class="w3-section">
		      <label> Subject </label>
		      <input class="w3-input w3-border w3-hover-border-black" style="width:100%;" name="Subject" required>
		    </div>
		    <div class="w3-section">
		      <label> Message </label>
		      <input class="w3-input w3-border w3-hover-border-black" style="width:100%;" name="Message" required>
		    </div>
		    <button type="button" class="w3-button w3-block w3-black">Send</button>
		</form>
  	</div>
  
	
	<!-- Footer -->
	<%-- <jsp:include page="../_includes/ms_footer.jsp" /> --%>
	<tiles:insertAttribute name="ms_footer" />
	
</div>


<script>
$(document).ready(function(){
	// On click of Login
	$('a#login').click(function(){
		$('div#login-popup').css('display','block');
		$('div#signup-popup').css('display','none');
	});

	$('.close-login-popup, .close-popup').click(function(){
		$('div#login-popup').css('display','none');
	});


	// On click of New User
	$('a#newuser').click(function(){
		$('div#signup-popup').css('display','block');
		$('div#login-popup').css('display','none');
	});

	$('.close-signup-popup, .close-popup').click(function(){
		$('div#signup-popup').css('display','none');
	});
	
	
	// Check Username availability css
	var newUserFormObj = $('form#new-user-form');
	var userNameObj = $(newUserFormObj).find('input.username');
	
	/* var timer = null;
	$(userNameObj).keyup(function(){
		clearTimeout(timer);
		timer = setTimeout(checkUserNameAvailability('home'), 1000);
	}); */
	
	$(userNameObj).keyup(function(){
		setTimeout(checkUserNameAvailability('home'), 1000);
	});
	
	
	$(newUserFormObj).find('#checkUserNameDiv').css({'cursor':'not-allowed'});
	$(newUserFormObj).find('#checkUserNameDiv').children().css({'pointer-events':'none'});
	
	$(newUserFormObj).find('input.username').on('mouseleave', function(){
		if($(newUserFormObj).find('input.username').val().length > 0){
			$(newUserFormObj).find('#checkUserNameDiv').css({'cursor':'pointer'});
			$(newUserFormObj).find('#checkUserNameDiv').children().css({'pointer-events':'auto'});
		}
		else{
			$(newUserFormObj).find('#checkUserNameDiv').css({'cursor':'not-allowed'});
			$(newUserFormObj).find('#checkUserNameDiv').children().css({'pointer-events':'none'});
			$(newUserFormObj).find('#checkUserNameDiv').find('span.check-msg').text('');
			$(newUserFormObj).find('input#validUserName').val('N');
		}
	});
});


// Slideshow
var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
	showDivs(slideIndex += n);
}

function currentDiv(n) {
	showDivs(slideIndex = n);
}

function showDivs(n) {
	var i;
	var x = document.getElementsByClassName("mySlides");
	var dots = document.getElementsByClassName("demodots");
	if (n > x.length){		  
		slideIndex = 1;
	}   
	if (n < 1){
		slideIndex = x.length;
	}
	for (i = 0; i < x.length; i++) {
		x[i].style.display = "none";  
	}
	for (i = 0; i < dots.length; i++) {
		dots[i].className = dots[i].className.replace(" w3-white", "");
	}
	x[slideIndex-1].style.display = "block";  
	dots[slideIndex-1].className += " w3-white";
}
</script>


<!-- http://hostname:portno/contextroot/urlpatternofservlet -->
