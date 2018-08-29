<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-1.9.1.js"></script>

<style type="text/css">
/* 
input[type=text] {
    width: 70%;
    box-sizing: border-box;
    border: 1px solid ;
    border-radius: 4px;
    font-size: 16px;
    background-color: white;
    background-position: 10px 10px; 
    background-repeat: no-repeat;
    padding: 4px 10px 4px 40px;
    -webkit-transition: width 0.4s ease-in-out;
    transition: width 0.4s ease-in-out;

} */

.fx-broad{
  width: 100%!important;
}
.fx-close{
      margin-top: -40px;
          margin-right: 10px;
    color: #ffffff;
}
.fx-close:hover{
  color: #fb0051;
}
</style>
 <style>
    .ui-autocomplete-category {
        font-weight: bold;
        padding: .2em .4em;
        margin: .8em 0 .2em;
        line-height: 1.5;
    }
    
    
.fx-broad{
  width: 100%!important;
}
.fx-close{
      margin-top: -40px;
          margin-right: 10px;
    color: #ffffff;
}
.fx-close:hover{
  color: #fb0051;
}
.fx-select{
      top: -15px;
}
.fx-select1{
      top: 18px;
}
.fx-select2
{
top:22px;

}
.menuhover:hover{
  background-color: #7b296f;
  color: white;
  padding: 5px 12px;
  border-radius: 3px;
}
.logout2
{
color:#c22e5e;font-weight:bold;text-decoration:underline;margint-top:2%;top:20px;"
}

@media (min-width: 1200px) { /*goldy*/
 .logowidth{
  width: 70%;
 }
}
@media (min-width: 992px) {
  .logowidth{
    width: 70%!important;
  }
}
@media (max-width: 992px) {
  .logowidth{
    width: 100%!important;
  }
}
@media (max-width: 767px) {
 .logowidth{
    width: 30%!important;
  }
  .loc-h{
    display: none;
  }
  .search-h{
    display: none;
      }
}

.jR3DCarouselGallery,.jR3DCarouselGalleryCustomeTemplate {
	margin: 40px auto; /* optional - if want to center align */
}


.jR3DCarouselGalleryCustomeTemplate a{
	text-decoration: none;			
}

  .fx-pophead{
margin-left: 240px!important;
  }
  .citypop{
    padding: 5px 12px;
  }
  .active {
  background-color: #7b296f;
  color: white!important;
  padding: 5px 10px !important;
  border-radius: 3px;
}
  
    </style>


