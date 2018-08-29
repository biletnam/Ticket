<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.9.1.js"></script>

</head>
<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header" >
                    <div class="dropdown profile-element"> <span>
                            <img alt="image" style="width: 32%;height: 50px;" class="img-circle" src="getUserProfileImage?modelId=<s:property value="%{#session.theater_owner_id}"/>" />
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold"><s:property value="%{#session.name}"/></strong>
                             </span> <span class="text-muted text-xs block">Theatre Owner<b class="caret"></b></span> </span> </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="theater-owner-profile-view">Profile</a></li>
                            <li><a href="change-password">Change password</a></li>
                            <li class="divider"></li>
                            <li><a href="theater-owner-logout">Logout</a></li>
                        </ul>
                       
                    </div>
                    <div class="logo-element">
                    </div>
                </li>
               
               
	             <li class="dashboardCls">
		           <a href="theater-owner-login-success"><i class="fa fa-ticket" aria-hidden="true"></i>
				  <span class="nav-label">Dashboard</span></a>
	             </li>
	                  		
                   <li class="theaterDetailCls">
                       <a href="#"><i class="fa fa-bars"></i> <span class="nav-label">Theatre</span> <span class="fa arrow"></span></a>
                       <ul class="nav nav-second-level collapse">
                       
	                  		
	                	   <li class="addTheaterCls">
		                       <a href="add-theater"><i class="fa fa-ticket" aria-hidden="true"></i>
							   <span class="nav-label">Add theatre</span></a>
	                  		</li>
	                  		
	                  		<s:if test="%{#session.nav_bar_screen_status  == 'Yes'}">
	                  		
	                  		</s:if>
	                  		<s:else>
	                  		<li class="addScreenCls">
		                       <a href="add-screen-detail"><i class="fa fa-desktop" aria-hidden="true"></i>
							   <span class="nav-label">Add screen</span></a>
	                  		</li>
	                  		</s:else>
	                  		
	                  		
	                  		<li class="viewScreenCls">
		                       <a href="view-screen-detail"><i class="fa fa-desktop" aria-hidden="true"></i>
							   <span class="nav-label">View screen detail</span></a>
	                  		</li>
	                  		<%-- <li class="viewScreenCls">
		                       <a href="edit-screen-detail"><i class="fa fa-pencil" aria-hidden="true"></i>
							   <span class="nav-label">Edit screen detail</span></a>
	                  		</li> --%>
                		</ul>
                  </li>	
                     <li class="theaterCounterEmployeCls">
                       <a href="#"><i class="fa fa-folder-open-o"></i> <span class="nav-label">Ticket Counter</span> <span class="fa arrow"></span></a>
                       <ul class="nav nav-second-level collapse">
                       
	                  		 <li class="addTheaterEmployerCls">
		                       <a href="add-counter-user"><i class="fa fa-cart-plus" aria-hidden="true"></i>
							   <span class="nav-label">Add User</span></a>
	                  		</li>
	                  
	                     <s:if test="%{#session.nav_bar_user_status  == 'Yes'}">
	                  		<li class="viewTheaterEmployerCls">
		                       <a href="view-counter-user"><i class="fa fa-street-view" aria-hidden="true"></i>
							   <span class="nav-label">View User</span></a>
	                  		</li>
	                  	</s:if>
	                  	<s:else>
	                  	</s:else>
                		</ul>
                  </li>	
                   <li class="movieDetailCls">
                       <a href="#"><i class="fa fa-folder"></i> <span class="nav-label">Movies</span> <span class="fa arrow"></span></a>
                       <ul class="nav nav-second-level collapse">
	                	   <li class="addMovieCls">
		                       <a href="add-movie"><i class="fa fa-film" aria-hidden="true"></i>
							   <span class="nav-label">Add Movie</span></a>
	                  		</li>
	                  	 <s:if test="%{#session.nav_bar_movie_status  == 'Yes'}">
	                  	  <li class="viewMovieCls">
		                       <a href="view-movie"><i class="fa fa-eye" aria-hidden="true"></i>
							   <span class="nav-label">View Movie</span></a>
	                  	  </li> 
	                  	  </s:if>
	                  	  <s:else>
	                  	</s:else>
	                  	<li class="movieArrangementCls">
		                       <a href="movie-arrangement"><i class="fa fa-desktop" aria-hidden="true"></i>
							   <span class="nav-label">Movie Arrangement</span></a>
	                  	</li> 
                		</ul>
                  </li>	
            </ul>
        </div>
    </nav>
</div>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class=" fa fa-bars icon"></i> </a>
                    <form role="search" class="navbar-form-custom" action="search_results.html">
                        <div class="form-group">
                            <input type="text" class="form-control" name="top-search" id="top-search">
                        </div>
                    </form>
                </div>
           
				<ul class="nav navbar-top-links navbar-right">
                    <li>
                        <img src="img/ticketlite-sm.png" class="m-t-xs" alt="ticketlite Logo" >
                    </li>
                    <li>
                <a href="theater-owner-logout">
                    <i class="fa fa-sign-out"></i> Log out
                </a>
            </li>
                </ul>
            </nav>
        </div>

</body>
</html>