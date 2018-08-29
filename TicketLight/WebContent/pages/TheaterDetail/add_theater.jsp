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
 		
    	 $("#theaterDetailFormId").validationEngine(); 
    		
    		
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
    	 
    	 //add screen detail
    	  $(document).on('click',".addScreenBtnCls",function(){
    		  
    			var id = $(this).closest('.screenDetailDivCls').attr('id');
    			var mainDivId = $(this).closest('.theaterDetailDivCls').attr('id');
                var theaterDivId = mainDivId.split("_");
                var theaterMainDivId = theaterDivId[1];
    			
    			var mainValue = id.split("_");
    			$('.theaterScrnDivCls_'+theaterMainDivId+' #'+id+' .addScreenBtnCls').hide();
    			$('.theaterScrnDivCls_'+theaterMainDivId+' #'+id+' .removeScreenBtnCls').hide();
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
    		    str +='<input name="theaterOwnerBean.theaterDetailList['+theaterMainDivId+'].screenList['+divCount+'].screen_name" class="form-control validate[required]" placeholder="Screen Name" type="text">';
    			str +='</div>';
    			str +='<div class="col-md-3">';
    		    str +='<input name="theaterOwnerBean.theaterDetailList['+theaterMainDivId+'].screenList['+divCount+'].seat_count" class="form-control validate[required]" placeholder="Seat Count" type="text">';
    			str +='</div>';
    			str +='<div class="col-md-3">';
                str +='<input type="file" name="theaterOwnerBean.theaterDetailList['+theaterMainDivId+'].screenList['+divCount+'].screen_layout" placeholder="Upload Layout" id="screenLayoutId_'+divCount+'" class="form-control screenImgLayoutCls">&nbsp;<span id="screenLayoutErrorId_'+divCount+'" style="color: red;">';
                str +='</div>';
                str +='<div class="col-md-2 m-t-sm">';
                str +='<a class="addScreenBtnCls"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>';
                str +='<a class="removeScreenBtnCls"><i class="fa fa-minus-square text-danger m-r-sm fnt-size" ></i></a>';
                str +='</div>';
                str +='</div>';
              
    			$(".theaterScrnDivCls_"+theaterMainDivId).append(str);
    			
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
    				}
    			else
    				{
    				$('.theaterScrnDivCls_'+theaterMainDivId+' #'+id).remove();
    				$('.theaterScrnDivCls_'+theaterMainDivId+' #'+mainValue[0]+'_'+divCount+' .addScreenBtnCls').show();
    				$('.theaterScrnDivCls_'+theaterMainDivId+' #'+mainValue[0]+'_'+divCount+' .removeScreenBtnCls').hide();
    				
    				} 
    		   });	
    		
    		
    		//add theater
    		
    		$(document).on('click','.addAnotherTheaterCls',function(){
   			 
    			var id = $(this).attr('id'); 
    		    var id1 = id.split("_");
    			
    			divCount = parseInt(id1[1]) + 1;
    			
    			var theaterCount = divCount + 1;
    			
    			$("#addTheater_"+id1[1]).hide();
    			$("#removeTheater_"+id1[1]).hide();
    			
    			var str = '';
    			
    			str +='<div class="theaterDetailDivCls" id="theaterDivID_'+divCount+'">';
    			str +='<div class="row">';
    			str +='<div class="col-md-6">';
    			str +='<h2 class="blue-bg m-b-md fx-head-title" >Theatre '+theaterCount+'</h2>';
    			str +='</div>';
    			str +='<div class="col-md-6">';
    			str +='<a class="removeTheaterCls" id="removeTheater_'+divCount+'" style="float:right;margin-left:5px" id="remove-rest"><button type="button" class="btn btn-danger">Delete Theatre</button></a>';
    			str +='<a class="addAnotherTheaterCls" id="addTheater_'+divCount+'" style="margin-left:87%;"><button type="button" class="btn btn-primary">Add Theatre</button></a>';
    			str +='</div>';
    			str +='</div>';
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
    			str +='<label  class="col-sm-3 control-label">Theater License Number<span class="text-danger">&#42;</span> :</label>';
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
                str +='<label>Upload Layout <small class="m-l-xs"><i class="fa fa-question-circle" title="Upload .png, .jpg and .jpeg files only"></i></small> </label>';
               /*  str +='<span style="color:red">* Upload .png, .jpg and .jpeg files only</span>'; */
                str +='<input type="file" name="theaterOwnerBean.theaterDetailList['+divCount+'].screenList[0].screen_layout" placeholder="Upload Layout" id="screenLayoutId_'+divCount+'" class="form-control screenImgLayoutCls">&nbsp;<span id="screenLayoutErrorId_'+divCount+'" style="color: red;">';
                str +='</div>';
                str +='<div class="col-md-2 m-t-lg">';
                str +='<a class="addScreenBtnCls"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>';
                str +='</div>';
                str +='</div>';
                str +='</div>';
                str +='</div>';
       
    			$("#addRowTheaterDivID").append(str);
    		});
    		
               $(document).on('click',".removeTheaterCls",function(){
    			
    			var id = $(this).closest('.theaterDetailDivCls').attr('id');
    			var mainValue = id.split("_");
    			var divCount = parseInt(mainValue[1]) - 1;
    			if(divCount > 0)
    				{
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
                            <strong>Add Theatre</strong>
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
                            <h2 class="font-bold">Add Theatre</h2>
                        </div>
                      <div class="ibox-content">
           <form method="post" theme="simple" action="add-theater-detail" id="theaterDetailFormId" class="form-horizontal" enctype="multipart/form-data">
                      
                        <div id="theaterMainDivId_0">
                        <div class="theaterDetailDivCls" id="theaterDivID_0">
                         <div class="row">
                         	<div class="col-md-6">
                         	 <h2 class="blue-bg m-b-md fx-head-title" >Theatre 1</h2>
                         	</div>
                         		<div class="col-md-6">
                         		    <a class="add_range addAnotherTheaterCls pull-right" id="addTheater_0" ><button type="button" class="btn btn-primary">Add Theatre</button></a>
                         	</div>
                         </div>
                         
                        
        
                         
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
                        <s:select list="districtMap" id="cityId_0" headerKey="" headerValue="Select Any City" name="theaterOwnerBean.theaterDetailList[0].city_id" cssClass="form-control validate[required]"></s:select>
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
                  <div class="row screenDetailDivCls" id="screenDivId_0">
                    <div class="col-md-1"></div>
                      <div class="col-md-3">
                        <label>Screen Name<span class="text-danger">&#42;</span></label>
                       <s:textfield name="theaterOwnerBean.theaterDetailList[0].screenList[0].screen_name"  placeholder="Screen Name" cssClass="form-control validate[required]" />
                        
                      </div>

                        <div class="col-md-2">
                        <label>Seat Count<span class="text-danger">&#42;</span> </label>
                       <s:textfield name="theaterOwnerBean.theaterDetailList[0].screenList[0].seat_count" placeholder="Seat Count" cssClass="form-control validate[required]" />
                      </div>

                      <div class="col-md-4">
                       <label>Upload Layout <small class="m-l-xs"><i class="fa fa-question-circle" title="Upload .png, .jpg and .jpeg files only"></i></small></label>
                     <%--   <span style="color:red">* Upload .png, .jpg and .jpeg files only</span> --%> 
                       <input type="file" name="theaterOwnerBean.theaterDetailList[0].screenList[0].screen_layout" id="screenLayoutId_0" placeholder="Upload Layout" class="form-control screenImgLayoutCls">&nbsp;<span id="screenLayoutErrorId_0" style="color: red;">
                      </div>
                      <div class="col-md-2 m-t-lg">
                       <a class="addScreenBtnCls"><i class="fa fa-plus-square  text-navy m-r-sm fnt-size"></i></a>
                      </div>
                  </div>
                  </div>
                </div>
                </div>
                   <div id="addRowTheaterDivID">
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
      

</body>
</html>