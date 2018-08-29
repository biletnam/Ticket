<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%@ taglib uri="/struts-tags" prefix="s" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="img/favicon.png">
    <link href="Dynamic/css/bootstrap.min.css" rel="stylesheet">
    <link href="Dynamic/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="Dynamic/css/animate.css" rel="stylesheet">
    <link href="Dynamic/css/style.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <link href="Dynamic/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <script src="Dynamic/js/plugins/datapicker/bootstrap-datepicker.js"></script>
    
    
<style type="text/css">
     td{
        padding: 5px;
     }
     .footer {
    background: none repeat scroll 0 0 white;
    border-top: 1px solid #e7eaec;
    bottom: 0;
    left: 0;
    padding: 10px 20px;
    position: fixed!important;
    right: 0;
}
@media only screen and (max-width: 600px) {
     .footer {
    background: none repeat scroll 0 0 white;
    border-top: 1px solid #e7eaec;
    bottom: -129px;
    left: 0;
    padding: 10px 20px;
    position: absolute!important;
    right: 0;
}
.m-l15{
  margin-left: 152px;
}

}
.b2{
      border-left: 2px solid;
      margin-top:15px;
}

.table > tbody > tr > td{
border-top:none;
}

</style>
</head>
<script type="text/javascript">
$(document).ready(function(){
	/* showTimingCls */
	$(document).on('click',".showTimingCls",function(){
		var id = $(this).attr("id");
		var res = id.split("_");
		var screen_id=res[0];
		var theatre_id=res[1];
		var show_time_id=res[2];
		var movie_details_id=res[3];
		var show_details_id=res[4];
		
		jQuery.ajax
		 ({
			 	data :{
					theaterIdVal : theatre_id,
					scrId : screen_id,
					showTimeId:show_time_id,
					movieDetailsId:movie_details_id,
					showDetailId:show_details_id,
					
				},
				url : "getCounterScreenTicketBookingLayout",
				type : "POST",
				success : function(results) 
				{
					var page = JSON.parse(results);
					var finalList = page['theaterRowList'];
					
					if($.isEmptyObject(finalList))
					{
						$('#floorDivId').empty();
						var str = 'The Screen have no Floor plan';
						$('#floorDivId').append(str);
						$('#floorDivId').show();
						$('#emptyDivId').hide();
					}
					else
					{
					var str = '';
					
					$('#emptyDivId').hide();
					$('#showDivId').show();
					$('#floorDivId').empty();
					
					str += '<div class="col-md-12">';
					str += '<div class="row m-t-md">';
					str += '</div>';
					str += '<div class="row">';
					str += '<div class="col-md-offset-1 col-md-10">';
					str += '<div class="table-responsive"> ';
					str += '<table class="table bdrnun" >';
					str += '<tbody class="text-center">';
					
					for(var x in finalList)
					{
						var obj1 = finalList[x];
						
						str += '<tr>';
						str += '<td><h4 >'+obj1['category_name']+'</h4></td>';
						
						for(var i = 0;i<obj1['col_count'];i++)
						{
							
							var obj2 = obj1['screenList'];
							for(var y in obj2)
							{
								str += '<td></td>';
								str += '<td></td>';
								str += '<td></td>';
								str += '<td></td>';
								var obj3 = obj2[y];
								var loopVal = parseInt(obj1['total_seat_count'])-parseInt(obj3['seat_count']);
								var stList = obj3['seatList'];

								 
								if(obj3['order_id']==1)
								{
									if(loopVal!=0)
									{
										for(var k = 0;k<loopVal;k++)
										{
											str += '<td></td>';
										}
									
										for(var st in stList)
										{
											var lay_seat = stList[st];

											if(lay_seat['seat_status_id'] == 3){
												
												str +='<td><img src="img/taken.png"></td>';
											}
											else{
												
												str +='<td><a class="availableCls selectSeatCls_'+lay_seat['seat_name_str']+'" id='+lay_seat['seat_name_str']+'><div class="span_'+lay_seat['seat_name_str']+'" id="1"><img src="img/available.png"></div></a></td>'; 

											}

										} 
										
										/*  for(var j = 0;j<obj3['seat_count'];j++)
										{
											
											 str +='<td><a class="availableCls selectSeatCls_'+j+'" id='+j+'><div class="span_'+j+'" id='+j+'><img src="img/available.png"></div></a></td>'; 
										}  */
									}
									else if(loopVal==0)
									{
										
											for(var st in stList)
											{
												var lay_seat = stList[st];

												if(lay_seat['seat_status_id'] == 3){
													
													str +='<td><img src="img/taken.png"></td>';
												}
												else{
													
													str +='<td><a class="availableCls selectSeatCls_'+lay_seat['seat_name_str']+'" id='+lay_seat['seat_name_str']+'><div class="span_'+lay_seat['seat_name_str']+'" id="1"><img src="img/available.png"></div></a></td>'; 

												}
												
											}
											
										/*  for(var j = 0;j<obj3['seat_count'];j++)
										{											
											
										 str +='<td><a class="availableCls selectSeatCls_'+j+'" id=j><div class="span_'+j+'" id='+j+'><img src="img/available.png"></div></a></td>'; 
										} */
									}
								}
								else if(obj3['order_id']==2)
								{
									if(loopVal!=0)
									{
										for(var st in stList)
										{
											var lay_seat = stList[st];
											
                                            if(lay_seat['seat_status_id'] == 3){
												
												str +='<td><img src="img/taken.png"></td>';
											}
											else{
												
												str +='<td><a class="availableCls selectSeatCls_'+lay_seat['seat_name_str']+'" id='+lay_seat['seat_name_str']+'><div class="span_'+lay_seat['seat_name_str']+'" id="1"><img src="img/available.png"></div></a></td>'; 

											}
										}
										
										/* for(var j = 0;j<obj3['seat_count'];j++)
										{
											
											str +='<td><a class="availableCls selectSeatCls_'+j+'" id='+j+'><div class="span_'+j+'" id='+j+'><img src="img/available.png"></div></a></td>'; 
										} */
										for(var k = 0;k<loopVal;k++)
										{
											str += '<td></td>';
										}
									}
									else if(loopVal==0)
									{
										for(var st in stList)
										{
											var lay_seat = stList[st];

                                            if(lay_seat['seat_status_id'] == 3){
												
												str +='<td><img src="img/taken.png"></td>';
											}
											else{
												
												str +='<td><a class="availableCls selectSeatCls_'+lay_seat['seat_name_str']+'" id='+lay_seat['seat_name_str']+'><div class="span_'+lay_seat['seat_name_str']+'" id="1"><img src="img/available.png"></div></a></td>'; 

											}
									 }
										
										/* for(var j = 0;j<obj3['seat_count'];j++)
										{
											
											str +='<td><a class="availableCls selectSeatCls_'+j+'" id='+j+'><div class="span_'+j+'" id='+j+'><img src="img/available.png"></div></a></td>'; 
										} */
									}
								}
								
								
							}
						}
						str += '<td><h4 >'+obj1['category_name']+'</h4></td>';
						str += '</tr>';
					}
					str += '</tbody>';
					str += '</table>';
					str += '</div>';
					str += '</div>';
					str += '</div>';
					str += '<div class="row">';
					str += '<div class="col-md-offset-2 col-md-8 text-center">';
					str += '<img src="images/screen.png" class="img-responsive" >';
					str += '</div>';
					str += '</div>';
					str += '</div>';
					
					
					$('#floorDivId').append(str);
					$('#floorDivId').show();
					}
	
					
				}

	});
		});
});
</script>
<script type="text/javascript">

	$(document).ready(function()
	{
		
		 $(".dateSelect").datepicker({
	    	 todayBtn: "linked",
	         autoclose: true,
	         format: "yyyy-mm-dd",
	         startDate: new Date()
	     });
		 
		var totalSeatCount = 0;
		var showTime = "";
		
		$(document).on('click',".showTimingCls",function(){
			 showTime = $(this).attr('id');
		});
		
	  	$(document).on('click',".availableCls",function(){

			$('#selectedSeatId').show();

			var id = $(this).attr("id");
			var spanId = $('.span_'+id).attr('id');
			var str = '';
			var str1 = '';

			totalSeatCount = 0;
			if(spanId == 1){
				
				$('.selectSeatCls_'+id+' .span_'+id).remove();
				
				str += '<div class="span_'+id+'" id="2"><img src="img/selected.png"></div>';
			   
				$('.selectSeatCls_'+id).append(str);  
				
				str1 +='<a style="margin-right: 13px;" class="btn btn-outline btn-danger selSeatBtnCls S_'+id+'" id="'+id+'">'+ id +'</a>';
				
				$('#selectedSeatShowId').append(str1);
				
				 $('.selSeatBtnCls').each(function()
		 		{
					 totalSeatCount ++; 
		 		});
				 

				var rupee = totalSeatCount*120;
				   
				$("#totalAmountId").empty();
				$("#totalAmountId").append(rupee);
			}
			else if(spanId == 2){
				
				$('.selectSeatCls_'+id+' .span_'+id).remove();
				
				str += '<div class="span_'+id+'" id="1"><img src="img/available.png"></div>';
				
				$('.selectSeatCls_'+id).append(str);  
				$('#selectedSeatShowId .S_'+id).remove();
				
				$("#totalAmountId").append();
				
				 $('.selSeatBtnCls').each(function()
				{
					 totalSeatCount ++; 
				});
				 
			    var rupee = totalSeatCount*120;
			    
				$("#totalAmountId").empty();
				$("#totalAmountId").append(rupee);
			}
			
		});
	
		
  	  $(document).on('click',"#makePaymentBtnId",function(){
               
		   
  		 		   
  		   if(toatalAmount == 0 || totalSeatCount == 0){
  			   
  		   }
  		   else if(showTime == ""){
  			  
  		   }
  		   else{
  	  		var date = $("#dateId").val();
  	  	    
		 
  	  		var seat = []; 
  	  	    totalSeatCount = 0;
   		    $('.selSeatBtnCls').each(function()
  		    {
   			   var seatCount = $(this).attr('id');
   			   seat.push(seatCount);
   			   totalSeatCount ++; 
  		    });
   		   
		   var toatalAmount = totalSeatCount*120;
		   var seatName = seat.toString();
		   
   		   
   		   $("#hiddenDateId").val(date);
   		   $("#hiddenShowTimeId").val(showTime);
   		   $("#hiddenSeatCountId").val(totalSeatCount);
   		   $("#hiddenSeatNameStrId").val(seatName);
   		   $("#hiddenTotalAmountId").val(toatalAmount);
   		   $("#makePaymentSubFormId").submit();
   		   
  		   }
  		   
  	  });
		
		
	});

