# crime-microservice



**Fetching crime data**
----
A Spring Boot microservice that allows for the retrival of data using third party APIs.

* **URLs**

/crime/categories

/crimes?postcode={postcode}&date={yyyy-mm}

* **Method:**
  
  <_/crime/categories_>

  `GET`
  
*  **URL Params**

   <_No parameters taken for this endpoint_> 

* **Success Response:**
  
* *localhost:8080/crime/categories*

  * **Code:** 200 <br />
    **Content:**    
    `{
        "url": "all-crime",
        "name": "All crime"
    },
    {
        "url": "anti-social-behaviour",
        "name": "Anti-social behaviour"
    },
    {
        "url": "bicycle-theft",
        "name": "Bicycle theft"
    },`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** 
    `{
    "timestamp": "2021-03-20T20:44:27.338+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "",
    "path": "/crime/categoriess"
     }`

* **Method:**
  
  <_/crimes?postcode={postcode}&date={yyyy-mm}_>

  `GET`
  
*  **URL Params**

   **Required:**
 
   `postcode=[alphanumeric]`
 
   `date=[yyyy-mm]`
   
   **Note: This web service makes use of third party APIs. Consider validating the postcode before expecting a valid response.**
 
   `api.postcodes.io/postcodes/{postcode}/validate`
   

* **Success Response:**

* *localhost:8080/crimes/postcode=B461JU&date=2019-11*

  * **Code:** 200 <br />
    **Content:** 
`[
    {
        "category": "burglary",
        "location_type": "Force",
        "location": {
            "latitude": "52.510194",
            "street": {
                "id": 1222617,
                "name": "On or near Augustus Close"
            },
            "longitude": "-1.711033"
        },
        "context": "",
        "outcome_status": {
            "category": "Investigation complete; no suspect identified",
            "date": "2019-12"
        },
        "persistent_id": "0c51a9368d6d715a7046edc59fc54a680ed7aa82d367413ff4461c563afc6be8",
        "id": 79187741,
        "location_subtype": "",
        "month": "2019-11"
    }
]`
 
* **Error Response:**

  * **Code:** 400 BAD_REQUEST <br />
    **Content:** 
    `{ "status": 400, "message": "Invalid postcode", "timeStamp": 1616359940911 }`

  OR

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `{ "status": 404, "message": "No data found", "timeStamp": 1616360000202 }`
