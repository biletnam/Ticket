<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="Dynamic/css/plugins/footable/footable.core.css" rel="stylesheet">
<style type="text/css">
  .footable > thead > tr > th > span.footable-sort-indicator {
    display: none;
    }

    .back-color {
    background-color: #1ab394;
    color: white;
    font-weight: bold;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('.theaterCounterEmployeCls').addClass("active"); 
	$('.viewTheaterEmployerCls').addClass("active"); 
	
	$(document).on('click','.editEmployee',function(){
		
		var empId=$(this).attr('id');
		$("#hidEmpId").val(empId);
		$("#editEmployeeFormId").submit();
	});
	
	
	$(document).on('click','.deleteEmployee',function(){
		var emp=$(this).attr('id');
		var id = emp.split('_');
	    var empId = id[0];
	   	var empName = id[1];
	   	$("#popUpUserNameId").empty();
	    $("#popUpUserNameId").append(empName);
		$("#hidEmpId1").val(empId);
	   	$("#popUpId").modal({modal: true});   
	});
	
	
    $(document).on('click',"#deleteUserPopupBtnId",function(){
		$("#deleteEmployeeFormId").submit();

   });
	
});

</script>
</head>
<body>
 <div class="row wrapper border-bottom white-bg m-b-md">
                <div class="col-lg-10 col-sm-10">
                   
                    <ol class="breadcrumb m-sm ">
                    
                        <li>
                            <a href="#">Home</a>
                        </li>
                        
                        <li class="active">
                            <strong>View User</strong>
                        </li>
                    </ol>
                </div>
                <div class="col-lg-2 col-sm-2">

                </div>
            </div>
      

       
         <div class=" row ">
            <div class="ibox-title">
                <h2 class="font-bold">Employee Details</h2>
            </div>


             <div class="ibox-content ">
                           <div class="table-responsive">
                            <table class="table table-stripped table-hover footable" data-page-size="8">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>User Id</th>
                                    <th>User Name</th>
                                    <th>Date of Birth </th>
                                    <th>Phone </th>
                                    <th>Email-ID </th>
                                    <th>Theatre</th>
                                    <th>User Role </th>
                                    <th>User Photo</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                 
                                <tbody>
                                <s:iterator value="theaterEmployeeList" status="status">
                                <tr>
                                    <td><s:property value="%{#status.count}" /></td>
                                    <s:if test="%{employee_id == null}">
                                     <td> - </td>
                                    </s:if>
                                    <s:else>
                                       <td><s:property value="employee_id"/></td>
                                    </s:else>
                                    <td><s:property value="first_name"/></td>
                                     <s:if test="%{date_of_birth == null}">
                                     <td> - </td>
                                    </s:if>
                                      <s:else>
                                    <td><s:property value="date_of_birth"/></td>
                                    </s:else>
                                    <td><s:property value="phone_number"/></td>
                                    <td><s:property value="email_id"/></td>
                                     <td><s:property value="theater_name"/></td>
                                    <td><s:property value="employee_role"/></td>
                                    <td class="client-avatar">
                                     <s:if test="%{status == 'Success'}">
                       		          <img alt="layout image" src="getEmployeProfileImage?modelId=<s:property value="theatre_employee_id"/>" class="img-responsive" width="100%" height="40%">
                      
                                      </s:if>
                                     <s:else>
                                    <span> - </span>
                                    </s:else>
                                    
                                    
                                    </td>
                                    <td>
                                    <i class="fa fa-pencil small editEmployee"  id="<s:property value="theatre_employee_id"/> "> </i>
                                    </td>
                                    <td>
                                    <i class="fa fa-trash text-danger m-l-xs deleteEmployee" id="<s:property value="theatre_employee_id"/>_<s:property value="first_name"/>"> </i>
                                    </td>
                                </tr>
                                </s:iterator>
                                </tbody>
                               <tfoot>
                                <tr>
                                    <td colspan="11">
                                        <ul class="pagination pull-right"></ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
        </div>
      
    </div>
     <s:form action="edit-employee-details" id="editEmployeeFormId">
     <s:hidden id="hidEmpId" name="theaterOwnerBean.theatre_employee_id"/>
   </s:form> 
  
   <s:form action="delete-employee-details" id="deleteEmployeeFormId">
     <s:hidden id="hidEmpId1" name="theaterOwnerBean.theatre_employee_id"/>
  </s:form>
  
  
  <div class="modal fade revise-page-modal" id="popUpId" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header revise-page-model-header">
               
              <button type="button" class="close close1" data-dismiss="modal" aria-hidden="true">
              </button>
              <h3 class="modal-title" id="myModalLabel">
              <span id="popUpUserNameId"></span>
              </h3>
            </div>
            <div>
            <div class="modal-body">
              <h3 class="font-bold text-danger text-center">Are you sure you want to delete this User Permanently ?</h3>
               </div>
            <div class="modal-footer">
               <button type="submit" class="btn btn-primary" id="deleteUserPopupBtnId">Yes</button>
               <button type="button" class="btn btn-default" data-dismiss="modal">No</button> 
            </div>
            </div>
          </div>
        </div>
   </div> 
   
   
     <!-- FooTable -->
    <script src="Dynamic/js/plugins/footable/footable.all.min.js"></script>
    <script src="Dynamic/js/plugins/staps/jquery.steps.min.js"></script>
    
   <script>
        $(document).ready(function() {

            $('.footable').footable();
            $('.footable2').footable();

        });

    </script>
    
</body>
</html>