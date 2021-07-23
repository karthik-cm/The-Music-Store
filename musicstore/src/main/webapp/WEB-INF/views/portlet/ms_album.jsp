<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertAttribute name="ms_libs" />


<div class="album-content" style="width:100%;">
	<tiles:insertAttribute name="ms_header" />
	
	<div class="ms-content-div ms-album-content-div" style="padding: 60px;">
		<h3 style="text-decoration: underline;"><b> ALBUM </b></h3>
	
		<!-- Album Navigation -->
		<ul class="nav nav-tabs album-nav-tabs" style="padding-top: 25px; width: 80%;">
			<li class="active">
				<a href="#" class="albumhome bold"> Home </a>
			</li>
			<li>
				<a href="#" class="addalbum bold"> Add Album </a>
			</li>
			<li>
				<a href="#" class="viewalbum bold"> View Album </a>
			</li>
			<li>
				<a href="#" class="updatealbum bold"> Update Album </a>
			</li>
		</ul>
	
	
		<div class="main-album-home-div" style="padding-bottom: 100px;">
			<!-- Album Home -->
			<div class="album-home-div" style="padding-top: 20px; padding-bottom: 50px;">
				<p class="margin-bottom-15"> This is Album section. It has mainly 3 sections : </p>
				<ol>
					<li class="margin-bottom-10"> Add Album : Here you can add your Album details.
					<li class="margin-bottom-10"> View Album : Here you can view your Album details.
					<li class="margin-bottom-10"> Update Album : Here you can update your Album details.
				</ol>
			</div>
			
		
			<!-- Add Album --> 
			<div class="add-album-div hide" style="width: 50%; margin: 30px 50px 25px 15px;">
				<h5 class="bold margin-bottom-25 underline"> Create new Album by filling below details :- </h5>
			
				<div class="ms-form-div">
					<form id="add-album-form" name="add-album-form" class="add-album-form ms-form">
						<div class="margin-bottom-15 error-msg hide"></div>
						
						<div class="margin-bottom-20">
						   	<label for="nameofalbum"><b> Name Of Album </b> <span class="red-asterisk">*</span></label>
						   	<input type="text" name="nameofalbum" class="nameofalbum" ctype="AN">
					   	</div>
					    
					    <div class="margin-bottom-20">
						   	<label for="genre"><b> Genre </b> <span class="red-asterisk">*</span></label>
						   	<select name="genre" class="genre select" ctype="AN">
					   			<option value="--Select--"> --Select-- </option>
					   			<option value="Blues"> Blues </option>
					   			<option value="Classical"> Classical </option>
					   			<option value="Country"> Country </option>
					   			<option value="Electronic"> Electronic </option>
					   			<option value="Folk"> Folk </option>
					   			<option value="Hip-hop"> Hip-hop </option>
					   			<option value="Jazz"> Jazz </option>
					   			<option value="Rock"> Rock </option>
					   			<option value="Rock"> Other </option>
						   	</select>
						</div>
					    
						<div class="margin-bottom-20">
						   	<label for="musicby"><b> Music By </b> <span class="red-asterisk">*</span></label>
						   	<input type="text" name="musicby" class="musicby" ctype="AN">
						</div>
					    
					    <div class="margin-bottom-20">
						   	<label for="yearofrelease"><b> Year of Release </b> <span class="red-asterisk">*</span></label>
						   	<input type="text" name="yearofrelease" class="yearofrelease" maxlength="4" ctype="NI">
						</div>
					    
					    <div class="align-center" style="padding-top: 35px;">
						    <button type="button" class="btn btn-success" onclick="addAlbumDetails()" style="width: 20%;"> Add Album </button>
						    <button type="reset" class="btn btn-info clear" style="width: 20%; margin-left: 20px;"> Clear </button>
					    	<button type="button" class="btn btn-danger close-signup-popup" style="width: 20%; margin-left: 20px;"> Cancel </button>
					    </div>
				 	</form>
			 	</div>
			 	
			 	<div class="ms-loader align-center hide" style="margin-top: 20px;">
					<img alt="Loading..." src="ui-resources/gifs/loading.gif">
				</div>
			</div>
			
			
			<!-- View Album -->
			<div class="view-album-div hide" style="width: 85%; margin: 30px 50px 25px 10px;">
				<h5 class="bold margin-bottom-25 underline"> Album details :- </h5>
			
				<div id="viewAlbumTableDiv" class="viewAlbumTableDiv">
					<table id="viewAlbumTable" class="table table-bordered table-striped ms-table viewAlbumTable">
						<thead>
							<tr>
								<th> Serial No </th>
								<th> Album Name </th>
								<th> Genre </th>
								<th> Music By </th>
								<th> Year of Release </th>
								<th> Created Date </th>
							</tr>
						</thead>
						
						<tbody id="viewAlbumBody">
						</tbody>
					</table>
				</div>
			</div>
			
			
			<!-- Update Album -->
			<div class="update-album-div hide" style="width: 90%; margin: 30px 50px 25px 10px;">
				<h5 class="bold margin-bottom-25 underline"> Update / Delete required Album fields by clicking on 'Edit' option :- </h5>
				
				<div id="updateAlbumTableDiv" class="updateAlbumTableDiv">
					<table id="updateAlbumTable" class="table table-bordered table-striped ms-table updateAlbumTable">
						<thead id="updateAlbumThead">
							<tr>
								<th> Serial No </th>
								<th> Album Name </th>
								<th> Genre </th>
								<th> Music By </th>
								<th> Year of Release </th>
								<th> Created Date </th>
								<th> Update / Delete </th>
							</tr>
						</thead>
						
						<tbody id="updateAlbumBody">
						</tbody>
					</table>
				</div>
				
				<div class="update-delete-album-div ms-form-div hide" style="width: 65%; margin: 30px 50px 25px 15px;">
					<form id="update-album-form" name="update-album-form" class="ms-form">
						<div class="margin-bottom-15 error-msg hide"></div>
						<input type="hidden" name="albumid" class="albumid">
						
						<div class="margin-bottom-20">
						   	<label for="nameofalbum"><b> Name Of Album </b> <span class="red-asterisk">*</span></label>
						   	<input type="text" name="nameofalbum" class="nameofalbum" ctype="AN">
						</div>
					    
					    <div class="margin-bottom-20">
						   	<label for="genre"><b> Genre </b> <span class="red-asterisk">*</span></label>
						   	<select name="genre" class="genre select" ctype="AN">
					   			<option value="--Select--"> --Select-- </option>
					   			<option value="Blues"> Blues </option>
					   			<option value="Classical"> Classical </option>
					   			<option value="Country"> Country </option>
					   			<option value="Electronic"> Electronic </option>
					   			<option value="Folk"> Folk </option>
					   			<option value="Hip-hop"> Hip-hop </option>
					   			<option value="Jazz"> Jazz </option>
					   			<option value="Rock"> Rock </option>
					   			<option value="Rock"> Other </option>
						   	</select>
						</div>
					    
					    <div class="margin-bottom-20">
						   	<label for="musicby"><b> Music By </b> <span class="red-asterisk">*</span></label>
						   	<input type="text" name="musicby" class="musicby" ctype="AN">
						</div>
					    
					    <div class="margin-bottom-20">
						   	<label for="yearofrelease"><b> Year of Release </b> <span class="red-asterisk">*</span></label>
						   	<input type="text" name="yearofrelease" class="yearofrelease" maxlength="4" ctype="NI">
						</div>
					    
					    <div class="align-center" style="padding-top: 35px;">
						    <button type="button" class="btn btn-success" onclick="updateAlbumDetails()"> Update </button>
					    	<button type="button" class="btn btn-info cancel" style="margin-left: 20px;"> Cancel </button>
					    	<button type="button" class="btn btn-danger" style="margin-left: 20px;" onclick="deleteAlbumDetails()"> Delete </button>
					    </div>
			 		</form>
				</div>
				
				<div class="ms-loader align-center hide" style="width: 68%; margin-top: 20px;">
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


