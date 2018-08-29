<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib uri="/struts-tags" prefix="s" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forget password</title>

<link rel="stylesheet" href="css/validationEngine.jquery.css"
	type="text/css" />
<script src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine-en.js"></script>

<script type="text/javascript">
	$(document).ready(function()
	{
		$("#forgetFormId").validationEngine({scroll : false});

	});
</script>
</head>
<body>

 <s:form action ="forget-password-submit" method="post" id="forgetFormId">   
 <s:hidden name = "loginBean.role_xid" value="2"/>
      
<section class="function1" >
         <div class="container-fluid forgot-body ticket-counter-bg"  >
         
            <div class="row " >
              <div class="col-md-4">
              </div>
              <div class="col-md-4 forgot-margintop" >
                <h4 class="text-center fx-white"><b>TICKET COUNTER PERSON FORGOT PASSWORD</b></h4>
                <label class="color-concept text-center" style="margin-left:-4%;"> Enter your e-mail address below to reset your password.</label>
              <s:if test="hasActionMessages()">
<s:iterator value="ActionMessages">
<span class="msg" style="color:#39ea39;margin-left:15%;"><s:property escape="false"/></span>
</s:iterator>
</s:if> 
<s:if test="hasActionErrors()">
<s:iterator value="ActionErrors">
<span class="msg"  style="color:#e80963;margin-left:15%;"><s:property escape="false"/></span>
</s:iterator>
</s:if>
              <input name="loginBean.email" type="text" class="form-control space5 m-bot validate[required,custom[email]]" placeholder="Email id">
        <button type="submit" class="btn btn-success btn-block fx-primary-button" >Submit </button>
      </div>
      </div>
    </div>
</section>   
 </s:form>
 
</body>
</html>