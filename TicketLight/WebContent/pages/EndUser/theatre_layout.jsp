<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 <link href="img/apple-touch-icon.png" rel="apple-touch-icon">
 <!-- Bootstrap CSS File -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/validationEngine.jquery.css"/>

  <!-- Libraries CSS Files -->
  <link href="lib/animate/animate.min.css" rel="stylesheet">
  <link href="css/font-awesome-animation.min.css" rel="stylesheet">
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
 <link rel="stylesheet" href="Dynamic/js/plugins/SingleAutoComplete/jquery.autocomplete.css" type="text/css" /> 
  
  <!-- Main Stylesheet File -->
  <link href="css/style.css" rel="stylesheet">
   <script type="text/javascript" src="js/jquery-1.9.1.js"></script>


<style type="text/css">

  .list1{
  list-style: none;
  margin-left: 1%;
  
}
 
.list1>li{
  float: left;
  padding: 4px;
}
.list2{
  list-style: none;
  
}
.list2>li{
  float: left;
  padding: 1px;
  margin-right: 5px;
}



.jR3DCarouselGalleryCustomeTemplate a{
  text-decoration: none;      
}
 

.imgsize{
  width: 20%!important;
}
.headsize{
	font-size: 11px;
	
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
}
.m-left{
margin-left: 150px;
}
.table td{
    padding:5px!important;
    vertical-align: top!important;
    border-top: none !important;
}
</style>
<script  type="text/javascript">
$(document).ready(function(){

$(".1").hide();
$(".2").hide();
 $(".3").hide();
  $(".4").hide();
   $(".5").hide();
     $(".6").hide();
          $(".7").hide();

  

$(".btn1").hover(function(){
$(".1").show();
$(".2").hide();
 $(".3").hide();
  $(".4").hide();
   $(".5").hide();
    $(".6").hide();
        $(".7").hide();
  
});
$(".btn2").hover(function(){
$(".1").hide();
$(".2").show();
 $(".3").hide();
  $(".4").hide();
     $(".5").hide();
      $(".6").hide();
         $(".7").hide();
    
});
$(".btn3").hover(function(){
$(".1").hide();
$(".2").hide();
 $(".3").show();
  $(".4").hide();
   $(".5").hide();
    $(".6").hide();
        $(".7").hide();

    
   
});
$(".btn4").hover(function(){
$(".1").hide();
$(".2").hide();
 $(".3").hide();
  $(".4").show();
   $(".5").hide();
    $(".6").hide();
      $(".7").hide();
  
    
   
});
$(".btn5").hover(function(){
$(".1").hide();
$(".2").hide();
 $(".3").hide();
  $(".4").show();
$(".5").hide();
 $(".6").hide();
   $(".7").hide();


});
$(".btn6").hover(function(){
$(".1").hide();
$(".2").hide();
 $(".3").hide();
  $(".4").hide();
   $(".5").show();
    $(".6").hide();
      $(".7").hide();
 
  
});
$(".btn7").hover(function(){
$(".1").hide();
$(".2").hide();
 $(".3").hide();
  $(".4").hide();
   $(".5").hide();
    $(".6").show();
      $(".7").hide();
   
  

});
$(".btn8").hover(function(){
$(".1").hide();
$(".2").hide();
 $(".3").hide();
  $(".4").hide();
   $(".5").hide();
    $(".6").show();
      $(".7").hide();

});
$(".btn9").hover(function(){
$(".1").hide();
$(".2").hide();
 $(".3").hide();
  $(".4").hide();
   $(".5").hide();
    $(".6").hide();
          $(".7").show();
    

  

});
$(".btn10").hover(function(){
$(".1").hide();
$(".2").hide();
 $(".3").hide();
  $(".4").hide();
   $(".5").hide();
    $(".6").hide();
     $(".7").show();
    

});
});
</script>

