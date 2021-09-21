// const fs = require('fs');
// var data = fs.readFileSync('../json/products.json','utf-8');
// var products = JSON.parse(data);
let products = [
    {
        "productId": "1",
        "productName": "Mobile",
        "productCategory": "Level1",
        "productPrice":40000
    },
    {
        "productId": "2",
        "productName": "Desktop",
        "productCategory": "Level2",
        "productPrice":64500
    },
    {
        "productId": "3",
        "productName": "tablet",
        "productCategory": "Level3",
        "productPrice":30000
    },
    {
        "productId": "4",
        "productName": "laptop",
        "productCategory": "Level2",
        "productPrice":60000
    },
    {
        "productId": "5",
        "productName": "mouse",
        "productCategory": "Level3",
        "productPrice":3000
    }
]; 
function showProductTable(event){
    event.preventDefault();
    var form = document.getElementById('submitcustomer');
    form.style.display = "none";
    // creating dynamic table
    var table = document.createElement('table');
    // set the table "id"
    table.setAttribute('id', 'productTable');
    var arrHead = ['Product ID', 'Product Name', 'Product Category', 'Product Price', 'Quantity', 'Add Product'];

    var tr = table.insertRow(-1);
    // creating table header
    for (var h = 0; h < arrHead.length; h++) {
        var th = document.createElement('th');          
        th.innerHTML = arrHead[h];
        tr.appendChild(th);
    }

    for (var c = 0; c <= products.length - 1; c++) {
        tr = table.insertRow(-1);
        // table data
            var td = document.createElement('td');        
            td = tr.insertCell(-1);
            td.innerHTML = products[c].productId;                 // ADD VALUES TO EACH CELL.
            td = tr.insertCell(-1);
            td.innerHTML = products[c].productName; 
            td = tr.insertCell(-1);
            td.innerHTML = products[c].productCategory; 
            td = tr.insertCell(-1);
            td.innerHTML = products[c].productPrice; 
            td = tr.insertCell(-1);
            var quantityOfProduct = document.createElement('input');
            quantityOfProduct.type = 'number';
            quantityOfProduct.min = 0;
            quantityOfProduct.placeholder = 'Quantity';
            td.appendChild(quantityOfProduct);
            td = tr.insertCell(-1);
            var addBtn = document.createElement('button');
            addBtn.innerHTML = 'Add';
            addBtn.className = 'addProductBtn';
            addBtn.id = 'addProduct' + c;
            addBtn.onclick = addProduct(event);
            td.appendChild(addBtn);
    }
// appending table to div
    document.getElementById('products').appendChild(table);
    form.style.display = "block";
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
}

function addProduct(event){
    event.preventDefault();
    // calculating shipping cost and total order value
    console.log();

}