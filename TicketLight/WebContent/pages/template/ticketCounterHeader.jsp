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
                <li class="nav-header" style="padding-top: 11px;">
                    <div class="dropdown profile-element"> <span>
                            <img alt="image" style="width: 42%;height: 71px;" class="img-circle" src="getTicketCounterPersonProfileImage?modelId=<s:property value="%{#session.ticket_counter_person_id}"/>" />
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold"><s:property value="%{#session.name}"/></strong>
                             </span> <span class="text-muted text-xs block">Ticket Counter Person<b class="caret"></b></span> </span> </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <!-- <li><a href="theater-counter-profile-view">Profile</a></li>
                            <li><a href="change-password">Change password</a></li>
                            <li class="divider"></li> -->
                            <li><a href="ticket-counter-logout">Logout</a></li>
                        </ul>
                       
                    </div>
                    <div class="logo-element">
                    </div>
                </li>
                 
	                <li class="dashboardCls">
		               <a href="ticket-counter-person-login-success"><i class="fa fa-ticket" aria-hidden="true"></i>
					 <span class="nav-label">Dashboard</span></a>
	               </li> 
	                <li class="active">
                    <a href="viewMoviesInCounter"><i class="fa fa-edit"></i><span class="nav-label">Movies</span></a>
                </li>
	             

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
                        <img src="img/ticketlite-sm.png" alt="ticketlite Logo" >
                    </li>
                </ul>
            </nav>
        </div>

</body>
</html>