<script type="text/javascript">
$(document).ready(function(){
	
	var theatre_id = $("#theatreId").val();
	var screen_id = $("#screenId").val();
	var movie_details_id = $("#movieId").val();
	var show_time_id = $("#showTimingId").val();
	var show_details_id = $("#showDetailId").val();
	
	jQuery.ajax
	 ({
		 data :{
				theaterIdVal : theatre_id,
				scrId : screen_id,
				showTimeId:show_time_id,
				movieDetailsId:movie_details_id,
				showDetailId:show_details_id,
				
			},
			url : "getUserScreenTicketBookingLayout",
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
				
				/* str += '<div class="row m-t-lg">';
				str += '<div class="col-md-12">';
				str += '<div class="row m-t-md">';
				str += '</div>';
				str += '<div class="row">';
				str += '<div class="col-md-offset-2 col-md-8">'; */
				
				str += '<div class="row m-t-lg">'; 
		        /* str += '<div class="col-md-3"></div>'; */
		     /*    str += '<div class="col-md-6 offset-md-3">'; */  
		        str += '<div class="col-md-8 offset-md-2">';
				
				str += '<div class="table-responsive"> ';
				str += '<table class="table" >';
				str += '<tbody class="text-center">';
				
				for(var x in finalList)
				{
					var obj1 = finalList[x];
					
					str += '<tr>';
					str += '<td><b >'+obj1['category_name']+'</b></td>';
					
					for(var i = 0;i<obj1['col_count'];i++)
					{
						
						var obj2 = obj1['screenList'];
						for(var y in obj2)
						{
							/* str += '<td></td>';
							str += '<td></td>';
							str += '<td></td>';
							str += '<td></td>'; */
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
					str += '<td><b >'+obj1['category_name']+'</b></td>';
					str += '</tr>';
				}
				str += '</tbody>';
				str += '</table>';
				str += '</div>';
				
				str += '</div>';
				str += '</div>';
				str += '<div class="row">';
				str += ' <div class="col-md-12 text-center m-t-lg">';
				str += ' <img src="images/screen.png" class="img-responsive" width="30%">';
				str += '</div>';
				str += '</div>';
			
		
				$('#floorDivId').append(str);
				$('#floorDivId').show();
				}

				
			}


		 
	 });
	
});
</script>
<script type="text/javascript">

$(document).ready(function()
{

$(document).on('click',"#ticketCountBtnId",function(){
		
		$(".btn"+$("#hiddenUserSeatCountId").val()).addClass("active");
		
		btnId = $("#hiddenUserSeatCountId").val();
		
		switch (btnId) {
		case 1:
			
			$(".1").show();
			break;
        case 2:
			
			$(".2").show();
			break;
        case 3:
			
			$(".3").show();
			break;
        case 4:
			
			$(".4").show();
			break;
        case 5:
			
			$(".4").show();
			break;
        case 6:
			
			$(".5").show();
			break;
        case 7:
			
			$(".6").show();
			break;
        case 8:
			
			$(".6").show();
			break;
        case 9:
			
			$(".7").show();
			break;
        case 10:
			
			$(".7").show();
			break;
		default:
			 $(".2").show();
		}
		
	});   
});
</script>

