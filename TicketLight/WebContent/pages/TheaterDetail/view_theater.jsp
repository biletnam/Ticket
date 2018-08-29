<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ taglib uri="/struts-tags" prefix="s"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
       .fx-head-title{
        padding: 8px;font-size: 17px;width: 30%;border-radius: 0px 3px 3px 0px;font-weight: bold;margin-left: -20px
       }

       .fx-font20{
        font-size: 20px;
       }
       .m-l{
            margin-left: -15px;
       }
        .fnt-size{
        font-size: 20px;
       }

     ul {
  list-style-type: none;
}
     </style>
<script type="text/javascript">
$(document).ready(function(){
	
	 $('.theaterDetailCls').addClass("active"); 
	 $('.addTheaterCls').addClass("active"); 
	 
	 $(document).on('change',"#theaterId",function(){
		 
		 var theaterId = $(this).val();
		 
		 jQuery.ajax({
			 data : {
				 theaterId : theaterId,
			 },
			 url  : "getJqueryTheaterdetail",
			 type : "post",
			 success: function (results){
				 
				 var page =  JSON.parse(results);
				 var screen_map = page['screenMap'];
				 
				 var str = '';
				 
				 $("#theaterNameId").empty();
				 $("#theaterNameId").append(page['theater_name']);
				 
				 $("#theaterMainDivId").empty();
				 
				 str +='<div class="col-md-12">';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3 col-xs-3" >City</label>';
				 str +='<label class="col-md-1 col-xs-1">:</label>';
				 str +='<div class="col-md-7 col-xs-7">'+page['city_name']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3 col-xs-3" >District</label>';
				 str +='<label class="col-md-1 col-xs-1">:</label>';
				 str +='<div class="col-md-7 col-xs-7">'+page['district_name']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3 col-xs-3" >Address</label>';
				 str +='<label class="col-md-1 col-xs-1">:</label>';
				 str +='<div class="col-md-7 col-xs-7">'+page['address']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3 col-xs-3" >Theatre License Number</label>';
				 str +='<label class="col-md-1 col-xs-1">:</label>';
				 str +='<div class="col-md-7 col-xs-7">'+page['theater_license_number']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3 col-xs-3 " >Theatre TIN Number</label>';
				 str +='<label class="col-md-1 col-xs-1">:</label>';
				 str +='<div class="col-md-7 col-xs-7">'+page['theater_tin_number']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3 col-xs-3" >GST Number</label>';
				 str +='<label class="col-md-1 col-xs-1">:</label>';
				 str +='<div class="col-md-7 col-xs-7">'+page['theater_gst_number']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3 col-xs-3" >Total Number of Screens</label>';
				 str +='<label class="col-md-1 col-xs-1">:</label>';
				 str +='<div class="col-md-7 col-xs-7">'+page['screen_count']+'</div>';
				 str +='</div>';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3 col-xs-3" >Total Number of Seats</label>';
				 str +='<label class="col-md-1 col-xs-1">:</label>';
				 str +='<div class="col-md-7 col-xs-7">'+page['number_of_seats']+'</div>';
				 str +='</div>';
				 str +='<div class="ibox-title">';
				 str +='<div class="row">';
				 str +='<div class="col-md-5"><h2>View Screen</h2></div>';
				 str +='<div class="col-md-7">';
				 str +='<span class="pull-right">';
				 str += '<select name='+page['screen_id']+' id="screenId" class="form-control">';
	    		 for ( var x in screen_map) 
	    		 {
	    				var obj1 = screen_map[x];
	    				
	    				if(page['screen_id'] == x){
		    				str += '<option value='+x+' selected>'+ obj1 +'</option>';
	    				}
	    				else{
		    				str += '<option value='+x+'>'+ obj1 +'</option>';

	    				}
	    	     }
	    		 str += '</select>';
				 str +='</span>';
				 str +='</div>';
				 /* str +='<div class="col-md-1">';
				 str +='<span class="pull-right m-l-md m-t-xs" ><i class="fa fa-pencil fa-2x text-success"></i></span>';
				 str +='</div>'; */
				 str +='</div>';
				 str +='</div>';
				 str +='<div class="ibox-content">';
				 str +='<form class="form-horizontal">';
				 str +='<div class="row" id="screenMainDivId">';
				 str +='<div class="col-md-12">';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3" >Screen Name</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['screen_name']+'</div>';
				 str +='</div>';
			     str +='<div class="form-group">';
			     str +='<label  class="col-md-3" >Seat Count</label>';
			     str +='<label class="col-md-1">:</label>';
			     str +='<div class="col-md-7 ">'+page['seat_count']+'</div>';
			     str +='</div>';
			     str +='<div class="form-group">';
			     str +='<label  class="col-md-3" >Layout</label>';
			     str +='<label class="col-md-1">:</label>';
			     str +='<div class="col-md-7 ">';
			     
			     if(page['screen_layout_status'] == "Yes")
                 {
			    	  str +='<img alt="layout image" src="getScreenImage?modelId='+page['screen_id']+'" class="img-responsive" width="100%" height="40%">';
			    	 /* str +='<span>image</span>'; */
			     }	
			     else{
			    	 str +='<span>No Layout</span>';
			     }
			     str +='</div>';
			     str +='</div>'; 
			     str +='</div>';
			     str +='</div>';
			     str +='</form>';
			     str +='</div>';
			     str +='</div>';
				 $("#theaterMainDivId").append(str);

			 }
		 });
	 });
	 
	 
   $(document).on('change',"#screenId",function(){
		 
	     var theaterId = $('#theaterId').val();
		 var screenId = $(this).val();
		 
		 jQuery.ajax({
			 data : {
				 theaterId : theaterId,
				 screenId  : screenId,
			 },
			 url  : "getJqueryTheaterBasicScreendetail",
			 type : "post",
			 success: function (results){
				 
				 var page =  JSON.parse(results);
				 
				 var str = '';
			     

				 $("#screenMainDivId").empty();
				 str +='<div class="col-md-12">';
				 str +='<div class="form-group">';
				 str +='<label  class="col-md-3" >Screen Name</label>';
				 str +='<label class="col-md-1">:</label>';
				 str +='<div class="col-md-7 ">'+page['screen_name']+'</div>';
				 str +='</div>';
			     str +='<div class="form-group">';
			     str +='<label  class="col-md-3" >Seat Count</label>';
			     str +='<label class="col-md-1">:</label>';
			     str +='<div class="col-md-7 ">'+page['seat_count']+'</div>';
			     str +='</div>';
			     str +='<div class="form-group">';
			     str +='<label  class="col-md-3" >Layout</label>';
			     str +='<label class="col-md-1">:</label>';
			     str +='<div class="col-md-7 ">';
			     if(page['screen_layout_status'] == "Yes")
                 {
				     /* str +='<img alt="image" style="width: 42%;height: 71px;" class="img-circle" src="getUserProfileImage?modelId='+page['screen_id']+'" />'; */ 
			    	  str +='<img alt="layout image" src="getScreenImage?modelId='+page['screen_id']+'" class="img-responsive" width="100%" height="40%">';
			     }	
			     else{
			    	 str +='<span>No Layout</span>';
			     }
			     str +='</div>';
			     str +='</div>'; 
			     str +='</div>';				 
				 $("#screenMainDivId").append(str);
			 }
		 });
     });
});

