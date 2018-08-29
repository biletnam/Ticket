<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Screen detail</title>
</head>
 <script src="js/jquery-1.9.1.js"></script>
 
<style type="text/css">
       .fx-head-title{
        padding: 8px;font-size: 17px;width: 30%;border-radius: 0px 3px 3px 0px;font-weight: bold;margin-left: -20px
       }

       .fx-font20{
        font-size: 20px;
       }
       .m-l{
            margin-left: 110px;
       }
       td{
        padding: 4px;
     }

     ul {
  list-style-type: none;
}
.fx-margin-left{
  margin-left: -100px;
}
</style>

<script type="text/javascript">

	$(document).ready(function()
	{
		var scrOriVal;
		$('#theaterId').change(function()
				{
					var theaterIdVal = $(this).val();
					
					jQuery.ajax({
						data :{
							theaterIdVal : theaterIdVal
							},
						
						url : "getTheaterAgainstScreenForView",
						type : "POST",
						success : function(results) 
						{
							var page = JSON.parse(results);
							scrOriVal = page['screen_id'];
							$('#theaterScreenDivId').empty();
							
							var obj = page['screenMap'];
							
							var str = '';
							
							str += ' <div class="form-group">';
							str += '<div class="col-sm-3">';
							str += '<label  class="fx-font20 ">Screen</label>';
							str += '<h5>('+page['theater_name']+')</h5>';
							str += '</div>';
							str += '<div class="col-sm-1"> <label>:</label></div>';
							str += '<div class="col-sm-6 ">';
							str += '<select class="form-control " id="screenId">';
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
							
							$('#theaterScreenDivId').append(str);
							getFloorPlanDetails(theaterIdVal,scrOriVal); 
						}
					});
					
					
					
				});
		
		function getFloorPlanDetails(theaterIdVal,scrOriVal)
		{

			 jQuery.ajax
			 ({
				 	data :{
						theaterIdVal : theaterIdVal,
						scrId : scrOriVal,
						
					},
					url : "getBasicFloorPlanDetails",
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
						}
						else
						{
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
					}
				 
			 });
		}
		
		 var main_theater_id = $('#theaterId').val();
		 var main_scr_id = $('#screenId').val();
		 
		 $(document).on('change','#screenId',function()
			{
						var scrId = $(this).val();
						var theaterId = $('#theaterId').val();
						jQuery.ajax
						 ({
							 	data :{
									theaterIdVal : theaterId,
									scrId : scrId,
									
								},
								url : "getBasicFloorPlanDetails",
								type : "POST",
								success : function(results) 
								{
									$('#imgDivId').empty();
									var str1 = '<img src="getScreenImage?modelId='+scrId+'" alt="Uploaded Screen Layout" class="img-responsive">';
									$('#imgDivId').append(str1);
									
									var page = JSON.parse(results);
									var finalList = page['theaterRowList'];
									
									if($.isEmptyObject(finalList))
									{
										$('#floorDivId').empty();
										var str = 'The Screen have no Floor plan';
										$('#floorDivId').append(str);
										$('#floorDivId').show();
									}
									else
									{
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
								}
								
						 });
			});
		 
		 
		
		 jQuery.ajax
		 ({
			 	data :{
					theaterIdVal : main_theater_id,
					scrId : main_scr_id,
					
				},
				url : "getBasicFloorPlanDetails",
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
					}
					else
					{
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
					str += '<img src="images/screen.png"  class="m-l img-responsive" style="width: 65%;">';
					str += '</div>';
					str += '</div>';
					str += '</div>';
					
					
					$('#floorDivId').append(str);
					$('#floorDivId').show();
					}
				}
			 
		 });
		
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
                            <strong>View Screen</strong>
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
                            <h2 class="font-bold">View screen  <span class="pull-right">
                              <s:select list="theaterOwnerBean.theaterMap" cssClass="form-control" id="theaterId" name="theaterOwnerBean.theater_id" headerKey=""/>
                            </span></h2>
                        </div>
                        <div class="ibox-content">
                        <h2 class="blue-bg m-b-md fx-head-title" >Screen Details </h2>
                         
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
                
</div>

</form>
                <hr>

<div class="row">

            <div class="col-md-12">
                <h2  class="fx-font20 navy-bg fx-head-title m-b-md">
                                    Floor Plan
                                    
                                </h2>
                
            </div>
</div>
              <div class="row"  id="floorDivId" style="display: none;">
               <div class="col-md-offset-2 col-md-8">
        
         <div class="table-responsive"> 
         <table>
            
              <tbody class="text-center">
                   <tr>
                       <td><h4 >A</h4></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>A</h4></td>
                   </tr>
                   <tr>
                        <td><h4>B</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><h4>B</h4></td>
                   </tr>
                   <tr>
                        <td><h4>C</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/taken.png"></td>
                       <td><img src="img/taken.png"></td>
                       <td><img src="img/taken.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><h4>C</h4></td>
                   </tr>
                   <tr>
                        <td><h4>D</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/taken.png"></td>
                       <td><img src="img/taken.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/taken.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/taken.png"></td>
                       <td><h4>D</h4></td>
                   </tr>
                   <tr>
                        <td><h4>E</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>E</h4></td>
                   </tr>
                   <tr>
                         <td><h4>F</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>F</h4></td>
                   </tr>
                   <tr>
                         <td><h4>G</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>G</h4></td>
                   </tr>
                   <tr>
                         <td><h4>H</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>H</h4></td>
                   </tr>
                   <tr>
                         <td><h4>I</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>I</h4></td>
                   </tr>  
                   <tr>
                         <td><h4>J</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/selected.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
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
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td></td>
                       <td><h4>K</h4></td>
                   </tr>
                   <tr>
                        <td><h4>L</h4></td>
                       <td></td>
                       <td></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td><img src="img/available.png"></td>
                       <td></td>
                       <td></td>
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
              
                 


                        </div>
                    </div>
                </div>
            </div>

         

        </div>
</s:elseif>

 </div>
</body>
</html>