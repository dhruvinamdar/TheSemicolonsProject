document.addEventListener('readystatechange', event => { 
 
    if (event.target.readyState === "complete") {
        var currentdate = new Date();
	var datetime = "Last Login: " + currentdate.getDate() + "/" + currentdate.getMonth() 
		+ "/" + currentdate.getFullYear() + " @ " 
		+ currentdate.getHours() + ":" 
		+ currentdate.getMinutes() + ":" + currentdate.getSeconds();
	document.getElementById("LoginDate").innerHTML = datetime;
	console.log(datetime);
	var today = new Date().toISOString().split('T')[0];
	document.getElementsByName("orderDate")[0].setAttribute('min', today);

    }
});

// ================================================== Employee =======================================================================================

// ------------------------------------- AJAX Request to Get Customer Details and Product Data ------------------------------------------------------
function showProductTable(event){
	var cId = document.getElementById("customerId").value;
	console.log("customer Id is "+cId);
	var xhr = new XMLHttpRequest();
	if(window.XMLHttpRequest){
		xhr = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		xhr = new ActiveXObject("Microsoft.XMLHTTP"); 	
	}
	var str = "customerCreds="+cId;
	//var preference = getPreference();
	xhr.open("GET","getCustomerDetails?customerCreds="+cId, true);
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState===4 && xhr.status==200){
			var customerProductData = xhr.responseText;
			//alert(prodList);
			console.log("customer");
			console.log(customerProductData);
			showData(customerProductData);
		}
	}
	xhr.send();
	
	
	event.preventDefault();
	document.getElementById('toHideGST').style.display = "flex";
	document.getElementById('toHideAddress').style.display = "flex";
	document.getElementById('toHideCity').style.display = "flex";
	document.getElementById('toHidePincode').style.display = "flex";
	document.getElementById('toHideEmail').style.display = "flex";
	
    var form = document.getElementById('submitcustomer');
    form.style.display = "none";
    
}
// ----------------------------------------- Adding Products --------------------------------------------------------------------
function addProduct(event, id){
    event.preventDefault();
    // ------------------------------ Calculating Total Price ---------------------------------------------------------------------
    var productTable = document.getElementById("productTable");
    let totalPrice = parseFloat(0);
    for(let i = 0;i<productTable.rows.length-1;i++){
        var price = document.getElementById("productTable").rows[i + 1].cells.item(3).innerHTML;
        var quantityIdx = "quantity" + i;
        var quantity = document.getElementById(quantityIdx).value;
        if(quantity.localeCompare('') != '0'){
            totalPrice = parseFloat(totalPrice) + (parseFloat(price) * parseFloat(quantity));
        }
    }
    // -------------------------------- Calculating Shipping Cost -------------------------------------------------------------
    var shippingCost = 0;
    if(totalPrice < 100000){
        var productTable = document.getElementById("productTable");
        for(let i = 0;i<productTable.rows.length-1;i++){
            var productCategory = document.getElementById("productTable").rows[i + 1].cells.item(2).innerHTML;
            var productPrice = parseFloat(document.getElementById("productTable").rows[i + 1].cells.item(3).innerHTML);
            var quantityIdx = "quantity" + i;
            var quantity = document.getElementById(quantityIdx).value;
            if((productCategory.localeCompare("Level1") == '0') && (quantity.localeCompare('') != '0')){
                var value = parseFloat(productPrice * parseInt(quantity));
				shippingCost = shippingCost + parseFloat( value * 5  / 100);
				console.log("shipping cost "+ i + shippingCost);
            }
            else if((productCategory.localeCompare("Level2") == '0') && (quantity.localeCompare('') != '0')){
                var value = parseFloat(productPrice * parseInt(quantity));
				console.log(value)
				shippingCost = shippingCost + parseFloat( value * 3  / 100);
				console.log("shipping cost "+ i + shippingCost);
            }
            else{
                if(quantity.localeCompare('') != '0'){
					var value = parseFloat(productPrice * parseInt(quantity));
                    shippingCost = shippingCost + parseFloat( value * 2  / 100);
					console.log("shipping cost "+ i + shippingCost);
                }
            }

        }
    }
    document.getElementById("orderValue").value = totalPrice;
    document.getElementById("shippingCost").value = shippingCost;
}