<script type="text/javascript">
	$(document).ready(function()
	{
		
       var theaterListPair = [];
       var movieListPair = [];
		
		 $("#cinemaSearchTabId").hide();
		 var activeCls = $("#activeTabId").val();
		    
		  if(activeCls == 0){
			   /*  $("a[href='#movieTabId']").parent("li").addClass("active");
		    	$("#movieTabId").addClass("active"); */
		    }
		    else if(activeCls == 1){
		    	
		    	$('li a').removeClass("active");
		    	$("a[href='#movieTabId']").parent("li").addClass("active");
		    	$("#movieTabId").addClass("active");
		    	$("#cinemaSearchTabId").hide();
		    	$("#movieSearchTabId").show();
		    }
		    else if(activeCls == 2){
		    	$('li a').removeClass("active");
		    	$("a[href='#cinemaTabId']").parent("li").addClass("active");
		    	$("#cinemaTabId").addClass("active");
		    	$("#movieSearchTabId").hide();
		    	$("#cinemaSearchTabId").show();
		    } 
		
		$('#signUpLoginId').click(function()
		{
			$('#signUpCloseId').click();
			$('#loginBtnId').click();
		});
		  
		$('#myModalpopId').click();
		
		var city_id = $("#hiddenCityId").val();
		if(city_id == ""){
			city_id = 0;
		}
		jQuery.ajax
	   	({
			url  : "getSearchDetails",
			data : {cityId : city_id},
			type : "POST",
			success : function(results) 
			{
				page = JSON.parse(results);
	
				var obj = page['theaterList'];
				
				for(var x in obj){
					
					var obj1 = obj[x];
					
					theaterListPair.push({ value: obj1['theater_id'], label: obj1['theater_name'] });
				}
				
				var source  = [ ];
				for(var i = 0; i < theaterListPair.length; ++i) {
				    source.push(theaterListPair[i].label);
				} 
				
                 var obj2 = page['movieList'];
				
				for(var x in obj2){
					
					var obj3 = obj2[x];
					
					movieListPair.push({ value: obj3['master_movie_id'], label: obj3['movie_name'] });
				}
				
				var source1  = [ ];
				for(var i = 0; i < movieListPair.length; ++i) {
				    source1.push(movieListPair[i].label);
				} 
				
			 	$('#cinemaSearchId').autocomplete({
			 		source: [source],
				});
				$('#movieSearchId').autocomplete({
				  	source:[source1],
				}); 
				
		}
	   
	   });
	   
	   $('.cinemaSearchCls').keypress(function (e) {
			  if (e.which == 13) {
				  var theaterName = $(this).val();
				  var theater_id = "";
				  var j = 0;
				  for(var i = 0; i < theaterListPair.length; ++i) {
					    
					  if(theaterName == theaterListPair[i].label){
						  
						  theater_id = theaterListPair[i].value;
						  j++;
					  }
				  } 
				  
				  if(j == 0){
					  
					  $("#searchErrorSpanId").show();
					  $("#searchErrorSpanId").fadeOut(5000);
				  }
				  else{
					  $('#searchTheaterHiddenId').val(theater_id);
					  $('#searchTheaterFormSubmit').submit();
				  }
			  }
	    });
	   
	   $('.movieSearchCls').keypress(function (e) {
			  if (e.which == 13) {
				  var movieName = $(this).val();
				  var movie_id = "";
				  var j = 0;
				  for(var i = 0; i < movieListPair.length; ++i) {
					    
					  if(movieName == movieListPair[i].label){
						  
						 movie_id = movieListPair[i].value;
						 j++;
					  }
					} 
				 
                  if(j == 0){
                	  $("#searchErrorSpanId").show();
					  $("#searchErrorSpanId").fadeOut(5000);
				  }
				  else{
					  $('#searchMvHiddenId').val(movie_id);
					  $('#searchMovieFormSubmit').submit();
				  }
			  }
	    });
		
		$(document).on('click','#selectChange',function()
		{
			categoryVal = categoryVal+"_"+$(this).val();

		});
		
		$('#nameId').val('');
		$('#emailId').val('');
		$('#mobileId').val('');
		$('#pwd').val('');
		
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
									$("#submitBtnID").attr("disabled", "disabled");
								}
								else
								{
									$("#submitBtnID").removeAttr("disabled");
								}
							}
						});
					}
						}
				});
		
		var user_id_final = 0;
		
		$('#submitBtnID').click(function()
		{
			$('#userRegisterForm').validationEngine({scroll : false});
			if($('#userRegisterForm').validationEngine('validate'))
			{
				var nameVal = $('#nameId').val();
				var emailVal = $('#emailId').val();
				var mobileVal = $('#mobileId').val();
				var pwdVal = $('#pwd').val();
				
				jQuery.ajax({
					data :{
						nameVal : nameVal,
						emailVal : emailVal,
						mobileVal : mobileVal,
						pwdVal : pwdVal
					},
					
					url : "setUserRegisterSubmit",
					type : "POST",
					success : function(results) 
					{
						var page = JSON.parse(results);
						user_id_final = page['user_id'];
						
						if(page['status'] == "success")
						{
							$('#smsPINID').val();
							$('#signUpCloseId').click();
							$('#activationBtnId').click();
							$("#errorSpanId").text("Successfully Registered..!");
							$("#errorSpanId").fadeOut(5000);
							$('#msgDivId').show();
						}
						
					}
				});
				
			}
			
		
		});
		
		$("#smsresendLinkId").on('click',function(){
			
			var user_id = user_id_final;
			
			jQuery.ajax({
				data	:	"user_id=" +user_id,
				url		:	"resendConfirmSMSForUser",
				type	:	"POST",
				success:function(results)
				{
					
					var page = JSON.parse(results);
					
					if(page['status'] == "success")
					{
						$("#errorSpanId5").text("A Pin has been sent to your Email Id");
						$("#errorSpanId5").fadeOut(5000);
						$('#msgDivId5').show();
					}	
					
				}
			});
		});
		
		
		//activationSubmitID
		
		$('#activationSubmitID').click(function()
		{
			$('#activationFormID').validationEngine({scroll : false});
			if($('#activationFormID').validationEngine('validate'))
			{
				var smspinId = $('#smsPINID').val();
				var userID = user_id_final;
				
				jQuery.ajax({
					data :{
						smspinId : smspinId,
						userID : userID
					},
					url : "setAccountActivation",
					type : "POST",
					success : function(results) 
					{
						var page = JSON.parse(results);
						
						if(page['status'] == "success")
						{
							
							$('#emailLoginId').val('');
							$('#pwdLoginId').val('');
							$('#activationPopUpCloseId').click();
							$('#loginBtnId').click();
							$("#errorSpanId1").text("Successfully activated..!");
							$("#errorSpanId1").fadeOut(5000);
							$('#msgDivId1').show();
						}
						else if(page['status'] == "failure")
						{
							
							$('#smsPINID').val('');
							$("#errorSpanId4").text("SMS pin wrong");
							$("#errorSpanId4").fadeOut(5000);
							$('#msgDivId4').show();
						}	
					}
					
				});
				
			}
		});
		
		$('#loginSubmiId').click(function()
		{
			$('#loginFormId').validationEngine({scroll : false});
			if($('#loginFormId').validationEngine('validate'))
			{
				var loginEmail = $('#emailLoginId').val();
				var loginPassword = $('#pwdLoginId').val();
				var cityId = $('#hiddenCityId').val();
				jQuery.ajax({
					data :{
						loginEmail : loginEmail,
						loginPassword : loginPassword,
						cityId : cityId
						
					},
					
					url : "setLoginForUser",
					type : "POST",
					success : function(results) 
					{
						var page = JSON.parse(results);
						if(page['status'] == "success")
						{
							$('#loginBtnCloseId').click();
						}
						else if(page['status'] == "actiavtion failed")
						{
							
							user_id_final = page['user_id'];
							$('#loginBtnCloseId').click();
							$('#activationBtnId').click();
						}	
						else if(page['status'] == "invalid")
						{
							
							$("#errorSpanId6").text("Invalid Username and password");
							$("#errorSpanId6").fadeOut(5000);
							$('#msgDivId6').show();
						}
					}
				});
			}
		});
		
		$('#forgetPasswordID').click(function()
		{
			$('#loginBtnCloseId').click();
			$('#forgetPasswordBtnId').click();
		});
		
		$('#forgetPasswordSubmitId').click(function()
		{
			$('#forgetPasswordFormId').validationEngine({scroll : false});
			if($('#forgetPasswordFormId').validationEngine('validate'))
			{
				var email = $('#forgetPasswordemailId').val();
				
				jQuery.ajax({
					data :{
						email : email
					},
					
					url : "forgetPasswordForUser",
					type : "POST",
					success : function(results) 
					{
						var page = JSON.parse(results);
						
						if(page['status'] == "success")
						{
							$('#forgetPasswordCloseId').click();
							$('#loginBtnId').click();
							$("#errorSpanId1").text("Password changed successfully..!");
							$("#errorSpanId1").fadeOut(5000);
							$('#msgDivId1').show();
						}	
						else if(page['status'] == "failure")
						{
							user_id_final = page['user_id'];
							$('#forgetPasswordCloseId').click();
							$('#activationBtnId').click();
						}	
						
					}
					
				});
				
			}
		});
		
		
		$(document).on('click','#movieTabId',function()
		{
            $("#movieSearchId").val("");
			$("#movieSearchTabId").show();
			$("#cinemaSearchTabId").hide();
			$("#formHiddenMvCityId").val(city_id);
			$("#hiddenActiveMoviesTabId").val(1);
			$("#cityWiseMovieFormSubmit").submit();
			
		});
		
		$(document).on('click','#cinemaTabId',function()
		{
            $("#cinemaSearchId").val("");
			$("#movieSearchTabId").hide();
			$("#cinemaSearchTabId").show();
			$("#formHiddenCineCityId").val(city_id);
			$("#hiddenActiveCinemasTabId").val(2);
			$("#cityWiseCinemasFormSubmit").submit();  

		});
		
		$(document).on('click','#movieSearchIconId',function()
				{
			      var movieName = $("#movieSearchId").val();
			      
			      if(movieName != ""){
			    	  var movie_id = "";
					  var j = 0;
				      for(var i = 0; i < movieListPair.length; ++i) {
					    
					     if(movieName == movieListPair[i].label){
						  
						   movie_id = movieListPair[i].value;
						   j++;
					     }
					   } 
				 
                      if(j == 0){
						  
						  $("#searchErrorSpanId").show();
						  $("#searchErrorSpanId").fadeOut(5000);
					  }
					  else{
			            $('#searchMvHiddenId').val(movie_id);
			            $('#searchMovieFormSubmit').submit();
					  }
			      }
				});
		
		$(document).on('click','#theaterSearchIconId',function()
		{
				
				var theaterName = $("#cinemaSearchId").val();
				
				if(theaterName != ""){
					
					var theater_id = "";
					var j = 0;
					  for(var i = 0; i < theaterListPair.length; ++i) {
						    
						  if(theaterName == theaterListPair[i].label){
							  
							  theater_id = theaterListPair[i].value;
							  j++;
						  }
						} 
					  
					  if(j == 0){
						  
						  $("#searchErrorSpanId").show();
						  $("#searchErrorSpanId").fadeOut(5000);
					  }
					  else{
						  $('#searchTheaterHiddenId').val(theater_id);
						  $('#searchTheaterFormSubmit').submit();
					  }
				}
		});
		
		$(document).on('click','#createActId',function()
		{
		      $("#loginBtnCloseId").click();
		});
	});
