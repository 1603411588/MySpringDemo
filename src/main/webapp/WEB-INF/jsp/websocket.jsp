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
<script type="text/javascript" src="<c:url value='/static/js/sockjs-0.3.4.min.js'/>"></script>
</head>
<body>
	<script type="text/javascript">
		//var url = "ws://" + window.location.host + "/MySpringDemo/websocket/WebsocketTest";
		//var socket = new WebSocket(url);
		var url = "http://localhost:8080/MySpringDemo/websocket/WebsocketTest";
		var socket = new SockJS(url);
		socket.onopen = function(){
			console.log(" connection open...");
			sayHello();
		}
		
		socket.onmessage = function(e){
			console.log(" client receive " + e.data + " from server");
			sayHello();
		}
		
		socket.onclose = function(){
			console.log(" connection close...");
		}
		
		function sayHello(){
			console.log(" client say hello to server.....");
			socket.send(" client say hello to server.....");
		}
	</script>
</body>
</html>