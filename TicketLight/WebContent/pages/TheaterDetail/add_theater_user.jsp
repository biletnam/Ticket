<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-1.9.1.js"></script>
<link rel="stylesheet" type="text/css" href="css/validationEngine.jquery.css"/>
<link href="Dynamic/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<script src="js/jquery.validationEngine-en.js"></script>
<script src="js/jquery.validationEngine.js"></script> 
<script src="Dynamic/js/plugins/datapicker/bootstrap-datepicker.js"></script>

</head>
<script type="text/javascript">
$(document).ready(function(){
	
	$('.theaterCounterEmployeCls').addClass("active"); 
	$('.addTheaterEmployerCls').addClass("active"); 
	
	$("#addTheaterEmployeeFormId").validationEngine(); 

	   $(document).on('change',"#profileImgId",function(){
           
    		 var filename = $(this).val();
    		 
		  		var validextensions = /(\.jpg|\.jpeg|\.gif|\.png)$/i;
		  		if (validextensions.test(filename)) {
		  			var sizeoffile = $('#profileImgId')[0].files[0].size;
		  			if (sizeoffile > 1000000) {
		  			    $('#spanErrorId').html("Selected file size too large maximum size 1MB.");
		  				$('#submitBtnId').attr('disabled', true);
		  				
		  			} else {
		  			    $('#spanErrorId').html("");
		  				$('#submitBtnId').attr('disabled',false);
		  				
		  			}
		  		} else {
		  		    $('#spanErrorId').html("Please select valid file (.jpeg|.png|.jpg) format");
		  			$('#submitBtnId').attr('disabled', true);
		  			
		  		}
         });
	   
	   $(".dateSelect").datepicker({
	    	 todayBtn: "linked",
	         autoclose: true,
	         format: "yyyy-mm-dd",
	     });
});
</script>
<body>
 <!-- <div id="page-wrapper" class="gray-bg"> -->
       

        <div class="row wrapper border-bottom white-bg ">
                <div class="col-lg-10">
                   
                    <ol class="breadcrumb m-sm ">
                        <li>
                            <a href="#">Home</a>
                        </li>
                      
                       
                        <li class="active">
                            <strong>Add Employee Details</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
      

        <div class=" row wrapper wrapper-content">
         <div class="row">
                <div class="col-md-12 ">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h2 class="font-bold">Employee Details</h2>
                        </div>
                        <div class="ibox-content">
                            <s:form method="post" action="theaterAddUserSubmit" id="addTheaterEmployeeFormId" cssClass="form-horizontal" enctype="multipart/form-data" theme="simple" >

                   
                            
                    <div class="form-group m-t-md">
                    <div class="col-sm-2">
                            <label  class=" control-label">Employee Name<span class="text-danger">&#42;</span></label>
                     </div>
                     <div class="col-sm-6">    
                      <!--   <input type="text" class="form-control"  > -->
                       <s:textfield name="theaterOwnerBean.first_name" cssClass="form-control validate[required]"  placeholder="First Name"/>
                    </div>
                </div>
                
                 <div class="form-group m-t-md">
                   <div class=" col-sm-2">
                        <label  class=" control-label">Theatre Id<span class="text-danger">&#42;</span></label>
                   </div>
                   <div class="col-sm-6"> 
                   <s:select list="theaterMap" name="theaterOwnerBean.theater_id" headerKey="" headerValue="Select Any Theatre" cssClass="form-control validate[required]"/>    
                </div>
              </div>
                
                    <div class="form-group m-t-md">
                    <div class="col-sm-2">
                         <label  class=" control-label">Employee Id</label>
                     </div>
                     <div class="col-sm-6">    
	                     <s:textfield name="theaterOwnerBean.employee_id" cssClass="form-control"  placeholder="Employee Id"/>
                    </div>
                </div>
                         <div class="form-group m-t-md">
            
                            <div class="col-sm-2">
                                    <label  class=" control-label">
                                    Gender<span class="text-danger">&#42;</span>
                                     </label>
                            </div>                   
                                <div class="col-sm-6">
                                <input type="radio" name="theaterOwnerBean.gender_id" class="validate[required]" value="1"> Male
                                <input type="radio" name="theaterOwnerBean.gender_id" class="validate[required]" value="2"> Female
                                <input type="radio" name="theaterOwnerBean.gender_id" class="validate[required]" value="3"> Other
            
                                </div>  
                            </div>
                       <div class="form-group m-t-md">
                            <div class=" col-sm-2">
                                <label class="control-label ">Date of birth</label>
                            </div>
                              <div class="col-sm-6">
		        
                      <div class="input-group date dateSelect">
                      <span class="input-group-addon"><i class="fa fa-calendar"></i></span><s:textfield name="theaterOwnerBean.date_of_birth" cssClass="form-control" id="dateofBirthId" placeholder="Date of Birth"/>
                     </div>
	                     </div>
                        </div>
                  <div class="form-group m-t-md">
                        <div class="col-sm-2">
                            <label  class=" control-label">Phone<span class="text-danger">&#42;</span></label>
                         </div>
                         <div class="col-sm-6">   
                             <s:textfield name="theaterOwnerBean.phone_number" cssClass="form-control validate[required,custom[mobile]]" maxlength="10" placeholder="Phone Number"/>
                        
                        </div>
                  </div>
                  <div class="form-group m-t-md">
                   <div class="col-sm-2">
                      <label  class=" control-label">Email-ID <span class="text-danger">&#42;</span></label>
                    </div>
                    <div class="col-sm-6">  
                          <s:textfield name="theaterOwnerBean.email_id" cssClass="form-control validate[required,custom[email]]" placeholder="Email"/>
                     </div>
                 </div>
                  <div class="form-group m-t-md">
                   <div class=" col-sm-2">
                        <label  class=" control-label">Employee Role<span class="text-danger">&#42;</span></label>
                   </div>
                   <div class="col-sm-6"> 
                   <s:select list="userRoleMap" name="theaterOwnerBean.theatre_employee_role_id" headerKey="" headerValue="Select Any Role" cssClass="form-control validate[required]"/>    
                </div>
              </div>

                     <div class="form-group m-t-md">
                   <div class="col-sm-2">
                    <label  class=" control-label">Employee Photo <small style="cursor:pointer"><i class="fa fa-question-circle" title=" Upload .png, .jpg and .jpeg files only"></i> </small></label>
                   </div>
                   <div class="col-sm-6">
                         <s:file cssClass="form-control" id="profileImgId" name="theaterOwnerBean.profile_image" />&nbsp;<span id="spanErrorId" style="color: red;">
                        <%--  <span id="fileDivId" style="color:red">* Upload .png, .jpg and .jpeg files only</span> --%>
                     </div>
                 </div>
                     
                
                <div class="form-group">
                <div class=" col-md-2 col-md-offset-8">
                <button class="btn btn-primary m-t-lg" id="submitBtnId" type="submit"> Submit</button>
                    </div>
                </div>
                           </s:form>
                        </div>
                        </div>
                    </div>
                </div>
            </div>

        

       <!--  </div> -->
       

   
</body>
</html>