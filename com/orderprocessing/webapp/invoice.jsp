<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<title>Show Invoice</title>
</head>
<body>
<div class="Container">
<div class="row">
<div class="col-2"></div>
<div class="col-8">
	<form class="productForm" action="" method="post">
  		<div class="form-group row">
    		<label for="invoiceId" class="col-sm-2 col-form-label">Invoice Id</label>
    		<div class="col-sm-10">
    			<input type="text" name="" class="form-control" id="invoiceId" value="${invoice.invoiceId}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="invoiceDate" class="col-sm-2 col-form-label">Invoice Date</label>
    		<div class="col-sm-10">
    			<input type="date" class="form-control" id="invoiceDate" value="${invoice.invoiceDate}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="totalGSTAmount" class="col-sm-2 col-form-label">Total GST Amount</label>
    		<div class="col-sm-10">
    			<input type="number" class="form-control" id="totalGSTAmount" value="${invoice.totalGstAmount}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="totalInvoiceValue" class="col-sm-2 col-form-label">Total Invoice Value</label>
    		<div class="col-sm-10">
    			<input type="number" class="form-control" id="totalInvoiceValue" value="${invoice.totalInvoiceValue}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="invoiceStatus" class="col-sm-2 col-form-label">Invoice Status</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="invoiceStatus" value="${invoice.invoiceStatus}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="orderId" class="col-sm-2 col-form-label">Order Id</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="orderId" value="${invoice.orders.orderId}" readonly>
    		</div>
  		</div>
  		<div class="form-group row" >
    		<label for="orderDate" class="col-sm-2 col-form-label">Order Date</label>
    		<div class="col-sm-10">
    			<input type="date" class="form-control" id="orderDate" value="${invoice.orders.orderDate }" readonly>
    		</div>	
  		</div>
  		<div class="form-group row">
    		<label for="totalOrderValue" class="col-sm-2 col-form-label">Total Order Value</label>
    		<div class="col-sm-10">
    			<input type="number" class="form-control" id="totalOrderValue" value="${invoice.orders.totalOrderValue}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="shippingCost" class="col-sm-2 col-form-label">Shipping Cost</label>
    		<div class="col-sm-10">
    			<input type="number" class="form-control" id="shippingCost" value="${invoice.orders.shippingCost}" readonly>
    		</div>
    	</div>
  		<div class="form-group row">
    		<label for="shippingAgency" class="col-sm-2 col-form-label">Shipping Agency</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="shippingAgency" value="${invoice.orders.shippingAgency}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="customerName" class="col-sm-2 col-form-label">Customer Name</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="customerName" value="${invoice.customer.customerName}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="customerAddress" class="col-sm-2 col-form-label">Customer Address</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="customerAddress" value="${invoice.customer.customerAddress}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="customerCity" class="col-sm-2 col-form-label">Customer City</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="customerCity" value="${invoice.customer.customerCity}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="customerState" class="col-sm-2 col-form-label">Customer State</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="customerState" value="${invoice.customer.customerState}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="customerEmail" class="col-sm-2 col-form-label">Customer Email</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="customerEmail" value="${invoice.customer.customerEmail}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="customerPincode" class="col-sm-2 col-form-label">Customer Pincode</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="customerPincode" value="${invoice.customer.customerPincode}" readonly>
    		</div>
  		</div>
  		<div class="form-group row">
    		<label for="customerContact" class="col-sm-2 col-form-label">Customer Contact</label>
    		<div class="col-sm-10">
    			<input type="text" class="form-control" id="customerContact" value="${invoice.customer.customerContact}" readonly>
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
  			<c:forEach items="${ invoice.productDetails }" var="product">
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