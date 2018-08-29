<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>

<link rel="stylesheet" href="css/validationEngine.jquery.css"
	type="text/css" />
<script src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine-en.js"></script>

</head>

<script type="text/javascript">
	$(document).ready(function()
	{

		$("#theaterOwnerFormId").validationEngine({scroll : false});
	
		$('#emailId').blur(function()
		{
			var email=$("#emailId").val().trim();
			if(email!=null && email!="")
				{
					var mailregex=/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
					if(mailregex.test(email))
				{
				
					jQuery.ajax({
					data	:	"email=" +email,
					url		:	"checkTheaterOwnerEmail",
					type	:	"POST",
					success:function(results)
					{
						$("#divId").fadeIn();
						$("#divId").html(results);

						var emailspanid=$('#emailSpanId').val();
						
						if(emailspanid!=1)
						{
							$("#btnId").attr("disabled", "disabled");
						}
						else
						{
							$("#btnId").removeAttr("disabled");
						}
					}
				});
			}
				}
		});
	
		
		
	});

</script>

<body>

<form action="theater-owner-registration-submit" method="post" id="theaterOwnerFormId">

<section class="function1" >
     <div class="container-fluid registration-body theatre-owner-bg"  >
        <div class="row " >
        <div class="col-md-4">
        </div>
        <div class="col-md-4 register-margintop" >
        <h4 class="fx-white text-center "><b>SIGN UP</b></h4>
        <input type="text" name="theaterOwnerBean.theater_owner_first_name" class="form-control space5 txt validate[required,custom[onlyLetterSp]]" placeholder="Name">
        <input type="text" id="emailId" name="theaterOwnerBean.theater_owner_email" class="form-control space5 txt validate[required,custom[email]]" placeholder="Email id">
        <span id="divId" style="color:#39ea39;"></span>
          <input type="text" name="theaterOwnerBean.theater_owner_mobile" class="form-control space5 validate[required,custom[mobile]]" placeholder="Mobile Number">
            <input type="password" name="theaterOwnerBean.passowrd" id="pwd" class="form-control space5 txt validate[required]" placeholder="Password">
              <input type="password" class="form-control space5 txt validate[required,equals[pwd]]" placeholder="Confirm Password">
            <h6 class="fx-white m-bot">
            <input type="checkbox" required>
            I Accept the Terms & Conditions </h6>
        <button id="btnId" type="submit" class="btn btn-success btn-block fx-primary-button" >Sign up</button>
      <p class="m-t-sm alreadymember">Already a member ? <a href="theater-owner-login" > Login </a> </p>
        </div>
      </div>
    </div>
  </section>

</form>

</body>
</html>