</script>

</head>
<body>

       
        <div class="row wrapper border-bottom white-bg ">
            <div class="col-lg-10">
                
                <ol class="breadcrumb m-t-xs m-b-xs">
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li class="active">
                        <a href="#">View Theatre</a>
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
                          <div class="row">
                            <div class="col-md-5">
                              <h2 class="font-bold">View Theatre</h2>
                            </div>
                           
                            <div class="col-md-7">
                             <!--  <span class="pull-right m-l-md m-t-xs"  ><i class="fa fa-pencil fa-2x text-success"></i></span> -->
                              <span class="pull-right">
                            <s:form action="edit-theater" method="post">                            
                            <button class="btn btn-success btn-outline">Update/Add theatre</button>
                            </s:form>  
                          </span>
                         
                            </div>
                          </div>
                         
                        </div>
                        <div class=" ibox-content">
                         <div class="row">
                           <div class="col-md-8">
                               <h2 class="blue-bg m-b-md fx-head-title" id="theaterNameId"> <s:property value="theaterDetailBean.theater_name"/></h2>
                           </div>
                            <div class="col-md-4">
                               <span class="pull-right">
                          <s:select list="theaterMap" id="theaterId" headerKey="" headerValue="Select Theater" name="theaterDetailBean.theater_id" cssClass="form-control"></s:select>
                          </span>
                            </div>
                         </div>
                       
     <form class="form-horizontal">
    <div class="row" id="theaterMainDivId">
      <div class="col-md-12">
       
       <%--  <div class="form-group">
              <label  class="col-md-3 col-xs-3" >
                <i class=""></i> Theater Name
              </label>
              <label class="col-md-1 col-xs-1">:</label>
                <div class="col-md-7 col-xs-7 ">
                    <s:property value="theaterDetailBean.theater_name"/>
                </div>
        </div> --%>

         <div class="form-group">
              <label  class="col-md-3 col-xs-3" >
                 City 
              </label>
              <label class="col-md-1 col-xs-1">:</label>
                <div class="col-md-7 col-xs-7">
                 <s:property value="theaterDetailBean.city_name"/>
                </div>
        </div>
          <div class="form-group">
              <label  class="col-md-3 col-xs-3" >
                 District 
              </label>
              <label class="col-md-1 col-xs-1">:</label>
                <div class="col-md-7 col-xs-7">
                 <s:property value="theaterDetailBean.district_name"/>
                </div>
        </div>
         

         <div class="form-group">
              <label  class="col-md-3 col-xs-3" >
                Address
              </label>
              <label class="col-md-1 col-xs-1">:</label>
                <div class="col-md-7 col-xs-7">
                 <s:property value="theaterDetailBean.address"/>
                </div>
        </div>

         <div class="form-group">
              <label  class="col-md-3 col-xs-3" >
                  Theatre License Number
              </label>
              <label class="col-md-1 col-xs-1">:</label>
                <div class="col-md-7 col-xs-7">
                 <s:property value="theaterDetailBean.theater_license_number"/>
                </div>
        </div>
         <div class="form-group">
              <label  class="col-md-3 col-xs-3 " >
                 Theatre TIN Number
              </label>
              <label class="col-md-1 col-xs-1">:</label>
                <div class="col-md-7 col-xs-7">
                 <s:property value="theaterDetailBean.theater_tin_number"/>
                </div>
        </div>
         <div class="form-group">
              <label  class="col-md-3 col-xs-3" >
                 GST Number 
              </label>
              <label class="col-md-1 col-xs-1">:</label>
                <div class="col-md-7 col-xs-7">
                 <s:property value="theaterDetailBean.theater_gst_number"/>
                </div>
        </div>
         <div class="form-group">
              <label  class="col-md-3 col-xs-3" >
                 Total Number of Screens
              </label>
              <label class="col-md-1 col-xs-1">:</label>
                <div class="col-md-7 col-xs-7">
                 <s:property value="theaterDetailBean.screen_count"/>
                </div>
        </div>
         <div class="form-group">
              <label  class="col-md-3 col-xs-3" >
                 Total Number of Seats
              </label>
              <label class="col-md-1 col-xs-1">:</label>
                <div class="col-md-7 col-xs-7">
                 <s:property value="theaterDetailBean.number_of_seats"/>
                </div>
        </div>
        <div class="ibox-title">
                          <div class="row">
                            <div class="col-md-5">
                              <h2>View Screen</h2>
                            </div>
                           <div class="col-md-7">
                               <span class="pull-right">
                      <s:select list="theaterDetailBean.screenMap" id="screenId" headerKey="" headerValue="Select Screen" name="theaterDetailBean.screen_id" cssClass="form-control"></s:select>
                          </span>
                            </div>
                            <%-- <div class="col-md-1">
                               <span class="pull-right m-l-md m-t-xs"  ><i class="fa fa-pencil fa-2x text-success"></i></span>
                            </div> --%>
                            </div>
                           
                         
         </div>
        <div class="ibox-content">
         <form class="form-horizontal">
           <div class="row" id="screenMainDivId">
              <div class="col-md-12">
                <div class="form-group">
              <label  class="col-md-3" >
                 Screen Name
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                   <s:property value="theaterDetailBean.screen_name"/>

                </div>
            </div>
               <div class="form-group">
              <label  class="col-md-3" >
                 Seat Count
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                    <s:property value="theaterDetailBean.seat_count"/>

                </div>
            </div>
           <div class="form-group">
              <label  class="col-md-3" >
                 Layout 
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                   <s:if test="%{theaterDetailBean.screen_layout_status == 'Yes'}">
                    <!-- <div class="row m-t-sm">	
                       		<div class="col-md-6"> -->
                       		<img alt="layout image" src="getScreenImage?modelId=<s:property value="theaterDetailBean.screen_id"/>" class="img-responsive" width="100%" height="40%">
                       	<!-- 	</div>
                    </div> -->
                   </s:if>
                   <s:else>
                    <span>No Layout</span>
                   </s:else>
                   
                </div>
            </div>   

          </div>
        </div>
      </form>
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