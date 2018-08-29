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

<script src="js/jquery.validationEngine-en.js"></script>
<script src="js/jquery.validationEngine.js"></script> 
<style type="text/css">
       .fx-head-title{
        padding: 8px;font-size: 17px;width: 30%;border-radius: 0px 3px 3px 0px;font-weight: bold;margin-left: -20px
       }
       td{
        padding: 8px;
       }
       .fnt-size{
        font-size: 20px;
       }
     </style>
     <script type="text/javascript">
     $(document).ready(function(){
    	 
    	 $('.theaterDetailCls').addClass("active"); 
    	 $('.addTheaterCls').addClass("active"); 
 		
    	 $("#updateTheaterDetailFormId").validationEngine(); 
    	
    	 $("#successSpanId").fadeOut(6000);
    	 $("#errorSpanId").fadeOut(6000);
    		
    	var district_map; 
 		jQuery.ajax({
 			
 			url : "getJSONDistrictDetails",
 			type : "POST",
 			success : function(results) 
 			{
 				var page = JSON.parse(results);
 				district_map = page;
          
 			}
 		
 		});
 		
  	  $(document).on('change',".districtCls",function(){

    		 var id1 = $(this).attr('id');
    		 var id2 = id1.split("_");
    		 var rowId = id2[1];
    		 var districtId = $("#districtId_"+rowId).val();

    		 jQuery.ajax({
    			 
    			 data : {
    				 districtId : districtId
    				 },
    		    url  : "districtWiseCityDetail",
    			type : "post",
    			success : function (results) {
					
    				var r = $("#cityId_"+rowId);
					var t = jQuery.parseJSON(results);
					$('#cityId_'+rowId+' option').remove();
					r.append($('<option>').text("Select City").attr('value', 0));
					$.each(t, function(key, value) {
						r.append($('<option>').text(value).attr('value', key));
					});
				}
    		 });
    		 
    	 });
    	 
  	  //removeAvailableTheater
  	     $(document).on('click',".removeAvailableTheaterCls",function(){
               var theater_detail = $(this).attr('id');
  	    	   var id = theater_detail.split('_');
  	    	   var theater_id = id[0];
  	    	   var theater_name = id[1];
  	    	   $("#theaterNameId").empty();
  	    	   $("#theaterNameId").append("Delete theater "+theater_name);
  	    	   $("#hiddenTheaterId").val(theater_id);
  	    	   $("#popUpId").modal({modal: true}); 
  	     });
  	  
  	  //removePerticularScreenBtnCls
  	   $(document).on('click',".removePerticularScreenBtnCls",function(){
               var screen_detail = $(this).attr('id');
  	    	   var id = screen_detail.split('_');
  	    	   var theater_id = id[0];
  	    	   var theater_name = id[1];
  	    	   var screen_id = id[2];
  	    	   var screen_name = id[3];
  	    	   $("#screenNameId").empty();
  	    	   $("#screenNameId").append("Delete "+screen_name+" Screen in this "+theater_name);
  	    	   $("#hiddenDelScrTheaterId").val(theater_id);
  	    	   $("#hiddenDelScreenId").val(screen_id);
  	    	   $("#popUpId1").modal({modal: true}); 
  	     });
  	  
  	   
    	  //add screen detail
    	  $(document).on('click',".addScreenBtnCls",function(){
    		  
    			var id = $(this).closest('.screenDetailDivCls').attr('id');
    			var mainDivId = $(this).closest('.theaterDetailDivCls').attr('id');
                var theaterDivId = mainDivId.split("_");
                var theaterMainDivId = theaterDivId[1];
    			
    			var mainValue = id.split("_");
    			$('.theaterScrnDivCls_'+theaterMainDivId+' #'+id+' .addScreenBtnCls').hide();
    			$('.theaterScrnDivCls_'+theaterMainDivId+' #'+id+' .removeScreenBtnCls').hide();
    			$('.theaterScrnDivCls_'+theaterMainDivId+' #'+id+' .removePerticularScreenBtnCls').hide();
    			var divCount = parseInt(mainValue[1]) + 1;
    			var scrCount = 0;
    			if(mainValue[1] == 0){
    				scrCount = 1;
    			}
    			else{
    				scrCount = parseInt(mainValue[1]) + 1;
    			}
    			var str = '';
    			str +='<div class="row m-t-sm screenDetailDivCls" id="screenDivId_'+scrCount+'">';
    			str +='<div class="col-md-1"></div>';
    			str +='<div class="col-md-3">';
                str +='<input type="hidden" name="theaterOwnerBean.theaterDetailList['+theaterMainDivId+'].screenList['+divCount+'].screen_id" id="hiddenAddNewScreenId"/>';
    			str +='<label>Screen Name<span class="text-danger">&#42;</span></label>';
    		    str +='<input name="theaterOwnerBean.theaterDetailList['+theaterMainDivId+'].screenList['+divCount+'].screen_name" class="form-control validate[required]" placeholder="Screen Name" type="text">';
    			str +='</div>';
    			str +='<div class="col-md-2">';
    			str +='<label>Seat Count<span class="text-danger">&#42;</span></label>';
    		    str +='<input name="theaterOwnerBean.theaterDetailList['+theaterMainDivId+'].screenList['+divCount+'].seat_count" class="form-control validate[required]" placeholder="Seat Count" type="text">';
    			str +='</div>';
    			str +='<div class="col-md-4">';
    			str +='<label>Upload Layout <small class="m-l-xs"><i class="fa fa-question-circle" title="Upload .png, .jpg and .jpeg files only"></i></small></label>';
    			//str +='<span style="color:red">* Upload .png, .jpg and .jpeg files only</span>';
                str +='<input type="file" style="height:auto" name="theaterOwnerBean.theaterDetailList['+theaterMainDivId+'].screenList['+divCount+'].screen_layout" placeholder="Upload Layout" id="screenLayoutId_'+divCount+'" class="form-control screenImgLayoutCls">&nbsp;<span id="screenLayoutErrorId_'+divCount+'" style="color: red;">';
                str +='</div>';
                str +='<div class="col-md-2 m-t-sm">';
                str +='<a class="addScreenBtnCls"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>';
                str +='<a class="removeScreenBtnCls"><i class="fa fa-minus-square text-danger m-r-sm fnt-size" ></i></a>';
                str +='</div>';
                str +='</div>';
                $('.theaterScrnDivCls_'+theaterMainDivId).append(str);
    		});


    	  $(document).on('click',".removeScreenBtnCls",function(){
  			
  			var id = $(this).closest('.screenDetailDivCls').attr('id');
  			var mainValue = id.split("_");
  			var mainDivId = $(this).closest('.theaterDetailDivCls').attr('id');
            var theaterDivId = mainDivId.split("_");
            var theaterMainDivId = theaterDivId[1];
  			var divCount = parseInt(mainValue[1]) - 1;
  			if(divCount > 0)
  				{
                
  				$('.theaterScrnDivCls_'+theaterMainDivId+' #'+id).remove();
  				$('.theaterScrnDivCls_'+theaterMainDivId+' #'+mainValue[0]+'_'+divCount+' .addScreenBtnCls').show();
  				$('.theaterScrnDivCls_'+theaterMainDivId+' #'+mainValue[0]+'_'+divCount+' .removeScreenBtnCls').show();
    			$('.theaterScrnDivCls_'+theaterMainDivId+' #'+mainValue[0]+'_'+divCount+' .removePerticularScreenBtnCls').show();

  				}
  			else
  				{
  				$('.theaterScrnDivCls_'+theaterMainDivId+' #'+id).remove();
  				$('.theaterScrnDivCls_'+theaterMainDivId+' #'+mainValue[0]+'_'+divCount+' .addScreenBtnCls').show();
  				$('.theaterScrnDivCls_'+theaterMainDivId+' #'+mainValue[0]+'_'+divCount+' .removeScreenBtnCls').hide();
    			$('.theaterScrnDivCls_'+theaterMainDivId+' #'+mainValue[0]+'_'+divCount+' .removePerticularScreenBtnCls').hide();

  				} 
  		   });		
    		
    		
    		//add theater
    		
    		$(document).on('click','.addAnotherTheaterCls',function(){
   			 
    			var id = $(this).attr('id'); 
    		    var id1 = id.split("_");
    			
    			divCount = parseInt(id1[1]) + 1;
    			
    			var theaterCount = divCount + 1;
    			
    			$("#addTheater_"+id1[1]).hide();
    			$("#removeTheater_"+id1[1]).show();
    			
    			var str = '';
    			
    			str +='<div class="theaterDetailDivCls" id="theaterDivID_'+divCount+'">';
    			str +='<div class="row">';
    			str +='<div class="col-md-6"><h2 class="blue-bg m-b-md fx-head-title" >Theatre '+theaterCount+'</h2></div>';
            	str +='<div class="col-md-6">';
            	str +='<a class="removeTheaterCls" id="removeTheater_'+divCount+'" style="float:right;margin-left:5px" id="remove-rest"><button type="button" class="btn btn-danger">Delete Theatre '+theaterCount+'</button></a>';
    			str +='<a class=" pull-right addAnotherTheaterCls" id="addTheater_'+divCount+'" ><button type="button" class="btn btn-primary m-r-sm">Add Theatre</button></a>';
    			str +='</div>';
    			str +='</div>';
                str +='<input type="hidden" name="theaterOwnerBean.theaterDetailList['+divCount+'].theater_id" id="hiddenAddNewTheaterId"/>';

    			str +='<div class="form-group">';
    			str +='<label  class="col-sm-3  control-label">Theatre Name<span class="text-danger">&#42;</span> :</label>';
    			str +='<div class="col-sm-6 ">';
    			str +='<input type="text" name="theaterOwnerBean.theaterDetailList['+divCount+'].theater_name" class="form-control validate[required]"/>';
    			str +='</div>';
    			str +='</div>';

    			str +='<div class="form-group">';
    			str +='<label  class="col-sm-3 control-label">District<span class="text-danger">&#42;</span> :</label>';
    			str +='<div class="col-sm-6">';
    			str += '<select name="theaterOwnerBean.theaterDetailList['+divCount+'].district_id" id="districtId_'+divCount+'" class="form-control validate[required] districtCls">';
    			str += '<option value="">Select Any District</option>';
    			for ( var x in district_map) {
    				var obj1 = district_map[x];
    				str += '<option value="'+x+'">'
    					+ obj1 + '</option>';
    	     	}
    			str += '</select>';
    			str +='</div>';
    			str +='</div>';


    			str +='<div class="form-group">';
    			str +='<label  class="col-sm-3 control-label">City<span class="text-danger">&#42;</span> :</label>';
    			str +='<div class="col-sm-6">';
    			str += '<select name="theaterOwnerBean.theaterDetailList['+divCount+'].city_id" id="cityId_'+divCount+'" class="form-control validate[required]">';
    			str += '<option value="">Select Any City</option>';
    			for ( var x in district_map) {
    				var obj1 = district_map[x];
    				str += '<option value="'+x+'">'
    					+ obj1 + '</option>';
    	     	}
    			str += '</select>';
    			str +='</div>';
    			str +='</div>';

    			str +='<div class="form-group">';
    			str +='<label  class="col-sm-3 control-label">Address<span class="text-danger">&#42;</span> :</label>';
    			str +='<div class="col-sm-6">';
    			str +='<textarea name="theaterOwnerBean.theaterDetailList['+divCount+'].address" class="form-control validate[required]"/>';
    			str +='</div>';
    			str +='</div>';

    			str +='<div class="form-group">';
    			str +='<label  class="col-sm-3 control-label">Theatre License Number<span class="text-danger">&#42;</span> :</label>';
    			str +='<div class="col-sm-6">';
    			str +='<input type="text" name="theaterOwnerBean.theaterDetailList['+divCount+'].theater_license_number" class="form-control validate[required]"/>';
    			str +='</div>';
    			str +='</div>';

    			str +='<div class="form-group">';
    			str +='<label  class="col-sm-3 control-label">Theatre TIN Number<span class="text-danger">&#42;</span> :</label>';
    			str +='<div class="col-sm-6">';
    			str +='<input type="text" name="theaterOwnerBean.theaterDetailList['+divCount+'].theater_tin_number" class="form-control validate[required]" maxlength="11"/>';
    			str +='</div>';
    			str +='</div>';

    			str +='<div class="form-group">';
    			str +='<label  class="col-sm-3 control-label">GST Number<span class="text-danger">&#42;</span> :</label>';
    			str +='<div class="col-sm-6">';
    			str +='<input type="text" name="theaterOwnerBean.theaterDetailList['+divCount+'].theater_gst_number" class="form-control validate[required]" maxlength="15"/>';
    			str +='</div>';
    			str +='</div>';

                str +='<h2 class="blue-bg m-b-md fx-head-title" >Screen Details </h2>';
                str +='<div class="theaterScrnDivCls_'+divCount+'">';
                str +='<div class="row screenDetailDivCls" id="screenDivId_0">';
                str +='<div class="col-md-1"></div>';
                str +='<div class="col-md-3">';
                str +='<label>Screen Name<span class="text-danger">&#42;</span></label>';
    			str +='<input type="text" name="theaterOwnerBean.theaterDetailList['+divCount+'].screenList[0].screen_name" placeholder="Screen Name" class="form-control validate[required]"/>';
                str +='</div>';

                str +='<div class="col-md-2">';
                str +='<label>Seat Count<span class="text-danger">&#42;</span> </label>';
    			str +='<input type="text" name="theaterOwnerBean.theaterDetailList['+divCount+'].screenList[0].seat_count" placeholder="Seat Count" class="form-control validate[required]"/>';
                str +='</div>';

                str +='<div class="col-md-4">';
                str +='<label>Upload Layout <small class="m-l-xs"><i class="fa fa-question-circle" title="Upload .png, .jpg and .jpeg files only"></i></small></label>';
                //str +='<span style="color:red">* Upload .png, .jpg and .jpeg files only</span>';
                str +='<input type="file" style="height:auto" name="theaterOwnerBean.theaterDetailList['+divCount+'].screenList[0].screen_layout" placeholder="Upload Layout" id="screenLayoutId_'+divCount+'" class="form-control screenImgLayoutCls">&nbsp;<span id="screenLayoutErrorId_'+divCount+'" style="color: red;">';
                str +='</div>';
                str +='<div class="col-md-2 m-t-lg">';
                str +='<a class="addScreenBtnCls"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>';
                str +='</div>';
                str +='</div>';
                str +='</div>';
                str +='</div>';
       
    			$("#theaterMainDivId").append(str);
    			
    		});
    		
             $(document).on('click',".removeTheaterCls",function(){
    			
    			var id = $(this).closest('.theaterDetailDivCls').attr('id');
    			var mainValue = id.split("_");
    			var divCount = parseInt(mainValue[1]) - 1;
    			if(divCount > 0)
    				{
    				$("#theaterMainDivId_"+mainValue[1]).remove();
    				$("#"+id).remove();
    				$('#'+mainValue[0]+'_'+divCount+' .addAnotherTheaterCls').show();
    				$('#'+mainValue[0]+'_'+divCount+' .removeTheaterCls').show();
    				}
    			else
    				{
    				$("#"+id).remove();
    				$('#'+mainValue[0]+'_'+divCount+' .addAnotherTheaterCls').show();
    				$('#'+mainValue[0]+'_'+divCount+' .removeTheaterCls').hide();
    				
    				} 
    		   });	
             
             
             $(document).on('change',".screenImgLayoutCls",function(){
          	   
            	 var id1 = $(this).attr('id');
          		 var id2 = id1.split("_");
          		 var rowId = id2[1];

          		 var mainDivId = $(this).closest('.theaterDetailDivCls').attr('id');
                 var theaterDivId = mainDivId.split("_");
                 var theaterMainDivId = theaterDivId[1];
                 
          		 var filename = $(this).closest('#screenLayoutId_'+rowId).val();

   		  		var validextensions = /(\.jpg|\.jpeg|\.gif|\.png)$/i;
   		  		if (validextensions.test(filename)) {
   		  			var sizeoffile = $(this).closest('#screenLayoutId_'+rowId)[0].files[0].size;
   		  			if (sizeoffile > 1000000) {
   		  			    $('.theaterScrnDivCls_'+theaterMainDivId+' #screenDivId_'+rowId+' #screenLayoutErrorId_'+rowId).html("Selected file size too large maximum size 1MB.");
   		  				$('#submitBtnId').attr('disabled', true);
   		  				
   		  			} else {
   		  			    $('.theaterScrnDivCls_'+theaterMainDivId+' #screenDivId_'+rowId+' #screenLayoutErrorId_'+rowId).html("");
   		  				$('#submitBtnId').attr('disabled',false);
   		  				
   		  			}
   		  		} else {
   		  		    $('.theaterScrnDivCls_'+theaterMainDivId+' #screenDivId_'+rowId+' #screenLayoutErrorId_'+rowId).html("Please select valid file (.jpeg|.png|.jpg) format");
   		  			$('#submitBtnId').attr('disabled', true);
   		  			
   		  		}
            	   
               });
             
             $(document).on('click',"#deleteTheaterBtnId",function(){
            	 
            	 $("#deleteFormSubmitId").submit();
             });
             
             $(document).on('click',"#deleteScreenBtnId",function(){
            	 
            	 $("#deleteTheaterScrnFormSubmitId").submit();
             });
             
             $(document).on('click',"#submitBtnId",function(){
            	 
            	 $("#hiddenAddNewTheaterId").val(0);
            	 $("#hiddenAddNewScreenId").val(0);
            	 $("#updateTheaterDetailFormId").submit();
             });
             
             
             
     });
     
     </script>
