<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="/struts-tags" prefix="s" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/validationEngine.jquery.css"
	type="text/css" />
<script src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine-en.js"></script>

</head>

<script type="text/javascript">
$(document).ready(function()
	{
		$("#loginSubmitId").validationEngine({scroll : false});
		
		if (localStorage.chkbx && localStorage.chkbx != '') {
	        $('#rememberMe').attr('checked', 'checked');
	        $('#emailId').val(localStorage.usrname);
	        $('#passwordId').val(localStorage.pass);
	    } else {
	        $('#rememberMe').removeAttr('checked');
	        $('#emailId').val('');
	        $('#passwordId').val('');
	    }
		
		$('#loginFormId').click(function()
		{
 			  if ($('#rememberMe').is(':checked')) 
 			  {
		            localStorage.usrname = $('#emailId').val();
		            localStorage.pass = $('#passwordId').val();
		            localStorage.chkbx = $('#rememberMe').val();
		      } 
 			  else 
 			  {
		            localStorage.usrname = '';
		            localStorage.pass = '';
		            localStorage.chkbx = '';
		      }
		});
                 
	});
</script>

<body>

  <form action="ticket-counter-person-login-submit" method="post" id="loginSubmitId">
  <s:hidden name = "loginBean.role_xid" value="2"/>
  	
    <section class="function1" >
       <div class="container-fluid login-body ticket-counter-bg"  >
       						
         <div class="row " >
          <div class="col-md-4">
         </div>
          <div class="col-md-4 login-margintop" >
         <h4 class="text-center fx-white"><b>TICKET COUNTER LOGIN</b></h4> 
         <s:if test="hasActionMessages()">
										<s:iterator value="ActionMessages">
										<span class="msg otp-page-msg" style="color:#39ea39;margin-left: 16%;"><s:property escape="false"/></span>
										</s:iterator>
										</s:if> 
										<s:if test="hasActionErrors()">
										<s:iterator value="ActionErrors">
										<span class="msg otp-page-msg" style="color:#e80963;margin-left: 20%;"><s:property escape="false"/></span>
										</s:iterator>
							</s:if>  
          <input  id="emailId" type="text" class="form-control space5 validate[required,custom[email]]" placeholder="User name" name="loginBean.email">
           <input id="passwordId" type="password" class="form-control space5 validate[required]" placeholder="Password" name="loginBean.password">
        <p class="fx-white"><input id="rememberMe" type="checkbox" class="checkbox"><b> Remember Me</b>
             <span><a href="ticket-counter-forget-password" class="forgot pull-right" > Forgot Password ? </a> </span></p>
            <button id="loginFormId" type="submit" class="btn btn-primary btn-block fx-primary-button" style="">Login to proceed</button>
            </div>
          </div>
        </div>
     </section>
     
  </form>
  
</body>
</html>