<script>

$('ul.album-nav-tabs').find('li > a').on('click', function(){
	$(this).parents('li').addClass('active');
	$(this).parents('li').siblings().find('a').css({'color':'black'});
	$(this).css({'color':'#337ab7'});
	
	if($(this).hasClass('albumhome')){ // Album Home
		$('div.album-home-div').removeClass('hide');
		$('div.add-album-div, div.view-album-div, div.update-album-div').addClass('hide');
	}
	else if($(this).hasClass('addalbum')){ // Add Album
		$('div.add-album-div').removeClass('hide');
		$('div.album-home-div, div.view-album-div, div.update-album-div').addClass('hide');
	}
	else if($(this).hasClass('viewalbum')){ // View Album
		var viewAlbumTbody = $('table.viewAlbumTable').find('tbody#viewAlbumBody');
		viewAlbumDetails(viewAlbumTbody, 'N');
		$('div.view-album-div').removeClass('hide');
		$('div.album-home-div, div.add-album-div, div.update-album-div').addClass('hide');
	}
	else if($(this).hasClass('updatealbum')){ // Update Album
		$('div.update-delete-album-div').addClass('hide');
		$('div.updateAlbumTableDiv').removeClass('hide');
		var updateAlbumTbody = $('table.updateAlbumTable').find('tbody#updateAlbumBody');
		viewAlbumDetails(updateAlbumTbody, 'Y');
		$('div.update-album-div').removeClass('hide');
		$('div.album-home-div, div.add-album-div, div.view-album-div').addClass('hide');
	}
	$(this).parents('li').siblings().removeClass('active');
});


