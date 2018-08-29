<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="/struts-tags" prefix="s"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile view</title>
</head>
<body>


  <div class="wrapper wrapper-content ">
         <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h2>Profile View <span class="pull-right"> <a href="theater-owner-edit-profile-page"><i class="fa fa-pencil-square-o"></i></a></span></h2>
                        </div>
                        <div class="ibox-content">
                           <form  class="form-horizontal">
    <div class="row">
      <div class="col-md-12" >
       
        <div class="form-group">
              <label  class="col-md-3" >
                 First Name
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                    <s:property value="theaterOwnerBean.theater_owner_first_name"/>
                </div>
        </div>
        
          <div class="form-group">
              <label  class="col-md-3" >
                  Last Name
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                    <s:property value="theaterOwnerBean.theater_owner_last_name"/>
                </div>
        </div>

         <div class="form-group">
              <label  class="col-md-3" >
                  Email
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7">
                 <s:property value="theaterOwnerBean.theater_owner_email"/>
                </div>
        </div>

         

         <div class="form-group">
              <label  class="col-md-3" >
                Phone
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                     <s:property value="theaterOwnerBean.theater_owner_mobile"/>
                </div>
        </div>

         <div class="form-group">
              <label  class="col-md-3" >
                  Address
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                    <s:property value="theaterOwnerBean.theater_owner_address"/>
                </div>
        </div>
        
         <div class="form-group">
              <label  class="col-md-3" >
                  State 
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                     <s:property value="theaterOwnerBean.state_name"/>
                </div>
        </div>
        
        <div class="form-group">
              <label  class="col-md-3" >
                  District
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                    <s:property value="theaterOwnerBean.district_name"/>
                </div>
        </div>
        
         <div class="form-group">
              <label  class="col-md-3" >
                  City
              </label>
              <label class="col-md-1">:</label>
                <div class="col-md-7 ">
                    <s:property value="theaterOwnerBean.city_name"/>
                </div>
        </div>
            		 <div class="form-group">
                <!-- <div class="col-sm-2 col-sm-offset-8">
                <button type="submit" class="btn btn-primary " > Edit</button>
                </div> -->
                </div>
        
      
     

        

      </div>
      
    </div>
  </form>
</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>