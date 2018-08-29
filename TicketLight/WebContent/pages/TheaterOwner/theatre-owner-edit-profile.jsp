<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile edit</title>
<script src="js/jquery-1.9.1.js"></script>
<link rel="stylesheet" type="text/css" href="css/validationEngine.jquery.css"/>

<script src="js/jquery.validationEngine-en.js"></script>
<script src="js/jquery.validationEngine.js"></script> 
<script type="text/javascript">

$(document).ready(function()
{
	
	$('#theaterOwnerProfileEditId').validationEngine({scroll : false});
	
	$(".txtNumeric").keypress(function(event){
        var inputValue = event.charCode;
        if(!((inputValue > 47 && inputValue < 58) || (inputValue==32) || (inputValue==46))){
            event.preventDefault();
        }
    });
	
	$('.stateCls').change(function()
	{
		var stateId = $(this).val();
		jQuery.ajax({
			data :{
				stateId : stateId
				},
			
			url : "getStateWiseCityDetails",
			type : "POST",
			success : function(results) 
			{
				 
				var page = JSON.parse(results);
				
				var obj = page['cityMap'];
			
				$('#districtDivId').empty();
				
				var str = '';
				
				str += '<select class="form-control validate[required]" id="districtId" name="theaterOwnerBean.district_id">';
				str += '<option value="0">--Select District--</option>';
				for(var x in obj)
					{
						var obj1 = obj[x];
						str += '<option value='+x+'>'+obj1+'</option>';
					}
				str += '</select>';
				
				$('#districtDivId').append(str);
			}
		
		});
	});
	
	$(document).on('change', '#districtId' ,function()
	{
		var stateId = $(this).val();
		jQuery.ajax({
			data :{
				stateId : stateId
				},
			
			url : "getDistrictWiseCityDetails",
			type : "POST",
			success : function(results) 
			{
				 
				var page = JSON.parse(results);
				
				var obj = page['cityMap'];
			
				$('#cityDivId').empty();
				
				var str = '';
				
				str += '<select class="form-control validate[required]"  name="theaterOwnerBean.city_id">';
				str += '<option value="0">--Select City--</option>';
				for(var x in obj)
					{
						var obj1 = obj[x];
						str += '<option value='+x+'>'+obj1+'</option>';
					}
				str += '</select>';
				
				$('#cityDivId').append(str);
			}
		
		});
		
	});
	
	$("#profileImageId").change(function() {
		var filename = $("#profileImageId").val();
		var validextensions = /(\.jpg|\.jpeg|\.gif|\.png)$/i;
		if (validextensions.test(filename)) {
			var sizeoffile = $("#profileImageId")[0].files[0].size;
			if (sizeoffile > 1000000) {
				$("#errorSpanId").html("Selected file size too large maximum size 1MB.");
				$('#submitbtnId').attr('disabled', true);
				
			} else {
				$("#errorSpanId").html("");
				$('#submitbtnId').attr('disabled',false);
				
			}
		} else {
			$("#errorSpanId").html("Please select valid file (.jpeg|.png|.jpg) format");
			$('#submitbtnId').attr('disabled', true);
			
		}

	});
	
});

</script>
</head>
<body>

        <div class="row wrapper border-bottom white-bg ">
                <div class="col-lg-10">
                   
                    <ol class="breadcrumb m-sm ">
                        <li>
                            <a href="#">Home</a>
                        </li>
                      
                       
                        <li class="active">
                            <strong>Edit Profile</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
 <div class="wrapper wrapper-content ">
         <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h2>Edit Profile</h2>
                        </div>
                        <form action="theater-owner-update-page" id="theaterOwnerProfileEditId" method="post" class="form-horizontal" enctype="multipart/form-data">
                        <div class="ibox-content">
                            
                               <div class="form-group">
                     
                    <label  class="col-sm-3  control-label">
                       First Name :
                    </label>
                    <div class="col-sm-6 ">
                        <s:textfield name="theaterOwnerBean.theater_owner_first_name" cssClass="form-control validate[required,custom[onlyLetterSp]]" />
                    </div>
                </div>
					
					<div class="form-group">
                     
                    <label  class="col-sm-3  control-label">
                       Last Name :
                    </label>
                    <div class="col-sm-6 ">
                        <s:textfield name="theaterOwnerBean.theater_owner_last_name" cssClass="form-control validate[required,custom[onlyLetterSp]]" />
                    </div>
                </div>
                
                 <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        phone :
                    </label>
                    <div class="col-sm-6">
                        <s:textfield name="theaterOwnerBean.theater_owner_mobile" cssClass="form-control txtNumeric validate[required]" maxlength="11" />
                    </div>
                </div>
                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        Address :
                    </label>
                
                    <div class="col-sm-6">
                           <s:textarea name="theaterOwnerBean.theater_owner_address" cssClass="form-control validate[required]"/>
                    </div>
                    </div>
                    <div class="form-group">
                    <label  class="col-sm-3 control-label">
                        State :
                    </label>
                    <div class="col-sm-6">
                    <s:select list="stateMap" name="theaterOwnerBean.state_id" headerKey="" headerValue="--Select State--" cssClass="validate[required] stateCls form-control"/><br>
                    </div>
                    </div>
                    <div class="form-group">
                     <label  class="col-sm-3 control-label">
                        District :
                    </label>
                    <div class="col-sm-6">
                    <div id="districtDivId">
					 <s:select list="districtMap" name="theaterOwnerBean.district_id" headerKey="" headerValue="--Select District--" cssClass=" form-control"/><br>                    
                    </div>
               		</div>
               		</div>
               		
               		<div class="form-group">
                     <label  class="col-sm-3 control-label">
                        City :
                    </label>
                    <div class="col-sm-6">
                    <div id="cityDivId">
					 <s:select list="cityMap" name="theaterOwnerBean.city_id" headerKey="" headerValue="--Select City--" cssClass=" form-control"/><br>                    
                    </div>
               		</div>
               		</div>
               		
               		 <div class="form-group">
                     <label  class="col-sm-3 control-label">
                        Profile :
                    </label>
                    <div class="col-sm-6">
                    <span id="errorSpanId" style="color:red;padding:23px;font-weight:500;"></span>
                    <input type="file" id="profileImageId" name="theaterOwnerBean.profile_photo" class="form-control" >
               		</div>
               		</div>
               		 <div class="form-group">
                <div class="col-sm-2 col-sm-offset-8">
                <button type="submit" id="submitbtnId" name="" class="btn btn-primary "> Save</button>
                    </div>
                </div>
                </div>
                
                           </form>
                        </div>
                    </div>
                </div>
            </div>
         </div>
</body>
</html>