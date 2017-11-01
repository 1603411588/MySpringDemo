<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<c:url value='/static/bootstrap/css/bootstrap.min.css'/>"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value='/static/js/jquery.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/static/bootstrap/js/bootstrap.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/static/js/sockjs-0.3.4.min.js'/>"></script>
<script type="text/javascript"
	src="http://cdn.bootcss.com/stomp.js/2.3.3/stomp.js"></script>
</head>
<body>
	<textarea rows="20" cols="100" id="c1"></textarea>
	<br/>
	<button id="connect1" onclick="connect();">连接1</button>
	<br/>
	<input type="text" id="s1" style="width: 500px;height: 40px;"><button id="send1" onclick="send();">发送1</button>
	<script type="text/javascript">
		var stompClient;
		function connect() {
			var socket = new SockJS("/MySpringDemo/stomp/testHello");
			stompClient = Stomp.over(socket);//使用stomp子协议的WebSocket 客户端
			stompClient.connect({}, function(frame) {//链接Web Socket的服务端。
				console.log('Connected: ' + frame);
				stompClient.subscribe('/topic/testHello', function(response) {
					console.log(response.body);
					$('#c1').text($('#c1').text() + "\n" + response.body);
				});
			});
		}

		function send() {
			stompClient.send("/app/testHello",{}, $('#s1').val());
		}
	</script>
</body>
</html>