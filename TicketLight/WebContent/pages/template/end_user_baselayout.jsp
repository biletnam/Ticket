<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!-- Favicons -->
 <!--  <link href="img/favicon.png" rel="icon"> -->
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Bootstrap CSS File -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/validationEngine.jquery.css"/>

  <!-- Libraries CSS Files -->
  <link href="lib/animate/animate.min.css" rel="stylesheet">
  <link href="css/font-awesome-animation.min.css" rel="stylesheet">
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
 <link rel="stylesheet" href="Dynamic/js/plugins/SingleAutoComplete/jquery.autocomplete.css" type="text/css" /> 
  
  <!-- Main Stylesheet File -->
  <link href="css/style.css" rel="stylesheet">
 


</head>
<body>

<tiles:insertAttribute name="header" /> 
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />


  <script src="src/jR3DCarousel.js"></script>
  <script src="lib/wow/wow.min.js"></script>
  <script src="lib/superfish/superfish.min.js"></script>
  <script src="js/main.js"></script>
  

<script src="js/jquery.validationEngine-en.js"></script>
<script src="js/jquery.validationEngine.js"></script> 
<script src="Dynamic/js/plugins/SingleAutoComplete/jquery.autocomplete.js" type="text/javascript" charset="utf-8"></script> 
 

</body>
</html>