// -------------------- Displaying Customer and Product Product on Web Page ------------------------------------------------------

function showData(customerProductData){
	var customerData = JSON.parse(customerProductData); 
	document.getElementById("customerGSTNo").value = customerData[0].customerGST;
	document.getElementById("inputAddress").value = customerData[0].customerAddress;
	document.getElementById("inputCity").value = customerData[0].customerCity;
	document.getElementById("inputEmail").value = customerData[0].customerEmail;
	document.getElementById("inputPincode").value = customerData[0].customerPincode;
	//xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	// creating dynamic table
    var table = document.createElement('table');
    // set the table "id"
	table.setAttribute('class', 'table table-hover table-striped');
    table.setAttribute('id', 'productTable');
    var arrHead = ['Product ID', 'Product Name', 'Product Category', 'Product Price', 'Quantity'];

    var tr = table.insertRow(-1);
    // creating table header
    for (var h = 0; h < arrHead.length; h++) {
        var th = document.createElement('th');          
        th.innerHTML = arrHead[h];
		th.setAttribute('scope', 'col');
        tr.appendChild(th);
    }

    for (var c = 0; c <= customerData[1].length - 1; c++) {
        tr = table.insertRow(-1);
        // table data
            var td = document.createElement('td');        
            td = tr.insertCell(-1);
			console.log(customerData[1][c].productId);
            td.innerHTML = customerData[1][c].productId;                 // ADD VALUES TO EACH CELL.
            td = tr.insertCell(-1);
            td.innerHTML = customerData[1][c].productName; 
            td = tr.insertCell(-1);
            td.innerHTML = customerData[1][c].category; 
            td = tr.insertCell(-1);
            td.id = "productPrice"; 
            td.innerHTML = customerData[1][c].price; 
            td = tr.insertCell(-1);
            var quantityOfProduct = document.createElement('input');
            quantityOfProduct.id = "quantity" + c;
            quantityOfProduct.type = 'number';
            quantityOfProduct.min = "0";
            td.appendChild(quantityOfProduct);
            
    }
// appending table to div
    document.getElementById('products').appendChild(table);
    //form.style.display = "block";
    var div = document.createElement('div');
    div.innerHTML = '<button class="btn btn-primary" id="addProduct'+c+'" onclick="addProduct(event, this.id)">Add Products</button>';
    document.getElementById('products').appendChild(div);
    // creating shipping cost field
    var shippingCostLabel = document.createElement('label');
    shippingCostLabel.innerHTML = "Shipping Cost : ";
    document.getElementById('products').appendChild(shippingCostLabel);
    var shippingCost = document.createElement('input');
    shippingCost.type = 'number';
    shippingCost.value = 0;
    shippingCost.setAttribute('id','shippingCost');
    shippingCost.readOnly = true;
    document.getElementById('products').appendChild(shippingCost);
    // creating order value field
    var totalOrderValueLabel = document.createElement('label');
    totalOrderValueLabel.innerHTML = "   Total Order Value : ";
    document.getElementById('products').appendChild(totalOrderValueLabel);
    var totalOrderValue = document.createElement('input');
    totalOrderValue.type = 'number';
    totalOrderValue.value = 0;
    totalOrderValue.setAttribute('id','orderValue');
    totalOrderValue.readOnly = true;
    document.getElementById('products').appendChild(totalOrderValue);
	document.getElementById('submitcustomer').style.display = "none";
	document.getElementById('submitQuote').style.display = "block";
}

