<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
              <%@ taglib uri="/struts-tags" prefix="s" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%-- <style type="text/css">
        /*hover effect*/
      .border{
       height: 100%;
       min-width: 100%;
       border: 1px solid black;
        padding:15px;
        margin-top: 10px;"
      }
  
        .m-top{
          margin-top: 3px;
        }
        .fx-padding{
          padding: 10px;
        }

.fx-border1{
border-bottom:1px solid black;
border-top: 1px solid black;
}
    </style> --%>
</head>
<script type="text/javascript">

	$(document).ready(function()
	{
		$('#successId').hide();
		var seatNameStr = $("#hiddenSeatNameStrId").val();
		var showDetailStr = $("#hiddenShowDetailsId").val();
		
		
		
		/* var res = showDetailStr.split("_");
		var screen_id=res[0];
		var theatre_id=res[1];
		var show_time_id=res[2];
		var movie_details_id=res[3];
		var show_details_id=res[4]; */
		var dateId;
		
		
		var str = '';
	 	$('#bookedSeatId').empty();

		var array = seatNameStr.split(',');
		for (i=0;i<array.length;i++){
			
		    /*  str +='<div class="col-md-2 col-xs-2">';
		     str +='<div class="">';
		     str +='<h3>'+array[i]+'</h3>';
		     str +='</div>';
		     str +='</div>'; */
		     str += '<button class="btn btn-danger m-r-xs">'+array[i]+'</button>';
		     
		}
		  $('#bookedSeatId').append(str);
		
		 var totalAmount =  $('#hiddenTotAmountId').val();
		 
		 var sgst = parseInt(totalAmount)*14/100;
		 var cgst = parseInt(totalAmount)*14/100;
		 var et = parseInt(totalAmount)*8/100;
		 var subTotal = parseInt(totalAmount)+parseInt(sgst)+parseInt(cgst)+parseInt(et);
         
		  $('#SGSTTaxId').empty();
		  $('#CGSTTaxId').empty();
		  $('#ETTaxId').empty();
		  $('#subTotalId').empty();
		  $('#totalAmountId').empty();
		  
		  
		  $('#SGSTTaxId').append(sgst);
		  $('#CGSTTaxId').append(cgst);
		  $('#ETTaxId').append(et);
		  $('#subTotalId').append(subTotal);
		  $('#totalAmountId').append(totalAmount);

		 /*  jQuery.ajax
			 ({
				 	data :{
						theaterIdVal : theatre_id,
						scrId : screen_id,
						showTimeId:show_time_id,
						movieDetailsId:movie_details_id,
						showDetailId:show_details_id,
						
					},
					url : "getTicketDetails",
					type : "POST",
					success : function(results) 
					{
					
						var page = JSON.parse(results);
						alert(page['theater_name'])
						
				     
						
				      $('#theatreNameId').empty();
					 $('#movieNameId').empty();
					 $('#screenNameId').empty();
					 $('#dateId').empty();
					 $('#showTimeId').empty();
					 
					 dateId=page['show_date_id'];
					 
				      $('#theatreNameId').append(page['theater_name']);
				      $('#movieNameId').append(page['movie_name']);
				      $('#screenNameId').append(page['screen_name']);
				      $('#dateId').append(page['show_date']);
				      $('#showTimeId').append(page['show_time']);
				      
					}
			 }); */
		  
		/*   payment action */
		  
		  $(document).on('click',".paymentCls",function()
		{
				var seatNameStr = $("#hiddenSeatNameStrId").val();
				 var showDetailStr = $("#hiddenShowDetailsId").val();
				
				
				var res = showDetailStr.split("_");
				
				
				
				var theatre_id=res[0];
				var screen_id=res[1];
				var movie_details_id=res[2];
				var show_time_id=res[3];
				var show_details_id=res[4]; 
		
				
				
				/*  */
				
				 jQuery.ajax
				 ({
					 	data :{
							theaterIdVal : theatre_id,
							scrId : screen_id,
							showTimeId:show_time_id,
							movieDetailsId:movie_details_id,
							showDetailId:show_details_id,
							seatStr:seatNameStr,
							dateId:dateId,
							totalPrice:subTotal,
						},
						url : "getUserTicketSoldDetail",
						type : "POST",
						success : function(results) 
						{
							 $('#successId').show();
						}
				 });
			
		  
	});
	
	
				
	});
</script>

<body>
<s:hidden name="userBean.seat_name_str" id="hiddenSeatNameStrId"></s:hidden>
<s:hidden name="userBean.total_amount" id="hiddenTotAmountId"></s:hidden>
<s:hidden name="userBean.show_timing"  id="hiddenShowsId"></s:hidden>
<s:hidden name="userBean.show_time_str"  id="hiddenShowDetailsId"></s:hidden>

