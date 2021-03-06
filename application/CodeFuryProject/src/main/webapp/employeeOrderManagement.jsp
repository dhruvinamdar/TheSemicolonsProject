<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400&display=swap" rel="stylesheet">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Order Management</title>
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body onload="displayDate()">
        <div class="order-employee">
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary bs-side-navbar" id="navbar">
                <img src="images/logo.jpg" class="navbar-brand logo">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <span class="employeeData">
                                <span>Welcome-</span>
                                <input class="user-info" value="${employee.employeeName}" readonly>
                            </span>
                            <span class="employeeData user-info" id="LoginDate">
                            </span>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link">Create a New Quote<span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <form action="importProductsBase" method="post">
                                <button class="nav-link" id="importBtn" style="border:none; background:none">Import
                                    Products</button>
                            </form>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">Sign Out</a>
                        </li>
                    </ul>
                </div>
            </nav>
            <div class="employee">
                <form class="newQuote" id="quoteForm">
                    <div class="form-group row">
                        <label for="oderDate" class="col-sm-2 col-form-label">Order Date</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control datepicker" id="orderDate" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="customerName" class="col-sm-2 col-form-label">Customer Id/Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="customerId"
                                placeholder="Enter Customer Id or Name" required>
                        </div>
                    </div>
                    <div class="form-group row" id="toHideGST">
                        <label for="customerGSTNo" class="col-sm-2 col-form-label">Customer GST No</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="customerGSTNo" readonly>
                        </div>
                    </div>
                    <div class="form-group row" id="toHideAddress">
                        <label for="inputAddress" class="col-sm-2 col-form-label">Address</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputAddress" readonly>
                        </div>
                    </div>
                    <div class="form-group row" id="toHideCity">
                        <label for="inputCity" class="col-sm-2 col-form-label">City</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputCity" readonly>
                        </div>
                    </div>
                    <div class="form-group row" id="toHidePincode">
                        <label for="inputPincode" class="col-sm-2 col-form-label">Pincode</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPincode" readonly>
                        </div>
                    </div>
                    <div class="form-group row" id="toHideEmail">
                        <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmail" readonly>
                        </div>
                    </div>
                    <div id="products"></div>
                    <button type="submit" class="btn btn-primary" onclick="showProductTable(event)"
                        id="submitcustomer">Submit</button>
                    <button type="submit" class="btn btn-primary" id="submitQuote" style="display:none"
                        onclick="createQuote(event)"> Generate Quote </button>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
        <script src="script/script.js"></script>
    </body>

    </html>