function createQuote(event){
	event.preventDefault();
	var productTable = document.getElementById("productTable");
	var quote = { "orderDate": document.getElementById("orderDate").value,
			      "customerId" : document.getElementById("customerId").value,
				  "shippingCost" : document.getElementById("shippingCost").value,
				  "orderValue" : document.getElementById("orderValue").value,
					"products" :[]
				}
    for(let i = 0;i<productTable.rows.length-1;i++){
        var quantityIdx = "quantity" + i;
		var productQuantity = document.getElementById(quantityIdx).value;
		var quantityOfProduct = parseInt(productQuantity);
		if(productQuantity.localeCompare('') != 0 && quantityOfProduct > 1){
    		var product = {
				 			"productId" : document.getElementById("productTable").rows[i+1].cells.item(0).innerHTML,
			     			"quantity" : productQuantity
					  	}
			quote.products.push(product);
		}
		
	}
	
	quote = JSON.stringify(quote);
	console.log(quote);	
	var xhr = new XMLHttpRequest();
	if(window.XMLHttpRequest){
		xhr = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		xhr = new ActiveXObject("Microsoft.XMLHTTP"); 	
	}
	//var preference = getPreference();
	//xhr.open("GET","storeQuote?quote="+quote, true);
	xhr.open("POST","storeQuote", true)
	xhr.setRequestHeader("content-type","application/json");
	xhr.send(quote);
}

// -------------------------------- Display Last Login DateTime ---------------------------------------------------------------
/*function displayDate(){
	var currentdate = new Date();
	var datetime = "Last Login: " + currentdate.getDate() + "/" + currentdate.getMonth() 
		+ "/" + currentdate.getFullYear() + " @ " 
		+ currentdate.getHours() + ":" 
		+ currentdate.getMinutes() + ":" + currentdate.getSeconds();
	document.getElementById("loginDate").innerHTML = datetime;
	console.log(datetime);
	console.log("abc");
}
*/

// ================================================================================================================================


// ======================================================================= Customer ================================================

function showCustomerQuote(event){
	event.preventDefault();
	console.log("in show");
	var cId = document.getElementById("customerIdName").value;
	console.log("customer Id is "+cId);
	var xhr = new XMLHttpRequest();
	if(window.XMLHttpRequest){
		xhr = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		xhr = new ActiveXObject("Microsoft.XMLHTTP"); 	
	}
	//var preference = getPreference();
	xhr.open("GET","CustomerViewQuoteServlet?customerId="+cId, true);
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState===4 && xhr.status==200){
			var customerQuoteData = xhr.responseText;
			console.log(customerQuoteData);
			showOrderData(customerQuoteData);
		}
		
	}
	xhr.send();
}

// =================================================================================================================================
function showOrderData(customerQuoteData){
	var customerQuoteData = JSON.parse(customerQuoteData);
	
	// creating dynamic table
    var ordertable = document.createElement('table');
    // set the table "id"
	ordertable.setAttribute('class', 'table table-hover');
    ordertable.setAttribute('id', 'orderTable');
    var arrHead = ['Order ID', 'Order Date', 'Total Order Value', 'Shipping Cost', 'Show Details'];
	console.log(customerQuoteData);
    var tr = ordertable.insertRow(-1);
    // creating table header
    for (var h = 0; h < arrHead.length; h++) {
        var th = document.createElement('th');          
        th.innerHTML = arrHead[h];
		th.setAttribute('scope', 'col');
        tr.appendChild(th);
		th.style.background = '#007bff';
		th.style.color = 'white';
    }
	console.log(customerQuoteData.length);
    for (var c = 0; c <= customerQuoteData.length - 1; c++) {
        	tr = ordertable.insertRow(-1);
        	// table data
            var td = document.createElement('td');        
            td = tr.insertCell(-1);
			var orderId = customerQuoteData[c].orderId;
            td.innerHTML = customerQuoteData[c].orderId;                 // ADD VALUES TO EACH CELL.
            td = tr.insertCell(-1);
            td.innerHTML = customerQuoteData[c].orderDate; 
            td = tr.insertCell(-1);
            td.innerHTML = customerQuoteData[c].totalOrderValue; 
            td = tr.insertCell(-1);
			td.innerHTML = customerQuoteData[c].shippingCost; 
            td = tr.insertCell(-1);
			td.innerHTML = '<button type="submit" class="btn btn-primary" id="'+orderId+'" onclick="populateHiddenField(this.id)">Show Quote Details</button>';
			
    }
	document.getElementById('showOrders').style.display = 'none';
// appending table to div
    document.getElementById('orderData').appendChild(ordertable);
    
}

