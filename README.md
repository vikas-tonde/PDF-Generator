# PDF Generator using Spring Boot Application



The file path where you want to store the pdf file is required and need to add into the **application.properties** file .<br>

-------------------------------------------------------------------------

To test this application you need any HTTP client like postman, thunder client, fast API etc. <br>
Make the request as shown below: <br>
<summary><code>POST</code> <code><b>-</b></code> <code>http://localhost:8080/api/generatepdf</code></summary>

Add JSON request body in following format: <br>

    {
    "seller": "XYZ Pvt. Ltd.",
    "sellerGstin": "29AABBCCDD121ZD",
    "sellerAddress": "New Delhi, India",
    "buyer": "Vedant Computers",
    "buyerGstin": "29AABBCCDD131ZD",
    "buyerAddress": "New Delhi, India",
    "items": [
            {
            "name": "Product 1",
            "quantity": "12 Nos",
            "rate": 123.00,
            "amount": 1476.00
            }
        ]
    }


-------------------------------------------------------------------------
