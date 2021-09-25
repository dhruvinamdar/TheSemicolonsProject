<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<title>Quote Details</title>
</head>
<body>
<div class="Container">
<div class="row">
<div class="col-2"></div>
<div class="col-8">
	<form class="productForm" action="CustomerApproveQuoteServlet" method="post">
  		<div class="form-group row">
    		<label for="orderId" class="col-sm-2 col-form-label">Order Id</label>
    		<div class="col-sm-10">
    			<input type="text" name="orderIdProducts" class="form-control" id="orderIdProducts" value="${quote.orderId}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="orderDate" class="col-sm-2 col-form-label">Order Date</label>
    		<div class="col-sm-10">
    			<input type="date" class="form-control" id="orderDateProducts" value="${quote.orderDate}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="totalOrderValue" class="col-sm-2 col-form-label">Total Order Value</label>
    		<div class="col-sm-10">
    			<input type="number" class="form-control" id="totalOrderValueProducts" value="${quote.orderValue}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="shippingCost" class="col-sm-2 col-form-label">Shipping Cost</label>
    		<div class="col-sm-10">
    			<input type="number" class="form-control" id="shippingCostProducts" value="${quote.shippingCost}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="shippingAddress" class="col-sm-2 col-form-label">Shipping Address</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="shippingAddressProducts" value="${quote.shippingAddress}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="shippingAgency" class="col-sm-2 col-form-label">Shipping Agency</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="shippingAgencyProducts" value="${quote.shippingAgency}" readonly>
    		</div>
  		</div>
  		<div class="form-group row" >
    		<label for="status" class="col-sm-2 col-form-label">Status</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="statusProducts" value="Pending" style="color:red" readonly>
    		</div>	
  		</div>
  		
  		
  		<table class="table table-bordered table-hover">
  			<thead>
    			<tr>
     			 <th scope="col">Product Name</th>
      			 <th scope="col">Product Price</th>
      			 <th scope="col">Quantity</th>
    		    </tr>
  			</thead>
  			<tbody>
  			<c:forEach items="${ quote.products }" var="product">
			<tr>
				<td>${product.productName}</td>
				<td>${product.productPrice}</td>
				<td>${product.quantity}</td>
			</tr>	
			</c:forEach>
  			</tbody>
		</table>
		<button type="submit" class="btn btn-primary">Approve</button>
	</form>
	</div>
	</div>
</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="script/script.js"></script>
</body>
</html>