// -------------------------------------- Display Quote Details -----------------------------------------------------------------------------------
function displayQuoteDetails(orderId){
	var xhr = new XMLHttpRequest();
	if(window.XMLHttpRequest){
		xhr = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		xhr = new ActiveXObject("Microsoft.XMLHTTP"); 	
	}
	//var preference = getPreference();
	xhr.open("GET","CustomerViewDetailedQuoteServlet?orderId="+orderId, true);
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState===4 && xhr.status==200){
			var customerQuoteDetails = xhr.responseText;
			console.log(customerQuoteDetails);
		}
		
	}
	xhr.send();
}

function populateHiddenField(id){
	console.log(id);
	document.getElementById("hiddenField").value = id;
	document.getElementById("hiddenField").style.display = "block";
}

function sendOrderId(id){
	console.log(id);
	document.getElementById("hiddenFields").value = id;
	document.getElementById("hiddenFields").style.display = "block";
}

// ------------------------------------ Show Products ---------------------------------------------------------------------------------------------
function showListOfOrders(event){
	event.preventDefault();
	console.log("Show orders");
	document.getElementById('orderList').style.display = "none";
	console.log("in show");
	var cId = document.getElementById("customerIdName").value;
	console.log("customer Id is "+cId);
	var xhr = new XMLHttpRequest();
	if(window.XMLHttpRequest){
		xhr = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		xhr = new ActiveXObject("Microsoft.XMLHTTP"); 	
	}
	//var preference = getPreference();
	xhr.open("GET","CustomerViewOrdersServlet?customerId="+cId, true);
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState===4 && xhr.status==200){
			var customerQuoteData = xhr.responseText;
			console.log(customerQuoteData);
			showApprovedOrder(customerQuoteData);
		}
		
	}
	xhr.send();
	
}

function showQuotes(){
	document.getElementById("customerOrders").style.display = "none";
}

function showApprovedOrder(customerQuoteData){
	var customerQuoteData = JSON.parse(customerQuoteData);
	
	// creating dynamic table
    var ordersTable = document.createElement('table');
    // set the table "id"
	ordersTable.setAttribute('class', 'table table-hover');
    ordersTable.setAttribute('id', 'ordersTable');
    var arrHead = ['Order ID', 'Order Date', 'Total Order Value', 'Shipping Cost', 'Status', 'Show Invoice'];
	console.log(customerQuoteData);
    var tr = ordersTable.insertRow(-1);
    // creating table header
    for (var h = 0; h < arrHead.length; h++) {
        var th = document.createElement('th');          
        th.innerHTML = arrHead[h];
		th.setAttribute('scope', 'col');
        tr.appendChild(th);
		th.style.background = '#007bff';
		th.style.color = 'white';
    }
	console.log(customerQuoteData.length);
    for (var c = 0; c <= customerQuoteData.length - 1; c++) {
        	tr = ordersTable.insertRow(-1);
        	// table data
            var td = document.createElement('td');        
            td = tr.insertCell(-1);
			var orderId = customerQuoteData[c].orderId;
			console.log(orderId);
            td.innerHTML = customerQuoteData[c].orderId;                 // ADD VALUES TO EACH CELL.
            td = tr.insertCell(-1);
            td.innerHTML = customerQuoteData[c].orderDate; 
            td = tr.insertCell(-1);
            td.innerHTML = customerQuoteData[c].totalOrderValue; 
            td = tr.insertCell(-1);
			td.innerHTML = customerQuoteData[c].shippingCost; 
            td = tr.insertCell(-1);
			td.innerHTML = customerQuoteData[c].status; 
            td = tr.insertCell(-1);
			td.innerHTML = '<button type="submit" class="btn btn-primary" id="'+orderId+'"  onclick="sendOrderId(this.id)">Show Invoice</button>';
			
    }
// appending table to div
	
    document.getElementById('ordersWithInvoiceButton').appendChild(ordersTable);
	document.getElementById('orderList').style.display = "none";
	document.getElementById('ordersWithInvoiceButton').style.display = "block";
}

function showQuoteForm(){
	document.getElementById("customerOrders").style.display = "none";
	document.getElementById("customer").style.display = "block";	
}

function showOrderForm(){
	document.getElementById("customer").style.display = "none";	
	document.getElementById("customerOrders").style.display = "block";	
} 