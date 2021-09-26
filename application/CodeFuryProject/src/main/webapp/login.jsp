<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
   <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400&display=swap" rel="stylesheet">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, , shrink-to-fit=no">
    <title>Order Processing</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel = "stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>  
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<form action="loginEmployee" method="post">
    <section class="ftco-section">
        <div class="container">
            <div class="row justify-content-center">

                <div class="col-md-7 col-lg-5">
                    <div class="login-wrap p-4 p-md-5">
                        <div class="icon d-flex align-items-center justify-content-center">
                            <span class="fa fa-user-o"></span>
                        </div>
                        <h3 class="text-center mb-4">Sign In</h3>
                                <form action="#" class="login-form">
                            <div class="form-group">
                                <input type="text" class="form-control rounded-left" name="username" id="username" placeholder="Username" required>
                            </div>
                        <div class="form-group d-flex">
                        <input type="password" class="form-control rounded-left" name="password" id="password" placeholder="Password" required>
                        
                        </div>
                        <div class="form-group">
                            <button type="submit" class="form-control btn btn-primary rounded submit px-3">Login</button>
                            
                            <input type="text" name="ErrorMessage" value="${ErrorMessage}" style="border:none; color:red;" readonly>
                        </div>
                        <div class="row">
                            <div class="col-4"></div>
                            <div class="form-group d-md-flex">
                                <a href="customerLogin.jsp" class="customer-login">Sign In As Customer?</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    </form>
</body>
</html>