<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Screen</title>
<script src="js/jquery-1.9.1.js"></script>
<link rel="stylesheet" type="text/css" href="css/validationEngine.jquery.css"/>

<script src="js/jquery.validationEngine-en.js"></script>
<script src="js/jquery.validationEngine.js"></script> 
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
       td{
        padding: 4px;
     }

     ul {
  list-style-type: none;
}
     </style>
</head>

<script type="text/javascript">
	$(document).ready(function()
	{
		 
		$('.theaterDetailCls').addClass("active"); 
    	$('.addScreenCls').addClass("active"); 
    	 
		$(".txtNumeric").keypress(function(event){
	        var inputValue = event.charCode;
	        if(!((inputValue > 47 && inputValue < 58) || (inputValue==32) || (inputValue==46))){
	            event.preventDefault();
	        }
	    });
		
		var main_scr_id = '';
		var main_theater_id = '';
		var ticketMap = '';
		var seatCtgryMap = '';
		var numberVal = '';
		var categoryVal = '';
		
		/* First Submit button - Start */
		
		$('#firstFormSubmitId').click(function()
		{
			main_theater_id = $('#theaterId').val();
			main_scr_id = $('#screenId').val();
			if($('#FormId').validationEngine('validate'))
			{
				var theaterIdVal = $('#theaterId').val();
				var scrId = $('#screenId').val();
				var pasCountId = $('#passageCountId').val();
				var ctgryFrmId = $('#categoryFromId').val();
				var ctgryToId = $('#categoryToId').val();
				var orderId = $('.radioCls:checked').attr("id");
				jQuery.ajax({
					data :{
							theaterIdVal : theaterIdVal,
							scrId : scrId,
							pasCountId : pasCountId,
							ctgryFrmId : ctgryFrmId,
							ctgryToId : ctgryToId,
							orderId : orderId
						},
					
					url : "setTheaterWiseScreenDetailSubmit",
					type : "POST",
					success : function(results) 
					{
						var page = JSON.parse(results);
						var obj = page['theaterRowList'];
						seatCtgryMap = page['ticketCategoryMap'];
						ticketMap = page['seatCategoryMap'];
						$('#screenCategoryDivId').empty();
						
						var str = '';
						
						
						str += '<div class="row">';
						str += '<form method="post" id="secondFormId" class="form-horizontal">';
						str += '<div class="col-md-12">';
						str += '<h3>Edit Seating</h3>';
						str += '<div class="ibox-content gray-bg text-center">';
						str += '<div class="row text-center">';
						str += '<table>';
						
						for (var x in obj)
						{
							var obj1 = obj[x];
							str += '<tr>';
							str += '<td>'+obj1['category_name']+'</td>';
							var categoryId = obj1['category_id'];
							for(var i = 0;i<obj1['col_count'];i++)
							{
								var iVal = parseInt(i)+1;
								
								str += '<td>';
								str += '<div class="row sampleCls" id='+categoryId+'_'+iVal+'>';
								str += '<div class="col-md-4">';
								str += '<input type="number" class="form-control validate[required] numberCls">';
								str += '</div>';
								str += '<div class="col-md-4">';
								str += '<select id="selectChange" class="form-control validate[required]">';
								str += '<option value="">Select Category</option>';	
								for(var y in ticketMap)
								{
									var obj4 = ticketMap[y];
									str += '<option value="'+y+'">'+obj4+'</option>';	
								}
								str += '</select>';
								str += '</div>';
								str += '</div>';
								str += '</td>';
							
							}
							str += '</tr>';
							
						}
						str += '</table>';
						str += '</div>';
						str += '</div>';
						str += '</div>';
						str += '</form>';
						str += '</div>';
						str += '<div class="row m-t-xs">';
						str += '<div class="col-md-12">';
						str += '<div class="ibox-content gray-bg">';
						str += '<div class="row m-t-md">';
						str += '<div class="col-md-12">';
						str += '<h2 class="blue-bg m-b-md fx-head-title" >Seat category</h2>';
						str += '<form method="post" id="ThirdFormId" class="form-horizontal">';
						str += '<div class="theaterMainDivClass">';
						str += '<div class="row theaterDetailClass" id="theaterDivId_0">';
						str += '<div class="col-sm-4">';
						str += '<div class="form-group" id="data_5">';
						str += '<div class="input-daterange input-group">';
						str += '<select class="form-control validate[required] categoryFromCls">';
						str += '<option value="">Select Category</option>';	
						for(var z in seatCtgryMap)
							{
							   var obj2 =  seatCtgryMap[z];
							   str += '<option value="'+z+'">'+obj2+'</option>';	
							}
						str += '</select>';
						str += '<span class="input-group-addon">to</span>';
						str += '<select class="form-control validate[required] categoryToCls">';
						str += '<option value="">Select Category</option>';
						for(var z in seatCtgryMap)
						{
						   var obj2 =  seatCtgryMap[z];
						   str += '<option value="'+z+'">'+obj2+'</option>';	
						}
						str += ' </select>';
						str += '</div>';
						str += '</div>';
						str += '</div>';
						str += '<div class="col-sm-2">';
						str += '<input type="number" placeholder="price" name="" class="form-control validate[required] numberCls1">';
						str += '</div>';
						str += '<div class="col-sm-3">';
						str += '<input type="text" name="" class="form-control validate[required] seatCategoryNameCls" placeholder="Enter seating category name" title="seating category name">';
						str += '</div>';
						str += '<div class="col-sm-1 m-t-xs fx-font20">';
						str += '<i class="fa fa-plus-square text-navy m-r-sm addBtnCls"></i>';
						str += '</div>';
						str += '</div>';
						str += '</div>';
						str += '</form>';
						str += '</div>';
						str += '</div>';
						str += '<div class="row m-t-md">';
						str += '<div class="col-md-12">';
						str += '<button id="secondFormSubmitId" class="btn btn-primary pull-right"> Submit</button>';
						str += '</div>';
						str += '</div>';
						str += '</div>';
						str += '</div>';
						str += '</div>';
						
						
						$('#screenCategoryDivId').append(str);
						$('#screenCategoryDivId').show();
					
					}
						
				});
			}
		});
		
		var categoryFromValue = '';
		var categoryToValue = '';
		
		
		$(document).on('change','#selectChange',function()
		{
			categoryVal = categoryVal+"_"+$(this).val();

		});
		
		$(document).on('change','.categoryFromCls',function()
		{
			categoryFromValue = categoryFromValue+"_"+$(this).val();
		});
		
		$(document).on('change','.categoryToCls',function()
		{
			categoryToValue = categoryToValue+"_"+$(this).val();
		});
	
		$(document).on('click','#secondFormSubmitId', function()
				{
					$('#secondFormId').validationEngine({scroll :false});
					$('#ThirdFormId').validationEngine({scroll :false});
					
					if($('#secondFormId').validationEngine('validate') && $('#ThirdFormId').validationEngine('validate'))
					{
						var value = '';
						var value1 = '';
						var value2 = '';
						var value3 = '';
						
						var localVal = '';
						var localVal1 = '';
						var localVal2 = '';
						
						/* For Seat Count - Start */
						
						$('.numberCls').each(function()
						{
							localVal = localVal+"_"+$(this).val();
						});
						
						localVal =  localVal.split("_");
						value1 =  categoryVal.split("_");
						
						var i = 1;
						
						$('.sampleCls').each(function()
						{	
							value = value+"-"+value1[i]+"_"+localVal[i]+"_"+$(this).attr("id");
							i = parseInt(i)+1;
						});
						
						value = value.substring(1);
						
						/* End */
						
					
						value2 =  categoryFromValue.split("_");
						value3 =  categoryToValue.split("_");
						var finalValue = '';
						
						$('.numberCls1').each(function()
						{
							localVal1 = localVal1+"_"+$(this).val();
						});
								
						$('.seatCategoryNameCls').each(function()
						{
							localVal2 = localVal2+"_"+$(this).val();
						});
						
						
						localVal1 =  localVal1.split("_");
						localVal2 =  localVal2.split("_");
						
						
								
						for(var j = 1;j<localVal1.length;j++)
							{
								finalValue = finalValue+"-"+value2[j]+"_"+value3[j]+"_"+localVal1[j]+"_"+localVal2[j];
							}
						
						finalValue = finalValue.substring(1);
						jQuery.ajax({
							data :{
									theaterIdVal : main_theater_id,
									scrId : main_scr_id,
									seatCountValue : value,
									searCategoryValue : finalValue
								},
							
							url : "setTheaterSeatCountDetailSubmit",
							type : "POST",
							success : function(results) 
							{
								var page = JSON.parse(results);
								var finalList = page['theaterRowList'];
								var str = '';
								
								$('#floorDivId').empty();
								
								str += '<div class="col-md-12">';
								str += '<div class="row m-t-md">';
								str += '</div>';
								str += '<div class="row">';
								str += '<div class="col-md-offset-2 col-md-8">';
								str += '<div class="table-responsive"> ';
								str += '<table>';
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
											if(obj3['order_id']==1)
											{
												if(loopVal!=0)
												{
													for(var k = 0;k<loopVal;k++)
													{
														str += '<td></td>';
													}
													for(var j = 0;j<obj3['seat_count'];j++)
													{
														str += '<td><img src="images/available.png"></td>';
													}
												}
												else if(loopVal==0)
												{
													for(var j = 0;j<obj3['seat_count'];j++)
													{
														str += '<td><img src="images/available.png"></td>';
													}
												}
											}
											else if(obj3['order_id']==2)
											{
												if(loopVal!=0)
												{
													for(var j = 0;j<obj3['seat_count'];j++)
													{
														str += '<td><img src="images/available.png"></td>';
													}
													for(var k = 0;k<loopVal;k++)
													{
														str += '<td></td>';
													}
												}
												else if(loopVal==0)
												{
													for(var j = 0;j<obj3['seat_count'];j++)
													{
														str += '<td><img src="images/available.png"></td>';
													}
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
								str += '<div class="col-md-12">';
								str += '<img src="images/screen.png" class="m-l img-responsive">';
								str += '</div>';
								str += '</div>';
								str += '</div>';
								
								
								$('#floorDivId').append(str);
								$('#floorDivId').show();
								
							}
								
						});		
						
					}
					
					
				});
		
		/* End */
		
		/* For Theater Change - start */
		
		$('#theaterId').change(function()
		{
			var theaterIdVal = $(this).val();
			jQuery.ajax({
				data :{
					theaterIdVal : theaterIdVal
					},
				
				url : "getTheaterAgainstScreenForEdit",
				type : "POST",
				success : function(results) 
				{
					var page = JSON.parse(results);
					$('#theaterScreenDivId').empty();
					
					var obj = page['screenMap'];
					var objMap = page['seatCategoryMap'];
					
					var str = '';
					
					str += ' <div class="form-group">';
					str += '<div class="col-sm-3">';
					str += '<label  class="fx-font20 ">Screen</label>';
					str += '<h5>('+page['theater_name']+')</h5>';
					str += '</div>';
					str += '<div class="col-sm-1"> <label>:</label></div>';
					str += '<div class="col-sm-6 ">';
					str += '<select class="form-control " id="screenId" name='+page['screen_id']+'>';
					for(var x in obj)
					{
						var obj1 = obj[x];
						if(page['screen_id'] == x)
						{
							str += '<option value='+x+' selected>'+obj1+'</option>';
						}	
						else
						{
							str += '<option value='+x+'>'+obj1+'</option>';
						}
					}
					str += '</select>';
					str += '</div>';
					str += '</div>';
					str += '<div class="form-group">';
					str += '<label  class="col-sm-3 " >';
					str += '<span class="pull-right">Screen Layout</span></label>';
					str += '<div class="col-sm-1"> <label>:</label></div>';
					str += '<div class="col-sm-6" id="imgDivId">';
					str += '<img src="getScreenImage?modelId='+page['screen_id']+'" alt="Uploaded Screen Layout" class="img-responsive">';
					str += '</div>';
					str += '</div>';
					str += '<div class="form-group">';
					str += '<label  class="col-sm-3 " >';
					str += '<span class="pull-right">Passage</span>  ';
					str += '</label>';
					str += '<div class="col-sm-1"> <label>:</label></div>';
					str += '<div class="col-sm-6">';
					str += '<input type="text" value="'+page['passage_count']+'" class="form-control m-l txtNumeric validate[required]" id="passageCountId"/>';
					str += '</div>';
					str += '</div>';
					str += '<div class="form-group">';
					str += '<label  class="col-sm-3 " >';
					str += '<span class="pull-right">Row Arrangement</span> </label>';
					str += '<div class="col-sm-1"> <label>:</label></div>';
					str += '<div class="col-sm-3">';
					str += '<div class="form-group">';
					str += '<div class="input-daterange input-group">';
					str += '<select id="categoryFromId" class="form-control">';
					for(var xy in objMap)
					{
						var objMapVal = objMap[xy];
						if(page['category_from_id'] == xy)
						{
							str += '<option value='+xy+' selected>'+ objMapVal +'</option>';
						}
						else
						{
							str += '<option value='+xy+'>'+ objMapVal +'</option>';
						}
					}
					str += '</select>';
					str += '<span class="input-group-addon">to</span>';
					str += '<select id="categoryFromId" class="form-control">';
						for(var xy in objMap)
						{
							var objMapVal1 = objMap[xy];
							if(page['category_to_id'] == xy)
							{
								str += '<option value='+xy+' selected>'+ objMapVal1 +'</option>';
							}
							else
							{
								str += '<option value='+xy+'>'+ objMapVal1 +'</option>';
							}
						}
					str += '</select>';
					str += '</div></div></div>';
					str += '<div class="col-sm-3 ">';
					str += '<label class="radio-inline">';
						if(page['order_id'] == 1)
						{
							str += '<input type="radio" name="radioNam" checked="checked" class="radioCls validate[required]" id="1">Ascending';
						}
						else
						{
							str += '<input type="radio" name="radioNam" class="radioCls validate[required]" id="1">Ascending';
						}
					str += '</label>';
					str += '<label class="radio-inline">';
						if(page['order_id'] == 2)
						{
							str += '<input type="radio" name="radioNam" checked="checked"  class="radioCls validate[required]" id="2">Descending';
						}
						else
						{
							str += '<input type="radio" name="radioNam"  class="radioCls validate[required]" id="2">Descending';
						}
					str += '</label>';
					str += '</div>';
					str += '</div>';
					$('#theaterScreenDivId').append(str);
					
				}
			});
			
		});
		
		/* End */
		
		/* For Screen Change - Start */
	 $(document).on('change','#screenId',function()
		{
		 	$('#screenCategoryDivId').hide();
		 	$('#floorDivId').hide();
		 	$('#imgDivId').empty();
			var str = '<img src="getScreenImage?modelId='+scrId+'" alt="Uploaded Screen Layout" class="img-responsive">';
			$('#imgDivId').append(str);
			var scrId = $(this).val();
			
			jQuery.ajax({
				data :{
					scrId : scrId
					},
				
				url : "getScreenDetailsForEdit",
				type : "POST",
				success : function(results) 
				{
					var page = JSON.parse(results);
					
					$('#innerDivId').empty();
					
					var objMap = page['seatCategoryMap'];
					var str = '';
					
					str += '<div class="form-group">';
					str += '<label  class="col-sm-3 " >';
					str += '<span class="pull-right">Passage</span>  ';
					str += '</label>';
					str += '<div class="col-sm-1"> <label>:</label></div>';
					str += '<div class="col-sm-6">';
					str += '<input type="text" value="'+page['passage_count']+'" class="form-control m-l txtNumeric validate[required]" id="passageCountId"/>';
					str += '</div>';
					str += '</div>';
					str += '<div class="form-group">';
					str += '<label  class="col-sm-3 " >';
					str += '<span class="pull-right">Row Arrangement</span> </label>';
					str += '<div class="col-sm-1"> <label>:</label></div>';
					str += '<div class="col-sm-3">';
					str += '<div class="form-group">';
					str += '<div class="input-daterange input-group">';
					str += '<select id="categoryFromId" class="form-control">';
					for(var xy in objMap)
					{
						var objMapVal = objMap[xy];
						if(page['category_from_id'] == xy)
						{
							str += '<option value='+xy+' selected>'+ objMapVal +'</option>';
						}
						else
						{
							str += '<option value='+xy+'>'+ objMapVal +'</option>';
						}
					}
					str += '</select>';
					str += '<span class="input-group-addon">to</span>';
					str += '<select id="categoryFromId" class="form-control">';
						for(var xy in objMap)
						{
							var objMapVal1 = objMap[xy];
							if(page['category_to_id'] == xy)
							{
								str += '<option value='+xy+' selected>'+ objMapVal1 +'</option>';
							}
							else
							{
								str += '<option value='+xy+'>'+ objMapVal1 +'</option>';
							}
						}
					str += '</select>';
					str += '</div></div></div>';
					str += '<div class="col-sm-3 ">';
					str += '<label class="radio-inline">';
						if(page['order_id'] == 1)
						{
							str += '<input type="radio" name="radioNam" checked="checked" class="radioCls validate[required]" id="1">Ascending';
						}
						else
						{
							str += '<input type="radio" name="radioNam" class="radioCls validate[required]" id="1">Ascending';
						}
					str += '</label>';
					str += '<label class="radio-inline">';
						if(page['order_id'] == 2)
						{
							str += '<input type="radio" name="radioNam" checked="checked"  class="radioCls validate[required]" id="2">Descending';
						}
						else
						{
							str += '<input type="radio" name="radioNam"  class="radioCls validate[required]" id="2">Descending';
						}
					str += '</label>';
					str += '</div>';
				
					$('#innerDivId').append(str);
				}
					
			});
			
			
		});
		/* End */
	 
	
		/* For Seat Category - Add And Remove start */
		
			$(document).on('click','.addBtnCls',function()
			{
				var id = $(this).closest('.theaterDetailClass').attr('id');
				var mainValue = id.split("_");
				
				$('#'+id+' .addBtnCls').hide();
				$('#'+id+' .removeBtnCls').hide(); 
				var divCount = parseInt(mainValue[1]) + 1;
				var str = '';
				
				str += '<div class="row theaterDetailClass"  id="theaterDivId_'+divCount+'">';
				str += '<div class="col-sm-4">';
				str += '<div class="form-group" id="data_5">';
				str += '<div class="input-daterange input-group">';
				str += '<select class="form-control validate[required] categoryFromCls">';
				str += '<option value="">Select Category</option>';
				for(var z in seatCtgryMap)
				{
				   var obj2 =  seatCtgryMap[z];
				   str += '<option value="'+z+'">'+obj2+'</option>';	
				}
				str += '</select>';
				str += '<span class="input-group-addon">to</span>';
				str += '<select class="form-control validate[required] categoryToCls">';
				str += '<option value="">Select Category</option>';
				for(var z in seatCtgryMap)
				{
				   var obj2 =  seatCtgryMap[z];
				   str += '<option value="'+z+'">'+obj2+'</option>';	
				}
				str += ' </select>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
				str += '<div class="col-sm-2">';
				str += '<input type="number" placeholder="price" name="" class="form-control validate[required] numberCls1">';
				str += '</div>';
				str += '<div class="col-sm-3">';
				str += '<input type="text" name="" class="form-control validate[required] seatCategoryNameCls" placeholder="Enter seating category name" title="seating category name">';
				str += '</div>';
				str += '<div class="col-sm-1 m-t-xs fx-font20">';
				str += '<i class="fa fa-plus-square text-navy m-r-sm addBtnCls"></i>';
				str += '<i class="fa fa-minus-square text-danger removeBtnCls"></i>';
				str += '</div>';
				str += '</div>';
				
				$('.theaterMainDivClass').append(str);
				
			});
		
			$(document).on('click',".removeBtnCls",function()
			{
				var id = $(this).closest('.theaterDetailClass').attr('id');
				var mainValue = id.split("_");
				var divCount = parseInt(mainValue[1]) - 1;
				if(divCount > 0)
					{
						$("#"+id).remove();
						$('#'+mainValue[0]+'_'+divCount+' .addBtnCls').show();
						$('#'+mainValue[0]+'_'+divCount+' .removeBtnCls').show();
					}
				else
					{
						$("#"+id).remove();
						$('#'+mainValue[0]+'_'+divCount+' .addBtnCls').show();
						$('#'+mainValue[0]+'_'+divCount+' .removeBtnCls').hide();
					} 
				
			    });	
		
		/* End */
	 
	 
	 
	 
	});

</script>

<body>

<div class="row wrapper border-bottom white-bg ">
                <div class="col-lg-10">
                   
                    <ol class="breadcrumb m-sm ">
                        <li>
                            <a href="#">Home</a>
                        </li>
                      
                       
                        <li class="active">
                            <strong>Edit Screen</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2">

                </div>
</div>

<s:if test="%{theaterOwnerBean.status == 'failure'}">

</s:if>
<s:elseif test="%{theaterOwnerBean.status == 'success'}">
<div class="wrapper wrapper-content ">
         <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h2 class="font-bold">Edit Screen  <span class="pull-right">
                              <s:select list="theaterOwnerBean.theaterMap" cssClass="form-control" id="theaterId" name="theaterOwnerBean.theater_id" headerKey=""/>
                            </span></h2>
                        </div>
                        <div class="ibox-content">
                        <h2 class="blue-bg m-b-md fx-head-title" >Edit Screen Details </h2>
                         
                        <form id="FormId" class="form-horizontal">

					<div id="theaterScreenDivId">
                        <div class="form-group">
                        <div class="col-sm-3">
                             <label  class="fx-font20 ">
                                    Screen 
                                    
                                </label>
                                <h5>(<s:property value="theaterOwnerBean.theater_name"/>)</h5>
                        </div>
                         <div class="col-sm-1"> <label>:</label></div>
                               
                                <div class="col-sm-6 ">
                                    <s:select cssClass = "form-control" list="theaterOwnerBean.screenMap" name="theaterOwnerBean.screen_id" id="screenId" headerKey=""/>
                                </div>
                            </div>

                <div class="form-group">
                     
                    <label  class="col-sm-3 " >
                       <span class="pull-right">Screen Layout</span>  
                    </label>
                    <div class="col-sm-1"> <label>:</label></div>
                    <div class="col-sm-6" id="imgDivId">
                        <img src="getScreenImage?modelId=<s:property value="theaterOwnerBean.screen_id"/>" alt="Uploaded Screen Layout" class="img-responsive">
                    </div>
                </div>
                

				<div id="innerDivId">
                <div class="form-group">
                     
                    <label  class="col-sm-3 " >
                       <span class="pull-right">Passage</span>  
                    </label>
                    <div class="col-sm-1"> <label>:</label></div>
                    <div class="col-sm-6">
                    
                    <s:textfield name="theaterOwnerBean.passage_count" cssClass="form-control m-l txtNumeric validate[required]" id="passageCountId"/>
                    
                    </div>
                </div>
                 <div class="form-group">
                     
                    <label  class="col-sm-3 " >
                       <span class="pull-right">Row Arrangement</span>  
                    </label>
                    <div class="col-sm-1"> <label>:</label></div>
                    <div class="col-sm-3">
                         <div class="form-group" id="data_5">
                               
                                <div class="input-daterange input-group">
                                <s:select list="theaterOwnerBean.ticketCategoryMap" name="theaterOwnerBean.category_from_id"  id="categoryFromId" cssClass = "form-control validate[required]" headerKey="" headerValue="--Select Category--"/>
                                    <span class="input-group-addon">to</span>
                                 <s:select list="theaterOwnerBean.ticketCategoryMap" name="theaterOwnerBean.category_to_id" id="categoryToId"  cssClass = "form-control validate[required]" headerKey="" headerValue="--Select Category--"/> 
                                </div>
                            </div>
                    </div>
                    
                    <div class="col-sm-3 ">
                          <label class="radio-inline">
                          <s:if test="%{theaterOwnerBean.order_id == 1}">
                          <input type="radio" name="radioNam" checked="checked" class="radioCls validate[required]" id="1">Ascending
                          </s:if>
                          <s:else>
                            <input type="radio" name="radioNam" class="radioCls validate[required]" id="1">Ascending
                          </s:else> 
                          </label>
                          <label class="radio-inline">
                           <s:if test="%{theaterOwnerBean.order_id == 2}">
                              <input type="radio" name="radioNam" checked="checked"  class="radioCls validate[required]" id="2">Descending
                          </s:if>
                          <s:else>
                            <input type="radio" name="radioNam"  class="radioCls validate[required]" id="2">Descending
                          </s:else> 
                          </label>
                    </div>
                 
                </div>
                </div>
                </div>
</form>
                  <div class="form-group m-t-md">
                <div class="col-sm-1 col-sm-offset-10">
                <button id="firstFormSubmitId"  class="btn btn-primary "> Submit</button>
                    </div>
                </div>

                <hr>

<div id="screenCategoryDivId" style="display: none;">
                <div class="row">
                  <div class="col-md-12">
                  <h3>Edit Seating</h3>
                  <div class="ibox-content gray-bg text-center">
                 <div class="row text-center">
                  <table>
                    <tr>
                      <td>A</td>
                      <td>
                        <div class="row">
                          <div class="col-md-4">
                            <input type="number" name="" class="form-control">
                          </div>
                          <div class="col-md-4">
                              <select class="form-control ">
                                   <option value="">R-L</option>
                                   <option value="">L-R</option>
                                   <option value="">Custom</option>
                               </select>
                          </div>
                        </div>
                      </td>

                       <td>
                        <div class="row">
                          <div class="col-md-4">
                            <input type="number" name="" class="form-control">
                          </div>
                          <div class="col-md-4">
                              <select class="form-control ">
                                   <option value="">R-L</option>
                                   <option value="">L-R</option>
                                   <option value="">Custom</option>
                               </select>
                          </div>
                        </div>
                      </td>

                       <td>
                        <div class="row">
                          <div class="col-md-4">
                            <input type="number" name="" class="form-control">
                          </div>
                          <div class="col-md-4">
                              <select class="form-control ">
                                   <option value="">R-L</option>
                                   <option value="">L-R</option>
                                   <option value="">Custom</option>
                               </select>
                          </div>
                        </div>
                      </td>

                    </tr>

                     <tr>
                      <td>B</td>
                      <td>
                        <div class="row">
                          <div class="col-md-4">
                            <input type="number" name="" class="form-control">
                          </div>
                          <div class="col-md-4">
                              <select class="form-control ">
                                   <option value="">R-L</option>
                                   <option value="">L-R</option>
                                   <option value="">Custom</option>
                               </select>
                          </div>
                        </div>
                      </td>

                       <td>
                        <div class="row">
                          <div class="col-md-4">
                            <input type="number" name="" class="form-control">
                          </div>
                          <div class="col-md-4">
                              <select class="form-control ">
                                   <option value="">R-L</option>
                                   <option value="">L-R</option>
                                   <option value="">Custom</option>
                               </select>
                          </div>
                        </div>
                      </td>

                       <td>
                        <div class="row">
                          <div class="col-md-4">
                            <input type="number" name="" class="form-control">
                          </div>
                          <div class="col-md-4">
                              <select class="form-control ">
                                   <option value="">R-L</option>
                                   <option value="">L-R</option>
                                   <option value="">Custom</option>
                               </select>
                          </div>
                        </div>
                      </td>
                      
                    </tr>

                     <tr>
                      <td>C</td>
                      <td>
                        <div class="row">
                          <div class="col-md-4">
                            <input type="number" name="" class="form-control">
                          </div>
                          <div class="col-md-4">
                              <select class="form-control ">
                                   <option value="">R-L</option>
                                   <option value="">L-R</option>
                                   <option value="">Custom</option>
                               </select>
                          </div>
                        </div>
                      </td>

                       <td>
                        <div class="row">
                          <div class="col-md-4">
                            <input type="number" name="" class="form-control">
                          </div>
                          <div class="col-md-4">
                              <select class="form-control ">
                                   <option value="">R-L</option>
                                   <option value="">L-R</option>
                                   <option value="">Custom</option>
                               </select>
                          </div>
                        </div>
                      </td>

                       <td>
                        <div class="row">
                          <div class="col-md-4">
                            <input type="number" name="" class="form-control">
                          </div>
                          <div class="col-md-4">
                              <select class="form-control ">
                                   <option value="">R-L</option>
                                   <option value="">L-R</option>
                                   <option value="">Custom</option>
                               </select>
                          </div>
                        </div>
                      </td>
                      
                    </tr>
                  </table>
                  </div>
 </div>
                </div>
              </div>


              <div class="row m-t-xs">
                <div class="col-md-12">
                  <div class="ibox-content gray-bg">
                      <div class="row m-t-md">
                    <div class="col-md-12">
                       <form method="get" class="form-horizontal">
                          <div class="form-group">
                     
                    <label  class="col-sm-3 " >
                       <span class="pull-right">Seating  Category</span>  
                    </label>
                    <div class="col-sm-1"> <label>:</label></div>
                    <div class="col-sm-3">
                         <div class="form-group" id="data_5">
                               
                                <div class="input-daterange input-group">
                                <select class="form-control">
                                    <option value="">Select </option>
                                    <option value="1">A</option>
                                    <option value="2">B</option>
                                    <option value="3">C</option>
                                    <option value="4">D</option>
                                </select>
                                    <span class="input-group-addon">to</span>
                                    <select class="form-control">
                                        <option value="">Select </option>
                                        <option value="1">A</option>
                                        <option value="2">B</option>
                                        <option value="3">C</option>
                                        <option value="4">D</option>
                                    </select>
                                </div>
                            </div>
                    </div>
                    
                    <div class="col-sm-1 ">
                        <input type="text" placeholder="price" name="" class="form-control">
                    </div>
                    <div class="col-sm-2">
                      <input type="text" name="" class="form-control" placeholder="Enter seating category name" title="seating category name">
                    </div>
                    <div class="col-sm-1 m-t-xs fx-font20"> <i class="fa fa-plus-square text-navy m-r-sm"></i> <i class="fa fa-minus-square text-danger "></i>
                    </div> 
                   

                </div>
                       </form>
                    </div>
                  </div>

                  <div class="row m-t-md">
                        <div class="col-md-12">
                            <button class="btn btn-primary pull-right"> Submit</button>
                        </div>
                    </div> 

                  </div>                  
                </div>
              </div>
</div>
              <div class="row" style="display: none;" id="floorDivId">
               
               </div>
              
                 


                        </div>
                    </div>
                </div>
            </div>

         

        </div>
</s:elseif>

 </div>

</body>
</html>