</script>

<body class="top-navigation">

<s:form action="ticket-payment-submit" id="makePaymentSubFormId" method="post">
<s:hidden name="ticketCounterBean.movie_date" id="hiddenDateId"></s:hidden>
<s:hidden name="ticketCounterBean.show_timing" id="hiddenShowTimeId"></s:hidden>
<s:hidden name="ticketCounterBean.seat_count" id="hiddenSeatCountId"></s:hidden>
<s:hidden name="ticketCounterBean.seat_name_str" id="hiddenSeatNameStrId"></s:hidden>
<s:hidden name="ticketCounterBean.total_amount" id="hiddenTotalAmountId"></s:hidden>
</s:form>

<div id="wrapper">
        <div id="page-wrapper" class="white-bg">
        <div class="row border-bottom white-bg">
        <nav class="navbar navbar-static-top" role="navigation">
            <div class="navbar-header">
                <button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                    <i class="fa fa-reorder"></i>
                </button>
                <a><img src="img/ticketlite.png"  width="12%" class="m-t-sm m-b-xs m-l-lg"></a>
            </div>
            <div class="navbar-collapse collapse pull-right" id="navbar">
                <ul class="nav navbar-nav">
                    
                    <li class="dropdown">
                        <a aria-expanded="false" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                                <%-- <span class="caret pull-right m-t-md m-l-sm"></span> --%>
                                 <span > <img alt="image" style="width: 50px;height: 50px;"  class="img-circle" src="img/Profile.png" />
                                 </span>
                          
                            <span class="m-t-sm">
                                <b>Murugesan</b>
                              </span>
                         </a>
                         
                    </li>
                   

                </ul>
              
            </div>
        </nav>
        </div>
          <div class="row wrapper">
            <div class="row">
                <div class="col-md-12 col-sm-6 col-xs-4 m-l-md">
                    <h2 class="font-bold text-black"><s:property value="theaterOwnerBean.movie_name"/></h2 >
                </div>
                
            </div>
            <div class="col-md-2 col-sm-12 col-xs-12 text-center">

            <!-- <img src="img/11.jpg" alt="movie" class="img-responsive img-thumbnail" width="78%" > -->
            
            <img style="height: 160px;width: 145px;" id="imgPhoto" src="getMoviePosterForCounterPerson.action?modelId=<s:property value="theaterOwnerBean.movie_details_id" />"  alt="Movie" class="image img-thumbnail">
            
            
            <h4 class="text-center"><s:property value="theaterOwnerBean.movie_id"/></h4>
               
            </div>
           <%--  <div class="col-md-2">
          <h3><b>Screen</b></h3>
          <div class="form-group">
          
          <select class=form-control>
          <option value="1">Screen</option>
          <option value="2">Screen 1</option>
          <option value="3">Screen 2</option>
          <option value="4">Screen 3</option>
          </select>
          </div>
          </div> --%>
         <%--    <div class="col-md-2 col-sm-12 col-xs-12 pull-left">
                <h3><b>Date</b></h3>
                <div class="input-group date dateSelect">
               <span class="input-group-addon"><i class="fa fa-calendar"></i></span> <input placeholder="select date" type="text" class="form-control" id="dateId" name="">
               </div>
          </div> --%>
             <div class="col-md-2 col-sm-12 col-xs-12 pull-left">
                <h3><b>Date</b></h3>
                <s:select list="movieDateMap" cssClass="form-control" theme="simple"/>
                <%--  <select class=form-control>
          <option value="1">13/08/2018</option>
          <option value="2">13/08/2018</option>
          <option value="3">13/08/2018</option>
          <option value="4">13/08/2018</option>
          </select> --%>
          </div>
          
          <div class="col-md-6 col-sm-7 col-xs-7">
                <h3><b>Showtime</b></h3>
              
                <s:iterator value="movieShowList">
                <button class="btn btn-outline btn-danger m-r-sm showTimingCls" id="<s:property value="screen_id"/>_<s:property value="theater_id"/>_<s:property value="show_timing_id"/>_<s:property value="movie_details_id"/>_<s:property value="show_detail_id"/>"><s:property value="show_time"/></button>
                </s:iterator>

               <!--  <button class="btn btn-outline btn-danger m-r-sm showTimingCls" id="9:00 Am">9:00 Am</button>
                <button class="btn btn-outline btn-danger m-r-sm showTimingCls" id="12:00 Am">12:00 Am</button>
                <button class="btn btn-outline btn-danger m-r-sm showTimingCls" id="5:00 Am">5:00 Am</button>
                <button class="btn btn-outline btn-danger m-r-sm showTimingCls" id="10:00 Am">10:00 Am</button> -->
           <!--      <button class="btn btn-outline btn-danger m-r-sm">9:00 Am</button>
                <button class="btn btn-outline btn-danger m-r-sm">9:00 Am</button>
                <button class="btn btn-outline btn-danger m-r-sm">9:00 Am</button> -->

          </div>
       </div>

         <hr>

          <!-- layout -->

