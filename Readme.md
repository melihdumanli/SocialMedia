
# SocialMediaWebApp

This is a sample social media web app that was developed with Java and can be accessible with computers and mobile devices.


## Authors

- [@muhammedali55](https://github.com/muhammedali55)
- [@melihdumanli](https://www.github.com/melihdumanli)


## Roadmap

- Amazon EC2 for databases.
- Google Cloud Kubernetes Engine for Load Balancers 

- RabbitMQ
- Zipkin
- ElasticSearch
- Spring Security
- Redis
- CircuitBraker
- GraphQL
- Pagination


## Tech Stack

**Client:** React, Redux

**Server:** Node, Kubernetes, Docker


## System Configuration

![App Screenshot](https://github.com/melihdumanli/SocialMedia/blob/master/System%20Configuration.png)


## Environment Variables

To run this project, you will need to add the following environment variables to your keys.bat file

For auth-service :

`auth_service_port`
`auth_service_spring_rabbit_host`
`auth_service_spring_rabbit_port`
`auth_service_spring_rabbit_username`
`auth_service_spring_rabbit_password`
`auth_service_datasource_url`
`auth_service_datasource_username`
`auth_service_datasource_password`
`auth_service_zipkin_baseurl`
`auth_service_myapplication_userservice`

For elastic-service:

`elastic_service_server_port`
`elastic_service_spring_rabbit_host`
`elastic_service_spring_elastic_uri`
`elastic_service_spring_rabbit_port`
`elastic_service_spring_rabbit_username`
`elastic_service_spring_rabbit_password`

For user-service:

`user_service_port`
`user_service_spring_rabbit_host`
`user_service_spring_rabbit_port`
`user_service_spring_rabbit_username`
`user_service_spring_rabbit_password`
`user_service_data_mongodb_database`
`user_service_data_mongodb_username`
`user_service_data_mongodb_password`
`user_service_data_mongodb_host`
`user_service_data_mongodb_port`
`user_service_zipkin_baseurl`

For web-service:

`web_service_server_port`
`web_service_zipkin_baseurl`

For api-gateway-service:

`api_gateway_service_server_port`
`api_gateway_service_zipkin_baseurl`

For config-service:

`config_service_zipkin_baseurl`
`config_security_username`
`config_security_password`
`token_security_key_sign`


## Feedback

If you have any feedback, please reach me at melihdumanli@hotmail.com

