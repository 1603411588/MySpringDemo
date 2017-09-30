<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload</title>
<link href="<c:url value='/static/bootstrap/css/bootstrap.min.css'/>"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value='/static/js/jquery.min.js'/>"></script>
<body>
<p></p>
<form id="fo" enctype="multipart/form-data" role="form">
	<input type="file" name="file"><br/>
	<input type="text" name="desc"><br/>
	<input type="button" class="btn btn-default" value="upload" onclick="doUpload();">
</form>
<script type="text/javascript">
function doUpload() {  
	var url = "<c:url value='/hello/upload'/>";
    var formData = new FormData(document.getElementById("fo"));  
    $.ajax({  
         url: url ,  
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (returndata) {  
             alert(returndata);  
         },  
         error: function (returndata) {  
             alert(returndata);  
         }  
    });  
}
</script>
</body>
</html>