<script type="text/javascript">

	$(document).ready(function()
	{
		
		var totalSeatCount = 0;
		var showTime = "";
		 var resId="";
		
		showTime = $('#FloorShowTimingForView').val();
		
	/* 	$(document).on('click',".showTimingCls",function(){
			 showTime = $(this).attr('id');
		}); */
		
		var total_clicked_seats = 0;
		var id = 0;
		
	  	$(document).on('click',".availableCls",function(){
	  		
	  		var theatre_id = $("#theatreId").val();
			var screen_id = $("#screenId").val();
			var movie_details_id = $("#movieId").val();
			var show_time_id = $("#showTimingId").val();
			var show_details_id = $("#showDetailId").val();
			var user_selected_seat_count = $("#hiddenUserSeatCountId").val();
			
			resId=theatre_id+"_"+screen_id+"_"+movie_details_id+"_"+show_time_id+"_"+show_details_id;

			$('#selectedSeatId').show();

			id = $(this).attr("id");
			var spanId = $('.span_'+id).attr('id');
			var str = '';
			var str1 = '';

			totalSeatCount = 0;
			if(spanId == 1){
				
				if(user_selected_seat_count != 0){
					
					var st = id.match(/[^\d]+|\d+/g);
					var trst = st.toString().trim();
					var st1 = trst.split(",");
					var spAlpha = st1[0];
					var spId = st1[1];
					var loop_count = 0;
					var seat_count = 0;

					if(total_clicked_seats == user_selected_seat_count){
						
						$('.selSeatBtnCls').each(function()
						{
							  var str6= '';
							  var id = $(this).attr('id');
							  $('.selectSeatCls_'+id+' .span_'+id).remove();
							  str6 += '<div class="span_'+id+'" id="1"><img src="img/available.png"></div>';
							  $('.selectSeatCls_'+id).append(str6);  
							  $('#selectedSeatShowId .S_'+id).remove();
						});
						total_clicked_seats = 0;
					}
					
					var clickedSeatNum = $('.selectSeatCls_'+id+' .span_'+id).attr('id');

					if(clickedSeatNum == 2){
						
						$('.selSeatBtnCls').each(function()
						{
							  var str6= '';
							  var id = $(this).attr('id');
							  $('.selectSeatCls_'+id+' .span_'+id).remove();
							  str6 += '<div class="span_'+id+'" id="1"><img src="img/available.png"></div>';
							  $('.selectSeatCls_'+id).append(str6);  
							  $('#selectedSeatShowId .S_'+id).remove();
						});
						total_clicked_seats = 0;
					}
					
					if(total_clicked_seats == 0){
						
						loop_count = user_selected_seat_count+1;
					}
					else{
						loop_count = parseInt(user_selected_seat_count) - parseInt(total_clicked_seats)+1;
						seat_count = total_clicked_seats;
					}
					var loopExistCount = 0;
					for(var i = 1;i<loop_count;i++)
					{
						if(loopExistCount == 0){
							
							spId = parseInt(spId)+1;
							var newId = spAlpha+spId;
							var seatViewNum = $('.selectSeatCls_'+newId+' .span_'+newId).attr('id');
							
							if(i == 1)
							{
								$('.selectSeatCls_'+id+' .span_'+id).remove();
								
								str += '<div class="span_'+id+'" id="2"><img src="img/selected.png"></div>';
							   
								$('.selectSeatCls_'+id).append(str);  
								
								str1 +='<span style="margin-right: 13px;" class="btn btn-outline btn-danger selSeatBtnCls S_'+id+'" id="'+id+'">'+ id +'</span>';
								
								$('#selectedSeatShowId').append(str1);
						
								seat_count = parseInt(seat_count)+1;
							}
							
							if(seat_count == user_selected_seat_count){
								
							}
							else{
								
								if(typeof seatViewNum == "undefined" || seatViewNum == 0){
									
									loopExistCount = parseInt(loopExistCount)+1;

								}
								else{
									
									var str4= '';
									var str5= '';
									
									$('.selectSeatCls_'+newId+' .span_'+newId).remove();
									
									str4 += '<div class="span_'+newId+'" id="2"><img src="img/selected.png"></div>';
								   
									$('.selectSeatCls_'+newId).append(str4);  
									
									if(seatViewNum == 1){
										str5 +='<span style="margin-right: 13px;" class="btn btn-outline btn-danger selSeatBtnCls S_'+newId+'" id="'+newId+'">'+ newId +'</span>';
										
										$('#selectedSeatShowId').append(str5);
									}
									seat_count = parseInt(seat_count)+1;
								}
							}
						}
					} 
					
					total_clicked_seats = seat_count;
				}
				else{
					
					$('.selectSeatCls_'+id+' .span_'+id).remove();
					
					str += '<div class="span_'+id+'" id="2"><img src="img/selected.png"></div>';
				   
					$('.selectSeatCls_'+id).append(str);  
					
					str1 +='<span style="margin-right: 13px;" class="btn btn-outline btn-danger selSeatBtnCls S_'+id+'" id="'+id+'">'+ id +'</span>';
					
					$('#selectedSeatShowId').append(str1);
					
				}
			}
			else if(spanId == 2 && total_clicked_seats == 1){
			
				var id = 0;
				$('.selSeatBtnCls').each(function()
				{
					id = $(this).attr('id');
				});
				
				$('.selectSeatCls_'+id+' .span_'+id).remove();
				
				str += '<div class="span_'+id+'" id="1"><img src="img/available.png"></div>';
				
				$('.selectSeatCls_'+id).append(str);  
				$('#selectedSeatShowId .S_'+id).remove();
				
				$("#totalAmountId").append();
				
				$('#selectedSeatId').hide();
			}
			else if(spanId == 2)
			{
			
				var st = id.match(/[^\d]+|\d+/g);
				var trst = st.toString().trim();
				var st1 = trst.split(",");
				var spAlpha = st1[0];
				var spId = st1[1];
				var loop_count = 0;
				var seat_count = 0;

				if(total_clicked_seats == user_selected_seat_count){
					
					$('.selSeatBtnCls').each(function()
					{
						  var str6= '';
						  var id = $(this).attr('id');
						  $('.selectSeatCls_'+id+' .span_'+id).remove();
						  str6 += '<div class="span_'+id+'" id="1"><img src="img/available.png"></div>';
						  $('.selectSeatCls_'+id).append(str6);  
						  $('#selectedSeatShowId .S_'+id).remove();
					});
					total_clicked_seats = 0;
				}
				
				var clickedSeatNum = $('.selectSeatCls_'+id+' .span_'+id).attr('id');

				if(clickedSeatNum == 2){
					
					$('.selSeatBtnCls').each(function()
					{
						  var str6= '';
						  var id = $(this).attr('id');
						  $('.selectSeatCls_'+id+' .span_'+id).remove();
						  str6 += '<div class="span_'+id+'" id="1"><img src="img/available.png"></div>';
						  $('.selectSeatCls_'+id).append(str6);  
						  $('#selectedSeatShowId .S_'+id).remove();
					});
					total_clicked_seats = 0;
				}
				
				if(total_clicked_seats == 0){
					
					loop_count = user_selected_seat_count;
				}
				else{
					loop_count = parseInt(user_selected_seat_count) - parseInt(total_clicked_seats)+1;
					seat_count = total_clicked_seats;
				}
				
				var loopExistCount = 0;
				for(var i = 1;i<loop_count;i++)
				{
					if(loopExistCount == 0){
						
						spId = parseInt(spId)+1;
						var newId = spAlpha+spId;
						var seatViewNum = $('.selectSeatCls_'+newId+' .span_'+newId).attr('id');
						
						if(i == 1)
						{
						
							$('.selectSeatCls_'+id+' .span_'+id).remove();
							
							str += '<div class="span_'+id+'" id="2"><img src="img/selected.png"></div>';
						   
							$('.selectSeatCls_'+id).append(str);  
							
							str1 +='<span style="margin-right: 13px;" class="btn btn-outline btn-danger selSeatBtnCls S_'+id+'" id="'+id+'">'+ id +'</span>';
								
							$('#selectedSeatShowId').append(str1);  
							seat_count = parseInt(seat_count)+1;
						} 
						
						if(seat_count == user_selected_seat_count){
							
							loopExistCount = parseInt(loopExistCount)+1;
						}
						else{
							
							if(typeof seatViewNum == "undefined" || seatViewNum == 0){
								
							}
							else{
								
								var str4= '';
								var str5= '';
								
								$('.selectSeatCls_'+newId+' .span_'+newId).remove();
								
								str4 += '<div class="span_'+newId+'" id="2"><img src="img/selected.png"></div>';
							   
								$('.selectSeatCls_'+newId).append(str4);  
								
								str5 +='<span style="margin-right: 13px;" class="btn btn-outline btn-danger selSeatBtnCls S_'+newId+'" id="'+newId+'">'+ newId +'</span>';
									
								$('#selectedSeatShowId').append(str5); 
								
								seat_count = parseInt(seat_count)+1;
							}
						}
					}
				} 
				
				total_clicked_seats = seat_count;
			}
			
			 $('.selSeatBtnCls').each(function()
			 {
				totalSeatCount ++; 
			 });
						 
			 var rupee = totalSeatCount*120;
					    
			 $("#totalAmountId").empty();
			 $("#totalAmountId").append(rupee);
		});
	
		
  	  $(document).on('click',"#makePaymentBtnId",function(){
  		   if(toatalAmount == 0 || totalSeatCount == 0)
  		   {
  			   
  		   }
  		   else if(showTime == "")
  		   {
  			   
  		   }
  		   else{
  			   
  	  		/* var date = $("#dateId").val(); */
		 
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
		   
   		 /*   $("#hiddenDateId").val(date); */
   		   $("#hiddenShowTimeId").val(showTime);
   		   $("#hiddenSeatCountId").val(totalSeatCount);
   		   $("#hiddenSeatNameStrId").val(seatName);
   		   $("#hiddenTotalAmountId").val(toatalAmount);
   		   $("#hiddenResId").val(resId);
   		   $("#makePaymentSubFormId").submit();
   		   
  		   }
  		   
  	  });
		
  	 $(document).on('click',"#backToMovieBtnId",function(){
  		 
  		  var status_id = $("#hidMasterMvId").val();
  		  
  		  if(status_id == 1){
  			  var masterMvId = $("#hidMasterMvId").val();
  	  		  $("#formHiddenMasterMvId").val(masterMvId);
  	  		  $("#movieFormSubmit").submit(); 
  		  }
  		  else{
  			  
  			  var theaterId = $("#hiddenTheaterId").val();
 	  		  $("#hiddenTheaterDetailTheaterId").val(theaterId);
 	  		  $("#theaterFormSubmitId").submit(); 
  		  }
  		  
  	 });
  	
	$(document).on('click',".dateCls",function(){
  		
  		$('ul li button .dateCls').removeClass("active");
  	    $(this).addClass("active");
  	});

  	
  	var seat_count = 0;
	
  	$(document).on('click',".seatCountCls",function(){
  		seat_count = $(this).attr('id');
  		
  		$('ul li button').removeClass("active");
  	    $(this).addClass("active");
  	    $("#countId").empty();
  	    $("#countId").append(seat_count);
  	});

  	$(document).on('click',"#seatSubmitBtnId",function(){

  		   $("#hiddenUserSeatCountId").val(seat_count);
  		   total_clicked_seats = 0;
  		   $('.selSeatBtnCls').each(function()
		   {
						  var str6= '';
						  var id = $(this).attr('id');
						  $('.selectSeatCls_'+id+' .span_'+id).remove();
						  str6 += '<div class="span_'+id+'" id="1"><img src="img/available.png"></div>';
						  $('.selectSeatCls_'+id).append(str6);  
						  $('#selectedSeatShowId .S_'+id).remove();
		   });
  		   $('#selectedSeatId').hide();
	       $("#popupClsId").click(); 
  	});
  	  
	});

