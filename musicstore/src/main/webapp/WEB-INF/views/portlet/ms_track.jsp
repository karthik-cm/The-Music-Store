<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertAttribute name="ms_libs" />


<div class="track-content" style="width:100%;">
	<tiles:insertAttribute name="ms_header" />
	
	<div class="ms-content-div ms-track-content-div" style="padding: 60px;">
		<h3 style="text-decoration: underline;"><b> TRACK </b></h3>
	
		<!-- Track Navigation -->
		<ul class="nav nav-tabs track-nav-tabs" style="padding-top: 25px; width: 80%;">
			<li class="active">
				<a href="#" class="trackhome bold"> Home </a>
			</li>
			<li>
				<a href="#" class="addtrack bold"> Add Track </a>
			</li>
			<li>
				<a href="#" class="viewtrack bold"> View Track </a>
			</li>
			<li>
				<a href="#" class="updatetrack bold"> Update Track </a>
			</li>
		</ul>
		
		
		<div class="main-track-home-div" style="padding-bottom: 100px;">
		
			<!-- Track Home -->
			<div class="track-home-div" style="padding-top: 20px; padding-bottom: 50px;">
				<p class="margin-bottom-15"> This is Track section. It has mainly 3 sections : </p>
				<ol>
					<li class="margin-bottom-10"> Add Track : Here you can add your Track details for added Album.
					<li class="margin-bottom-10"> View Track : Here you can view your Track details for added Album.
					<li class="margin-bottom-10"> Update Track : Here you can update your Track details for added Album.
				</ol>
			</div>
			
			
			<!-- Add Track --> 
			<div class="add-track-div hide" style="width: 50%; margin: 30px 50px 25px 15px;">
				<h5 class="bold margin-bottom-25 underline"> Create new Track by filling below details :- </h5>
			
				<div class="ms-form-div">
					<form id="add-track-form" name="add-track-form" class="add-track-form ms-form">
						<div class="margin-bottom-15 error-msg hide"></div>
						
						<input type="hidden" name="albumid" class="albumid">
						
						<div class="margin-bottom-20">
							<label for="genre"><b> For Album </b> <span class="red-asterisk">*</span></label>
							<select name="albumname" class="albumname select" style="margin-bottom: 10px;" ctype="AN"></select>
						</div>
						
						<div class="margin-bottom-20">
						   	<label for="nameoftrack"><b> Name Of Track </b> <span class="red-asterisk">*</span></label>
						   	<input type="text" name="trackname" class="trackname" ctype="AN">
					   	</div>
					    
					    <div class="margin-bottom-20">
							<label for="artists"><b> Artists </b> <span class="red-asterisk">*</span></label>
							<input type="text" name="artists" class="artists" ctype="AN">
						</div>
					    
					    <div class="margin-bottom-20">
							<label for="duration"><b> Duration </b> <span class="red-asterisk">*</span></label>
							<input type="text" name="duration" class="duration" placeholder="Enter in the form of mm:ss eg: 4:26">
						</div>
						
						<div class="margin-bottom-20">
							<label for="sequenceno"><b> Sequence No </b> <span class="red-asterisk">*</span></label>
							<input type="number" name="sequenceno" class="sequenceno" ctype="NI">
						</div>
					    
					    <div class="align-center" style="padding-top: 35px;">
						    <button type="button" class="btn btn-success" onclick="addTrackDetails()" style="width: 20%;"> Add Track </button>
						    <button type="reset" class="btn btn-info clear" style="width: 20%; margin-left: 20px;"> Clear </button>
					    	<button type="button" class="btn btn-danger close-signup-popup" style="width: 20%; margin-left: 20px;"> Cancel </button>
					    </div>
				 	</form>
			 	</div>
			 	
			 	<div class="ms-loader align-center hide" style="margin-top: 20px;">
					<img alt="Loading..." src="ui-resources/gifs/loading.gif">
				</div>
			</div>
			
			
			<!-- View Track -->
			<div class="view-track-div hide" style="width: 85%; margin: 30px 50px 25px 10px;">
				<h5 class="bold margin-bottom-25 underline"> Track details :- </h5>
			
				<div id="viewTracksAccordian" class="viewTracksAccordian">
				</div>
			</div>
			
			
			<!-- Update Track -->
			<div class="update-track-div hide" style="width: 90%; margin: 30px 50px 25px 10px;">
				<h5 class="bold margin-bottom-25 underline"> Update / Delete required Track fields by clicking on 'Edit' option :- </h5>
				
				<div id="updateTracksAccordian" class="updateTracksAccordian">
				</div>
				
				<div class="update-delete-track-div ms-form-div hide" style="width: 65%; margin: 30px 50px 25px 15px;">
					<form id="update-track-form" name="update-track-form" class="ms-form">
						<input type="hidden" name="albumid" class="albumid">
						<input type="hidden" name="trackid" class="trackid">

					   	<div class="margin-bottom-20">
						   	<label for="genre"><b> For Album Name </b> <span class="red-asterisk">*</span></label>
							<select name="albumname" class="albumname" style="margin-bottom: 10px;" ctype="AN"></select>
						</div>
						
						<div class="margin-bottom-20">
						   	<label for="nameoftrack"><b> Name Of Track </b> <span class="red-asterisk">*</span></label>
						   	<input type="text" name="trackname" class="trackname" ctype="AN">
						</div>
					    
					    <div class="margin-bottom-20">
							<label for="artists"><b> Artists </b> <span class="red-asterisk">*</span></label>
							<input type="text" name="artists" class="artists" ctype="AN">
						</div>
					    
					    <div class="margin-bottom-20">
							<label for="duration"><b> Duration </b> <span class="red-asterisk">*</span></label>
							<input type="text" name="duration" class="duration" placeholder="Enter in the form of mm:ss eg: 4:26">
						</div>
						
						<div class="margin-bottom-20">
							<label for="sequenceno"><b> Sequence No </b> <span class="red-asterisk">*</span></label>
							<input type="number" name="sequenceno" class="sequenceno" ctype="NI">
						</div>
					    
					    <div class="align-center" style="padding-top: 35px;">
						    <button type="button" class="btn btn-success" onclick="updateTrackDetails()"> Update </button>
					    	<button type="button" class="btn btn-info cancel" style="margin-left: 20px;"> Cancel </button>
					    	<button type="button" class="btn btn-danger" style="margin-left: 20px;" onclick="deleteTrackDetails()"> Delete </button>
					    </div>
			 		</form>
				</div>
				
				<div class="ms-loader hide" style="margin-top: 20px; margin-left: 285px;">
					<img alt="Loading..." src="ui-resources/gifs/loading.gif">
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- Contents -->
 	<tiles:insertAttribute name="ms_contents" />
 	
 	
	<!-- Footer -->
	<tiles:insertAttribute name="ms_footer" />