</script>


</head>
<body class="fx-greycolor">

<s:hidden name="theaterOwnerBean.city_id" id="hiddenCityId"></s:hidden>
<s:hidden name="theaterOwnerBean.tab_id" id="activeTabId"></s:hidden>

<s:form action="all-city-movies" id="cityWiseMovieFormSubmit">
<s:hidden name="theaterOwnerBean.city_id" id="formHiddenMvCityId"></s:hidden> 
<s:hidden name="theaterOwnerBean.tab_id" id="hiddenActiveMoviesTabId"></s:hidden>
</s:form> 

<s:form action="city-cinemas" id="cityWiseCinemasFormSubmit">
<s:hidden name="theaterOwnerBean.location_id" id="formHiddenCineCityId"></s:hidden>
<s:hidden name="theaterOwnerBean.tab_id" id="hiddenActiveCinemasTabId"></s:hidden>
</s:form>

<s:form action="search-movie" id="searchMovieFormSubmit">
<s:hidden name="theaterOwnerBean.master_movie_id" id="searchMvHiddenId"></s:hidden>
<s:hidden name="theaterOwnerBean.city_id"></s:hidden>
</s:form>

<s:form action="search-theater" id="searchTheaterFormSubmit">
<s:hidden name="theaterOwnerBean.theater_id" id="searchTheaterHiddenId"></s:hidden>
<s:hidden name="theaterOwnerBean.city_id"></s:hidden>
</s:form>

