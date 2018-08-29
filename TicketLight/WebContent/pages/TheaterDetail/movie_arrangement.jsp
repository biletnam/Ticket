<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                    <%@ taglib uri="/struts-tags" prefix="s"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.9.1.js"></script>
<link href="css/plugins/clockpicker/clockpicker.css" rel="stylesheet">
<link href="Dynamic/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/validationEngine.jquery.css"/>
<script src="js/jquery.validationEngine-en.js"></script>
<script src="js/jquery.validationEngine.js"></script> 
<script src="js/plugins/clockpicker.js"></script>
<script src="Dynamic/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<title>Insert title here</title>
<style type="text/css">
     .fx-font18{
      font-size: 18px;
     }
     .fx-font20{
      font-size: 20px;
      

     }
     .b-blue{
      border-color: #1c84c6 !important;
     }
     .b-danger{
      border-color: #ed5565 !important;
     }
     .b-yellow{
      border-color: #f89c59 !important;
     }
     .b-lazur{
      border-color: #23c6c8 !important;
     }
     .b-navy{
      border-color: #1ab394 !important;
     }
</style>

<script type="text/javascript">
   
   $(document).ready(function(){
     
	   $('.movieDetailCls').addClass("active"); 
	   $('.movieArrangementCls').addClass("active"); 
	   $('.clockpicker').clockpicker();
	   $('#theaterId').val('');
	   $('#screenId').val('');
	   $('#movieId').val(''); 
	   $('#dateId').val('');
	   $('#showTimeId').val('');
	   $('#languageId').val('');
	   $('#genreId').val('');
       $('#formatId').val('');
	   
	   $("#showTimeFormId").validationEngine(); 
      
      $(".dateSelect").datepicker({
     	 todayBtn: "linked",
          autoclose: true,
          format: "yyyy-mm-dd",
          startDate: new Date()
      });
      
      $(".dateSelect1").datepicker({
      	 todayBtn: "linked",
           autoclose: true,
           format: "yyyy-mm-dd",
       });
      
      $(document).on('click',".addShowTimeBtnId",function(){
    	  
    		var id = $(this).closest('.showTimeClass').attr('id');
    		
    		var mainValue = id.split("_");
    		
    		$('#'+id+' .addShowTimeBtnId').hide();
    		$('#'+id+' .removeShowTimeBtnId').hide(); 
    		var divCount = parseInt(mainValue[1]) + 1;
    		var str = '';
    		 
    	    str +='<div class="showTimeClass" style="margin-top:10px" id="showTimeDivId_'+divCount+'">';
    	    str +='<div class="input-group m-b clockpicker" data-autoclose="true">';
    	    str +='<input type="text" name="theaterOwnerBean.showTimingList['+divCount+']" placeholder="Show Timing" class="form-control validate[required]" />';
    	    str +='<span class="input-group-addon">';
    	    str +='<a class="add_range addShowTimeBtnId"><i class="fa fa-plus-square m-l-xs fx-font18 text-navy" title="Add showtiming"></i></a>';
    	    str +='<a class="add_range removeShowTimeBtnId"><i class="fa fa-minus-square m-l-sm  fx-font18 text-danger"></i></a>';
    	    str +='</span>';
    	    str +='</div>';
    	    str +='</div>';
    	    
    		$(".timeMainDivCls").append(str);
    		
    		$('.clockpicker').clockpicker();
    	});
      
      $(document).on('click',".removeShowTimeBtnId",function(){
    		
    		var id = $(this).closest('.showTimeClass').attr('id');
    		var mainValue = id.split("_");
    		var divCount = parseInt(mainValue[1]) - 1;
    		if(divCount > 0)
    			{
    			$("#"+id).remove();
    			$('#'+mainValue[0]+'_'+divCount+' .addShowTimeBtnId').show();
    			$('#'+mainValue[0]+'_'+divCount+' .removeShowTimeBtnId').show();
    			}
    		else
    			{
    			$("#"+id).remove();
    			$('#'+mainValue[0]+'_'+divCount+' .addShowTimeBtnId').show();
    			$('#'+mainValue[0]+'_'+divCount+' .removeShowTimeBtnId').hide();
    			
    			
    			} 
    	});	
      
      
            $(document).on('click',"#theaterId",function(){
    				var theaterId = $(this).val();
    				jQuery.ajax({
    					data :{
    						theaterId : theaterId
    					},
    					url : "getTheaterwiseScreenDetails",
    					type : "POST",
    					success : function(results) 
    					{
    						 
    						var page = JSON.parse(results);
    						
    						$('#screenDivId').empty();
    						
    						var str = '';
    						
    						str += '<select class="form-control validate[required]" name="theaterOwnerBean.screen_id">';
    						str += '<option value="0">--Select Screen--</option>';
    						for(var x in page)
    							{
    								var obj1 = page[x];
    								str += '<option value='+x+'>'+obj1+'</option>';
    							}
    						str += '</select>';
    						
    						$('#screenDivId').append(str);
    					}
    				
    				});
    			});
      
            $(document).on('click',".deleteShowTimingClass",function(){
          	  var show_detail = $(this).attr('id');
          	   
          	   var id = show_detail.split('_');
          	   var screenName = id[0];
 	    	   var showId = id[1];
 	    	   var showTime = id[2];
 	    	   $("#screenNameId").empty();
	    	   $("#screenNameId").append(screenName);
 	    	   $("#showTimeNameId").empty();
  	    	   $("#showTimeNameId").append("Delete "+showTime+" Show in this "+screenName);
 	    	   $("#hiddenShowTimeDetailId").val(showId);
 	    	   $("#popUpId").modal({modal: true}); 
             });
            
            $(document).on('click',"#deleteShowPopupBtnId",function(){
           	 
           	 $("#deleteShowTimeFormSubmitId").submit();
            });
            
        $(document).on('click',"#searchDateId",function(){
				var date = $("#selectShowDateId").val();
				
				jQuery.ajax({
					data :{
						date : date
					},
			 	    url : "getDatewiseSearchShowDetails", 
					type : "POST",
					success : function(results) 
					{
						 
						var page = JSON.parse(results);
						
						$('#movieListMainDivId').empty();
						
						var obj = page['theaterDetailList'];
						
						
						var str = '';	
						
						$(".showDateCls").val(page['date']);
						str += '<div class="col-md-9 ">';
						str += '<div class="row ">';
						str += '<div class="col-md-4">';
						str += '<h2 class="font-bold  m-l-md">'+page['date']+'</h2>';
						str += '</div>';
						str += '<div class="col-md-4 input-group date dateSelect1" style="float: left;margin-left: 143px;">';
						str += '<span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" name="'+page['date']+'" id="selectShowDateId" class="form-control showDateCls" placeholder="Show Date"/>';
						str += '</div>';
						str += ' <div class="col-md-2" style="margin-left: -15px;">';
						str += '<button id="searchDateId" class="btn btn-primary">Search</button>';
						str += '</div>';
						str += '</div>';
			           
						if($.isEmptyObject(obj)){
							   str += '<div class="text-center" style="margin-top:50px">';
							   str += '<img alt="Movie arrangement" src="images/armv copy.png" width="25%">';
					           str += '<h1 class="font-bold text-danger">No Show time added</h1>';
					           str += '</div>';
						}
						else{
							
							for(var x in obj){
								
								var obj1 = obj[x];
					                
						            str += '<div class="row"><h3 class="m-l-md" style="margin-top: -21px;">'+obj1['theater_name']+'-'+obj1['movie_name']+'</h3><div class="col-md-3 text-center">';
						            str += '<img  id="imgPhoto" src="getMoviePoster.action?modelId='+obj1['movie_detail_id']+'"  alt="Movie" class="image" width="90%">';
						            /* str += '<h4 class="text-center">'+obj1['movie_id']+'</h4>'; */
						             str += '<h4 class="text-center"></h4>';
						            str += '</div>';
						            str += '<div class="col-md-9 m-t-md">';
						            str += '<div class="row">';
						               
						           var obj2 = obj1['screenList'];
						           for(var j in obj2){
						        	   
							           var scrList = obj2[j];

							            str += '<div class="col-md-6 ">';
							            str += '<div class="ibox float-e-margins" >';
							            str += '<div class="ibox-title b-blue  collapse-link" >';
							            str += '<h5 class="font-bold">'+scrList['screen_name']+'</h5>';
							            str += '<div class="ibox-tools">';
							            str += '<a><i class="fa fa-chevron-up"></i></a>';
							            str += '</div>';
							            str += '</div>';
							            str += '<div class="ibox-content">'; 

							            var obj3 = scrList['showTimingList'];
								        for(var n in obj3){
								        	   
									           var showTmList = obj3[n];
									           
									          str += '<button class="btn deleteShowTimingClass" id="'+scrList['screen_name']+'_'+showTmList['show_timing_id']+'_'+showTmList['show_timing']+'">'+showTmList['show_timing']+'<i class="fa fa-times-circle text-danger"></i></button>';

								        }
							            str += '</div>';
							            str += '</div>';
							            str += '</div>';
						           }
						            str += '</div>';
						            str += '</div>';
						            str += '</div>';
							}
						}
			            str += '</div>';
			            
						$('#movieListMainDivId').append(str);
						
						$(".dateSelect1").datepicker({
			             	 todayBtn: "linked",
			                  autoclose: true,
			                  format: "yyyy-mm-dd",
			        });
					}
				
				});
			});
   });
   
