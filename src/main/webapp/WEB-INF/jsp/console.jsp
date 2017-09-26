<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value='/static/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/static/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/static/bootstrap/js/bootstrap.min.js'/>"></script>

<script type="text/javascript">
	function doResolve() {
		var content = $("#scriptContent").val();
		$.ajax({
			type : "POST",
			url : " <c:url value='/console/eval' />",
			data : {
				"scriptContent" : content
			},
			success : function(msg) {
				$("#scriptResult").val(msg);
			},
			error : function(msg) {
				$("#scriptResult").val(msg.responseText);
			}
		});
	}
</script>
</head>
<body>

	<h1>console</h1>
	<form role="form">
		<textarea class="form-control" rows="20" cols="100" id="scriptContent"></textarea>
		<br /> <input class="btn btn-default" type="button" value="解析" id="resolve"
			onclick="doResolve();"> <input class="btn btn-default" type="button" value="清空"
			id="clearResult" onclick="javascript:$('#scriptResult').val('');">
		<br /> <br />
		<textarea class="form-control" rows="20" cols="100" id="scriptResult"></textarea>
		<br />
	</form>
</body>
</html>