</script>

</head>
<body class="fx-greycolor">

<s:hidden name="userBean.show_timing" id="FloorShowTimingForView" />
<s:hidden name="userBean.date"></s:hidden>
<s:hidden name="theaterOwnerBean.master_movie_id" id="hidMasterMvId"></s:hidden>
<s:hidden name="theaterOwnerBean.status_id" id="hiddenStatusId"></s:hidden>
<s:hidden name="userBean.theater_id" id="hiddenTheaterId"></s:hidden>
<s:hidden name="userBean.selected_seat_count" id="hiddenUserSeatCountId"></s:hidden> 


<s:form action="user-ticket-payment-submit" id="makePaymentSubFormId" method="post">
<s:hidden name="userBean.movie_name"></s:hidden>
<s:hidden name="userBean.theatre_name"></s:hidden>
<s:hidden name="userBean.date"></s:hidden>
<s:hidden name="userBean.show_timing" id="hiddenShowTimeId"></s:hidden>
<s:hidden name="userBean.seat_count" id="hiddenSeatCountId"></s:hidden>
<s:hidden name="userBean.seat_name_str" id="hiddenSeatNameStrId"></s:hidden>
<s:hidden name="userBean.total_amount" id="hiddenTotalAmountId"></s:hidden>
<s:hidden name="userBean.screen_name"></s:hidden>
<s:hidden name="userBean.show_time_str" id="hiddenResId"></s:hidden>
</s:form>