</script>
</head>
<body>

   <s:form action="delete-show-time" id="deleteShowTimeFormSubmitId" method="post">
     <s:hidden name="theaterOwnerBean.show_timing_id" id="hiddenShowTimeDetailId"></s:hidden>
   </s:form>
       
        <div class="row wrapper border-bottom white-bg ">
                <div class="col-lg-10">
                   
                    <ol class="breadcrumb m-sm ">
                        <li>
                            <a href="#">Home</a>
                        </li>
                      
                       
                        <li class="active">
                            <strong>Movie Arrangement</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
            </div>
            
             <s:if test="%{theaterOwnerBean.show_time_status == 'success'}">
                  <span style="color:green;" id="successSpanId">  <h2 class="alert alert-success">Theatre is deleted Successfully...</h2></span>
                          </s:if>
                   <s:elseif test="%{theaterOwnerBean.show_time_status == 'NotSuccess'}">
                              <span style="color:red;" id="errorSpanId">  <h2 class="alert alert-danger">Theatre is not deleted Successfully....</h2></span>
              </s:elseif>
      
         <div class="row ibox-content gray-bg">
                <div class="col-md-3 " >
                 
                 <h3> Movie Arrangements</h3>
                 <form action="addMovieScreenShowTimeDetail" id="showTimeFormId" method="post" class="form-horizontal">
                   <div class="form-group">
                      <label>Theatre :</label>
                 <s:select list="theaterMap" id="theaterId" headerKey="" headerValue="Select Theater" name="theaterOwnerBean.theater_id" cssClass="form-control validate[required]"></s:select>
                  </div>

                  <div class="form-group">
                      <label>Screen :</label>
                      <div id="screenDivId">
                      <s:select list="theaterMap" id="screenId" headerKey="" headerValue="Select Screen" name="theaterOwnerBean.screen_id" cssClass="form-control validate[required]"></s:select>
                      </div>
                  </div>

                   <div class="form-group">
                      <label>MovieId :</label>
                   <s:select list="movieIdMap" id="movieId" headerKey="" headerValue="Select " name="theaterOwnerBean.movie_details_id" cssClass="form-control validate[required]"></s:select>
                  </div>

                    <div class="form-group">
                      <label>Date :</label>
                      <div class="input-group date dateSelect">
                      <span class="input-group-addon"><i class="fa fa-calendar"></i></span><s:textfield name="theaterOwnerBean.show_date" id="dateId" cssClass="form-control validate[required]" placeholder="Show Date"/>
                     </div>
                      <!-- <input type="date" name="" class="form-control" > -->
                  </div>

                  <div class="form-group">
                    <label>Show Timing  </label>
                    <!-- <input type="text" name="" class="form-control"> -->
                    <div class="timeMainDivCls">
                    <div class="showTimeClass" id="showTimeDivId_0">
                     <div class="input-group m-b clockpicker" data-autoclose="true">
                     <s:textfield name="theaterOwnerBean.showTimingList[0]" placeholder="Show Timing" id="showTimeId" cssClass="form-control validate[required]" />
                     <span class="input-group-addon"><a class="add_range addShowTimeBtnId"><i class="fa fa-plus-square m-l-xs fx-font18 text-navy" title="Add showtiming"></i></a></span></div>
                     </div>
                     </div>
                     <%-- <div class="input-group m-b clockpicker" data-autoclose="true"><input type="text" class="form-control"> <span class="input-group-addon"><i class="fa fa-plus-square fx-font18 m-l-xs text-navy"></i> <i class="fa fa-minus-square m-l-sm  fx-font18 text-danger"></i></span></div> --%>
                  </div>

                  <div class="form-group">
                      <label>Language :</label>
              <s:select list="movieLangMap" id="languageId" headerKey="" headerValue="Select Language" name="theaterOwnerBean.movie_language_id" cssClass="form-control validate[required]"></s:select>
                  </div>

                   <div class="form-group">
                      <label>Genre :</label>
              <s:select list="movieGenreMap" id="genreId" headerKey="" headerValue="Select Genre" name="theaterOwnerBean.movie_genre_id" cssClass="form-control validate[required]"></s:select>

                  </div>

                   <div class="form-group">
                      <label>Format :</label>
                     <s:select list="movieFormatMap" id="formatId" headerKey="" headerValue="Select Format" name="theaterOwnerBean.movie_format_id" cssClass="form-control validate[required]"></s:select>

                  </div>

                  <div class="form-group">
                    <button type="submit" class="btn btn-primary pull-right">Submit</button>
                  </div>


                 </form>
               </div>



             <div id="movieListMainDivId">
               <div class="col-md-9 ">
               <div class="row ">
               <div class="col-md-4">
                 <h2 class="font-bold  m-l-md">Today</h2>
               </div>
                 <div class="col-md-4 input-group date dateSelect1" style="float: left;margin-left: 143px;">
                  <span class="input-group-addon"><i class="fa fa-calendar"></i></span> <s:textfield name="theaterOwnerBean.date" id="selectShowDateId" cssClass="form-control validate[required]" placeholder="Show Date"/>
                 
                 </div>
                 <div class="col-md-2" style="margin-left: -15px;">
                 <button id="searchDateId" class="btn btn-primary">Search</button>
                 </div>
               </div>
               
                <s:if test="%{theaterOwnerBean.theaterDetailList.isEmpty()}">
                
                 <div class="text-center" style="margin-top:50px">
                 <img alt="Movie arrangement" src="images/armv copy.png" width="25%">
                 <h1 class="text-danger font-bold">No Show time added</h1>
                 </div>
  					</s:if>
               <s:else>
               
                <s:iterator value="theaterOwnerBean.theaterDetailList" status="scrStatus" var="varStat">
                
               <div class="row">
               
                    <div class="col-md-3 text-center">
                     <img  id="imgPhoto" src="getMoviePoster.action?modelId=<s:property value="movie_detail_id" />"  alt="Movie" class="image" width="90%">
                        <%-- <h4 class="text-center"><s:property value="movie_id"/></h4> --%>
                        <h4 class="text-center"></h4>
                    </div>
                    <div class="col-md-9 m-t-md">
                     <div class="row">
                       <h3 class="m-l-md" style="margin-top: -21px;"><s:property value="theater_name"/> <span>-</span><s:property value="movie_name"/></h3>
                    <s:iterator value="screenList">
                     
                      <div class="col-md-6 ">
                       <div class="ibox float-e-margins collapsed ">
                        <div class="ibox-title b-blue  collapse-link" >
                            <h5 class="font-bold"><s:property value="screen_name"/></h5>
                            <div class="ibox-tools">
                                <a><i class="fa fa-chevron-up"></i></a>
                             </div>
                        </div>
                    <div class="ibox-content">    
                                    
                       <s:iterator value="showTimingList">
                       
                       <button class="btn deleteShowTimingClass" id="<s:property value="screen_name"/>_<s:property value="show_timing_id"/>_<s:property value="show_timing"/>"><s:property value="show_timing"/><i class="fa fa-times-circle text-danger"></i></button>
                       </s:iterator>
                     <!--   <button class="btn "> 11:00am <i class="fa fa-times-circle text-danger"></i></button>
                       <button class="btn "> 2:00pm <i class="fa fa-times-circle text-danger"></i></button>
                       <button class="btn "> 6:00pm <i class="fa fa-times-circle text-danger"></i></button> -->

                        </div>
                    </div>
                      </div>
                      </s:iterator>

                     <!--  <div class="col-md-6 ">
                       <div class="ibox float-e-margins collapsed">
                        <div class="ibox-title b-yellow  collapse-link">
                            <h5 class="font-bold">Screen 2</h5>
                            <div class="ibox-tools">
                                <a><i class="fa fa-chevron-up"></i></a>
                             </div>
                        </div>
                        <div class="ibox-content">                           
                          
                       <button class="btn "> 7:00am <i class="fa fa-times-circle text-danger"></i></button>
                       <button class="btn "> 11:00am <i class="fa fa-times-circle text-danger"></i></button>
                       <button class="btn "> 2:00pm <i class="fa fa-times-circle text-danger"></i></button>
                       <button class="btn "> 6:00pm <i class="fa fa-times-circle text-danger"></i></button>
                          </div>
                       
                    </div>
                      </div>


                      <div class="col-md-6 ">
                       <div class="ibox float-e-margins collapsed">
                        <div class="ibox-title b-navy  collapse-link">
                            <h5 class="font-bold">Screen 4</h5>
                            <div class="ibox-tools">
                                <a><i class="fa fa-chevron-up"></i></a>
                             </div>
                        </div>
                        <div class="ibox-content">                           
                          
                              <button class="btn "> 7:00am <i class="fa fa-times-circle text-danger"></i></button>
                       <button class="btn "> 11:00am <i class="fa fa-times-circle text-danger"></i></button>
                       <button class="btn "> 2:00pm <i class="fa fa-times-circle text-danger"></i></button>
                       <button class="btn "> 6:00pm <i class="fa fa-times-circle text-danger"></i></button>
                          </div>
                        
                    </div>
                      </div> -->
                      </div>
                   
                    </div>
            </div>
             </s:iterator>  
               </s:else>
               
                  

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
              <span id="showTimeNameId"></span>
              </h3>
            </div>
            <div>
            <div class="modal-body">
              <h3 class="font-bold text-danger text-center">Are you sure you want to delete this Show Time Permanently ?</h3>
               </div>
            <div class="modal-footer">
               <button type="submit" class="btn btn-primary" id="deleteShowPopupBtnId">Yes</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">No</button> 
            </div>
            </div>
          </div>
        </div>
      </div> 

 <script>
      
      $("#showTimeFormId").validationEngine({scroll: false});
      
      </script>
</body>
</html>