</div>


<!-- View Track ::::  Handlebars template  -->
<script id="view-track-template" type="text/x-handlebars-template">
{{#each albumTrackDtls}}
<div class="panel-group" id="accordion">
   	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title cursor-pointer" data-toggle="collapse" data-parent="#accordion" href="#collapse_view_{{this.albumid}}">
				<a class="bold">{{this.albumid}}. {{this.albumName}}</a>
			</h4>
		</div>
		<div id="collapse_view_{{this.albumid}}" class="panel-collapse collapse">
			<table id="viewTrackTable_{{this.albumid}}" class="table table-bordered table-striped ms-table viewTrackTable" style="width: 95%; margin: 15px;">
				<thead id="viewTrackThead_{{this.albumid}}">
					<tr>
						<th> Serial No </th>
						<th> Track Name </th>
						<th> Artists </th>
						<th> Duration </th>
						<th> Sequence No </th>
						<th> Created Date </th>
					</tr>
				</thead>
				<tbody id="viewTrackBody_{{this.albumid}}">
					{{#if this.tracks}}
						{{#each this.tracks}}
							<tr>
								<td trackId="{{trackId}}"> {{trackIndex}} </td>
								<td> {{trackName}} </td>
								<td> {{artists}} </td>
								<td> {{duration}} </td>
								<td> {{sequenceNo}} </td>
								<td> {{createdDt}} </td>
							</tr>
						{{/each}}
					{{else}}
						<tr> <td class="align-center bold" colspan="7"> No records found </tr>
					{{/if}}
				</tbody>
			</table>
		</div>
    </div>
</div>
{{/each}}
</script>


<!-- Update Track ::::  Handlebars template  -->
<script id="update-track-template" type="text/x-handlebars-template">
{{#each albumTrackDtls}}
<div class="panel-group" id="accordion">
   	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title cursor-pointer" data-toggle="collapse" data-parent="#accordion" href="#collapse_update_{{this.albumid}}">
				<a class="bold">{{this.albumid}}. {{this.albumName}}</a>
			</h4>
		</div>
		<div id="collapse_update_{{this.albumid}}" class="panel-collapse collapse">
			<table id="viewTrackTable_{{this.albumid}}" class="table table-bordered table-striped ms-table viewTrackTable" style="width: 95%; margin: 15px;">
				<thead id="viewTrackThead_{{this.albumid}}">
					<tr>
						<th> Serial No </th>
						<th> Track Name </th>
						<th> Artists </th>
						<th> Duration </th>
						<th> Sequence No </th>
						<th> Created Date </th>
						<th class="updateDeleteTrackTh hide"> Update / Delete </th>
					</tr>
				</thead>
				<tbody id="viewTrackBody_{{this.albumid}}">
					{{#if this.tracks}}
						{{#each this.tracks}}
							<input type="hidden" class="updateTrackParams" albumId="{{../albumid}}" albumName="{{../albumName}}" trackId="{{trackId}}" trackName="{{trackName}}" artists="{{artists}}" duration="{{duration}}" sequenceNo="{{sequenceNo}}">
							<tr>
								<td> {{trackIndex}} </td>
								<td> {{trackName}} </td>
								<td> {{artists}} </td>
								<td> {{duration}} </td>
								<td> {{sequenceNo}} </td>
								<td> {{createdDt}} </td>
								<td class="updateDeleteTrack cursor-pointer underline hide" id="updateDeleteTrack_{{trackIndex}}" onclick="editTrack($(this))"> Edit </td>
							</tr>
						{{/each}}
					{{else}}
						<tr> <td class="align-center bold" colspan="7"> No records found </tr>
					{{/if}}
				</tbody>
			</table>
		</div>
    </div>
</div>
{{/each}}
</script>


<script>
$('ul.track-nav-tabs').find('li > a').on('click', function(){
	$(this).parents('li').addClass('active');
	$(this).parents('li').siblings().find('a').css({'color':'black'});
	$(this).css({'color':'#337ab7'});
	
	if($(this).hasClass('trackhome')){ // track Home
		$('div.track-home-div').removeClass('hide');
		$('div.add-track-div, div.view-track-div, div.update-track-div').addClass('hide');
	}
	else if($(this).hasClass('addtrack')){ // Add track
		$('div.add-track-div').removeClass('hide');
		$('div.track-home-div, div.view-track-div, div.update-track-div').addClass('hide');
		var selectAlbum = $('form#add-track-form').find('select.albumname');
		
		getAlbumDetails(selectAlbum);
	}
	else if($(this).hasClass('viewtrack')){ // View Track
		var viewTrackTbody = $('table.viewTrackTable').find('tbody#viewTrackBody');
		viewTrackDetails('N');
		$('div.view-track-div').removeClass('hide');
		$('div.track-home-div, div.add-track-div, div.update-track-div').addClass('hide');
	}
	else if($(this).hasClass('updatetrack')){ // Update Track
		$('div.update-track-div').removeClass('hide');
		$('div.update-delete-track-div').addClass('hide');
		viewTrackDetails('Y');
		$('div.update-track-div').find('#updateTracksAccordian').removeClass('hide');
		$('div.track-home-div, div.add-track-div, div.view-track-div').addClass('hide');
	}
	$(this).parents('li').siblings().removeClass('active');
});


$('div.update-delete-track-div').find('.cancel').on('click', function(){
	$('div.track-content').find('a.updatetrack').trigger('click');
});


function getAlbumDetails(selectAlbum){
	$.ajax({
		type : 'GET', 
		url : 'viewAlbum',
		success : function(response){
			var albumDetailsJson = $.parseJSON(response);
			var options = '<option value="--Select--"> --Select-- </option>';
			if(albumDetailsJson != null && albumDetailsJson.length > 0){
				$.each(albumDetailsJson, function(index, obj){
					options += '<option id=albumName'+obj.album_id +'" albumId="'+obj.album_id+'" value='+obj.album_name+'>' +obj.album_name +'</option>';
				});
			}
			$(selectAlbum).html(options);
		},
		error : function(response){
			$(selectAlbum).html('<option value="--Select--"> --Select-- </option>');
		}
	});
}


function addTrackDetails(){
	var formObj = $('form#add-track-form');
	$(formObj).find('.error-msg').addClass('hide').text('');
	$(formObj).find('.form-val-error-msg').remove();
	
	var albumName = $(formObj).find('.albumname').val().trim();
	var trackName = $(formObj).find('.trackname').val().trim();
	var artists = $(formObj).find('.artists').val().trim();
	var duration = $(formObj).find('.duration').val().trim();
	var sequenceNo = $(formObj).find('.sequenceno').val().trim();
	
	var albumId = $(formObj).find('select.albumname').find('option[value^='+albumName+']').attr('albumid');
	$(formObj).find('.albumid').val(albumId);
	
	if(albumId != null && albumId.length > 0 && albumName != null && albumName.length > 0 && trackName != null && trackName.length > 0 && 
			artists != null && artists.length > 0 && duration != null && duration.length > 0 && sequenceNo != null && sequenceNo.length > 0){
		$('div.add-track-div').find('.ms-loader').removeClass('hide');
		
		var isFormValid = validateFormAndShowError($(formObj).attr('id'));
		var isDurationValid = validateDuration(duration);
		
		if(isFormValid && isDurationValid){
			setTimeout(function(){
				$.ajax({
					type : 'POST',
					url : 'addTrack',
					data : $(formObj).serialize(),
					success : function(response){
						var status = response;
						$('div.add-track-div').find('.ms-loader').addClass('hide');
						status = (status != null && status != undefined && status == 'Y') ? 'Y' : 'N';
						showPopUp('add_track', status);
					},
					error : function(response){
						$('div.add-album-div').find('.ms-loader').addClass('hide');
						showPopUp('add_track', 'N');
					}
				});
			}, 2000);
		}
		else if(!isDurationValid){
			$(formObj).find('.duration').after('<span class="error-msg block form-val-error-msg padding-bottom-10" style="font-size: smaller;"> Invalid duration format. Eg 4:26 </span>');
		}
		$('div.add-track-div').find('.ms-loader').addClass('hide');
	}
	else{
		$(formObj).find('.error-msg').removeClass('hide').text('Fields marked with (*) are mandatory');
	}
}


function viewTrackDetails(showEdit){
	$.ajax({
		type : 'GET',
		url : 'viewTrack',
		success : function(response){
			if(response != null && response != undefined){
				let templateId = null, loaderId = null;
				if(showEdit != null && showEdit == 'N'){
					templateId = 'view-track-template';
					loaderId = $('div.view-track-div').find('#viewTracksAccordian');
				}else{
					templateId = 'update-track-template';
					loaderId = $('div.update-track-div').find('#updateTracksAccordian');
				}
				var contextObject = JSON.parse(response);
				
				// load view/update track template
				load_handlebar_template(templateId, contextObject, loaderId);
				
				if(showEdit != null && showEdit == 'Y'){
					$('#updateTracksAccordian').find('th.updateDeleteTrackTh, td.updateDeleteTrack').removeClass('hide');
				}
			}
		},
		error : function(response){
			
		}
	});
}


function editTrack(obj){
	var updateTrackParams = $(obj).parents('tbody[id^="viewTrackBody_"]').find('input.updateTrackParams');
	var albumId = $(updateTrackParams).attr('albumId').trim();
	var trackId = $(updateTrackParams).attr('trackId').trim();
	$('form#update-track-form').find('input.trackid').val(trackId);
	
	$('div.updateTracksAccordian').addClass('hide');
	$('div.update-delete-track-div').removeClass('hide');
	
	var albumName = $(updateTrackParams).attr('albumName').trim();
	var trackName = $(updateTrackParams).attr('trackName').trim();
	var artists = $(updateTrackParams).attr('artists').trim();
	var duration = $(updateTrackParams).attr('duration').trim();
	var sequenceNo = $(updateTrackParams).attr('sequenceno').trim();
	
	var formObj = $('form#update-track-form');
	$(formObj).find('input.albumid').val(albumId);
	$(formObj).find('input.trackid').val(trackId);
	$(formObj).find('input.trackname').val(trackName);
	$(formObj).find('input.artists').val(artists);
	$(formObj).find('input.duration').val(duration);
	$(formObj).find('input.sequenceno').val(sequenceNo);
	
	var selectAlbum = $('form#update-track-form').find('select.albumname');
	getAlbumDetails(selectAlbum);
	
	setTimeout(function(){
		$(formObj).find('select.albumname').val(albumName);	
	},200);
}


/** To update selected Album from Music Store
 * @returns
 */
function updateTrackDetails(){
	var formObj = $('form#update-track-form');
	$(formObj).find('.error-msg').addClass('hide').text('');
	$(formObj).find('.form-val-error-msg').remove();
	
	// var albumId = $(formObj).find('input.albumid').val();
	var albumName = $(formObj).find('select.albumname').val();
	var trackId = $(formObj).find('input.trackid').val();
	var trackName = $(formObj).find('input.trackname').val();
	var artists = $(formObj).find('input.artists').val();
	var duration = $(formObj).find('input.duration').val();
	var sequenceNo = $(formObj).find('input.sequenceno').val();
	
	var albumId = $(formObj).find('select.albumname').find('option[value^='+albumName+']').attr('albumid');
	$(formObj).find('.albumid').val(albumId);
	
	if(albumId != null && albumId.length > 0 && albumName != null && albumName.length > 0 && trackName != null && trackName.length > 0 && 
			artists != null && artists.length > 0 && duration != null && duration.length > 0 && sequenceNo != null && sequenceNo.length > 0){
		$('div.update-track-div').find('.ms-loader').removeClass('hide');
		
		var isFormValid = validateFormAndShowError($(formObj).attr('id'));
		var isDurationValid = validateDuration(duration);
		
		if(isFormValid && isDurationValid){
			setTimeout(function(){
				$.ajax({
					type : 'POST',
					url : 'updateTrack',
					data : $(formObj).serialize(),
					success : function(response){
						var status = response;
						$('div.update-track-div').find('.ms-loader').addClass('hide');
						status = (status != null && status != undefined && status == 'Y') ? 'Y' : 'N';
						showPopUp('update_track', status);
					},
					error : function(response){
						$('div.update-album-div').find('.ms-loader').addClass('hide');
						showPopUp('update_track', 'N');
					}
				});
			}, 2000);
		}
		else if(!isDurationValid){
			$(formObj).find('.duration').after('<span class="error-msg block form-val-error-msg padding-bottom-10" style="font-size: smaller;"> Invalid duration format. Eg 4:26 </span>');
		}else{
			$('div.update-track-div').find('.ms-loader').addClass('hide');
		}
	}
	else{
		$(formObj).find('.error-msg').removeClass('hide').text('Fields marked with (*) are mandatory');
	}
}


/** To delete selected Album from Music Store
 * @returns
 */
function deleteTrackDetails(){
	$('div.update-track-div').find('.ms-loader').removeClass('hide');
	var trackId = $('form#update-track-form').find('input.trackid').val();
	
	if(trackId != null){
		setTimeout(function(){
			$.ajax({
				type : 'POST',
				url : 'deleteTrack',
				async : false,
				data : {
					'trackid' : trackId
				},
				success : function(response){
					var status = response;
					$('div.update-track-div').find('.ms-loader').addClass('hide');
					status = (status != null && status != undefined && status == 'Y') ? 'Y' : 'N';
					showPopUp('delete_track', status);
				},
				error : function(response){
					$('div.update-track-div').find('.ms-loader').addClass('hide');
					showPopUp('delete_track', 'N');
				}
			});
		}, 1000);
	}
}


function validateDuration(duration){
	var isValidDuration = false;
	if(duration != null && duration.length > 0){
		let min = parseInt(duration.split(':')[0]);
		let sec = parseInt(duration.split(':')[1]);
		
		if(min >= 0 && min <= 60 && sec >= 0 && sec <= 60){
			isValidDuration = true;
		}
	}
	return isValidDuration;
}
</script>