<s:form action="movie-cinemas" id="movieFormSubmit">
<s:hidden name="theaterOwnerBean.master_movie_id" id="formHiddenMasterMvId"></s:hidden>
<s:hidden name="theaterOwnerBean.city_id"></s:hidden>
</s:form>

<s:form action="theater-movies" id="theaterFormSubmitId">
<s:hidden name="theaterOwnerBean.theater_id" id="hiddenTheaterDetailTheaterId"></s:hidden>
<s:hidden name="theaterOwnerBean.city_id"></s:hidden>
</s:form>  

<nav class="navbar navbar-dark bg-dark">
  <div class="container-fluid">
    <div class="navbar-header">
      <ul class="nav-menu ">
        <li class="m-t-sm ">
        <button type="button" class="btn btn-white " id="backToMovieBtnId">
           <span style="padding: 5px 15px"> Back</span>
        </button>
        </li>
        <li class="">
         <a class="navbar-brand m-t-xs" href="#">
         <span class="h6 font-weight-bold"><s:property value="userBean.movie_name"/></span><br>
       <span class="headsize"><s:property value="userBean.theatre_name"/></span></a></li>
      </ul>

      
    </div>
  <ul class="nav navbar-nav navbar-right">
      <li> 
      
     <button type="button"id="ticketCountBtnId" class="btn btn-outline btn btn-outline-warning" data-toggle="modal" data-target="#myModal18">
          <span class="headsize"><a id="countId"><s:property value="userBean.selected_seat_count"/></a> Ticket  &#9660; </span>                            
       </button> 
     </li>
      </ul>                  
            
  
        <!--popup start -->
    <div class="modal inmodal" id="myModal18" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content animated flipInY">
                                        <div class="modal-header">
                                          
                                          	<div class="m-left">
                                           <span class="font-weight-bold">How Many Tickets?</span>
                                         </div>
                                     
                                            <button type="button" class="close" data-dismiss="modal" id="popupClsId"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                            
                                           
                                        </div>
         <div class="modal-body">
                                          
            <div class="text-center m-t-lg">
                <img src="images/rsz_cycle.png" class="1 imgsize">
                   <img src="images/rsz_bike.png" class="2 imgsize"> 
                   <img src="images/rsz_auto.png" class="3 imgsize">
                   <img src="images/rsz_minicar.png" class="4 imgsize">
                   <img src="images/rsz_car3.png" class="5 imgsize">
                   <img src="images/rsz_van2.png" class="6 imgsize">
                   <img src="images/rsz_bus3.png" class="7 imgsize">
                                                       
            </div>
          <div class="row m-t-md">
                                            <ul class="list2">
                                              <li><button type="button" id="1" class="btn btn-outline-primary btn1 seatCountCls">1</button></li>
                                              <li><button type="button" id="2" class="btn btn-outline-primary btn2 seatCountCls">2</button></li>
                                              <li><button type="button" id="3" class="btn btn-outline-primary btn3 seatCountCls">3</button></li>
                                              <li><button type="button" id="4" class="btn btn-outline-primary btn4 seatCountCls">4</button></li>
                                              <li><button type="button" id="5" class="btn btn-outline-primary btn5 seatCountCls">5</button></li>
                                              <li><button type="button" id="6" class="btn btn-outline-primary btn6 seatCountCls">6</button></li>
                                              <li><button type="button" id="7" class="btn btn-outline-primary btn7 seatCountCls">7</button></li>
                                              <li><button type="button" id="8" class="btn btn-outline-primary btn8 seatCountCls">8</button></li>
                                              <li><button type="button" id="9" class="btn btn-outline-primary btn9 seatCountCls">9</button></li>
                                              <li><button type="button" id="10" class="btn btn-outline-primary btn10 seatCountCls">10</button></li>
                                            </ul>
                                        </div>
                                    </div>
                                        <%-- <div class="row m-t-sm">
                                          <div class="col-md-3">
                                            
                                          </div>
                                         <div class="col-md-3">
                                        <div>
                                                  <small>ELITE_2D</small><br>
                                              <span><i class="fa fa-inr" aria-hidden="true"></i></span><span class=" font-weight-bold">165.78</span><br>
                                              <span><small>Available</small></span>
                                            </div>
                                          </div>


                                         
                                        <div class="col-md-3">
                                             <small>BUDGET</small><br>
                                             <span><i class="fa fa-inr" aria-hidden="true"></i></span><span class="p font-weight-bold">63.62</span><br>
                                               <small>Available</small>
                                           </div>
                                           <div class="col-md-3">
                                             
                                           </div>
                                         </div> --%>
                                         <div class="row justify-content-center m-t-md m-bot">
                                            <div class="m-b-md">
                                          <button type="button" id="seatSubmitBtnId" class="btn btn-primary btn-md m-b-md">Select Seats</button>
                                        </div>
                                        </div>
                                      
                                       


                                    </div>
                                        </div>
                                    </div>
   <!-- Popup  -->
       
               
     
