<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>


<body class="fx-greycolor">
<header id="header" class="bg-white">
   <div class="container">
    <div class="row ">
      <div class="col-md-3"> 
        <div class="pull-left">
        <a href="#" class="scrollto "><img src="images/ticketlite.png" width="40%;" class="log"></a>         
         </div>
          </div>
          <div class="col-md-9">
          <nav id="nav-menu-container">
          <ul class="nav-menu m-t-sm">
          <li class="menu-active"><a href="home-page">Home</a></li>
          <!-- <li class="menu-active"><a href="user-home-page">End user</a></li> -->
          <li><a href="#">Movies</a></li>
          <li><a href="theater-owner-registration">Signup</a></li>
          <li class="dropdown">
              <a href="#" class="" data-toggle="dropdown">Login <b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="theater-owner-login">Theatre-Owner Login</a></li>
                <li><a href="ticket-counter-login">Ticket-Counter Login</a></li>
                <li><a href="government-user-login">Government Login</a></li>
              </ul>
           </li>
        </ul>
      </nav>
      </div>
    </div>
    
    </div>
                           
      
    </header>
    
    
<script type="text/javascript">
$('ul.nav li.dropdown').hover(function() 
{
  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
},
function() 
{
  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
});
</script>

</body>
</html>