<%-- <s:hidden name="userBean.theater_id"  id="theatreId"></s:hidden>
<s:hidden name="userBean.screen_id"  id="screenId"></s:hidden>
<s:hidden name="userBean.movie_details_id" id="movieId"></s:hidden>
<s:hidden name="userBean.show_timing_id" id="showTimingId"></s:hidden>
<s:hidden name="userBean.show_detail_id" id="showDetailId"></s:hidden> --%>

    <div class="container ">
    
        <div class="row" style="margin-top: 120px;margin-bottom: 40px">
            <div class="col-md-6 offset-md-3" style="background-color: #ddd;padding: 10px">
                <div style="background-color: #ddd;padding: 5px;text-align: center;">
                    <img src="images/ticketlite.png"   width="25%">
                    
<h3 class="font-weight-bold" style="color: green" id="successId"> Your Booking is Confirmed</h3>

                    <h3 class="font-weight-bold" style="color: black"> Booking Summary</h3>
                    <h6 >Booking ID <span class="font-weight-bold">JACM0001315350</span></h6>

                    
                </div>
                 <div style="background-color: #fff; padding:10px; border-radius: 5px ">
                       <div class="row">
                           <div class="col-md-9">
                               <h5 style="margin: 0px 0px 5px 0px" class="font-weight-bold"><s:property value="userBean.movie_name"/></h5>
                        <h6 style="margin: 0px 0px 5px 0px"><span class="m-r-sm"><s:property value="userBean.date"/>-08-2018</span><span class="m-r-xs">|</span> <span><s:property value="userBean.show_timing"/></span></h6>

                        <h6 class="font-weight-bold"><s:property value="userBean.theatre_name"/>(<s:property value="userBean.screen_name"/>)</h6>
                           </div>
                           <div class="col-md-3" style="text-align: center;margin-top: 10px">
                               <img src="images/qr.png" width="60%">
                           </div>
                       </div>
                       <hr>
                       <div class="row">
                           <div class="col-md-3">
                               <div style="text-align: center;padding: 10px;border-radius: 3px;border:1px solid #ddd">
                                   <span><b style="font-size: 20px"><s:property value="userBean.seat_count"/></b><br>
                            <small>Tickets</small></span>
                               </div>
                           </div>
                           <div class="col-md-6" >
                           <div id="bookedSeatId">
                               <button class="btn btn-danger">E1</button>
                               <button class="btn btn-danger">E2</button>
                                <button class="btn btn-danger">E3</button>
                              </div>
                           </div>
                           <div class="col-md-3">
                               <img src="images/bookingstamps.png" width="60%">
                           </div>
                       </div>
                        

                 </div>

<hr>
             <table style="width: 100%; padding-bottom: 20px; font-family: Arial,sans-serif; margin: 0 auto" cellspacing="0" cellpadding="0"><tbody><tr><td style="width: 50%; font-size: 14px; font-family: Arial,sans-serif; text-align: left; vertical-align: top; background-color: #ffffff; color: #3f474e;padding: 10px" align="left">
                           <div style="color: #343a40; font-size: 13px">Ticket Base Price (<s:property value="userBean.seat_count"/> Tkt)</div>
                           <div style="color: #343a40; font-size: 13px">CGST (14%)</div>
                           <div style="color: #343a40; font-size: 13px">SGST (14%)</div>
                           <div style="color: #343a40; font-size: 13px">ET (8%)</div>
                           <div style="margin-bottom: 10px;margin-top: 10px;border-bottom: 1px solid #ddd"></div>
                            <p style="margin: 0; padding: 0 0 12px 0">
                              <strong>TICKET AMOUNT</strong>
                            </p>
                            
                          </td>
                          <td style="width: 50%; font-size: 15px; font-family: Arial,sans-serif; text-align: right; vertical-align: top; background-color: #ffffff; color: #3c3c3c;padding: 10px" align="right">
                              <div style="color: #343a40; font-size: 13px">Rs. <span id="totalAmountId">119.00</span></div>
                              <div style="color: #343a40; font-size: 13px">Rs. <span id="CGSTTaxId">17.99</span></div>
                              <div style="color: #343a40; font-size: 13px">Rs. <span id="SGSTTaxId">17.99</span></div>
                              <div style="color: #343a40; font-size: 13px">Rs. <span id="ETTaxId">9.52</span></div>
                              <div style="margin-bottom: 10px;margin-top: 10px;border-bottom: 1px solid #ddd"></div>
                            <p style="margin: 0; padding: 0 0 12px 0;font-weight: bold">Rs. <span id="subTotalId">552.18</span></p>
                          
                          </td>
                        </tr></tbody></table>   

                        <hr>  
                  
                  <div class="text-center">
                    <button class="btn btn-primary btn-fulid paymentCls"> Proceed Pay</button>
                  </div>

      
            </div>


        </div>


        
    </div>

</body>
</html>