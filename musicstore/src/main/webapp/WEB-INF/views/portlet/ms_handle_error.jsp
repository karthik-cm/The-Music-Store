<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertAttribute name="ms_libs" />

<div id="handle-error-content">
	
	<div style="padding: 60px;">
		<h4>Note :-</h4>
		<h4>This page is not available / some error occurred. Please check and try after sometime</h4>
		<h4>Go to <a class="underline" href="home">Home</a> OR Press back arrow to go to previously visited page</h4>
	</div>

</div>

<div style="margin-top: 175px;">
	<tiles:insertAttribute name="ms_footer" />
</div>