# CoffeeControllerApi

All URIs are relative to *https://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addJsonCoffeeUsingPOST**](CoffeeControllerApi.md#addJsonCoffeeUsingPOST) | **POST** /coffee/ | addJsonCoffee
[**getAllUsingGET**](CoffeeControllerApi.md#getAllUsingGET) | **GET** /coffee/ | getAll
[**getByIdUsingGET**](CoffeeControllerApi.md#getByIdUsingGET) | **GET** /coffee/{id} | getById


<a name="addJsonCoffeeUsingPOST"></a>
# **addJsonCoffeeUsingPOST**
> Coffee addJsonCoffeeUsingPOST(request)

addJsonCoffee

### Example
```java
// Import classes:
//import com.example.waiterservice.swagger_client.invoker.ApiException;
//import com.example.waiterservice.swagger_client.api.CoffeeControllerApi;


CoffeeControllerApi apiInstance = new CoffeeControllerApi();
CoffeeRequest request = new CoffeeRequest(); // CoffeeRequest | request
try {
    Coffee result = apiInstance.addJsonCoffeeUsingPOST(request);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CoffeeControllerApi#addJsonCoffeeUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **request** | [**CoffeeRequest**](CoffeeRequest.md)| request |

### Return type

[**Coffee**](Coffee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="getAllUsingGET"></a>
# **getAllUsingGET**
> List&lt;Coffee&gt; getAllUsingGET()

getAll

### Example
```java
// Import classes:
//import com.example.waiterservice.swagger_client.invoker.ApiException;
//import com.example.waiterservice.swagger_client.api.CoffeeControllerApi;


CoffeeControllerApi apiInstance = new CoffeeControllerApi();
try {
    List<Coffee> result = apiInstance.getAllUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CoffeeControllerApi#getAllUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Coffee&gt;**](Coffee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="getByIdUsingGET"></a>
# **getByIdUsingGET**
> Coffee getByIdUsingGET(id)

getById

### Example
```java
// Import classes:
//import com.example.waiterservice.swagger_client.invoker.ApiException;
//import com.example.waiterservice.swagger_client.api.CoffeeControllerApi;


CoffeeControllerApi apiInstance = new CoffeeControllerApi();
Long id = 789L; // Long | id
try {
    Coffee result = apiInstance.getByIdUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CoffeeControllerApi#getByIdUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

[**Coffee**](Coffee.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