<div class="row" id="showDivId"  style="display: none;">
    <div class="col-md-offset-1 col-md-10 text-center">
       <div class="col-md-4 col-sm-2 col-xs-4 text-center">
            <img src="img/taken.png" alt="selected">
            <b>OCCUPIED</b>
        </div>
        <div class="col-md-4 col-sm-2 col-xs-4 text-center">
            <img src="img/available.png" alt="available">
            <b>AVAILABLE</b>
        </div>
        <div class="col-md-4 col-sm-2 col-xs-4 text-center">
            <img src="img/selected.png" alt="taken">
            <b>SELECTED</b>
        </div>
    </div>
</div>

<div id="emptyDivId" class="text-center">
<img src="images/armv copy.png" alt="select the show" width="17%">
<h2 class="font-bold text-danger"> Choose the Showtime...!</h2>
</div>


  <div id="floorDivId" style="display: none;">
      <div class="container">
      <div class="row m-t-md">
       <div class="col-md-offset-1 col-md-10 text-center">
      
        <div class="table-responsive">
         <table>
            <tbody class="text-center">
                   <tr>
                       <td><h4>A</h4></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                      <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><h4>A</h4></td>
                   </tr>
                   <tr>
                        <td><h4>B</h4></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td><a><img src="img/selected.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>B</h4></td>
                   </tr>
                   <tr>
                        <td><h4>C</h4></td>
                       <td><a class="availableCls selectSeatCls_45" id="45"><div class="span_45" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_46" id="46"><div class="span_46" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_47" id="47"><div class="span_47" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_48" id="48"><div class="span_48" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_49" id="49"><div class="span_49" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_50" id="50"><div class="span_50" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_51" id="51"><div class="span_51" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_52" id="52"><div class="span_52" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_53" id="53"><div class="span_53" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_54" id="54"><div class="span_54" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_55" id="55"><div class="span_55" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_56" id="56"><div class="span_56" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_57" id="57"><div class="span_57" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_58" id="58"><div class="span_58" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_59" id="59"><div class="span_59" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_60" id="60"><div class="span_60" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_61" id="61"><div class="span_61" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_62" id="62"><div class="span_62" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_63" id="63"><div class="span_63" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>C</h4></td>
                   </tr>
                   <tr>
                        <td><h4>D</h4></td>
                       <td><a class="availableCls selectSeatCls_64" id="64"><div class="span_64" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_65" id="65"><div class="span_65" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_66" id="66"><div class="span_66" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_67" id="67"><div class="span_67" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_68" id="68"><div class="span_68" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_69" id="69"><div class="span_69" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_70" id="70"><div class="span_70" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_71" id="71"><div class="span_71" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_72" id="72"><div class="span_72" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_73" id="73"><div class="span_73" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_74" id="74"><div class="span_74" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_75" id="75"><div class="span_75" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_76" id="76"><div class="span_76" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_77" id="77"><div class="span_77" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_78" id="78"><div class="span_78" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_79" id="79"><div class="span_79" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_80" id="80"><div class="span_80" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_81" id="81"><div class="span_81" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_82" id="82"><div class="span_82" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>D</h4></td>
                   </tr>
                   <tr>
                        <td><h4>E</h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_83" id="83"><div class="span_83" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_84" id="84"><div class="span_84" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_85" id="85"><div class="span_85" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_86" id="86"><div class="span_86" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_87" id="87"><div class="span_87" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_88" id="88"><div class="span_88" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_89" id="89"><div class="span_89" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_90" id="90"><div class="span_90" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_91" id="91"><div class="span_91" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_92" id="92"><div class="span_92" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_93" id="93"><div class="span_93" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_94" id="94"><div class="span_94" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_95" id="95"><div class="span_95" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_96" id="96"><div class="span_96" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_97" id="97"><div class="span_97" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_98" id="98"><div class="span_98" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_99" id="99"><div class="span_99" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>E</h4></td>
                   </tr>
                   <tr>
                         <td><h4>F</h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_100" id="100"><div class="span_100" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_101" id="101"><div class="span_101" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_102" id="102"><div class="span_102" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_103" id="103"><div class="span_103" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_104" id="104"><div class="span_104" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_105" id="105"><div class="span_105" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_106" id="106"><div class="span_106" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_107" id="107"><div class="span_107" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_108" id="108"><div class="span_108" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_109" id="109"><div class="span_109" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_110" id="110"><div class="span_110" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_111" id="111"><div class="span_111" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_112" id="112"><div class="span_112" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_113" id="113"><div class="span_113" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_114" id="114"><div class="span_114" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_115" id="115"><div class="span_115" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_116" id="116"><div class="span_116" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>F</h4></td>
                   </tr>
                   <tr>
                         <td><h4>G</h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_117" id="117"><div class="span_117" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_118" id="118"><div class="span_118" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_119" id="119"><div class="span_119" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_120" id="120"><div class="span_120" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_121" id="121"><div class="span_121" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_122" id="122"><div class="span_122" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_123" id="123"><div class="span_123" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_124" id="124"><div class="span_124" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_125" id="125"><div class="span_125" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_126" id="126"><div class="span_126" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_127" id="127"><div class="span_127" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_128" id="128"><div class="span_128" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_129" id="129"><div class="span_129" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_130" id="130"><div class="span_130" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_131" id="131"><div class="span_131" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_132" id="132"><div class="span_132" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_133" id="133"><div class="span_133" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>G</h4></td>
                   </tr>
                   <tr>
                         <td><h4>H</h4></td>
                      <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_134" id="134"><div class="span_134" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_135" id="135"><div class="span_135" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_136" id="136"><div class="span_136" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_137" id="137"><div class="span_137" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_138" id="138"><div class="span_138" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_139" id="139"><div class="span_139" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_140" id="140"><div class="span_140" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_141" id="141"><div class="span_141" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_142" id="142"><div class="span_142" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_143" id="143"><div class="span_143" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_144" id="144"><div class="span_144" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_145" id="145"><div class="span_145" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_146" id="146"><div class="span_146" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_147" id="147"><div class="span_147" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_148" id="148"><div class="span_148" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_149" id="149"><div class="span_149" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_150" id="150"><div class="span_150" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>H</h4></td>
                   </tr>
                   <tr>
                         <td><h4>I</h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_151" id="151"><div class="span_151" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_152" id="152"><div class="span_152" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_153" id="153"><div class="span_153" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_154" id="154"><div class="span_154" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_155" id="155"><div class="span_155" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_156" id="156"><div class="span_156" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_157" id="157"><div class="span_157" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_158" id="158"><div class="span_158" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_159" id="159"><div class="span_159" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_160" id="160"><div class="span_160" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_161" id="161"><div class="span_161" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_162" id="162"><div class="span_162" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_163" id="163"><div class="span_163" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_164" id="164"><div class="span_164" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_165" id="165"><div class="span_165" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_166" id="166"><div class="span_166" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_167" id="167"><div class="span_167" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>I</h4></td>
                   </tr>  
                   <tr>
                         <td><h4>J</h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_168" id="168"><div class="span_168" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_169" id="169"><div class="span_169" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_170" id="170"><div class="span_170" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_171" id="171"><div class="span_171" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_172" id="172"><div class="span_172" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_173" id="173"><div class="span_173" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_174" id="174"><div class="span_174" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_175" id="175"><div class="span_175" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_176" id="176"><div class="span_176" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_177" id="177"><div class="span_177" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>J</h4></td>
                   </tr>
                   <tr>
                       
                   </tr>
                   <tr>
                       
                   </tr>
                   <tr>
                       
                   </tr>
                   <tr>
                        <td><h4>K</h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_178" id="178"><div class="span_178" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_179" id="179"><div class="span_179" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_180" id="180"><div class="span_180" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_181" id="181"><div class="span_181" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_182" id="182"><div class="span_182" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_183" id="183"><div class="span_183" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_184" id="184"><div class="span_184" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_185" id="185"><div class="span_185" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_186" id="186"><div class="span_186" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_187" id="187"><div class="span_187" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_188" id="188"><div class="span_188" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_189" id="189"><div class="span_189" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_190" id="190"><div class="span_190" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_191" id="191"><div class="span_191" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_192" id="192"><div class="span_192" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_193" id="193"><div class="span_193" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_194" id="194"><div class="span_194" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>K</h4></td>
                   </tr>
                   <tr>
                        <td><h4>L</h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><a class="availableCls selectSeatCls_195" id="195"><div class="span_195" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_196" id="196"><div class="span_196" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_197" id="197"><div class="span_197" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_198" id="198"><div class="span_198" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_199" id="199"><div class="span_199" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_200" id="200"><div class="span_200" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_201" id="201"><div class="span_201" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_202" id="202"><div class="span_202" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_203" id="203"><div class="span_203" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_204" id="204"><div class="span_204" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_205" id="205"><div class="span_205" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_206" id="206"><div class="span_206" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_207" id="207"><div class="span_207" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_208" id="208"><div class="span_208" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_209" id="209"><div class="span_209" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_210" id="210"><div class="span_210" id="1"><img src="img/available.png"></div></a></td>
                       <td><a class="availableCls selectSeatCls_211" id="211"><div class="span_211" id="1"><img src="img/available.png"></div></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>L</h4></td>
                   </tr>
              </tbody>
         </table>
       </div>
       
       </div>          
      </div>

       <div class="row">
           <div class="col-md-offset-1 col-md-10 text-center m-t-lg">
               <img src="img/screen.png" class="img-responsive" >
           </div>
       </div>
     </div>
    </div>
     <div class="footer fixed-bottom" style="display: none;" id="selectedSeatId">
        <div class="row ">
         <div class="col-md-8 col-sm-8 col-xs-8 m-t-md m-b-md" id="selectedSeatShowId">
            <!--  <a href="#" class="btn btn-outline btn-danger ">E14</a>
             <a href="#" class="btn btn-outline btn-danger ">E14</a>
             <a href="#" class="btn btn-outline btn-danger ">E14</a>
             <a href="#" class="btn btn-outline btn-danger ">E14</a>
             <a href="#" class="btn btn-outline btn-danger ">E14</a> -->
         </div>
         <div class="col-md-2 col-sm-1 col-xs-1 b2">
             <%-- <h3>Total
              <small>(INR)</small>
             <span class="text-center m-l-sm" id="totalAmountId">0</span></h3> --%>
             <span class="left txt text-dark-grey text-uppercase " >Total (&#8377;) </span>
             <b class="text-center m-l-sm" style="font-size:30px" id="totalAmountId">0</b>

         </div>
          <div class="col-md-2 col-sm-1 col-xs-3 m-t-md m-l15">
            <a href="#" class="btn btn-danger pull-right" id="makePaymentBtnId">Make Payment</a>

         </div>
       </div> 
    </div>

     </div>
</div>
</body>
</html>