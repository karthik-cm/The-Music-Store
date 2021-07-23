<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertAttribute name="ms_libs" />


<!-- Dashboard page Content -->
<div id="ms-dashboard-content" class="ms-dashboard-content" style="width: 100%;">
	<!-- Header -->
	<tiles:insertAttribute name="ms_header" />
	
	
	<!-- Music Store - Content -->
	<div class="ms-content-div">
		<marquee class="bold" style="font-size: large; padding-top: 15px;"> Welcome to Music Store ! </marquee>
	 
	 	<!-- Music Store : Functions -->
		<div id="ms-functions" class="ms-functions">
			<div style="display: flex;">
			  	<div class="card" style="padding-right: 75px;">
			  		<img class="card-img-top" src="/musicstore/ui-resources/images/dashboard/album_track.png" alt="Music" style="height: 100px; width: 120px;">
				 		<div class="card-body">
				    		<a href="#" class="btn btn-primary" onclick="goToAlbum(1)"> Go to Album </a>
				  		</div>
			 	</div>
			 	
			 	<div class="card" style="padding-right: 75px;">
			  		<img class="card-img-top" src="/musicstore/ui-resources/images/dashboard/mp3.jpeg" alt="Music" style="height: 100px; width: 120px;">
				 		<div class="card-body">
				    		<a href="#" class="btn btn-primary" onclick="goToTrack(1)"> Go to Track </a>
				  		</div>
			 	</div>
			 
			 	<div class="card" style="padding-right: 75px;">
			  		<img class="card-img-top" src="/musicstore/ui-resources/images/dashboard/files2.jpeg" alt="Music" style="height: 100px; width: 120px;">
				 		<div class="card-body">
				    		<a href="#" class="btn btn-primary" onclick="goToFile()"> Go to Files </a>
				  		</div>
			 	</div>
		 	</div>
		</div>
		
		
		<!-- Profile Details -->
		<!-- <div id="profile-details-portlet"></div>
		
		
		Album
		<div id="album-portlet"></div>
		
		
		Track
		<div id="track-portlet"></div> -->
		
		
		<!-- Image Loader -->
		<img class="ms-loader align-center hide" style="padding: 0px 0px 20px 555px; border-radius: 60%;" alt="Loading..." src="ui-resources/gifs/loading.gif">
		 
	</div>


 	<!-- Contents -->
 	<tiles:insertAttribute name="ms_contents" />
 	
 	
	<!-- Footer -->
	<tiles:insertAttribute name="ms_footer" />
 
</div>