<button id="signUpFormId" style="display: none;" data-toggle="modal" data-target="#myModal2"></button>

<button id="myModalpopId" style="display: none;" data-toggle="modal" data-target="#myModalpop"></button>
<button id="activationBtnId" style="display: none;" data-toggle="modal" data-target="#myModal4"></button>
<button id="loginBtnId" style="display: none;" data-toggle="modal" data-target="#myModal1"></button>
<button id="forgetPasswordBtnId" style="display: none;" data-toggle="modal" data-target="#myModal3"></button>

<header id="header" class="bg-white">
   <div class="container">
    <div class="row ">
      <div class="col-md-2"> 
        <div class="pull-left">
        <a href="index.html" class="scrollto  "><img src="images/ticketlite.png"  class="log logowidth"></a>         
         </div>
          </div>
          
          <div class="col-md-8 fx-select" >
          
              <div class="row m-n-t-md">
                  <nav id="nav-menu-container"> 
                   <ul class="nav-menu p">
                     <li ><a href="#" id="movieTabId" class="menuhover">Movies</a></li>
                     <li><a href="#" id="cinemaTabId" class="menuhover" >Cinemas</a></li>
                     <li><a href="#"  data-toggle="modal" data-target="#myModal2"  class="menuhover">SignUp</a></li>
                     <li><a href="#"  data-toggle="modal" data-target="#myModal1" class="menuhover">Login</a></li>
                     <!--  <li><a href="#"  data-toggle="modal" data-target="#popupcity" class="menuhover">popupcity</a></li> -->
                   </ul>  
                    </nav>
 
           <div class="input-group search-h" id="movieSearchTabId">
                   <s:textfield id="movieSearchId" placeholder="Search in Movie..." name="search" cssClass="form-control input-lg border-right-0 movieSearchCls"></s:textfield>
                <div class="input-group-prepend bg-white">
            <span class="input-group-text border-left-0 rounded-right bg-white" id="movieSearchIconId"><i class="fa fa-search"></i></span>
          </div>
             </div>  

       <div class="input-group search-h" id="cinemaSearchTabId">
        
            <s:textfield id="cinemaSearchId" placeholder="Search in Cinema..." name="search" cssClass="form-control input-lg border-right-0 cinemaSearchCls"></s:textfield>
           <div class="input-group-prepend bg-white">
            <span class="input-group-text border-left-0 rounded-right bg-white" id="theaterSearchIconId"><i class="fa fa-search"></i></span>
          </div>
        </div>  
        
       <span id="searchErrorSpanId" style="display: none">Search Result Not Found...</span> 
              </div>           
          </div>
            <div class="col-md-2 pull-right fx-select2 loc-h">
                  
             <a href="user-logout" class="logout2" > Logout </a>
      
                         </div>
 
              </div>
     

    </div>
  </header>
  
  <!-- #header -->
  
  
  <div class="modal inmodal" id="myModal1" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content animated flipInY">
                                        
                                        <div class="modal-body">
                                              <section class="function1" >
       <div class="container pop-login-body enduser-bg"  >
         <div class="row " >

          <div class="col-md-12 pop-login-margintop" >
            <button id="loginBtnCloseId" type="button" class="close fx-close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
             	<div id="msgDivId1" style="display: none;">
				<span style="color:white;" id="errorSpanId1"></span>
				</div> 
				<div id="msgDivId6" style="display: none;">
				<span style="color:white;" id="errorSpanId6"></span>
				</div> 
         <h4 class="text-center fx-white"><b>LOGIN</b></h4>  
         <s:form id="loginFormId">
          <input type="text" id="emailLoginId"  class="form-control space5 fx-broad validate[required]" placeholder="Username">
           <input type="password" id="pwdLoginId" class="form-control space5 fx-broad validate[required]" placeholder="Password">
           </s:form>
        <p class="fx-white"><input type="checkbox"  class="checkbox"><b> Remember Me</b>
             <span><a id="forgetPasswordID" style="cursor: pointer;" class="forgot pull-right"> Forgot Password ? </a> </span></p>
            <button id="loginSubmiId" class="btn btn-primary btn-block fx-primary-button" style="">Login to proceed</button>
            <p class="m-t-sm text-white font-weight-bold  text-center" style="margin-top:56px"><a id="createActId" style="cursor: pointer;text-align:center;" data-toggle="modal" data-target="#myModal2"> Create New Account </a> </p>
            </div>
          </div>
        </div>
     </section>
    
                                        </div>
                                       
                                    </div>
                                </div>
                            </div>
                            <!-- end of login -->
                            <!-- start of signup -->