</head>
<body>
        
        <s:form action="delete-theater-detail" id="deleteFormSubmitId" method="post">
         <s:hidden name="theaterOwnerBean.theater_id" id="hiddenTheaterId"></s:hidden>
       </s:form>
        <s:form action="delete-screen-layout-detail" id="deleteTheaterScrnFormSubmitId" method="post">
         <s:hidden name="theaterDetailBean.theater_id" id="hiddenDelScrTheaterId"></s:hidden>
         <s:hidden name="theaterDetailBean.screen_id" id="hiddenDelScreenId"></s:hidden>
       </s:form>
       
        <div class="row wrapper border-bottom white-bg ">
                <div class="col-lg-10">
                   
                    <ol class="breadcrumb m-sm ">
                        <li>
                            <a href="#">Home</a>
                        </li>
                      
                       
                        <li class="active">
                            <strong>Edit Theatre</strong>
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
                            <h2 class="font-bold">Edit Theatre <span class="pull-right"> <a href="edit-theater"><i class="fa fa-refresh"></i></a></span></h2>
                        </div>
                         <s:if test="%{theaterOwnerBean.theater_status == 'success'}">
                          <span style="color:green;" id="successSpanId">  <h2 class="alert alert-success">Theatre is deleted Successfully...</h2></span>
                          </s:if>
                          <s:elseif test="%{theaterOwnerBean.theater_status == 'NotSuccess'}">
                              <span style="color:red;" id="errorSpanId">  <h2 class="alert alert-danger">Theatre is not deleted Successfully....</h2></span>
                          </s:elseif>
                           <s:if test="%{theaterDetailBean.screen_status == 'success'}">
                          <span style="color:green;" id="successSpanId">  <h2 class="alert alert-success">Screen is deleted successfully ...</h2></span>
                          </s:if>
                          <s:elseif test="%{theaterDetailBean.screen_status == 'NotSuccess'}">
                              <span style="color:red;" id="errorSpanId">  <h2 class="alert alert-danger">Screen is not deleted Successfully....</h2></span>
                          </s:elseif>
                          
                      <div class="ibox-content">
                      <form method="post" theme="simple" action="update-theater-detail" id="updateTheaterDetailFormId" class="form-horizontal" enctype="multipart/form-data">
                      
                        <div id="theaterMainDivId">
                        <div class="theaterDetailDivCls" id="theaterDivID_0">
                        <div class="row">
                        	<div class="col-md-6"><h2 class="blue-bg m-b-md fx-head-title" >Theatre 1 : </h2></div>
                        	<div class="col-md-6">
                        	  <s:if test="theaterOwnerBean.status == 'Yes'">
                        <a class="add_range pull-right addAnotherTheaterCls" id="addTheater_0" style=" display: none;"><button type="button" class="btn btn-primary ">Add Theatre</button></a>
                       <!--  <a class="add_range removeTheaterCls" id="removeTheater_0" style="margin-left:87%;"><button type="button" class="btn btn-danger">Delete Theater 1</button></a> -->
                  <a class="add_range pull-right removeAvailableTheaterCls" id="<s:property value="theaterOwnerBean.theaterDetailList[0].theater_id" />_<s:property value="theaterOwnerBean.theaterDetailList[0].theater_name" />" ><button type="button" class="btn btn-danger">Delete Theatre 1</button></a>
               
                </s:if>
                <s:else>
                           <a class="add_range pull-right addAnotherTheaterCls" id="addTheater_0" ><button type="button" class="btn btn-primary">Add Theatre</button></a>
                <a class="add_range pull-right removeAvailableTheaterCls" id="<s:property value="theaterOwnerBean.theaterDetailList[0].theater_id" />_<s:property value="theaterOwnerBean.theaterDetailList[0].theater_name" />" ><button type="button" style=" display: none; class="btn btn-danger">Delete Theatre 1</button></a>
                </s:else> 
                        	</div>
                        </div>
                        
                   <s:hidden name="theaterOwnerBean.theaterDetailList[0].theater_id"></s:hidden>
                   
                   <div class="form-group">
                     
                    <label  class="col-sm-3  control-label">
                        Theatre Name<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6 ">
                        <s:textfield name="theaterOwnerBean.theaterDetailList[0].theater_name" cssClass="form-control validate[required]"/>
                    </div>
                </div>


              <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        District<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                        <s:select list="districtMap" id="districtId_0" headerKey="" headerValue="Select Any District" name="theaterOwnerBean.theaterDetailList[0].district_id" cssClass="form-control districtCls validate[required]"></s:select>
                    </div>
                </div>


                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        City<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                        <s:select list="cityMap" id="cityId_0" headerKey="" headerValue="Select Any City" name="theaterOwnerBean.theaterDetailList[0].city_id" cssClass="form-control validate[required]"></s:select>
                    </div>
                </div>


                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        Address<span class="text-danger">&#42;</span> :
                    </label>
                
                    <div class="col-sm-6">
                        <s:textarea name="theaterOwnerBean.theaterDetailList[0].address" cssClass="form-control validate[required]"/>
                    </div>
                    
                </div>


                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        Theatre License Number<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                        <s:textfield name="theaterOwnerBean.theaterDetailList[0].theater_license_number" cssClass="form-control validate[required]"/>
                    </div>
                </div>

                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        Theatre TIN Number<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                        <s:textfield name="theaterOwnerBean.theaterDetailList[0].theater_tin_number" cssClass="form-control validate[required]" maxlength="11"/>
                    </div>
                </div>

                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        GST Number<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                        <s:textfield name="theaterOwnerBean.theaterDetailList[0].theater_gst_number" cssClass="form-control validate[required]" maxlength="15"/>
                    </div>
                </div>

             <h2 class="blue-bg m-b-md fx-head-title" >Screen Details </h2>
               
              <div class="theaterScrnDivCls_0">
              <s:iterator value="theaterOwnerBean.theaterDetailList[0].screenList" status="scrStatus" var="scrStat">
                    <s:hidden name="theaterOwnerBean.theaterDetailList[0].screenList[%{#scrStatus.index}].screen_id"></s:hidden>
                         
                  <div class="row screenDetailDivCls" id="screenDivId_<s:property value="%{#scrStatus.index}" />">
                    <div class="col-md-1"></div>
                      <div class="col-md-3">
                        <label>Screen Name<span class="text-danger">&#42;</span></label>
                       <s:textfield name="theaterOwnerBean.theaterDetailList[0].screenList[%{#scrStatus.index}].screen_name"  placeholder="Screen Name" cssClass="form-control validate[required]" />
                        
                      </div>

                        <div class="col-md-2">
                        <label>Seat Count<span class="text-danger">&#42;</span> </label>
                       <s:textfield name="theaterOwnerBean.theaterDetailList[0].screenList[%{#scrStatus.index}].seat_count" placeholder="Seat Count" cssClass="form-control validate[required]" />
                      </div>

                      <s:if test="%{theaterOwnerBean.theaterDetailList[0].screenList[#scrStatus.index].screen_layout_status == 'Yes'}">

                       <div class="col-md-4">
                       <label>Change Layout <small class="m-l-xs"><i class="fa fa-question-circle" title="Upload .png, .jpg and .jpeg files only"></i></small></label><br>
                       
                       <div class="row m-t-sm" id="oldScrLayoutId_<s:property value="%{#scrStatus.index}" />">	
                       		<div class="col-md-5" id="scrImageId_<s:property value="%{#scrStatus.index}" />">
                       		<img alt="layout image" src="getScreenImage?modelId=<s:property value="%{#scrStat.screen_id}" />" class="img-responsive" width="100%" height="40%">
                       		</div>
                       		<div class="col-md-10">
                       		<%-- <span style="color:red;font-size:20px;cursor:pointer" class="screenLayoutDelCls" id="screenLayoutDeleteId_<s:property value="%{#scrStatus.index}" />"><i class="fa fa-trash"></i></span> --%>
                       		<%-- <label for="screenLayoutId_<s:property value="%{#scrStatus.index}" />" class="btn btn-default m-l-sm"><i class="fa  fa-upload"></i></label> --%>
    						<s:file name="theaterOwnerBean.theaterDetailList[0].screenList[%{#scrStatus.index}].screen_layout" id="screenLayoutId_%{#scrStatus.index}" style="height:auto!important" cssClass="form-control screenImgLayoutCls"></s:file>&nbsp;<span id="screenLayoutErrorId_<s:property value="%{#scrStatus.index}" />" style="color: red;">
                       		</div>
   					  </div>
   					
   					  
                      </div>
                      </s:if>
                      <s:else>
                       <div class="col-md-4">
                       <label>Upload Layout</label>
                       
                       <s:file name="theaterOwnerBean.theaterDetailList[0].screenList[%{#scrStatus.index}].screen_layout" id="screenLayoutId_%{#scrStatus.index}" cssClass="form-control screenImgLayoutCls"></s:file>&nbsp;<span id="screenLayoutErrorId_<s:property value="%{#scrStatus.index}" />" style="color: red;">
                       <small class="text-danger">* Upload .png, .jpg and .jpeg files only</small> 
                      </div>
                      </s:else>
                      
                      <s:if test="#scrStatus.count == theaterOwnerBean.theaterDetailList[0].screen_count">
                      <div class="col-md-2 m-t-lg">
                       <s:if test="#scrStatus.count == 1">
                       <a class="addScreenBtnCls"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>
                       </s:if>
                       <s:else>
                         <a class="addScreenBtnCls"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>
                         <a class="removePerticularScreenBtnCls" id="<s:property value="theaterOwnerBean.theaterDetailList[0].theater_id" />_<s:property value="theaterOwnerBean.theaterDetailList[0].theater_name" />_<s:property value="%{#scrStat.screen_id}" />_<s:property value="%{#scrStat.screen_name}" />"><i class="fa fa-minus-square  text-danger m-r-sm fnt-size"></i></a>
                       </s:else>
                      </div>
                      </s:if>
                       <s:else>
                      <div class="col-md-2 m-t-lg">
                         <a class="addScreenBtnCls" style="display:none"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>
                         <a class="removePerticularScreenBtnCls" id="<s:property value="theaterOwnerBean.theaterDetailList[0].theater_id" />_<s:property value="theaterOwnerBean.theaterDetailList[0].theater_name" />_<s:property value="%{#scrStat.screen_id}" />_<s:property value="%{#scrStat.screen_name}" />" style="display:none"><i class="fa fa-minus-square  text-danger m-r-sm fnt-size"></i></a>
                      </div>
                      </s:else>
                  </div>
                 </s:iterator> 
                 </div>  
                </div>
                   
                <s:iterator value="theaterOwnerBean.theaterDetailList" status="status" var="stat">
                
                <s:if test="#status.index != 0">
                <div class="theaterDetailDivCls" id="theaterDivID_<s:property value="%{#status.index}" />">
                
                <div class="row">
                	<div class="col-md-6"><h2 class="blue-bg m-b-md fx-head-title" >Theater <s:property value="%{#status.count}"/> :</h2></div>
                	<div class="col-md-6">
                	<s:if test="#status.count == theaterOwnerBean.theater_count">
                <a class="add_range pull-right removeAvailableTheaterCls" id="<s:property value="%{#stat.theater_id}" />_<s:property value="%{#stat.theater_name}" />" ><button type="button" class="btn btn-danger">Delete Theatre <s:property value="%{#status.count}"/></button></a>
               <a class="add_range pull-right addAnotherTheaterCls" id="addTheater_<s:property value="%{#status.index}" />" ><button type="button" class="btn btn-primary m-r-sm">Add Theatre </button></a>
                
                </s:if>
                <s:else>
                          
                <a class="add_range pull-right removeAvailableTheaterCls" id="<s:property value="%{#stat.theater_id}" />_<s:property value="%{#stat.theater_name}" />" ><button type="button" class="btn btn-danger">Delete Theatre <s:property value="%{#status.count}"/></button></a>
                <a class="add_range pull-right addAnotherTheaterCls" id="addTheater_<s:property value="%{#status.index}" />" style=" display:none"><button type="button" class="btn btn-primary">Add Theatre</button></a>
                </s:else> 
                	
                	</div>
                </div>
                  <s:hidden name="theaterOwnerBean.theaterDetailList[%{#status.index}].theater_id"></s:hidden>
                
                   <div class="form-group">
                    <label  class="col-sm-3  control-label">
                        Theatre Name<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6 ">
                        <s:textfield name="theaterOwnerBean.theaterDetailList[%{#status.index}].theater_name" value="%{#stat.theater_name}" cssClass="form-control validate[required]"/>
                    </div>
                </div>


              <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        District<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                       <s:select list="districtMap" id="districtId_%{#status.index}" value="%{#stat.district_id}" headerKey="" headerValue="Select Any District" name="theaterOwnerBean.theaterDetailList[%{#status.index}].district_id" cssClass="form-control districtCls validate[required]"></s:select>  
                    </div>
                </div>


                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        City<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                      <s:select list="cityMap" id="cityId_%{#status.index}" value="%{#stat.city_id}" headerKey="" headerValue="Select Any City" name="theaterOwnerBean.theaterDetailList[%{#status.index}].city_id" cssClass="form-control validate[required]"></s:select>  
                    </div>
                </div>


                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        Address<span class="text-danger">&#42;</span> :
                    </label>
                
                    <div class="col-sm-6">
                        <s:textarea name="theaterOwnerBean.theaterDetailList[%{#status.index}].address" value="%{#stat.address}" cssClass="form-control validate[required]"/>
                    </div>
                    
                </div>


                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        Theatre License Number<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                        <s:textfield name="theaterOwnerBean.theaterDetailList[%{#status.index}].theater_license_number" value="%{#stat.theater_license_number}" cssClass="form-control validate[required]"/>
                    </div>
                </div>

                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        Theatre TIN Number<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                        <s:textfield name="theaterOwnerBean.theaterDetailList[%{#status.index}].theater_tin_number" value="%{#stat.theater_tin_number}" cssClass="form-control validate[required]"/>
                    </div>
                </div>

                <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                        GST Number<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                        <s:textfield name="theaterOwnerBean.theaterDetailList[%{#status.index}].theater_gst_number" value="%{#stat.theater_gst_number}" cssClass="form-control validate[required]"/>
                    </div>
                </div>

                  <div class="form-group">
                     
                    <label  class="col-sm-3 control-label">
                       Total Number of Seats<span class="text-danger">&#42;</span> :
                    </label>
                    <div class="col-sm-6">
                        <s:textfield name="theaterOwnerBean.theaterDetailList[%{#status.index}].number_of_seats" value="%{#stat.number_of_seats}" cssClass="form-control validate[required]"/>
                    </div>
                </div>

                 <h2 class="blue-bg m-b-md fx-head-title" >Screen Details </h2>
                 
                <div class="theaterScrnDivCls_<s:property value="%{#status.index}" />">
                 <s:iterator value="theaterOwnerBean.theaterDetailList[#status.index].screenList" status="scrStatus" var="varStat">
                 <s:hidden name="theaterOwnerBean.theaterDetailList[%{#status.index}].screenList[%{#scrStatus.index}].screen_id"></s:hidden>
                         
                  <div class="row screenDetailDivCls" id="screenDivId_<s:property value="%{#scrStatus.index}" />">
                    <div class="col-md-1"></div>
                      <div class="col-md-3">
                        <label>Screen Name<span class="text-danger">&#42;</span></label>
                       <s:textfield name="theaterOwnerBean.theaterDetailList[%{#status.index}].screenList[%{#scrStatus.index}].screen_name"  placeholder="Screen Name" cssClass="form-control validate[required]" />
                        
                      </div>

                        <div class="col-md-2">
                        <label>Seat Count<span class="text-danger">&#42;</span> </label>
                       <s:textfield name="theaterOwnerBean.theaterDetailList[%{#status.index}].screenList[%{#scrStatus.index}].seat_count" placeholder="Seat Count" cssClass="form-control validate[required]" />
                      </div>
                     
                      <s:if test="%{theaterOwnerBean.theaterDetailList[#status.index].screenList[#scrStatus.index].screen_layout_status == 'Yes'}">

                       <div class="col-md-4">
                       <label>Change Layout <small class="m-l-xs"><i class="fa fa-question-circle" title="Upload .png, .jpg and .jpeg files only"></i></small></label><br>
                       
                       <div class="row m-t-sm" id="oldScrLayoutId_<s:property value="%{#scrStatus.index}" />">	
                       		<div class="col-md-5" id="scrImageId_<s:property value="%{#scrStatus.index}" />">
                       		<img alt="layout image" src="getScreenImage?modelId=<s:property value="%{#varStat.screen_id}" />" class="img-responsive" width="100%" height="40%">
                       		</div>
                       		<div class="col-md-10">
                       		<%-- <span style="color:red;font-size:20px;cursor:pointer" class="screenLayoutDelCls" id="screenLayoutDeleteId_<s:property value="%{#scrStatus.index}" />"><i class="fa fa-trash"></i></span> --%>
                       		<%-- <label for="screenLayoutId_<s:property value="%{#scrStatus.index}" />" class="btn btn-default m-l-sm"><i class="fa  fa-upload"></i></label> --%>
    						<s:file name="theaterOwnerBean.theaterDetailList[%{#status.index}].screenList[%{#scrStatus.index}].screen_layout" id="screenLayoutId_%{#scrStatus.index}" style="height:auto!important;" cssClass="form-control screenImgLayoutCls"></s:file>&nbsp;<span id="screenLayoutErrorId_<s:property value="%{#scrStatus.index}" />" style="color: red;">
                       		</div>
   					  </div>
   				
                      </div>
                      </s:if>
                      <s:else>
                       <div class="col-md-4">
                       <label>Upload Layout <small class="m-l-xs"><i class="fa fa-question-circle" title="Upload .png, .jpg and .jpeg files only"></i></small></label>
                   
                       <s:file name="theaterOwnerBean.theaterDetailList[%{#status.index}].screenList[%{#scrStatus.index}].screen_layout" id="screenLayoutId_%{#scrStatus.index}" style="height:auto!important" cssClass="form-control screenImgLayoutCls"></s:file>&nbsp;<span id="screenLayoutErrorId_<s:property value="%{#scrStatus.index}" />" style="color: red;">
                          
                      </div>
                      </s:else>
                      
                      <s:if test="#scrStatus.count == theaterOwnerBean.theaterDetailList[#status.index].screen_count">
                      <div class="col-md-2 m-t-lg">
                      <s:if test="#scrStatus.count == 1">
                       <a class="addScreenBtnCls"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>
                         <a class="removePerticularScreenBtnCls" id="<s:property value="theaterOwnerBean.theaterDetailList[#status.index].theater_id" />_<s:property value="theaterOwnerBean.theaterDetailList[#status.index].theater_name" />_<s:property value="%{#varStat.screen_id}" />_<s:property value="%{#varStat.screen_name}" />" style="display:none"><i class="fa fa-minus-square  text-danger m-r-sm fnt-size"></i></a>
                       
                       </s:if>
                       <s:else>
                         <a class="addScreenBtnCls"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>
                         <a class="removePerticularScreenBtnCls" id="<s:property value="theaterOwnerBean.theaterDetailList[#status.index].theater_id" />_<s:property value="theaterOwnerBean.theaterDetailList[#status.index].theater_name" />_<s:property value="%{#varStat.screen_id}" />_<s:property value="%{#varStat.screen_name}" />"><i class="fa fa-minus-square  text-danger m-r-sm fnt-size"></i></a>
                       </s:else>
                      </div>
                      </s:if>
                      <s:else>
                      <div class="col-md-2 m-t-lg">
                         <a class="addScreenBtnCls" style="display:none"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>
                         <a class="removePerticularScreenBtnCls" id="<s:property value="theaterOwnerBean.theaterDetailList[#status.index].theater_id" />_<s:property value="theaterOwnerBean.theaterDetailList[#status.index].theater_name" />_<s:property value="%{#varStat.screen_id}" />_<s:property value="%{#varStat.screen_name}" />" style="display:none"><i class="fa fa-minus-square  text-danger m-r-sm fnt-size"></i></a>
                      </div>
                      </s:else>
                  </div>
                 </s:iterator> 
                </div>
              </div>
               </s:if>
                </s:iterator>
              </div>
                 
                <div class="form-group">
                <div class="col-sm-1 col-sm-offset-10">
                <button class="btn btn-primary m-l-md m-t-md" id="submitBtnId"> Submit</button>
                    </div>
                </div>
                    </form>
                    
                        </div>
                    </div>
                </div>
            </div>
        </div>
      
       
      <div class="modal fade revise-page-modal" id="popUpId" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header revise-page-model-header">
               
              <button type="button" class="close close1" data-dismiss="modal" aria-hidden="true">
              </button>
              <h3 class="modal-title" id="myModalLabel">
              <span id="theaterNameId"></span>
              </h3>
            </div>
            <div>
            <div class="modal-body">
              <h3 class="font-bold text-danger text-center">Are you sure you want to delete this Theatre Permanently ?</h3>
               </div>
            <div class="modal-footer">
               <button type="submit" class="btn btn-primary" id="deleteTheaterBtnId">Yes</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">No</button> 
            </div>
            </div>
          </div>
        </div>
      </div> 
      
      <div class="modal fade revise-page-modal" id="popUpId1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header revise-page-model-header">
               
              <button type="button" class="close close1" data-dismiss="modal" aria-hidden="true">
              </button>
              <h3 class="modal-title" id="myModalLabel">
              <span id="screenNameId"></span>
              </h3>
            </div>
            <div>
            <div class="modal-body">
             <h3 class="font-bold text-danger text-center">Are you sure you want to delete this Screen Permanently ?</h3>
               </div>
            <div class="modal-footer">
               <button type="submit" class="btn btn-primary" id="deleteScreenBtnId">Yes</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">No</button> 
            </div>
            </div>
          </div>
        </div>
      </div> 

</body>
</html>