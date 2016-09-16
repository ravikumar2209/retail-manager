Retail Manager Application

  Retail Manager Application exposes two REST endpoints one for adding shops and the other one for finding the nearest shop.
  
  1) Add Shop API:
      This API takes the shop name and Shop Address as input and uses the google's geocoding API to fetch the latitude and longitude 
      and internally stores it in the in-memory database.
      
    Sample Input:
    
    URI: http://<hostname>:8080/retail-manager/shop/add
    
    POST
      
    {
	  "shopName" : "ABC corp",
	    "shopAddress" : {
		    "number": "1600 Amphitheatre Parkway Mountain View CA",
	    	"postCode": 94035
	    }
    }
    
    Response : 200 OK
    
  
  2) Fetching a nearest shop
  
      This API takes the latitude and longitude as input and tries to fetch the nearest shop from the list of in-memory shops.
      
      Sample input:
      
      GET
      
      URI: http://localhost:8080/retail-manager/shop/findNearest?lat=36.7783&lng=-119.41
      
      Sample Response:
      
      {
        "shopName": "HDFC Bank",
          "shopAddress": {
            "number": "New Agaram Rd, Tambaram, Chennai, Tamil Nadu",
            "postCode": 600126
          }
      }
        
