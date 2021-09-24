// ------------------------------------- AJAX Request to Get CCustomer Details and Product Data ------------------------------------------------------
function showProductTable(event){
	event.preventDefault();
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
	
	//step 4
	
    
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
                shippingCost = shippingCost + parseFloat( productPrice * 5  / 100);
            }
            else if((productCategory.localeCompare("Level2") == '0') && (quantity.localeCompare('') != '0')){
                shippingCost = shippingCost + parseFloat( productPrice * 3  / 100);
            }
            else{
                if(quantity.localeCompare('') != '0'){
                    shippingCost = shippingCost + parseFloat( productPrice * 2  / 100);
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
function displayDate(){
	var currentdate = new Date();
	var datetime = "Last Login: " + currentdate.getDate() + "/" + currentdate.getMonth() 
		+ "/" + currentdate.getFullYear() + " @ " 
		+ currentdate.getHours() + ":" 
		+ currentdate.getMinutes() + ":" + currentdate.getSeconds();
	document.getElementById("loginDate").innerHTML = datetime;
	console.log(datetime);
	console.log("abc");
}