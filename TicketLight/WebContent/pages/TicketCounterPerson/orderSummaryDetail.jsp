<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
              <%@ taglib uri="/struts-tags" prefix="s" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
        /*hover effect*/
      .border{
       height: 100%;
       min-width: 100%;
       border: 1px solid #ccc;
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
    </style>
</head>
<script type="text/javascript">

	$(document).ready(function()
	{
		$('#successId').hide();
		var seatNameStr = $("#hiddenSeatNameStrId").val();
		var showDetailStr = $("#hiddenShowDetailsId").val();
		
		var res = showDetailStr.split("_");
		var screen_id=res[0];
		var theatre_id=res[1];
		var show_time_id=res[2];
		var movie_details_id=res[3];
		var show_details_id=res[4];
		var dateId;
		
		
		var str = '';
	 	$('#bookedSeatId').empty();

		var array = seatNameStr.split(',');
		for (i=0;i<array.length;i++){
			
		    // str +='<div class="col-md-2 col-xs-2">';
		     //str +='<div class="">';
		     str +='<button class="btn btn-xs m-r-xs m-t-sm  m-l-xs  btn-danger">'+array[i]+'</button>';
		    // str +='</div>';
		    // str +='</div>';
		     
		}
		  $('#bookedSeatId').append(str);
		
		 var totalAmount =  $('#hiddenTotAmountId').val();
		 var sgst = parseInt(totalAmount)*14/100;
		 var cgst = parseInt(totalAmount)*14/100;
		 var et = parseInt(totalAmount)*8/100;
		// var subTotal = parseInt(totalAmount)+parseInt(sgst)+parseInt(cgst)+parseInt(et);
        var subTotal = parseFloat(totalAmount)+parseFloat(sgst)+parseFloat(cgst)+parseFloat(et);
        subTotal=subTotal.toFixed(2);
         
		  $('#SGSTTaxId').empty();
		  $('#CGSTTaxId').empty();
		  $('#ETTaxId').empty();
		  $('#subTotalId').empty();
		  
		  $('#SGSTTaxId').append(sgst);
		  $('#CGSTTaxId').append(cgst);
		  $('#ETTaxId').append(et);
		  $('#subTotalId').append(subTotal);
		  $('#totalRupId').append('Rs.'+subTotal);

		  jQuery.ajax
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
						
				     /*  for(var i in results){
						 var obj=results[i]; 
						 theatre_name=obj.theater_name; 
					}  */
						
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
			 });
		  
		/*   payment action */
		  
		  $(document).on('click',".paymentCls",function(){
				var seatNameStr = $("#hiddenSeatNameStrId").val();
				var showDetailStr = $("#hiddenShowDetailsId").val();
				
				
				
				var res = showDetailStr.split("_");
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
							seatStr:seatNameStr,
							dateId:dateId,
							totalPrice:subTotal,
						},
						url : "getTicketSoldDetail",
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
<s:hidden name="ticketCounterBean.seat_name_str" id="hiddenSeatNameStrId"></s:hidden>
<s:hidden name="ticketCounterBean.total_amount" id="hiddenTotAmountId"></s:hidden>
<s:hidden name="ticketCounterBean.show_timing"  id="hiddenShowDetailsId"></s:hidden>


        <div class=" row wrapper wrapper-content">
        <div id="successId" style="color: rgb(49, 167, 63);display: block;font-size: 17px;text-align:center;margin-top: -10px;margin-bottom: 12px;font-weight: bold;">Successfully Booked!!!</div>
             <div class="col-md-4 col-md-offset-4  ibox-content">
         <div class="row text-center">
         	<img alt="ticketlite" src="images/ticketlite.png" class="text-center" width="25%">
         	
         </div>
              <h3 class="text-center" id="theatreNameId"><b>LUXE</b></h3>
             <!--  <h4 class="text-center">No.142, PHOENIX MARKET CITY</h4>
              <h5 class="text-center">CHENNAI-42</h5>  -->
         <h5 class="font-bold text-center	">Tax Invoice</h5>
      
      <div class="row">
      	<div class="col-md-7">
      	   <div class="row">
               <b class="m-l-sm text-success" style="font-size:16px"" id="movieNameId">TAMIZH PADAM 2(U)</b>
              
               
           </div>
           <div class="row ">
            <b class="m-l-sm" id="dateId"><s:property value="ticketCounterBean.movie_date"/></b> <span class="m-l-sm"> | </span> 
            
             <b class="m-l-xs" id="showTimeId"><s:property value="ticketCounterBean.show_timing"/></b>
           </div>
           <div class="row">
            <b class="m-l-sm" id="screenNameId">SCREEN8</b> <span class="m-l-sm"> | </span>   <b class="m-l-xs">ELITE</b>
           </div>
      	</div>
      	<div class="col-md-5">
      	<img src="Dynamic/img/19.png" class="pull-right" width="59px"; height="60px">
      	</div>
      </div>
      
      
           
           
           
           
           
           
       <%--    <div class="row text-center">
              <div class="col-md-5 col-xs-3 col-sm-3">
              <p class="">SCREEN</p>
          </div>
          <div class="col-md-2 col-xs-2 col-sm-2">
              <p>:</p>
          </div>

             <div class="col-md-5 col-xs-7 col-sm-7">
                <p class="" id="screenNameId">SCREEN8</p>
            </div>
        </div>
 <div class="row text-center">
              <div class="col-md-5 col-xs-3 col-sm-3">
              <p class="" > DATE</p>
          </div>
          <div class="col-md-2 col-xs-2 col-sm-2">
              <p>:</p>
          </div>

             <div class="col-md-5 col-xs-7 col-sm-7">
                <p class="" id="dateId"><s:property value="ticketCounterBean.movie_date"/></p>
            </div>
        </div>
         <div class="row text-center">
              <div class="col-md-5 col-xs-3 col-sm-3">
              <p class="">TIME</p>
          </div>
          <div class="col-md-2 col-xs-2 col-sm-2">
              <p>:</p>
          </div>

             <div class="col-md-5 col-xs-7 col-sm-7">
                             <p class="" id="showTimeId"><s:property value="ticketCounterBean.show_timing"/></p>
             
            </div>
        </div>

        
        
        
         <div class="row text-center">
              <div class="col-md-5 col-xs-3 col-sm-3">
              <p class="">CLASS</p>
          </div>
          <div class="col-md-2 col-xs-2 col-sm-2">
              <p>:</p>
          </div>

             <div class="col-md-5 col-xs-7 col-sm-7">
                <p class="">ELITE</p>
            </div>
        </div>
        --%>
    
    <div class="border ">
     <div class="row text-danger "  id="bookedSeatId">
     	
     </div>
    </div>
     
      <!-- <div class="col-md-2 col-xs-2">
        <div class="">
        <h3>A1</h3>
      </div>
      </div>
      <div class="col-md-2 col-xs-2"> 
        <div class="">
        <h3>A2</h3>
      </div>
    </div>
      <div class="col-md-2 col-xs-2">
        <div class="">
        <h3>A3</h3>
         </div>
      </div>
      <div class="col-md-2 col-xs-2">
         <div class="">
       <h3>A4</h3>
     </div>
     </div>
      <div class="col-md-2 col-xs-2"> 
  <div class="">       
   <h3>A5</h3>
      </div>
    </div> -->
  
     <!-- <div class="row m-top">
    
      <div class="col-md-2 col-xs-2">
          <div class="">
        <h3>B1</h3>
          </div>
      </div>
      <div class="col-md-2 col-xs-2"> 
          <div class="">
        <h3>B2</h3>
      </div>
      </div>
      <div class="col-md-2 col-xs-2">
        <div class=""> 
        <h3>B3</h3>
      </div>
      </div>
      <div class="col-md-2 col-xs-2"> 
          <div class="">
        <h3>B4</h3>
      </div>
    </div>
      <div class="col-md-2 col-xs-2">
        <div class=""> 
        <h3>B5</h3>
      </div>
      </div>
    </div> -->


  <div class="row">

<div class="col-md-12 m-t-sm text-center  ">
<b class=" ">
 Booking Id : 33AABCH7993R1ZQ
</b>
  </div>
  </div>
       
      <div class="row">
        <div class="col-md-12  col-sm-12  col-xs-12  fx-padding" >
                 <div class="row">
              <div class="col-md-6 col-xs-6 col-sm-6">
              <b class="">Tickets <span class="m-l-xs">(<s:property value="ticketCounterBean.seat_count"/></span>*120) </b>
          </div>
          <!-- <div class="col-md-2 col-xs-2 col-sm-2">
              <p class="">:</p>
          </div> -->

             <div class="col-md-6 col-xs-6 col-sm-6">
                <span class="pull-right"><s:property value="ticketCounterBean.total_amount"/>.00</span>
            </div>
        </div>
 <div class="row">
              <div class="col-md-6 col-xs-6 col-sm-6">
              <b class="">SGST&nbsp;(14%)</b>
          </div>
        <!--   <div class="col-md-2 col-xs-2 col-sm-2">
              <p class="">:</p>
          </div> -->

             <div class="col-md-6 col-xs-6 col-sm-6">
                <span class="pull-right" id="SGSTTaxId">.00</span>
            </div>
        </div>
         <div class="row">
              <div class="col-md-6 col-xs-6 col-sm-6">
              <b class="">CGST&nbsp;(14%)</b>
          </div>
          <!-- <div class="col-md-2 col-xs-2 col-sm-2">
              <p class="">:</p>
          </div> -->

             <div class="col-md-6 col-xs-6 col-sm-6">
                <span class="pull-right" id="CGSTTaxId">.00</span>
            </div>
        </div>
        <div class="row">
              <div class="col-md-6 col-xs-6 col-sm-6">
              <b class="">ET&nbsp;(8%)</b>
          </div>
         <!--  <div class="col-md-2 col-xs-2 col-sm-2">
              <p class="">:</p>
          </div> -->

             <div class="col-md-6 col-xs-6 col-sm-6">
                <span class="pull-right" id="ETTaxId">.00</span>
            </div>
        </div>
        <div class="fx-border1">
        <div class="row m-t-xs" >
              <div class="col-md-6 col-xs-6 col-md-6">
              <b class="">SUBTOTAL (&#8377;)</b>
          </div>
          <!-- <div class="col-md-2 col-xs-2 col-sm-2">
              <p class="">:</p>
          </div> -->

             <div class="col-md-6 col-xs-6 col-md-6">
                <b class="pull-right" id="subTotalId">.00</b> 
            </div>
        </div></div>
      </div>
    
    <div class="row text-center ">
              <div class="col-md-5 col-sm-5 col-xs-5 m-t-sm">
                <h3><b>TICKET&nbsp;TOTAL</b></h3>
          </div>
          <div class="col-md-2 col-xs-1 col-sm-1 m-t-sm">
              <h3><b>:</b></h3>
          </div>

             <div class="col-md-5 col-xs-5 col-sm-5 m-t-sm">
              <h3>  <b id="totalRupId"></b></h3>
            </div>       
       
      </div>
      <div class="row text-center">
       <button type="button" class="btn btn-primary m-t-lg m-b-lg font-bold paymentCls">
            Make Payment
          </button>
      </div>
        </div>
          
         

     </div>
 </div>

</body>
</html>