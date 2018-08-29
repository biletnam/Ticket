<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="shortcut icon" href="img/favicon.png">
    <link href="Dynamic/css/bootstrap.min.css" rel="stylesheet">
    <link href="Dynamic/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="Dynamic/css/animate.css" rel="stylesheet">
    <link href="Dynamic/css/style.css" rel="stylesheet">
</head>
<body>

<tiles:insertAttribute name="header" /> 
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />


<!-- Mainly scripts -->
<script src="Dynamic/js/bootstrap.min.js"></script>
<script src="Dynamic/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="Dynamic/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

 
<!-- Custom and plugin javascript -->
<script src="Dynamic/js/inspinia.js"></script>
<script src="Dynamic/js/plugins/pace/pace.min.js"></script>


</body>
</html>