$('div.update-delete-album-div').find('.cancel').on('click', function(){
	$('div.update-delete-album-div').addClass('hide');
	$('div.updateAlbumTableDiv').removeClass('hide');
});



/** To add given album to Music Store
 * @returns
 */
function addAlbumDetails(){
	var formObj = $('form#add-album-form');
	$(formObj).find('.error-msg').addClass('hide').text('');
	
	var nameOfAlbum = $(formObj).find('.nameofalbum').val().trim();
	var genre = $(formObj).find('.genre').val().trim();
	var musicBy = $(formObj).find('.musicby').val().trim();
	var yearOfRelease = $(formObj).find('.yearofrelease').val().trim();
	
	if(nameOfAlbum != null && nameOfAlbum.length > 0 && genre != null && genre.length > 0 && musicBy != null && musicBy.length > 0 
			&& yearOfRelease != null && yearOfRelease.length > 0){
		$('div.add-album-div').find('.ms-loader').removeClass('hide');
		$('div.add-album-popup').removeClass('hide');
		
		var isFormValid = validateFormAndShowError($(formObj).attr('id'));
		if(isFormValid){
			setTimeout(function(){
				$.ajax({
					type : 'POST',
					url : 'addAlbum',
					data : $(formObj).serialize(),
					success : function(response){
						var status = response;
						$('div.add-album-div').find('.ms-loader').addClass('hide');
						status = (status != null && status != undefined && status == 'Y') ? 'Y' : 'N';
						showPopUp('add_album', status);
					},
					error : function(response){
						$('div.add-album-div').find('.ms-loader').addClass('hide');
						showPopUp('add_album', 'N');
					}
				});
			}, 2000);
		}
		else{
			$('div.add-album-div').find('.ms-loader').addClass('hide');
		}
	}
	else{
		$(formObj).find('.error-msg').removeClass('hide').text('Fields marked with (*) are mandatory');
	}
}


/** To view Album from Music Store
 * @param tbodyDiv
 * @param showEdit
 * @returns
 */
function viewAlbumDetails(tbodyDiv, showEdit){
	$.ajax({
		type : 'GET',
		url : 'viewAlbum',
		success : function(response){
			var albumDetailsJson = $.parseJSON(response);
			var trTdObj = '';
			
			if(albumDetailsJson != null && albumDetailsJson.length > 0){
				$.each(albumDetailsJson, function(index, obj){
					trTdObj += '<tr class="row_'+parseInt(index+1)+'" albumId="'+obj.album_id+'">';
					trTdObj += '<td>' +parseInt(index+1) +'</td>';
					trTdObj += '<td id=albumName value='+obj.album_name+'>' +obj.album_name +'</td>';
					trTdObj += '<td id=genre value='+obj.genre+'>' +obj.genre +'</td>';
					trTdObj += '<td id=musicBy value='+obj.music_by+'>' +obj.music_by +'</td>';
					trTdObj += '<td id=yearOfRelease value='+obj.year_of_release+'>' +obj.year_of_release +'</td>';
					trTdObj += '<td>' +obj.created_date +'</td>';
					
					if(showEdit != null && showEdit == 'Y'){
						trTdObj += '<td class="updateDeleteAlbum cursor-pointer underline" id="updateDeleteAlbum_'+parseInt(index+1)+'" onclick="editAlbum($(this))"> Edit </td>';
					}
					trTdObj += '</tr>';
				});
			}
			else{
				trTdObj = '<tr> <td class="align-center bold" colspan="6"> No records found </tr>';
			}
			
			$(tbodyDiv).html(trTdObj);
			var tableId = (showEdit == 'Y') ? 'updateAlbumTable' : 'viewAlbumTable' ;
			applyPagination(tableId);
		},
		error : function(response){
			trTdObj = '<tr> <td class="align-center bold" colspan="7"> No records found </tr>';
		}
	});
}


