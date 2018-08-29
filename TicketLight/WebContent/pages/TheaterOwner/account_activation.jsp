<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account activation</title>
<link rel="stylesheet" href="css/validationEngine.jquery.css"
	type="text/css" />
<script src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine-en.js"></script>

</head>

<script type="text/javascript">

	$(document).ready(function(){
		
		$("#activationFormId").validationEngine({scroll : false});

		$("#smsresendLinkId").on('click',function(){
			
			var owner_id = $("#ownerId").val();
			
			jQuery.ajax({
				data	:	"owner_id=" +owner_id,
				url		:	"resendConfirmSMS",
				type	:	"POST",
				success:function(results)
				{
					
					$("#errorSpanId").text("A Pin has been sent to your Email Id");
					$("#errorSpanId").fadeOut(5000);
					$('#actionMsgDiv').hide();
					$('#msgDivId').show();
				}
			});
		});
		
	$("#mailresendLinkId").on('click',function(){
			
			var owner_id = $("#ownerId").val();
			
			jQuery.ajax({
				data	:	"owner_id=" +owner_id,
				url		:	"resendConfirmMail",
				type	:	"POST",
				success:function(results)
				{
					
					$("#errorSpanId").text("A Pin has been sent to your Email Id");
					$("#errorSpanId").fadeOut(5000);
					$('#actionMsgDiv').hide();
					$('#msgDivId').show();
				}
			});
		});
		
	});
</script>
<body>

<form action="account-activation" method="post" id="activationFormId">
<s:hidden name="theaterOwnerBean.theater_owner_id" id="ownerId"/>


  <section class="function1" >
     <div class="container-fluid forgot-body theatre-owner-bg"  >
    
	<div id="msgDivId" style="display: none;">
	<span style="color:#e80963;" id="errorSpanId"></span>
	</div>
        <div class="row " >
        <div class="col-md-4">
        </div>
         
        <div class="col-md-4 forgot-margintop" >
        <h4 class="fx-white text-center"><b>ACCOUNT ACTIVATION</b></h4>
        <div id="actionMsgDiv">
			<s:if test="hasActionMessages()">
										<s:iterator value="ActionMessages">
										<span class="msg otp-page-msg" style="color:#39ea39;margin-left: -1%;text-align:center;"><s:property escape="false"/></span>
										</s:iterator>
										</s:if> 
										<s:if test="hasActionErrors()">
										<s:iterator value="ActionErrors">
										<span class="msg otp-page-msg" style="color:#e80963;margin-left: 20%;"><s:property escape="false"/></span>
										</s:iterator>
										
			</s:if>
	</div>
           <s:if test="%{theaterOwnerBean.mail_pin_status == 'not_success'}">
               	<input type="password" placeholder="Enter your Email OTP " class="form-control space5 validate[required]" name="theaterOwnerBean.mail_pin">
               </s:if>
               <s:elseif test="%{theaterOwnerBean.mail_pin_status == 'success'}">
               successfully updated..!
               </s:elseif>
        <p class="linkdecor fx-white"><a style="cursor: pointer;" id="mailresendLinkId"> Resend mail OTP </a></p>
            	<s:if test="%{theaterOwnerBean.sms_pin_status == 'not_success'}">
               <input type="password" placeholder="Enter your mobile OTP" class="form-control space5 validate[required]" name="theaterOwnerBean.sms_pin">
               </s:if>
               <s:elseif test="%{theaterOwnerBean.sms_pin_status == 'success'}">
               Successfully updated.!
               </s:elseif>
             <p  class="linkdecor m-bot fx-white"><a style="cursor: pointer;"  id="smsresendLinkId" > Resend mobile OTP </a></p>
          <button type="submit" class="btn btn-success btn-block fx-primary-button"><b>Submit</b></button>
        </div>
        </div>
    </div>
    </section>

</form>
</body>
</html>