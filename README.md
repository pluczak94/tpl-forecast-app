#Weather APP TCP

###Overview
The Weather App is a RESTful service supplying a forecast for provided coordinates.

###Endpoints:
######/api/currentForecast
######Method: GET
######Paramaters: 
   **latitude** Valid range:(-90, 90) <br>
   **longitude** Valid range:(-180, 180) <br>
######Response:
```json
{
  "currentTemp": 27.4,
  "currentHumidity": 78,
  "forecastDays": [
    {
      "day": [
        2021,
        12,
        26
      ],
      "tempDay": 28.01,
      "tempNight": 27.32,
      "humidity": 0
    },
   
  ]
}
```
######Description:
Service call OpenWeather Api for provided parameters and  return formatted response.


###Components affected by Service:
    -GKE, Service is deployed on GKE with Deployment and LoadBalancer
    -Cloud Build, Service is tested, built, deployed with CloudBuild
    -Cloud Logging, Service logs are aggregated by Cloud Logging
    -Cloud Monitoring, Service is monitored by Cloud Monitoring
    -Cloud Trace, Service Trace can be checked in Cloud Trace
    -Cloud Memorystore Redis, Service is caches OpenWeather responses in Redis
    -Secret Manager, API key is stored in SecretManager











