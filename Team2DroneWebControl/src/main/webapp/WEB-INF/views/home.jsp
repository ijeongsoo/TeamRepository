<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link
	href="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=application.getContextPath()%>/resources/jquery/jquery-3.2.1.min.js"
	type="text/javascript"></script>
<script
	src="<%=application.getContextPath()%>/resources/bootstrap-3.3.7/js/bootstrap.min.js"
	type="text/javascript"></script>
<style>
body{
    text-align:center;
}

#box{
    display:block;
    width:150px;
    margin:150px auto;
    padding:15px;
    text-align:center;
    border:7px solid blue;
    background:red;
    -webkit-transition: -webkit-transform 1.5s linear;
}
</style>
<script>
var cur = 0;

function doit() {
    var speed = +$("#controlo").val();
    cur = (cur + speed);
    $("#box").css("-webkit-transform", "rotate(" + cur + "deg)");
}

setInterval(doit, 100);
</script>
</head>


<body>
    <div id="box">
        <p id="text">Something goes here...</p>
    </div>
    <div id="control">
        <input id="controlo" type="range" min="0" max="50" step="5" value="25" />
    </div>
</body>
</html>








