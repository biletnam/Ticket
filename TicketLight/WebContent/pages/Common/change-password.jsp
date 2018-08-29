<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>

<script src="js/jquery-1.9.1.js"></script>

<link rel="stylesheet" type="text/css" href="css/validationEngine.jquery.css"/>

<script src="js/jquery.validationEngine-en.js"></script>
<script src="js/jquery.validationEngine.js"></script> 


 <script type="text/javascript">

$(document).ready(function()
{
	$("#oldPassword").on('blur',function() 
	{
		$('#changePasswordFormId').validationEngine({scroll : false});
		var old_password = $("#oldPassword").val().trim(); 
		if (old_password != null && old_password != "") 
		{
				jQuery
						.ajax({
							data : "oldPassword="
									+ old_password,
							url : "checkOldPasswordAvailable",
							type : "POST",
							success : function(results) {
								if (results == 'misMatch')
								{
									$("#MsgDiv").css('color','red');
									$("#MsgDiv").html("MisMatched Old Password. Please Enter Correct Old Password! ");
									$('#submitId').attr('disabled','true');
								} 
								else 
								{
									$("#MsgDiv").css('color','green');
									$("#MsgDiv").html("Old Password Is Matched");
									$('#submitId').removeAttr('disabled');
								}
							}
						});
		}
	});
});
</script> 
</head>
<body>



<div id="MsgDiv"></div>
           <div class="row wrapper border-bottom white-bg page-heading">
                 <div class="col-lg-10">
                
                <ol class="breadcrumb m-t-xs m-b-xs">
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">
                        <a href="change-password">Change Password</a>
                    </li>
                </ol>
            </div>
                <div class="col-lg-2">

                </div>
            </div>
        <div class="wrapper wrapper-content">
         <div class="col-md-6 col-md-offset-3">
          <div class="row">
           <form action="change-password-submit" method="post" id="changePasswordFormId">
            <div class="form-group text-center">
           <label><h2><b>Change Password</b></h2></label> 
         </div>
    <div class="form-group">
      <input type="password" name="theaterOwnerBean.old_password" id="oldPassword" class="form-control validate[required]" placeholder="Old Password">
    </div>
    <div class="form-group">
      <input type="password" id="pwd" name="theaterOwnerBean.password" class="form-control validate[required]" placeholder="New password">
    </div>
    <div class="form-group">
      <input type="password"  class="form-control validate[required,equals[pwd]]" placeholder="Conform New password">
    </div>
    <button id="submitId" type="submit" class="btn btn-primary btn-lg btn-block">Submit</button>
  </form>
</div>
</div>
</div>
</div>




</body>
</html>