</nav>

<s:hidden name="userBean.theater_id"  id="theatreId"></s:hidden>
<s:hidden name="userBean.screen_id"  id="screenId"></s:hidden>
<s:hidden name="userBean.movie_details_id" id="movieId"></s:hidden>
<s:hidden name="userBean.show_timing_id" id="showTimingId"></s:hidden>
<s:hidden name="userBean.show_detail_id" id="showDetailId"></s:hidden>

<div class="row " style="box-shadow: 0 0 30px 0 rgba(0,0,0,.1);">
   <ul class="list1 m-t-md">
        <li>
        <button type="button" class="btn btn-outline-danger  btn-sm dateCls">
<s:property value="userBean.show_timing"/>
</button></li>
</ul>

</div>
      
           <div class="container-fluid" id="floorDivId">
            <div class="row m-t-lg">
            <div class="col-md-3"></div>
            
            <div class="col-md-9">
           
            <div class="table-responsive">
         <table> 
            <tbody class="">
                   <tr>
                      
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><a href=""><img src="images/selected.png"></a></td>
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                        <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                        <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                         <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                  <td><a href=""><img src="images/available.png"></a></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                     <td><a href=""><img src="images/available.png"></a></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                        <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                     <td><a href=""><img src="images/available.png"></a></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                     <td><a href=""><img src="images/available.png"></a></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                        <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                     <td><a href=""><img src="images/available.png"></a></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                          <td><img src="images/available.png"></td>
                             <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                         <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                         <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                                             <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                         <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                         <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                         <td><h4></h4></td>
                      <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                         <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>  
                   <tr>
                         <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
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
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                       
                   </tr>
                   <tr>
                       
                   </tr>
                   <tr>
                       
                   </tr>
                   <tr>
                        <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                        <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>
                   <tr>
                        <td><h4></h4></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                      <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                   <td><a href=""><img src="images/available.png"></a></td>
                    <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td><a href=""><img src="images/available.png"></a></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4></h4></td>
                   </tr>
              </tbody>
         </table>
       </div>
     </div>
     <!-- </div> -->
   </div>
 
          
    

       <div class="row">
           <div class="col-md-12 text-center m-t-lg">
               <img src="images/screen.png" class="img-responsive" width="30%">
           </div>
       </div>
       </div>
    
    <div class="container"  style="display: none;" id="selectedSeatId">
     <div class="footer">
        <div class="row m-l-lg list1">
         <div class="col-md-8 col-sm-8 col-xs-8 m-t-md m-b-md" id="selectedSeatShowId">
             <!-- <a href="#" class="btn btn-outline btn-danger ">E14</a>
             <a href="#" class="btn btn-outline btn-danger ">E15</a>
             <a href="#" class="btn btn-outline btn-danger ">E16</a>
             <a href="#" class="btn btn-outline btn-danger ">E17</a>
             <a href="#" class="btn btn-outline btn-danger ">E18</a>
             <a href="#" class="btn btn-outline btn-danger ">E19</a> -->
         </div>
         <div class="col-md-2 col-sm-1 col-xs-1  m-t-md m-l15">
          <div class="row">
            <div class="col-md-12">
              <span class="h5">TOTAL:</span>
              <span class="h4 font-weight-bold">&nbsp; <i class="fa fa-inr m-r-xs"  ></i> <span id="totalAmountId"></span></span>
            </div>
           <%--  <div class="col-md-6">
              <span class="h4 font-weight-bold">&nbsp; <i class="fa fa-inr m-r-sm"  ></i> <span id="totalAmountId"></span></span>

            </div> --%>

          </div>

         </div>
          <div class="col-md-2 col-sm-1 col-xs-3 m-t-md m-l15">
            <span class="btn btn-outline btn-danger pull-right" id="makePaymentBtnId">Make Payment</span>

         </div>
       </div> 
    </div>
  </div>

  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script src="src/jR3DCarousel.js"></script>
  <script src="lib/wow/wow.min.js"></script>
  <script src="lib/superfish/superfish.min.js"></script>
  <script src="js/main.js"></script>


<script type="text/javascript">
  $('ul.nav li.dropdown').hover(function() {
  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
}, function() {
  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
});
</script>

</body>
</html>