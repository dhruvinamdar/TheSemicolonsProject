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
    var arrHead = ['Product ID', 'Product Name', 'Product Category', 'Product Price', 'Quantity'];

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
            td.id = "productPrice"; 
            td.innerHTML = products[c].productPrice; 
            td = tr.insertCell(-1);
            var quantityOfProduct = document.createElement('input');
            quantityOfProduct.id = "quantity" + c;
            quantityOfProduct.type = 'number';
            quantityOfProduct.min = "0";
            td.appendChild(quantityOfProduct);
            // td = tr.insertCell(-1);
            // var span = document.createElement('span');
            // span.innerHTML = '<button id="addProduct'+c +'" onclick="addProduct(event, this.id)">Add</button>';
            // var addBtn = document.createElement('button');
            // addBtn.innerHTML = 'Add';
            // addBtn.className = 'addProductBtn';
            // addBtn.id = 'addProduct' + c;
            // addBtn.setAttribute("onclick", addProduct);
            // td.appendChild(span);
    }
// appending table to div
    document.getElementById('products').appendChild(table);
    form.style.display = "block";

    var div = document.createElement('div');
    div.innerHTML = '<button class="btn btn-primary" id="addProduct'+c +'" onclick="addProduct(event, this.id)">Add Products</button>';
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
}

function addProduct(event, id){
    event.preventDefault();
    // calculating shipping cost and total order value
    // var rowIdx = id.charAt(id.length - 1);
    // rowIdx = parseInt(rowIdx) + 1;

    // console.log(rowIdx);
    // var price = document.getElementById("productTable").rows[rowIdx].cells.item(3).innerHTML;
    // console.log(price);
    // var previousShippingCost = parseInt(document.getElementById("shippingCost").value);
    var productTable = document.getElementById("productTable");
    let totalPrice = parseInt(0);
    for(let i = 0;i<productTable.rows.length-1;i++){
        var price = document.getElementById("productTable").rows[i + 1].cells.item(3).innerHTML;
        var quantityIdx = "quantity" + i;
        var quantity = document.getElementById(quantityIdx).value;
        console.log(price + " " + quantity);
        if(quantity.localeCompare('') != '0'){
            totalPrice = parseInt(totalPrice) + (parseInt(price) * parseInt(quantity));
            // console.log(totalPrice)
        }
    }
    console.log(totalPrice);
    document.getElementById("shippingCost").value = totalPrice;

}
// timestamp => last loggin
// order table => orders