<div class="modal inmodal" id="myModal2" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content animated flipInY">
                                        
                                        <div class="modal-body">
                                              <section class="function1" >
     <div class="container pop-registration-body enduser-bg"  >
        <div class="row " >
        <div class="col-md-12 pop-register-margintop" >
     <button id="signUpCloseId"  type="button" class="close fx-close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="fx-white text-center "><b>REGISTER</b></h4>
        <s:form id="userRegisterForm">
        <input type="text" id="nameId" placeholder="Name"  class="form-control space5 fx-broad validate[required,custom[onlyLetterSp]] txt" >
        <input type="text"  id="emailId" placeholder="Email id" class="form-control space5 fx-broad txt validate[required,custom[email]]" >
        <div id="divId"></div>
        <input type="text" id="mobileId" placeholder="Mobile Number" class="form-control space5 fx-broad validate[required,custom[mobile]]" maxlength="10"/>
        <input type="password" class="form-control space5 fx-broad validate[required]" placeholder="Password" id="pwd" >
        <input type="password" placeholder="Confirm Password" class="form-control space5 fx-broad  validate[required,equals[pwd]]" >
        </s:form>
        <h6 class="fx-white m-bot"><input type="checkbox" required class="checkbox"> I Accept the Terms & Conditions </h6>
            
            
        <button id="submitBtnID" class="btn btn-success btn-block fx-primary-button" >Sign up</button>
        
      <p class="m-t-sm alreadymember">Already a member ? <a style="cursor: pointer;" id="signUpLoginId"> Login </a> </p>
        </div>
      </div>
    </div>
  </section>
    
                                        </div>
                                       
                                    </div>
                                </div>
                            </div>
                            <!-- end of registration -->
                            <!-- start of forgot password -->
                                            <div class="modal inmodal" id="myModal3" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content animated flipInY">
                                        
                                        <div class="modal-body">
                                              <section class="function1" >
     <div class="container pop-forgot-body enduser-bg"  >
        <div class="row " >
              <div class="col-md-12 pop-forgot-margintop" >
      <button id="forgetPasswordCloseId" type="button" class="close fx-close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>  
                <h4 class="text-center fx-white"><b>FORGOT PASSWORD</b></h4>
                <label class="fx-white text-center forgot-label fx-broad"> Enter your e-mail address below to reset your password.</label>
               <s:form id="forgetPasswordFormId"> 
              <input id="forgetPasswordemailId" type="text" style="margin-bottom: 35px" class="form-control space5 fx-broad validate[required,custom[email]]" placeholder="Email id">
              </s:form>
        <button id="forgetPasswordSubmitId" class="btn btn-success btn-block fx-primary-button" >Submit </button>
      </div>
        </div>
      </div>
    </div>
  </section>
    
                                        </div>
                                       
                                    </div>
                                </div>
                            <!-- end of forgot password -->
                            <!-- start of activation -->
                        <div class="modal inmodal" id="myModal4" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content animated flipInY">
                                        
                                        <div class="modal-body">
                                              <section class="function1" >
     <div class="container pop-forgot-body enduser-bg"  >
        <div class="row " >
              <div class="col-md-12 pop-forgot-margintop" >
      <button id="activationPopUpCloseId" type="button" class="close fx-close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
     <div id="msgDivId5" style="display: none;">
	<span style="color:white;" id="errorSpanId5"></span>
	</div>  
	<div id="msgDivId4" style="display: none;">
	<span style="color:white;" id="errorSpanId4"></span>
	</div>  
        <h4 class="fx-white text-center"><b>ACCOUNT ACTIVATION</b></h4>
            <s:form id="activationFormID">
            <input type="password" id="smsPINID" class="form-control space5 validate[required]" placeholder="Enter your Mobile otp"> <p class="linkdecor m-bot fx-white text-center">
            </s:form>
            <p class="linkdecor m-bot fx-white text-center">
            <a style="cursor: pointer;"  id="smsresendLinkId"> Resend mobile otp </a></p>
          <button id="activationSubmitID" class="btn btn-success btn-block fx-primary-button"><b>Submit</b></button>
        </div>
        </div>
      </div>
    </div>
  </section>
    
                                        </div>
                                       
                                    </div>
                                </div>
                            </div> 
                            <!-- end of activation -->  
  
<script type="text/javascript">
  $(document).ready(function(){
  $('ul li a').click(function(){
  $('li a').removeClass("active");
  $(this).addClass("active");

});
});
</script>
  
  
<script type="text/javascript">
$('ul.nav li.dropdown').hover(function() 
{
  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
},
function() 
{
  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
});
</script>

  <script src="lib/bootstrap/js/bootstrap.min.js"></script>


</body>
</html>