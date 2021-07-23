<!-- Music Store Contents -->
	
	<!-- Logout PopUp -->
	<div id="logout-popup" class="modal" style="display: none; margin-bottom: 15px;">
		<div class="modal-content animate" style="height:auto; width:40%;">
			<div class="imgcontainer">
				<h3 class="bold"> LOGOUT </h3>
		  		<span class="close close-popup" title="Close Modal" style="float:right" onclick="toggleLogoutPopup('hide')">&times;</span>
		   		<img src="ui-resources/images/user-img.png" height="100px" width="100px" alt="login-user image">
		 	</div> <br>
		
			<h4 class="align-center"> Are you sure want to Logout ? </h4>
		
		 	<div class="con align-center" style="margin-bottom: 25px; margin-top: 35px;">
				<button type="button" class="btn btn-danger bold" onclick="logout()"> LogOut </button>
		  		<button type="button" class="btn btn-success bold close-logout-popup" style="margin-left: 40px;" onclick="toggleLogoutPopup('hide')"> Cancel </button>
			</div>
		</div>
	</div>
	
	
	<!-- MS Info Pop Up -->
	<div id="ms-info-popup" class="modal fade ms-info-popup" role="dialog" style="margin-bottom: 15px;">
		<div class="ms-info-popup-content modal-content animate align-center">
			<div class="ms-info-popup-header popup-border-bottom" style="padding-top: 3px; background-color: #92caf9;">
				<button type="button" class="close close-info-popup" data-dismiss="modal" style="right: 10px; top: 4px; font-size: 30px;">&times;</button>
				<h4 class="bold"> INFO </h4>
			</div>
		
			<div class="ms-info-popup-body">
				<div class="align-center popup-border-bottom" style="padding: 15px 0px 30px 0px;">
					<span class="success-msg hide"></span>
					<span class="error-msg hide"></span>
				</div>
			
				<div style="padding: 10px 0px 10px 0px;">
					<button type="button" class="btn btn-danger close-info-popup" data-dismiss="modal"> Close </button>
				</div>
			</div>
		</div>
	</div>