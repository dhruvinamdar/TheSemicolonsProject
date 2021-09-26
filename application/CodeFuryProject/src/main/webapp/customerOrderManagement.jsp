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

    <body>
        <div class="order-customer">
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary bs-side-navbar">
                <img src="images/logo.jpg" class="navbar-brand logo">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <span style="display:flex">
                                <span style="color:white">Welcome- </span>
                                <input type="text" class="user-info" value="${customer.customerName}" readonly>
                            </span>
                            <span class="employeeData user-info" id="LoginDate">
                        </li>
                        <li class="nav-item active">
                            <button class="nav-link" style="background:none; border:none" onclick="showQuoteForm()">List
                                Of Quotes</a>
                        </li>
                        <li class="nav-item">
                            <button class="nav-link" style="background:none; border:none" onclick="showOrderForm()">List
                                Of Orders</button>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="customerLogin.jsp">Sign Out</a>
                        </li>
                    </ul>

                </div>
            </nav>
            <div class="customer" id="customer">
                <form class="newQuote" id="newQuote" action="CustomerViewDetailedQuoteServlet" method="get">

                    <div class="form-group row">
                        <label for="customerId" class="col-sm-2 col-form-label">Customer Id/Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="customerIdName" value="${customer.customerName}"
                                readonly>
                        </div>
                    </div>
                    <input type="text" name="orderId" id="hiddenField" style="display:none">
                    <div id="orderData"></div>
                    <button type="button" class="btn btn-primary" onclick="showCustomerQuote(event)"
                        id="showOrders">Show Quotes</button>
                </form>


            </div>

            <div class="customerOrders" id="customerOrders" style="display:none">
                <form class="newQuote" id="newQuote" action="CustomerViewInvoiceServlet" method="get">
                    <div class="form-group row">
                        <label for="customerId" class="col-sm-2 col-form-label">Customer Id/Name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="customerIdName" value="${customer.customerName}"
                                readonly>
                        </div>
                    </div>
                    <input type="text" name="orderId" id="hiddenFields" style="display:none">
                    <div id="ordersWithInvoiceButton"></div>
                    <button type="button" class="btn btn-primary" id="orderList" onclick="showListOfOrders(event)">Show
                        Orders</button>
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