/** To edit selected Album from Music Store
 * @param obj
 * @returns
 */
function editAlbum(obj){
	var parentTr = $(obj).parents('tr');
	var albumId = $(parentTr).attr('albumid');
	$('form#update-album-form').find('input.albumid').val(albumId);
	
	$('div.updateAlbumTableDiv').addClass('hide');
	$('div.update-delete-album-div').removeClass('hide');
	
	var albumName = $(parentTr).find('td#albumName').text();
	var genre = $(parentTr).find('td#genre').text();
	var musicBy = $(parentTr).find('td#musicBy').text();
	var yearOfRelease = $(parentTr).find('td#yearOfRelease').text();
	
	$('form#update-album-form').find('input.nameofalbum').val(albumName);
	$('form#update-album-form').find('select.genre').val(genre);
	$('form#update-album-form').find('input.musicby').val(musicBy);
	$('form#update-album-form').find('input.yearofrelease').val(yearOfRelease);
	
	$('form#update-album-form').find('input.nameofalbum').trigger('click');
}


/** To update selected Album from Music Store
 * @returns
 */
function updateAlbumDetails(){
	$(formObj).find('.error-msg').addClass('hide').text('');
	var formObj = $('form#update-album-form');
	var nameOfAlbum = $(formObj).find('.nameofalbum').val().trim();
	var genre = $(formObj).find('.genre').val().trim();
	var musicBy = $(formObj).find('.musicby').val().trim();
	var yearOfRelease = $(formObj).find('.yearofrelease').val().trim();
	
	if(nameOfAlbum != null && nameOfAlbum.length > 0 && genre != null && genre.length > 0 && musicBy != null && musicBy.length > 0 
			&& yearOfRelease != null && yearOfRelease.length > 0){
		$('div.update-album-div').find('.ms-loader').removeClass('hide');
		
		var isFormValid = validateFormAndShowError($(formObj).attr('id'));
		if(isFormValid){
			setTimeout(function(){
				$.ajax({
					type : 'POST',
					url : 'updateAlbum',
					async : false,
					data : $(formObj).serialize(),
					success : function(response){
						var status = response;
						$('div.update-album-div').find('.ms-loader').addClass('hide');
						status = (status != null && status != undefined && status == 'Y') ? 'Y' : 'N';
						showPopUp('update_album', status);
					},
					error : function(response){
						$('div.update-album-div').find('.ms-loader').addClass('hide');
						showPopUp('update_album', 'N');
					}
				});
			}, 2000);
		}
		else{
			$('div.add-album-div').find('.ms-loader').addClass('hide');
		}
	}
	else{
		$(formObj).find('.error-msg').removeClass('hide').text('Fields marked with (*) are mandatory');
	}
}


/** To delete selected Album from Music Store
 * @returns
 */
function deleteAlbumDetails(){
	$('div.update-album-div').find('.ms-loader').removeClass('hide');
	var albumId = $('form#update-album-form').find('input.albumid').val();
	
	if(albumId != null){
		setTimeout(function(){
			$.ajax({
				type : 'POST',
				url : 'deleteAlbum',
				async : false,
				data : {
					'albumid' : albumId
				},
				success : function(response){
					var status = response;
					$('div.update-album-div').find('.ms-loader').addClass('hide');
					status = (status != null && status != undefined && status == 'Y') ? 'Y' : 'N';
					showPopUp('delete_album', status);
				},
				error : function(response){
					$('div.update-album-div').find('.ms-loader').addClass('hide');
					showPopUp('delete_album', 'N');
				}
			});
		}, 2000);
	}
}

</script>
