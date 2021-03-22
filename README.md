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

  <_Most endpoints will have many ways they can fail. From unauthorized access, to wrongful parameters etc. All of those should be liste d here. It might seem repetitive, but it helps prevent assumptions from being made where they should be._>

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

* **Success Response:**

* *localhost:8080/crimes/postcode=B461JU&date=2018-04*

  * **Code:** 200 <br />
    **Content:** 
`[
    {
        "category": "anti-social-behaviour",
        "location_type": "Force",
        "location": {
            "latitude": "52.514807",
            "street": {
                "id": 1222679,
                "name": "On or near Gorsey Lane"
            },
            "longitude": "-1.711563"
        },
        "context": "",
        "outcome_status": null,
        "persistent_id": "",
        "id": 64609701,
        "location_subtype": "",
        "month": "2018-04"
    }
]`
 
* **Error Response:**

  <_Most endpoints will have many ways they can fail. From unauthorized access, to wrongful parameters etc. All of those should be liste d here. It might seem repetitive, but it helps prevent assumptions from being made where they should be._>

  * **Code:** 400 BAD_REQUEST <br />
    **Content:** 
    `{ "status": 400, "message": "Invalid postcode", "timeStamp": 1616359940911 }`

  OR

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `{ "status": 404, "message": "No data found", "timeStamp": 1616360000202 }`
